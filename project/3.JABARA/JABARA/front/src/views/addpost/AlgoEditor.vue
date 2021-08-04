<template>
  <div id="algo-editor">
    <div class="title">
      <h1>Algorithm</h1>
    </div>
    <div class="solution-form">
      <select v-model="solutionBoolean" class="input-forms solution-suc-fa">
        <option disabled value>성공유무</option>
        <option>못품</option>
        <option>품</option>
      </select>
      <select v-model="solutionSite" class="input-forms site-select">
        <option disabled value>사이트 선택</option>
        <option>백준</option>
        <option>SW Expert Academy</option>
        <option>프로그래머스</option>
        <option>기타 사이트</option>
      </select>
      <input
        v-model="solutionNum"
        placeholder="문제 번호 입력"
        type="number"
        class="input-forms solution-num"
      />
      <input
        v-model="solutionTitle"
        placeholder="문제 제목 입력"
        type="text"
        class="input-forms solution-title"
      />
      <button class="create-tag-btn" @click="addSoultionTag">태그생성</button>
    </div>
    <div class="radio-group">
      <div class="row">
        <input v-model="tags" type="checkbox" id="hash" value="#해시" class="clickbox hash" />
        <label for="hash">
          <p class="p-text">해시</p>
        </label>
        <input v-model="tags" type="checkbox" id="stack-q" value="#스택큐" class="clickbox stack-q" />
        <label for="stack-q">
          <p class="p-text">스택/큐</p>
        </label>
        <input v-model="tags" type="checkbox" id="heap" value="#HEAP" class="clickbox heap" />
        <label for="heap">
          <p class="p-text">힙(Heap)</p>
        </label>
        <input v-model="tags" type="checkbox" id="sort" value="#정렬" class="sort" />
        <label for="sort">
          <p class="p-text">정렬</p>
        </label>
        <input
          v-model="tags"
          type="checkbox"
          id="comsearch"
          value="#완전탐색"
          class="clickbox comsearch"
        />
        <label for="comsearch">
          <p class="p-text">완전탐색</p>
        </label>
        <input v-model="tags" type="checkbox" id="greedy" value="#탐욕법" class="clickbox greedy" />
        <label for="greedy">
          <p class="p-text">탐욕법</p>
        </label>
      </div>
      <div class="row">
        <input
          v-model="tags"
          type="checkbox"
          id="programming"
          value="#동적계획법"
          class="clickbox programming"
        />
        <label for="programming">
          <p class="p-text">동적계획법</p>
        </label>
        <input v-model="tags" type="checkbox" id="dfs-bfs" value="#DFSBFS" class="clickbox dfs-bfs" />
        <label for="dfs-bfs">
          <p class="p-text">DFS/BFS</p>
        </label>
        <input
          v-model="tags"
          type="checkbox"
          id="div-search"
          value="#이분탐색"
          class="clickbox div-search"
        />
        <label for="div-search">
          <p class="p-text">이분탐색</p>
        </label>
        <input v-model="tags" type="checkbox" id="graph" value="#그래프이론" class="clickbox graph" />
        <label for="graph">
          <p class="p-text">그래프 이론</p>
        </label>
        <input
          v-model="tags"
          type="checkbox"
          id="simulation"
          value="#시뮬레이션"
          class="clickbox simulation"
        />
        <label for="simulation">
          <p class="p-text">시뮬레이션</p>
        </label>
        <input v-model="tags" type="checkbox" id="guitar" value="#기타문제" class="clickbox guitar" />
        <label for="guitar">
          <p class="p-text">기타문제</p>
        </label>
      </div>
      <!-- <h5>current value : {{algochecked}}</h5> -->
    </div>
    <div id="text-box">
      <textarea v-model="message" placeholder="내용을 입력하세요"></textarea>
      <!-- <h4>확인: {{message}}</h4> -->
    </div>
    <Algoform id="myeditor" ref="algoEditor" />
    <div class="tag-input">
      <input
        @keyup.space="addNewTag"
        @keyup.enter="addNewTag"
        type="text"
        placeholder="추가 태그 내용"
        v-model="newTag"
      />
    </div>
    <div class="auto-tags">
      <ul>
        <li v-for="(tag,index) in tags" :key="tag.id" class="tag-list">
          {{tag}}
          <button @click="deleteTag(index)">&#10006;</button>
        </li>
        <!-- <li v-for="(check,index) in algochecked" :key="check" class='tag-list'>
        #{{check}}  <button @click="deleteAlgoTag(index)">&#10006;</button>
        </li>-->
      </ul>
    </div>
    <button @click="postCode" class="post-button">
      <p>Upload</p>
    </button>
  </div>
</template>

