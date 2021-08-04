<template>
  <div class="container_contact">
    <span></span>
    <div class="search_contact">
      <select
        class="form-control"
        name="searchCategory"
        v-model="searchCategory"
      >
        <option disabled value>카테고리</option>
        <option value="user_name" selected>사용자 이름</option>
        <option value="user_number">사번</option>
        <option value="user_grade">직급</option>
        <option  value="user_email">e-mail</option>
        <option value="user_dept">부서</option>
      </select>
      <input
        type="text"
        class="search_contact-input"
        v-model="searchText"
        placeholder=" 검색어 입력"
        @keyup.enter="setUser(searchCategory, searchText)"
      />
    </div>
    <div class="contactList">
      <table class="table table-hover">
        <thead>
          <tr>
            <td>이름</td>
            <td>부서</td>
            <td>직급</td>
            <td class="hide">이메일</td>
            <td></td>
            <td></td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in userList" :key="index + 'user_number'">
            <td>{{ item.user_name }}</td>
            <td>{{ item.user_dept }}</td>
            <td>{{ item.user_grade }}</td>
            <td class="hide">{{ item.user_email }}</td>
            <td>
              <img
                @click="toggleFav(item.user_number)"
                src="../../assets/fullstar.png"
                width="20"
                height="20"
                v-if="item.isFavorite"
              />
              <img
                @click="toggleFav(item.user_number)"
                src="../../assets/emptystar.png"
                width="20"
                height="20"
                v-else
              />
            </td>
            <td class="call"><img @click="toCallSet()" src="../../assets/image/card/table/통화.png" alt="voda 로고" width="20" height="20" /></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="btn-cover">
      <button :disabled="pageNum === 1" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum }} / {{ pageSize }} 페이지</span>
      <button
        :disabled="pageNum >= pageSize"
        @click="nextPage"
        class="page-btn"
      >
        다음
      </button>
    </div>
  </div>
</template>
<script>
import http from "../../http-common";
export default {
  data() {
    return {
      searchCategory: "",
      searchText: "",
      userList: [],
      checked: false,
      pageSize: 1,
      pageNum: 1,
    };
  },
  created() {
    this.setUser("", "");
  },
  computed: {
  },
  watch: {},
  methods: {
    nextPage() {
      this.pageNum += 1;
      this.setUser(this.searchCategory, this.searchText);
    },
    prevPage() {
      this.pageNum -= 1;
      this.setUser(this.searchCategory, this.searchText);
    },
    addFav(user_number){
      http.post("v2/favor/",{
        user_number:user_number,
      }).then((res)=>{
        this.toast("추가", "info");
      }).catch((err)=>{
        this.toast("추가 실패", "error");
      })
    },
    delFav(user_number){
      http.delete("v2/favor/", {
        params :{
          user_number:user_number,
        }
      }).then((res) =>{
        this.toast("삭제", "info");
      }).catch((err)=>{
        this.toast("삭제 실패", "error");
      })
    },
    toggleFav(user_number) {
      this.userList.forEach((user) => {
        if (user.user_number == user_number) {
          user.isFavorite = !user.isFavorite;
          if (user.isFavorite){
            this.addFav(user.user_number);
          } else {
            this.delFav(user.user_number);
          }
        }
      });
    },
    setUser(category, search) {
      if (category == "" && search != "") category = "user_name";
      http
        .get("v2/users/", {
          params: {
            category: category,
            search: search,
            page: this.pageNum,
          },
        })
        .then((res) => {
          let list = res.data.content;
          let favorites = res.data.favorites == undefined ? [] : res.data.favorites;
          this.pageSize = parseInt((res.data.total_count) / 10);
          if(res.data.total_count % 10 != 0) this.pageSize += 1;
          this.userList = [];
          for (let i = 0; i < list.length; i++) {
            list[i].isFavorite = false;
            for(let j = 0; j < favorites.length; j++){
              if(favorites[j] == list[i].user_number){
                list[i].isFavorite = true;
                break;
              }
            }
            this.userList.push(list[i]);
          }
        })
        .catch((error) => {});
    },
    toast(msg, type){
      this.$toast.open({
            message: msg,
            type: type,
            position: "top-right",
          });
    },
    toCallSet(){
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", "isCallSet");
      this.mainRouter = this.$store.state.mainRouter;
    },
  },
  mounted() {},
};
</script>
<style scoped>
@import url("../../../src/assets/modules/vue-toast-notification/dist/theme-sugar.css");
@font-face {
  font-family: "Fredoka One";
  src: url("../../assets/font/login/FredokaOne-Regular.ttf");
}
@font-face {
  font-family: "NanumGothic";
  src: url("../../assets/font/mainfont/NanumGothic-Regular.ttf");
}
@font-face {
  font-family: "NotoSerif";
  src: url("../../assets/font/mainfont/NotoSerif-Regular.ttf");
}
.page-count{
  
    margin-top: 3px;

}
.btn-cover{
  font-family: "NanumGothic", "Fredoka One" ;
  font-size: 15px;
}
.call :hover{
  	transform: rotate( -10deg );
}
.search_contact {
  display:flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  width:100%;
}
.form-control{
  width:30%;
  margin-left: 3%;
}
.search_contact-input{
  width:58%;
  margin-right: 3%;
  margin-left: 3%;
  height: 35px;
}
.contactList{
  font-family: "NanumGothic", "Fredoka One" ;
  font-size: 15px;
}
.contactList td{
  padding: 7px;
}
.table-hover > thead {
  background-color: rgb(21, 10, 117);
  font-family: "Fredoka One";
  color: #c0c4f5;
  font-size: 15px;
}

.favCheckbox {
  float: left;
}
/* button */
.btn-cover {
  max-width: 650px;
  margin: 30px auto;
 
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.page-btn {
  margin-left: 10px;
  margin-right: 10px;
  border-radius: 5px;
  width: 100px;
  background: radial-gradient(
      circle at 50% 0,
      rgba(0, 47, 255, 0.527),
      rgb(24, 1, 105) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(26, 4, 87, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgb(0, 7, 109),
        rgba(2, 6, 68, 0) 70.71%
      )
      beige;
  color: #fff;
  border: none;
  position: relative;
  height: 30px;
  font-size: 100%;
  font-weight: 500;
  padding: 0 2em;
  cursor: pointer;
  transition: 800ms ease all;
  outline: none;
}
.page-btn:hover {
  background: #fff;
  color: #030a6d;
}
.page-btn:before,
.page-btn:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: radial-gradient(
      circle at 50% 0,
      rgb(8, 18, 107),
      rgba(10, 2, 78, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(16, 1, 83, 0.993),
      rgba(0, 0, 107, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(10, 5, 78, 0.329),
        rgba(0, 5, 68, 0) 70.71%
      )
      rgb(220, 237, 245);
  transition: 400ms ease all;
}
.page-btn:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.page-btn:hover:before,
.page-btn:hover:after {
  width: 100%;
  transition: 800ms ease all;
}

@media screen and (max-width: 768px) {
 .form-control{
  width:35%;
  margin-left: 1%;
}
.search_contact-input{
  width:60%;
  margin-right: 1%;
  margin-left: 1%;
  height: 30px;
}
 .hide{
   display: none;
 }
}
</style>
</style>
