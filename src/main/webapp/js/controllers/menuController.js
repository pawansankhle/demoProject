app.
controller('menuCtrl', ['$scope','departmentSrv','$rootScope', function($scope, departmentSrv,$rootScope) {
   
   $scope.departments =  departmentSrv.getList().$object;
   
   $scope.openSubCategory = function(indexId){
	   $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   }
}]);


