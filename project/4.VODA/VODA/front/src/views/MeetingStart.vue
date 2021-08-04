<template>
  <div class="meeting-start">
    <div class="meeting-box">
      <div class="room-title">
        <h3>{{ memberInfo.username }}님의 미팅룸</h3>
      </div>
      <!-- video-box 화면 대신임 -->
      <div class="video-box">
        <div class="video-header">
          <div class="nickname-box">
            <a href="/customer">nickname</a>
          </div>
        </div>
        <div class="video-center">
          <img src="@/assets/profile.png" alt="" width="200" height="200" />
        </div>
        <div class="video-bottom">
          <span class="mike-img">
            <img src="@/assets/mike.png" alt="" width="50" height="50" />
          </span>
          <span class="film-img">
            <img src="@/assets/film.png" alt="" width="50" height="50" />
          </span>
          <span class="meeting-enter-button">
            <a href="/meeting/room"
              ><button class="meeting-enter-link"><h5>참여하기</h5></button></a
            >
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MeetingStart",
  data() {
    return {
      memberInfo: {
        id: "",
        email: "",
        username: "",
      },
    };
  },
  created() {
    this.info();
  },
  methods: {
    info() {
      if (this.$cookies.get("token")) {
        const config = {
          headers: {
            Authorization: `Bearer ${this.$cookies.get("token")}`,
          },
        };
        axios
          .post(
            process.env.VUE_APP_DJANGO_API_SERVER_URL + "api/users/info/",
            null,
            config
          )
          .then((res) => {
            // console.log(res.data);
            this.memberInfo.id = res.data.id;
            this.memberInfo.username = res.data.username;
            this.memberInfo.email = res.data.email;
          })
          .catch(() => {
            // console.log(err);
          });
      }
    },
  },
};
</script>

<style>
.meeting-start {
  background-image: linear-gradient(
    to left bottom,
    #c8f7f4,
    #c4f9f1,
    #c2fbed,
    #c2fde7,
    #c3fee0,
    #c3fee0,
    #c4ffe0,
    #c4ffe0,
    #c3ffe7,
    #c3ffee,
    #c5fff4,
    #c8fff9
  );
  width: 100vw;
  height: 100vh;
  position: fixed;
  margin: 0;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1;
  font-family: "Viga", sans-serif;
  color: rgb(255, 255, 255);
  font-size: 20px;
}
.video-box {
  background: gray;
  width: 800px;
  height: 500px;
  margin: 50px 100px;
}
.meeting-box {
  width: 1000px;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.nickname-box {
  width: 100px;
  padding: 10px;
  background: white;
  border-radius: 50px;
}
.video-center {
  padding: 100px;
}
.video-center img {
  border: 5px solid black;
  border-radius: 50%;
}
.mike-img img {
  border-radius: 50%;
  border: 1px solid green;
}
.film-img img {
  border-radius: 50%;
  border: 1px solid red;
}

.meeting-enter-link {
  border-radius: 10px;
}
</style>