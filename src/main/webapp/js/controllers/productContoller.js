app
.controller('productCtrl', ['$scope','$stateParams','productSrv', function($scope, $stateParams, productSrv) {

	$scope.products =  productSrv.getList().$object;
	$scope.currentPage = 1;
	$scope.pageSize = 12;
    $scope.pageChangeHandler = function(num) {
		console.log('meals page changed to ' + num);
	};


}])
.controller('productViewCtrl',['$scope', '$stateParams', 'productSrv','SearchSrv','URLS',
	function($scope, $stateParams, productSrv,SearchSrv,URLS){
	var fiql = "?_s=id=="+$stateParams.id;
	SearchSrv.fiqlSearch(URLS.productSearchUrl+fiql).then(
       function(res){
       	$scope.product = res[0];
       }
		); 
	//productSrv.get($stateParams.id).$object;
	var reviwFiql = "?_s=product.id=="+$stateParams.id;
	SearchSrv.fiqlSearch(URLS.reviewSearchUrl,reviwFiql).then(function(res){
		$scope.reivews =  res;
	});

	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};

}])
.controller('productbyCategoryCtrl',
		['$rootScope','$scope','URLS','SearchSrv','$stateParams','pageLowerLimit','pageUpperLimit','maxlimitofpagination',
		 function($rootScope,$scope,URLS,SearchSrv,$stateParams,pageLowerLimit,pageUpperLimit,maxlimitofpagination){
			var fiql = "";
			fiql="?_s=category.id=="+$stateParams.cid+"&lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit
			SearchSrv.fiqlSearch(URLS.productSearchUrl,fiql).then(function(res){
				  $scope.products = res;
			})
            
			$(document).ready(function(){
				$(window).scroll(function () {
					if($(window).scrollTop() + $(window).height() == $(document).height()) {
						pageLowerLimit= pageUpperLimit+1;
						pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
						fiql="?_s=category.id=="+$stateParams.cid+"&lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit
						SearchSrv.fiqlSearch(URLS.productSearchUrl,fiql).getList().then(function(newProducts){
							newProducts.forEach(function(p){
								$scope.products.push(p);
							});
						})
					}
				});
			});

		}]);


