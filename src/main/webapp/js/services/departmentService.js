app.factory('departmentSrv',['URLS','Restangular', function(URLS,Restangular) { 
	 return Restangular.all(URLS.departmentUrl);
  }]);
