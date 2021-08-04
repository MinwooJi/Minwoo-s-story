<template>
  <div id="my-introduction">
    <div id="introduction-box">
      <div class="intro-submit-btn">
        <button @click="showIntroModalForm">작성하기</button>
      </div>
      <div class="non-submit">
        <h1 v-if="introList.length==0">아직 자소서를 적지 않았습니다</h1>
        <div class="submit-status" v-else>
          <div class="intro-table">
            <MyintroductionTable
              :introList="introList"
              @deleteResumeData="deleteResumeData"
              @getResumeDetail="getResumeDetail"
            />
          </div>
        </div>
      </div>
      <div v-if="Ishow" class="my-intro-modal">
        <MyintroModal :Ishow="Ishow" @pushIntroList="pushIntroList" />
      </div>
      <div v-if="IDshow" class="my-intro-modal">
        <MyintroDetail :IDResume="IDResume" @modifyIntroList="modifyIntroList" />
      </div>
    </div>
  </div>
</template>

<script>
import MyintroModal from "./MyintroModal.vue";
import MyintroductionTable from "./MyintroductionTable.vue";
import IntroductionApi from "@/apis/introductionAPI";
import MyintroDetail from "./MyintroductionDetail";

export default {
  name: "Myintroduction",
  components: {
    MyintroModal,
    MyintroductionTable,
    MyintroDetail
  },
  data() {
    return {
      Ishow: false,
      introList: [],
      IDshow: false,
      IDResume: {}
    };
  },
  created() {
    this.loadIntroDataList();
  },
  methods: {
    modifyIntroList(ele) {
      console.log("###################실행됨");
      console.log("element:", ele);
      if (ele) {
        if (!ele.RESUME_DATE) {
          ele.RESUME_DATE = new Date().toISOString().replace(/T.*$/, "");
          // ele.RESUME_DATE = this.$moment(new Date()).format("YYYYMMDD");
        }
        for (let i = 0; i < this.introList.length; i++) {
          if (this.introList[i].RESUME_NUMBER == ele.RESUME_NUMBER) {
            this.introList.splice(i, 1, ele);
          }
        }
      }
      console.log("수정된 INTROLIST ###", this.introList);

      this.IDshow = false;
    },
    getResumeDetail(resume) {
      console.log("getResumeDetail: ", resume);
      this.Ishow = false;
      this.IDResume = resume;
      this.IDshow = true;
    },
    deleteResumeData(data) {
      for (let i = 0; i < this.introList.length; i++) {
        if (this.introList[i].RESUME_NUMBER == data.resumeNumber) {
          this.introList.splice(i, 1);
        }
      }
      console.log("deleteResumeData", data);
    },
    pushIntroList(ele) {
      console.log("###################실행됨");
      console.log("element:", ele);
      if (ele) {
        ele.RESUME_DATE = new Date().toISOString().replace(/T.*$/, "");
        // ele.RESUME_DATE = this.$moment(new Date()).format("YYYYMMDD");
        this.introList.unshift(ele);
      }
      this.showIntroModalForm();
    },
    showIntroModalForm() {
      console.log(this.Ishow);
      this.Ishow = !this.Ishow;
    },
    loadIntroDataList() {
      console.log("intro 리로드");
      IntroductionApi.selectIntroduction(
        this.$session.get("loginUserId"),
        res => {
          console.log("intro data확인하기");
          console.log(res);
          let introtempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            introtempArr.push(res.data[i]);
          }
          this.introList = introtempArr;
          console.log(this.introList);
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
#my-introduction {
  margin: 0 auto;
  width: 95%;
  height: 800px;
  /* background: blue; */
}
#introduction-box {
  margin: 0 1%;
  padding: 2% 0;
  /* background: #fd93da; */
  height: inherit;
}
.non-submit {
  text-align: center;
  clear: both;
  height: 90%;
}
.non-submit h1 {
  line-height: 578px;
}
.intro-submit-btn {
  display: flex;
  flex-direction: row-reverse;
  margin-right: 5%;
  /* margin-top: 2%; */
  height: 10%;
  width: 100%;
  /* background: yellow; */
}
.intro-submit-btn > button {
  /* float: right; */
  margin: 0 2%;
  padding: 2px;
  width: 15%;
  text-align: center;
  height: 64.3px;
  background: #edbe68da;
  color: white;
  line-height: 33px;
  text-align: center;
  font-size: 150%;
  border-radius: 5px;
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