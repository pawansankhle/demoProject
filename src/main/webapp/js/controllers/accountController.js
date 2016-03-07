app
.controller('accountCtrl',['$scope',function($scope){

}])
.controller('accountOrdersCtrl',['$scope','URLS','$rootScope','OrderSrv',
    '$stateParams','SearchSrv','$state','STATS','UserSrv',
    function($scope,URLS,$rootScope,OrderSrv,$stateParams,SearchSrv,$state,STATS,UserSrv){
    var userid='';
    console.log(exist($rootScope.currentUser));
    if(exist($rootScope.currentUser)){
    	userid = $rootScope.currentUser.id;
    }else{
        $state.go(STATS.home);
    }
    
    $scope.getOrderById = function(){
        var orderId = $stateParams.id;
        var fiql = "?_s=id=="+orderId
        $scope.order = SearchSrv.fiqlSearch(URLS.orderSearchUrl,fiql).getList().$object;
    }
    //var fiql = "?_s=customer.id=="+userid+"&lowerLimit=0&upperLimit=12";
    $scope.getAllorders = function(){
      if(!exist($scope.orders)){
            $scope.orders = OrderSrv.getService(URLS.orderByUserIdUrl+userid).getList().$object;
         }
        }
  
    
    if($state.current.name == STATS.accountOrders){
       $scope.getAllorders();
    }else if($state.current.name == STATS.accountOrderDetail){
        $scope.getOrderById();
    }
     
   

}])


.controller('accountSettingCtrl',['$scope','UserSrv','URLS','$rootScope','$state','STATS',
    function($scope,UserSrv,URLS,$rootScope,$state,STATS){
      
      $scope.submitUserForm = function(user){
        UserSrv.getService(URLS.userUpdateUrl).post(user);
      }
     $scope.submitUserPasswordForm = function(userform){
       UserSrv.getService(URLS.userChangePasswordUrl+userform.oldPassword+"/"+userform.newPassword).post();
     }
      
}]);