app.service('departmentSrv',['URLS','Restangular','baseUrl','$resource'
  ,function(URLS,Restangular,baseUrl,$resource) { 
	 
      this.getService = function(url){
      	//URLS.departmentUrl
      	  return Restangular.all(url);
      }

      this.getAllDepartments = function(){
      	var url = URLS.departmentSearchUrl
        return $resource(baseUrl+url).query().$promise;
      }
	 


  }]);
