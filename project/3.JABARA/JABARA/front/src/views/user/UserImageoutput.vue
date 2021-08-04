<template>
  <div id="app">
    <br />
    유저이름: {{this.userId}}
    <br />
    <button v-on:click="sendMessage" value="messaeg">알림 보기</button>
    <br />
    <button v-on:click="insertLike">좋아요 누르기</button>
    <!-- 내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >-->
    <div v-for="(item, idx) in recvList" :key="idx">
      <h3>내용: {{recvList[idx]}}</h3>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import ActivityAPI from "@/apis/activityAPI";
import LikeAPI from "@/apis/likeAPI";

export default {
  name: "App",
  data() {
    return {
      userId: "",
      postNumber: 0,
      targetuserId: "",
      message: "",
      recvList: []
    };
  },
  created() {
    this.userId = this.$session.get("loginUserId");
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect();
  },
  methods: {
    insertLike() {
      LikeAPI.insertLike({
        userId: this.userId,
        targetuserId: "wlalsdn0406",
        postNumber: 29
      });
      this.send();
    },
    sendMessage: function() {
      console.log("버튼이 클릭되었습니다");
      this.send();
    },
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
      const serverURL = "http://localhost:8090/jobara";
      let socket = new SockJS(serverURL);
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
              this.recvList = [];
              for (var i = 0; i < res.data.length; i++) {
                this.recvList.push(res.data[i].content);
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
    }
  }
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
