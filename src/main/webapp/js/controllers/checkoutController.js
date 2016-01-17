app.controller('checkoutCtrl',['UserSrv','$scope','$state','AuthService','STATS','AUTH_EVENTS','URLS','Restangular','baseUrl',
	function(UserSrv,$scope,$state,AuthService,STATS,AUTH_EVENTS,URLS,Restangular,baseUrl){
		$scope.$on(AUTH_EVENTS.loginSuccess,function(){
			  $state.go(STATS.checkoutAddress);
			});
		if(AuthService.isAuthenticated())
		{
		  $state.go(STATS.checkoutAddress);
		  
		}
}]);
