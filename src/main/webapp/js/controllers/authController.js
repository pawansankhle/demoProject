	app.
	controller('authCtrl',['$scope', '$rootScope','STATS','AUTH_EVENTS','AuthService','SessionSrv','$state',
	function ($scope, $rootScope,STATS,AUTH_EVENTS, AuthService,SessionSrv,$state) {
	  $scope.credentials = {
	    username: '',
	    password: ''
	  };
	  $scope.signUpForm = {
	    username: '',
	    email: '',
	    password: '',
	    
	  };
	 $scope.login = function (credentials) {
		  AuthService.login(credentials).then(function (user) {
		  $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
		  $scope.setCurrentUser(user);
		  $state.go(STATS.home);
	    }, function () {
	      $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
	    });
	  };
	  $scope.signUp = function(userForm){
		  if(userForm.password == $scope.repassword){
			AuthService.signup(userForm).then(function (user) {
				  $rootScope.$broadcast(AUTH_EVENTS.signUpSuccess);
				  $scope.setCurrentUser(user);
				  $state.go(STATS.home);
				}, function () {
				  $rootScope.$broadcast(AUTH_EVENTS.signupFailed);
				});
		  }else{
			  
			  Notifier.error("Signup","Both Password must be same");
		  }
		 	  
	  };
	}]);
	
	
