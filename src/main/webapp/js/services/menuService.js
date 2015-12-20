app.factory('menuSrv',['baseUrl', 'URLS','$resource', function(baseUrl,URLS, $resource)
{ 
	return $resource(baseUrl+URLS.departmentUrl,{id:'@id'},
    {
      update:{method: 'PUT'}
    });
  
}
]);
