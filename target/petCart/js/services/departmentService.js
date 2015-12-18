app.factory('departmentSrv',  function($resource) { 
   return $resource(DEPARTMENT_URL,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
});
