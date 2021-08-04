<template>
  <nav class="nav-header">
    <div class="logo-search">
      <router-link v-if="loginUserId" to="/jobara" class="logo">JOBARA</router-link>
      <router-link v-if="!loginUserId" to="/" class="logo">JOBARA</router-link>
      <div class="search">
        <input
          type="text"
          v-model="searchTagName"
          placeholder="태그 검색은 '#'을 붙여주세요"
          @keyup.enter="seachTag"
        />
      </div>
      <label for="btn-bars" class="icon">
        <span class="fa fa-bars"></span>
      </label>
    </div>
    <input type="checkbox" id="btn-bars" class="checkinput" @click="checkmethods1" />
    <ul class="nav-list ul-tag" :class="{ischecked1:checktrue1}">
      <li v-if="loginUserId" class="list-plus">
        <label for="btn-1" class="click-show">
          <i class="fas fa-plus fa-lg"></i>
        </label>
        <router-link to class="plus">
          <i class="fas fa-plus fa-lg"></i>
        </router-link>
        <input
          type="checkbox"
          id="btn-1"
          class="checkinput"
          @click="checkmethods2"
          :class="{ischecked2:checktrue2}"
        />
        <ul class="sub-menu post-submenu ul-tag" :class="{ischecked2:checktrue2}">
          <li>
            <router-link to="/add/post/addpost">POST</router-link>
          </li>
          <li>
            <router-link to="/add/post/algorithm">ALGORITHM</router-link>
          </li>
        </ul>
      </li>
      <li v-if="loginUserId" class="list-bell">
        <label for="btn-2" class="click-show">
          <i class="fas fa-bell fa-lg fa-regular"></i>
        </label>
        <a class="bell">
          <i class="fas fa-bell fa-lg fa-regular"></i>
        </a>
        <input
          type="checkbox"
          id="btn-2"
          class="checkinput"
          @click="checkmethods2"
          :class="{ischecked2:checktrue2}"
        />
        <ul
          class="sub-menu bell-submenu ul-tag"
          style="width: 300px; height:150px; background:whitesmoke;"
        >
          <li v-for="(item, idx) in recvList" :key="idx" class="jobAlrimLi" style="height: 70px;">
            <div class="jobAlrimContent">
              <span style="font-size: 80%; color:black;">{{recvList[idx].content}}</span>
              <span style="font-size: 70%; color:tomato;">{{calTime(recvList[idx].date)}}</span>
              <!-- <a href='#'>Category</a>
                &middot;
              <a href='#'>Reply</a>-->

              <p style="font-size: 80%; color:black;" class="alrimpreview">{{postContent}}</p>
            </div>
          </li>
        </ul>
      </li>
      <li v-if="loginUserId">
        <router-link to="/directtalk" class="comments">
          <i class="fas fa-comments fa-lg"></i>
        </router-link>
      </li>
      <li v-if="loginUserId" class="list-tie">
        <label for="btn-3" class="click-show">
          <i class="fab fa-black-tie fa-lg fa-regular"></i>
        </label>
        <router-link to class="black-tie">
          <i class="fab fa-black-tie fa-lg fa-regular"></i>
        </router-link>
        <input type="checkbox" id="btn-3" class="checkinput" @click="checkmethods3" />
        <ul class="sub-menu employ-submenu ul-tag" :class="{ischecked3:checktrue3}">
          <li>
            <router-link to="/hire/main">채용 달력</router-link>
          </li>

          <li>
            <router-link to="/it/dictionary">기술용어사전</router-link>
          </li>
        </ul>
      </li>
      <li class="list-user">
        <label for="btn-4" class="click-show">
          <i class="fas fa-user fa-lg"></i>
        </label>
        <router-link to="#">
          <i class="fas fa-user fa-lg"></i>
        </router-link>
        <input type="checkbox" id="btn-4" class="checkinput" @click="checkmethods4" />
        <ul class="sub-menu user-submenu ul-tag" :class="{ischecked4:checktrue4}">
          <li v-if="!loginUserId">
            <router-link to="/user/login">로그인</router-link>
          </li>
          <li v-if="!loginUserId">
            <router-link to="/user/join">회원가입</router-link>
          </li>
          <li v-if="loginUserId" @click="logout">
            <router-link to="/">로그아웃</router-link>
          </li>
          <li v-if="loginUserId">
            <router-link to="/user/usermodified">회원정보</router-link>
          </li>
          <li v-if="loginUserId">
            <router-link to="/post/postmyhome">My피드</router-link>
          </li>
          <li v-if="loginUserId">
            <router-link to="/profile">My채용관리</router-link>
          </li>
        </ul>
      </li>
      <li v-if="loginUserId" class="login-id">
        <router-link id="loginp" to="/post/postmyhome">{{loginUserId}}</router-link>
      </li>
      <li v-else class="login-id">
        <router-link id="loginp" to="/user/login">로그인이 필요합니다</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
