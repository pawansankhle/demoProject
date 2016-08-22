app.directive('modal', function (AUTH_EVENTS) {
    return {
      templateUrl:'assets/js/directives/pages/authDialog.html' ,
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope,element, attrs) {
		 scope.title = attrs.title;
		 scope.$watch(attrs.visible, function(value){
<<<<<<< HEAD
          // if(value == true)
          //   //$(element).modal('show');
          // else
          //   $(element).modal('hide');
=======
          /*if(value == true)
            //$(element).modal('show');
          else
           // $(element).modal('hide');*/
>>>>>>> 369ab10f4634de05ad9a1e0e5ee0f200159d98c4
        });
        
        scope.$on(AUTH_EVENTS.loginSuccess,function(evt){
			   //$(element).modal('hide');
        });
        scope.$on(AUTH_EVENTS.signUpSuccess,function(evt){
			   //$(element).modal('hide');
        });
        
        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });
