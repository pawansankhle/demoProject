app
.controller('categoryCtrl',['$scope','categorySrv', function($scope,categorySrv) {
  $scope.categories =  categorySrv.query();
   
}])
