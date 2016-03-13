app.
controller('UMCtrl',['$scope','UserSrv','URLS','STATS','$state','pageLowerLimit','pageUpperLimit','Msgs',function($scope,UserSrv,URLS,STATS,$state,pageLowerLimit,pageUpperLimit,Msgs){
  
  $scope.createUserform = {
     roles : [{roleName: ''}],
   };
  
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
                    toastr.info(Msgs.errorMsg,'User Action');
            },
            function(res){
             toastr.error(Msgs.errorMsg,'User Action');
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
               toastr.info(Msgs.errorMsg,'User Action');
            
            },function(res){
             toastr.error(Msgs.errorMsg,'User Action');
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
     var fiql= "?_s=";
     $scope.users = [];
     if(exist($scope.form.byRole)){
          fiql+= "(roles.roleName=="+$scope.form.byRole+");";
        }
      if(exist($scope.form.byName)){
            fiql+="(username=="+$scope.form.byName+");";
        }
    if(exist(fiql)){
           fiql = fiql.substring(0,fiql.length-1);
       }else{
         fiql = "";
       }
   $scope.filter = fiql;
   $scope.getUserList(0,12)

}

$scope.addUser = function(form){
    UserSrv.getService(URLS.userCreateUrl).post(form).then(function(res){
        $scope.isAddUser = !$scope.isAddUser;
        toastr.success(Msgs.userCreateSuccessMsg,'User');
         $scope.users.push(res);
      },function(res){
        toastr.error(Msgs.errorMsg,'User');
      });
  }

}]);