<template>
  <div id="app">
    <!-- <button @click="openForm" class="btn open-form">Open form</button> -->
    <div class="form-popup">
      <div class="form-wrapper">
        <div class="two-button">
          <button class="e-cancle-button no-css-btn" @click="confirm">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <h3 class="form-title">일정</h3>
          </div>
        </div>
        <div class="pop-scroll">
          <div class="contents">
            <div class="row">
              <div class="form-group col-md-12">
                <label for="myCalTitle">제목</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="myCalTitle"
                  id="myCalTitle"
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
                <label for="myCalContents">내용</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="myCalContents"
                  id="myCalContents"
                  v-bind:readonly="is_readonly"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="register-wrap" v-if="!is_modify_form">
          <!-- <div class="detail-btn-wrap">
            <button @click="confirm" class="register">확인</button>
          </div> -->
          <div class="detail-btn-wrap">
            <button @click="modifyForm" class="register">수정</button>
          </div>
          <div class="detail-btn-wrap">
            <button @click="deleteSchdule" class="register">삭제</button>
          </div>
        </div>
        <div class="register-wrap" v-if="is_modify_form">
          <div class="detail-btn-wrap">
            <button @click="modifySchdule" class="register">수정하기</button>
          </div>
          <div class="detail-btn-wrap">
            <button @click="confirm" class="register">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MyCalAPI from "@/apis/myProfileCalAPI";
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
// import { error } from "jquery";
export default {
  name: "myCalDetail",
  data() {
    return {
      type: "text",
      is_clicked_bookmark: false,
      is_clicked_check: false,
      is_modify_form: false,
      is_readonly: true,
      myCalTitle: "",
      startDate: {},
      endDate: {},
      myCalContents: "",
      calNumber: -1
    };
  },
  props: ["clickInfo", "readyCal"],
  created() {
    this.endDate = this.$moment(
      this.getDt9(this.clickInfo.event.endStr, 1)
    ).format("YYYY-MM-DD");
    console.log("clicked: ", this.clickInfo.event);
    this.myCalTitle = this.clickInfo.event.title;
    this.startDate = this.clickInfo.event.startStr;
    // this.endDate = this.clickInfo.event.endStr;
    if (this.clickInfo.event.groupId != "undefined")
      this.myCalContents = this.clickInfo.event.groupId;
  },
  methods: {
    getDt9(s, i) {
      let newDt = new Date(s);
      newDt.setDate(newDt.getDate() - i);
      return newDt.toISOString();
    },

    modifyForm() {
      this.is_modify_form = true;
      this.is_readonly = false;
      this.type = "date";
    },
    confirm() {
      console.log("자식의 confirm()실행됨");
      this.$emit("showDetailFalse", "false");
    },
    modifySchdule() {
      let data = {
        calContent: this.myCalContents,
        calNumber: this.clickInfo.event.id,
        calTitle: this.myCalTitle,
        startDate: this.startDate,
        endDate: this.getDt9(this.endDate, -1),
        userId: this.$session.get("loginUserId")
      };
      let event = {
        id: this.clickInfo.event.id,
        title: this.myCalTitle,
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
        groupId: this.myCalContents
      };
      console.log("modifyMyCal : ", event);
      MyCalAPI.requestModifyMyEvent(
        data,
        res => {
          console.log(res);
          this.$emit("showDetailFalse", event);
          alert("일정이 수정 되었습니다.");
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
      // alert("일정이 수정 되었습니다.");
      // this.$emit("showDetailFalse", false);
    },
    deleteSchdule() {
      console.log("자식의 deleteSchdule()실행됨");
      console.log(Number(this.clickInfo.event.id));
      let data = {
        calNumber: Number(this.clickInfo.event.id),
        userId: this.$session.get("loginUserId")
      };
      MyCalAPI.requestDeleteMyEvent(
        data,
        res => {
          console.log(res);
          this.$emit("showDetailFalse", this.clickInfo.event.id);
          alert("일정이 삭제 되었습니다.");
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>
<style scoped>
.detail-btn-wrap {
  margin: 0 auto;
  width: 10%;
  margin-top: 3%;
  margin-right: 1%;
  float: left;
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
  margin: 0 auto;
  width: 80%;
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
  background-color: rgba(0, 0, 0, 0.082);;
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
  padding: 30px;
  height: 80%;
  width: 60%;
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
.register-wrap {
  width: 50%;
  margin: 0 auto;
  /* height: 10%; */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.detail-btn-wrap {
  width: 45%;
  height: 80%;
}
.two-button {
  height: 5%;
  margin-top: 10px;
  /* margin-left: 20px; */
  margin-right: 30px;
  width: 98%;
  /* flex이용해서 한줄에 놓음 */
  display: flex;
  flex-direction: row-reverse;
  
}
.e-cancle-button {
  top:0;
  float: right;
  /* width: 7%; */
  margin: 5px;
  /* padding: 10px; */
  line-height: 52.8px;
  color: black !important;
}
.fa-times {
  margin:0;
  top: 0;
}
.no-css-button:hover {
  cursor: pointer;
}
</style>
