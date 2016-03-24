app.controller('productbyCategoryCtrl',['productSrv','$rootScope','$scope','URLS','SearchSrv','$stateParams','pageLowerLimit','pageUpperLimit','maxlimitofpagination',
    function(productSrv,$rootScope,$scope,URLS,SearchSrv,$stateParams,pageLowerLimit,pageUpperLimit,maxlimitofpagination){
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
			
            
			$(document).ready(function(){
				$(window).scroll(function () {
					if($(window).scrollTop() + $(window).height() == $(document).height()) {
						pageLowerLimit= pageUpperLimit+1;
						pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
						$scope.getProductByCategory(pageLowerLimit,pageUpperLimit);
                       }
				});
			});

		}]);


