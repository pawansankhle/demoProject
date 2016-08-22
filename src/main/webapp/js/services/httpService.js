app.service('httpSrv',['$http','$rootScope','baseUrl','$q','$timeout',
	                    function($http,$rootScope,baseUrl,$q,$timeout){

  this.getRequest = function(url){
  	    $rootScope.ready = false;
        var defer = $q.defer();
        $http.get(baseUrl+url).then(function(res){
        	defer.resolve(res.data);
        	$rootScope.ready = true;
        },function(res){
        	defer.reject(errResponse);
        	$rootScope.ready = true;
        });
        return defer.promise;
  };

  this.postRequest = function(url,data){
  	    $rootScope.ready = false;
        var defer = $q.defer();
        $http.post(baseUrl+url,data).then(function(res){
        	defer.resolve(res.data);
        	$rootScope.ready = true;
        },function(res){
        	defer.reject(errResponse);
        	$rootScope.ready = true;
        });
        return defer.promise;
  }

}]);