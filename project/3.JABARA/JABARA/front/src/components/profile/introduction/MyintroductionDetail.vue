<template>
  <div class="intro-Modal">
    <div class="form-box">
      <div class="form-title">
        <div class="two-button">
          <button class="i-cancle-button no-css-btn" @click="cancleDetail">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
          <button class="i-submit-button no-css-btn" v-if="isModify" @click="modifyResume">
            <i style="color:black;" class="far fa-save fa-2x"></i>
          </button>
        </div>
        <div class="i-title">
          <h3>{{myTitle}}</h3>
        </div>
      </div>
      <div class="i-fixed">
        <div class="pass-enterprise">
          <div class="i-pass">
            <select name="pass" v-model="resumePass" :disabled="!isModify">
              <option disabled value>합격 여부</option>
              <option>미정</option>
              <option>합격</option>
              <option>불합격</option>
            </select>
          </div>
          <div class="i-enterprise">
            <input type="text" placeholder="기업명" v-model="companyName" :disabled="!isModify" />
          </div>
          <div v-if="isModify" class="insert-btn">
            <button @click="saveNow">내용 저장</button>
          </div>
          <div v-else class="insert-btn">
            <button @click="makeDetailForm">수정 하기</button>
          </div>
        </div>

        <div class="select-title" :style="{display:true}">
          <div class="select-option">
            <select v-model="selected">
              <option disabled value="문항번호">문항 번호</option>
              <option
                v-for="(select,index) in selectNum"
                :key="index"
                :value="select"
              >{{select+1}}번 문항</option>
              <!-- <option value="questionNumber">{{questionNumber}}번 문항</option> -->
            </select>
          </div>
          <div class="question-title">
            <input type="text" placeholder="자소서 문항" v-model="resumeQ" :disabled="!isModify" />
          </div>
          <div class="Qadd-del-btn">
            <div class="Qadd-btn">
              <button @click="addresumeQuestion" :disabled="!isModify">
                <i style="color: white;" class="fas fa-plus"></i>
              </button>
            </div>
            <div class="Qdel-btn">
              <button @click="popList" :disabled="!isModify">
                <i style="color: white;" class="fas fa-minus"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="i-scroll" :style="{display:true}">
        <div class="i-input">
          <textarea
            v-model="resumeA"
            @input="evt=>resumeA=evt.target.value"
            type="text"
            id="i-name"
            :disabled="!isModify"
          ></textarea>
        </div>
      </div>
      <div class="textcount" :style="{display:Tdisplay}">
        <p>글자 수: {{resumeA.length}}자</p>
      </div>
    </div>
  </div>
</template>

