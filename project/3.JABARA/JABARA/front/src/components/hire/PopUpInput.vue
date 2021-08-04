<template>
  <div id="app">
    <!-- <button @click="openForm" class="btn open-form">Open form</button> -->
    <div class="form-popup">
      <div class="close">
        <button @click="closeForm" class="close-btn">
          <i style="color:white;" class="fas fa-times fa-x"></i>
        </button>
      </div>
      <div class="form-wrapper">
        <div class="text-center">
          <h3 class="form-title">일정 등록</h3>
        </div>
        <div class="pop-scroll">
          <div class="row">
            <div class="form-group col-md-12">
              <label for="companyName">채용기업</label>
              <input type="text" class="form-control" v-model="companyName" id="companyName" />
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-12">
              <label for="companyURL">기업 url 주소</label>
              <input type="text" class="form-control" v-model="companyURL" id="companyURL" />
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-12">
              <label for="companyImage">공고 이미지</label>
              <!-- <textarea class="form-control" v-model="companyImage" id="companyImage"></textarea> -->
              <div id="fileupload">
                <div v-if="!changeimage">
                  <input class="file-input" ref="file" type="file" @change="onFileChange" />
                </div>
                <div v-else class="file-success">
                  <img :src="changeimage" width="100%" height="auto"/>
                  <div class="remove-button">
                    <button @click="removeImage">사진 삭제하기</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="register-wrap">
          <button @click="registForm" class="register">등록하기</button>
        </div>
      </div>
    </div>
    <!-- <div class="success-message">
      <h1>You success reset this form</h1>
    </div> -->
  </div>
