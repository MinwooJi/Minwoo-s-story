<template>
  <div id="my-experience">
    <div id="experienve-box">
      <div class="exp-button">
        <button @click="showCertiModalForm">자격증작성</button>
        <button @click="showExpModalForm">경험작성</button>
      </div>
      <div class="submit-exp">
        <h1 v-if="expDataList.length == 0 && certiDataList.length == 0">아직 경험을 적지 않았습니다</h1>
        <MyexperienceTable
          v-if="expDataList != 0"
          :exp-data-list="expDataList"
          @deleteExpData="deleteExpData"
          @is-clicked-exp-modify="isClickexpMod"
        />
        <MycertiTable
          v-if="certiDataList != 0"
          :certi-data-list="certiDataList"
          @deleteCertiData="deleteCertiData"
          @is-clicked-modify="isClickMod"
        />
      </div>
    </div>
    <div v-if="Eshow" class="my-exp-modal">
      <!-- @modal-show="showExpModalForm" -->
      <MyexpModal @pushNewExpData="pushNewExpData" />
    </div>
    <div v-if="EMshow" class="my-exp-modal">
      <MyexpModifyModal
        :EMshow="EMshow"
        :exp="exp"
        @modifyExpData="modifyExpData"
        @modal-show="showexpModifyModalForm"
      />
    </div>
    <div v-if="Cshow" class="my-certi-modal">
      <MycertiModal @pushNewCertiData="pushNewCertiData" />
    </div>
    <div v-if="CMshow" class="my-certi-modal">
      <MycertiModifyModal
        :CMshow="CMshow"
        :p="p"
        @modifyCertiData="modifyCertiData"
        @modal-show="showCertiModifyModalForm"
      />
    </div>
  </div>
</template>

<script>
import LicenseApi from "@/apis/licenseAPI";
import ExpApi from "@/apis/expAPI";
import MycertiModifyModal from "./MycertiModifyModal.vue";
import MyexpModifyModal from "./MyexpModifyModal.vue";

// import axios from "axios";

import MyexpModal from "./MyexpModal.vue";
import MycertiModal from "./MycertiModal.vue";
import MyexperienceTable from "./MyexperienceTable.vue";
import MycertiTable from "./MycertiTable.vue";
export default {
  name: "Myexperience",
  components: {
    MyexpModal,
    MycertiModal,
    MyexperienceTable,
    MycertiTable,
    MycertiModifyModal,
    MyexpModifyModal
  },
  data() {
    return {
      exp: {},
      p: {},
      CMshow: false,
      Cshow: false,
      EMshow: false,
      Eshow: false,
      expDataList: [],
      certiDataList: []
    };
  },
  created() {
    this.reloadcertiDataList();
    this.reloadexpDataList();
  },

  methods: {
    isClickexpMod(exp) {
      this.showexpModifyModalForm();
      console.log(exp);
      this.exp = exp;
    },
    isClickMod(p) {
      this.showCertiModifyModalForm();
      console.log(p);
      this.p = p;
    },
    reloadexpDataList() {
      console.log("exp 리로드");
      ExpApi.selectExp(
        this.$session.get("loginUserId"),
        res => {
          console.log("exp data확인하기");
          console.log(res);
          let exptempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            exptempArr.push(res.data[i]);
          }
          this.expDataList = exptempArr;
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteExpData(data) {
      for (let i = 0; i < this.expDataList.length; i++) {
        if (this.expDataList[i].EXP_NUMBER == data.expNumber) {
          this.expDataList.splice(i, 1);
        }
      }
      console.log("deleteExpData", data);
    },
    deleteCertiData(data) {
      for (let i = 0; i < this.certiDataList.length; i++) {
        if (this.certiDataList[i].LICENSE_NUMBER == data.licenseNumber) {
          this.certiDataList.splice(i, 1);
        }
      }
      console.log("deleteCertiData", data);
    },
    modifyExpData(data) {
      console.log("modifyExpData : ", data);
      for (let i = 0; i < this.expDataList.length; i++) {
        console.log("for문돈다" + this.expDataList);
        if (this.expDataList[i].EXP_NUMBER == data.EXP_NUMBER) {
          console.log("되라되라" + this.expDataList);
          this.expDataList.splice(i, 1, data);
        }
      }
      this.showexpModifyModalForm();
    },
    modifyCertiData(data) {
      console.log("modCertDataInList", data);
      for (let i = 0; i < this.certiDataList.length; i++) {
        if (this.certiDataList[i].LICENSE_NUMBER == data.LICENSE_NUMBER) {
          this.certiDataList.splice(i, 1, data);
        }
      }
      this.showCertiModifyModalForm();
    },
    pushNewCertiData(data) {
      // console.log("push:", data);
      if (data) {
        if (data.LICENSE_NUMBER) {
          this.certiDataList.unshift(data);
          // console.log("change list: ", this.certiDataList);
        }
      }
      this.showCertiModalForm();
      // this.certiDataList.sort(function(a, b) {
      //   return b.EXP_NUMBER-a.EXP_NUMBER;
      // })
    },
    pushNewExpData(data) {
      if (data) {
        if (data.EXP_NUMBER) {
          this.expDataList.push(data);
        }
        console.log("##############실행됐다!!");
        this.expDataList.sort(function(a, b) {
          return b.EXP_NUMBER - a.EXP_NUMBER;
        });
      }
      this.showExpModalForm();
    },
    reloadcertiDataList() {
      console.log("license 리로드");
      LicenseApi.selectLicense(
        this.$session.get("loginUserId"),
        res => {
          let tempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            tempArr.push(res.data[i]);
          }
          this.certiDataList = tempArr;
        },
        error => {
          console.log(error);
        }
      );
    },
    showExpModalForm() {
      this.Eshow = !this.Eshow;
    },
    showCertiModalForm() {
      this.Cshow = !this.Cshow;
    },
    showCertiModifyModalForm() {
      // console.log("호출됨");
      this.CMshow = !this.CMshow;
    },
    showexpModifyModalForm() {
      console.log(this.EMshow);
      this.EMshow = !this.EMshow;
    }
  }
};
</script>

<style scoped>
#my-experience {
  margin: 0 auto;
  width: 95%;
  height: 800px;
  /* background: blue; */
}
#experienve-box {
  margin: 0% 1% 5% 1%;
  padding: 2% 0 0 0;
  /* background: #fd93da; */
  height: inherit;
}
.exp-button {
  display: flex;
  flex-direction: row-reverse;
  margin-right: 5%;
  /* margin-top: 2%; */
  height: 10%;
  width: 100%;
  /* background: yellow; */
}
.exp-button > button {
  /* float: right; */
  margin: 0 2%;
  padding: 2px;
  width: 15%;
  text-align: center;
  height: 64.3px;
  background: #ed7568;
  color: white;
  line-height: 33px;
  text-align: center;
  font-size: 150%;
  border-radius: 5px;
}

.certi-table {
  height: 50%;
  background: green;
}
.submit-exp {
  text-align: center;
  clear: both;
  height: 90%;
  /* background: yellow; */
}
.submit-exp h1 {
  line-height: 578px;
}
button:active {
  border: 0 !important;
  outline: none !important;
}
button:visited {
  border: 0 !important;
  outline: none !important;
}
</style>
