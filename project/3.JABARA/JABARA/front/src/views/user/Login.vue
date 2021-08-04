<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1 style="text-align:center;" class="jobara-h1">JOBARA에서 취뽀하자&#128588;</h1>

      <div class="input-with-label">
        <input v-model="userId" id="userId" placeholder="아이디를 입력하세요." type="text" />
        <label for="userId">아이디</label>
      </div>

      <div class="input-with-label">
        <input v-model="userPw" type="password" id="userPw" placeholder="비밀번호를 입력하세요." />
        <label for="userPw">비밀번호</label>
      </div>
      <button
        class="login-button"
        @click="onLogin"
        :disabled="isSubmit"
        :class="{ disabled: !isSubmit }"
      >로그인</button>

      <div class="sns-login">
        <div class="text">
          <p>SNS 간편 로그인</p>
          <div class="bar"></div>
        </div>
        <kakaoLogin :component="component" />
        <!-- <GoogleLogin :component="component" /> -->
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
          <router-link to="/user/findpassword" class="btn--text">비밀번호 찾기</router-link>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/user/join" class="btn--text">가입하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/user.scss";
import KakaoLogin from "@/components/user/snsLogin/Kakao.vue";
// import GoogleLogin from "@/components/user/snsLogin/Google.vue";
// import axios from "axios";
import UserApi from "../../apis/userAPI";
export default {
  name: "Login",
  data() {
    return {
      userId: "",
      userPw: "",
      isSubmit: false
    };
  },
  components: {
    KakaoLogin
    // GoogleLogin
  },
  created() {
    this.component = this;
  },
  methods: {
    checkForm() {
      console.dir("checkform");
    },
    onLogin() {
      if (!this.isSubmit) {
        let err = true;
        let msg = "";
        !this.userId && ((msg = "아이디를 입력해주세요"), (err = false));
        err &&
          !this.userPw &&
          ((msg = "비밀번호를 입력해주세요"), (err = false));
        if (!err) {
          alert(msg);
          return;
        }
        this.isSubmit = true;
        let data = {
          userId: this.userId,
          userPw: this.userPw
        };
        UserApi.requestLogin(
          data,
          res => {
            if (!res.data.userId) {
              // 아이디가 다를시 500에러 생김
              alert("아이디 비밀번호를 확인해주세요");
              this.isSubmit = false;
            } else {
              this.$session.set("loginUserId", res.data.userId);
              //this.$session.set("loginUserNickname", response.userNickname);
              this.funcprofileimage();
              this.$session.set("loginUserNickName", res.data.userNickname);
              console.log("별명" + res.data.userNickname);
              this.$router.push("/jobara");
            }
          },
          error => {
            console.log(error);
          }
        );
      }
    },
    funcprofileimage() {
      UserApi.requestAWSUserImage(
        this.$session.get("loginUserId"),
        res => {
          console.log("이미지");
          console.log(res.data);
          this.$session.set("loginUserProfileImage", res.data);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
* {
  font-family: "Do Hyeon", sans-serif;
}
.wrapC {
  margin-top: 150px;
}
.login-button {
  border-radius: 5px;
  width: 100%;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 38, 0, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 187, 99, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(255, 54, 104, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  color: #fff;
  border: none;
  position: relative;
  height: 50px;
  font-size: 100%;
  font-weight: 500;
  padding: 0 2em;
  cursor: pointer;
  transition: 800ms ease all;
  outline: none;
}
.login-button:hover {
  background: #fff;
  color: #fd6145;
}
.login-button:before,
.login-button:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 38, 0, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 187, 99, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(255, 54, 104, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  transition: 400ms ease all;
}
.login-button:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.login-button:hover:before,
.login-button:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
/* .jobara-h1 {
   text-decoration : none;
   border-bottom : 5px solid #ffcd60;
   padding-bottom : 2px;
} */
</style>
