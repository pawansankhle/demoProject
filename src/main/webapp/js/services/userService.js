app.factory('UserSrv',['Restangular','baseUrl','URLS','SessionSrv', function (Restangular,baseUrl,URLS,SessionSrv){
	  var userSrv = {};  
	   
	  userSrv.getService = function(url){
			  return Restangular.all(url);
		};
	  userSrv.getProfile = function(){
		  return Restangular.oneUrl("userProfile",baseUrl+URLS.userProfileUrl).get().then(function(user){
			         SessionSrv.saveUser(user)
			         return user;
			  });
		};
	return userSrv;
	}]);



