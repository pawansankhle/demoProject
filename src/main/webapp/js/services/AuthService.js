app.factory('AuthService',['$http','baseUrl', 'SessionSrv','Msgs', function ($http, baseUrl, SessionSrv,Msgs) {
  var authService = {};
 
  authService.login = function (credentials) {
	  var url = baseUrl+'/user/login';
	  return $http
      .post(url , credentials)
      .then(function (res) {
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
  authService.signup = function (credentials) {
    return $http
      .post(baseUrl+'/user/signup', credentials)
      .then(function(res) {
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
	    return $http
	      .get(baseUrl+'/user/logout')
	      .then(function (res) {
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
