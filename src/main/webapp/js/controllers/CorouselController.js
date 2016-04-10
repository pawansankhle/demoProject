app.controller('corouselCtrl',['$scope',function($scope){
	$scope.myInterval = 3000;
    $scope.noWrapSlides = false;
    $scope.active = 0;
    var slides = $scope.slides = [];
    
    slides.push({image:"images/amazon.jpg",id:"1"});
    slides.push({image:"images/amazon.jpg",id:"2"});
    slides.push({image:"images/amazon.jpg",id:"3"});
    slides.push({image:"images/amazon.jpg",id:"4"});
    

    
    
}]);