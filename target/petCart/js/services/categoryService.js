app.factory('categorySrv',  function($resource) { 
   return $resource(CATEGORY_URL,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
});