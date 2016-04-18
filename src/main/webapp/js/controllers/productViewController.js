app.controller('productViewCtrl',['$scope','$rootScope', '$stateParams', 'productSrv','Msgs','UserSrv','fileUploadSrv',
	function($scope,$rootScope, $stateParams, productSrv,Msgs,UserSrv,fileUploadSrv){
    var fileName = '';
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
	 
	 $scope.getProductById(); 
	 $scope.getReviewById();
	 $scope.findProductRecomm();
	$scope.showAvailabe = function(qwt){if(qwt!=0){return true;}};
	$scope.showOfferPrice = function(o,p){if(o!=p){return true;}};

}])
