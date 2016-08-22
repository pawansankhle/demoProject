<<<<<<< HEAD
var app = angular.module("petwale", ['ngMaterial','ui.router','ngResource','restangular','ngAnimate','angularUtils.directives.dirPagination','ngMessages','md.data.table']);
=======
var app = angular.module("petCart", ['ui.router','ngResource','restangular','ngAnimate','angularUtils.directives.dirPagination','ngMessages','infinite-scroll','ui.bootstrap']);
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
app.value('count',0);
app.value('pageUpperLimit',12);
app.value('maxlimitofpagination',12);
app.value('pageLowerLimit',0);
app.value('baseUrl','http://pet4you-tshoopy.rhcloud.com/rest');
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
<<<<<<< HEAD
.config(['$stateProvider', '$urlRouterProvider', '$httpProvider','GLOBAL_APP', 'STATS', 'USER_ROLES','RestangularProvider','$mdThemingProvider', '$mdIconProvider'
    ,function ($stateProvider, $urlRouterProvider, $httpProvider,GLOBAL_APP, STATS, USER_ROLES,RestangularProvider,$mdThemingProvider, $mdIconProvider) {
  
  $mdIconProvider
  .defaultIconSet("./assets/svg/avatars.svg", 128)
  .icon("menu", "./assets/svg/menu.svg"        , 24)
                      .icon("share"      , "./assets/svg/share.svg"       , 24)
                      .icon("google_plus", "./assets/svg/google_plus.svg" , 512)
                      .icon("hangouts"   , "./assets/svg/hangouts.svg"    , 512)
                      .icon("twitter"    , "./assets/svg/twitter.svg"     , 512)
                      .icon("phone", "./assets/svg/phone.svg", 512)
                      .icon("avatar", "./assets/svg/avatar-1.svg",24);

                      $mdThemingProvider.theme('default')
                        .primaryPalette('blue')
                        .accentPalette('amber')
                        .warnPalette('pink');
                        

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
=======
.controller('ApplicationController',['$scope','$rootScope','USER_ROLES','AuthService', 'CartSrv','SessionSrv','GLOBAL_APP','AUTH_EVENTS','UserSrv','$state'
,'STATS','menuService',function($scope,$rootScope, USER_ROLES,AuthService,CartSrv,SessionSrv,GLOBAL_APP,AUTH_EVENTS,UserSrv,$state,STATS,menuService)
		{ 
		   var self = this;
           self.adminmenus = [];
		   $rootScope.currentUser = null;
		   $rootScope.shoppingCart = null;
		   $scope.toggleModal = false;
		   $scope.showsignup = false;
		   $scope.showLoading = ''
		  

		    
	       
	       CartSrv.getCart().then(function(res){$scope.setShoppingCart(res);},function(){});
	       UserSrv.getProfile().then(function(user){SessionSrv.saveUser(user);$rootScope.setCurrentUser(user)});
	       $rootScope.$watch('$rootScope.count',function(){return $rootScope.count},true);
	       $rootScope.$watch('$rootScope.pageUpperLimit',function(){return $rootScope.pageUpperLimit},true);
	       $rootScope.$watch('$rootScope.pageLowerLimit',function(){return $rootScope.pageLowerLimit},true);
	       $rootScope.$watch('$rootScope.currentUser',function(){return $rootScope.currentUser},true);
	       $rootScope.$watch('$scope.shoppingCart',function(){return $rootScope.shoppingCart},true);
	       $rootScope.$on('setShoppingCart',function(evnt,res){$scope.setShoppingCart(res.cart);});
		   $rootScope.$on('reload:menu',function(ev,data){$scope.loadMenu()})
           $rootScope.$on('toggleLoading',function(){
                  if(exist($scope.showLoading)){
                  	  $scope.showLoading = ''
                  }else{
                  	$scope.showLoading = 'loading';
                  }
           });

           $scope.loadMenu = function(){
           	 menuService.loadadminmenu().then(function(menus) {self.adminmenus = [].concat(menus);});
           }
           $scope.loadMenu();

           $scope.userRoles = USER_ROLES;
           $rootScope.setCurrentUser = function (user) 
		    {  
		      if(exist(user) && exist(user.roles))
			  {
			  	   if(user.roles[0].roleName == $scope.userRoles.admin || user.roles[0].roleName == $scope.userRoles.supplier)
			  	   	{
			  	   		$state.go(STATS.dashboard)
			  	   	}
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

}])
.config(['$stateProvider', '$urlRouterProvider', '$httpProvider','GLOBAL_APP', 'STATS', 'USER_ROLES','RestangularProvider', 
    function ($stateProvider, $urlRouterProvider, $httpProvider,GLOBAL_APP, STATS, USER_ROLES,RestangularProvider) {
	RestangularProvider.setBaseUrl('http://pet4you-tshoopy.rhcloud.com/rest');
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
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
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
<<<<<<< HEAD
        templateUrl: GLOBAL_APP.topRatedProductTplPath,
         controller: 'productCtrl'
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
              authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
             }
=======
				templateUrl: GLOBAL_APP.topRatedProductTplPath,
				 controller: 'productCtrl'
			},
			'homeFeaureItem@home': {
				templateUrl: GLOBAL_APP.featuredProductTplPath
				 //controller: 'homeSliderController'
			},
			'homeRecentView@home': {
				templateUrl: GLOBAL_APP.recentViewProductTplPath
				 //controller: 'homeSliderController'
			}
			},
		    resolve: {
          		breadcumb: function() {
            		return ['home'];
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
			   controller: 'productViewCtrl',
			   resolve: {
          		breadcumb: function($stateParams) {
            		return ['home','product',$stateParams.id];
               }
             }
		   })
		   .state(STATS.productReview,{
			   url: '/review/:id',
			   templateUrl: GLOBAL_APP.productreviewTplPath,
			   resolve: {
          		breadcumb: function($stateParams) {
            		return ['home','review',$stateParams.id];
               }
           }
			   //controller: 'productReviewCtrl'
		   })
		   .state(STATS.dashboard, {
			   	url: '/dashboard',
			   	templateUrl: GLOBAL_APP.adminDashBoardTplPath,
			   	controller : 'dashBoardCtrl',
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
			   		 }
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
           })
           .state(STATS.dashboardOrders, {
          url: '/orders',
          templateUrl: GLOBAL_APP.dashboardOrdersTplPath,
          controller: 'ordersCtrl',
          data:{
              authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
             }
           })
           .state(STATS.dashboardOrderView, {
          url: '/order/:id',
          templateUrl: GLOBAL_APP.dashboardOrderViewTplPath,
          controller: 'orderViewCtrl',
          data:{
              authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
             }
           })
           .state(STATS.dashboardProducts, {
          url: '/products',
          templateUrl: GLOBAL_APP.dashboardPrductsTplPath,
          controller: 'dashBoardProductCtrl',
          data:{
              authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
             }
           })
           .state(STATS.dashboardProductView, {
          url: '/product',
          templateUrl: GLOBAL_APP.dashboardPrductViewTplPath,
          controller: 'dashBoardProductViewCtrl',
          data:{
              authorizedRoles: [USER_ROLES.admin,USER_ROLES.supplier]
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
<<<<<<< HEAD
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
=======
			   	url: '/cartView',
			   	templateUrl: GLOBAL_APP.cartViewTplPath,
			   	resolve: {
          		breadcumb: function($stateParams) {
            		return ['cart','view',$stateParams.id];
               }
             }
			})
			.state(STATS.checkout, {
			   	url: '/checkout',
			   	templateUrl: GLOBAL_APP.checkoutTplPath,
			   	controller: 'checkoutCtrl',
			   	resolve: {
          		breadcumb: function() {
            		return ['checkout'];
                  }
                }
			})
			.state(STATS.checkoutLogin, {
			   	url: '/login',
			   	templateUrl: GLOBAL_APP.checkoutLoginTplPath,
			   	controller: 'checkoutCtrl',
			   	resolve: {
          		breadcumb: function() {
            		return ['checkout','login'];
                 }
                }
			 })
			.state(STATS.checkoutAddress, {
			   	url: '/address',
			   	templateUrl: GLOBAL_APP.checkoutAddressTplPath,
			   	controller: 'checkoutCtrl',
			   	resolve: {
          		breadcumb: function() {
            		return ['checkout','address'];
                }
               }
			})
			.state(STATS.checkoutPayment, {
			   	url: '/payment',
			   	templateUrl: GLOBAL_APP.checkoutPaymentTplPath,
			   	controller: 'checkoutCtrl',
			   	resolve: {
          		breadcumb: function() {
            		return ['checkout','payment'];
                }
               }
			})
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
           .state('homeCategoryProduct', {
              url: '/category/:cid',
              templateUrl: GLOBAL_APP.categoryTplPath,
              controller: 'productbyCategoryCtrl',
              resolve: {
          		breadcumb: function() {
            		return ['home','category'];
                }
               }
            })
           .state(STATS.user ,{
          abstract: true,
          url: '/user',
          template: '<div ui-view></div>'
       })
            .state(STATS.account ,{
<<<<<<< HEAD
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
=======
			    url: '/account',
			    template: '<div ui-view></div>',
			    contorller: 'accountCtrl',
			    resolve: {
          		breadcumb: function() {
            		return ['account'];
                }
               }
		   })
            .state(STATS.accountOrders ,{
			    url: '/orders',
			    templateUrl: GLOBAL_APP.ordersTplPath,
			    controller: 'accountOrdersCtrl',
			    resolve: {
          		breadcumb: function() {
            		return ['account','orders'];
                }
               }
		   })
            .state(STATS.accountOrderDetail ,{
			    url: '/order/:id',
			    templateUrl: GLOBAL_APP.orderDetailTplPath,
			    controller: 'accountOrdersCtrl',
			    resolve: {
          		breadcumb: function($stateParams) {
            		return ['account','order',$stateParams.id];
                }
               }
		   })
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
           .state(STATS.accountSetting, {
               url : '/setting',
               templateUrl: GLOBAL_APP.settingTplPath,
               controller: 'accountSettingCtrl',
               resolve: {
          		breadcumb: function($stateParams) {
            		return ['account','setting'];
                }
               }

           });
}]);


