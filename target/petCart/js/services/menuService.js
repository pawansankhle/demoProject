app.factory('menuSrv', function($resource) { 
	console.log(CATEGORY_URL);
   return $resource(CATEGORY_URL,{id:'@id'},
    {
      update: {
      method: 'PUT'
    }
    });
  
});
