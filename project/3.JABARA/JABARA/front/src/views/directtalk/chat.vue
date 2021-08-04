<template>
<html class="jobchathtml">
  <body class="jobchatbody">
    <div class="jobchatcontainer" ng-cloak ng-app="chatApp">
      <div class="jobchatbox" ng-controller="MessageCtrl as chatMessage">
        <div class="jobchatbox__user-list">
          <p class="chat-p">채팅 리스트</p>
          <div v-if="chatList.length==0" class="c">
            <p class="chat-script">
              채팅 리스트가 비어있습니다!
              <br />상대방에게 DT를 보내보세요!
            </p>
            <p class="chat-script-two">
              DT를 보내려면?
              <br />&#128270;ID검색
              <i class="far fa-hand-point-right"></i> DirectMessage
            </p>
          </div>
          <ul class="jobchat-ul" v-else>
            <li class="jobchat-li" v-for="(item,idx) in chatList" :key="idx">
              <img
                :src="profileimageHtml[idx]"
                onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
                class="chatprofile"
              />
              <div class="jobchatname" @click="selectUser(item, idx)">{{chatList[idx].OTHER_ID}}</div>
            </li>
          </ul>
        </div>
        <div class="jobchatbox__messages" v-html="totalHtml" ref="messageDisplay"></div>
        <div class="chatbutton">
          <input
            class="message-to-send"
            v-model="message"
            @keyup.enter="sendMessage"
            @keyup.space="scrollTop"
            type="text"
            name="message-to-send"
            id="message-to-send"
            placeholder="Type your message"
          />
          <button class="chatbutton chatinputbtn" @click="sendMessage">Send</button>
        </div>
      </div>
    </div>
  </body>
</html>
</template>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.5.13/vue.js"></script>
<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import ChatAPI from "@/apis/chatAPI";
import UserApi from "../../apis/userAPI";

var scroll = new SmoothScroll();

