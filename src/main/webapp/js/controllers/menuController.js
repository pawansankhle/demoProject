app.
controller('menuCtrl', ['$scope','departmentSrv', function($scope, departmentSrv) {
   $scope.departments =  departmentSrv.getList().$object;
   
   $scope.openSubCategory = function(indexId){
	   $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   }
}]);


