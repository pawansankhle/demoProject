/**
 *	for use Attachment Directive. define this in your html as
 *  <aps-upload-file layout-gt-sm="row"></aps-upload-file>
 *  
 */

app.directive('uploadFile', apsUploadFile);

  function apsUploadFile() {
    var directive = {
      restrict: 'E',
      templateUrl: 'js/directives/pages/imageUpload.html',
      link: apsUploadFileLink
    };
    return directive;
  }

  function apsUploadFileLink(scope, element, attrs) {
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
      scope.errMsg = '';
      var supportedTypeArray = ['png','jpg','jpeg'];
      if (files[0]) {
        scope.fileName = '';
         for (var i = 0; i < files.length; i++) {
          scope.fileName += files[i].name;
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
  };
