app.service('OrderSrv',['Restangular','URLS','baseUrl','$http',
 function (Restangular,URLS,baseUrl,$http){
    
	this.getService = function(url){
		return Restangular.all(url);
	}

       this.getOrderList = function(page,limit,filter){
          var url = '';
          if(exist(filter)){
              url  = URLS.orderSearchUrl+filter+"&limit="+limit+"&page="+page;
          }else{
              url = URLS.orderSearchUrl+"?&limit="+limit+"&page="+page;
          }
         return this.getService(url).getList();
       }

       this.getFiql = function(form){
            var fiql= "";
            if(exist(form)){
                fiql+="?_s=";
              if(exist(form.status))
              {
                fiql+= "(status=="+form.status+");";
              }
              if(exist(form.byOrderId))
              {
                fiql+="(id=="+form.byOrderId+");";
              }
              if(exist(fiql))
              {
                 fiql = fiql.substring(0,fiql.length-1);
              }
            }
               
             return fiql;
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