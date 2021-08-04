<template>
  <div class="img-modify-wrap">
    <div class="user join wrapC">
      <h2 style="text-align:center;">&#128142;프로필 이미지 수정&#128142;</h2>
      <div class="middle-wrap">
        <h3 style="text-align:center;"><h3 style="display: inline-block; background-color: rgba(255, 240, 109, 0.726);">{{ loginUserId }}님</h3>의 프로필 사진</h3>
        <!-- <p style="text-align:center;">{{ loginUserId }}님 어떤 사진으로 바꾸고 싶으세요??</p> -->
        <div id="fileupload">
          <div v-if="!changeimage">
            <p style="text-align:center; font-weight:700;" ><i class="far fa-hand-point-down" ></i>Clcik<i class="far fa-hand-point-down"></i></p>
            <img
              v-bind:src="
                'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage
              "
              class="postgallery-image"
              alt
              width="200px"
              height="auto"
            />
            <input id="file-selector" ref="file" type="file" @change="onFileChange" />
            <button @click="modifiedDefaultImage" class="basic-button" >기본 이미지로수정</button>

          </div>

          <div v-else>
            <img :src="changeimage" class="postgallery-image" alt width="200px" height="auto" />
            <div class="two-btn">
              <button @click="removeImage" class="remove-btn">선택한 사진 삭제</button>
              <button @click="modifiedImage" class="modify-btn">선택한 사진으로 수정</button>
              <button @click="modifiedDefaultImage" class="basic-button" >기본 이미지로수정</button>
            </div>
          </div>
        </div>

        <!--  AWS  -->
        <!-- <div v-for="(file, index) in fileList" :key="file.Key">
          #{{ index + 1 }} {{ file.Key }}
          <button @click="deleteFile(file.Key)">삭제</button>
        </div>

        <button @click="upload">업로드</button>-->
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from "../../apis/userAPI";
import AWS from "aws-sdk";
export default {
  name: "ModifiedImage",
  data() {
    return {
      loginUserId: "",
      // AWS
      file: null,
      fileList: [],
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48",
      profileimage: "",
      changeimage: ""
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.profileimage = this.$session.get("loginUserProfileImage");
  },
  methods: {
    // AWS
    onFileChange() {
      //파일 입력
      this.file = this.$refs.file.files[0];
      console.log(this.file, "파일이 업로드 되었습니다");

      let reader = new FileReader();
      reader.readAsDataURL(this.file);
      reader.onload = e => {
        console.log(e.target.result);
      };
      // console.log(files);
      this.createImage(this.file);
    },
    createImage(file) {
      //이미지 미리 보여주기
      // var image = new Image();
      var reader = new FileReader();

      reader.onload = e => {
        this.changeimage = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function() {
      //이미지 선택 취소
      this.changeimage = "";
    },
    modifiedImage() {
      //수정 버튼 클릭시
      if (this.profileimage != "jobaraprofile//user.png") {
        console.log("기본이 아님");
        this.deleteFile(this.profileimage);
        console.log("삭제완료");
      }
      this.upload();
      console.log("업로드완료");
    },
    upload() {
      //aws접속하기 위해 이런 것들이 필요
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { Bucket: this.albumBucketName }
      });
      // let photoKey = "jobaraprofile" + "//" + this.file.name; // 기존 파일의 이름을 photoKey로 지정
      let photoKey = "jobaraprofile" + "//" + this.loginUserId;
      console.log(photoKey);
      s3.upload(
        {
          Bucket: this.albumBucketName,
          Key: photoKey,
          Body: this.file,
          ContentType: "image/jpeg", //<-- this is what you need!,
          ACL: "public-read"
        },
        (err, data) => {
          if (err) {
            console.log(err);

            return alert(
              "There was an error uploading your photo: ",
              err.message
            );
          }
          console.log(data);
          this.$session.set("loginUserProfileImage", photoKey);
          this.profileimage = this.$session.get("loginUserProfileImage");
          this.updateDB();
          this.movePage();
          // this.alert("Successfully uploaded photo.");
        }
      );
    },
    updateDB() {
      let data = {
        userId: this.loginUserId,
        userimagePath: this.profileimage
      };
      UserApi.updateAWSUserImage(
        data,
        res => {
          console.log("수정완료");
          console.log(res);
        },
        error => {
          console.log(error);
        }
      );
    },

    getFiles() {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { Bucket: this.albumBucketName }
      });
      s3.listObjects(
        {
          Delimiter: "/jobaraprofile/"
        },
        (err, data) => {
          if (err) {
            return alert(
              "There was an error listing your albums: " + err.message
            );
          } else {
            this.fileList = data.Contents;
            console.log(data);
          }
        }
      );
    },

    deleteFile(key) {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { Bucket: this.albumBucketName }
      });
      console.log(key);
      s3.deleteObject({ Key: key }, (err, data) => {
        if (err) {
          return alert("There was an error deleting your photo: ", err.message);
        }
        console.log("삭제");
        console.log(key);
        alert("Successfully deleted photo.");
        this.getFiles();
        console.log(data);
      });
    },
    modifiedDefaultImage() {
      //기본이미지로 수정
      if (this.profileimage != "jobaraprofile//user.png") {
        console.log("기본이 아님");
        this.deleteFile(this.profileimage);
        console.log("삭제완료");
      }
      this.$session.set("loginUserProfileImage", "jobaraprofile//user.png");
      this.profileimage = this.$session.get("loginUserProfileImage");
      this.updateDB();
      this.movePage();
    },
    movePage() {
      this.$router.push("/user/usermodified");
    }
  }
};
</script>
<style scoped>

