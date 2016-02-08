app.factory('UserSrv',['Restangular','baseUrl','URLS','SessionSrv','$resource','$http', function (Restangular,baseUrl,URLS,SessionSrv,$resource,$http){
	  var userSrv = {}; 
	  
	   
	  userSrv.getService = function(url){
			  return Restangular.all(url);
		};
	  userSrv.getProfile = function(){
		  return $http.get(baseUrl+URLS.userProfileUrl)
		  .then(function(res){
		     if(res.status == 204){
		           return null;
		     }
		     return res.data;
          },function(res){});
	  };
	    return userSrv;
	}]);



