app
.controller('productCtrl', ['$scope','productSrv', function($scope, productSrv) {
  $scope.products =  productSrv.query(); 
}])

.controller('productViewCtrl',['$scope', '$stateParams', 'productSrv', function($scope, $stateParams, productSrv){
	$scope.product =  productSrv.get({ pstate: 'view', id: $stateParams.id  });
	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};
	
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