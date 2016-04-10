app.service('productSrv',['URLS', 'Restangular', 'baseUrl','$resource','fileUploadSrv',
 function(URLS, Restangular, baseUrl,$resource,fileUploadSrv) { 
        
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
            var reviwFiql = "?_s=(product.id=="+id+")";
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

        };


        this.updateProduct = function(){
            var product = this.getCurrentProduct();
            product.discount = parseInt(product.discount);
            this.getService(URLS.productUpdateUrl).post(product).then(function(res){
                console.log(res);
            });
            
        };

        this.getProductByCategoryId = function(id,lower,upper){
          var fiql="?_s=category.id=="+id+"&lowerLimit="+lower+"&upperLimit="+upper
            return this.getService(URLS.productSearchUrl+fiql).getList();
            
        };

       this.addProduct = function(form){
         return this.getService(URLS.productCreateUrl).post(form);
       };

       this.enableDisableProduct = function(product,action){
           var url = URLS.productUrl+"/"+product.id;
           if(action == 'enable'){
                url+="/enable"
           }else{
               url+="/disable"
           }
          return this.getService(url).post();
       };
        
     this.uploadProductImage = function(filename,files)
     {   
         var product = this.getCurrentProduct();
         var url = baseUrl+URLS.productUploadImage+"/"+product.id+"/"+filename
         fileUploadSrv.uploadFile(files,url);
     }

     this.getRatedProducts = function(){
        var url =URLS.productSearchUrl+'?_s=reviews.rating=gt=2';
        return this.getService(url);
     }

     this.getTopRatedProducts = function(){
        var url =URLS.productSearchUrl+'?_s=(reviews.rating=ge=4)';
        return this.getService(url);
     }

     this.findProductRecomm = function(id)
     {
        return $resource(baseUrl+"/"+URLS.productRecommUrl+"/"+id).query().$promise;
     }

     this.addProductReview = function(review)
     {   
        
        var product = this.getCurrentProduct();
        return $resource(baseUrl+URLS.reviewAddUrl+"/"+product.id).save(review).$promise;
     }

}]);
