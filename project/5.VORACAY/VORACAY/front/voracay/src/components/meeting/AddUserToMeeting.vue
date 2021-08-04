<template>
<div>
    <div class="add-user-frame-app">
        <div class="add-user-frame">
            <div class="meeting-header">
                <span class="header-text">사원 목록</span>
                <p class="divider"></p>
            </div>
            <div class="user_list_search">
              <select
                class="form-control"
                name="searchCategory"
                v-model="searchCategory"
              >
                <option disabled value>카테고리 설정</option>
                <option value="user_name" selected>사용자 이름</option>
                <option value="user_number">사번</option>
                <option value="user_grade">직급</option>
                <option value="user_email">e-mail</option>
                <option value="user_dept">부서</option>
              </select>
              <input
                type="text"
                class="search_contact-input"
                v-model="searchText"
                placeholder="이름을 입력하세요"
                @keyup.enter="setUser(searchCategory, searchText)"
              />
            </div>
            <div class="user-list-div">
                <ul class="user-info-li">
                    <li class="user-info-li">
                      <div 
                            v-for="(item, index) in userList"
                            :key="index + 'userNum'"
                            class="user-info-div"
                            :class="{ 'on' : checked[index]}"
                            @click="ischecked(index)"
                            > 
                              <div v-if="item.user_name != userName">
                                <input 
                                  name="user" 
                                  type="radio"
                                  class="user-input"
                                >
                                <label class="user-name">
                                    {{item.user_name}}
                                </label>
                                <label class="user-dept">
                                    {{item.user_dept}}    /
                                </label>
                                <label class="user-grade">
                                    {{item.user_grade}}
                                </label>
                              </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="button-area">
      <div class="add-button" @click="clickedAddButton()">
        <img 
          src="../../assets/add.png"
          />
      </div>
      <div class="del-button" @click="clickedDelButton()">
        <img 
          src="../../assets/del.png"
          />
      </div>
    </div>
    <div class="add-user-frame-app second">
        <div class="add-user-frame">
            <div class="meeting-header">
                <span class="header-text">추가된 목록</span>
                <p class="divider"></p>
            </div>
            <div class="user-list-div second">
                <ul class="user-info-li">
                    <li class="user-info-li">
                        <div 
                            v-for="(item, index) in addedUserList" 
                            :key="index + 'userNum'" 
                            class="user-info-div"
                            :class="{ 'on' : AddedChecked[index]}"
                            @click="isAddedchecked(index)">
                            <div v-if="item[1].user_name != userName">
                              <input 
                                    name="addedUser" 
                                    type="radio"
                                  >
                              <label class="user-name">
                                  {{item[1].user_name}}
                              </label>
                              <label class="user-dept">
                                  {{item[1].user_dept}}    /
                              </label>
                              <label class="user-grade">
                                  {{item[1].user_grade}}
                              </label>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="start-button-div">
      <button class="btn btn-dark start-button">미팅 시작</button>
    </div>
</div>
</template>
<script>
import http from "../../http-common";

