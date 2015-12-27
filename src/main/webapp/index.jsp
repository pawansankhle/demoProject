<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Pet Cart</title>
<script>
		var context='<%=request.getContextPath()%>';
</script>
<link rel="stylesheet" href="../petCart/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="../petCart/css/bootstrap/bootstrap-theme.min.css">
<link href="../petCart/css/bootstrap/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../petCart/css/app/main.css">
<link rel="stylesheet" href="../petCart/css/app/ps-animate.css">

</head> 

<body ng-app="DemoApp" ng-controller="ApplicationController">
    
    <div ng-include="'views/navbar/navbar.html'"></div>
    <div class="container-fluid">
		<!--row for main page -->
		<div class="row" >
		   <div ng-switch on="currentUser.role">
                 <div ng-switch-when="userRoles.admin">You're admin.</div>
                 <div ng-switch-when="userRoles.editor">You're editor.</div>
                 <div ng-switch-default>
					 <div class="col-sm-2 hidden-xs right-border" id="ps-menu-col" style="">
			          <div ng-include="'views/menu/menu.html'" style="margin-top: 22px;"></div>
                    </div>
	                <div class="col-sm-10 col-xs-12 span12 ui-view-container" >
						<div ui-view></div>
				    </div>
                 </div>
              </div>	
	   </div>
    </div>
	 <div>
	 	<div ng-include="'views/other/footer.html'"></div>
	 </div>
    
  
    
	
	



<!-- Include the core AngularJS library -->
    <script src="../petCart/js/lib/angular/angular.min.js"></script>
    <script src="../petCart/js/lib/angular/angular-resource.min.js"></script>
    <script src="../petCart/js/lib/angular/angular-sanitize.js"></script>
     <script src="../petCart/js/lib/angular/angular-animate.js"></script>
    <script src="../petCart/js/lib/angular/angular-ui-router.js"></script>
    
<!-- Jqouery and bootstrap -->
	<script src="../petCart/js/lib/jquery-1.11.3.min.js"></script>
	<script src="../petCart/js/bootstrap/bootstrap.min.js" ></script>
	
<!-- pagination module -->
    <script src="../petCart/js/lib/angular/dirPagination.js"></script>
    
    
<!-- notifier -->
    <script src="../petCart/js/lib/Notifier.js"></script>
    
<!-- Modules -->
    <script src="js/app.js"></script>
    
<!-- constants-->
    <script src="../petCart/js/constants/url.js"></script>
    <script src="../petCart/js/constants/global-var.js"></script>
	<script src="../petCart/js/constants/authEvents.js"></script>
    <script src="../petCart/js/constants/userRoles.js"></script>
    <script src="../petCart/js/constants/stats.js"></script>
    
<!-- Controllers -->
    <script src="js/controllers/productContoller.js"></script>
    <script src="js/controllers/departmentController.js"></script>
    <script src="js/controllers/categoryController.js"></script>
    <script src="js/controllers/menuController.js"></script>
    <script src="js/controllers/loginController.js"></script>
    <script src="js/controllers/cartController.js"></script>

<!-- Services -->
    <script src="js/services/productService.js"></script>
    <script src="js/services/departmentService.js"></script>
    <script src="js/services/categoryService.js"></script>
    <script src="js/services/menuService.js"></script>
    <script src="js/services/sessionService.js"></script>
    <script src="js/services/requestInterceptor.js"></script>
    <script src="js/services/AuthService.js"></script>
    <script src="js/services/CartService.js"></script>
    
<!-- Directives -->
    <script src="js/directives/js/product.js"></script>
    <script src="js/directives/js/formAutofillFix.js"></script>
    <script src="js/directives/js/loginDialog.js"></script>
  </body>
</html>                                		
