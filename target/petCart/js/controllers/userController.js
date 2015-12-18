app.
controller('userAuthCtrl', ['$scope','userSrv','$state', function($scope, userSrv,$state) {
   
   $scope.submitForm = function(userLoginForm) {
	   if(userLoginForm.$valid){
		   Notifier.success('you have been Loged in','Login');
		   $state.go('home');
	   }
     };
}]);
