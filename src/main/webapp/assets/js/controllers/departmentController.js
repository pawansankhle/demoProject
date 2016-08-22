app.controller('departmentCtrl', ['$scope','URLS','departmentSrv', function($scope,URLS, departmentSrv) {
	 $scope.departments = departmentSrv.getList().$object;
    
      
}]);
