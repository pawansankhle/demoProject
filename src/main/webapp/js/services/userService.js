app.factory('UserSrv',['Restangular','baseUrl','URLS','SessionSrv','$resource', function (Restangular,baseUrl,URLS,SessionSrv,$resource){
	  var userSrv = {}; 
	  
	   
	  userSrv.getService = function(url){
			  return Restangular.all(url);
		};
	  userSrv.getProfile = function(){
		  return $resource(baseUrl+URLS.userProfileUrl);
	  };
	 return userSrv;
	}]);



