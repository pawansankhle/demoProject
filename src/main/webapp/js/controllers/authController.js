app.
<<<<<<< HEAD
controller('authCtrl',['$scope', '$rootScope','STATS','AUTH_EVENTS','AuthService','SessionSrv','$state','Msgs','CartSrv','$mdDialog',
function ($scope, $rootScope,STATS,AUTH_EVENTS, AuthService,SessionSrv,$state,Msgs,CartSrv,$mdDialog) {
	$scope.errorDialog = false;
	$scope.status = 'init';
=======
controller('authCtrl',['$scope', '$rootScope','STATS','AUTH_EVENTS','AuthService','SessionSrv','$state','Msgs','CartSrv','$uibModal','GLOBAL_APP','$uibModalStack',
                       function ($scope, $rootScope,STATS,AUTH_EVENTS, AuthService,SessionSrv,$state,Msgs,CartSrv,$uibModal,GLOBAL_APP,	$uibModalStack) {
	$scope.errorDialog = false;
	$scope.isLoginLoading = false;
	$scope.isSignupLoading = false;
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
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
<<<<<<< HEAD
		$scope.status = 'progress';
		AuthService.login(credentials).then(function (res) {
			$scope.status = 'init';
			switch(res.status){
=======
		$scope.isLoginLoading = true;
	    AuthService.login(credentials).then(function (res) {
	    	$scope.isLoginLoading = false;
	        switch(res.status){
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
			case 204:
				$scope.message = Msgs.loginFailedMsg;
				$scope.errorDialog = true;
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				$scope.disable=!$scope.disable;
				$scope.reset();
				//$scope.close();
				break;
			case 200:
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$rootScope.setCurrentUser(res.data);
				$scope.disable=!$scope.disable;
				$scope.reset();
<<<<<<< HEAD
				$mdDialog.hide();
=======
				$scope.close();
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
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
<<<<<<< HEAD
		console.log('form::',userForm);
		console.log(userForm.password + "::" +$scope.repassword)
=======

	    $scope.isSignupLoading = true;
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
		if(userForm.password == $scope.repassword){
			$scope.status = 'progress';
			AuthService.signup(userForm).then(function(res){
<<<<<<< HEAD
				$scope.status = 'init';
=======
			 $scope.isSignupLoading = false;
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
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
					$scope.close();
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
  
  $scope.authModal = function(modalFor){
				
			   /* if(modalFor == 'login')
	            {
                    //$uibModalInstance.close();
					$scope.showlogin = true;
					$scope.showsignup = false;
					$scope.title="Login"
						
				}
	            if(modalFor == 'signup')
	            {
				    //$uibModalInstance.close();
				    $scope.showlogin = false;
				    $scope.showsignup = true;
				    $scope.title="Signup"
				    
					
				}*/
                
				var modelInstance = $uibModal.open({
      			animation: true,
      			templateUrl: GLOBAL_APP.loginTplPath,
      			controller: 'authCtrl',
      			size: 'lg',
      			
    		});
            
		};
   
   $scope.close = function(){
   	$uibModalStack.dismissAll();
   	
   }

}]);


