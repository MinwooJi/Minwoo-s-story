<template>
  <div id="app">
    <!-- <button @click="openForm" class="btn open-form">Open form</button> -->
    <div class="form-popup">
      <div class="two-button">
        <button class="e-cancle-button no-css-btn" @click="confirm">
          <i style="color:white;" class="fas fa-times fa-2x"></i>
        </button>
        <!-- <button class="e-submit-button no-css-btn" @click="submitCinfo">
          <i style="color:black;" class="far fa-save fa-2x"></i>
        </button>-->
      </div>
      <div class="form-wrapper">
        <div class="row">
          <div class="col-md-12 text-center">
            <h1 class="form-title">기업상세</h1>
            <div class="check">
              <div class="apply-company">
                <button class="add-btn" @click="addApplyCom">ADD 지원 기업</button>
              </div>
              <div class="interest-company">
                <button class="add-btn" @click="addInterestCom">ADD MYCAL</button>
              </div>
            </div>
          </div>
        </div>
        <div class="pop-scroll">
          <div class="contents">
            <div class="row">
              <div class="form-group col-md-12">
                <label for="companyName">기업명</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="companyName"
                  id="companyName"
                  v-bind:readonly="is_readonly"
                />
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-6">
                <label for="startDate">시작일</label>
                <input
                  v-bind:type="type"
                  class="form-control"
                  v-model="startDate"
                  id="startDate"
                  v-bind:readonly="is_readonly"
                />
              </div>
              <div class="form-group col-md-6">
                <label for="endDate">종료일</label>
                <input
                  v-bind:type="type"
                  class="form-control"
                  v-model="endDate"
                  id="endDate"
                  v-bind:readonly="is_readonly"
                />
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-12">
                <label for="companyURL">기업 url 주소</label>
                <div v-if="!is_modify_form">
                  <a v-bind:href="companyURL">{{ companyURL }}</a>
                </div>
                <div v-if="is_modify_form">
                  <input
                    type="text"
                    class="form-control"
                    v-model="companyURL"
                    id="companyURL"
                    v-bind:readonly="is_readonly"
                  />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-12">
                <div v-if="companyImage">
                  <label for="companyImage">공고</label>
                  <div v-if="!is_modify_form">
                    <img :src="companyImage" alt="이미지" width="100%" height="auto" />
                  </div>
                </div>
                <div v-if="is_modify_form">
                  <div v-if="!changeimage">
                    <div v-if="companyImage">
                      <img :src="companyImage" alt="이미지" width="100%" height="auto" />
                    </div>
                    <input id="file-selector" ref="file" type="file" @change="onFileChange" />
                    <!-- <textarea class="form-control" v-model="companyImage" id="companyImage"></textarea> -->
                  </div>
                  <div v-else class="other-img-float">
                    <img :src="changeimage" alt width="50%" height="auto" />
                    <button @click="removeImage" class="other-image">다른 이미지 찾기</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="register-wrap" v-if="(!is_modify_form) && (loginUserId == 'admin')">
          <div class="detail-btn-wrap">
            <button @click="modifyForm" class="register">수정</button>
          </div>
          <div v-if="companyImage" class="detail-btn-wrap">
            <button @click="deleteimg" class="register">이미지삭제</button>
          </div>
          <div class="detail-btn-wrap">
            <button @click="deleteSchdule" class="register">삭제</button>
          </div>
        </div>
        <div class="register-wrap" v-if="is_modify_form">
          <div class="two-btn-wrap">
            <button @click="modifySchdule" class="register">수정하기</button>
          </div>
          <div class="two-btn-wrap">
            <button @click="confirm" class="register">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import HireAPI from "@/apis/hireAPI";