<script>
import IntroductionApi from "@/apis/introductionAPI";
// import { error } from "jquery";
export default {
  name: "MyintroModal",
  props: {
    IDResume: Object
  },
  data() {
    return {
      myTitle: "자기소개서 상세",
      selectdisable: false,
      submitResume: false,
      btnDisabled: false,
      Nodisabled: "",
      Tdisplay: "none",
      Idisplay: "none",
      seloption: 1,
      // api data (resume)
      resumePass: "미정",
      companyName: "",
      resumeNumber: -1,
      // api data(resume detail)
      resumeA: "",
      questionNumber: 0,
      resumeQ: "",

      tempData: {}, // to parents
      selectNum: [],
      selected: 0,
      //detail
      isModify: false,
      listFromParents: [],
      prevSelected: -1,
      isPoping: false,
      cascadeAddCnt: 0,
      cascadeWatchCnt: 0,
      cascadeFlag: false
    };
  },
  created() {
    console.log("Detail created");
    console.log("##########Detail data", this.IDResume);
    let data = {
      resumeNumber: this.IDResume.RESUME_NUMBER,
      userId: this.$session.get("loginUserId")
    };
    IntroductionApi.selectIntroductionQA(
      data,
      res => {
        console.log("DETAIL data list : ", res);
        this.listFromParents = res.data;

        if (this.listFromParents.length != 0) {
          this.resumePass = this.IDResume.RESUME_PASS;
          this.companyName = this.IDResume.COMPANY_NAME;
          for (let i = 0; i < this.listFromParents.length; i++) {
            this.selectNum.push(i);
          }

          this.resumeQ = this.listFromParents[this.selected].RESUME_Q;
          this.resumeA = this.listFromParents[this.selected].RESUME_A;
        }
      },
      error => {
        console.log(error);
      }
    );
  },
  watch: {
    selected: function(newVal, oldVal) {
      // old:now new:target
      if (this.cascadeAddCnt != 0) {
        this.cascadeWatchCnt++;
      }
      if (this.cascadeAddCnt < this.cascadeWatchCnt) {
        this.cascadeAddCnt = 0;
        this.cascadeWatchCnt = 0;
      }
      console.log("oldval:", oldVal);
      console.log("newval:", newVal);
      if (this.isPoping) {
        // pop할때 원치않는 데이터추가막기
        console.log("pop->watch");
        this.isPoping = false;
      } else {
        console.log("targetlist", this.listFromParents[newVal]);
        console.log("nowgetlist", this.listFromParents[newVal]);
        if (this.listFromParents[newVal]) {
          if (!this.isModify) {
            this.resumeQ = this.listFromParents[newVal].RESUME_Q;
            this.resumeA = this.listFromParents[newVal].RESUME_A;
          } else {
            // 3->1 1->3 (둘다 이미 push되있을때 이전값 새로수정)
            if (this.listFromParents[oldVal]) {
              console.log("#");
              this.listFromParents[oldVal].RESUME_Q = this.resumeQ;
              this.listFromParents[oldVal].RESUME_A = this.resumeA;
            } else {
              // 타겟이 있는데 내값이 없음
              // 제일 최신(old)은 아직 push 안됬을때(새로 만들고 전으로돌아갈때)

              console.log("##");
              let data = {
                RESUME_PASS: this.resumePass,
                COMPANY_NAME: this.companyName,
                RESUME_A: this.resumeA,
                RESUME_Q: this.resumeQ,
                RESUME_NUMBER: this.resumeNumber,
                QUESTION_NUMBER: -1,
                USER_ID: this.$session.get("loginUserId")
              };
              this.listFromParents.push(data); //////// 814 여기부터
              // this.selectNum.push(this.listFromParents.length);
            }

            this.resumeQ = this.listFromParents[newVal].RESUME_Q;
            this.resumeA = this.listFromParents[newVal].RESUME_A;
            console.log("modWacher :", this.listFromParents);
            console.log(oldVal);
          }
        } else {
          // 타겟이 없음
          // 1-> 3 3은아직 push안됬을때
          if (this.listFromParents[oldVal]) {
            // 1값 변경하고3으로 넘어가기
            console.log("###");
            this.listFromParents[oldVal].RESUME_Q = this.resumeQ;
            this.listFromParents[oldVal].RESUME_A = this.resumeA;
          } else {
            // 타겟도 없는데 나도 아직 저장이 안됬어
            console.log("####");
            let data = {
              RESUME_PASS: this.resumePass,
              COMPANY_NAME: this.companyName,
              RESUME_A: this.resumeA,
              RESUME_Q: this.resumeQ,
              RESUME_NUMBER: this.resumeNumber,
              QUESTION_NUMBER: -1,
              USER_ID: this.$session.get("loginUserId")
            };
            this.listFromParents.push(data); //////// 81
            //   this.selectNum.push(this.listFromParents.length);
          }
          this.resumeQ = "";
          this.resumeA = "";
        }
      }
    }
  },
  methods: {
    saveNow() {
      // console.log("##", this.selected);
      console.log(this.listFromParents.length);
      console.log(this.selected);
      if (this.listFromParents.length <= this.selected) {
        let data = {
          RESUME_A: this.resumeA,
          RESUME_Q: this.resumeQ,
          RESUME_NUMBER: this.resumeNumber,
          QUESTION_NUMBER: this.selected + 1,
          USER_ID: this.$session.get("loginUserId")
        };
        this.listFromParents.push(data);
        alert("현재 작성중인 내용이 추가되었습니다.");
      } else {
        if (
          this.listFromParents[this.selected].RESUME_Q != this.resumeQ ||
          this.listFromParents[this.selected].RESUME_A != this.resumeA
        ) {
          let data = {
            RESUME_A: this.resumeA,
            RESUME_Q: this.resumeQ,
            RESUME_NUMBER: this.resumeNumber,
            QUESTION_NUMBER: this.selected + 1,
            USER_ID: this.$session.get("loginUserId")
          };
          this.listFromParents.splice(this.selected, 1, data);
          alert("현재 작성중인 내용이 저장되었습니다.");
        }
      }
    },
    // detail
    modifyResume() {
      // if (
      //   this.selected == this.listFromParents.length &&
      //   (this.resumeQ || this.resumeA)
      // ) {
      //   let num = this.listFromParents.length + 1;
      //   if (
      //     confirm(
      //       "마지막문항(" +
      //         num +
      //         "번째)이 저장되지 않았습니다. 저장 하시겠습니까?"
      //     ) == true
      //   ) {
      //     let data = {
      //       RESUME_PASS: this.resumePass,
      //       COMPANY_NAME: this.companyName,
      //       RESUME_A: this.resumeA,
      //       RESUME_Q: this.resumeQ,
      //       RESUME_NUMBER: this.resumeNumber,
      //       QUESTION_NUMBER: -1,
      //       USER_ID: this.$session.get("loginUserId")
      //     };
      //     this.listFromParents.push(data); //////// 814 여기부터
      //     this.selectNum.push(this.listFromParents.length);

      //     this.selected = this.listFromParents.length;
      //   }
      // }
      this.saveNow();
      let data = {
        resumeNumber: this.IDResume.RESUME_NUMBER,
        userId: this.$session.get("loginUserId")
      };
      IntroductionApi.deleteResume(
        data,
        res => {
          console.log("deleteResume", res);
          if (res.data == "success") {
            let data = {
              resumePass: this.resumePass,
              companyName: this.companyName,
              userId: this.$session.get("loginUserId"),
              resumeNumber: this.IDResume.RESUME_NUMBER
            };
            IntroductionApi.requestInsertIntroduction(
              data,
              res => {
                console.log("insertResume", res);
                if (res.data != 0) {
                  let tempData = {
                    RESUME_NUMBER: res.data,
                    COMPANY_NAME: this.companyName,
                    RESUME_DATE: this.resumeDate,
                    RESUME_PASS: this.resumePass
                  };
                  console.log("intro Detail mod", tempData);
                  this.resumeNumber = res.data;
                  console.log("!!!!!list", this.listFromParents);
                  for (let i = 0; i < this.listFromParents.length; i++) {
                    this.listFromParents[i].QUESTION_NUMBER = i + 1;
                    this.listFromParents[i].RESUME_NUMBER = res.data;
                    let DetailData = {
                      resumeNumber: this.listFromParents[i].RESUME_NUMBER,
                      questionNumber: this.listFromParents[i].QUESTION_NUMBER,
                      resumeQ: this.listFromParents[i].RESUME_Q,
                      resumeA: this.listFromParents[i].RESUME_A,
                      userId: this.$session.get("loginUserId")
                    };
                    console.log("$$$$수정시 준비된 데이터", DetailData);
                    IntroductionApi.requestInsertIntroductionQuestion(
                      DetailData,
                      res => {
                        console.log("InsertDetail", res);
                      },
                      error => {
                        console.log(error);
                      }
                    );
                  }
                  alert("수정 되었습니다.");
                  this.$emit("modifyIntroList", tempData);
                  // alert(
                  //   "작성 시작 후, 합격 여부와 기업명 수정은 리스트에서 가능합니다:)"
                  // );
                }
              },
              error => {
                console.log(error);
              }
            );
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    popList() {
      console.log("POPOPOPOPOP");
      if (this.selectNum.length != this.listFromParents.length) {
        let data = {
          RESUME_PASS: this.resumePass,
          COMPANY_NAME: this.companyName,
          RESUME_A: this.resumeA,
          RESUME_Q: this.resumeQ,
          RESUME_NUMBER: this.resumeNumber,
          QUESTION_NUMBER: -1,
          USER_ID: this.$session.get("loginUserId")
        };
        this.listFromParents.push(data);
        // this.selectNum.push(this.listFromParents.length);
        this.prevSelected = this.selected;
        this.selected = this.listFromParents.length;
      }
      if (this.listFromParents.length > 0) {
        this.isPoping = true;
        this.listFromParents.pop();
        this.selectNum.pop();
        console.log("POPNUM", this.selectNum);
        console.log("POP", this.listFromParents);
        this.selected = this.listFromParents.length - 1;
        console.log("가리키는곳 ", this.selected);
        if (this.listFromParents[this.selected]) {
          this.resumeQ = this.listFromParents[this.selected].RESUME_Q;
          this.resumeA = this.listFromParents[this.selected].RESUME_A;
        } else {
          this.resumeQ = "";
          this.resumeA = "";
        }
        let num = this.listFromParents.length + 1;
        alert("마지막문항(" + num + "번째)이 삭제되었습니다");

        console.log(this.listFromParents);
      } else {
        alert("등록된 문항이없습니다.");
      }
    },
    addresumeQuestion() {
      this.cascadeAddCnt++;
      if (
        this.cascadeAddCnt >= 2 &&
        this.cascadeAddCnt - this.cascadeWatchCnt == 1
      ) {
        // this.cascadeWatchCnt = 0;
        // this.cascadeAddCnt = 0;
        this.cascadeFlag = true;
      }
      if (this.cascadeFlag) {
        this.cascadeFlag = false;
        this.selected = this.listFromParents.length + 1;
      } else {
        this.selected = this.listFromParents.length;
      }

      this.selectNum.push(this.selected);
      this.prevSelected = this.selected;
    },
    cancleDetail() {
      this.addFisrt = true;
      this.$emit("modifyIntroList");
    },
    makeDetailForm() {
      this.myTitle = "자기소개서 수정";
      this.isModify = true;
    }
  }
};
</script>

<style scoped>
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

.i-fixed {
  width: 90%;
  height: 30%;
  margin: 50px auto 0 auto;
}
.i-pass {
  width: 18%;
  margin-right: 10px;
  height: 100%;
}
.i-pass > select {
  width: 100%;
  padding: 12px;
  text-align: center;
  border-radius: 5px;
}
.i-enterprise {
  width: 70%;
  height: 100%;
}
.i-enterprise > input {
  width: 100%;
  border-radius: 5px;
}
.insert-btn {
  width: 20%;
  height: 100%;
  margin-left: 10px;
}
.insert-btn button {
  width: 95%;
  padding: 12px;
  background: #edbf68;
  border-radius: 5px;
  color: rgb(248, 248, 248);
}
.pass-enterprise {
  margin: 0;
  display: flex;
  flex-direction: row;
  height: 50%;
  width: 100%;
}
.select-title {
  margin: 0;
  display: flex;
  flex-direction: row;
  height: 50%;
  width: 100%;
}
.select-option {
  width: 18%;
  margin-right: 10px;
  height: 100%;
}
.select-option > select {
  width: 100%;
  padding: 12px;
  text-align: center;
  border-radius: 5px;
}

.question-title {
  width: 70%;
  height: 100%;
}
.question-title > input {
  width: 100%;
  border-radius: 5px;
}
.Qadd-del-btn {
  display: flex;
  flex-direction: row;
  margin-left: 10px;
  width: 20%;
  height: 100%;
  /* padding: 12px; */
}
.Qadd-btn {
  width: 45%;
  margin-right: 10px;
}
.Qadd-btn button {
  width: 100%;
  padding: 12px;
  background: #edbf68;
  border-radius: 5px;
  color: rgb(248, 248, 248);
}
.Qdel-btn {
  width: 45%;
}
.Qdel-btn button {
  width: 100%;
  padding: 12px;
  background: #ed7568;
  border-radius: 5px;
  color: rgb(248, 248, 248);
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
  width: 70%;
  height: 90%;
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
  height: 60%;
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
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.i-scroll textarea {
  border: solid 1px rgb(212, 212, 212);
  border-radius: 5px;
  width: 100%;
  height: 400px;
  padding: 1%;
}
.add-input {
  margin: 5% 0;
  text-align: center;
  border-radius: 5px;
}
.i-submit-button {
  float: right;
  margin: 0;
  width: 7%;
  line-height: 52.8px;
  color: black !important;
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