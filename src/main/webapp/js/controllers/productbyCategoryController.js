app.controller('productbyCategoryCtrl',['productSrv','$scope','$stateParams','pageLowerLimit','pageUpperLimit','breadcumb','$rootScope',
    function(productSrv,$scope,$stateParams,pageLowerLimit,pageUpperLimit,breadcumb,$rootScope){
			var fiql = "";
			$scope.products = [];
           	$scope.breadcumb = breadcumb;
           	$scope.orderBy = 'price';
           	$scope.type = 'desc'
            
           
            
			$scope.getProductByCategory = function(pageLowerLimit,pageUpperLimit)
			{   
				$scope.products = [];
				productSrv.getProductByCategoryId($stateParams.cid,pageLowerLimit,pageUpperLimit,$scope.orderBy,$scope.type).then(function(res){
				 res.forEach(function(p){
					$scope.products.push(p);
				})
				  $rootScope.$emit('toggleLoading');
			});
			};
			$scope.getProductByCategory(pageLowerLimit,pageUpperLimit);
			
           $scope.loadMore = function(){
           	   $scope.loading = true;
           	   var last = $scope.products[$scope.products.length - 1];
           	   
             }

            $scope.sortBy = function(orderBy,type){
            	$scope.orderBy = orderBy;
            	$scope.type = type;
               $scope.getProductByCategory(pageLowerLimit,pageUpperLimit);
            }


		}]);


