app.directive('uploadFile',function apsUploadFile(fileUploadSrv) {
    return  {
      restrict: 'E',
      templateUrl: 'assets/js/directives/pages/imageUpload.html',
      link: function apsUploadFileLink(scope, element, attrs) {
    var input = $('#fileInput');
    var button = $('#uploadButton');
    var textInput = $('#textInput');
    
    if (input.length && button.length && textInput.length) {
      button.click(function(e) {
        input.click();
      });
      textInput.click(function(e) {
        input.click();
      });
    }
    
    
    input.on('change', function(e) {
      var files = e.target.files;
      scope.files = files;
      fileUploadSrv.setCurretnFile(files);

      scope.errMsg = '';
      var supportedTypeArray = ['png','jpg','jpeg'];
      if (files[0]) {
        scope.fileName = '';
         for (var i = 0; i < files.length; i++) {
          scope.fileName += files[i].name;
          fileUploadSrv.setCurretnFileName(scope.fileName);
          var elementSize =(files[i].size/1024/1024);
          if (i != (files.length - 1)) scope.fileName += ', ';
          var ext = scope.fileName.split('.').pop().toLowerCase();
          var isSupported = _.contains(supportedTypeArray,ext);
          if(!isSupported){
              scope.errMsg = "only png, jpg, jpeg supported";
          }else if(elementSize>10) {
                scope.fileName = '';
                scope.errMsg = "File Size Can not be More than 10MB";
              } else if(scope.fileName.length>50){
                scope.fileName = '';
                scope.errMsg = "File Name is Too Long";
          }
       
        }
      } else {
        scope.fileName = null;
        scope.errMsg = '';
      }
      scope.$apply();
    });
  }
    };
    
  });

  

  
