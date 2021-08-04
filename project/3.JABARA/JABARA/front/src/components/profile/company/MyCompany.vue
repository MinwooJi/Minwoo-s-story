<template>
  <div id="my-company">
    <div id="company-box">
      <div class="mycompany-button">
        <button @click="insertMyApply">작성하기</button>
      </div>
      <div v-if="dataList.length==0" class="non-submit">
        <h1>아직 지원한 회사가 없습니다</h1>
      </div>
      <div v-else class="card-box">
        <MyCompanyCard :dataList="dataList" @deleteNewMyApply="deleteNewMyApply" />
      </div>
      <div v-if="show">
        <MyCompanyModal @pushNewMyApply="pushNewMyApply" @modal-show="setShowFalse" />
      </div>
    </div>
  </div>
</template>

<script>
import MyCompanyCard from "./MyCompanyCard.vue";
import MyCompanyModal from "./MyCompanyInputModal.vue";
import MyApplyApi from "@/apis/myProfileApplyAPI";
export default {
  name: "Mycompany",
  components: {
    MyCompanyCard,
    MyCompanyModal
  },
  data() {
    return {
      show: false,
      dataList: []
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    insertMyApply() {
      this.show = true;
    },
    deleteNewMyApply(MY_APPLY_NUMBER) {
      for (let i = 0; i < this.dataList.length; i++) {
        if (this.dataList[i].MY_APPLY_NUMBER == MY_APPLY_NUMBER) {
          this.dataList.splice(i, 1);
        }
      }
    },
    pushNewMyApply(data) {
      console.log("pushoNewMyApply :", data);
      if (data.MY_APPLY_NUMBER) {
        this.dataList.push(data);
      }
      this.setShowFalse();
    },
    setShowFalse() {
      this.show = false;
    },
    loadData() {
      console.log("####APPLY");
      MyApplyApi.requestSelectMyApply(
        this.$session.get("loginUserId"),
        res => {
          console.log(res);
          this.dataList = res.data;
        },
        error => {
          console.log(error);
        }
      );
    },
    showModalForm() {
      this.show = !this.show;
    }
  }
};
</script>

<style scoped>
.card-box {
  width: 100%;
  height: 81%;
}
/* .card-box:hover {
  cursor: pointer;
} */
.mycompany-button {
  display: flex;
  flex-direction: row-reverse;
  margin-right: 5%;
  /* margin-top: 2%; */
  height: 10%;
  width: 100%;
  /* background: yellow; */
}
.mycompany-button > button {
  /* float: right; */
  margin: 0 2%;
  padding: 2px;
  width: 15%;
  text-align: center;
  height: 64.3px;
  background: rgb(253, 125, 125);
  color: white;
  line-height: 33px;
  text-align: center;
  font-size: 150%;
  border-radius: 5px;
}

#my-company {
  margin: 0 auto;
  width: 95%;
  height: 700px;
  /* background: blue; */
}
#company-box {
  margin: 0 1%;
  padding: 2% 0;
  /* background: #fd93da; */
  height: inherit;
}
.submit-btn {
  margin-right: 5%;
  height: 10%;
  width: 100%;
  /* background: yellow; */
}
.submit-btn > button {
  float: right;
  margin: 0 3%;
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

.non-submit {
  text-align: center;
  clear: both;
  height: 90%;
}
.non-submit h1 {
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