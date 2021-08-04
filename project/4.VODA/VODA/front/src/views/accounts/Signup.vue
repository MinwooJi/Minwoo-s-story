<template>
  <div class="signup">
    <div class="signup-input">
      <div class="voda-logo">
        <!-- <img alt="voda logo" src="@/assets/logo_transparent.png" width="300" height="300"> -->
        <h1 class="voda-logo">SIGNUP</h1>
      </div>

      <div class="s-email-input signup-div">
        <label for="email">EMAIL </label>
        <input type="text" v-model="email" id="email" />
      </div>
      <div class="s-id-input signup-div">
        <label for="username">USERNAME </label>
        <input type="text" v-model="username" id="username" />
      </div>
      <div class="s-password1-input signup-div">
        <label for="password1">PASSWORD </label>
        <input id="password1" v-model="password1" type="password" />
      </div>
      <div class="s-password2-input signup-div">
        <label for="password2">Confirm Password</label>
        <input id="password2" v-model="password2" type="password" />
      </div>
      <div class="signup-button">
        <button @click="checkHandler">Sign up</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Signup",
  title: "VODA | Signup",
  data() {
    return {
      email: null,
      username: null,
      password1: null,
      password2: null,

      errorMessage: null,
    };
  },
  methods: {
    signup() {
      // console.log("signup@:", this.signupData);
      axios
        .post(process.env.VUE_APP_DJANGO_API_SERVER_URL + "api/users/signup/", {
          email: this.email,
          username: this.username,
          password: this.password1,
        })
        .then((res) => {
          if (res.data.message == "ok") {
            alert("등록이 완료되었습니다.");
            //처리하고 다른 경로로 이동
            this.$router.push("/");
          } else {
            alert("이메일을 중복입니다");
          }
        })
        .catch((err) => {
          this.errorMessage = err.response.data;
          alert("이메일을 중복입니다");
        });
    },
    checkHandler() {
      let err = true;
      let msg = "";

      err && !this.email && ((msg = "이메일을 확인해주세요."), (err = false));
      err &&
        !this.username &&
        ((msg = "닉네임을 입력해주세요."), (err = false));
      err &&
        !this.password1 &&
        ((msg = "비밀번호를 입력해주세요."), (err = false));
      err &&
        !this.password2 &&
        ((msg = "비밀번호확인을 입력해주세요."), (err = false));
      err &&
        this.password1 != this.password2 &&
        ((msg = "비밀번호가 다릅니다."), (err = false));

      if (!err) alert(msg);
      else this.signup();
    },
  },
};
</script>

<style scoped>
.signup {
  background: linear-gradient(-30deg, #e8c6ff, #bdabff, #eba9ff, #c061ff);
  background-size: 400% 400%;
  animation: gradient 10s ease infinite;
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
@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.signup-input {
  width: 300px;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.signup-div {
  display: inline-block;
  width: 100%;
  margin-bottom: 15px;
}
.signup-div label {
  display: block;
}
.signup-div input {
  width: 250px;
  height: 20px;
  border-radius: 5px;
  border: 2px solid rgba(255, 255, 255, 0.932);
  background: rgba(240, 255, 240, 0.185);
  font-family: "Viga", sans-serif;
  padding: 10px 20px;
  color: rgba(162, 49, 255, 0.863);
  font-size: 15px;
}
.signup-div input:hover {
  border: 2px solid rgba(145, 108, 245, 0.616);
}
.signup-div input:focus {
  /* input 클릭했을 때 검정 테두리 없애줌 */
  outline: none !important;
  border: 2px solid rgba(145, 108, 245, 0.616);
  box-shadow: 0 0 20px rgba(145, 108, 245, 0.616);
}

.signup-button button {
  justify-content: center;
  width: 295px;
  border-radius: 5px;
  height: 40px;
  transition: all ease 0.5s;
  border: 1px solid rgba(255, 255, 255, 0.932);
  background-color: rgba(255, 254, 255, 0.082);
  font-family: "Viga", sans-serif;
  color: white;
  font-size: 20px;
}
.signup-button button:hover {
  cursor: pointer;
  transform: scale(1.1);

  background-color: #b86ffc49;
}
.signup-button button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(255, 200, 255, 0.616);
  box-shadow: 0 0 20px rgba(255, 200, 255, 0.616);
}

/* .withus-logo {
  width: 100%;
}
.withus-logo img {
  display: block;
  margin: 0 auto;
  width: 80%;
  height: 80%;
} */
.voda-logo {
  width: 100%;
  font-size: 180%;
  text-align: center;
  font-family: "Cairo", sans-serif;
  opacity: 0.9;
  text-shadow: 2px 3px 0px #f7ecff;
  color: rgb(198, 185, 255);
  /* font: inherit; */
}
</style>