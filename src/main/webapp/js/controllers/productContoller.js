app
.controller('productCtrl', ['$scope','productSrv', function($scope, productSrv) {
  $scope.products =  productSrv.query(); 
}])

.controller('productViewCtrl',['$scope', '$stateParams', 'productSrv', function($scope, $stateParams, productSrv){
	$scope.products =  productSrv.get({ pstate: 'view', id: $stateParams.id  });
	
}])
.controller('homeProductCtrl', ['$scope','$stateParams','productSrv',function($scope, $stateParams, productSrv) {
  //$scope.products =  productSrv.get({ pstate: 'home' });
   $scope.products =  productSrv.query(); 
   
}])
.controller('homeCategoryCtrl',['$scope', '$stateParams', 'productSrv', function($scope, $stateParams, productSrv){
	$scope.products = productSrv.get({ pstate: 'category' ,id: $stateParams.cid });
}])
.controller('productAddToCartCtrl', ['$scope', function($scope){
	$scope.addToCart = function(pid) { 
  	    Notifier.success('product','1 product added count is: ');
  	    };
	
	}
]);
	