<script>
import Algoform from "@/components/form/Algoform.vue";
import insertPostAPI from "@/apis/insertPostAPI.js";
import InsertTagApi from "@/apis/tagAPI.js";
export default {
  name: "AlgoEditor",
  components: {
    Algoform
  },
  data() {
    return {
      addSoultionTagFlag: false,
      // titleTag: "",
      tags: [],
      prevTitleTags: [],
      // algochecked: [],
      titletag: "",
      message: "",
      newTag: "",
      solutionBoolean: "",
      solutionSite: "",
      solutionTitle: "",
      solutionNum: "",
      boxing: false,
      category: "algo"
    };
  },
  methods: {
    postCode() {
      console.log(this.addSoultionTagFlag);
      if (!this.addSoultionTagFlag) {
        alert("상단의 알고리즘 문제 태그생성 버튼을 눌러주세요");
        return;
      }
      console.log(this.tags);
      let isSolved;
      let postAlgoTitle = this.titletag;
      let postCategory = this.category;
      let postCodeContent = this.$refs.algoEditor.createAction();
      let postSnsContent = this.message;
      let userId = this.$session.get("loginUserId");
      if (this.solutionBoolean == "못품") {
        isSolved = 0;
      } else {
        isSolved = 1;
      }
      console.log(443);
      console.log(this.solutionBoolean);
      console.log(isSolved);
      let data = {
        isSolved,
        postAlgoTitle,
        postCategory,
        postCodeContent,
        postSnsContent,
        userId
      };

      insertPostAPI.insertAlgoPost(
        data,
        res => {
          if (res.data != "fail") {
            let tagStr = "";
            this.tags.forEach(element => {
              tagStr += element;
            });
            let tag = {
              category: postCategory,
              postNumber: res.data + 0,
              tagStr
            };
            let data = {
              tag,
              userId
            };
            InsertTagApi.insertPostTag(
              data,
              res => {
                if (res.status == 200) {
                  alert("등록이 성공했습니다");
                  this.$router.push("/jobara");
                } else {
                  alert("ERROR BY INSERT_ALGOPOST_TAG(서버통신)");
                }
              },
              error => {
                console.log(error);
              }
            );
            console.log(tag);
            // console.log(res);
            // this.$router.push("/jobara");
          }
          console.log(res);
        },
        error => {
          console.log(error);
        }
      );

      console.log(data);
    },
    colorChange() {
      console.log("color change");
      this.color = "gold";
    },
    addSoultionTag() {
      let err = true;
      let msg = "";
      !this.solutionBoolean &&
        ((msg = "문제풀이 성공유무를 선택 해주세요"), (err = false));
      err &&
        !this.solutionSite &&
        ((msg = "문제출제 사이트를 입력해주세요"), (err = false));
      err &&
        !this.solutionTitle &&
        ((msg = "문제제목을 입력해주세요"), (err = false));
      err &&
        this.solutionNum != null &&
        this.isNumber(this.solutionNum) == false &&
        ((msg = "문제번호 숫자를 입력해주세요"), (err = false));

      if (!err) {
        alert(msg);
        return;
      }
      // console.log(442);
      // console.log(this.solutionBoolean);
      this.addSoultionTagFlag = true;
      // console.log(this.addSoultionTagFlag);
      // console.log(this.tags);
      // console.log(this.solutionNum)
      let string = "";
      if (this.solutionBoolean.length - 1 >= 0) {
        this.tags.push("#" + this.solutionBoolean);
      }
      if (this.solutionSite.length - 1 >= 0) {
        this.tags.push("#" + this.solutionSite);
        string += this.solutionSite;
      }
      if (this.solutionNum.length - 1 >= 0) {
        this.tags.push("#" + this.solutionNum);
        string += "_";
        string += this.solutionNum;
      }
      if (this.solutionTitle.length - 1 >= 0) {
        this.tags.push("#" + this.solutionTitle);
        string += "_";
        string += this.solutionTitle;
      }

      if (this.prevTitleTags.length != 0) {
        alert("타이틀 태그가 변경되었습니다.");

        // console.log("tempTitleTags");
        // console.log(tempTitleTags);
        let tempTags = this.tags;
        this.prevTitleTags.forEach(function(ele) {
          // 이전 tag들과 새로 만든 태그가 겹치는게 있는지 확인하고 삭제
          // console.log("여기를 채크합시다.");
          // console.log(ele);
          // console.log(tempTitleTags.indexOf(ele));
          if (tempTags.indexOf(ele) != -1) {
            tempTags.splice(ele, 1);
          }
        });
        this.tags = tempTags;
        // console.log(this.tags);

        this.prevTitleTags = []; // 초기화
      }

      if (this.solutionBoolean.length - 1 >= 0) {
        this.prevTitleTags.push("#" + this.solutionBoolean);
      }
      if (this.solutionSite.length - 1 >= 0) {
        this.prevTitleTags.push("#" + this.solutionSite);
      }
      if (this.solutionNum.length - 1 >= 0) {
        this.prevTitleTags.push("#" + this.solutionNum);
      }
      if (this.solutionTitle.length - 1 >= 0) {
        this.prevTitleTags.push("#" + this.solutionTitle);
      }

      this.tags = Array.from(new Set(this.tags));
      // console.log(this.tags);
      this.titletag = string;
    },
    deleteTag(index) {
      this.tags.splice(index, 1);
      console.log(this.tags.index);
    },
    deleteAlgoTag(index) {
      console.log(this.algochecked);
      this.algochecked.splice(index, 1);
    },
    addNewTag() {
      let regExp = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/gi;
      if (regExp.test(this.newTag)) {
        this.newTag = "";
        alert("태그는 특수문자를 포함할수없습니다!");
        return;
      }
      this.tags.push("#" + this.newTag);
      this.tags = Array.from(new Set(this.tags));
      this.newTag = "";
    },
    isNumber(s) {
      s += ""; // 문자열로 변환
      s = s.replace(/^\s*|\s*$/g, ""); // 좌우 공백 제거
      if (s == "" || isNaN(s)) return false;
      return true;
    }
  }
};
</script>

