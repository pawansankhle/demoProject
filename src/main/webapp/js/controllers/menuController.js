app.
controller('menuCtrl', ['$scope','menuSrv', function($scope, menuSrv) {
   $scope.departments =  menuSrv.query(); 
   
   $scope.openSubCategory = function(indexId){
	   $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   }
}]);


