app.service('OrderSrv',['Restangular','URLS','baseUrl','$http',
 function (Restangular,URLS,baseUrl,$http){
    
	this.getService = function(url){
		return Restangular.all(url);
	}

	this.updateOrder = function(order){
	   if(exist(order.shippedOn)){
       	    	 order.shippedOn = (new Date(order.shippedOn)).getTime();
       	    }
       	    if(exist(order.createdOn)){
       	    	 order.createdOn = (new Date(order.createdOn)).getTime();
       	    }
       	    if(exist(order.updatedOn)){
       	    	order.updatedOn = (new Date(order.updatedOn)).getTime();
       	    }
       return $http.post(baseUrl+URLS.orderUpdateUrl,order).then(function(res){
       	   return res.data;
       },function(res){
       	  return res.data;
       });
	}

}]);