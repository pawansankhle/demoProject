app.
controller('menuCtrl', ['$scope','departmentSrv','$rootScope','SearchSrv','URLS','$state'
 ,function($scope, departmentSrv,$rootScope,SearchSrv,URLS,$state) {
   
    departmentSrv.getAllDepartments().then(function(res){
   	   $scope.departments = res;
    });
   
   $scope.openSubCategory = function(indexId){
   	  $scope.resetMenu($scope.departments[indexId]);
   	  $scope.departments[indexId].isActive= true;
   	  $scope.departments[indexId].isOpen = !$scope.departments[indexId].isOpen;
   	  $('#ps-menu-'+indexId).slideToggle(300, 'linear');
   	}

   $scope.resetMenu = function(dept) {
   	  var index = 0;

      angular.forEach($scope.departments,function(obj){
         obj.isActive = false;
         obj.categories.forEach(function(obj){
              obj.isActive = false;
         })
         if(dept != obj){
         	  $('#ps-menu-'+index).slideUp(300,'linear');
            obj.isOpen = false;
          }
         index+=1
      });
   }

   $scope.resetCategoryMenu = function(categories) {
   		angular.forEach(categories,function(obj){
            obj.isActive = false;
   		});
   		
   }

   $scope.openCategory = function(dept,categories,category){
   	  $scope.resetMenu(dept);
   	  $scope.resetCategoryMenu(categories)
   	  category.isActive = true;
   	  $state.go('homeCategoryProduct',{cid: category.id,name: category.name},{reload : true})
   	  
   }
   
}]);


