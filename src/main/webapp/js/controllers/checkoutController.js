app.controller('checkoutCtrl',['UserSrv','$scope','$state','AuthService','STATS','AUTH_EVENTS','URLS','Restangular','baseUrl','Msgs',
	function(UserSrv,$scope,$state,AuthService,STATS,AUTH_EVENTS,URLS,Restangular,baseUrl,Msgs){
		$scope.orderDetails= {};
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
				 console.log(res);
				 if(res.status=='success'){
				     $state.go("checkout.payment");
				 }else{
				    toastr.info(Msgs.errorMsg,"checkout")
				   }
				});
		      }
		$scope.placeOrder = function(orderDetails){
			console.log(orderDetails)
		}
}]);
