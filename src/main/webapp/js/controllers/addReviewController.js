app.controller('addRevieCtrl',['$scope','$uibModalStack','productSrv','GLOBAL_APP','$uibModal','Msgs',
	function($scope,$uibModalStack,productSrv,GLOBAL_APP,$uibModal,Msgs){
 
 $scope.infoMsg = Msgs.reviewInfoMsg;
 $scope.errorMsg = false;
 $scope.reviewForm = '';
 $scope.rate = 0;
 $scope.max = 5;
  $scope.isReadonly = false;

$scope.hoveringOver = function(value) {

    $scope.overStar = value;
    $scope.percent = 100 * (value / $scope.max);
  };

 $scope.addReview = function(action){
 	 var modelInstance = $uibModal.open({
      			animation: true,
      			templateUrl: GLOBAL_APP.addReviewTplPath,
      			controller: 'addRevieCtrl',
      			size: 'lg',
      			
    		});
            
		};
   closeReviewModal = function()
   {
       $scope.close();
   };

   $scope.close = function()
   {
   	$uibModalStack.dismissAll();
   }

   $scope.submitReivewForm = function(form){
   	     form.rating = $scope.overStar;
   	     productSrv.addProductReview(form).then(function(res){

      	 	if(res.status == 'failed'){
      	 		 $scope.errorMsg = res.msg;
      	 		}else if(res.status == 'success'){
      	 		 $scope.errorMsg = '';
      	 		 $scope.infoMsg = Msgs.reviewSuccessMsg;
      	 		 toastr.success(Msgs.reviewSuccessMsg,"Review");
      	 		 $scope.isAddPrifileImage = !$scope.isAddPrifileImage;
      	 		 }
      	 	  });
      	};

}])