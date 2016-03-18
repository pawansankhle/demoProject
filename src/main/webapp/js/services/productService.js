app.service('productSrv',['URLS', 'Restangular', 'baseUrl', function(URLS, Restangular, baseUrl) { 
        

        this.getAllProducts = function(){
            return Restangular.all(URLS.productUrl);
        }
        this.getService = function(url)
        {
			return Restangular.all(url);
	    }

	    this.getProductList = function(filter,lower,upper){
        var url = '';
        var products = [];
    	if(exist(filter)){
        	url  = URLS.productSearchUrl+filter+"&lowerLimit="+lower+"&upperLimit="+upper;
    	}else{
        	url = URLS.productSearchUrl+"?lowerLimit="+lower+"&upperLimit="+upper;
        }
         return this.getService(url).getList();
          }
        
        this.getFIQL = function(form){
             var fiql= "?_s=";
		     if(exist(form.status)){
		            fiql+= "(status=="+form.status+");";
		         }
		        if(exist(form.byProductId)){
		            fiql+="(id=="+form.byProductId+");";
		          }
		       if(exist(fiql)){
		       	   fiql = fiql.substring(0,fiql.length-1);
		       }else{
		       	 fiql = "";
		       }
		      return  fiql;

        }
        
    
}]);
