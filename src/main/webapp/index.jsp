<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Pet Cart</title>

<!-- Material Design fonts -->
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/angular/bootstrap-material-design.min.css">
<link rel="stylesheet" href="css/angular/ripples.min.css">
<link rel="stylesheet" href="css/bootstrap/font-awesome.min.css">
<link rel="stylesheet" href="css/app/main.css">
<link rel="stylesheet" href="css/app/toastr.min.css">
<link rel="stylesheet" href="css/app/ps-animate.css">

<style type="text/css">
    /*** Hide when Angular is not yet loaded and initialized */

    [ng\:cloak],
    [ng-cloak],
    [data-ng-cloak],
    [x-ng-cloak],
    .ng-cloak,
    .x-ng-cloak {
      display: none !important;
    }
  </style>
  
<script type="text/ng-template" id="error-messages">
    <div ng-message='required'> This Field is Required </div>
    <div ng-message='minlength'>This Field is Too Short </div>
    <div ng-message='maxlength'>This Field is Too Long </div>
    <div ng-message='email'>Please Enter Valid Email</div>
  </script>
</head>
<body ng-app="petCart" ng-controller="ApplicationController as App" ng-cloak>
    <div class="bs-component">
       <jsp:include page="views/navbar/navbar.jsp" />
   </div>
   <div class="container-fluid">
		<!--row for main page -->
		<div class="row">
		    <div class="bs-component">
			 	
					<div class="col-sm-2 hidden-xs" id="ps-menu-col">
				    	<div ng-include="'views/menu/menu.html'" style="margin-top: 22px;"></div>
				    </div>
					<div class="col-sm-10 left-border col-xs-12 ui-view-container">
						<div ui-view></div>
			        </div>
			</div>
		</div>
	</div>
	<div>
		<div ng-include="'views/other/footer.html'"></div>
	</div>
</body>
   


   <!-- Jquery and bootstrap -->
	<script src="js/lib/jquery-1.11.3.min.js"></script>
	
	
	



	<!-- Include the core AngularJS library -->
	<script src="js/lib/angular/angular.min.js"></script>
	<script src="js/lib/angular/angular-animate.min.js"></script>
	<script src="js/lib/ui-bootstrap-tpls-1.3.1.min.js"></script>
	<script src="js/lib/angular/underscore-min.js"></script>
	<script src="js/lib/angular/restangular.min.js"></script>
	<script src="js/lib/angular/angular-resource.min.js"></script>
	<script src="js/lib/angular/ng-infinite-scroll.min.js"></script> 
	<script src="js/lib/angular/angular-sanitize.js"></script>
	
	<script src="js/lib/angular/angular-ui-router.js"></script>
	<script src="js/lib/angular/angular-messages.min.js"></script>

	<script src="js/app/common.js"></script>

	
	<script src="js/lib/angular/material.min.js"></script>
	<script src="js/lib/angular/ripples.min.js"></script>
	<script src="js/lib/angular/dirPagination.js"></script>
    <script src="js/lib/toastr.min.js"></script>

	<!-- Modules -->
	<script src="js/app.js"></script>

	<!-- constants-->
	<script src="js/constants/url.js"></script>
	<script src="js/constants/global-var.js"></script>
	<script src="js/constants/authEvents.js"></script>
	<script src="js/constants/userRoles.js"></script>
	<script src="js/constants/stats.js"></script>
	<script src="js/constants/message.js"></script>

	<!-- Controllers -->
	<script src="js/controllers/productContoller.js"></script>
	<script src="js/controllers/departmentController.js"></script>
	<script src="js/controllers/categoryController.js"></script>
	<script src="js/controllers/menuController.js"></script>
	<script src="js/controllers/authController.js"></script>
	<script src="js/controllers/cartController.js"></script>
	<script src="js/controllers/checkoutController.js"></script>
	<script src="js/controllers/productViewController.js"></script>
	<script src="js/controllers/productbyCategoryController.js"></script>
	<script src="js/controllers/dashboard/dashBoardController.js"></script>
    <script src="js/controllers/dashboard/userManagementController.js"></script>
    <script src="js/controllers/dashboard/ordersController.js"></script>
    <script src="js/controllers/accountController.js"></script>
   <script src="js/controllers/dashboard/productController.js"></script>
   <script src="js/controllers/dashboard/orderViewController.js"></script>
   <script src="js/controllers/dashboard/productDetailController.js"></script>
   <script src="js/controllers/CorouselController.js"></script>
   <script src="js/controllers/addReviewController.js"></script>
    

	<!-- Services -->
	<script src="js/services/userService.js"></script>
	<script src="js/services/productService.js"></script>
	<script src="js/services/departmentService.js"></script>
	<script src="js/services/categoryService.js"></script>
	<script src="js/services/searchService.js"></script>
	<script src="js/services/sessionService.js"></script>
	<script src="js/services/requestInterceptor.js"></script>
	<script src="js/services/AuthService.js"></script>
	<script src="js/services/CartService.js"></script>
	<script src="js/services/MenuService.js"></script>
	<script src="js/services/OrdersService.js"></script>
	<script src="js/services/fileUploadService.js"></script>
         
	<!-- Directives -->
	<script src="js/directives/js/product.js"></script>
	<script src="js/directives/js/formAutofillFix.js"></script>
	<script src="js/directives/js/authDialog.js"></script>
	<script src="js/directives/js/infiniteScroll.js"></script>
	<script src="js/directives/js/imageUpload.js"></script>

	<script>
 $(document).ready(function(){
	 $.material.init();
   });

</script>


</html>
