<template>
  <div class="wrapper">
    <div class="container">
      <!-- <img src="@/assets/NAMU.png" alt="voda 로고" width="100" height="150" /> -->

      <h1>VORACAY</h1>
      <form class="form" @submit.prevent="login">
        <input type="text" v-model='user_number' placeholder="사번입력" />
        <input type="password" v-model='user_password' placeholder="비밀번호" />
        <button id="login-button">Login</button>
      </form>
    </div>

    <ul class="bg-bubbles">
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
  </div>
</template>
<script>
import http from '../http-common';

export default {
  name: "Voracay",
  data() {
    return {
      user_number: '',
      user_password: ''
    };
  },
  methods: {
     login() {
      let formData = new FormData();
      formData.append('user_number', this.user_number);
      formData.append('user_password', this.user_password);

      if(this.user_number == "") {
        this.toast('사번을 입력하세요', 'error');
        return;
      }else if(this.user_password == ""){
        this.toast('비밀번호를 입력하세요', 'error');
        return;
      }

      http.post('/v2/login/', formData)
      .then( res => {
        if(res.status == 200){
          let msg = res.data.message;
          if(msg == "success"){
            let token = res.data.token;
            let userName = res.data.user_name;
            this.$store.commit("setUserToken", token);
            this.$store.commit("setUserName", userName);
            sessionStorage.setItem("token", token);
            sessionStorage.setItem("userName", userName);
            this.$router.push("/main");
            http.defaults.headers.common['Bearer'] = token;
          }
          else this.toast(msg, 'error');
        }else{
          this.toast('Login falied', 'error');   
        }
       
      }).catch( error => {
        this.toast('Login falied', 'error');  
      })
    },
    toast(msg, type){
      this.$toast.open({
            message: msg,
            type: type,
            position: 'top-right',
          });
    },
  },
  created() {
    if (this.$store.state.isLogin && !this.$store.state.userToken) {
      this.$router.push("/main");
    }
  },
};
</script>
<style scoped>
@import url("../../src/assets/modules/vue-toast-notification/dist/theme-sugar.css");

@font-face {
  font-family: "Fredoka One";
  src: url("../assets/font/login/FredokaOne-Regular.ttf");
}
@font-face {
  font-family: "Noto Sans KR";
  src: url("../assets/font/headerFont/NotoSansKR-Black.otf");
}
@font-face {
  font-family: "Dokdo";
  src: url("../assets/font/headerFont/EastSeaDokdo-Regular.ttf");
}
input::placeholder {
  color: white;
}
.wrapper {
  background: linear-gradient(to bottom right,#0a07b8, #291c74, #090766, #1a187a,#291c74);
  /* background:rgb(41 58 151); */
  position: absolute;
  /* top: 100px; */
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.wrapper.form-success .container h1 {
  -webkit-transform: translateY(85px);
  transform: translateY(85px);
}
.container {
  max-width: 600px;
  margin: 100px auto;
  padding: 80px 0;
  height: 400px;
  text-align: center;
}
.container h1 {
  color: #fff;
  font-size: 40px;
  -webkit-transition-duration: 1s;
  transition-duration: 1s;
  -webkit-transition-timing-function: ease-in-put;
  transition-timing-function: ease-in-put;
  font-weight: 250;
  font-family: "Fredoka One", cursive;
  margin-bottom: 150px;
}
form {
  padding: 20px 0;
  position: relative;
  z-index: 2;
}
form input {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  outline: 0;
  border: 1px solid rgba(255, 255, 255, 0.4);
  background-color: rgba(255, 255, 255, 0.2);
  width: 80%;
  border-radius: 3px;
  padding: 10px 15px;
  margin: 0 auto 10px auto;
  display: block;
  text-align: center;
  font-size: 18px;
  color: white;
  -webkit-transition-duration: 0.25s;
  transition-duration: 0.25s;
  font-weight: 300;
}
form input:hover {
  background-color: rgba(255, 255, 255, 0.4);
}
form input:focus {
  background-color: white;
  width: 90%;
  color: rgb(41 58 151);
}
form button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  outline: 0;
  background-color: white;
  border: 0;
  padding: 10px 15px;
  color: rgb(41 58 151);
  border-radius: 3px;
  width: 80%;
  cursor: pointer;
  font-size: 18px;
  -webkit-transition-duration: 0.25s;
  transition-duration: 0.25s;
  margin-top: 20px;

}
form button:hover {
  background-color: #f5f7f9;
}
.bg-bubbles {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.bg-bubbles li {
  position: absolute;
  list-style: none;
  display: block;
  width: 40px;
  height: 40px;
  background-color: rgba(255, 255, 255, 0.15);
  bottom: -160px;
  -webkit-animation: square 25s infinite;
  animation: square 25s infinite;
  -webkit-transition-timing-function: linear;
  transition-timing-function: linear;
}
.bg-bubbles li:nth-child(1) {
  left: 10%;
}
.bg-bubbles li:nth-child(2) {
  left: 20%;
  width: 80px;
  height: 80px;
  -webkit-animation-delay: 2s;
  animation-delay: 2s;
  -webkit-animation-duration: 17s;
  animation-duration: 17s;
}
.bg-bubbles li:nth-child(3) {
  left: 25%;
  -webkit-animation-delay: 4s;
  animation-delay: 4s;
}
.bg-bubbles li:nth-child(4) {
  left: 40%;
  width: 60px;
  height: 60px;
  -webkit-animation-duration: 22s;
  animation-duration: 22s;
  background-color: rgba(255, 255, 255, 0.25);
}
.bg-bubbles li:nth-child(5) {
  left: 70%;
}
.bg-bubbles li:nth-child(6) {
  left: 80%;
  width: 120px;
  height: 120px;
  -webkit-animation-delay: 3s;
  animation-delay: 3s;
  background-color: rgba(255, 255, 255, 0.2);
}
.bg-bubbles li:nth-child(7) {
  left: 32%;
  width: 160px;
  height: 160px;
  -webkit-animation-delay: 7s;
  animation-delay: 7s;
}
.bg-bubbles li:nth-child(8) {
  left: 55%;
  width: 20px;
  height: 20px;
  -webkit-animation-delay: 15s;
  animation-delay: 15s;
  -webkit-animation-duration: 40s;
  animation-duration: 40s;
}
.bg-bubbles li:nth-child(9) {
  left: 25%;
  width: 10px;
  height: 10px;
  -webkit-animation-delay: 2s;
  animation-delay: 2s;
  -webkit-animation-duration: 40s;
  animation-duration: 40s;
  background-color: rgba(255, 255, 255, 0.3);
}
.bg-bubbles li:nth-child(10) {
  left: 90%;
  width: 160px;
  height: 160px;
  -webkit-animation-delay: 11s;
  animation-delay: 11s;
}
@-webkit-keyframes square {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
  100% {
    -webkit-transform: translateY(-700px) rotate(600deg);
    transform: translateY(-700px) rotate(600deg);
  }
}
@keyframes square {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
  100% {
    -webkit-transform: translateY(-700px) rotate(600deg);
    transform: translateY(-700px) rotate(600deg);
  }
}
</style>
