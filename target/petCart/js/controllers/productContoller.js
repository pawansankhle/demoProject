app.controller('productCtrl', ['$scope','productSrv', function($scope, productSrv) {
  $scope.products =  productSrv.query(); 
}]).controller('homeProductCtrl', ['$scope','$stateParams','productSrv',function($scope, $stateParams, productSrv) {
  $scope.products =  productSrv.get({ pstate: 'home' });
}]).controller('homeCategoryCtrl', function($scope, $stateParams, productSrv){
	$scope.products = productSrv.get({ pstate: 'category' ,id: $stateParams.cid });
});
