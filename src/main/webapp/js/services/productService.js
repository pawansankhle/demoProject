app.service('productSrv',['URLS', 'Restangular', 'baseUrl','$resource',
 function(URLS, Restangular, baseUrl,$resource) { 
        
        this.setCurrentProduct = function(product){
            this.currentProduct = product;
        }
        this.getCurrentProduct = function(){
            return this.currentProduct;
        }
        this.getAllProducts = function(){
            return this.getService(URLS.productUrl);
        }
        this.getService = function(url)
        {
			return Restangular.all(url);
	    }

        this.getProductbyId = function(id){
            var url = URLS.productSearchUrl+"?_s=id=="+id;
            return $resource(baseUrl+url).query().$promise;
        }

        this.getRatingbyId = function(id){
            var reviwFiql = "?_s=product.id=="+id;
            return $resource(baseUrl+URLS.reviewSearchUrl+reviwFiql).query().$promise;
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
		    if(exist(form.status))
            {
		        fiql+= "(status=="+form.status+");";
		    }
		    if(exist(form.byProductId))
            {
		        fiql+="(id=="+form.byProductId+");";
		    }
		    if(exist(form.department))
            {
                    fiql+="(department.id=="+form.department+");";
            } 
            if(exist(form.category))
            { 
                    fiql+="(category.id=="+form.category+");";
            }
            if(exist(form.byCreatedTime))
            { 
                    var date = (new Date(form.byCreatedTime)).getTime();
                    fiql+="(createdtime=="+date+");";
            } 
            if(exist(fiql)){
		       	   fiql = fiql.substring(0,fiql.length-1);
		    }else{
		       	 fiql = "";
		    }
		      return  fiql;

        }


        this.updateProduct = function(){
            var product = this.getCurrentProduct();
            product.discount = parseInt(product.discount);
            this.getService(URLS.productUpdateUrl).post(product).then(function(res){
                console.log(res);
            });
            
        }

        this.getProductByCategoryId = function(id,lower,upper){
          var fiql="?_s=category.id=="+id+"&lowerLimit="+lower+"&upperLimit="+upper
            return this.getService(URLS.productSearchUrl+fiql).getList();
            
        }
        
    
}]);
