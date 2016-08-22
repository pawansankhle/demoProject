<<<<<<< HEAD
app.controller('productViewCtrl',['$scope','$rootScope', '$stateParams', 'productSrv','Msgs','UserSrv','fileUploadSrv','$mdDialog','GLOBAL_APP','$mdMedia',
	function($scope,$rootScope, $stateParams, productSrv,Msgs,UserSrv,fileUploadSrv,$mdDialog,GLOBAL_APP,$mdMedia){
     $scope.isAddReview = false;
     $scope.reviewForm = '';
     var fileName = '';
=======
app.controller('productViewCtrl',['$scope','$rootScope', '$stateParams', 'productSrv','Msgs','UserSrv','fileUploadSrv',
	function($scope,$rootScope, $stateParams, productSrv,Msgs,UserSrv,fileUploadSrv){
    var fileName = '';
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
     var files = '';
     $scope.errorMsg = false;
     $scope.isAddPrifileImage = false;

	 $scope.getProductById = function(id){
	 	 productSrv.getProductbyId($stateParams.id | id).then(function(res){
	 	$scope.product = res[0];
	 	productSrv.setCurrentProduct(res[0]);
	 });
    }
	 
	 $rootScope.$on('review.RefreshList',
		function(evnt,res){
		if(exist(res.id)){$scope.getReviewById(res.id)}
	});
	
     
	 $scope.getReviewById = function(id){
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
	 
<<<<<<< HEAD
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
=======
	 $scope.getProductById(); 
	 $scope.getReviewById();
	 $scope.findProductRecomm();
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};

}])
