<template>
  <div class="introduction-table">
    <!-- <h3 class="introduction-table-title">자격증</h3> -->
    <div class="intro-search">
      <div class="sel-op">
        <select class="intro-select" v-model="searchOption">
          <option disabled>합격여부</option>
          <option>전체</option>
          <option>합격</option>
          <option>불합격</option>
          <option>미정</option>
        </select>
      </div>
      <div class="search-input">
        <input
          type="text"
          v-model="searchKeyword"
          @keyup.enter="searchQuestion"
          placeholder="질문 검색"
        />
      </div>
      <div class="btn-search">
        <button @click="searchQuestion">
          <p>검색</p>
        </button>
      </div>
    </div>
    <div v-if="searchedQuestionList.length" class="my-question-list">
      <table class="question-table-wrap">
        <thead class="question-thead">
          <tr>
            <th class="question-Q">질문</th>
            <th class="question-A">답변</th>
          </tr>
        </thead>
        <tbody>
          <!-- <tr v-for="(expTableData, index) in paginatedData" :key="expTableData.index"> -->
          <tr
            v-for="(question,index) in searchPaginatedData"
            :key="index"
            @click="goToQuestionDetail(question)"
          >
            <td class="question-Q">{{ question.RESUME_Q }}</td>
            <td class="question-A">{{ question.RESUME_A }}</td>
          </tr>
        </tbody>
      </table>
      <div class="btn-cover">
        <button :disabled="searchPageNum === 0" @click="searchPrevPage" class="page-btn">이전</button>
        <span class="page-count">{{ searchPageNum + 1 }} / {{ searchPageCount }} 페이지</span>
        <button
          :disabled="searchPageNum >= searchPageCount - 1"
          @click="searchNextPage"
          class="page-btn"
        >다음</button>
      </div>
      <div v-if="showQuestionDetail" class="qustion-detail-modal">
        <QuestionDetail :questionDetailData="questionDetailData" @modal-close="closeDetail" />
      </div>
    </div>
    <br />
    <div class="my-resume-list">
      <table class="introduction-table-wrap">
        <thead class="introduction-thead">
          <tr>
            <th class="company-name">회사명</th>
            <th class="introduction-date">작성 날짜</th>
            <th class="introduction-pass">합격 여부</th>
            <th class="introduction-del-up">-</th>
          </tr>
        </thead>
        <tbody>
          <!-- <tr v-for="(expTableData, index) in paginatedData" :key="expTableData.index"> -->
          <tr v-for="intro in paginatedData" :key="intro.RESUME_NUMBER">
            <td @click="goToDetail(intro)">{{ intro.COMPANY_NAME }}</td>
            <td>{{ intro.RESUME_DATE }}</td>
            <td>{{ intro.RESUME_PASS }}</td>
            <td>
              <button @click="clickDeleteResume(intro)">삭제</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