export default {
  name: "Directtalk",
  data() {
    return {
      clickedUser: false,
      userId: "",
      otherId: "",
      content: "",
      recvList: [],
      sendList: [],
      chatList: [],
      userList: [],
      imgList: [],
      imgIndexList: [],
      message: "",
      totalHtml: "",
      profileimageHtml: [],
      idx: "",
      isSelected: 0,
      recvHtml:
        ' <li><div  class="message-data"><span class="message-data-name"> {{recvList[idxx].USER_ID}}</span><span class="message-data-time">{{recvList[idxx].DATE}}</span></div><div class="jobchatmessage my-message" >{{recvList[idxx].CONTENT}}</div></li>',
      sendHtml:
        '<li class="jobchatclearfix"><div class="message-data align-right" ><span class="message-data-time" >{{sendList[idxx].DATE}}</span> &nbsp; &nbsp;<span class="message-data-name" >{{sendList[idxx].USER_ID}}</span></div><div class="message other-message float-right">{{sendList[idxx].CONTENT}}</div> </li>'
    };
  },
  mounted() {
    this.connect();
  },
  created() {
    // this.downScroll();
    // (프로필을 통해서 타고 온 거라면)
    this.otherId = this.$route.query.otherId;
    if (this.otherId != null) {
      this.$session.set("otherId", this.otherId);
      this.selectChat();
    }
    this.userId = this.$session.get("loginUserId");
    console.log(this.otherId);
    this.userList.push(this.otherId);

    ChatAPI.selectChatList(this.userId, res => {
      // this.chatList = new Array(res.length);
      // this.imgList = new Array(res.length);
      for (var i = 0; i < res.data.length; i++) {
        console.log("res.data.lenght : " + res.data.length);
        console.log(res.data);
        if (res.data[i].OTHER_ID == this.$session.get("loginUserId")) {
          var temp = res.data[i].OTHER_ID;
          console.log("temp : " + temp);
          res.data[i].OTHER_ID = res.data[i].USER_ID;
          res.data[i].USER_ID = temp;
        }
        console.log(res.data[i]);
        console.log("사진에 쓰일 OTHER ID는 ?" + res.data[i].OTHER_ID);
        this.otherId = res.data[i].OTHER_ID;
        console.log(this.otherId);
        this.profileimageHtml[i] =
          `https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//` +
          this.otherId +
          ``;
        console.log(this.profileimageHtml[i]);
        this.chatList.push(res.data[i]);
        console.log(this.chatList);
        // this.selectImage(res.data[i].OTHER_ID,i);
        console.log("chatList : ");
      }
    });
  },
  methods: {
    scrollTop() {
      scroll.animateScroll(0);
    },
    makeUrl(id) {
      console.log("id ????" + id);
      console.log(
        "이미지 URL : https://jobara.s3.ap-northeast-2.amazonaws.com/" + id
      );
      return (
        `<img
              :src="https://jobara.s3.ap-northeast-2.amazonaws.com/` +
        id +
        `" onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'`` alt="프로필사진"
              class="profile"
            />`
      );
    },

    sendMessage: function() {
      this.send();
      ChatAPI.insertChat({
        userId: this.$session.get("loginUserId"),
        otherId: this.$session.get("otherId"),
        content: this.message
      });
      this.message = "";
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userId: this.userId,
          otherId: this.$session.get("otherId"),
          content: this.message
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
            console.log("otherId는 : " + this.otherId);
            const msg = {
              userId: this.$session.get("loginUserId"),
              otherId: this.$session.get("otherId")
            };
            ChatAPI.selectChat(msg, res => {
              this.recvList = [];
              this.sendList = [];
              for (var i = 0; i < res.data.length; i++) {
                //   console.log("USERID는?"+res.data[i].USER_ID);
                if (res.data[i].USER_ID != this.$session.get("loginUserId")) {
                  this.recvList.push(res.data[i]);
                } else {
                  this.sendList.push(res.data[i]);
                }
                this.selectChat();
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
    selectUser(item, idx) {
      this.clickedUser = true;
      this.idx = idx;
      if (item.USER_ID == this.$session.get("loginUserId")) {
        this.$session.set("otherId", item.OTHER_ID);
      } else {
        this.$session.set("otherId", item.USER_ID);
      }
      console.log("세션에 저장한 아이디 : " + this.$session.get("otherId"));
      const msg = {
        userId: this.userId,
        otherId: this.otherId
      };
      this.selectChat();
    },
    // 채팅 조회하기
    selectChat() {
      this.isSelected = 1;
      const msg = {
        userId: this.$session.get("loginUserId"),
        otherId: this.$session.get("otherId")
      };
      console.log("msg의 otherId = " + msg.otherId);
      ChatAPI.selectChat(msg, res => {
        this.recvList = [];
        this.sendList = [];
        this.totalHtml = "";
        for (var i = 0; i < res.data.length; i++) {
          console.log("USERID는?" + res.data[i].USER_ID);
          //undefined 일때는 안띄어준다
          if (res.data[i].CONTENT != null) {
            if (res.data[i].USER_ID != this.$session.get("loginUserId")) {
              // this.selectImage(re s.data[i].USER_ID);
              this.totalHtml +=
                '<div class="jobchatbox__messages__other-message--ind-message"><p class="message">' +
                res.data[i].USER_ID +
                '<p class="message">' +
                res.data[i].DATE +
                '<p class="message" >' +
                res.data[i].CONTENT +
                "</p></div>";
              // console.log("recv : ");
              // console.log(this.recvList);
            } else {
              // this.selectImage(res.data[i].OTHER_ID);
              this.totalHtml +=
                '<div class="jobchatbox__messages__user-message--ind-message"><p class="message">' +
                res.data[i].USER_ID +
                '<p class="message">' +
                res.data[i].DATE +
                '<p class="message" >' +
                res.data[i].CONTENT +
                "</p></div>";
              console.log(this.totalHtml);
              // this.sendList.push(res.data[i]);
              // console.log("send : ");
              // console.log(this.sendList);
            }
            this.recvList.push(res.data[i]);
          }

          ChatAPI.selectChatList(this.userId, res => {
            this.chatList = [];
            // this.chatList = new Array(res.length);
            // this.imgList = new Array(res.length);
            for (var i = 0; i < res.data.length; i++) {
              console.log("res.data.lenght : " + res.data.length);
              console.log(res.data);
              if (res.data[i].OTHER_ID == this.$session.get("loginUserId")) {
                var temp = res.data[i].OTHER_ID;
                console.log("temp : " + temp);
                res.data[i].OTHER_ID = res.data[i].USER_ID;
                res.data[i].USER_ID = temp;
              }
              console.log(res.data[i]);
              console.log("사진에 쓰일 OTHER ID는 ?" + res.data[i].OTHER_ID);
              this.otherId = res.data[i].OTHER_ID;
              console.log(this.otherId);
              this.profileimageHtml[i] =
                `https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//` +
                this.otherId +
                ``;
              console.log(this.profileimageHtml[i]);
              this.chatList.push(res.data[i]);
              console.log(this.chatList);
              // this.selectImage(res.data[i].OTHER_ID,i);
              console.log("chatList : ");
            }
            // if(this.imgList==res.length){
            //   for (var i = 0; i < res.data.length; i++) {
            //     this.chatList[i] = res.data[i];
            //     console.log("chatList : ");
            //     console.log(this.chatList);
            // }
            // }
          });
        }
      });
    },
    selectImage(otherId, idx) {
      console.log("select Image에서 받은 otherId" + otherId);
      UserApi.requestAWSUserImage(
        otherId,
        res => {
          console.log("idx???????????????????????" + idx);
          this.imgList[idx] = res.data;
          console.log("이미지는 : " + res.data);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.jobchatbox__user-list {
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.jobchatbox__user-list::-webkit-scrollbar {
  display: none;
}
.chat-script-two {
  text-align: center;
  padding-top: 20px;
  font-family: "Do Hyeon", sans-serif;
  color: rgba(255, 255, 255, 0.9);
  font-size: 80%;
}
.chat-script {
  /* position: absolute; */
  text-align: center;
  padding-top: 100px;
  font-family: "Do Hyeon", sans-serif;
  color: rgba(255, 136, 136, 0.9);
  /* top: 40%; */
  /* left: 25%; */
}
.jobchat-li:hover {
  cursor: pointer;
  background: rgba(255, 174, 136, 0.795);
}
.chatbutton input:hover {
  border: none !important;
}
.chatbutton input {
  border: 1px solid rgba(201, 201, 201, 0.356);
  border-bottom: none;
  font-family: "Do Hyeon", sans-serif;
}
.chatbutton {
  font-family: "Do Hyeon", sans-serif;
  color: white;
}
.chatbutton:hover {
  color: rgba(223, 100, 96, 0.637);
}
.chatinputbtn {
  width: 100%;
  height: 100%;
}
/* input 태그 */
.message-to-send {
  height: 100%;
  float: left;
  width: 85%;
}
div.chatbutton {
  height: 10%;
  width: 75%;
  background: #f79d7b;
}

button.chatbutton {
  float: left;
  width: 10%;
  height: 50%;
  margin: 2%;
}
html.jobchathtml {
  font-family: "Do Hyeon", sans-serif;
  background: linear-gradient(120deg, #f88882a4, #f9db9b98);
  overflow: hidden;
}
body.jobchatbody {
  font-family: "Do Hyeon", sans-serif;
  background: linear-gradient(120deg, #f88782, #f9dc9b);
  overflow: hidden;
}
.jobchat-ul {
  height: 100%;
  /* margin-top: 30px; */
}
.jobchat-li {
  height: 70px;
  width: 100%;
  border-bottom: 1px solid rgb(122 113 101 / 15%);
  top: 50%;
}
.chatprofile {
  width: 50px;
  height: 50px;
  background-color: mediumaquamarine;
  display: inline-table;
  float: left;
  border-radius: 50%;
  margin-top: 4%;
  margin-left: 5%;
  margin-bottom: 7px;
}

.jobchatcontainer {
  overflow: auto;
  margin-top: 30px;
  position: relative;
  height: 100vh;
  width: 100vw;
}

.jobchatbox {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* background: linear-gradient(120deg, #f99190, #f9dc9b); */
  width: 70%;
  height: 80%;
  margin: 0 auto;
  border-radius: 0.2em;
  /* position: relative; */
  box-shadow: 1px 1px 12px rgba(0, 0, 0, 0.1);
}
.jobchatbox__messages {
  width: 75%;
  height: 90%;
  overflow: auto;
  overflow-x: hidden;
  padding: 5% 0;
}

.jobchatbox__messages::-webkit-scrollbar {
  width: 6px;
}
.jobchatbox__messages::-webkit-scrollbar-track {
  background-color: #ffd9d9;
}
.jobchatbox__messages::-webkit-scrollbar-thumb {
  background-color: #ff9885a1;
  border-radius: 10px;
}
.jobchatbox__messages::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 31, 31, 0.541);
}

.jobchatbox__messages .jobchatbox__messages__other-message--ind-message {
  float: right;
}
.jobchatbox__messages .jobchatbox__messages__user-message--ind-message {
  float: left;
}
.jobchatbox__messages .jobchatbox__messages__user-message {
  width: 450px;
}
.jobchatbox__messages .jobchatbox__messages__user-message--ind-message {
  background: rgb(250 184 169);
  padding: 1em 0;
  height: auto;
  width: 35%;
  border-radius: 5px;
  margin-left: 55%;
  margin-right: 3%;
  margin-top: 3%;
  flex-wrap: wrap;
  word-wrap: break-word;
}
.jobchatbox__messages__user-message--ind-message p.message {
  font-family: "Do Hyeon", sans-serif;
}
.jobchatbox__messages__user-message--ind-message p:nth-last-child(1) {
  font-size: 1em;
  margin-left: 20px;
}

.jobchatbox__messages__user-message--ind-message p:nth-child(1) {
  color: rgb(255, 70, 132);
}
.jobchatbox__messages .jobchatbox__messages__other-message {
  width: 450px;
}
.jobchatbox__messages .jobchatbox__messages__other-message--ind-message {
  background: rgb(250 221 164 / 51%);
  padding: 1em 0;
  height: auto;
  width: 35%;
  border-radius: 5px;
  margin-left: 2%;
  margin-right: 55%;
  margin-top: 3%;
  flex-wrap: wrap;
  word-wrap: break-word;
}
.jobchatbox__messages .jobchatbox__messages__other-message--ind-message p {
  font-family: "Do Hyeon", sans-serif;
}
.jobchatbox__messages
  .jobchatbox__messages__other-message--ind-message
  p:nth-last-child(1) {
  font-size: 1em;
  margin-left: 20px;
}
.jobchatbox__messages
  .jobchatbox__messages__other-message--ind-message
  p:nth-child(1) {
  color: rgb(255, 76, 70);
}
.jobchatname {
  float: left;
  color: #fff;
  margin-top: 10%;
  margin-left: 5%;
  font-family: "Do Hyeon", sans-serif;
}
.jobchatname:hover {
  cursor: pointer;
}
/*
.jobchatbox__messages__user-message--ind-message > p.message {
  color: #FFF;
  font-size: 0.7em;
  margin: 0 2.8em;
} */
.jobchatbox__user-list {
  background: rgba(255, 255, 255, 0.1);
  width: 25%;
  height: 100%;
  float: right;
  border-top-right-radius: 0.2em;
  border-bottom-right-radius: 0.2em;
}
.chat-p {
  padding-top: 20px;
  text-align: center;
  height: 7%;
  width: 100%;
  font-size: 120%;
  color: rgba(26, 17, 17, 0.87);
  font-family: "Do Hyeon", sans-serif;
}
.jobchatbox__user-list h1 {
  background: rgba(255, 255, 255, 0.05);
  color: rgba(255, 255, 255, 0.9);
  font-size: 0.9em;
  padding: 1em;
  margin: 0;
  font-weight: 300;
  text-align: center;
}
/* .jobchat-ul{
  margin: 1em 0.7em;
} */
/* .jobchat-ul {
  background: rgba(23, 190, 187, 0.8);
} */

p.message {
  float: left;
  text-align: left;
  margin: -0.25em 2em;
  font-size: 0.7em;
  font-weight: 300;
  color: #fff;
  width: 80%;
}
p.message:nth-last-child(1) {
  margin-top: 10px;
  line-height: 1em;
}

.jobchatclearfix:after {
  visibility: hidden;
  display: block;
  font-size: 0;
  content: " ";
  clear: both;
  height: 0;
}
.jobchatbox form {
  background: #222;
}
.jobchatbox form input {
  background: rgba(255, 255, 255, 0.03);
  position: absolute;
  bottom: 0;
  left: 0;
  border: none !important;
  width: 75%;
  padding: 1.2em;
  outline: none;
  color: rgba(255, 255, 255, 0.9);
  font-weight: 300;
}

::-webkit-input-placeholder {
  color: rgba(255, 255, 255, 0.9);
}

:-moz-placeholder {
  color: rgba(255, 255, 255, 0.9);
}

::-moz-placeholder {
  color: rgba(255, 255, 255, 0.9);
}

:-ms-input-placeholder {
  color: rgba(255, 255, 255, 0.9);
}
</style>