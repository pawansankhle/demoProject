app.factory('productSrv',['URLS', '$resource', 'baseUrl', function(URLS, $resource, baseUrl) { 
   return $resource(URLS.productUrl,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
}]);
