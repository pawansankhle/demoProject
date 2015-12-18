<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Pet Cart</title>



<link rel="stylesheet" href="../petCart/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="../petCart/css/bootstrap/bootstrap-theme.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../petCart/css/app/main.css">
<link rel="stylesheet" href="../petCart/css/app/ps-animate.css">
<script src="../petCart/js/app/global-var.js"></script>
<script>
        context='<%=request.getContextPath()%>';
</script>
<script src="../petCart/js/lib/jquery-1.11.3.min.js"></script>
<script src="../petCart/js/bootstrap/bootstrap.min.js" ></script>
<script src="../petCart/js/app/common.js"></script>
<script src="../petCart/js/app/url.js"></script>
<script src="../petCart/js/app/dept.js"></script>



<!-- Include the core AngularJS library -->
    <script src="../petCart/js/lib/angular/angular.min.js"></script>
    <script src="../petCart/js/lib/angular/angular-resource.min.js"></script>
    <script src="../petCart/js/lib/angular/angular-sanitize.js"></script>
     <script src="../petCart/js/lib/angular/angular-animate.js"></script>
    <script src="../petCart/js/lib/angular/angular-ui-router.js"></script>
</head> 

<body ng-app="DemoApp">
    
    <div ng-include="'views/navbar/navbar.html'"></div>
	<div class="container-fluid">
		<!--row for main page -->
		<div class="row" >
			<div class="col-sm-2 hidden-xs right-border" id="ps-menu-col" style="">
			 	<div ng-include="'views/menu/menu.html'" style="margin-top: 22px;"></div>
	        </div>

		    <div class="col-sm-10 col-xs-12 span12 ui-view-container" >
		         <div ui-view></div>
		    </div>
		 </div>
    </div>
	 <div>
	 	<div ng-include="'views/other/footer.html'"></div>
	 </div>
    
  <script>
	  $(document).ready(function(){
		  //loadNavBarData();
	  });
	  
  </script>
   
   <!-- notifier -->
    <script src="../petCart/js/lib/Notifier.js"></script>
    
    <!-- Modules -->
    <script src="js/app.js"></script>

    <!-- Controllers -->
    <script src="js/controllers/productContoller.js"></script>
    <script src="js/controllers/departmentController.js"></script>
    <script src="js/controllers/categoryController.js"></script>
    <script src="js/controllers/menuController.js"></script>
    <script src="js/controllers/userController.js"></script>

    <!-- Services -->
    <script src="js/services/productService.js"></script>
    <script src="js/services/departmentService.js"></script>
    <script src="js/services/categoryService.js"></script>
    <script src="js/services/menuService.js"></script>
    <script src="js/services/userService.js"></script>
    
    <!-- Directives -->
    <script src="js/directives/product.js"></script>
    
  
  
 </body>
</html>                                		