import KaKaoAPI from "@/apis/kakaoAPI";
import UserAPI from "@/apis/userAPI";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import ActivityAPI from "@/apis/activityAPI";
import GetPostsApi from "@/apis/postAPI";
// import { error } from "jquery";
export default {
  name: "NewHeader",
  props: ["loginUserId"],
  data() {
    return {
      userId: "",
      postNumber: 0,
      targetuserId: "",
      message: "",
      postContent: "",
      recvList: [],

      // loginUserId: this.$session.get("loginUserId"),
      searchTagName: "",
      checktrue1: false,
      checktrue2: false,
      checktrue3: false,
      checktrue4: false
      // chtransition: '',
    };
  },
  // created: function() {
  //   this.loginUserId = this.$session.get("loginUserId");
  // },
  created() {
    this.userId = this.$session.get("loginUserId");
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    ActivityAPI.selectActivity(this.userId, res => {
      console.log("res.data는 " + res.data);
      this.recvList = [];
      for (var i = 0; i < res.data.length; i++) {
        this.getPostContent(res.data[i].postNumber);
        this.recvList.push(res.data[i]);
      }
    });
    this.connect();
  },
  methods: {
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userId: this.userId,
          chat: this.message
        };
        this.stompClient.send("/jobara/receive", JSON.stringify(msg), {});
      }
    },
    connect() {
      // const serverURL = "http://localhost:8090";
      const serverURL = "http://i3d207.p.ssafy.io:8090";
      let socket = new SockJS(serverURL + "/jobara");
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/jobara/send", res => {
            console.log("구독으로 받은 메시지 입니다.", res.body);
            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            console.log("userId는 : " + this.userId);
            ActivityAPI.selectActivity(this.userId, res => {
              console.log("res.data는 " + res.data);
              this.recvList = [];
              for (var i = 0; i < res.data.length; i++) {
                this.recvList.push(res.data[i]);
                this.postContent = this.getPostContent(
                  this.recvList[i].postNumber
                ).data.postSnsContent;
                console.log(this.postContent);
                console.log("recv : ");
                console.log(this.recvList);
              }
            });
          });
        },
        error => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },
    calTime(regtime) {
      let today = new Date();
      let timeValue = new Date(regtime);

      let betweenTime = Math.floor(
        (today.getTime() - timeValue.getTime()) / 1000 / 60
      );
      if (betweenTime < 1 || regtime == null) return "방금전";
      if (betweenTime < 60) {
        return `${betweenTime}분전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }

      return `${Math.floor(betweenTimeDay / 365)}년전`;
    },
    getPostContent(postNumber) {
      GetPostsApi.searchPostByNo(postNumber, res => {
        console.log(res);
        this.postContent = res.data.postSnsContent;
      });
    },
    searchUser() {
      UserAPI.requestDetail(
        this.searchTagName,
        res => {
          console.log(res);
          this.searchTagName = "";
          if (res.data.userId == this.loginUserId) {
            this.$router.push("/post/postmyhome");
          } else {
            this.$router
              .push("/post/posthome?userId=" + res.data.userId)
              .catch(() => {});
          }
        },
        error => {
          console.log(error);
          this.searchTagName = "";
          alert("일치하는 회원정보가 없습니다.");
        }
      );
    },
    seachTag() {
      if (this.searchTagName.charAt(0) == "#") {
        let regExp = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/gi;
        let t = "";
        if (regExp.test(this.searchTagName)) {
          t = this.searchTagName.replace(regExp, "");
        } else {
          t = this.searchTagName;
        }
        this.$store.commit("setSearchTagName", t);
        // this.$router.push("/post/search?tag=" + t);
        this.searchTagName = "";
        this.$router.push("/post/search").catch(() => {});
      } else {
        this.searchUser();
      }
    },
    checkmethods1() {
      this.checktrue1 = !this.checktrue1;
      // this.chtransition = '0.6s'
      console.log(this.checktrue1);
    },
    checkmethods2() {
      this.checktrue2 = !this.checktrue2;
      this.checktrue3 = false;
      this.checktrue4 = false;
      console.log("2 => " + this.checktrue2);
    },
    checkmethods3() {
      this.checktrue3 = !this.checktrue3;
      this.checktrue2 = false;
      this.checktrue4 = false;
      console.log("3 => " + this.checktrue3);
    },
    checkmethods4() {
      this.checktrue4 = !this.checktrue4;
      this.checktrue3 = false;
      this.checktrue2 = false;
      console.log("4 => " + this.checktrue4);
    },
    logout() {
      let kakaoToken = this.$session.get("kakaoAccessToken");
      if (kakaoToken) {
        alert("카카오 계정 로그아웃!");
        KaKaoAPI.requestKakaoLogout(
          kakaoToken,
          res => {
            console.log("카카오 로그아웃 요청성공");
            console.log(kakaoToken);
            console.dir(res);
          },
          error => {
            console.log(error);
          }
        );
      }
      this.$session.destroy();
      location.reload();
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

#loginp {
  /* font-weight: normal | bold | bolder */
  padding-left: 0px;
  font-size: 16.5px;
  line-height: 70px;
  margin: 0;
  font-weight: 700;
  transform: rotate(0.1deg);
  font-family: "Do Hyeon", sans-serif;
  letter-spacing: -1.5px;
  word-break: keep-all;
}

.list-plus {
  position: relative;
}
.list-plus:hover {
  position: relative;
}
.list-bell {
  position: relative;
}
.list-bell:hover {
  position: relative;
}
.list-tie {
  position: relative;
}
.list-user {
  position: relative;
}
.fa-plus {
  color: #eb4836c4;
}
li.bell:hover {
  animation: headShake;
  animation-duration: 0.5s;
}
.sub-menu {
  position: absolute;
  right: -1.8rem;
}
.fa-bell {
  color: #ec473bde;
}
.fa-comments {
  color: #eb4646ce;
}
.fa-black-tie {
  color: #f32749de;
}
.fa-user {
  color: #ff4e6bde;
}
a {
  text-decoration: none !important;
}
a:hover {
  text-decoration: none !important;
}
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  font-family: "Roboto", "sans-serif";
  scroll-behavior: smooth;
  /* font-size: 10px; */
}

body {
  overflow-x: hidden;
  background-color: black;
}
.bell-submenu {
  overflow-x: hidden;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.733);
  z-index: 100000;
}
.bell-submenu li {
  height: 70px;
}
.bell-submenu::-webkit-scrollbar {
  width: 6px;
}
.bell-submenu::-webkit-scrollbar-track {
  background-color: #ffd9d9;
}
.bell-submenu::-webkit-scrollbar-thumb {
  background-color: #ff9885a1;
  border-radius: 10px;
}
.bell-submenu::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 31, 31, 0.541);
}
.search {
  line-height: 70px;
  float: left;
  width: 100%;
  height: 70%;
  margin: 0% 0% 0 10%;
}
.search > input {
  width: 100%;
  height: 65%;
  padding: 10px;
  border-radius: 4px;
  font-size: 80%;
}
.nav-header {
  background: rgba(255, 255, 255);
  width: 100%;
  margin: 0;
  padding: 0 10%;
  position: fixed;
  top: 0;
  right: 0;
  z-index: 999;
  border-bottom: 2px solid rgba(168, 168, 168, 0.1);
}
.nav-header:after {
  content: "";
  clear: both;
  display: table;
}
.nav-header .logo-search {
  float: left;
  display: flex;
  flex-direction: row;
  width: 300px;
  /* display: none; */
}
.nav-header .logo {
  color: #ed7568;
  font: 50px;
  font-weight: 700;
  line-height: 70px;
  font-family: "Do Hyeon", sans-serif;
  font-size: 120%;
  letter-spacing: 3px;
}
.nav-header ul {
  float: right;
  list-style: none;
  margin-right: 40px;
  margin: 0;
  position: relative;
}
.nav-header ul li {
  float: left;
  display: inline-block;
  background: rgba(255, 255, 255, 0.959);
  margin: 0 5px;
  z-index: 10000;
}
.nav-header ul li a {
  text-decoration: none;
  line-height: 70px;
  font-size: 18px;
  padding: 8px 20px;
  transition: color 650ms;
  color: rgba(58, 58, 58, 0.801);
}
.nav-header ul li a:hover {
  color: rgba(250, 99, 94, 0.918);
  font-weight: 500;
}
.nav-header ul ul {
  position: absolute;
  top: 90px;
  /* display: none; */
  opacity: 0;
  visibility: hidden;
  width: 7rem;
  display: block;
  background: rgba(255, 255, 255, 0.521);
  z-index: 2147483647;
  margin: 0;
  padding: 0;
  border-top: 3px solid rgba(250, 111, 93, 0.89);
}

.nav-header ul li:hover > ul {
  top: 70px;
  opacity: 1;
  visibility: visible;
  transition: all 300ms ease;
}
.nav-header ul li:hover {
  color: rgba(250, 111, 93, 0.89);
  box-shadow: none;
  cursor: pointer;
}
.nav-header ul ul li {
  position: relative;
  margin: 0px;
  width: inherit;
  height: 50px;
  float: none;
  display: list-item;
  text-align: center;
}
.nav-header ul ul li:not(:last-child) {
  border-bottom: 1px solid rgba(173, 173, 173, 0.205);
}
.bell-submenu li {
  padding: 10px;
}
/* .nav-header ul ul li:{
  border-bottom: none;
} */
.nav-header ul ul li a {
  font-size: 90%;
  line-height: 50px;
}
.login-id p {
  line-height: 70px;
  margin: 0;
}
/* .user-submenu {
  background: rgba(255, 255, 255, 0.959);
  border-top:3px solid #ed7568;
}
.user-submenu::before {
  content: "";
  position: absolute;
} */
.click-show,
.icon,
.checkinput {
  display: none;
}

@media screen and (max-width: 960px) {
  .nav-header {
    background: rgba(255, 255, 255, 0.925);
    padding-left: 3%;
  }
  .nav-header ul {
    margin-right: 0px;
    float: left;
  }
  .nav-header .logo-search {
    padding-left: 30px;
    width: 100%;
  }
  .nav-header ul li,
  .nav-header ul ul li {
    display: block;
    width: 100%;
    background: rgba(255, 255, 255, 0);
  }
  .nav-header ul ul li:last-child {
    border-bottom: 1px solid rgba(173, 173, 173, 0.205);
  }
  .nav-header ul ul {
    top: 70px;
    position: static;
    border-top: none;
    float: none;
    display: none;
    opacity: 1;
    visibility: visible;
    width: 100%;
    background: #ffffff00;
  }
  .nav-list {
    width: 100%;
  }
  .click-show {
    display: block;
    font-size: 18px;
    padding: 0 20px;
    line-height: 70px;
    cursor: pointer;
  }
  .click-show:hover {
    color: cyan;
  }
  .search input {
    width: 80%;
    padding: 5px;
  }
  .icon {
    display: block;
    color: rgb(65, 65, 65);
    position: absolute;
    right: 40px;
    line-height: 70px;
    font-size: 25px;
    cursor: pointer;
  }
  .nav-header ul li {
    text-align: left;
  }
  .nav-header ul li a:hover {
    box-shadow: none;
  }
  .click-show + a,
  .ul-tag {
    display: none;
  }
  .ischecked1 {
    display: block;
  }
  .nav-header ul .ischecked2 {
    display: block;
  }
  .nav-header ul .ischecked3 {
    display: block;
  }
  .nav-header ul .ischecked4 {
    display: block;
  }
  .login-id p {
    display: none;
  }
}

@media screen and (max-width: 576px) {
  .nav-header {
    height: 50px;
  }
  .nav-header .logo {
    line-height: 50px;
    font-size: 80%;
    left: 20px;
  }
  .nav-header .logo-search {
    padding-left: 20px;
  }
  div.search {
    line-height: 50px;
  }
  .search input {
    padding: 3px;
  }
  .icon {
    line-height: 55px;
    right: 20px;
  }
}
</style>