.wrapC {
  width: 100%;
  height: 500px;
  position: relative;
  margin: 10% auto;
}
.img-modify-wrap {
  position:absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* margin: 0 auto; */
}
.middle-wrap {
  position: relative;
  margin:0 auto;

}
.two-btn {
  width: 500px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
#fileupload {
  text-align: center;
}
.basic-button {
	-webkit-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-moz-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-ms-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-o-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	display: block;
	margin: 20px auto;
	max-width: 270px;
	text-decoration: none;
	border-radius: 5px;
	/* padding: 0 1.3rem; */
}

.basic-button:hover {
  color: rgba(255, 255, 255, 0.932);
	box-shadow: rgba(255, 131, 127, 0.726) 0 0px 0px 40px inset;
}

.basic-button {
  color:rgba(255, 131, 127, 0.726);
	box-shadow: rgba(255, 186, 207, 0.863) 0 0px 0px 0px inset;
  font-weight: 700;
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 10px;
  cursor: pointer;
  border:2px solid rgba(255, 131, 127, 0.726);
  margin-bottom: 50px;
}
#file-selector {
  background: rgba(230, 230, 230, 0.575);
  border: 0;
  border-radius: 5px;
  padding-top: 10px;
  padding-left: 30%;
}
.remove-btn {
	-webkit-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-moz-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-ms-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-o-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	display: block;
	margin: 20px auto;
	max-width: 270px;
	text-decoration: none;
	border-radius: 5px;
	/* padding: 0 1.3rem; */
}

.remove-btn:hover {
  color: rgba(255, 255, 255, 0.932);
	box-shadow: rgba(221, 7, 71, 0.726) 0 0px 0px 40px inset;
}

.remove-btn {
  color:rgba(221, 7, 71, 0.726);
	box-shadow: rgba(255, 124, 164, 0.863) 0 0px 0px 0px inset;
  font-weight: 700;
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 10px;
  cursor: pointer;
  border:2px solid rgba(221, 7, 71, 0.726);
  margin-bottom: 50px;
}
.modify-btn {
	-webkit-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-moz-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-ms-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-o-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	display: block;
	margin: 20px auto;
	max-width: 270px;
	text-decoration: none;
	border-radius: 5px;
	/* padding: 0 1.3rem; */
}

.modify-btn:hover {
  color: rgba(255, 255, 255, 0.932);
	box-shadow: rgb(255, 217, 0) 0 0px 0px 40px inset;
}

.modify-btn {
  color:rgb(255, 217, 0);
	box-shadow: rgba(255, 248, 186, 0.774) 0 0px 0px 0px inset;
  font-weight: 700;
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 10px;
  cursor: pointer;
  border:2px solid rgb(255, 217, 0);
  margin-bottom: 50px;
}
</style>  
