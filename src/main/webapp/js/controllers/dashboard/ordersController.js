app.controller('ordersCtrl',['$scope','GLOBAL_APP','URLS','STATS','$state','pageLowerLimit','pageUpperLimit','Msgs','OrderSrv',
	function($scope,GLOBAL_APP,URLS,STATS,$state,pageLowerLimit,pageUpperLimit,Msgs,OrderSrv){
	   $scope.orderStatus = GLOBAL_APP.ORDER_STATUS;
	   
	   $scope.orders = [];
       $scope.filter = "";
	   $scope.getOrdersList = function(lower,upper){
    	var url = '';
    	if(exist($scope.filter)){
        	url  = URLS.orderSearchUrl+$scope.filter+"&lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit;
    	}else{
        	url = URLS.orderSearchUrl+"?lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit;
        }
    
        OrderSrv.getService(url).getList().then(function(res){
        res.forEach(function(o){
            $scope.orders.push(o);
        });

    });
    }
     $scope.getOrdersList(pageLowerLimit,pageUpperLimit);
     
     $scope.search = function(){
     var fiql= "?_s=";
     $scope.orders = [];
       if(exist($scope.orderForm.status)){
            fiql+= "(status=="+$scope.orderForm.status+");";
         }
        if(exist($scope.orderForm.byOrderId)){
            fiql+="(id=="+$scope.orderForm.byOrderId+");";
            
        }
       if(exist(fiql)){
       	   fiql = fiql.substring(0,fiql.length-1);
       }else{
       	 fiql = "";
       }
   $scope.filter = fiql;
   $scope.getOrdersList(pageLowerLimit,pageUpperLimit)

}

     $(document).ready(function(){
                $(window).scroll(function () {
                    if($(window).scrollTop() + $(window).height() == $(document).height()) {
                        pageLowerLimit= pageUpperLimit+1;
                        pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
                        $scope.getOrdersList(pageLowerLimit,pageUpperLimit);
                    }
                });
            });
 }

])
.controller('orderViewCtrl',['$scope','$stateParams','SearchSrv','URLS','GLOBAL_APP','$filter','OrderSrv',
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