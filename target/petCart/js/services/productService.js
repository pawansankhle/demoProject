app.factory('productSrv',  function($resource) { 
   return $resource(PRODUCT_URL,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
});
