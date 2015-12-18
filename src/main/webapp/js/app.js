var app = angular.module("DemoApp", ['ui.router','ngResource', 'ngAnimate','angularUtils.directives.dirPagination']);


app.config(function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');
	$stateProvider
	.state('home',{
		url: '/',
        views: {
        	// the main template will be placed here (relatively named)
            '': { templateUrl: HOME_TEMPLATE_PATH },
            'homeSlider@home': { 
            	templateUrl: SLIDER_TEMPLAE_PATH 
                //controller: 'homeSliderController'		
            },
            'homeproducts@home': { 
            	templateUrl: HOME_PRODUCT_TEMPLATE_PATH, 
                controller: 'homeProductCtrl'		
            },
            'homeTopRatedItem@home': {
				templateUrl: TOP_RATED_PRODUCT_TEMPLATE_PATH
				 //controller: 'homeSliderController'
			},
			'homeFeaureItem@home': {
				templateUrl: FEATURED_PRODUCT_TEMPLATE_PATH
				 //controller: 'homeSliderController'
			},
			'homeRecentView@home': {
				templateUrl: RECENT_VIEW_RPDOUCT_PATH
				 //controller: 'homeSliderController'
			}
			}
		   }).state('user',{
			    abstract: true,
			    url: '/user',
			    template: '<div ui-view></div>'
		   })
		   .state('user.login',{
			   url: '/login',
			   templateUrl: USER_LOGIN_FROM_TEMPLATE_PATH
		   })
		   .state('user.sighup',{
			   url: '/signup',
			   templateUrl: USER_SIGNUP_TEMPLATE_PATH
				   
		   })
		   .state('product',{
			    abstract: true,
			    url: '/product',
			    template: '<div ui-view></div>'
		   })
		   .state('product.view',{
			   url: '/view/:id',
			   templateUrl: VIEW_PRODUCT_TEMPLATE_PATH,
			   controller: 'productViewCtrl'
		   })
		   .state('homeCategoryProduct', {
              url: '/category/:name/:cid',
              template: 'I could sure use a drink right now.',
              controller: 'homeCategoryCtrl'
            })
			
    
});
