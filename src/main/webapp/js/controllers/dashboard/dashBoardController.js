app.
controller('dashBoardCtrl',['$scope','UserSrv','URLS','$state','STATS','$rootScope',
	function($scope,UserSrv,URLS,$state,STATS,$rootScope){
        
        
        $rootScope.$emit('reload:menu',{});
        $state.go(STATS.dashboardProducts);
 }]);