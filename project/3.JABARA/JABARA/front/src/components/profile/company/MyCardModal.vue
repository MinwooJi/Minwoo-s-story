<template>
  <div class="companyCardModal">
    <div class="form-box">
      <div class="two-button">
        <button class="e-cancle-button no-css-btn" @click="cancleWrite">
          <i style="color:black;" class="fas fa-times fa-2x"></i>
        </button>
        <!-- <button class="e-submit-button no-css-btn" @click="submitCinfo">
          <i style="color:black;" class="far fa-save fa-2x"></i>
        </button> -->
      </div>
      <div class="form-title">
        <h3>지원한 회사</h3>
      </div>
      <div class="middle-wrap">
        <div class="c-input input-company">
          <label for="companyName">회사명</label>
          <input v-model="dataList.COMPANY_NAME" type="text" id="companyName" readonly="true"/>
        </div>
        <div class="c-input input-job">
          <label for="companyUrl">기업 URL</label>
          <input v-model="dataList.COMPANY_URL" type="text" id="companyUrl" />
        </div>
        <div class="c-input input-area">
          <label for="companyImage" class="image-title">공고 이미지</label>
          <img :src="dataList.COMPANY_IMAGE" alt="공고 이미지" width="70%" height="auto"/>
        </div>
      </div>
      <!-- <div class="c-button">
        <button class="c-user-btn c-submit-btn no-css-btn" @click="submitCinfo">등록하기</button>
        <button class="c-user-btn c-cancle-btn no-css-btn" @click="cancleWrite">
          <i style="color:white; margin: 10px 5px;" class="fas fa-times"></i>취소하기
        </button>
      </div> -->
    </div>
  </div>
</template>

<script>
import AWS from "aws-sdk";
// import MyApplyApi from "@/apis/myProfileApplyAPI";
export default {
  name: "MycompanyModal",
  props: {
    data: Object,
    show: Boolean
  },
  data() {
    return {
      dataList: this.data,
      openModal: this.show,
      companyName: "",
      companyUrl: "",
      companyImage: "false",
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48",
      changeimage: "",
      hireNumber: "",
      postImagePath: "",
      mycompanynum: ""
    };
  },
  methods: {
    cancleWrite() {
      console.log(this.openModal);
      // console.log(this.cList.company);
      this.$emit("modal-show");
    },
    // submitCinfo() {
    //   let data = {
    //     companyName: this.companyName,
    //     companyUrl: this.companyUrl,
    //     companyImage: this.companyImage,
    //     userId: this.$session.get("loginUserId")
    //   };
    //   console.log(data);
    //   MyApplyApi.requestInsertMyApply(
    //     data,
    //     res => {
    //       console.log(res);
    //       this.mycompanynum = res.data + 0;
    //       this.postImagePath =
    //         "myhireinfo//" +
    //         this.$session.get("loginUserId") +
    //         this.mycompanynum;
    //       this.uploadImage();
    //       let tempData = {
    //         COMPANY_IMAGE: this.postImagePath,
    //         COMPANY_NAME: data.companyName,
    //         COMPANY_URL: data.companyUrl,
    //         MY_APPLY_NUMBER: res.data,
    //         USER_ID: data.userId
    //       };
    //       this.$emit("pushNewMyApply", tempData);
    //     },
    //     error => {
    //       console.log(error);
    //     }
    //   );
    // },
    onFileChange() {
      this.file = this.$refs.file.files[0];
      let reader = new FileReader();
      reader.readAsDataURL(this.file);
      reader.onload = e => {
        console.log(e.target.result);
      };
      this.createImage(this.file);
    },
    createImage(file) {
      // var image = new Image();
      var reader = new FileReader();

      reader.onload = e => {
        this.changeimage = e.target.result;
        this.companyImage = "true";
      };
      reader.readAsDataURL(file);
    },
    removeImage: function() {
      this.changeimage = "";
      this.companyImage = "false";
    },
    uploadImage() {
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

      let photoKey = this.postImagePath;
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
          console.log("aws s3 사진 업로드 완료" + photoKey);
        }
      );
    }
  }
};
</script>

<style scoped>
/* 전체화면영역 */
.companyCardModal {
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  position: fixed;
  z-index: 10000;
  background-color: rgba(0, 0, 0, 0.26);
  transition: 0.5s;
  /* position: relative; */
}
.form-box {
  /* box-shadow: 10px 10px 10px #5c5c5c; */
  border-radius: 5px;
  position: absolute;
  /* 정가운데 정렬 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 70%;
  height: 70%;
  background: white;
  margin: 0 auto;
  
}
.middle-wrap {
  height: 60%;
  padding-top: 5%;
  overflow-x: hidden;
  overflow-y: scroll;
  -ms-overflow-style: none;
}
.middle-wrap::-webkit-scrollbar {
  display: none;
}
.form-box .form-title {
  height: 5%;
  margin-bottom: 30px;
  /* padding: 5%; */
  /* margin: 5% 5% 8% 5%; */
  text-align: center;
}
.form-box .c-input {
  text-align: left;
  margin: 3% 2%;
}
.c-input label {
  margin-right: 5%;
  width: 15%;
  text-align: right;
}
.c-input input {
  border-radius: 5px;
  width: 70%;
}
.add-input {
  margin: 5% 0;
  text-align: center;
}
.c-button {
  margin-top: 5%;
  height: 10%;
  text-align: center;
}
.c-button:hover {
  cursor: pointer;
}
.c-submit-btn {
  margin: 0 10% 0 15%;
  height: 100%;
  width: 30%;
  float: left;
  background: #edbf68;
  line-height: 52.8px;
  color: white !important;
}
.c-cancle-btn {
  background: #f78e6d;
  height: 100%;
  float: left;
  width: 30%;
  line-height: 52.8px;
  margin-right: 15%;
  color: white !important;
}
.c-user-btn {
  border-radius: 5px;
}
button:active {
  border: 0 !important;
  outline: 0 !important;
}
button:visited {
  border: 0 !important;
  outline: 0 !important;
}
.c-user-btn:hover {
  cursor: pointer;
}
.file-input {
  padding: 10px 0;
  border: none;
  float: left;
  width: 100%;
}
.file-input:hover {
  border: none !important;
}
#fileupload {
  float:left;
  width: 65%;
}
.image-title {
  float: left;
  width: 10%;
}
.img-input-wrap {
  float:left;
  width: 100%;
}
.remove-button {
  width: 80%;
  padding: 5px;
  margin-top: 2px;
  background: rgb(255, 179, 179);
}
.remove-button button{
  width: 100%;
  text-align: center;
}
.two-button {
  height: 10%;
  margin-top: 10px;
  /* margin-left: 20px; */
  margin-right: 30px;
  width: 98%;
  /* flex이용해서 한줄에 놓음 */
  display: flex;
  flex-direction: row-reverse;
  
}
.e-cancle-button {
  float: right;
  width: 7%;
  margin: 5px;
  padding: 10px;
  line-height: 52.8px;
  color: black !important;
}
.e-cancle-button:focus {
  outline-width: 0px;
  outline: none;
}
.e-submit-button {
  float: right;
  margin: 0;
  margin: 5px;
  line-height: 52.8px;
  color: black !important;
}
.e-submit-button:focus {
  outline-width: 0px;
  outline: none;
}
button:focus {
  border: 0 !important;
  outline: 0 !important;
}
button:visited {
  border: 0 !important;
  outline: 0 !important;
}
.no-css-button:hover {
  cursor: pointer;
}

</style>