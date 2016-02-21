app
.controller('accountCtrl',['$scope',function($scope){

}])
.controller('accountOrdersCtrl',['$scope','URLS','$rootScope','OrderSrv','$stateParams',function($scope,URLS,$rootScope,OrderSrv,$stateParams){
    var userid='';
    if(exist($rootScope.currentUser)){
    	userid = $rootScope.currentUser.id;
    }
    
    //var fiql = "?_s=customer.id=="+userid+"&lowerLimit=0&upperLimit=12";
    getAllorders = function(){
	  //$scope.orders =  SearchSrv.fiqlSearch(URLS.orderSearchUrl,fiql).getList().$object;
	   if(!exist($scope.orders)){
            $scope.orders = OrderSrv.getService(URLS.orderByUserIdUrl+userid).getList().$object;
         }
        var orderId = $stateParams.id;
        $scope.order = _.find($scope.orders,function(or){return or.id == orderId});
       }
   getAllorders(); 
     
   

}])
.controller('accountSettingCtrl',['$scope',function($scope){
	
}]);