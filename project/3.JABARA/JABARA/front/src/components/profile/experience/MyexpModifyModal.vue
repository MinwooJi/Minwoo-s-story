<template>
  <div class="experienceModal">
    <div class="form-box">
      <div class="form-title">
        <div class="two-button">
          <button class="e-cancle-button no-css-btn" @click="cancleWrite">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
          <button class="e-submit-button no-css-btn" @click="modifyEinfo">
            <i style="color:black;" class="far fa-save fa-2x"></i>
          </button>
        </div>
        <div class="e-title">
          <h3>경험정리 수정</h3>
        </div>
      </div>
      <div class="e-scroll">
        <div class="e-input input-subject">
          <label for="subjectId">주제</label>
          <input v-model="expSubject" type="text" id="subjectId" />
        </div>
        <div class="e-input input-date">
          <label for="dateId">언제(기간)</label>
          <input v-model="expDate" type="text" id="dateId" />
        </div>
        <div class="e-input input-working">
          <label for="workingID">과목(활동명)</label>
          <textarea v-model="expWorking" type="text" id="workingID"></textarea>
        </div>
        <div class="e-input input-role">
          <label for="roleID">역할</label>
          <textarea v-model="expRole" type="text" id="roleID"></textarea>
        </div>
        <div class="e-input input-content">
          <label for="contentID">내용</label>
          <textarea v-model="expContent" type="text" id="roleID"></textarea>
        </div>
        <div class="e-input input-diff-exp">
          <label for="hardID">어려웠던 점</label>
          <textarea v-model="expHard" type="text" id="hardID"></textarea>
        </div>
        <div class="e-input input-solve">
          <label for="solveID">해결 방법</label>
          <textarea v-model="expSolve" type="text" id="solveID"></textarea>
        </div>
        <div class="e-input input-learn">
          <label for="learnID">배운 점</label>
          <textarea v-model="expLearn" type="text" id="learnID"></textarea>
        </div>
        <div class="e-input input-etc">
          <label for="etcID">기타</label>
          <textarea v-model="expEtc" type="text" id="etcID"></textarea>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ExpApi from "@/apis/expAPI";
import { mapMutations } from "vuex";

export default {
  name: "MyexpModifyModal",
  props: {
    EMshow: Boolean,
    exp: Object
  },
  data() {
    return {
      openModal: this.EMshow,
      // eData: this.expDatas
      expNumber: this.exp.EXP_NUMBER,
      expSubject: this.exp.EXP_SUBJECT,
      expDate: this.exp.EXP_DATE,
      expWorking: this.exp.EXP_WORKING,
      expRole: this.exp.EXP_ROLE,
      expContent: this.exp.EXP_CONTENT,
      expHard: this.exp.EXP_HARD,
      expSolve: this.exp.EXP_SOLVE,
      expLearn: this.exp.EXP_LEARN,
      expEtc: this.exp.EXP_ETC
    };
  },
  methods: {
    ...mapMutations(["setViewStatus"]),
    cancleWrite() {
      // console.log(this.openModal)
      // console.log(this.eData.compony)
      // this.openModal = !this.openModal
      // this.$emit('modal-show', this.openModal)
      this.$emit("modal-show");
    },
    modifyEinfo() {
      let data = {
        expNumber:this.expNumber,
        expContent:this.expContent,
        expDate:this.expDate,
        expEtc:this.expEtc,
        expHard:this.expHard,
        expLearn:this.expLearn,
        expRole:this.expRole,
        expSolve:this.expSolve,
        expSubject:this.expSubject,
        expWorking:this.expWorking,
        userId:this.$session.get("loginUserId"),
      }
      console.log(data);
      let EtempData= {
        EXP_NUMBER: -1,
        EXP_DATE: data.expDate,
        EXP_WORKING: data.expWorking,
        EXP_SUBJECT: data.expSubject
      }
      ExpApi.modifyExp(
        data,
        res => {
          console.log("성공했니");
          console.log(res.data);
          if (res.data == "success") {
            EtempData.EXP_NUMBER = data.expNumber;
            console.log("if문 성공했니",EtempData);
          }
          this.$emit("modifyExpData", EtempData);
        },
        error => {
          console.log(error);
        }
      );
      // console.log(data);
      // console.log("exp modify=>");
      // console.log(this.openModal);
      // this.openModal = !this.openModal;
      // this.eData = this.eData
      // this.$emit("modal-show", this.openModal);
      // this.$emit("reloadexpModify");
    }
  }
};
</script>

<style scoped>
/* 전체화면영역 */
.experienceModal {
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  position: fixed;
  z-index: 9999;
  background: rgba(0, 0, 0, 0.5);
  transition: 0.5s;
  /* position: relative; */
}
.form-box {
  border-radius: 5px;
  position: absolute;
  /* 정가운데 정렬 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 80%;
  padding-bottom: 5%;
  background: white;
  margin: 0 auto;
}
.form-box .form-title {
  /* height: 10%; */
  margin: 4%;
  text-align: center;
  display: block;
}
.form-title .e-title {
  height: 10%;
  width: 100%;
  text-align: center;
}

/* .e-title h3 {
  text-align: center;
} */
.form-title .two-button {
  margin-top: 0;
  width: 100%;
  /* flex이용해서 한줄에 놓음 */
  display: flex;
  flex-direction: row-reverse;
}
.e-scroll {
  height: 70%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.e-scroll::-webkit-scrollbar {
  display: none;
}
.e-scroll .e-input {
  text-align: left;
  margin: 3% 2%;
}
.e-input label {
  margin-right: 5%;
  width: 15%;
  text-align: right;
}
.e-input input {
  border-radius: 5px;
  width: 70%;
}
.e-input textarea {
  border: solid 1px rgb(212, 212, 212);
  border-radius: 5px;
  width: 70%;
  height: 200px;
  padding: 1%;
}
.add-input {
  margin: 5% 0;
  text-align: center;
}
.e-submit-button {
  float: right;
  margin: 0;
  width: 7%;
  line-height: 52.8px;
  color: black !important;
}
.e-cancle-button {
  float: right;
  width: 7%;
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