app.
controller('dashBoardProductCtrl',['$scope','pageLowerLimit','pageUpperLimit','productSrv','URLS','STATS','$state'
	,function($scope,pageLowerLimit,pageUpperLimit,productSrv,URLS,STATS,$state){
	$scope.products = [];
	$scope.productDepartments = [];

	$scope.getProducts = function(lower,upper){
		productSrv.getProductList($scope.filter,lower,upper).then(function(res){
			res.forEach(function(p){
				$scope.products.push(p);
			});
		});
	}
	
    $scope.getProducts(pageLowerLimit,pageUpperLimit);
     $scope.search = function(){
     	 $scope.products = [];
         $scope.filter = productSrv.getFIQL($scope.productForm);
         $scope.getProducts(0,12);

     };

     $scope.viewProduct = function(product){
          productSrv.setCurrentProduct(product);
          $state.go(STATS.dashboardProductView);
     }
	 
	 $(document).ready(function(){
                $(window).scroll(function () {
                    if($(window).scrollTop() + $(window).height() == $(document).height()) {
                        pageLowerLimit= pageUpperLimit+1;
                        pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
                        $scope.getProducts(pageLowerLimit,pageUpperLimit);
                    }
                });
            });
}]);
