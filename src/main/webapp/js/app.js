var app = angular.module("DemoApp", ['ui.router','ngResource', 'ngAnimate','angularUtils.directives.dirPagination']);
app.value('count',0);
app.value('baseUrl','http://localhost:8888/petCart/rest');
app.run(function ($rootScope, count, AUTH_EVENTS, STATS, AuthService) 
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
		})
app.controller('ApplicationController',['$scope','$rootScope','USER_ROLES','AuthService',
		function($scope,$rootScope, USER_ROLES, AuthService)
		{ 
	       $rootScope.$watch('$rootScope.count',function(){return $rootScope.count},true);
	       $scope.currentUser = null;
		   $scope.userRoles = USER_ROLES;
		   $scope.isAuthorized = AuthService.isAuthorized;
		   $scope.setCurrentUser = function (user) 
		    {
			  $scope.currentUser = user;
			};
		
		}]
        
		
);


app.config(function ($stateProvider, $urlRouterProvider, $httpProvider,GLOBAL_APP, STATS, USER_ROLES) {
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
                controller: 'homeProductCtrl'		
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
		   .state(STATS.dashboard, {
			   	url: '/dashboard',
			   	templateUrl: GLOBAL_APP.dasthBoardTplPath,
			   	data:{
			   			authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
			   		 }
           })
		   .state('homeCategoryProduct', {
              url: '/category/:name/:cid',
              template: 'I could sure use a drink right now.',
              controller: 'homeCategoryCtrl'
            })
			
    
});
