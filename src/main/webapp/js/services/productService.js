app.factory('productSrv',['URLS', '$resource', 'baseUrl', function(URLS, $resource, baseUrl) { 
   return $resource(baseUrl+URLS.productUrl,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
}]);
