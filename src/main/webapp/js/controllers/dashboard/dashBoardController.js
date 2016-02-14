app.
controller('dashBoardCtrl',['$scope','UserSrv','URLS','$state','STATS',function($scope,UserSrv,URLS,$state,STATS){
        $state.go(STATS.dashboardUM);
 }]);