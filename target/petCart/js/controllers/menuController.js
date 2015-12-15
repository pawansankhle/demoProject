app.
controller('menuCtrl', ['$scope','menuSrv', function($scope, menuSrv) {
   $scope.categories =  menuSrv.query(); 
}]);


