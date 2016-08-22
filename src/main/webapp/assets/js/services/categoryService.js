app.factory('categorySrv', ['URLS','$resource',  function($resource,URLS) { 
   return $resource(URLS.categoryUrl,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
}]);
