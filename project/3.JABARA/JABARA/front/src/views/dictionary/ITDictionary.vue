<template>
  <div class="dic-wrap">
    <header>
      <h1 class="page--title">IT Dictionary</h1>

      <label @click="seachDictionary" class="header--search" for="search">
        Search
        <input
          class="dict-search"
          type="text"
          id="search"
          v-model="searchWord"
          @keyup.enter="seachDictionary"
        />
      </label>
    </header>

    <main>
      <div>
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a @click="consonant('ㄱ')" class="page-link" href="#">ㄱ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㄴ')" class="page-link" href="#">ㄴ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㄷ')" class="page-link" href="#">ㄷ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㄹ')" class="page-link" href="#">ㄹ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅁ')" class="page-link" href="#">ㅁ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅂ')" class="page-link" href="#">ㅂ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅅ')" class="page-link" href="#">ㅅ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅇ')" class="page-link" href="#">ㅇ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅈ')" class="page-link" href="#">ㅈ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅊ')" class="page-link" href="#">ㅊ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅋ')" class="page-link" href="#">ㅋ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅌ')" class="page-link" href="#">ㅌ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅍ')" class="page-link" href="#">ㅍ</a>
            </li>
            <li class="page-item">
              <a @click="consonant('ㅎ')" class="page-link" href="#">ㅎ</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="main--titles">
        <h2 class="main--title main--title-left" style="color:white;">Term</h2>
        <h2 class="main--title main--title-right" style="color:white;">Definition</h2>
      </div>
      <div class="main--content">
        <div class="left pop-scroll" style="border-right-color: #fad5d1c9;">
          <div
            v-for="(term, index) in selectedDatas"
            :key="index"
            class="hover-effect"
          >
          <p @click="showDiscription(term)" class="term">{{ term.NAME }}</p>
          </div>
        </div>
        <div class="right">
          <div v-if="selectedName" class="detail-contents">
            <p class="term term--selected">
              {{ selectedName }}({{ selectedEngName }})
            </p>
            <p class="definition" >
              {{ definition }}
            </p>
          </div>
          <div v-else class="no-data">
            <h1 style="text-align:center;">
              기술용어 사전 서비스는 우측상단<br> 
              기술용어 검색기능과초성으로 검색기능을 지원합니다.</h1>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
