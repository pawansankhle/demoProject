app.directive('productInfo',
              function(){
  return {
    restrict: 'E',
    scope: {
      info: '='
    },
    templateUrl:
    'js/directives/pages/product.html'
  };
});
