app.factory('SearchSrv',['baseUrl','$http', function (baseUrl, $http){
	  var searchSrv = {};  
	   
	  searchSrv.fiqlSearch = function(baseuUrl1,fiql){
	  	  var url = '';
	  	  if(exist(fiql)){
              url = baseUrl+baseuUrl1+fiql;
	  	  }else{
	  	      url = baseUrl+baseuUrl1;
	  	  }
		  return $http.get(url).then(
		  	function(res){
		  		return res.data;
		  	},function(res){
		  		console.log(res);
		  	}

		  	);
	  }
	 return searchSrv; 
	}]);
	
