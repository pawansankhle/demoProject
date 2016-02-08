function Cart() {
  return {
    'id': '',
    'items': [],
    'total': ''
  };
};

function User() {
	 return {
	'addressLine1': '',
    'addressLine2': '',
    'city': '',
    'deliveryAddress': '',
    'email': '',
    'enabled': false,
    'firstName': '',
    'id': '',
    'image': {},
    'lastName': '',
    'mobile': '',
    'postalCode': '',
    'roles': [],
    'username': ''
   };
  	
};


app.service('SessionSrv', function () {
  /*this.create = function (userid, userName, userRole) {
    this.userid = userid;
    this.username = userName;
    this.userrole = userRole;
    this.deliveryAddress = deliveryAddress;
    
  };
  this.destroy = function () {
    this.userid = null;
    this.username = null;
    this.userrole = null;
  };*/
  
  this.clearUser = function(){
	this.user = null;
  },
  this.saveUser = function(user){
	  this.user = user;
 },
  this.cart = new Cart(),
  this.clearCart = function() {
    this.cart = new Cart();
    this.cart.cartId = 1;
  },
  this.saveCart = function(cart) {
	  this.cart = cart;
  },
  this.updateCart =  function(id,item,qwt,total) {
	 this.cart.items.push({
		 'id': id,
		 'itemId': item,
		 'quantity': qwt
		  });
     this.cart.total = total;
	}
  });
