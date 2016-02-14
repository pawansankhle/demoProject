app.controller('checkoutCtrl',['$rootScope','UserSrv','SessionSrv','$scope','$state','AuthService','STATS','AUTH_EVENTS','URLS','Restangular','baseUrl','Msgs','OrderSrv','CartSrv',
	function($rootScope,UserSrv,SessionSrv,$scope,$state,AuthService,STATS,AUTH_EVENTS,URLS,Restangular,baseUrl,Msgs,OrderSrv,CartSrv){
		$scope.orderDetails= {};
		$scope.orderDetails.paymentMethod = "COD";
		if(exist(SessionSrv.user) && exist(SessionSrv.cart)){
			$scope.shippingCharge = 100;
			$scope.orderDetails.mobile = SessionSrv.user.mobile;
			$scope.orderDetails.totalAmount = $scope.shippingCharge + SessionSrv.cart.total;
			
		}
		$scope.isShippingAddressEdit = false;
		$scope.showShippingAddress = true;
		$scope.btnStatePayment = true;
		$scope.$on(AUTH_EVENTS.loginSuccess,function(){
			  $state.go(STATS.checkoutAddress);
			});
			
			
	    if($state.current.name == STATS.checkoutAddress){
	    	if(!AuthService.isAuthenticated()){
				 $state.go(STATS.checkoutLogin);
				 }
		       } 
	    if($state.current.name == STATS.checkoutLogin){
	    	if(AuthService.isAuthenticated()){
				 $state.go(STATS.checkoutAddress);
			 }
		}
		if($state.current.name == STATS.checkoutPayment){
			 if(!AuthService.isAuthenticated()){
				 $state.go(STATS.checkoutLogin);
			 }
		}
		$scope.editShippingAddress = function(){
			$scope.isShippingAddressEdit = !$scope.isShippingAddressEdit;
			$scope.showShippingAddress = !$scope.showShippingAddress;
			$scope.btnStatePayment = !$scope.btnStatePayment;
			
		}
		$scope.shippingDetails = function(user){
		    UserSrv.getService(URLS.updateShippingDetailUrl).post(user).then(function(res){
				if(res.status=='success'){
				     $state.go("checkout.payment");
				 }else{
				    toastr.info(Msgs.errorMsg,"checkout")
				   }
				});
		      }
		$scope.placeOrder = function(orderDetails){
			var btn  = angular.element('#place_order_id');
			btn.button('loading');
			OrderSrv.getService(URLS.placeOrderUrl+SessionSrv.cart.id).post($scope.orderDetails).
			then(
				function(res){
				   btn.button('reset');
				   $scope.shippingCharge = 0;
				   CartSrv.getCart().then(function(cart){
				   	  SessionSrv.saveCart(cart);
				   	  $rootScope.$emit('setShoppingCart',{cart});
				   });
                   toastr.info(Msgs.PlaceOrderSuccessMsg,"Checkout")  
				},
				function(res){
				  btn.button('reset');
				  toastr.error(Msgs.errorMsg,"checkout")

			});
		}
}]);
