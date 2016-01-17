app.controller('departmentCtrl', ['$scope','departmentSrv', function($scope, departmentSrv) {
	  $scope.departments = departmentSrv.getList().$object;
	
}]);
