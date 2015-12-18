app.
factory('userSrv',  function($resource) { 
   return $resource('',{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
});
