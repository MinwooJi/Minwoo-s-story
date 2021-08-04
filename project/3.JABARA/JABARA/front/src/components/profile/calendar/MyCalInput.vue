<template>
  <div>
    <!-- <button @click="openForm" class="btn open-form">Open form</button> -->
    <div class="form-popup">
      <div class="form-wrapper">
        <div class="two-button">
          <button class="e-cancle-button no-css-btn" @click="closeForm">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
          <button class="e-submit-button no-css-btn" @click="registForm">
            <i style="color:black;" class="far fa-save fa-2x"></i>
          </button>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <h3 class="form-title">일정 등록</h3>
          </div>
        </div>
        <div class="input-content">
          <div class="pop-scroll">
            <div class="row">
              <div class="form-group col-md-12">
                <label for="calTitle">제목</label>
                <input type="text" class="form-control" v-model="calTitle" id="calTitle" />
              </div>
            </div>
            <div class="row">
              <div class="form-group col-md-12">
                <label for="calContent">내용</label>
                <input type="text" class="form-control" v-model="calContent" id="calContent" />
              </div>
            </div>
          </div>
          <!-- <div class="register-wrap">
            <button @click="registForm" class="register">등록하기</button>
          </div> -->
        </div>
      </div>
    </div>
    <!-- <div class="success-message">
      <h1>You success reset this form</h1>
    </div> -->
  </div>
</template>
<script>
import MyCalAPI from "@/apis/myProfileCalAPI";
export default {
  name: "PopUpInput",
  data() {
    return {
      calTitle: "",
      calContent: ""
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
      let data = {
        calContent: this.calContent,
        calTitle: this.calTitle,
        endDate: this.$moment(this.selectInfo.end).format("YYYY-MM-DD"),
        startDate: this.$moment(this.selectInfo.start).format("YYYY-MM-DD"),
        userId: this.$session.get("loginUserId")
      };
      let event = {
        id: -1,
        title: this.calTitle,
        startStr: this.$moment(this.selectInfo.start)
          .format("YYYY-MM-DD")
          .replace(/-/g, ""),
        endStr: this.$moment(this.selectInfo.end)
          .format("YYYY-MM-DD")
          .replace(/-/g, ""),
        start: this.$moment(this.selectInfo.start).format("YYYY-MM-DD"),
        end: this.$moment(this.selectInfo.end).format("YYYY-MM-DD"),
        groupId: this.calContent
      };
      console.log("### myinput : ", data);
      MyCalAPI.requestInsertMyEvent(
        data,
        res => {
          console.log("MyCal 등록!");
          event.id = res.data;
          // this.sovledAlgoCount = res.data;
          // this.$emit("reloadHireInfo");
          alert("일정이 등록 되었습니다.");
          // console.log(this.sovledAlgoCount);
          this.$emit("showfalse", event);
        },
        error => {
          console.log(error);
        }
      );
      // alert("일정이 등록 되었습니다.");
    }
  }
};
</script>
<style scoped>
.input-content {
  padding: 50px;
  height: 80%;
}
.close-btn {
  left: 0;
  top: 0;
}
.register-wrap {
  height: 10%;
  margin: 0 auto;
  width: 30%;
  margin-top: 10%;
}
.register {
  border-radius: 5px;
  width: 100%;
  padding: 12px;
  color: white;
  background: rgb(223, 128, 99);
}
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
/* .success-message {
  display: none;
} */

.form-popup .form-wrapper {
  /* top: 2em; */
  /* max-width: 700px; */
  background: white;
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
  z-index: 100;
  margin-top: 3%;
  margin-bottom: 3%;
  border-radius: 15px;
  height: 500px;
  width: 50%;
}
.form-title {
  height: 5%;
  margin-bottom: 20px;
  text-transform: uppercase;
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
.e-submit-button {
  float: right;
  width: 7%;
  /* margin: 0; */
  padding: 10px;
  margin: 5px;
  line-height: 52.8px;
  color: black !important;
}
button:active {
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
