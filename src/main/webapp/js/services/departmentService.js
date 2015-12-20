app.factory('departmentSrv',['$resource', 'URLS',  function($resource,URLS) { 
   return $resource(context+URLS.departmentUrl,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
}]);
