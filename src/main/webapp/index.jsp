<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Pet Cart</title>

<!-- Material Design fonts -->
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


<link rel="stylesheet" href="http://localhost/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="http://localhost/css/angular/bootstrap-material-design.min.css">
<link rel="stylesheet" href="http://localhost/css/angular/ripples.min.css">
<link rel="stylesheet" href="http://localhost/css/bootstrap/font-awesome.min.css">
<link rel="stylesheet" href="http://localhost/css/app/main.css">
<link rel="stylesheet" href="http://localhost/css/app/toastr.min.css">
<link rel="stylesheet" href="http://localhost/css/app/ps-animate.css">

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
	<div class="bs-component">
		<modal title="form" visible="toggleModal">
		<div class="row">
			<div class="col-sm-12" ng-show="showlogin">
				<form class="bs-component" role="loginForm" class="form-horizontal" style="padding: 1px 70px 0px 70px;"
					name="loginForm" ng-controller="authCtrl"
					ng-submit="login(credentials)" novalidate>
					<div class="bs-component">
						<div class="alert alert-danger alert-dismissible" ng-show="errorDialog">{{
							message }}</div>
					</div>
					<div class="form-group label-floating is-empty">
						<label for="username" class="control-label">User Name</label> 
						<input id="username"
							class="form-control" ng-model="credentials.username" />
					</div>
					<div class="form-group label-floating is-empty">
						<label for="password" class="control-label">Password</label> 
						<input class="form-control"
							type="password"  required id="password"
							ng-model="credentials.password" />
					</div>
					<input type="submit" class="btn btn-success  btn-raised col-sm-12"
						value="Login">
					<div class="form-group">
						<button class="btn btn-link col-sm-6">Forgot Password..??</button>
						<span class="pull-right badge ps-badge-uf"
							ng-click="authModal('signup')">signUp</span>
					</div>
				</form>
			</div>
			<div class="col-sm-12" ng-show="showsignup">
				<form class="form-control" class="form-horizontal" role="form" style="padding: 1px 70px 0px 70px;"
					ng-controller="authCtrl" name="signUpForm"
					ng-submit="signUp(signUpForm)">
					<div class="form-group">
						<div class="alert alert-danger" ng-show="errorDialog">{{ message }}</div>
					</div>
					<div class="form-group label-floating is-empty">
						<label  class="control-label">User Name</label> <input type="text"
							class="form-control" ng-model="signUpForm.username"
							>
					</div>
					<div class="form-group  label-floating is-empty">
						<label  class="control-label">Email address</label> <input type="email"
							class="form-control" ng-model="signUpForm.email"
							>
					</div>
					<div class="form-group label-floating is-empty">
						<label for="password" class="control-label">Password</label> <input type="password"
							class="form-control" ng-model="signUpForm.password" required
							>
					</div>
					<div class="form-group label-floating is-empty">
						<label for="userrepassword" class="control-label">Re Password</label> <input
							type="password" class="form-control" ng-model="repassword" required
							>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-success btn-raised col-sm-12"
							value="SignUp">
					</div>
					
				</form>
			</div>
		</div>
		</modal>
	</div>
	<div>
		<div ng-include="'views/other/footer.html'"></div>
	</div>





   <!-- Jquery and bootstrap -->
	<script src="http://localhost/js/lib/jquery-1.11.3.min.js"></script>
	<script src="http://localhost/js/bootstrap/bootstrap.min.js"></script>
	


	<!-- Include the core AngularJS library -->
	<script src="http://localhost/js/lib/angular/angular.min.js"></script>
	<script src="http://localhost/js/lib/angular/underscore-min.js"></script>
	<script src="http://localhost/js/lib/angular/restangular.min.js"></script>
	<script src="http://localhost/js/lib/angular/angular-resource.min.js"></script>
	<script src="http://localhost/js/lib/angular/angular-sanitize.js"></script>
	<script src="http://localhost/js/lib/angular/angular-animate.js"></script>
	<script src="http://localhost/js/lib/angular/angular-ui-router.js"></script>
	
	<script src="js/app/common.js"></script>

	<script src="http://localhost/js/lib/angular/material.min.js"></script>
	<script src="http://localhost/js/lib/angular/ripples.min.js"></script>
	<script src="http://localhost/js/lib/angular/dirPagination.js"></script>
    <script src="http://localhost/js/lib/toastr.min.js"></script>

	<!-- Modules -->
	<script src="js/app.js"></script>

	<!-- constants-->
	<script src="http://localhost/js/constants/url.js"></script>
	<script src="http://localhost/js/constants/global-var.js"></script>
	<script src="http://localhost/js/constants/authEvents.js"></script>
	<script src="http://localhost/js/constants/userRoles.js"></script>
	<script src="http://localhost/js/constants/stats.js"></script>
	<script src="http://localhost/js/constants/message.js"></script>

	<!-- Controllers -->
	<script src="http://localhost/js/controllers/productContoller.js"></script>
	<script src="http://localhost/js/controllers/departmentController.js"></script>
	<script src="http://localhost/js/controllers/categoryController.js"></script>
	<script src="http://localhost/js/controllers/menuController.js"></script>
	<script src="http://localhost/js/controllers/authController.js"></script>
	<script src="http://localhost/js/controllers/cartController.js"></script>
	<script src="http://localhost/js/controllers/checkoutController.js"></script>
	<script src="http://localhost/js/controllers/dashboard/dashBoardController.js"></script>
    <script src="http://localhost/js/controllers/dashboard/userManagementController.js"></script>
	<!-- Services -->
	<script src="http://localhost/js/services/userService.js"></script>
	<script src="http://localhost/js/services/productService.js"></script>
	<script src="http://localhost/js/services/departmentService.js"></script>
	<script src="http://localhost/js/services/categoryService.js"></script>
	<script src="http://localhost/js/services/searchService.js"></script>
	<script src="http://localhost/js/services/sessionService.js"></script>
	<script src="http://localhost/js/services/requestInterceptor.js"></script>
	<script src="http://localhost/js/services/AuthService.js"></script>
	<script src="http://localhost/js/services/CartService.js"></script>
	<script src="http://localhost/js/services/MenuService.js"></script>
	<script src="http://localhost/js/services/OrdersService.js"></script>
	
         
	<!-- Directives -->
	<script src="http://localhost/js/directives/js/product.js"></script>
	<script src="http://localhost/js/directives/js/formAutofillFix.js"></script>
	<script src="http://localhost/js/directives/js/authDialog.js"></script>
</body>
<script>
 $(document).ready(function(){
	 $.material.init();
   });

</script>
</html>
