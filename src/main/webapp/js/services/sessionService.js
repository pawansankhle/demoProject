function Cart() {
  return {
    'id': '',
    'items': [],
    'total': ''
  };
}


app.service('SessionSrv', function () {
  this.create = function (sessionId, userName, userRole) {
    this.id = sessionId;
    this.username = userName;
    this.userrole = userRole;
    
  };
  this.destroy = function () {
    this.id = null;
    this.usernme = null;
    this.userrole = null;
  };
  
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
