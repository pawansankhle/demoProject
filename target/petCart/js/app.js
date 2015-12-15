var app = angular.module("DemoApp", ['ui.router','ngResource']);

app.config(function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/');
	$stateProvider
	.state('home',{
		url: '/',
        views: {
        	// the main template will be placed here (relatively named)
            '': { templateUrl: 'views/home.html' },
            'homeSlider@home': { 
            	templateUrl: 'views/slider/home-slider.html' 
                //controller: 'homeSliderController'		
            },
            'homeproducts@home': { 
            	templateUrl: 'views/product/home-product.html', 
                controller: 'homeProductCtrl'		
            },
            'homeTopRatedItem@home': {
				templateUrl: 'views/other/top-rated-product.html'
				 //controller: 'homeSliderController'
			},
			'homeFeaureItem@home': {
				templateUrl: 'views/other/feature-product.html'
				 //controller: 'homeSliderController'
			},
			'homeRecentView@home': {
				templateUrl: 'views/other/recent-view.html'
				 //controller: 'homeSliderController'
			},
			}
		   }).state('homeCategoryProduct', {
              url: '/category/:name/:cid',
              template: 'I could sure use a drink right now.',
              controller: 'homeCategoryCtrl'
            })
			
    
});