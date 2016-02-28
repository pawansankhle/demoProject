app.
controller('UMCtrl',['$scope','UserSrv','URLS','STATS','$state','pageLowerLimit','pageUpperLimit',function($scope,UserSrv,URLS,STATS,$state,pageLowerLimit,pageUpperLimit){
  
  

  /*$scope.users = UserSrv.getService(URLS.findAllUsersUrl).getList().$object;
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
  }*/
  $scope.users = [];
  $scope.filter = "";
  $scope.getUserList = function(lower,upper){
    url = '';
    if(exist($scope.filter)){
        url  = URLS.userSearch+$scope.filter+"&lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit;
    }else{
        url = URLS.userSearch+"?lowerLimit="+pageLowerLimit+"&upperLimit="+pageUpperLimit;
    }
    
     UserSrv.getService(url).getList().then(function(users){
     users.forEach(function(u){
            $scope.users.push(u);
        });

    });

  }
   $scope.getUserList(0,12);
       
   $scope.adminAction = function(action,user,index,type){
    
    var url = '';
    var fiql = ''; 
    switch(action){
      case 'disable':
        url = URLS.userActionUrl+action+"/"+user.id;
        UserSrv.getService(url).post()
         .then(
            function(res){
                if(exist(res)){
                    $scope.users[index] = res;
                    toastr.success(user.firstName+' disable Successfully...','User Action');
                       
                }else 
                    toastr.info('Something went Wrong...','User Action');
            },
            function(res){
             toastr.error('Something went Wrong...','User Action');
            });
        break;
        case 'enable':
          url = URLS.userActionUrl+action+"/"+user.id;
          UserSrv.getService(url).post()
         .then(
            function(res){
            if(exist(res)){
                 $scope.users[index] = res;
                 toastr.success(user.firstName+' enable Successfully...','User Action');
              }else 
               toastr.info('Something went Wrong...','User Action');
            
            },function(res){
             toastr.error('Something went Wrong...','User Action');
            });
         break;
      case 'delete':
         url = URLS.userDeleteUrl+user.id;
         UserSrv.getService(url).remove()
         .then(
            function(res){
                $scope.users = _.filter($scope.users, function(usr){ return usr.id != user.id; });
                toastr.success(user.firstName+' delete Successfully...','User Action');
            },
            function(res){
            console.log(res);
         });
         
         break;
    }
  }  
   $(document).ready(function(){
                $(window).scroll(function () {
                    if($(window).scrollTop() + $(window).height() == $(document).height()) {
                        pageLowerLimit= pageUpperLimit+1;
                        pageUpperLimit= pageUpperLimit+maxlimitofpagination+1;
                        $scope.getUserList(pageLowerLimit,pageUpperLimit);
                    }
                });
            });

  
  $scope.search = function(){
     var fiql= "";
     $scope.users = [];
     if(exist($scope.form) && (exist($scope.form.byRole) || exist($scope.form.byName))){
        fiql= "?_s=";
        if(exist($scope.form.byRole)){
            fiql+= "(roles.roleName=="+$scope.form.byRole+")";
            if(exist($scope.form.byName)){
                   fiql+=";"
            }
        }
        if(exist($scope.form.byName)){
            fiql+="(username=="+$scope.form.byName+")";
            
        }
    }
   $scope.filter = fiql;
   $scope.getUserList(0,12)

}
 
}])

.controller('userCtrl','$scope',function($scope){
     

});