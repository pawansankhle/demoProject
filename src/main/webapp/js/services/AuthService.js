app.factory('AuthService',['$http', 'SessionSrv', function ($http, SessionSrv) {
  var authService = {};
 
  authService.login = function (credentials) {
    return $http
      .post('/login', credentials)
      .then(function (res) {
    	  SessionSrv.create(res.data.id, res.data.user.id, res.data.user.role);
        return res.data.user;
      });
  };
 
  authService.isAuthenticated = function () {
    return !!SessionSrv.userId;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
    if (!angular.isArray(authorizedRoles)) {
      authorizedRoles = [authorizedRoles];
    }
    return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(SessionSrv.userRole) !== -1);
  };
 
  return authService;
}])
