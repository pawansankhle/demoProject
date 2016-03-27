app
.controller('productCtrl', ['$scope','$stateParams','productSrv', function($scope, $stateParams, productSrv) {

	$scope.products =  productSrv.getRatedProducts().getList().$object;
    $scope.currentPage = 1;
	$scope.pageSize = 12;
    $scope.pageChangeHandler = function(num) {
		console.log('meals page changed to ' + num);
	};

	
}]);

