app.controller('productViewCtrl',['$scope', '$stateParams', 'productSrv',
	function($scope, $stateParams, productSrv){

	 $scope.getProductById = function(id){
	 	 productSrv.getProductbyId($stateParams.id | id).then(function(res){
	 	$scope.product = res[0];
	 });
	 }
	 

	 $scope.getRatingById = function(id){
         productSrv.getRatingbyId($stateParams.id | id).then(function(res){
	 	 $scope.reivews = res;
	 });
	 }

	 $scope.findProductRecomm = function()
	 {
	 	productSrv.findProductRecomm($stateParams.id | id).then(function(res){
	 		console.log(res);
	 		$scope.recommendationProduct = res;

	 	})
	 }
	 
	 $scope.getProductById(); 
	 $scope.getRatingById();
	  $scope.findProductRecomm();
	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};

}])
