app.service('menuService', ['$q','STATS','AuthService','USER_ROLES',
    function MenuService($q,STATS,AuthService,USER_ROLES) {
	   
    
    return {
      loadadminmenu: function() {
        var adminmenu = [{
        name: 'Products',
        icon: 'fa fa-product-hunt',
        href: STATS.dashboardProducts
           },{
        name: 'Orders',
        icon: 'fa fa-th-list',
        href: STATS.dashboardOrders
      }];
      if(AuthService.isAuthorized(USER_ROLES.admin)){
            adminmenu.push({
          icon: 'fa fa-users',
          name:'User Management',
          href: STATS.dashboardUM
      });
     }
        var deferred = $q.defer();
        setTimeout(function() {
          deferred.resolve(adminmenu);
        }, 1000);
        return deferred.promise;
       },
    };
  }]);