<style scoped>
/* .row {
  display: table-row;
} */
.tag-list {
  display: inline;
  margin-right: 15px;
  /* border: 1px solid black; */
  background-color: rgba(243, 255, 131, 0.349);
  border-radius: 5px;
  padding: 4px;
}

.auto-tags {
  margin-top: 15px;
}
.auto-tags p {
  display: inline;
  margin-right: 15px;
}
.solution-form {
  width: 100%;
  display: inline-block;
  margin-bottom: 15px;
}
.input-forms {
  width: 22.5%;
  border: 2px solid white;
  border-radius: 4px;
}
.create-tag-btn {
  width: 10%;
  border-radius: 4px;
  /* border: 1px solid red; */
  padding: 10px 0px;
  /* background-color: #EDBF68; */
  /* color: white; */
}
.create-tag-btn:hover {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.219);
  transition: box-shadow 400ms;
}
.create-tag-btn:focus {
  border: 2px solid #ddd;
}
.input-forms:hover {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.219);
  transition: box-shadow 400ms;
}
.input-forms:focus {
  border: 2px solid rgba(0, 0, 0, 0.178);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
  transition: box-shadow 400ms;
}
.site-select {
  padding: 14px;
  border-radius: 4px;
}
.solution-suc-fa {
  padding: 14px;
  border-radius: 4px;
}

body {
  background-color: black;
  margin-top: 120px;
}
#algo-editor {
  width: 80%;
  margin: 0 auto;
  margin-bottom: 80px;
  /* margin: 50px 20px; */
  background-color: #fff;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.15);
}
#algo-editor {
  height: 80%;
  margin-top: 130px;
}
#myeditor {
  background-color: #f7b6a459;
}
.title {
  margin-bottom: 20px;
}
h1 {
  text-align: center;
}
.title-input {
  width: 100%;
  margin-bottom: 10px;
  border-radius: 4px;
  border: 2px solid #ddd;
}
.title-input:focus {
  border: 3px solid rgb(251, 231, 253);
  box-shadow: 0 1px 2px rgba(255, 1, 221, 0.15);
  transition: box-shadow 400ms;
}
.radio-group {
  width: 100%;
  margin-bottom: 10px;
}
.clickbox {
  margin: 0 0.25rem;
}
.row {
  display: flex;
  margin: 10px 0;
}
label {
  height: 90px;
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 1rem 1.25rem;
  border-radius: 5px;
  cursor: pointer;
  color: #444;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
  transition: box-shadow 400ms;
  text-align: center;
  flex: 6;
  display: inline-table;
}

label:hover {
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.12);
}
label > p {
  margin: 0.15rem 0;
  display: table-cell;
  vertical-align: middle;
}
input[type="radio"] {
  display: none;
}
input:checked + label {
  background: #ee8572;
  color: white;
}
#text-box {
  margin-bottom: 15px;
}
textarea {
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 100%;
  padding: 30px;
  height: 250px;
}
.tag-input {
  margin-top: 15px;
}
.tag-input > input {
  border-radius: 4px;
  border: 2px solid #ddd;
  width: 100%;
}
.post-button {
  border-radius: 4px;
  margin-top: 15px;
  width: 50%;
  font-size: 30px;
  position: relative;
  left: 50%;
  transform: translate(-50%, 0%);
  background-color: #ee8572;
}
.post-button p {
  text-align: center;
  margin: auto 0;
  font-weight: 600;
  color: white;
}
</style>