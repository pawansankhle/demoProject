var app = angular.module("petCart", ['ui.router','ngResource','restangular','ngAnimate','angularUtils.directives.dirPagination','ngMessages']);
app.value('count',0);
app.value('pageUpperLimit',12);
app.value('maxlimitofpagination',12);
app.value('pageLowerLimit',0);
app.value('baseUrl','http://localhost:8989/petCart/rest');
app.run(['$rootScope','count','AUTH_EVENTS','STATS','AuthService','CartSrv','$state',
function ($rootScope, count, AUTH_EVENTS, STATS, AuthService,CartSrv,$state) 
		{
			$rootScope.count = count;
			$rootScope.$on('$stateChangeStart', function (event, next) {
			if(next.name == STATS.dashboard || next.name == STATS.dashboardUM || next.name == STATS.dashboardOrders || next.name == STATS.dashboardProducts){
				var authorizedRoles = next.data.authorizedRoles;
				if (!AuthService.isAuthorized(authorizedRoles)) {
			         event.preventDefault();
			         $state.go(STATS.home);
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
.controller('ApplicationController',['$scope','$rootScope','USER_ROLES','AuthService', 'CartSrv','SessionSrv','GLOBAL_APP','AUTH_EVENTS','UserSrv','$state'
,'STATS','menuService',function($scope,$rootScope, USER_ROLES,AuthService,CartSrv,SessionSrv,GLOBAL_APP,AUTH_EVENTS,UserSrv,$state,STATS,menuService)
		{ 
		   var self = this;
           self.adminmenus = [];
		   $rootScope.currentUser = null;
		   $rootScope.shoppingCart = null;
		   $scope.toggleModal = false;
		   $scope.showsignup = false;
		   $scope.showlogin = true;
	       
	       CartSrv.getCart().then(function(res){$scope.setShoppingCart(res);},function(){});
	       UserSrv.getProfile().then(function(user){SessionSrv.saveUser(user);$rootScope.setCurrentUser(user)});
	       $rootScope.$watch('$rootScope.count',function(){return $rootScope.count},true);
	       $rootScope.$watch('$rootScope.pageUpperLimit',function(){return $rootScope.pageUpperLimit},true);
	       $rootScope.$watch('$rootScope.pageLowerLimit',function(){return $rootScope.pageLowerLimit},true);
	       $rootScope.$watch('$rootScope.currentUser',function(){return $rootScope.currentUser},true);
	       $rootScope.$watch('$scope.shoppingCart',function(){return $rootScope.shoppingCart},true);
	       $rootScope.$on('setShoppingCart',function(evnt,res){$scope.setShoppingCart(res.cart);});
		   
   
           menuService.loadadminmenu().then(function(menus) {self.adminmenus = [].concat(menus);});
           $scope.userRoles = USER_ROLES;
		   $rootScope.setCurrentUser = function (user) 
		    {  
			  if(exist(user) && exist(user.roles))
			  {
				    if(user.roles[0].roleName == $scope.userRoles.admin){$state.go(STATS.dashboard)}
				    $rootScope.currentUser = user;
		      }else{
			  	$rootScope.currentUser = null;
			  	$state.go(STATS.home);
			  	
			  }
            };
			
			$scope.isAuthorized = AuthService.isAuthorized;
			$scope.setShoppingCart = function (cart) 
		    {    
				$rootScope.shoppingCart = cart;
				SessionSrv.saveCart(cart);
				if(exist(cart.items)){
					$rootScope.count = cart.items.length;
				}else{
					$rootScope.count = 0;
				}
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
	RestangularProvider.setBaseUrl('http://localhost:8989/petCart/rest');
	//RestangularProvider.setDefaultHeaders({token: "x-restangular"});
	$urlRouterProvider.otherwise('/');
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$httpProvider.interceptors.push(['$injector',function ($injector) { return $injector.get('AuthInterceptor');} ]);
	$httpProvider.interceptors.push('AuthInterceptor');
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
			   	templateUrl: GLOBAL_APP.adminDashBoardTplPath,
			   	controller : 'dashBoardCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 }
           })
           .state(STATS.dashboardOrders, {
			   	url: '/orders',
			   	templateUrl: GLOBAL_APP.dashboardOrdersTplPath,
			   	controller: 'ordersCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 }
           })
           .state(STATS.dashboardOrderView, {
			   	url: '/order/:id',
			   	templateUrl: GLOBAL_APP.dashboardOrderViewTplPath,
			   	controller: 'orderViewCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 }
           })
           .state(STATS.dashboardProducts, {
			   	url: '/products',
			   	templateUrl: GLOBAL_APP.dashboardPrductsTplPath,
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 }
           })
           .state(STATS.dashboardProductView, {
			   	url: '/product/:id',
			   	templateUrl: GLOBAL_APP.dashboardPrductViewTplPath,
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 }
           })
           .state(STATS.dashboardUM, {
			   	url: '/usrManagement',
			   	templateUrl: GLOBAL_APP.dashboardUMTplPath,
			   	controller : 'UMCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 },
			    
           }).state(STATS.dashboardUMUsers, {
			   	url: '/users',
			   	templateUrl: GLOBAL_APP.dashboardUMUsersTplPath,
			   	controller : 'UMCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 },
			    
           })
           .state(STATS.dashboardViewUser, {
			   	url: '/user/:id',
			   	templateUrl: GLOBAL_APP.dashboardUMUserTplPath,
			   	controller : 'userCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 },
			    
           })
           /*.state(STATS.dashboardUMSuppliers, {
			   	url: '/suppliers',
			   	templateUrl: GLOBAL_APP.dashboardUMSuppliersTplPath,
			   	controller : 'UMCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 },
			    
           })*/
           /*.state(STATS.dashboardViewSupplier, {
			   	url: '/supplier/:id',
			   	templateUrl: GLOBAL_APP.dashboardUMSuppliersTplPath,
			   	controller : 'supplierCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin]
			   		 },
			    
           })*/
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
           .state(STATS.user ,{
			    abstract: true,
			    url: '/user',
			    template: '<div ui-view></div>'
		   })
            .state(STATS.account ,{
			    url: '/account',
			    template: '<div ui-view></div>',
			    contorller: 'accountCtrl'
		   })
            .state(STATS.accountOrders ,{
			    url: '/orders',
			    templateUrl: GLOBAL_APP.ordersTplPath,
			    controller: 'accountOrdersCtrl'
		   })
            .state(STATS.accountOrderDetail ,{
			    url: '/order/:id',
			    templateUrl: GLOBAL_APP.orderDetailTplPath,
			    controller: 'accountOrdersCtrl'
		   })
           .state(STATS.accountSetting, {
               url : '/setting',
               templateUrl: GLOBAL_APP.settingTplPath,
               controller: 'accountSettingCtrl'

           });
}]);


