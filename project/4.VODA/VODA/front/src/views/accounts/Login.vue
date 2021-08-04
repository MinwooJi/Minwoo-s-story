<template>
  <div class="login">
    <div class="login-input">
      <div class="voda-logo">
        <h1 class="voda-logo">LOGIN</h1>
      </div>
      <div class="id-input login-div">
        <label for="email">EMAIL </label>
        <input type="text" v-model="loginData.email" id="email" />
      </div>
      <div class="password-input login-div">
        <label for="password">PASSWORD </label>
        <input id="password" v-model="loginData.password" type="password" />
      </div>
      <div class="login-button">
        <button @click="login">LOGIN</button>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Login",
  title: "VODA | Login",
  data() {
    return {
      loginData: {
        email: null,
        password: null,
      },
      isLoggedIn: false,
    };
  },
  methods: {
    login() {
      let err = true;
      let msg = "";
      err &&
        !this.loginData.email &&
        ((msg = "이메일을 입력해주세요."), (err = false));
      err &&
        !this.verifyEmail() &&
        ((msg = "이메일 양식을 확인해주세요."), (err = false));
      err &&
        !this.loginData.password &&
        ((msg = "비밀번호를 입력해주세요."), (err = false));

      if (!err) alert(msg);
      else {
        // console.log(this.loginData);
        this.$emit("submit-login-data", this.loginData);
      }
    },
    verifyEmail() {
      var emailVal = this.loginData.email;
      var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (emailVal.match(regExp) != null) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.login {
  background: linear-gradient(-45deg, #a4ccff, #cec4ff, #f6daff, #adc9ff);
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

.login-input {
  width: 300px;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-div {
  display: inline-block;
  width: 100%;
  margin-bottom: 20px;
}
.login-div label {
  display: block;
}
.login-div input {
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
.login-div input:hover {
  border: 2px solid rgba(145, 108, 245, 0.616);
}
.login-div input:focus {
  /* input 클릭했을 때 검정 테두리 없애줌 */
  outline: none !important;
  border: 2px solid rgba(145, 108, 245, 0.616);
  box-shadow: 0 0 20px rgba(145, 108, 245, 0.616);
}

.login-button button {
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
.login-button button:hover {
  cursor: pointer;
  transform: scale(1.1);

  background-color: #b86ffc49;
}
.login-button button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(203, 134, 235, 0.63);
  box-shadow: 0 0 20px rgba(243, 175, 252, 0.747);
}

.voda-logo {
  width: 100%;
  font-size: 180%;
  text-align: center;
  font-family: "Cairo", sans-serif;
  opacity: 0.9;
  text-shadow: 2px 3px 0px #f1f1f1;
  color: rgb(210, 170, 255);
  /* font: inherit; */
}

.voda-logo img {
  display: block;
  margin: 0 auto;
}
</style>