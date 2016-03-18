app.
controller('dashBoardProductCtrl',['$scope','pageLowerLimit','pageUpperLimit','productSrv','URLS'
	,function($scope,pageLowerLimit,pageUpperLimit,productSrv,URLS){
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
         $scope.getProducts(pageLowerLimit,pageUpperLimit);

     };
	 
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