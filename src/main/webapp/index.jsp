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
	<div class="bs-component">
		<modal title="" visible="toggleModal">
		<div class="row">
			<div class="col-sm-12" ng-show="showlogin">
				<form class="bs-component"  name="loginForm"  ng-init="disable=false" style="padding: 1px 70px 0px 70px;"
					 ng-controller="authCtrl"
					ng-submit="login(credentials);disable=!disable;" novalidate>
					<div class="bs-component">
						<div class="alert alert-danger alert-dismissible" ng-show="errorDialog">{{message }}</div>
					</div>
					<div class="form-group label-floating is-empty">
						<label for="username" class="control-label">User Name</label> 
						<input autocomplete="off" id="username"
							class="form-control" ng-model="credentials.username" />
					</div>
					<div class="form-group label-floating is-empty">
						<label for="password" class="control-label">Password</label> 
						<input class="form-control" name="password"
							type="password" autocomplete="off" ng-required="true" id="password"
							ng-model="credentials.password" />
							<div ng-messages="loginForm.password.$error" ng-if="loginForm.password.$touched">
                            <div ng-message="required" class="text-danger">password is required.</div>
                        </div>
					</div>
					<input type="submit" ng-disabled="disable || loginForm.$invalid" class="btn btn-success  btn-raised col-sm-12"
						value="Login" >
					<div class="form-group">
						<button class="btn btn-link col-sm-6">Forgot Password..??</button>
						<span class="pull-right badge ps-badge-uf"
							ng-click="authModal('signup');" >signUp</span>
					</div>
				</form>
			</div>
			<div class="col-sm-12" ng-show="showsignup">
				<form class="bs-component"  style="padding: 1px 70px 0px 70px;" ng-init="disable=false"
					ng-controller="authCtrl" name="signupForm"
					ng-submit="signUp(signUpForm)">
					<div class="form-group">
						<div class="alert alert-danger" ng-show="errorDialog">{{ message }}</div>
					</div>
					<div class="form-group label-floating is-empty">
						<label  class="control-label">User Name</label>
						 <input type="text" name="username" ng-required="true" class="form-control" ng-model="signUpForm.username"
							>
						<div ng-messages="signupForm.username.$error" ng-if="signUpForm.username.$touched"></div>
					</div>
					<div class="form-group  label-floating is-empty">
						<label  class="control-label">Email address</label> 
						<input type="email" class="form-control" ng-required="true" ng-model="signUpForm.email" maxlength="30">
					</div>
					<div class="form-group label-floating is-empty">
						<label for="password" class="control-label">Password</label> 
						<input type="password" name="password"
							class="form-control" minlength="6" ng-required="true" ng-model="signUpForm.password" required
							>
					    <div ng-messages="signupForm.password.$error" ng-if="signupForm.password.$touched">
			                <div ng-message="minlength">
			                  <font color="red" >minimum length 6 required</font>
			                </div>
			            </div>
					</div>
					<div class="form-group label-floating is-empty">
						<label for="userrepassword"class="control-label">Re Password</label> 
						<input type="password"  minlength="6"  name="repassword" class="form-control" ng-required="true" ng-model="repassword" required
							>
                        <div ng-messages="signupForm.repassword.$error" ng-if="signupForm.repassword.$touched">
			                <div ng-message="minlength"><font color="red" >minimum length 6 required</font></div>
			            </div>
					</div>
					<div class="form-group">
						<input type="submit" ng-disabled="disable || signupForm.$invalid" class="btn btn-success btn-raised col-sm-12"
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
	<script src="js/lib/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	


	<!-- Include the core AngularJS library -->
	<script src="js/lib/angular/angular.min.js"></script>
	<script src="js/lib/angular/underscore-min.js"></script>
	<script src="js/lib/angular/restangular.min.js"></script>
	<script src="js/lib/angular/angular-resource.min.js"></script>
	<script src="js/lib/angular/ng-infinite-scroll.min.js"></script> 
	<script src="js/lib/angular/angular-sanitize.js"></script>
	<script src="js/lib/angular/angular-animate.min.js"></script>
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
</body>
<script>
 $(document).ready(function(){
	 $.material.init();
   });

</script>
</html>
