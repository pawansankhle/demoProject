
app.controller('cartCtrl',['$rootScope', '$scope', 'SessionSrv','CartSrv',function($rootScope, $scope, SessionSrv,CartSrv) {
	
	
	$scope.cart = SessionSrv.cart;
	$rootScope.$on('setShoppingCart',
		function(evnt,res){
		$scope.cart = res.cart
		if(exist(res.cart.items)){
					$rootScope.count = res.cart.items.length;
				}
	});
		   
	$scope.upQwt = function(index,cart){
		SessionSrv.cart.items[index].quantity+=1;
		CartSrv.updateCartTotal(cart);
	 }
	 $scope.downQwt = function(index,cart){
	 	var item = SessionSrv.cart.items[index];
	 	if(item.quantity>=1){
	 		 SessionSrv.cart.items[index].quantity-=1;
	 		 CartSrv.updateCartTotal(cart);
		}

		 	  
	 }
	$scope.updateCart = function(crt) {
		 CartSrv.updateCart(crt).then(function(data){
		 	if(data.msg != 'ok'){
		 		$scope.errMsg = data.msg;
		 	}else{
		 		$scope.errMsg = '';
		 	}
		 	CartSrv.getCart().then(function(cart){
                   $rootScope.$emit('setShoppingCart',{cart});
		 	    });
		 	},function(){})
	}
	$scope.cartItemRemove = function(index,id){
		SessionSrv.cart.items.pop(index);
		var cart = SessionSrv.cart;
		CartSrv.deleteItem(id).then(function(res){
            $rootScope.$emit('setShoppingCart',{cart});
		});
		CartSrv.updateCartTotal(SessionSrv.cart);
	}

	
}])
 .controller('AddToCartCtrl', ['$rootScope', '$scope','$stateParams','CartSrv','STATS','$state',function($rootScope,$scope, $stateParams, CartSrv, STATS,$state) {
	
	$scope.addToCart = function(product){
		CartSrv.addToCart(product).then(function(cart){
			$rootScope.$emit('setShoppingCart',{cart});
			},function(){}
		);} 

	$scope.buyNow = function(product){
		$scope.addToCart(product);
		$state.go(STATS.checkoutLogin);

	}
}]);
