app
.controller('productCtrl', ['$scope','$stateParams','productSrv','$rootScope', function($scope, $stateParams, productSrv,$rootScope) {

	
	function getProduct() {
		productSrv.getRatedProducts().then(function(res){
			$scope.products = res;
		});
	}
	function getRatedProduct(){
	  productSrv.getTopRatedProducts().then(function(res){
	  	 $scope.topRatedProducts = res;
	  });
	  
	}
	getRatedProduct();
	getProduct();
	
    $scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.pageChangeHandler = function(num) {
		console.log('meals page changed to ' + num);
	};

	
}]);

