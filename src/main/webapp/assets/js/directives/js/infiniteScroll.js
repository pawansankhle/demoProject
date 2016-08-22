app.directive('scrolly', function ($window) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
           // var raw = element[0];
        $(window).scroll(function (){
        if($(window).scrollTop() + $(window).height() == $(document).height()) {
                scope.$apply(attrs.scrolly);
         }
        });
        }
    };
});


