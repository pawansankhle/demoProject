app.controller('ordersCtrl',['$scope','GLOBAL_APP','URLS','STATS','$state','$window','$document','Msgs','OrderSrv',
	function($scope,GLOBAL_APP,URLS,STATS,$state,$window,$document,Msgs,OrderSrv){
	   $scope.orderStatus = GLOBAL_APP.ORDER_STATUS;
	   
	   $scope.orders = [];
     $scope.filter = "";
     $scope.page = page;
     $scope.limit = maxlimitofpagination;
    
     $scope.$watch('page',function(newVal,oldVal){
           if(newVal != oldVal){
              return newVal;
          }
     })
     $scope.getOrdersList = function(page,limit){
    	  
        OrderSrv.getOrderList(page,limit,$scope.filter).then(function(res){
        res.forEach(function(o){
            $scope.orders.push(o);
        });

    });
    }
     $scope.getOrdersList(1,12);
     
     $scope.search = function()
     {
       $scope.orders = [];
       $scope.filter = OrderSrv.getFiql($scope.orderForm);
       $scope.getOrdersList(1,12)
    }
     $scope.reset = function()
     {
        $scope.filter = "";
        $scope.orderForm = '';
        $scope.search();
     }

    /* $window.scroll(function (){
                    if($window.scrollTop() + $window.height() == $document.height()) {
                        $scope.page = $scope.page+1;
                    }
                },'');*/
  }
]);