</template>
<script>
import AWS from "aws-sdk";
import HireAPI from "@/apis/hireAPI";
export default {
  name: "PopUpInput",
  data() {
    return {
      companyName: "",
      companyURL: "",
      companyImage: "false",
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48",
      changeimage: "",
      hireNumber: "",
      postImagePath: ""
    };
  },
  props: ["selectInfo", "readyCal"],
  methods: {
    // openForm() {
    //   console.log("openForm 호출!");
    //   this.show = true;
    // },
    getDt9(s, i) {
      let newDt = new Date(s);
      newDt.setDate(newDt.getDate() - i);
      return newDt.toISOString();
    },
    closeForm() {
      this.$store.commit("showFalse");
      console.log(this.$store.state.show);
      this.$emit("showfalse", false);
    },
    registForm() {
      console.log("자식의 registForm()실행됨");
      console.log(this.selectInfo);
      let title = this.companyName;
      // let calendarApi = this.selectInfo.view.calendar;
      // console.log(calendarApi);
      // calendarApi.unselect(); // clear date selection
      // if (title) {
      //   calendarApi.addEvent({
      //     title,
      //     start: this.selectInfo.startStr,
      //     end: this.selectInfo.endStr,
      //     allDay: this.selectInfo.allDay,
      //     groupId: this.companyURL, // groupid에 url..
      //     allow: this.companyImage, // 여기에 이미지 주소를 주자!
      //     //backgroundColor: "red"
      //   });
      // } // !!!!!!!!!!!! 여기 지우는것 고려해보기
      let data = {
        companyImage: this.companyImage,
        companyName: title,
        companyUrl: this.companyURL,
        endDate: this.$moment(this.selectInfo.end).format("YYYY-MM-DD"),

        // hireNumber: 0,
        startDate: this.$moment(this.selectInfo.start).format("YYYY-MM-DD"),
        userId: this.$session.get("loginUserId")
      };
      console.log(
        this.selectInfo.end.setHours(this.selectInfo.end.getHours() + 6)
      );
      let event = {
        id: -1,
        title: data.companyName,
        startStr: data.startDate.replace(/-/g, ""),
        endStr: data.endDate.replace(/-/g, ""),
        start: data.startDate,
        end: data.endDate,
        allow: data.companyImage,
        groupId: data.companyURL
        // overlap: "wow", // 관심 & 지원한
        // backgroundColor: color[(this.num + 1) % 10]
      };
      console.log("### input", data);
      HireAPI.requestInsertEvent(
        data,
        res => {
          console.log("hire 등록!");
          this.hireNumber = res.data + 0;
          event.id = res.data + 0;
          alert("채용일정이 등록 되었습니다.");
          // this.$emit("reloadHireInfo");
          this.$emit("showfalse", event);
          console.log(this.hireNumber);
          this.postImagePath = "hireinfo//" + this.hireNumber;
          console.log(this.postImagePath);
          this.uploadImage();
        },
        error => {
          console.log(error);
        }
      );

      // alert("채용일정이 등록 되었습니다.");
      // this.$emit("showfalse", event);
    },
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
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

.text-center {
  height: 10%;
  width: 100%;
  text-align: center;
  padding: 2em;
  margin-bottom: 3%;
}
.close-btn {
  left: 0;
  top: 0;
  padding: 20px;
  z-index: 1000;
}
.remove-button {
  width: 100%;
  padding: 5px;
  margin-top: 2px;
  background: rgb(255, 179, 179);
}
.remove-button button{
  width: 100%;
  text-align: center;
}
.register-wrap {
  height: 10%;
  margin: 5% auto;
  width: 30%;
  margin-top: 3%;
}
.register {
  border-radius: 5px;
  width: 100%;
  padding: 12px;
  color: white;
  background: rgb(223, 128, 99);
}
.pop-scroll {
  margin: 0 auto;
  width: 70%;
  height: 70%;
  overflow-x: hidden;
  overflow-y: scroll;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.open-form {
  margin-top: 50px;
  margin-left: 50px;
  cursor: pointer;
  color: white;
  padding: 10px 25px;
  box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
  border-radius: 19px;
  border: 2px solid #83b566;
  background-color: #83b566;
  text-transform: uppercase;
  font-size: 14px;
  transition: 0.3s;
}
.form-popup,
.success-message {
  /* display: none; */
  width: 100%;
  height: 100%;
  left: 0;
  position: fixed;
  top: 0;
  z-index: 9999;
  background-color: rgba(0, 0, 0, 0.5);
  transition: 0.5s;
}
/* .success-message {
  display: none;
} */
.form-group {
  /* margin: 0 auto; */
  width: 100%;
  height: 30%;
}
.form-popup .form-wrapper {
  /* top: 2em; */
  /* max-width: 700px; */
  z-index: 99;
  background: white;
  width: 50%;
  height: 70%;
  padding: 10px;
  /* padding: 80px; */
  /* position: absolute; */
  /* margin-left: auto;
  margin-right: auto; */
  /* left: 0; */
  /* right: 0; */

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.form-wrapper {
  margin-top: 3%;
  margin-bottom: 3%;
  border-radius: 15px;
}
.form-title {
  margin-bottom: 20px;
  text-transform: uppercase;
}
.file-input {
  padding: 10px 0;
  border: none; 
}
.close-form {
  border-radius: 30px;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 9;
  cursor: pointer;
  padding: 10px 30px;
  background-color: #83b566;
  border: 2px solid #83b566;
  color: #ffffff;
  font-size: 18px;
  text-transform: uppercase;
  margin-bottom: 20px;
  transition: 0.3s;
}
.send-form {
  cursor: pointer;
  padding: 10px 30px;
  background-color: #83b566;
  border: 2px solid #83b566;
  color: #ffffff;
  font-size: 18px;
  text-transform: uppercase;
  transition: 0.3s;
}
.form-control:focus {
  border-color: #83b566;
  box-shadow: 0 0 0 0.2rem rgba(131, 181, 102, 0.25);
}
.btn:hover {
  box-shadow: 0 10px 15px rgba(0, 0, 0, 0.35);
}

</style>
