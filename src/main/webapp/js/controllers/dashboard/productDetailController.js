app.controller('dashBoardProductViewCtrl',['$scope','productSrv',function($scope,productSrv){
     
      $scope.currentProduct = productSrv.getCurrentProduct();
      $scope.updateProduct = function(){
        productSrv.updateProduct();
      }

}]);