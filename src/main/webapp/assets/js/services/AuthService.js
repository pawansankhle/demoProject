app.factory('AuthService',['$http','baseUrl', 'SessionSrv','Msgs','$rootScope', function ($http, baseUrl, SessionSrv,Msgs,$rootScope) {
  var authService = {};
 
  authService.login = function (credentials) {
  	  $rootScope.ready = false;
	  var url = baseUrl+'/user/login';
	  return $http
      .post(url , credentials)
      .then(function (res) {
      	    $rootScope.ready = true;
		    switch(res.status){
				case 200:
			      SessionSrv.saveUser(res.data);
			      toastr.success(Msgs.loginSuccessMsg, "Login");
			      return res;
			      break;
			   case 204:
			      return res;
			      break;
			   }
			    
		});
  };
  authService.signup = function (userForm) {
  	$rootScope.ready = false;
  	userForm.address.state="Madhya Pradesh";
  	userForm.address.city="Indore";
    return $http
      .post(baseUrl+'/user/signup', userForm)
      .then(function(res) {
      	     $rootScope.ready = true;
		     switch(res.status){
				case 200:
			      SessionSrv.saveUser(res.data);
			      toastr.success(Msgs.signupSuccessMsg, "Signup")
			      return res;
			      break;
			    case 204:
			        return res;
			        break;
			        
			        }
		     },function(){});
		
  };
  authService.logout = function () {
  	    $rootScope.ready = false;
	    return $http
	      .get(baseUrl+'/user/logout')
	      .then(function (res) {
	      	  $rootScope.ready = true;
			  switch(res.status){
				  case 200:
				      SessionSrv.clearUser();
				      toastr.success(Msgs.logoutSuccessMsg,"Logout")
				      return res;
				      break
				  case 204:
					  toastr.error(Msgs.logoutErrorMsg,"Logout")
				      return res;
				      break;
			         }
			     });
	  };
 
  authService.isAuthenticated = function () {
	  return !!SessionSrv.user;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
  	  if (!angular.isArray(authorizedRoles)) {
           authorizedRoles = [authorizedRoles];
      }
      if(exist(SessionSrv.user)){
      return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(SessionSrv.user.roles[0].roleName) !== -1);
      }else
          return false;
  };
 
  return authService;
  
}]);
