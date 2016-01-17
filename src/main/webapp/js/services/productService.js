app.factory('productSrv',['URLS', 'Restangular', 'baseUrl', function(URLS, Restangular, baseUrl) { 
        return Restangular.all(URLS.productUrl);
    
}]);
