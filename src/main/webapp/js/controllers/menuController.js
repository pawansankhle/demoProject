app.
controller('menuCtrl', ['$scope','departmentSrv','$rootScope','SearchSrv','URLS',
 function($scope, departmentSrv,$rootScope,SearchSrv,URLS) {
   
    departmentSrv.getAllDepartments().then(function(res){
   	   $scope.departments = res;
    });
   
   $scope.openSubCategory = function(indexId){
	   $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   }
}]);


