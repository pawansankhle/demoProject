app.
controller('authCtrl',['$scope', '$rootScope','STATS','AUTH_EVENTS','AuthService','SessionSrv','$state','Msgs','CartSrv',
                       function ($scope, $rootScope,STATS,AUTH_EVENTS, AuthService,SessionSrv,$state,Msgs,CartSrv) {
	$scope.errorDialog = false;
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
	$scope.login = function (credentials) {
		AuthService.login(credentials).then(function (res) {
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
		if(userForm.password == $scope.repassword){
			AuthService.signup(userForm).then(function(res){
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


