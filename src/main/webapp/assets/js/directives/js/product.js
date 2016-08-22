app.directive('productInfo',
              function(){
  return {
    restrict: 'E',
    scope: {
      product: '='
    },
    templateUrl: 'assets/js/directives/pages/product.html'
  };
});
