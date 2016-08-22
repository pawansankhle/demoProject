app.service('fileUploadSrv',['$rootScope','$q',function($rootScope,$q){
    
    var self = this;
    
    self.setCurretnFile = function(files){
      self.files = files;

    }
    self.getCurretnFile = function(){
      return self.files;
    }
    self.setCurretnFileName = function(filesName){
      self.filesName = filesName;

    }
    self.getCurretnFileName = function(){
      return self.filesName;
    }
    self.uploadFile = function(files,url){
        $rootScope.ready = false;
        var fd = new FormData()
        for (var i in files) {
             fd.append("filedata",files[i])
        }
      var xhr = new XMLHttpRequest()
      xhr.upload.addEventListener("progress", self.uploadProgress, false)
      xhr.addEventListener("load", self.uploadComplete, false)
      xhr.addEventListener("error", self.uploadFailed, false)
      xhr.addEventListener("abort", self.uploadCanceled, false)
      xhr.open("POST", url)
      //$scope.progressVisible = true
        xhr.send(fd);
       

    }   
    self.uploadProgress = function(evt) {
      if (evt.lengthComputable) {
         // $scope.progress = Math.round(evt.loaded * 100 / evt.total)
          //console.log(Math.round(evt.loaded * 100 / evt.total));
        } else {
          //$scope.progress = 'unable to compute'
        }
      
    }

    self.uploadComplete = function(evt) {
      if(evt.currentTarget.status == 200){
           var msg = "file uploaded succesfully..";
           toastr.success(msg,"File Upload");
           $rootScope.ready = true;
      }
      
     
    }

    self.uploadFailed = function (evt) {
      var msg = "There was an error attempting to upload the file.";
      toastr.error(msg,"File Upload");
      


    }

    self.uploadCanceled = function(evt) {
      //$scope.progressVisible = false
      var msg = "The upload has been canceled by the user or the browser dropped the connection."
      toastr.info(msg,"File Upload");
      

    }


}]);


