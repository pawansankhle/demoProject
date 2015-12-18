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
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	$scope.products =  productSrv.query();
	
	$scope.pageChangeHandler = function(num) {
	      console.log('meals page changed to ' + num);
	  };
   
   
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