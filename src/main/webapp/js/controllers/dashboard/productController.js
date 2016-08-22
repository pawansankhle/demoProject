app.
controller('dashBoardProductCtrl',['$scope','pageLowerLimit','pageUpperLimit','productSrv','URLS','STATS','$state','departmentSrv','Msgs','$uibModal','$uibModalStack','GLOBAL_APP','$rootScope',
	function($scope,pageLowerLimit,pageUpperLimit,productSrv,URLS,STATS,$state,departmentSrv,Msgs,$uibModal,$uibModalStack,GLOBAL_APP,$rootScope){
	$scope.products = [];
    
    $scope.getProducts = function(lower,upper){
       productSrv.getProductList($scope.filter,lower,upper).then(function(res){
			res.forEach(function(p){
                $scope.products.push(p);
			});
		});
	}

    $scope.resetDept = function(){
        $scope.productDepartments = [{name:'-select department-',id:''}];
        $scope.productcategories = [{name:'-select category-',id:''}];
        departmentSrv.getAllDepartments().then(function(res){
             res.forEach(function(d){
                 $scope.productDepartments.push(d);
             })
         });
    }
    $scope.resetDept();
	
    

    $scope.getCategories = function(department){
        
        if(exist(department)){
             $scope.productcategories = [{name:'-select category-',id:''}];
             var dept = _.find($scope.productDepartments, function(dept){ return dept.id == department; });
              dept.categories.forEach(function(c){
                 $scope.productcategories.push(c);
             });
               
        }else{
           $scope.resetDept();
        }
       
    }
    $scope.getProducts(0,12);
     
    $scope.search = function(){
     	 $scope.products = [];
         if(exist($scope.productForm)){
            $scope.filter = productSrv.getFIQL($scope.productForm);
         }
         $scope.getProducts(0,12);
    };

     $scope.reset = function(){
        $scope.productForm = '';
        $scope.filter = '';
        $scope.search();
      }

     $scope.viewProduct = function(product){
          productSrv.setCurrentProduct(product);
          $state.go(STATS.dashboardProductView);
     }

     $scope.addProduct = function(productForm){
        $rootScope.$emit('toggleLoading');
         if(exist(productForm) && exist(productForm.department) && exist(productForm.category)){
              productForm.department  = _.find($scope.productDepartments, function(dept){ return dept.id == productForm.department; });
              productForm.category = _.find($scope.productcategories, function(cat){ return cat.id == productForm.category; });
              productSrv.addProduct(productForm).then(function(res){
                $scope.products.push(res);
                toastr.success(Msgs.productCreateMsg,"Product");
                 $scope.close();
                  $rootScope.$emit('toggleLoading');
              });
         }
         
     };

     $scope.enableDisableProduct = function(product,index,action){
        if(exist(product))
        {
           productSrv.enableDisableProduct(product,action).then(function(res){
                if(action == 'enable'){
                   toastr.success(Msgs.productEnableMsg,"Product");
                   $scope.products[index] = res;
                }else{
                   toastr.success(Msgs.productDisableMsg,"Product");
                   $scope.products[index] = res; 
                }
           });
        }

     }

     $scope.showAddProduct = function(){
          
          var modelInstance = $uibModal.open({
            animation: true,
            templateUrl: GLOBAL_APP.dashboardaddProductTplPath,
            controller: 'dashBoardProductCtrl',
            size: 'lg',
        });
     }
    $scope.close = function(){
      $uibModalStack.dismissAll();
    }

     
	 /*$(document).ready(function(){
                $(window).scroll(function () {
                    if($(window).scrollTop() + $(window).height() == $(document).height()) {
                        pageLowerLimit= pageUpperLimit+1;
                        pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
                        $scope.getProducts(pageLowerLimit,pageUpperLimit);
                    }
                });
            });*/
}]);
