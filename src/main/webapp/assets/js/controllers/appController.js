app.controller('AppController',['$scope','$rootScope','USER_ROLES','AuthService', 'CartSrv','SessionSrv','GLOBAL_APP','AUTH_EVENTS','UserSrv','$state'
,'STATS','menuService','$mdSidenav','$mdMedia','$mdDialog',
function($scope,$rootScope, USER_ROLES,AuthService,CartSrv,SessionSrv,GLOBAL_APP,AUTH_EVENTS,UserSrv,$state,STATS,menuService,$mdSidenav,$mdMedia,$mdDialog)
    { 
       var self = this;
       self.menus = [];
       $rootScope.currentUser = null;
       $rootScope.shoppingCart = null;
       $scope.toggleModal = false;
       $scope.showsignup = false;
       $scope.showlogin = true;
       self.$mdSidenav = $mdSidenav;
         
         CartSrv.getCart().then(function(res){$scope.setShoppingCart(res);},function(){});
         UserSrv.getProfile().then(function(user){SessionSrv.saveUser(user);$rootScope.setCurrentUser(user)});
         $rootScope.$watch('$rootScope.count',function(){return $rootScope.count},true);
         $rootScope.$watch('$rootScope.pageUpperLimit',function(){return $rootScope.pageUpperLimit},true);
         $rootScope.$watch('$rootScope.pageLowerLimit',function(){return $rootScope.pageLowerLimit},true);
         $rootScope.$watch('$rootScope.currentUser',function(){return $rootScope.currentUser},true);
         $rootScope.$watch('$scope.shoppingCart',function(){return $rootScope.shoppingCart},true);
         $rootScope.$on('setShoppingCart',function(evnt,res){$scope.setShoppingCart(res.cart);});
       $rootScope.$on('reload:menu',function(ev,data){$scope.loadMenu()})
   
           $scope.loadMenu = function(){
             menuService.loadadminmenu().then(function(menus) {self.menus = [].concat(menus);});
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
     /* $scope.authModal = function(modalFor){
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
       };*/
    
    /*function to toggle navbar */
    $scope.toggleNavbar = function() {
       self.$mdSidenav('left').toggle();
       
    }

    /*function to toggle right-navbar */   
    $scope.toggleRightNavbar = function() {
      $mdSidenav('right-navbar').toggle();
    }

    

    

    $scope.cancel = function() {
        $mdDialog.cancel();
  };
  
    /*function to show auth model windonw according to action*/  
  $scope.showAuthModal = function(ev) {
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
      var tplpath = '';
      tplpath = GLOBAL_APP.authTplPath;
      

      $mdDialog.show({
               controller: 'authCtrl',
               templateUrl: tplpath,
               parent: angular.element(document.body),
               targetEvent: ev,
               clickOutsideToClose:false,
               fullscreen: useFullScreen
            }).then(function(done){
           }
            ,function(){});
       }
      $scope.$watch(function() {
      return $mdMedia('xs') || $mdMedia('sm');
    }, function(wantsFullScreen) {
      $scope.customFullscreen = (wantsFullScreen === true);
    });
}]);