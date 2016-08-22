app.controller('productViewCtrl',['$scope','$rootScope', '$stateParams', 'productSrv','Msgs','UserSrv','fileUploadSrv','$mdDialog','GLOBAL_APP','$mdMedia',
	function($scope,$rootScope, $stateParams, productSrv,Msgs,UserSrv,fileUploadSrv,$mdDialog,GLOBAL_APP,$mdMedia){
     $scope.isAddReview = false;
     $scope.reviewForm = '';
     var fileName = '';
     var files = '';
     $scope.errorMsg = false;
     $scope.infoMsg = Msgs.reviewInfoMsg;
     $scope.isAddPrifileImage = false;

	 $scope.getProductById = function(id){
	 	 productSrv.getProductbyId($stateParams.id | id).then(function(res){
	 	$scope.product = res[0];
	 	productSrv.setCurrentProduct(res[0]);
	 });
    }
	 

	 $scope.getRatingById = function(id){
         productSrv.getRatingbyId($stateParams.id | id).then(function(res){
	 	 $scope.reivews = res;
	 });
	 }

	 $scope.findProductRecomm = function(id)
	 {  
	 	 $scope.recommProductList = [];
	 	productSrv.findProductRecomm($stateParams.id | id).then(function(res){
	 		res.forEach(function(r){
	 			var p = _.object(['id','name','price','discount','image'],_.values(r));
	 			$scope.recommProductList.push(p);
                
	 		})
	 	})
	 };

	 $scope.closeReviewModal = function(form){
         $scope.isAddReview = !$scope.isAddReview;
     }

      $scope.submitReivewForm = function(form){
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
      $scope.addPrifileImage = function(){
      	  files = fileUploadSrv.getCurretnFile();
      	  fileName = fileUploadSrv.getCurretnFileName();
      	  var id = $rootScope.currentUser.id;
      	  if(exist(fileName) && exist(files) && exist(id)){
      	 	 UserSrv.uploadProfileImage(id,fileName,files);
      	   }
      }

	  function closeDialog(){
		  $mdDialog.hide();
	  }
	 
	 $scope.addReview = function(ev){
		var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
        $mdDialog.show({
			   controller: 'authCtrl',
               templateUrl: GLOBAL_APP.reivewDialogTplPath,
               parent: angular.element(document.body),
               targetEvent: ev,
               clickOutsideToClose:true,
               fullscreen: useFullScreen
		});
	 };

	$scope.$watch(
		function() {
      		return $mdMedia('xs') || $mdMedia('sm');},
	    function(wantsFullScreen) {
      		$scope.customFullscreen = (wantsFullScreen === true);
        });
	$scope.getProductById(); 
	$scope.getRatingById();
	$scope.findProductRecomm();
	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};

}])
