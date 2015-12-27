app.directive('productInfo',
              function(){
  return {
    restrict: 'E',
    scope: {
      product: '='
    },
    templateUrl: 'js/directives/pages/product.html'
  };
});
