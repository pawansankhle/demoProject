app.factory('CartSrv',['$http', 'URLS', 'SessionSrv', 'baseUrl','$rootScope', function ($http, URLS, SessionSrv,baseUrl,$rootScope) {
		var cartService = {};
		
		cartService.addToCart = function(product){
			var url = baseUrl+URLS.addToCartUrl+product.id;
			return $http
		      .post(url)
		      .then(function (res) {
				      var exist = false;
				      toastr.success(product.name+' added to your cart', 'Product');
				      if(SessionSrv.cart.items != null)
				      {
						  var len = SessionSrv.cart.items.length;
					  }
					  else
					  {
					      var len = 0;
					      
					   }
					   for(var i=0; i< len; i++ ){
							   if(SessionSrv.cart.items[i].id == res.data.items[0].id)
							   {
								   SessionSrv.cart.items[i].quantity+=1;
								   exist=true;
							   }
							 }
					 if(!exist)
					 {
						SessionSrv.updateCart(res.data.items[0].id,res.data.items[0].itemId,res.data.items[0].quantity,res.data.total);
						cartService.getCart();
						
					 }
				    
		        return SessionSrv.cart;
		      },function(res){
		    	  toastr.error('Error','Something Wrong...try Again')
				  });
	       };
	       cartService.updateCart = function(cart){
			     return $http
				  .post(baseUrl+URLS.updateCartUrl,cart)
				  .then(function(res) {
					  toast.success('cart Updated Successfully','Info');
				  })
				      
			   };
		   cartService.deleteItem = function(id){
				     return $http
					  .put(baseUrl+URLS.cartItemDelete+id)
					  .then(function(res) {
						  toastr.info('one Product Remove From Cart','Info');
					  })
			   };
	       cartService.getCart = function(){
			   var url = baseUrl+URLS.cartUrl;
			   return $http.get(url)
		      .then(function (res) {
				 SessionSrv.saveCart(res.data);
				  return res.data;
				},function(){
					});
		      };
		   
		return	cartService;
	}]);
	
