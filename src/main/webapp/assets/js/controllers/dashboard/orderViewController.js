app.controller('orderViewCtrl',['$scope','$stateParams','SearchSrv','URLS','GLOBAL_APP','$filter','OrderSrv',
  function($scope,$stateParams,SearchSrv,URLS,GLOBAL_APP,$filter,OrderSrv){
    
    $scope.order = []
    $scope.orderForm = [];
    $scope.orderDetailForm = [];
    $scope.paymentMethod = GLOBAL_APP.PAYMENT_METHOD;
    $scope.orderStatus = GLOBAL_APP.ORDER_STATUS;
    var fiql = "?_s=id=="+$stateParams.id;
  SearchSrv.fiqlSearch(URLS.orderSearchUrl,fiql).then(function(res){
    res.forEach(function(order){
      $scope.orderDetailForm.createdOn =  $filter('date')(order.createdOn);
      $scope.orderDetailForm.shippedOn =  $filter('date')(order.shippedOn || "--");
      $scope.orderDetailForm.orderStatus = order.status;
      $scope.orderDetailForm.notified = order.customerNotified;

      $scope.orderForm.shippingCharge = order.shippingCharge;
      $scope.orderForm.paymentMethod = order.paymentMethod;
      $scope.orderForm.deliveryAddress =  order.customer.deliveryAddress;
      $scope.order.push(order);
    });
  });
  
    
    $scope.updateOrder = function(){
       if(exist($scope.orderForm.paymentMethod)){
           $scope.order[0].paymentMethod = $scope.orderForm.paymentMethod;
       }
       if(exist($scope.orderForm.shippingCharge)){
            $scope.order[0].shippingCharge = parseInt($scope.orderForm.shippingCharge);
       }
       
       if(exist($scope.orderForm.deliveryAddress)){
           $scope.order[0].customer.deliveryAddress = $scope.orderForm.deliveryAddress;
        }    
       
       if(exist($scope.orderDetailForm.orderStatus))
       {    
            $scope.order[0].status = $scope.orderDetailForm.orderStatus;
            
       }
            
            $scope.order[0].customerNotified = $scope.orderDetailForm.notified;
            OrderSrv.updateOrder($scope.order[0]);
            
            
       
    }

}]);