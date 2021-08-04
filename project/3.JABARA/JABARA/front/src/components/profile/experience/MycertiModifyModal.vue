<template>
  <div class="licenseModal">
    <div class="form-box">
      <div class="form-title">
        <div class="two-button">
          <button class="l-cancle-button no-css-btn" @click="cancleWrite">
            <i style="color: black;" class="fas fa-times fa-2x"></i>
          </button>
          <button class="l-submit-button no-css-btn" @click="modifyCinfo">
            <i style="color: black;" class="far fa-save fa-2x"></i>
          </button>
        </div>
        <div class="l-title">
          <h3>자격증 수정</h3>
        </div>
      </div>
      <div class="l-scroll">
        <div class="l-input input-subject">
          <label for="l-name">자격증명</label>
          <input v-model="licenseName" type="text" id="l-name" />
        </div>
        <div class="l-input input-date">
          <label for="l-Number">자격번호</label>
          <input v-model="licenseNumber" type="text" id="l-Number" readonly />
        </div>
        <div class="l-input input-working">
          <label for="l-date">만료기간</label>
          <input v-model="licenseDate" type="text" id="l-date" />
        </div>
        <div class="l-input input-role">
          <label for="l-score">점수(등급)</label>
          <input v-model="licenseScore" type="text" id="l-score" />
        </div>
        <div class="l-input input-content">
          <label for="l-inst">출제기관</label>
          <input v-model="licenseInst" type="text" id="l-inst" />
        </div>
        <div class="l-input input-etc">
          <label for="l-etc">기타</label>
          <input v-model="licenseEtc" type="text" id="l-etc" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LicenseApi from "@/apis/licenseAPI";
import { mapMutations } from "vuex";
export default {
  name: "MycertiModifyModal",
  props: {
    CMshow: Boolean,
    p: Object
  },
  data() {
    return {
      openModal: this.CMshow,
      licenseDate: this.p.LICENSE_DATE,
      licenseEtc: this.p.LICENSE_ETC,
      licenseInst: this.p.LICENSE_INST,
      licenseName: this.p.LICENSE_NAME,
      licenseScore: this.p.LICENSE_SCORE,
      licenseNumber: this.p.LICENSE_NUMBER
    };
  },

  methods: {
    ...mapMutations(["setViewStatus"]),
    cancleWrite() {
      //   // console.log(this.openModal)
      //   // console.log(this.eData.compony)
      //   this.openModal = !this.openModal;
      //   this.$emit("throw-ReloadList", this.openModal);
      this.$emit("modal-show");
    },
    modifyCinfo() {
      let data = {
        licenseEtc: this.licenseEtc,
        licenseDate: this.licenseDate,
        licenseInst: this.licenseInst,
        licenseName: this.licenseName,
        licenseNumber: this.licenseNumber,
        licenseScore: this.licenseScore,
        userId: this.$session.get("loginUserId")
      };
      let tempData = {
        LICENSE_NAME: data.licenseName,
        LICENSE_NUMBER: "",
        LICENSE_DATE: data.licenseDate,
        LICENSE_INST: data.licenseInst,
        LICENSE_SCORE: data.licenseScore,
        LICENSE_ETC: data.licenseEtc
        //userID
      };
      // console.log(data);
      LicenseApi.modifyLicense(
        data,
        res => {
          console.log(res);
          if (res.data == "success") {
            tempData.LICENSE_NUMBER = data.licenseNumber;
          }
          console.log("mmodal>", tempData);
          this.$emit("modifyCertiData", tempData);
        },
        error => {
          console.log(error);
        }
      );
      // console.log("mod ->");
      // console.log('Einfo 보낸다')
      // this.openModal = !this.openModal;
      // this.eData = this.eData
      // this.$emit("modifyCertiData", tempData);
      // this.$emit("reload-by-modify");
    }
  }
};
</script>

<style scoped>
/* 전체화면영역 */
.licenseModal {
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
  width: 60%;
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
.form-title .l-title {
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
.l-scroll {
  height: 70%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.l-scroll::-webkit-scrollbar {
  display: none;
}
.l-scroll .l-input {
  text-align: left;
  margin: 3% 2%;
}
.l-input label {
  margin-right: 5%;
  width: 15%;
  text-align: right;
}
.l-input input {
  border-radius: 5px;
  width: 70%;
}
.l-input textarea {
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
.l-submit-button {
  float: right;
  margin: 0;
  width: 7%;
  line-height: 52.8px;
  color: black !important;
}
.l-cancle-button {
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
