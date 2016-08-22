app.controller('productbyCategoryCtrl',['productSrv','$scope','$stateParams','pageLowerLimit','pageUpperLimit',
    function(productSrv,$scope,$stateParams,pageLowerLimit,pageUpperLimit){
			var fiql = "";
			$scope.products = [];
           	

           

			$scope.getProductByCategory = function(pageLowerLimit,pageUpperLimit)
			{
				productSrv.getProductByCategoryId($stateParams.cid,pageLowerLimit,pageUpperLimit).then(function(res){
				 res.forEach(function(p){
					$scope.products.push(p);
				})
			});
			};
			$scope.getProductByCategory(pageLowerLimit,pageUpperLimit);
			
           $scope.loadMore = function(){
           	   $scope.loading = true;
           	   var last = $scope.products[$scope.products.length - 1];
           	   console.log(last);
             }


		}]);


