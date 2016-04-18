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
		$scope.paymentok = false;
		$rootScope.$on('checkout:paymentOk',function(res){
			$scope.paymentok = res;
		});
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

		$scope.editAddress = function(){
			$scope.editShippingAddress();
			$state.go('checkout.address');
		}
		      
		$scope.placeOrder = function(orderDetails){
			OrderSrv.getService(URLS.placeOrderUrl+SessionSrv.cart.id).post($scope.orderDetails).
			then(
				function(res){
				   $scope.shippingCharge = 0;
				   $scope.order = res;
				   $scope.paymentok = !$scope.paymentok;
				   $rootScope.$emit('checkout:paymentOk',true);

				   CartSrv.getCart().then(function(cart){
				   	  SessionSrv.saveCart(cart);
				   	  $rootScope.$emit('setShoppingCart',{cart});
				   });
                   toastr.success(Msgs.PlaceOrderSuccessMsg,"Checkout")  
				},
				function(res){
				  toastr.error(Msgs.errorMsg,"checkout")

			});
		}
}]);
