app.service('departmentSrv',['URLS','Restangular', function(URLS,Restangular) { 
	 
      this.getService = function(url){
      	//URLS.departmentUrl
      	  return Restangular.all(url);
      }

      this.getAllDepartments = function(){
      	var url = URLS.departmentSearchUrl
      	return this.getService(url);
      }
	 


  }]);
