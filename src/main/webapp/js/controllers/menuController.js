app.
controller('menuCtrl', ['$scope','departmentSrv','$rootScope','SearchSrv','URLS',
 function($scope, departmentSrv,$rootScope,SearchSrv,URLS) {
   
   $scope.departments =  departmentSrv.getAllDepartments().getList().$object;
   
   $scope.openSubCategory = function(indexId){
	   $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   }
}]);


