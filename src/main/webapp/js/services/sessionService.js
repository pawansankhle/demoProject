function Cart() {
  return {
    'id': '',
    'items': [],
    'total': ''
  };
}

app.service('SessionSrv', function () {
  this.create = function (sessionId, userId, userRole) {
    this.id = sessionId;
    this.userId = userId;
    this.userRole = userRole;
  };
  this.destroy = function () {
    this.id = null;
    this.userId = null;
    this.userRole = null;
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
