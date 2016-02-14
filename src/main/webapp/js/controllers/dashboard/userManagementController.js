app.
controller('UMCtrl',['$scope','UserSrv','URLS','STATS','$state',function($scope,UserSrv,URLS,STATS,$state){
  
  
  $scope.users = UserSrv.getService(URLS.findAllUsersUrl).getList().$object;
  $scope.suppliers = UserSrv.getService(URLS.findAllSuppliersUrl).getList().$object;
  
  $scope.adminAction = function(action,user,index,type){
    var url = '';
    switch(action){
      case 'disable':
        if(type == 'user'){
             url = URLS.userActionUrl+action+"/"+user.id;
        }else if(type == 'supplier'){
            url = URLS.supplierActionUrl+action+"/"+user.id;
        }
        UserSrv.getService(url).post()
         .then(
         	function(res){
            if(exist(res)){
                 if(type == 'user'){
                    $scope.users[index] = res;
                 }else if(type == 'supplier'){
                    $scope.suppliers[index] = res;
                 }
         	       toastr.success(user.firstname+' disable Successfully...','User Action');
         	  }else 
               toastr.info('Something went Wrong...','User Action');
            
         	},function(res){
             toastr.error('Something went Wrong...','User Action');
         	});
         
         break;
      case 'enable':
        if(type == 'user'){
             url = URLS.userActionUrl+action+"/"+user.id;
        }else if(type == 'supplier'){
            url = URLS.supplierActionUrl+action+"/"+user.id;
        }
        UserSrv.getService(url).post()
         .then(
         	function(res){
            if(exist(res)){
                 if(type == 'user'){
                    $scope.users[index] = res;
                 }else if(type == 'supplier'){
                    $scope.suppliers[index] = res;
                 }
         	       toastr.success(user.firstname+' enable Successfully...','User Action');
         	  }else 
               toastr.info('Something went Wrong...','User Action');
            
         	},function(res){
             toastr.error('Something went Wrong...','User Action');
         	});
         break;
      case 'delete':
         if(type == 'user'){
            url = URLS.userDeleteUrl+user.id;
         }else if(type == 'supplier'){
            url = URLS.supplierDeleteUrl+user.id;
         }
         UserSrv.getService(url).remove()
         .then(
         	function(res){
            if(type == 'user'){
              $scope.users = _.filter($scope.users, function(usr){ return usr.id != user.id; });
            }else if(type == 'supplier'){
                   $scope.suppliers = _.filter($scope.suppliers, function(sply)
                    { return sply.id != user.id; 
                    });
                 }
              toastr.success(user.firstname+' delete Successfully...','User Action');
         	},
         function(res){
            console.log(res);
         });
         
         break;
    }
  }
  $scope.showAddSupplierModal = function(show){
     $scope.isAddSupplier = show;
  }

}])
.controller('supplierCtrl','$scope',function($scope){
     

})
.controller('userCtrl','$scope',function($scope){
     

});