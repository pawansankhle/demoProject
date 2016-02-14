app.service('OrderSrv',['Restangular', function (Restangular){
    
	this.getService = function(url){
		return Restangular.all(url);
	}

}]);