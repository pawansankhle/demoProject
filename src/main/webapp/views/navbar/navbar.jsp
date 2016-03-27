<div class="navbar  navbar-inverse">
	   <div class="container-fluid"> 
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a ui-sref="dashboard" ng-if="isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier)" class="navbar-brand">Brand</a>
				<a href="#" ng-if="!(isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier))" class="navbar-brand">Brand</a>
			</div>
			<!-- Collection of nav links and other content for toggling -->
			<div id="navbarCollapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav" ng-if="!(isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier))">
					<li class="active hidden-xs"><a href="#">Home</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right hidden-xs">
				 <li ng-if="!currentUser"><a href="" ng-click="authModal('login')">Login</a></li>
				 <li ng-if="isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier)">
				 	<a href="" class="dropdown-toggle"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				 	<i class="fa fa-bell"><span class="badge">2</span></i></a>
				 	<ul class="dropdown-menu">
				 	   <li>data for  dropdown</li>
				 	</ul>
				 </li>
				 <li ng-if="(isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier))">
				 	<a ng-click=""><i class="fa fa-face"></i></a>
				 </li>
				<li ng-if="(isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier))" ng-controller="authCtrl"><a ng-click="logout()">Log out</a>
				 </li>


				 
				 <li ng-if="isAuthorized(userRoles.user)" class="dropdown" >
					 <a href="" class="dropdown-toggle"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">hi!! {{ currentUser.username | lowercase}}<span class="caret"></span></a>
					 <ul class="dropdown-menu">
						<li>
						   <a ui-sref="account.setting">Settings</a>
						</li>
						<li>
						   <a ui-sref="account.orders">Orders</a>
						</li>
						<li ng-controller="authCtrl">
						   <a ng-click="logout()">Log out</a>
						</li>
					 </ul>
				</li>
				  <!--<li ng-click="showModal('login')"><a href="">Login</a></li> -->
				</ul>
				<ul class="nav navbar-nav navbar-right hidden-xs" ng-if="!(isAuthorized(userRoles.admin) || isAuthorized(userRoles.supplier))">
				  <li style="height: 1px;">
					  <form class="navbar-form navbar-left" role="search">
						<input type="text" class="form-control" placeholder="Search">
					    <button type="submit" class="btn btn-fab btn-fab-mini" style="margin-top: -5px;font-size: 15px;">Go</button>
					 </form>
				   </li>
			<li class="dropdown">
			  <a href="" class="dropdown-toggle hidden-xs" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cart <i class="glyphicon glyphicon-shopping-cart"></i> &nbsp;<span class="badge" >{{ count }}</span> <span class="caret"></span></a>
				<ul class="dropdown-menu" style="min-width: 320px;">
				  <li ng-if="shoppingCart.items.length < 1"> <h4 style="color: black;" class="text-center">No item in your Cart</h4></li>
				<li ng-if="shoppingCart.items.length > 0">
				  <table class="table table-responsive" style="color: black;">
					  <thead><tr><td></td><td>Name</td><td>Qwt</td><td>Price</td></tr></thead>
					  <tbody>
						<tr ng-repeat="item in shoppingCart.items" >
						<td><img class=" ps-cart-img img-rounded img-responsive" ng-src="{{item.itemId.images[0].file}}"></td>
						<td>{{ item.itemId.name | uppercase }}</td>
						<td>{{ item.quantity }} </td>   
						<td>{{ item.itemId.offerPrice | currency:'&#8377;' }}</td>
					  </tr>
					  <tr><td>Total</td><td></td><td></td><td>{{shoppingCart.total | currency:'&#8377;' }}</td></tr>
                      </tbody>
				  </table>
				</li>
			    <li role="separator" class="divider"></li>
			    <li ng-if="shoppingCart.items.length > 0"><button ui-sref="cart" class="btn btn-success btn-raised col-sm-12">View Cart</button></li>
			</ul>
			</li>
		  </ul>
		  </div>
		</div>
		<!-- <div class="progress" style="margin-bottom: 0px;">
            <div style="width: 50%" class="progress-bar progress-bar-success">
            </div> -->
    </div>
	</div><!-- /nav bar -->
</div>
