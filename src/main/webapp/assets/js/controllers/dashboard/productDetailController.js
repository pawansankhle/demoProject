app.controller('dashBoardProductViewCtrl',['$scope','productSrv',function($scope,productSrv){
     
      $scope.currentProduct = productSrv.getCurrentProduct();
      $scope.updateProduct = function(){
        productSrv.updateProduct();
      }

      $scope.uploadImage = function(){
      	 if(exist($scope.fileName) && exist($scope.files)){
      	 	 productSrv.uploadProductImage($scope.fileName,$scope.files);
      	   }
        
       }
	 

}]);