import AWS from "aws-sdk";
// import { error } from "jquery";
function dateAddDel(sDate, nNum, type) {
  var yy = parseInt(sDate.substr(0, 4), 10);
  var mm = parseInt(sDate.substr(5, 2), 10);
  var dd = parseInt(sDate.substr(8), 10);
  let d = new Date();
  if (type == "d") {
    d = new Date(yy, mm - 1, dd + nNum);
  } else if (type == "m") {
    d = new Date(yy, mm - 1, dd + nNum * 31);
  } else if (type == "y") {
    d = new Date(yy + nNum, mm - 1, dd);
  }

  yy = d.getFullYear();
  mm = d.getMonth() + 1;
  mm = mm < 10 ? "0" + mm : mm;
  dd = d.getDate();
  dd = dd < 10 ? "0" + dd : dd;

  return "" + yy + "-" + mm + "-" + dd;
}
export default {
  name: "PopUpDetail",
  data() {
    return {
      loginUserId: "",
      type: "text",
      is_clicked_bookmark: false,
      is_clicked_check: false,
      is_modify_form: false,
      is_readonly: true,
      companyName: "",
      startDate: {},
      endDate: {},
      companyURL: "",
      companyImage: "",
      hireNumber: "",
      changeimage: "",
      deleteimage: "",
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48"
    };
  },
  props: ["clickInfo", "readyCal"],
  created() {
    this.loginUserId = this.$session.get("loginUserId");
    this.companyName = this.clickInfo.event.title;
    this.startDate = this.clickInfo.event.startStr;
    this.endDate = this.$moment(
      this.getDt9(this.clickInfo.event.endStr, 1)
    ).format("YYYY-MM-DD");
    this.companyURL = this.clickInfo.event.groupId;
    this.companyImage = this.clickInfo.event.allow;
    this.hireNumber = this.clickInfo.event.id;
    console.log(this.clickInfo.event);
  },
  methods: {
    addApplyCom() {
      let data = {
        companyImage: this.companyImage,
        companyName: this.companyName,
        companyUrl: this.companyURL,
        endDate: this.getDt9(this.endDate.replace(/T.*$/, ""), -1),
        startDate: this.startDate.replace(/T.*$/, ""),
        userId: this.$session.get("loginUserId"),
        hireNumber: this.hireNumber
      };
      console.log("###tomyApplydata:", data);
      HireAPI.requestInsertMyApply(
        data,
        res => {
          console.log(res);
          if (res.data) {
            alert("지원했던 기업정보가 등록되었습니다.");
            this.confirm();
          } else {
            alert("이미등록된 지원기업입니다.");
            this.confirm();
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    addInterestCom() {
      let data = {
        companyImage: this.companyImage,
        companyName: this.companyName,
        companyUrl: this.companyURL,
        endDate: this.getDt9(this.endDate.replace(/T.*$/, ""), -1),
        startDate: this.startDate.replace(/T.*$/, ""),
        userId: this.$session.get("loginUserId"),
        hireNumber: this.hireNumber
      };
      console.log("###tomycaldata:", data);
      HireAPI.requestInsertHireToMyCal(
        data,
        res => {
          console.log(res);
          if (res.data == "success") {
            alert("관심기업 정보가 등록되었습니다.");
          } else {
            alert("이미등록된 채용정보입니다.");
          }
        },
        error => {
          console.log(error);
        }
      );
      this.$emit("showDetailFalse", false);
    },
    getDt9(s, i) {
      let newDt = new Date(s);
      newDt.setDate(newDt.getDate() - i);
      return newDt.toISOString();
    },
    clickBookMarkEmo() {
      this.is_clicked_bookmark = true;
      this.addToMyWatchlist();
    },
    unClickBookMarkEmo() {
      this.is_clicked_bookmark = false;
      this.deleteToMyWatchlist();
    },
    clickCheckEmo() {
      this.is_clicked_check = true;
      this.addToMyDonelist();
    },
    unClickCheckEmo() {
      this.is_clicked_check = false;
      this.deleteToMyDonelist();
    },
    addToMyWatchlist() {
      if (this.$session.get("loginUserId")) {
        let watchevent = {
          companyNumber: this.clickInfo.event.id,
          userId: this.$session.get("loginUserId")
        };
        console.log(watchevent);
      }
    },
    deleteToMyWatchlist() {
      if (this.$session.get("loginUserId")) {
        let watchevent = {
          companyNumber: this.clickInfo.event.id,
          userId: this.$session.get("loginUserId")
        };
        console.log(watchevent);
      }
    },
    addToMyDonelist() {
      if (this.$session.get("loginUserId")) {
        let doneEvent = {
          companyNumber: this.clickInfo.event.id,
          userId: this.$session.get("loginUserId")
        };
        console.log(doneEvent);
      }
    },
    deleteToMyDonelist() {
      if (this.$session.get("loginUserId")) {
        let doneEvent = {
          companyNumber: this.clickInfo.event.id,
          userId: this.$session.get("loginUserId")
        };
        console.log(doneEvent);
      }
    },
    modifyForm() {
      this.is_modify_form = true;
      this.is_readonly = false;
      this.type = "date";
    },
    confirm() {
      console.log("자식의 confirm()실행됨");
      this.$emit("showDetailFalse", false);
    },
    modifySchdule() {
      if (this.changeimage != "") {
        this.companyImage =
          "https://jobara.s3.ap-northeast-2.amazonaws.com/hireinfo//" +
          this.hireNumber;
        this.modifiedImage();
      }
      if (this.deleteimage != "") {
        console.log("companyImage삭제");
        this.companyImage = "";
      }
      let data = {
        companyImage: this.companyImage,
        companyName: this.companyName,
        companyUrl: this.companyURL,
        endDate: this.getDt9(this.endDate, -1),
        hireNumber: this.clickInfo.event.id,
        startDate: this.startDate,
        userId: this.$session.get("loginUserId")
      };
      let event = {
        id: data.hireNumber,
        title: data.companyName,
        startStr: data.startDate.replace(/-/g, ""),
        endStr: data.endDate.replace(/-/g, ""),
        start: dateAddDel(
          new Date(data.startDate).toISOString().replace(/T.*$/, ""),
          0,
          "d"
        ),
        end: dateAddDel(
          new Date(data.endDate).toISOString().replace(/T.*$/, ""),
          0,
          "d"
        ),
        allow: data.companyImage,
        groupId: data.companyURL
        // overlap: "wow", // 관심 & 지원한
        // backgroundColor: color[(this.num + 1) % 10]
      };
      console.log("modify : ", data);
      HireAPI.requestModifyEvent(
        data,
        res => {
          console.log(res);
          alert("채용일정이 수정 되었습니다.");
          this.$emit("showDetailFalse", event);
        },
        error => {
          console.log(error);
        }
      );
      this.is_modify_form = false;
      this.is_readonly = true;
      this.type = "text";
      console.log("자식의 modifySchdule()실행됨");
      // HireApi() // this.clickInfo.event.id ~~~
      // location.reload
      // alert("채용일정이 수정 되었습니다.");
    },
    deleteSchdule() {
      console.log("자식의 deleteSchdule()실행됨");
      console.log(this.clickInfo.event);
      HireAPI.requestDeleteEvent(
        this.clickInfo.event.id,
        res => {
          console.log(res);
          alert("채용일정이 삭제 되었습니다.");
          this.$emit("showDetailFalse", this.clickInfo.event.id);
        },
        error => {
          console.log(error);
        }
      );
      // alert("채용일정이 삭제 되었습니다.");
    },
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
      this.uploadImage();
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

      let photoKey = "hireinfo//" + this.hireNumber;
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
    },
    deleteimg() {
      this.deleteFile("hireinfo//" + this.hireNumber);
      this.deleteimage = "delete";
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
          console.log("There was an error deleting your photo: ", err.message);
        }

        console.log(key);
        console.log(data);
        this.modifySchdule();
      });
    }
  }
};
</script>
<style scoped>
.other-img-float img {
  float: left;
}
.other-img-float button {
  float: left;
}
.other-image {
  width: 51%;
  background: rgba(255, 177, 154, 0.918);
}
.register-wrap {
  width: 50%;
  display: flex;
  flex-direction: row;
  margin: 0 auto;
}
.detail-btn-wrap {
  margin: 0 auto;
  width: 30%;
  margin-top: 3%;
  margin-right: 1%;
  justify-content: center;
  /* floa: left; */
}
.two-btn-wrap {
  margin: 0 auto;
  width: 45%;
  margin-top: 3%;
  margin-right: 1%;
  justify-content: center;
}
.register {
  border-radius: 5px;
  width: 100%;
  padding: 12px;
  color: white;
  background: rgb(223, 128, 99);
}
.contents {
  width: 97%;
}
.row-button {
  padding-left: 2%;
}
/* .p-scroll {
  margin-top: 5%;
  overflow-x: hidden;
  overflow-y: auto;
  height: 80%;
} */
.pop-scroll {
  width: 100%;
  height: 70%;
  overflow-x: hidden;
  overflow-y: auto;
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
.success-message {
  display: none;
}

.form-popup .form-wrapper {
  /* top: 2em; */
  /* max-width: 700px; */
  background: white;
  /* padding: 50px;*/
  padding: 50px 80px 80px 80px;
  height: 80%;
  width: 80%;
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
  position: relative;
  margin-top: 3%;
  margin-bottom: 3%;
  border-radius: 15px;
}
.form-title {
  margin-bottom: 20px;
  text-transform: uppercase;
}
.close-form {
  border-radius: 30px;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 10000;
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
.btn-space {
  margin-right: 10px;
  /* color: #ffee00; */
}
.bookmark {
  width: 50px;
  height: 100%;
  display: inline-table;
  float: right;
}
.check {
  display: float;
  margin-bottom: 10px;
}
.apply-company {
  float: right;
  margin-left: 3%;
  margin-right: 3%;
}
.interest-company {
  float: right;
  margin-left: 3%;
}
.add-btn {
  color: gray;
}
.add-btn:hover {
  color: #ed7568;
}

.emoji i {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  font-size: 25px;
}
.emoji i:hover {
  cursor: pointer;
}
.two-button {
  height: 10%;
  margin-top: 10px;
  /* margin-left: 20px; */
  margin-right: 30px;
  width: 98%;
  top: 0;
  right: 0;
  z-index: 10000;
  /* flex이용해서 한줄에 놓음 */
  /* display: flex;
  flex-direction: row-reverse; */
}
.e-cancle-button {
  float: right;
  /* width: 7%; */
  /* margin: 5px; */
  padding: 10px;
  /* line-height: 52.8px; */
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
