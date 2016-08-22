app.
controller('authCtrl',['$scope', '$rootScope','STATS','AUTH_EVENTS','AuthService','SessionSrv','$state','Msgs','CartSrv','$mdDialog',
function ($scope, $rootScope,STATS,AUTH_EVENTS, AuthService,SessionSrv,$state,Msgs,CartSrv,$mdDialog) {
	$scope.errorDialog = false;
	$scope.status = 'init';
	$scope.credentials = {
			username: '',
			password: ''
	};
	$scope.signUpForm = {
			username: '',
			email: '',
			password: '',
			address: {}

	};

	$scope.cancel = function() {
    $mdDialog.cancel();
  };
	$scope.login = function (credentials) {
		$scope.status = 'progress';
		AuthService.login(credentials).then(function (res) {
			$scope.status = 'init';
			switch(res.status){
			case 204:
				$scope.message = Msgs.loginFailedMsg;
				$scope.errorDialog = true;
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				$scope.disable=!$scope.disable;
				$scope.reset();
				break;
			case 200:
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$rootScope.setCurrentUser(res.data);
				$scope.disable=!$scope.disable;
				$scope.reset();
				$mdDialog.hide();
				break;

			}
		});
	};
	$scope.logout = function () {
		AuthService.logout().then(function (res) {
			$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
			$rootScope.setCurrentUser(null);
			
			CartSrv.getCart().then(function(cart){ $rootScope.shoppingCart = cart; $rootScope.count = cart.items.length;});
			$state.go(STATS.home);
			$scope.reset();


		}, function () {
			$rootScope.$broadcast(AUTH_EVENTS.logoutFailed);
		});
	};
	$scope.signUp = function(userForm){
		console.log('form::',userForm);
		console.log(userForm.password + "::" +$scope.repassword)
		if(userForm.password == $scope.repassword){
			$scope.status = 'progress';
			AuthService.signup(userForm).then(function(res){
				$scope.status = 'init';
				switch(res.status){
				case 204:
					$scope.message = Msgs.signupErrorMsg;
					$scope.errorDialog = true;
					$rootScope.$broadcast(AUTH_EVENTS.signupFailed);
					$scope.disable=!$scope.disable;
					$scope.reset();
					break;
				case 200:
					$rootScope.$broadcast(AUTH_EVENTS.signUpSuccess);
					$rootScope.setCurrentUser(res.data);
					$scope.disable=!$scope.disable;
					$scope.reset();
					$mdDialog.hide();
					break;
				}
			});

		}else{
              toastr.error("Both Password must be same", "Signup")
		}

	};
    $scope.reset = function(){
            $scope.credentials = {
			username: '',
			password: ''
	};
	$scope.signUpForm = {
			username: '',
			email: '',
			password: '',
			address: {}

	};
  };
  $scope.reset();
}]);


