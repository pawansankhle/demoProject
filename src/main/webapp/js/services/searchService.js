app.factory('SearchSrv',['baseUrl','Restangular', function (baseUrl, Restangular){
	  var searchSrv = {};  
	   
	  searchSrv.fiqlSearch = function(baseuUrl,fiql){
	  	  var url = '';
	  	  if(exist(fiql)){
              url = baseuUrl+fiql;
	  	  }else{
	  	      url = baseuUrl;
	  	  }
		  return Restangular.all(url);
	  }
	 return searchSrv; 
	}]);
	