<script src="hangul.js" type="text/javascript"></script>
<script scoped>
import DictionaryAPI from "@/apis/dictionaryAPI";
// import { error } from "jquery";
const Hangul = require("hangul-js");
export default {
  name: "ITDictionary.vue",
  data() {
    return {
      dictionaryDatas: [],
      selectedDatas: [],
      definition: "",
      searchWord: "",
      selectedEngName: "",
      selectedName: "",
    };
  },
  created() {
    DictionaryAPI.selectDictionaryAll(
      (res) => {
        console.log(res);
        this.dictionaryDatas = res.data;
        this.selectedDatas = res.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    seachDictionary() {
      this.selectedDatas = [];
      DictionaryAPI.selectDictionaryByName(
        this.searchWord,
        (res) => {
          this.selectedDatas = res.data;
          if (this.selectedDatas.length == 0) {
            alert("일치하는 결과가 없습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
      // console.log("길이", this.selectedDatas.length);
      this.searchWord = "";
    },
    showDiscription(term) {
      this.selectedEngName = term.ENG_NAME;
      this.selectedName = term.NAME;
      this.definition = term.CONTENT;
    },
    consonant(con) {
      this.selectedDatas = [];
      // console.log(con);
      let searcher = new Hangul.Searcher(con);
      this.dictionaryDatas.forEach((element) => {
        if (Hangul.disassemble(element.NAME, true)[0][0] == con) {
          this.selectedDatas.push(element);
        }
      });
      console.log("!", this.selectedDatas);
    },
  },
};
</script>

<style scoped>

.no-data {
  margin: 180px;
}
.detail-contents {
  margin: 25px 25px 25px 0;
}
.term {
  /* padding-left: 10px; */
  padding-top: 10px;
}
p.term--selected {
  padding-left: 0;
}
.definition {
  line-height: 35px; 
}
.hover-effect {
  transition: 0.4s;
}
.hover-effect:hover {
  background-color: #fdf6eb;
  color: #eea200;
  padding: 10px 10px;
  cursor: pointer;
}
.pop-scroll {
  width: 100%;
  height: 90%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
  
}
.pop-scroll p {
  margin-left: 20%;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.dic-wrap {
  margin-top: 80px;
}
.page-link:focus,
.page-link:hover {
  color: #eceeef;
  text-decoration: none;
  background-color: #f78e6d;
  border-color: #ddd;
  box-shadow: #f7a58cb6;
}
.page-item:first-child .page-link {
  margin-left: 0;
  border-bottom-left-radius: 0.25rem;
  border-top-left-radius: 0.25rem;
}
.page-link {
  position: relative;
  display: block;
  padding: 0.5rem 0.75rem;
  margin-left: -1px;
  line-height: 1.25;
  color: #ed7568;
  background-color: #fff;
  border: 1px solid #ddd;
}
[role="button"],
a,
area,
button,
input,
label,
select,
summary,
textarea {
  -ms-touch-action: manipulation;
  touch-action: manipulation;
}
a {
  color: #ed7568;
  text-decoration: none;
}
a {
  background-color: transparent;
  -webkit-text-decoration-skip: objects;
}
*,
::after,
::before {
  -webkit-box-sizing: inherit;
  box-sizing: inherit;
}
user agent stylesheet a:-webkit-any-link {
  color: -webkit-link;
  cursor: pointer;
  text-decoration: underline;
}
user agent stylesheet li {
  text-align: -webkit-match-parent;
}
.justify-content-center {
  -webkit-box-pack: center !important;
  -webkit-justify-content: center !important;
  -ms-flex-pack: center !important;
  justify-content: center !important;
}
.pagination {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  padding-left: 0;
  list-style: none;
  border-radius: 0.25rem;
}

dl,
ol,
ul {
  margin-top: 0;
  margin-bottom: 1rem;
}
*,
::after,
::before {
  -webkit-box-sizing: inherit;
  box-sizing: inherit;
}
user agent stylesheet ul {
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}
body {
  background-color: #ffffff;
  color: black;
  display: flex;
  flex-direction: column;
  font-family: 'Lalezar', cursive;
  font-family: 'Russo One', sans-serif;
  height: 100vh;
}

header {
  display: flex;
  align-items: center;
  flex: 1;
  padding: 4vh 5vw;
  margin-top: 2vh;
}

.header--search {
  flex: 1;
  /* font-family: 'Lalezar', cursive; */
  font-family: 'Russo One', sans-serif;
}

#search {
  border: 2px solid #ffb4ae;
  height: 40px;
  padding: 10px;
  border-radius: 5px;
  width: 100%;
}

.page--title {
  flex: 4;
  font-family: 'Lalezar', cursive;
  font-family: 'Russo One', sans-serif;
  font-size: 3em;
  padding: 0;
  margin: 0;
  color:rgb(255, 104, 93)
}

main {
  display: flex;
  flex-direction: column;
  flex: 2;
}

.main--titles {
  display: flex;
  flex-direction: row;
  align-items: center;
  /* border-bottom: 2px solid #ed7568; */
  border-bottom: 2px solid #fad5d1c9;
  background: 
  radial-gradient(circle at 50% 0, rgba(255, 0, 34, 0.5),rgba(255,0,0,0) 70.71%), 
  radial-gradient(circle at 6.7% 75%, rgba(255, 228, 108, 0.5), rgba(0,0,255,0) 70.71%),
  radial-gradient(circle at 93.3% 75%, rgba(245, 113, 113, 0.5),
 rgba(0,255,0,0) 70.71%) beige;
  height: 70px;
  
}
.main--titles h2:nth-child(1) {
  padding-left: 5%;
  color: white;
  font-family: 'Russo One', sans-serif;
}
.main--content {
  display: flex;
  flex-direction: row;
  margin-top: 1px;
  height: 800px;
}

.main--title {
  flex: 1;
  font-family: 'Lalezar', cursive;
  font-family: 'Russo One', sans-serif;
  /* padding: 0 2vw; */
}

.main--title-left {
  padding-left: 10px;
  flex: 1;
  /* border-right: 2px solid; */
}

.main--title-right {
  flex: 3;
}

.left {
  flex: 1;
  border-right: 2px solid;
  /* padding: 0 2vw; */
  /* background-color: #ece2cf; */
  /* overflow-y: scroll; */
}

.right {
  flex: 3;
  padding: 0 4vw;

  /* background-color: #ece2cf; */
}

.term--selected {
  border-bottom: 2px solid #ed7568;
  padding-left: 1vw;
  margin-left: 15px;
  /* width: auto; */
  display: inline-block;
}
</style>
