(function() {
  'use strict';

  app.service('menuService', ['$q','STATS', MenuService]);

  /**
   * Menu DataService
   * Uses embedded, hard-coded data model; acts asynchronously to simulate
   * remote data service call(s).
   *
   * @returns {{loadAll: Function}}
   * @constructor
   */
  function MenuService($q,STATS) {
	   var adminmenu = [{
				icon: 'material-icons',
			    name:'User Management',
			    href: 'dashboard'
			},
			{
				name: 'Orders',
				icon: 'fa fa-settings',
				href: STATS.dashboardOrders
			},
			{
				name: 'Products',
				icon: 'zmdi zmdi-settings',
				href: STATS.dashboardProducts
           }];
           
		
    
    return {
      /*loadmenu: function() {
        // Simulate async nature of real remote calls
        var deferred = $q.defer();
        setTimeout(function() {
          deferred.resolve(menu);
        }, 1000);
        return deferred.promise;
        //return $q.when(menu);
      },*/
      loadadminmenu: function() {
        var deferred = $q.defer();
        setTimeout(function() {
          deferred.resolve(adminmenu);
        }, 1000);
        return deferred.promise;
       },
    };
  }

})();