export default {
    data(){
        return{
            userName : "",
            searchText:"",
            searchCategory:"",
            addedUserList:[],
            checkedList:[],
            searchedList:[],
            checked: [],
            AddedChecked: [],
            searchedChecked: [],
            userList: [],
        };
    },
    created(){
        this.setUser();
    },
    watch:{
      checked: function(newVal, oldVal){
        if(newVal){
          for(var i=0; i<this.checked.length; i++){
            if(this.checked[i]){
              return true;
            }
          }
        }
      },

      AddedChecked: function(newVal, oldVal){
        if(newVal){
          for(var i=0; i<this.AddedChecked.length; i++){
            if(this.AddedChecked[i]){
              return true;
            }
          }
        }
      },
      selectChecked: function(newVal, oldVal){
        if(newVal){
          for(var i=0; i<this.searchedChecked.length; i++){
            if(this.searchedChecked[i]){
              return true;
            }
          }
        }
      },
    },
    methods:{
        setUser(category, search) {
          this.userName = this.$store.state.userName;
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
              this.pageSize = parseInt((res.data.total_count) / 10);
              if(res.data.total_count % 10 != 0) this.pageSize += 1;
              this.userList = [];
              for (let i = 0; i < list.length; i++) {
                list[i].isFavorite = false;
                this.userList.push(list[i]);
              }
              this.sortName(this.userList);
            })
            .catch((error) => {});
        },
        ischecked(index){
          this.checked = [];
          var obj = document.getElementsByName("user");
          for(var i=0; i<obj.length; i++){
            if(index == i){
              this.checkedList[i] = true;
              this.checked[i] = true;
            }
            else{
              this.checkedList[i] = false;
              this.checked[i] = false;
            }
          }
        },

        isAddedchecked(index){
          this.AddedChecked = [];
          var obj = document.getElementsByName("addedUser");
          for(var i=0; i<obj.length; i++){
            if(index == i){
              this.checkedList[i] = true;
              this.AddedChecked[i] = true;
            }
            else{
              this.checkedList[i] = false;
              this.AddedChecked[i] = false;
            }
          }
        },

        isSearchedChecked(index){
          this.searchedChecked = [];
          var obj = document.getElementsByName("searchedUser");
          for(var i=0; i<obj.length; i++){
            if(index == i){
              this.checkedList[i] = true;
              this.searchedChecked[i] = true;
            }
            else{
              this.checkedList[i] = false;
              this.searchedChecked[i] = false;
            }
          }
        },
        // (의문)
        // 이름 오름차순으로 정렬하는 함수
        // 1. 이름을 오름차순으로 나타내는게 맞는지 모르겠다.
        // 2. 이렇게 함수를 만들지 않아도 정렬할 수 있는 함수가 있는지 모르겠다.
        sortName(obj){
          obj.sort(function(a, b) { // 오름차순
            return a.user_name < b.user_name ? -1 : a.user_name > b.user_name ? 1 : 0;
          });
        },
        clickedAddButton(){
          // 검색 O
          if(this.searchedList.length!=0){
            for(var i=0; i<this.searchedList.length; i++){
              if(this.searchedList[i]){
                var temp = this.searchedList[i][0];
                this.addedUserList.push([temp,this.searchedList[i][1]]);

                //전체 유저 목록에서 삭제하기
                if(this.searchedList[i][1].user_num == this.userList[temp].user_num){
                  this.userList.splice(temp,1);
                }else{
                  for(var j=0; j<this.userList.length; j++){
                    if(this.userList[j].user_num.includes(this.searchedList[i][1].user_num)){
                      this.userList.splice(j,1);
                    }
                  }
                }
                this.searchedList.splice(i,1);
              }
            }
            this.searchedChecked = [];
          //검색 X
          }else{
            // var obj = document.getElementsByName("user-info-div");
            // for(var i=0; i<obj.length; i++){
            //   if(obj[i].checked){
            //     this.addedUserList.push([i,this.userList[i]]);
            //     this.userList.splice(i,1);
            //   }
            // }
            for(var i=0; i<this.checkedList.length; i++){
              if(this.checkedList[i]){
                this.addedUserList.push([i,this.userList[i]]);
                this.checkedList = [];
                this.checked = [];
                this.userList.splice(i,1);
              }
            }
          }
          this.setStoreUserList();
        },
        clickedDelButton(){
          if(this.searchedList.length!=0){
            for(var i=0; i<this.searchedList.length; i++){
              if(this.searchedList[i]){
                var temp = this.addedUserList[i][0];
                this.userList.push(this.addedUserList[i][1]);
                this.searchedList.push([temp,this.addedUserList[i][1]]);
                this.addedUserList.splice(i,1);
                this.sortName(this.searchedList[i]);
              }
            }
          }else{
          // var obj = document.getElementsByName("addedUser");
            for(var i=0; i<this.addedUserList.length; i++){
              if(this.checkedList[i]){
                this.userList.push(this.addedUserList[i][1]);
                this.addedUserList.splice(i,1);
                this.AddedChecked = [];
                this.sortName(this.userList);
              }
            }
          }
          this.setStoreUserList();
        },
        setStoreUserList(){
          let userList = this.addedUserList;
          let storeList = [];
          for(let i = 0; i < userList.length; i++){
            storeList.push(userList[i][1]);
          }
          this.$store.commit("setUserList", storeList);
        },
    },
    mounted() {},
}
</script>
<style scoped>
.add-user-frame-app{
  margin-left: 10%;
  margin-top: 5%;
  background-color: #fff;
  width: 32%;
  height: 400px;
  box-shadow: 2px 2px 3px rgba(69,28,100,0.15);
  float: left;

}

.user-list-div {
    width: 100%;
    height: 280px;
    overflow: auto;
}

.user-list-div.second{
  height: 325px;
}

.add-user-frame {
    position:relative;
}

.meeting-header{
  color: #555;
  text-align: center;
  border-top: 10px solid rgb(41 58 151);
  border-radius: 2px;
  padding: 0.25em 0;
}

.header-text{
  font-family: 'Catamaran';
  font-size: 32px;
}

