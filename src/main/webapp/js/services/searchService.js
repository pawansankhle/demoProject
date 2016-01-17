app.factory('SearchSrv',['baseUrl','Restangular', function (baseUrl, Restangular){
	  var searchSrv = {};  
	   
	  searchSrv.fiqlSearch = function(url,fiql){
		  return Restangular.all(url+fiql);
	  }
	 return searchSrv; 
	}]);
	
