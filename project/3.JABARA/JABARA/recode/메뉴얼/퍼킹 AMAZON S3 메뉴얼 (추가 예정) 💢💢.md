## 	퍼킹 AMAZON S3 메뉴얼 (추가 예정) 💢💢

1. 간단 예제

   - index.html

   ```
   <!DOCTYPE html>
   <html>
    <head>
    <!-- **DO THIS**: -->
    <!-- Replace SDK_VERSION_NUMBER with the current SDK version number -->
    <script src="https://sdk.amazonaws.com/js/aws-sdk-2.726.0.min.js"></script>
    <script src="./s3_photoExample.js"></script>
    <script>
    function getHtml(template) {
    return template.join('\n');
    }
    listAlbums();
    </script>
    </head>
    <body>
    <h1>My Photo Albums App</h1>
    <div id="app"></div>
    </body>
   </html>
   ```

   - s3_photoExample.js

   ```
   // AWS 설정
   var albumBucketName = "jobara";
   var bucketRegion = "ap-northeast-2";
   var IdentityPoolId = "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48";
   
   AWS.config.update({
       region: bucketRegion,
       credentials: new AWS.CognitoIdentityCredentials({
       IdentityPoolId: IdentityPoolId
       })
   });
   var s3 = new AWS.S3({
       apiVersion: "2006-03-01",
       params: { Bucket: albumBucketName }
   });
   
   // 앨범 리스트 보여주는 기능
   function listAlbums() {
       s3.listObjects({ Delimiter: "/" }, function(err, data) {
       if (err) {
           return alert("There was an error listing your albums: " + err.message);
       } else {
   
           var albums = data.CommonPrefixes.map(function(commonPrefix) {
               var prefix = commonPrefix.Prefix;
               var albumName = decodeURIComponent(prefix.replace("/", ""));
               return getHtml([
                   "<li>",
                   "<span onclick=\"deleteAlbum('" + albumName + "')\">X</span>",
                   "<span onclick=\"viewAlbum('" + albumName + "')\">",
                   albumName,
                   "</span>",
                   "</li>"
               ]);
           });
   
           var message = albums.length
           ? getHtml([
               "<p>앨범을 보려면 이름을 클릭해주세요</p>",
               "<p>X를 클릭 시 삭제할 수 있습니다.</p>"
           ])
           : "<p>앨범이 없네요~ 생성해주세요";
   
           var htmlTemplate = [
               "<h2>Albums</h2>",
               message,
               "<ul>",
               getHtml(albums),
               "</ul>",
               "<button onclick=\"createAlbum(prompt('앨범 이름을 입력해주세요:'))\">",
               "Create New Album",
               "</button>"
           ];
           document.getElementById("app").innerHTML = getHtml(htmlTemplate);
           }
       });
   }
   
   // 앨범 만들기
   function createAlbum(albumName) {
    albumName = albumName.trim();
    if (!albumName) {
    return alert("Album names must contain at least one non-space character.");
    }
    if (albumName.indexOf("/") !== -1) {
    return alert("Album names cannot contain slashes.");
    }
    var albumKey = encodeURIComponent(albumName);
    s3.headObject({ Key: albumKey }, function(err, data) {
    if (!err) {
    return alert("Album already exists.");
    }
    if (err.code !== "NotFound") {
    return alert("There was an error creating your album: " + err.message);
    }
    s3.putObject({ Key: albumKey }, function(err, data) {
    if (err) {
    return alert("There was an error creating your album: " + err.message);
    }
    alert("Successfully created album.");
    viewAlbum(albumName);
    });
    });
   }
   
   //앨범 보기
   function viewAlbum(albumName) {
       var albumPhotosKey = encodeURIComponent(albumName) + "/";
       s3.listObjects({ Prefix: albumPhotosKey }, function(err, data) {
       if (err) {
       return alert("There was an error viewing your album: " + err.message);
       }
       // 'this' references the AWS.Response instance that represents the response
       var href = this.request.httpRequest.endpoint.href;
       var bucketUrl = href + albumBucketName + "/";
       var photos = data.Contents.map(function(photo) {
       var photoKey = photo.Key;
       var photoUrl = bucketUrl + encodeURIComponent(photoKey);
       return getHtml([
       "<span>",
       "<div>",
       '<img style="width:128px;height:128px;" src="' + photoUrl + '"//>',
       "</div>",
       "<div>",
       "<span onclick=\"deletePhoto('" +
       albumName +
       "','" +
       photoKey +
       "')\">",
       "X",
       "</span>",
       "<span>",
       photoKey.replace(albumPhotosKey, ""),
       "</span>",
       "</div>",
       "</span>"
       ]);
       });
       var message = photos.length
       ? "<p>Click on the X to delete the photo</p>"
       : "<p>You do not have any photos in this album. Please add photos.</p>";
       var htmlTemplate = [
       "<h2>",
       "Album: " + albumName,
       "</h2>",
       message,
       "<div>",
       getHtml(photos),
           "</div>",
           '<input id="photoupload" type="file" accept="image/*">',
           '<button id="addphoto" onclick="addPhoto(\'' + albumName + "')\">",
           "Add Photo",
           "</button>",
           '<button onclick="listAlbums()">',
           "Back To Albums",
           "</button>"
       ];
       document.getElementById("app").innerHTML = getHtml(htmlTemplate);
       });
       }
       ///////////////////////////////////////////////////////////
       // 사진 올리는코드 
       function addPhoto(albumName) {
       var files = document.getElementById("photoupload").files;
       if (!files.length) {
           return alert("Please choose a file to upload first.");
        }
       var file = files[0];
       var fileName = file.name;
       console.log(fileName);
       var albumPhotosKey = encodeURIComponent(albumName) + "//";
       var photoKey = albumPhotosKey + fileName;
       // 포토키 = 경로!!!
       console.log(photoKey);
       // Use S3 ManagedUpload class as it supports multipart uploads
       var upload = new AWS.S3.ManagedUpload({
       params: {
        Bucket: albumBucketName,
           Key: photoKey,
           Body: file,
           ContentType : "image/jpeg", //<-- this is what you need!,
           ACL: "public-read"
       }
       });
       var promise = upload.promise();
       promise.then(
       function(data) {
       alert("Successfully uploaded photo.");
       viewAlbum(albumName);
       },
       function(err) {
       return alert("There was an error uploading your photo: ", err.message);
       }
       );
   }
   function deletePhoto(albumName, photoKey) {
       s3.deleteObject({ Key: photoKey }, function(err, data) {
       if (err) {
       return alert("There was an error deleting your photo: ", err.message);
       }
       alert("Successfully deleted photo.");
       viewAlbum(albumName);
       });
   }
   function deleteAlbum(albumName) {
       var albumKey = encodeURIComponent(albumName) + "/";
       s3.listObjects({ Prefix: albumKey }, function(err, data) {
       if (err) {
       return alert("There was an error deleting your album: ", err.message);
       }
       var objects = data.Contents.map(function(object) {
       return { Key: object.Key };
       });
    s3.deleteObjects(
       {
    Delete: { Objects: objects, Quiet: true }
       },
       function(err, data) {
       if (err) {
       return alert("There was an error deleting your album: ", err.message);
       }
       alert("Successfully deleted album.");
       listAlbums();
       }
    );
    });
   }
   
    "<button onclick=\"createAlbum(prompt('Enter Album Name:'))\">",
    "Create New Album",
    "</button>"
    ];
    document.getElementById("app").innerHTML = getHtml(htmlTemplate);
    }
    });
   }
   function createAlbum(albumName) {
    albumName = albumName.trim();
    if (!albumName) {
    return alert("Album names must contain at least one non-space character.");
    }
    if (albumName.indexOf("/") !== -1) {
    return alert("Album names cannot contain slashes.");
    }
    var albumKey = encodeURIComponent(albumName);
    s3.headObject({ Key: albumKey }, function(err, data) {
    if (!err) {
    return alert("Album already exists.");
    }
    if (err.code !== "NotFound") {
    return alert("There was an error creating your album: " + err.message);
    }
    s3.putObject({ Key: albumKey }, function(err, data) {
    if (err) {
    return alert("There was an error creating your album: " + err.message);
    }
    alert("Successfully created album.");
    viewAlbum(albumName);
    });
    });
   }
   function viewAlbum(albumName) {
    var albumPhotosKey = encodeURIComponent(albumName) + "/";
    s3.listObjects({ Prefix: albumPhotosKey }, function(err, data) {
    if (err) {
    return alert("There was an error viewing your album: " + err.message);
    }
    // 'this' references the AWS.Response instance that represents the response
    var href = this.request.httpRequest.endpoint.href;
    var bucketUrl = href + albumBucketName + "/";
    var photos = data.Contents.map(function(photo) {
    var photoKey = photo.Key;
    var photoUrl = bucketUrl + encodeURIComponent(photoKey);
    return getHtml([
    "<span>",
    "<div>",
    '<img style="width:128px;height:128px;" src="' + photoUrl + '"//>',
    "</div>",
    "<div>",
    "<span onclick=\"deletePhoto('" +
    albumName +
    "','" +
    photoKey +
    "')\">",
    "X",
    "</span>",
    "<span>",
    photoKey.replace(albumPhotosKey, ""),
    "</span>",
    "</div>",
    "</span>"
    ]);
    });
    var message = photos.length
    ? "<p>Click on the X to delete the photo</p>"
    : "<p>You do not have any photos in this album. Please add photos.</p>";
    var htmlTemplate = [
    "<h2>",
    "Album: " + albumName,
    "</h2>",
    message,
    "<div>",
    getHtml(photos),
    "</div>",
    '<input id="photoupload" type="file" accept="image/*">',
    '<button id="addphoto" onclick="addPhoto(\'' + albumName + "')\">",
    "Add Photo",
    "</button>",
    '<button onclick="listAlbums()">',
    "Back To Albums",
    "</button>"
    ];
    document.getElementById("app").innerHTML = getHtml(htmlTemplate);
    });
   }
   function addPhoto(albumName) {
    var files = document.getElementById("photoupload").files;
    if (!files.length) {
    return alert("Please choose a file to upload first.");
    }
    var file = files[0];
    var fileName = file.name;
    var albumPhotosKey = encodeURIComponent(albumName) + "/";
    var photoKey = albumPhotosKey + fileName;
    // Use S3 ManagedUpload class as it supports multipart uploads
    var upload = new AWS.S3.ManagedUpload({
    params: {
    Bucket: albumBucketName,
    Key: photoKey,
    Body: file,
    ACL: "public-read"
    }
    });
    var promise = upload.promise();
    promise.then(
    function(data) {
    alert("Successfully uploaded photo.");
    viewAlbum(albumName);
    },
    function(err) {
    return alert("There was an error uploading your photo: ", err.message);
    }
    );
   }
   function deletePhoto(albumName, photoKey) {
    s3.deleteObject({ Key: photoKey }, function(err, data) {
    if (err) {
    return alert("There was an error deleting your photo: ", err.message);
    }
    alert("Successfully deleted photo.");
    viewAlbum(albumName);
    });
   }
   function deleteAlbum(albumName) {
    var albumKey = encodeURIComponent(albumName) + "/";
    s3.listObjects({ Prefix: albumKey }, function(err, data) {
    if (err) {
    return alert("There was an error deleting your album: ", err.message);
    }
    var objects = data.Contents.map(function(object) {
    return { Key: object.Key };
    });
    s3.deleteObjects(
    {
    Delete: { Objects: objects, Quiet: true }
    },
    function(err, data) {
    if (err) {
    return alert("There was an error deleting your album: ", err.message);
    }
    alert("Successfully deleted album.");
    listAlbums();
    }
    );
    });
   }
   
   ```
   
   
   
   

