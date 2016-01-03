<nav role="navigation" class="navbar navbar-default"> 
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
			<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">Brand</a>
        </div>
        <!-- Collection of nav links and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Messages</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
             <li ng-if="!currentUser"><a ui-sref="user.login" >Login</a></li>
             <li ng-if="currentUser" class="dropdown" >
				 <a href="" class="dropdown-toggle"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">hi!! {{ currentUser.username | lowercase}}<span class="caret"></span></a>
		         <ul class="dropdown-menu">
			        <li>
			           <a href="">Settings</a>
			        </li>
			        <li>
			           <a href="">Orders</a>
			        </li>
			        <li>
			           <a href="/petCart/j_spring_security_logout">Log out</a>
			        </li>
			     </ul>
		    </li>
             <!--  <li ng-click="showModal('login')"><a href="">Login</a></li> -->
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li>
				  <form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
				   </div>
				   <button type="submit" class="btn btn-default">Go</button>
				 </form>
		    </li>
        <li class="dropdown ps-cart-table">
          <a href="" class="dropdown-toggle hidden-xs" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cart <i class="glyphicon glyphicon-shopping-cart"></i> &nbsp;<span class="badge" >{{ count }}</span> <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li ng-if="shoppingCart.items.length < 1"> no Item in you cart</li>
			<li ng-if="shoppingCart.items.length > 0">
			  <table class="table table-responsive">
				  <thead><tr><td></td><td>Name</td><td>Qwt</td><td>Price</td></tr></thead>
				  <tbody>
					<tr ng-repeat="item in shoppingCart.items" >
					<td><img class=" ps-cart-img img-rounded img-responsive" ng-src="{{item.itemId.images[0].file}}"></td>
					<td>{{ item.itemId.name | uppercase }}</td>
				    <td>{{ item.quantity }} </td>   
				    <td>{{ item.itemId.offerPrice | currency:'&#8377;' }}</td>
				  </tr>
				 </tbody>
			  </table>
			</li>
            <li role="separator" class="divider"></li>
            <li ng-if="shoppingCart.items.length > 0">
              <table class="table table-stripped">
				  <thead><tr><td>Total</td><td></td><td></td><td>{{shoppingCart.total | currency:'&#8377;' }}</td></tr></thead>
			  </table> 
            </li>
            <li ng-if="shoppingCart.items.length > 0"><button ui-sref="cart" class="btn btn-success col-sm-12">View Cart</button></li>

            </ul>
        </li>
      </ul>
        </div>
        
    </nav><!-- /nav bar -->
