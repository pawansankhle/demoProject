var app = angular.module("DemoApp", ['ui.router','ngResource','restangular','ngAnimate','angularUtils.directives.dirPagination']);
app.value('count',0);
app.value('pageUpperLimit',12);
app.value('maxlimitofpagination',12);
app.value('pageLowerLimit',0);
app.value('baseUrl','http://localhost:8888/petCart/rest')
app.run(['$rootScope','count','AUTH_EVENTS','STATS','AuthService','CartSrv','$state',
function ($rootScope, count, AUTH_EVENTS, STATS, AuthService,CartSrv,$state) 
		{
			$rootScope.count = count;
			$rootScope.$on('$stateChangeStart', function (event, next) {
			if(next.name == STATS.dashboard){
				var authorizedRoles = next.data.authorizedRoles;
			    if (!AuthService.isAuthorized(authorizedRoles)) {
			         event.preventDefault();
			      if (AuthService.isAuthenticated()) {
			        // user is not allowed
			        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
			      } else {
			        // user is not logged in
			        $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
			      }
			    }
			   }
			
			  });
}])
.controller('ApplicationController',['$scope','$rootScope','USER_ROLES','AuthService', 'CartSrv','SessionSrv','GLOBAL_APP','AUTH_EVENTS','UserSrv',
		function($scope,$rootScope, USER_ROLES,AuthService,CartSrv,SessionSrv,GLOBAL_APP,AUTH_EVENTS,UserSrv)
		{ 
		   $rootScope.currentUser = null;
		   $rootScope.shoppingCart = null;
		   $scope.toggleModal = false;
		   $scope.showsignup = false;
		   $scope.showlogin = true;
	       CartSrv.getCart().then(function(res){$scope.setShoppingCart(res);},function(){});
	       UserSrv.getProfile().then(function(user){$rootScope.setCurrentUser(user)});
	       
	       $rootScope.$watch('$rootScope.count',function(){return $rootScope.count},true);
	       $rootScope.$watch('$rootScope.pageUpperLimit',function(){return $rootScope.pageUpperLimit},true);
	       $rootScope.$watch('$rootScope.pageLowerLimit',function(){return $rootScope.pageLowerLimit},true);
	       $rootScope.$watch('$rootScope.currentUser',function(){return $rootScope.currentUser},true);
	       $rootScope.$watch('$scope.shoppingCart',function(){return $rootScope.shoppingCart},true);
	       $rootScope.$on('setShoppingCart',function(evnt,res){
			       $scope.setShoppingCart(res.cart);
			   });
		   
	       $scope.userRoles = USER_ROLES;
		   $scope.isAuthorized = AuthService.isAuthorized;
		   $rootScope.setCurrentUser = function (user) 
		    {   
				$rootScope.currentUser = user;
			};
			$scope.setShoppingCart = function (cart) 
		    {    
				$rootScope.shoppingCart = cart;
				$rootScope.count = cart.items.length;
			};
			$scope.authModal = function(modalFor){
			    if(modalFor == 'login')
	            {
					$scope.toggleModal = !$scope.toggleModal;
					$scope.showlogin = true;
					$scope.showsignup = false;
						
				}
	            if(modalFor == 'signup')
	            {
				    $scope.toggleModal = !$scope.toggleModal;
				    $scope.showlogin = false;
				    $scope.showsignup = true;
				    $scope.toggleModal = !$scope.toggleModal;
					
				}
			 };
}])
.config(['$stateProvider', '$urlRouterProvider', '$httpProvider','GLOBAL_APP', 'STATS', 'USER_ROLES','RestangularProvider', 
    function ($stateProvider, $urlRouterProvider, $httpProvider,GLOBAL_APP, STATS, USER_ROLES,RestangularProvider) {
	RestangularProvider.setBaseUrl('http://localhost:8888/petCart/rest');
	$urlRouterProvider.otherwise('/');
	$httpProvider.interceptors.push(['$injector',function ($injector) { return $injector.get('AuthInterceptor');} ]);
	$stateProvider
	.state(STATS.home,{
		url: '/',
		views: {
        	// the main template will be placed here (relatively named)
            '': { templateUrl: GLOBAL_APP.homeTplPath },
            'homeSlider@home' : { 
            	templateUrl: GLOBAL_APP.sliderTplPath 
                //controller: 'homeSliderController'		
            },
            'homeproducts@home': { 
            	templateUrl: GLOBAL_APP.homeProductTplPath, 
                controller: 'productCtrl'		
            },
           'homeTopRatedItem@home': {
				templateUrl: GLOBAL_APP.topRatedProductTplPath
				 //controller: 'homeSliderController'
			},
			'homeFeaureItem@home': {
				templateUrl: GLOBAL_APP.featuredProductTplPath
				 //controller: 'homeSliderController'
			},
			'homeRecentView@home': {
				templateUrl: GLOBAL_APP.recentViewProductTplPath
				 //controller: 'homeSliderController'
			}
			}
		   }).state(STATS.user ,{
			    abstract: true,
			    url: '/user',
			    template: '<div ui-view></div>'
		   })
		   .state(STATS.userLogin ,{
			   url: '/login',
			   templateUrl: GLOBAL_APP.loginTplPath,
			   
		   })
		   .state(STATS.userSighup,{
			   url: '/signup',
			   templateUrl: GLOBAL_APP.signUpTplPath,
			   
				   
		   })
		   .state(STATS.product ,{
			    abstract: true,
			    url: '/product',
			    template: '<div ui-view></div>'
		   })
		   .state(STATS.productView,{
			   url: '/view/:id',
			   templateUrl: GLOBAL_APP.viewProductTplPath,
			   controller: 'productViewCtrl'
		   })
		   .state(STATS.productReview,{
			   url: '/review/:id',
			   templateUrl: GLOBAL_APP.productreviewTplPath,
			   //controller: 'productReviewCtrl'
		   })
		   .state(STATS.dashboard, {
			   	url: '/dashboard',
			   	templateUrl: GLOBAL_APP.dasthBoardTplPath,
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
			   		 }
           })
           .state(STATS.cart, {
			   	url: '/cartView',
			   	templateUrl: GLOBAL_APP.cartViewTplPath,
			})
			.state(STATS.checkout, {
			   	url: '/checkout',
			   	templateUrl: GLOBAL_APP.checkoutTplPath,
			   	controller: 'checkoutCtrl'
			})
			.state(STATS.checkoutLogin, {
			   	url: '/login',
			   	templateUrl: GLOBAL_APP.checkoutLoginTplPath,
			   	controller: 'checkoutCtrl'
			 })
			.state(STATS.checkoutAddress, {
			   	url: '/address',
			   	templateUrl: GLOBAL_APP.checkoutAddressTplPath,
			   	controller: 'checkoutCtrl'
			})
			.state(STATS.checkoutPayment, {
			   	url: '/payment',
			   	templateUrl: GLOBAL_APP.checkoutPaymentTplPath,
			   	controller: 'checkoutCtrl'
			})
           .state('homeCategoryProduct', {
              url: '/category/:cid',
              templateUrl: GLOBAL_APP.categoryTplPath,
              controller: 'productbyCategoryCtrl'
            })
			
    
}]);