import IntroductionApi from "@/apis/introductionAPI";
import QuestionDetail from "./QuestionDetail";
// import { error } from "jquery";
export default {
  name: "MyintroductionTable",
  components: {
    QuestionDetail
  },
  props: {
    introList: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  // 시험
  data() {
    return {
      pageNum: 0,
      searchPageNum: 0,
      searchOption: "",
      searchKeyword: "",
      searchedQuestionList: [],
      showQuestionDetail: false,
      questionDetailData: {}
    };
  },
  created() {},
  methods: {
    closeDetail() {
      this.showQuestionDetail = false;
    },
    searchQuestion() {
      // if (this.searchOption) {
      let data = [];
      data.push(this.$session.get("loginUserId"));
      if (this.searchOption.indexOf("전체") < 0) {
        console.log("전체이외");
        data.push(this.searchOption);
      } else {
        data.push("");
      }
      data.push(this.searchKeyword);
      IntroductionApi.searchQuestion(
        data,
        res => {
          console.log("검색", res);
          this.searchedQuestionList = res.data;
        },
        error => {
          console.log(error);
        }
      );
      // }
      // else {
      //   alert("검색조건을 선택해주세요");
      // }
    },
    goToDetail(resume) {
      console.log("goToDetail :", resume);
      this.$emit("getResumeDetail", resume);
    },
    goToQuestionDetail(question) {
      console.log(question);
      this.questionDetailData = question;
      this.showQuestionDetail = true;
    },
    clickDeleteResume(intro) {
      console.log(intro);
      let data = {
        resumeNumber: intro.RESUME_NUMBER,
        userId: this.$session.get("loginUserId")
      };

      IntroductionApi.deleteResume(
        data,
        res => {
          console.log(res);
          this.$emit("deleteResumeData", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    nextPage() {
      this.pageNum += 1;
    },
    searchNextPage() {
      this.searchPageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    searchPrevPage() {
      this.searchPageNum -= 1;
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.introList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    searchPageCount() {
      let listLeng = this.searchedQuestionList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.introList.slice(start, end);
    },
    searchPaginatedData() {
      const start = this.searchPageNum * this.pageSize,
        end = start + this.pageSize;
      return this.searchedQuestionList.slice(start, end);
    }
  }
};
</script>

<style scoped>
.question-Q {
  width: 40%;
}
.sel-op {
  width: 10%;
}

.btn-search input {
  border-radius: 5px;
  width: 100%;
}
.btn-search {
  width: 10%;
  display: flex;
  align-items: center;
}
.btn-search button {
  border-radius: 5px;
  background: #f7b66d;
  color: white;
  width: 100%;
  height: 100%;
  margin-left: 5%;
}
.btn-search button p {
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.select-input {
  width: 100%;
}
.search-input {
  width: 60%;
}
.search-input input {
  border-radius: 5px;
  width: 100%;
}
.intro-select {
  padding: 5% 10%;
  height: 100%;
  border-radius: 5px;
}
.intro-search {
  margin: 3% auto;
  display: flex;
  justify-content: center;
  flex-direction: row;
  width: 80%;
}
.introduction-thead {
  height: 15%;
}
.company-name {
  font-size: 2vmin;
  width: 30%;
  font-size: 100%;
  overflow: hidden;
}
.introduction-date {
  font-size: 2vmin;
  width: 20%;
  font-size: 100%;
  overflow: hidden;
}
.introduction-pass {
  width: 20%;
  font-size: 100%;
}
.introduction-del-up {
  width: 10%;
  font-size: 100%;
  overflow: hidden;
}

th,
td {
  color: black;
  padding-top: 10px;
  padding-bottom: 10px;
  border: 1px solid #eee;
  border-collapse: collapse;
}
.introduction-thead th {
  background: #edbe68da;
}
.question-thead th {
  background: #f7b66d;
}
.introduction-table-title {
  padding-left: 8%;
  width: 100%;
  height: 15%;
  text-align: left;
}
.introduction-table-title h3 {
  margin-bottom: 10%;
  font-size: 150%;
}
.introduction-table {
  height: 50%;
  /* background: blue; */
}
.question-table-wrap {
  table-layout: fixed;
  border-collapse: collapse;
  border-bottom: 2px solid #edbe68da;
  border-radius: 5px;
  margin: auto;
  width: 1200px;
  background: white;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1), 0px 10px 20px rgba(0, 0, 0, 0.05),
    0px 20px 20px rgba(0, 0, 0, 0.05), 0px 30px 20px rgba(0, 0, 0, 0.05);
}
.question-table-wrap td {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.introduction-table-wrap {
  border-collapse: collapse;
  border-bottom: 2px solid #edbe68da;
  border-radius: 5px;
  margin: auto;
  width: 1200px;
  background: white;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1), 0px 10px 20px rgba(0, 0, 0, 0.05),
    0px 20px 20px rgba(0, 0, 0, 0.05), 0px 30px 20px rgba(0, 0, 0, 0.05);
  /* height: 80%; */
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>
