<template>
  <div class="intro-Modal">
    <div class="form-box">
      <div class="form-title">
        <div class="two-button">
          <button class="i-cancle-button no-css-btn" @click="cancleWrite">
            <i style="color:black;" class="fas fa-times fa-2x"></i>
          </button>
          <button
            class="i-submit-button no-css-btn"
            :disabled="!btnDisabled"
            @click="submitQuestion"
          >
            <i style="color:black;" class="far fa-save fa-2x"></i>
          </button>
        </div>
        <div class="i-title">
          <h3>자기소개서 작성</h3>
        </div>
      </div>
      <div class="i-fixed">
        <div class="pass-enterprise">
          <div class="i-pass">
            <select name="pass" v-model="resumePass" :disabled="selectdisable">
              <option disabled value>합격 여부</option>
              <option>미정</option>
              <option>합격</option>
              <option>불합격</option>
            </select>
          </div>
          <div class="i-enterprise">
            <input type="text" placeholder="기업명" v-model="companyName" :readonly="submitResume" />
          </div>

          <div v-if="btnDisabled" class="insert-btn">
            <button @click="saveNow">내용 저장</button>
          </div>
          <div v-else class="insert-btn">
            <button @click="comalertMessage">작성 시작</button>
          </div>
        </div>
        <div class="non-disable" :style="{display:Nodisabled}">
          <h2>
            합격 여부와 기업명 작성 후,
            <br />작성 시작 버튼을 클릭해주세요
          </h2>
          <p>작성 시작 버튼 클릭 후, 합격여부와 기업명은 테이블에서 수정 가능합니다</p>
        </div>
        <div class="select-title" :style="{display:Idisplay}">
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
            <input type="text" placeholder="자소서 문항" v-model="resumeQ" />
          </div>
          <div class="Qadd-del-btn">
            <div class="Qadd-btn">
              <button @click="addresumeQuestion">
                <i style="color: white;" class="fas fa-plus"></i>
              </button>
            </div>
            <div class="Qdel-btn">
              <button @click="popList">
                <i style="color: white;" class="fas fa-minus"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="i-scroll" :style="{display:Tdisplay}">
        <div class="i-input">
          <textarea
            v-model="resumeA"
            @input="evt=>resumeA=evt.target.value"
            type="text"
            id="i-name"
            @keyup="suggestTerms"
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
export default {
  name: "MyintroModal",
  props: {
    Ishow: Boolean
  },
  data() {
    return {
      submitFin: false,

      selectdisable: false,
      submitResume: false,
      btnDisabled: false,
      Nodisabled: "",
      Tdisplay: "none",
      Idisplay: "none",
      openModal: this.Ishow,
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
      resumeDetailList: [],
      selectNum: [0],
      selected: 0,
      prevSelected: 0,
      isPoping: false,
      cascadeAddCnt: 0,
      cascadeWatchCnt: 0,
      cascadeFlag: false
    };
  },
  created() {
    this.resumeDetailList = [];
  },
  watch: {
    selected: function(newVal, oldVal) {
      console.log("list:", this.resumeDetailList);
      console.log("numlist", this.selectNum);
      if (this.cascadeAddCnt != 0) {
        this.cascadeWatchCnt++;
      }
      if (this.cascadeAddCnt < this.cascadeWatchCnt) {
        this.cascadeAddCnt = 0;
        this.cascadeWatchCnt = 0;
      }
      console.log("oldVal:", oldVal);
      console.log("newVal:", newVal);
      if (this.isPoping) {
        // pop할때 원치않는 데이터추가막기
        console.log("pop->watch");
        this.isPoping = false;
      } else {
        console.log("targetlist", this.resumeDetailList[newVal]);
        console.log("nowgetlist", this.resumeDetailList[newVal]);
        if (this.resumeDetailList[newVal]) {
          if (this.resumeDetailList[oldVal]) {
            console.log("#");
            this.resumeDetailList[oldVal].resumeQ = this.resumeQ;
            this.resumeDetailList[oldVal].resumeA = this.resumeA;
          } else {
            console.log("##");
            let data = {
              resumeA: this.resumeA,
              resumeQ: this.resumeQ,
              resumeNumber: this.resumeNumber
            };
            this.resumeDetailList.push(data); //////// 814 여기부터
          }

          this.resumeQ = this.resumeDetailList[newVal].resumeQ;
          this.resumeA = this.resumeDetailList[newVal].resumeA;
          console.log("modWacher :", this.resumeDetailList);
          console.log(oldVal);
        } else {
          // 타겟이 없음
          // 1-> 3 3은아직 push안됬을때
          if (this.resumeDetailList[oldVal]) {
            // 1값 변경하고3으로 넘어가기
            console.log("###");
            this.resumeDetailList[oldVal].resumeQ = this.resumeQ;
            this.resumeDetailList[oldVal].resumeA = this.resumeA;
          } else {
            // 타겟도 없는데 나도 아직 저장이 안됬어
            console.log("####");
            let data = {
              resumeA: this.resumeA,
              resumeQ: this.resumeQ,
              resumeNumber: this.resumeNumber
            };
            this.resumeDetailList.push(data); //////// 81
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
      console.log("sn");
      console.log("선택", this.selected);
      // console.log("데이터", this.resumeDetailList.length);
      console.log("최대 선택", this.selectNum[this.selectNum.length - 1]);
      // 선택 = 최대선택 -> push
      // 선택 < 최대선택 -> 저장
      console.log("selected", this.selected);
      console.log("selectNum", this.selectNum[this.selectNum.length - 1]);
      if (
        // !this.submitFin &&
        this.selected >= this.selectNum[this.selectNum.length - 1]
      ) {
        if (this.submitFin) return;
        // if (this.submitFin) return;
        let data = {
          resumeA: this.resumeA,
          resumeQ: this.resumeQ,
          resumeNumber: this.resumeNumber
        };
        this.resumeDetailList.push(data);
        alert("현재 작성중인 내용이 추가되었습니다.");
      } else {
        if (
          this.resumeDetailList[this.selected].resumeQ != this.resumeQ ||
          this.resumeDetailList[this.selected].resumeA != this.resumeA
        ) {
          let data = {
            resumeA: this.resumeA,
            resumeQ: this.resumeQ,
            resumeNumber: this.resumeNumber
          };
          this.resumeDetailList.splice(this.selected, 1, data);
          alert("현재 작성중인 내용이 저장되었습니다.");
        }
      }
      console.log("!!!!!!!", this.resumeDetailList);
    },

    popList() {
      console.log("POPOPOPOPOP");
      if (this.selectNum.length != this.resumeDetailList.length) {
        let data = {
          resumeA: this.resumeA,
          resumeQ: this.resumeQ,
          resumeNumber: this.resumeNumber
        };
        this.resumeDetailList.push(data);
        // this.selectNum.push(this.listFromParents.length);
        this.prevSelected = this.selected;
        this.selected = this.resumeDetailList.length;
      }

      if (this.resumeDetailList.length > 0) {
        alert(
          "마지막문항(" +
            this.resumeDetailList.length +
            "번째)이 삭제되었습니다"
        );

        this.isPoping = true;
        this.resumeDetailList.pop();
        this.selectNum.pop();
        console.log("POP", this.resumeDetailList);
        this.selected = this.resumeDetailList.length - 1;
        console.log("가리키는곳 ", this.selected);
        console.log(this.resumeDetailList[this.selected]);
        if (this.resumeDetailList[this.selected]) {
          this.resumeQ = this.resumeDetailList[this.selected].resumeQ;
          this.resumeA = this.resumeDetailList[this.selected].resumeA;
        } else {
          this.resumeQ = "";
          this.resumeA = "";
        }

        console.log(this.resumeDetailList);
      } else {
        alert("등록된 문항이없습니다.");
      }
    },
    submitQuestion() {
      console.log("#####제출 list Data", this.resumeDetailList);
      this.submitFin = true;
      // if (
      //   this.resumeDetailList.length != this.selectNum.length &&
      //   this.selected == this.selectNum[this.selectNum.length - 1] &&
      //   (this.resumeQ || this.resumeA)
      // ) {
      //   if (
      //     confirm(
      //       "마지막문항(" +
      //         this.resumeQ +
      //         ")이 저장되지 않았습니다. 저장 하시겠습니까?"
      //     ) == true
      //   ) {
      //     let data = {
      //       resumeA: this.resumeA,
      //       resumeQ: this.resumeQ,
      //       resumeNumber: this.resumeNumber
      //     };
      //     this.resumeDetailList.push(data);
      //   }
      // }
      this.saveNow();
      this.submitIinfo();
      // for (let i = 0; i < this.resumeDetailList.length; i++) {
      //   this.resumeDetailList[i].questionNumber = i + 1;
      //   this.resumeDetailList[i].resumeNumber = this.resumeNumber;
      //   IntroductionApi.requestInsertIntroductionQuestion(
      //     this.resumeDetailList[i],
      //     res => {
      //       console.log(res);
      //     },
      //     error => {
      //       console.log(error);
      //     }
      //   );
      // }
      // this.cancleWrite();
      this.submitFin = false;
      alert("자기소개서가 등록되었습니다.");
    },
    suggestTerms() {
      console.log("resumeA length = " + this.resumeA.length);
    },

    changeflag() {
      console.log(this.Tdisplay);
      this.Idisplay = "";
      this.Tdisplay = "";
      console.log(this.Tdisplay);
      this.Nodisabled = "none";
      alert("작성 시작 후, 합격 여부와 기업명 수정은 리스트에서 가능합니다:)");
      // this.selectdisable = true;
      this.btnDisabled = true;
      // this.submitResume = true;
      // this.submitIinfo();
    },
    comalertMessage() {
      if (!this.companyName) {
        alert("기업명을 작성해주세요");
      } else {
        this.changeflag();
      }
    },
    cancleWrite() {
      // this.openModal = !this.openModal;
      // this.$emit("modal-show", this.openModal);
      this.$emit("pushIntroList");
    },
    addresumeQuestion() {
      console.log("과연?");
      this.cascadeAddCnt++;
      if (!this.resumeDetailList.length) {
        console.log("입구컷?", this.selected);
        let data = {
          resumeA: this.resumeA,
          resumeQ: this.resumeQ,
          resumeNumber: this.resumeNumber
        };
        this.resumeDetailList.push(data);
        this.selected = this.resumeDetailList.length;
        console.log("입구컷?2", this.selected);
        this.selectNum.push(this.selected);
      } else {
        // this.cascadeAddCnt++;
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
          this.selected = this.resumeDetailList.length + 1;
        } else {
          this.selected = this.resumeDetailList.length;
        }
        this.selectNum.push(this.selected);
        this.prevSelected = this.selected;
      }
    },
    submitIinfo() {
      let data = {
        resumePass: this.resumePass,
        companyName: this.companyName,
        userId: this.$session.get("loginUserId")
      };
      console.log(data);

      IntroductionApi.requestInsertIntroduction(
        data,
        // this.testCallBack(res,ITempData),
        res => {
          if (res.data != 0) {
            let tempData = {
              RESUME_NUMBER: res.data,
              COMPANY_NAME: this.companyName,
              RESUME_DATE: this.resumeDate,
              RESUME_PASS: this.resumePass
            };
            console.log("intro modal", tempData);
            this.resumeNumber = res.data;
            // this.selectdisable = true;
            // this.btnDisabled = true;
            // this.submitResume = true;
            for (let i = 0; i < this.resumeDetailList.length; i++) {
              // console.log("OHHHHHHHHHHHHHHHHH! ", i + 1);
              this.resumeDetailList[i].questionNumber = i + 1;

              this.resumeDetailList[i].resumeNumber = res.data;
              this.resumeDetailList[i].userId = this.$session.get(
                "loginUserId"
              );
              console.log("준비된 데이터는", this.resumeDetailList[i]);
              IntroductionApi.requestInsertIntroductionQuestion(
                this.resumeDetailList[i],
                res => {
                  console.log(res);
                },
                error => {
                  console.log(error);
                }
              );
            }
            this.$emit("pushIntroList", tempData);
            // alert(
            //   "작성 시작 후, 합격 여부와 기업명 수정은 리스트에서 가능합니다:)"
            // );
          }
        },
        error => {
          console.log(error);
        }
      );

      // this.openModal = !this.openModal;
      // this.$emit("modal-show", this.openModal);
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