<<<<<<< HEAD
app.service('productSrv',['URLS', 'Restangular','$resource','fileUploadSrv','httpSrv',function(URLS, Restangular,$resource,fileUploadSrv,httpSrv) { 
=======
app.service('productSrv',['URLS', 'Restangular', 'baseUrl','$resource','fileUploadSrv','$rootScope',
 function(URLS, Restangular, baseUrl,$resource,fileUploadSrv,$rootScope) { 
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
        
        this.setCurrentProduct = function(product){
            this.currentProduct = product;
        };

        this.getCurrentProduct = function(){
            return this.currentProduct;
        };

        this.getAllProducts = function(){
            return this.getService(URLS.productUrl);
        };

        this.postService = function(url,data) {
            return httpSrv.postRequest(url,data);
        };

        this.getService = function(url) {

            return httpSrv.getRequest(url);
	    };

        this.getProductbyId = function(id) {
            var url = URLS.productSearchUrl+"?_s=id=="+id;
            return httpSrv.getRequest(url);
        };

        this.getRatingbyId = function(id) {
            var reviwFiql = "?_s=(product.id=="+id+")";
            return httpSrv.getRequest(URLS.reviewSearchUrl+reviwFiql);
        }

	    this.getProductList = function(filter,lower,upper){
        var url = '';
        var products = [];
    	if(exist(filter)){
        	url  = URLS.productSearchUrl+filter+"&lowerLimit="+lower+"&upperLimit="+upper;
    	}else{
        	url = URLS.productSearchUrl+"?lowerLimit="+lower+"&upperLimit="+upper;
        }
         return this.getService(url);
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
            this.postService(URLS.productUpdateUrl,product);
        };

<<<<<<< HEAD
        this.getProductByCategoryId = function(id,lower,upper){
          var fiql="?_s=category.id=="+id+"&lowerLimit="+lower+"&upperLimit="+upper
            return this.getService(URLS.productSearchUrl+fiql);
=======
        this.getProductByCategoryId = function(id,lower,upper,orderBy,type){
           $rootScope.$emit('toggleLoading');
           var fiql = "?_s=category.id=="+id;
           
            fiql+= "&lowerLimit="+lower+"&upperLimit="+upper+"&orderType="+type+"&orderBy="+orderBy;
            return this.getService(URLS.productSearchUrl+fiql).getList();
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
            
        };

       this.addProduct = function(form){
         return this.postService(URLS.productCreateUrl,form);
       };

       this.enableDisableProduct = function(product,action){
           var url = URLS.productUrl+"/"+product.id;
           if(action == 'enable'){
                url+="/enable"
           }else{
               url+="/disable"
           }
          return this.postService(url);
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
        var url = URLS.productSearchUrl+'?_s=(reviews.rating=ge=4)';
        return this.getService(url);
     }

     this.findProductRecomm = function(id)
     {
        console.log(URLS.productRecommUrl+id);
        return this.getService(URLS.productRecommUrl+id);
     }

     this.addProductReview = function(review)
     {   
        
        var product = this.getCurrentProduct();
        return this.postService(URLS.reviewAddUrl+"/"+product.id,review);
     }

}]);
