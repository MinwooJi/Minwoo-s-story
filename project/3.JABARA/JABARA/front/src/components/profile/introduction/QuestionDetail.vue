<template>
  <div class="intro-Modal">
    <div class="form-box">
      <div class="form-title">
        <div class="two-button">
          <button @click="closeDetail" class="i-cancle-button no-css-btn">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
        </div>
        <div class="i-title">
          <h3>질문 상세</h3>
        </div>
      </div>
      <div class="i-scroll">
        <div class="i-input">
          <textarea disabled v-model="questionDetail" class="question"></textarea>
        </div>
      </div>
      <div class="i-scroll">
        <div class="i-input">
          <textarea disabled v-model="answerDetail" class="answer"></textarea>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "QuestionDetail",
  props: {
    questionDetailData: Object
  },
  // 시험
  data() {
    return {
      questionDetail: this.questionDetailData.RESUME_Q,
      answerDetail: this.questionDetailData.RESUME_A
    };
  },
  methods: {
    closeDetail() {
      this.$emit("modal-close");
    }
  }
};
</script>
<style scoped>
.question {
  height: 130px;
}
.answer {
  height: 200px;
}
.textcount {
  text-align: right;
  margin: 20px 5% 0 5%;
}
.non-disable {
  display: block;
  position: relative;
  padding: 100px 0px;
}
.non-disable h2 {
  text-align: center;
}
.non-disable p {
  text-align: center;
}
/* 기업, 문항 넣는 부분 */

.question-title {
  width: 70%;
  height: 100%;
}
.question-title > input {
  width: 100%;
  border-radius: 5px;
}

/* 전체화면영역 */
.intro-Modal {
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
  display: flex;
  flex-direction: column;
}
.form-box .form-title {
  /* height: 10%; */
  margin: 4% 4% 0 4%;
  text-align: center;
  display: block;
}
.form-title .i-title {
  height: 10%;
  width: 100%;
  text-align: center;
}

.form-title .two-button {
  margin-top: 0;
  width: 100%;
  /* flex이용해서 한줄에 놓음 */
  display: flex;
  flex-direction: row-reverse;
}
.i-scroll {
  margin: 20px auto 0 auto;
  width: 90%;
  /* height: 60%; */
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.i-scroll::-webkit-scrollbar {
  display: none;
}
.i-scroll textarea::-webkit-scrollbar {
  /* display: none; */
  width: 10px;
}
.i-scroll textarea::-webkit-scrollbar-thumb {
  background-color: #f7b66d80;
  border-radius: 10px;
  background-clip: padding-box;
  border: 3px solid transparent;
  height: calc(100% - 100px);
}
.i-scroll textarea::-webkit-scrollbar-track {
  background-color: rgb(172, 94, 94);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.i-scroll textarea {
  border: solid 1px rgb(212, 212, 212);
  border-radius: 5px;
  width: 100%;
  /* height: 400px; */
  padding: 1%;
}

.i-cancle-button {
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