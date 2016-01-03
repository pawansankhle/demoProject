app.factory('AuthService',['$http','baseUrl', 'SessionSrv', function ($http, baseUrl, SessionSrv) {
  var authService = {};
 
  authService.login = function (credentials) {
	  var url = baseUrl+'/user/login';
	  return $http
      .post(url , credentials)
      .then(function (res) {
		   switch(res.data.status){
			  case 'success':
			      SessionSrv.create(res.data.id,res.data.username, res.data.roles);
			      Notifier.success("Login","you have been logged in");
			      return res.data;
			      break
			 case 'error':
			      Notifier.error("Login",res.data.message)
			      break;
		         }
		    });
  };
  authService.signup = function (credentials) {
    return $http
      .post(baseUrl+'/user/signup', credentials)
      .then(function (res) {
		  switch(res.data.status){
			  case 'success':
			      SessionSrv.create(res.data.id,res.data.username, res.data.roles);
			      Notifier.success("Signup","Sign up Successfully");
			      return res.data;
			      break
			 case 'error':
			      Notifier.error("Signup",res.data.message)
			      break;
		         }
		     });
  };
 
  authService.isAuthenticated = function () {
    return !!SessionSrv.username;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
	 if (!angular.isArray(authorizedRoles)) {
           authorizedRoles = [authorizedRoles];
      }
     
     return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(SessionSrv.userrole) !== -1);
  };
 
  return authService;
  
}]);
