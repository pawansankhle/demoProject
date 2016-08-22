app.service('UserSrv',['Restangular','baseUrl','URLS','SessionSrv','$resource','$http','fileUploadSrv',
 function (Restangular,baseUrl,URLS,SessionSrv,$resource,$http,fileUploadSrv){
	  var userSrv = {}; 
	  
	   

	this.getService = function(url)
	{
			return Restangular.all(url);
    };
    
    this.getUserList = function(lower,upper,filter){
       var url = '';
       if(exist(filter)){
        url  = URLS.userSearch+filter+"&lowerLimit="+lower+"&upperLimit="+upper;
       }else{
        url = URLS.userSearch+"?lowerLimit="+lower+"&upperLimit="+upper;
      }
       return this.getService(url).getList();
    }
	this.getProfile = function()
	{
		return $http.get(baseUrl+URLS.userProfileUrl)
		.then(function(res){
		    if(res.status == 204){
		        return null;
		    }
		     return res.data;
          },function(res){});
	  };

	 this.uploadProfileImage = function(id,filename,files)
     {   
         var url = baseUrl+URLS.userUploadProfilePicUrl+id+"/"+filename
         fileUploadSrv.uploadFile(files,url);
     }
	   
	}]);



