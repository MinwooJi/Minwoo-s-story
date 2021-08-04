<template>
  <div>
  
      <div>
      <div class="contactList" >
       <table class="table table-hover">
        <colgroup>
          <col width="50%" />
          <col width="20%" />
          <col width="20%" />
          <col width="10%" />
        </colgroup>
        <thead class="thead">
          <tr>
            <th colspan="4">최근 통화 목록</th>
            
          </tr>
        </thead>
        <tbody class="tbody" v-if="callerInfo.length">
          <tr  v-for="(caller, index) in callerInfo" :key="index + '_items'">
            <td >
              {{ caller.caller  }}
            </td>
            <td>{{ caller.date  }}</td>
            <td >{{ caller.time  }}</td>
             <td class="call "><img src="../../assets/image/card/table/통화.png" alt="통화btn" width="20" height="20" /></td>
          </tr>
        </tbody>
        <tbody v-else class="tbody-nobody">
          <th colspan="4">
          최근 통화 기록이 없습니다</th>
        </tbody>
       </table>
      </div>
      <div v-if="pageSize>1" class="btn-cover">
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
      <!-- <div class="contactList" v-else>
        최근 통화 기록이 없습니다
      </div> -->
  
  </div>
</template>
<script>
import http from "../../http-common";
export default {
  name: 'recentcall',
  data() {
    return {
      pageSize: 2,
      pageNum: 1,
      callerInfo: [
        {
          caller: "윤신혜",
          position: "회장",
          department: "몰라",
          date: "2020-10-17",
          time:"09:01"
        },
        {
          caller: "임효진,윤신혜,주지환,지민우,정연욱",
          position: "사장",
          department: "쫄다구",
          date: "2020-10-17",
          time:"09:04"
        },
        {
          caller: "주지환",
          position: "부장",
          department: "쫄다구2",
          date: "2020-10-17",
          time:"09:54"
        },
        {
          caller: "정연욱",
          position: "차장",
          department: "쫄다구3",
          date: "2020-10-17",
          time:"19:05"
        },
        {
          caller: "지민우",
          position: "인턴",
          department: "쫄다구of쫄다구",
          date: "2020-10-17",
          time:"19:15"
        },
        {
          caller: "윤신혜",
          position: "회장",
          department: "몰라",
          date: "2020-10-17",
          time:"09:01"
        },
        {
          caller: "임효진,윤신혜,주지환,지민우,정연욱",
          position: "사장",
          department: "쫄다구",
          date: "2020-10-17",
          time:"09:04"
        },
        {
          caller: "주지환",
          position: "부장",
          department: "쫄다구2",
          date: "2020-10-17",
          time:"09:54"
        },
        
      ],
    };
  },
  created() {
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
      this.setUser(this.searchCategory, this.searchText);
    },
    prevPage() {
      this.pageNum -= 1;
      this.setUser(this.searchCategory, this.searchText);
    },
    call() {
      this.checkedCallerName = [];
      for (let i = 0; i < this.checkedCallerInfo.length; i++) {
        this.checkedCallerName.push(this.checkedCallerInfo[i].caller);
      }
      var permitCall = confirm(
        this.checkedCallerName + "에게 전화하시겠습니까?"
      );
      if (permitCall) {
        alert("전화합니다");
      }
    },
    
  },
};
</script>
<style scoped>
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
.tbody-nobody{
  text-align: center;
}

.tbody td{
    vertical-align: middle;
    
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

.form-control{
  width:30%;
  margin-left: 3%;
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
