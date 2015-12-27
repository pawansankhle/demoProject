
app.controller('cartCtrl',['$rootScope', '$scope', 'SessionSrv','CartSrv',function($rootScope, $scope, SessionSrv,CartSrv) {
	
	
	$scope.cart = SessionSrv.cart;
	$scope.upQwt = function(item){
		SessionSrv.cart.items[item].quantity+=1;
	 }
	 $scope.downQwt = function(item){
		 if(SessionSrv.cart.items[item].quantity>0)
		      SessionSrv.cart.items[item].quantity-=1;
	 }
	$scope.updateCart = function(cart) {
		 CartSrv.updateCart(cart).then(function(res){},function(){})
	}
	$scope.cartItemRemove = function(item){
		SessionSrv.cart.items.pop(item)
	}
	
}])
 .controller('AddToCartCtrl', ['$rootScope', '$scope','$stateParams','CartSrv','SessionSrv' ,function($rootScope,$scope, $stateParams, CartSrv, SessionSrv) {
	
	$scope.addToCart = function(product){
		CartSrv.addToCart(product).then(function(cart){
			$rootScope.$emit('setShoppingCart',{cart})
			$rootScope.count+=1;
		  },function(){}
		);} 
}]);
