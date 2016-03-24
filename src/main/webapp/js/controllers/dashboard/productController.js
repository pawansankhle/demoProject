app.
controller('dashBoardProductCtrl',['$scope','pageLowerLimit','pageUpperLimit','productSrv','URLS','STATS','$state','departmentSrv',
	function($scope,pageLowerLimit,pageUpperLimit,productSrv,URLS,STATS,$state,departmentSrv){
	$scope.products = [];
	$scope.productDepartments = [];
    $scope.productcategories = [];

	$scope.getProducts = function(lower,upper){
       productSrv.getProductList($scope.filter,lower,upper).then(function(res){
			res.forEach(function(p){
                $scope.products.push(p);
			});
		});
	}
	
    departmentSrv.getAllDepartments().getList().then(function(res){
             res.forEach(function(d){
                 $scope.productDepartments.push(d);
             })
    });

    $scope.getCategories = function(department){
       var dept = _.find($scope.productDepartments, function(dept){ return dept.id == department; });
        $scope.productcategories  = dept.categories;
    }
    $scope.getProducts(0,12);
     
    $scope.search = function(){
     	 $scope.products = [];
         if(exist($scope.productForm)){
            $scope.filter = productSrv.getFIQL($scope.productForm);
         }
         $scope.getProducts(0,12);
    };

     $scope.reset = function(){
        $scope.productForm = '';
        $scope.filter = '';
        $scope.search();
    }

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
