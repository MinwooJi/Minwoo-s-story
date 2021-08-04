<template>
<body class="jobAlrimBody">
<div class="jobalrimdiv">
  <div class='jobAlrim'>
    <header class="jobAlrimHeader"></header>
    <div class='jobAlrimBodyClass'>
      <ul class="jobAlrimUl" v-for="(item, idx) in recvList" :key="idx">
        <li class="jobAlrimLi">
          <div class='jobAlrimContent'>
            <h5>{{recvList[idx].content}}</h5><span class='alrimmeta'>
              {{calTime(recvList[idx].date)}}
              <!-- <a href='#'>Category</a>
              &middot;
              <a href='#'>Reply</a> -->
            </span>
            <span class='alrimpreview'>{{postContent}}</span>
          </div>
        </li>
      </ul>
    </div>
    <header class="jobAlrimHeader"></header>
  </div>
</div>
</body>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import ActivityAPI from "@/apis/activityAPI";
import GetPostsApi from "@/apis/postAPI";

export default {
  name: "App",
  data() {
    return {
      userId: "",
      postNumber: 0,
      targetuserId: "",
      message: "",
      postContent: "",
      recvList: [],
    };
  },
  created() {
    this.userId = this.$session.get("loginUserId");
    // App.vue가 생성되면 소켓 연결을 시도합니다.
            ActivityAPI.selectActivity(this.userId, res => {
            console.log("res.data는 "+res.data);
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
      let socket = new SockJS(serverURL+"/jobara");
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
            console.log("res.data는 "+res.data);
              this.recvList = [];
              for (var i = 0; i < res.data.length; i++) {
                this.recvList.push(res.data[i]);
                this.postContent = this.getPostContent(this.recvList[i].postNumber).data.postSnsContent;
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
    getPostContent(postNumber){
      GetPostsApi.searchPostByNo(postNumber, res =>{
        console.log(res);
        this.postContent = res.data.postSnsContent;
      })
    }
  }
};
</script>

<style scoped>
body.jobAlrimBody {
  font-family: 'Roboto', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.jobalrimdiv{
  margin-top:10%;
}
.jobAlrim {
  background: #ffffff;
  width: 35%;
  margin: 0 auto;
}
.jobAlrim header.jobAlrimHeader {
  background: linear-gradient(120deg,#f88782, #f9dc9b);
  padding: 10px 15px;
  color: #ffffff;
  font-size: 14px;
  cursor: move;
}
.jobAlrim header.jobAlrimHeader:before,
.jobAlrim header.jobAlrimHeader:after {
  display: block;
  content: '';
  clear: both;
}


.jobAlrim header.jobAlrimHeader h2 a {
  color: #ffffff;
  text-decoration: none;
}
.jobAlrim header.jobAlrimHeader .tools {
  list-style: none;
  margin: 0;
  padding: 0;
  float: right;
}
.jobAlrim header.jobAlrimHeader .tools li.jobAlrimLi {
  display: inline-block;
  margin-right: 6px;
}
.jobAlrim header.jobAlrimHeader .tools li.jobAlrimLi:last-child {
  margin: 0;
}
.jobAlrim header.jobAlrimHeader .tools li.jobAlrimLi a {
  color: #ffffff26;
  text-decoration: none;
  -webkit-transition: all 0.3s linear 0s;
  -moz-transition: all 0.3s linear 0s;
  -ms-transition: all 0.3s linear 0s;
  -o-transition: all 0.3s linear 0s;
  transition: all 0.3s linear 0s;
}
.jobAlrim .jobAlrimBodyClass {
  position: relative;
  max-height: 360px;
  overflow-y: scroll;
}
.jobAlrim .jobAlrimBodyClass .search {
  display: none;
  width: 100%;
}
.jobAlrim .jobAlrimBodyClass .search.opened {
  display: block;
}
.jobAlrim .jobAlrimBodyClass .search input {
  width: 100%;
  margin: 0;
  padding: 10px 15px;
  border: none;
  -webkti-box-size: border-box;
  -moz-box-size: border-box;
  box-size: border-box;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl {
  list-style: none;
  padding: 0;
  margin: 0;
  border-top: 1px solid #f2f2f2;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi {
  position: relative;
  background: #ffffff;
  display: block;
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi:before,
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi:after {
  display: block;
  content: '';
  clear: both;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi:hover .thumbnail {
  background: #bd6982;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi:nth-child(2n) {
  background: #f2f2f2;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .thumbnail {
  display: inline-block;
  background: #bfbfbf;
  width: 50px;
  color: #ffffff;
  line-height: 50px;
  text-align: center;
  text-decoration: none;
  -webkit-transition: background 0.3s linear 0s;
  -moz-transition: background 0.3s linear 0s;
  -ms-transition: background 0.3s linear 0s;
  -o-transition: background 0.3s linear 0s;
  transition: background 0.3s linear 0s;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .thumbnail img {
  width: 100%;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent {
  display: inline-block;
  margin-left: 6px;
  vertical-align: top;
  line-height: 1;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent h3 {
  display: block;
  width: 100%;
  margin-bottom: 5px;
  color: #808080;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent .alrimpreview {
  display: block;
  width: 100%;
  max-width: 200px;
  margin-bottom: 5px;
  color: #cccccc;
  font-size: 15px;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent .alrimmeta {
  color: #b3b3b3;
  font-size: 12px;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent .meta a {
  color: #999999;
  text-decoration: none;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .jobAlrimContent .meta a:hover {
  text-decoration: underline;
}
.jobAlrim .jobAlrimBodyClass ul.jobAlrimUl li.jobAlrimLi .message {
  display: none;
  position: absolute;
  top: 0;
  left: 0;
  overflow: hidden;
  height: 100%;
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
}
.jobAlrim footer a {
  background: #bd6982;
  display: block;
  width: 100%;
  padding: 10px 15px;
  color: #ffffff;
  font-size: 14px;
  text-align: center;
  text-decoration: none;
  box-sizing: border-box;
}
.jobAlrim footer a:hover {
  background: #cd8ca0;
  -webkit-transition: background 0.3s linear 0s;
  -moz-transition: background 0.3s linear 0s;
  -ms-transition: background 0.3s linear 0s;
  -o-transition: background 0.3s linear 0s;
  transition: background 0.3s linear 0s;
}
.info {
  width: 300px;
  margin: 25px auto;
  text-align: center;
}
.info h1 {
  margin: 0;
  padding: 0;
  font-size: 24px;
  font-weight: 400;
  color: #333333;
}
.info span {
  color: #666666;
  font-size: 12px;
}
.info span a {
  color: #000000;
  text-decoration: none;
}
.info span .fa {
  color: #bd6982;
}
.info span .spoilers {
  color: #999999;
  margin-top: 5px;
  font-size: 10px;
}


</style>