.user-info-li {
  list-style:none;
  padding: 0;
}

.divider {
  margin: 0 15px;
  margin-bottom: 1%;
  border-top: 1px solid rgb(41 58 151);
}

.user-info-div{
    float: left;
    margin-left: 12%;
    margin-right: 20%;
    margin-bottom: 1%;
    width: 80%;
}

.user-info-div.on{
  background-color: #B0C4DE;
}
.user-name{
    font-size: 20px;
}
.user-grade{
    margin-left: 2%;
    color: gray;
    
}
.user-dept{
    margin-left: 3%;
    color: gray;
}
.button-area{
  width: 10%;
  height: 400px;
  float: left;
  margin-top: 5%;
}
.add-button {
  width: 100%;
  height: 50%;
}

.del-button {
  width: 100%;
  height: 50%;
}

.add-button > img{
  width : 15%;
  height: 8%;
  margin-top: 100%
}

.del-button > img{
  width : 15%;
  height: 8%;
  margin-top: 50%;
}

.add-button:hover{
  cursor:pointer;
}

.del-button:hover{
  cursor:pointer;
}

.add-user-frame-app.second{
  margin-left: 0;
}

p {
  margin: 0; 
}

input[type="radio"]{
    visibility:hidden;
}

.user-list-search{
  width: 330px;
  height: 40px;
  margin: 1%;
}
.search-category{
  margin-left: 10%;
  margin-right: 2%;
  margin-top: 1%;
  float: left;
  width: 70px;
}

.search-input{
  float: left;
}

.start-button-div{
  width: 100%;
  height: 30%;
}

.start-button{
  margin-left: 5%;
  margin-top: 5%;
}
/* ::-webkit-input-placeholder{
  color: #ccc;
}
:-moz-input-placeholder{
  color: #ccc;
} */

@media screen and (max-width: 768px){
  .add-user-frame-app{
  margin-left: 1%;
  margin-right: 4%;
  margin-top: 1px;
  background-color: #fff;
  width: 100%;
  height: 200px;
  box-shadow: 2px 2px 3px rgba(69,28,100,0.15);
  float: left;
  }
.user-list-div {
    width: 100%;
    height: 110px;
    overflow: auto;
    margin-top: 2px;
}
.add-user-frame {
    position:relative;
    padding-bottom: 25px;
}

.meeting-header{
  color: #555;
  text-align: center;
  border-top: 10px solid rgb(41 58 151);
  border-radius: 2px;
  padding: 0.25em 0;
}

.header-text{
  font-family: 'Catamaran';
  font-size: 18px;
}

.user-info-li {
  list-style:none;
  padding: 0;
}

.user-list-div.second{
  height: 150px;
}

.divider {
  margin: 0 15px;
  margin-bottom: 1%;
  border-top: 1px solid rgb(41 58 151);
}

.user-info-div{
    float: left;
    margin-left: 1px;
    margin-right: 5%;
    margin-bottom: 1%;
    width: 100%;
}

.user-info-div.on{
  background-color: #B0C4DE;
}
.user-name{
    font-size: 20px;
}
.user-grade{
    margin-left: 2%;
    color: gray;
    
}
.user-dept{
    margin-left: 3%;
    color: gray;
}
.button-area{
  width: 100%;
  height: 38px;
  float: left;
  margin-top: 2%;
}
.add-button {
  float: left;
  width: 35%;
  height: 30px;
  margin-left: 13%;
}

.del-button {
  float: left;
  width: 35%;
  height: 30px;
  margin-left: 1%;
}

.add-button > img{
  width: 30px;
  height: 30px;
  margin-top: 1%; 
  transform: rotate( 90deg );
}

.del-button > img{
  width: 30px;
  height: 30px;
  margin-top: 1%;
  transform: rotate( 90deg );
}

.add-button:hover{
  cursor:pointer;
}

.del-button:hover{
  cursor:pointer;
}

.add-user-frame-app.second{
  margin-left: 0;
}

p {
  margin: 0; 
}

input[type="radio"]{
    visibility:hidden;
}

.user-list-search{
  width: 330px;
  height: 30px;
  margin: 1.2%;
}
.search-category{
  margin-left: 5%;
  margin-right: 3%;
  margin-top: 1px;
  float: left;
  width: 70px;
  height: 24px;
}

.search-input{
  float: left;
  width: 200px;
  height: 26px;
}

.user-input{
  width: 1px;
  height: 1px;
}

.start-button-div{
  margin : 0;
}
.start-button{
  margin-top : 10%;
}
}
</style>