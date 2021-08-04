<template>
  <div id="join">
    <div v-if="termPopup">
      <TermOfUse @closeTerm="closeTerm" />
    </div>
    <div class="user join wrapC">
      <h3 style="text-align:center">
        IT취준생을 위한
        <br />JOBARA에 오신걸 환영합니다&#129498;
      </h3>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="userId" id="userId" placeholder="아이디를 입력하세요." type="text" />
          <label for="userId">아이디</label>
        </div>
        <div class="input-with-label">
          <input v-model="userNickName" id="userNickName" placeholder="닉네임을 입력하세요." type="text" />
          <label for="userNickName">닉네임</label>
        </div>
        <div class="input-with-label">
          <input v-model="userPw1" id="userPw1" placeholder="비밀번호를 입력하세요." type="password" />
          <label for="userPw1">비밀번호</label>
        </div>
        <div class="input-with-label">
          <input v-model="userPw2" id="userPw2" placeholder="비밀번호를 다시한번 입력하세요." type="password" />
          <label for="userPw2">비밀번호 확인</label>
        </div>
        <div class="input-with-label">
          <input v-model="userName" id="userName" placeholder="이름을 입력하세요." type="text" />
          <label for="userName">이름</label>
        </div>
        <div class="input-with-label">
          <input v-model="userEmail" id="userEmail" placeholder="gmail을 입력하세요." type="text" />
          <label for="userEmail">gmail</label>
        </div>
        <div class="input-with-label">
          <input v-model="userGitURL" id="userGitURL" placeholder="Git 주소를 입력하세요" type="text" />
          <label for="userGitURL">Git URL</label>
        </div>
      </div>
      <div class="labeln-btn">
        <label>
          <input v-model="isTerm" type="checkbox" id="term" />
          <span>약관을 동의합니다.</span>
        </label>
        <span @click="termPopup = true">약관보기</span>
        <button @click="join" class="button-block">가입하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import TermOfUse from "@/components/user/TermOfUse.vue";
import UserApi from "../../apis/userAPI";
export default {
  name: "Join",
  components: {
    TermOfUse
  },
  data() {
    return {
      userId: "",
      userPw1: "",
      userPw2: "",
      userName: "",
      userNickName: "",
      userEmail: "",
      userGitURL: "",
      isTerm: false,
      termPopup: false
    };
  },
  methods: {
    closeTerm() {
      this.termPopup = false;
    },
    chkEmail(str) {
      let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

      if (regExp.test(str)) return true;
      else return false;
    },

    join() {
      // 공란 확인후 알림메시지 출력할것.
      let regExp = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/gi;
      if (regExp.test(this.userId)) {
        this.userId = "";
        alert("아이디는 특수문자를 포함할수없습니다.");
        return;
      }
      let err = true;
      let msg = "";
      !this.userId && ((msg = "아이디를 입력해주세요"), (err = false));
      err &&
        !this.userNickName &&
        ((msg = "닉네임을 입력해주세요"), (err = false));
      err &&
        !this.userPw1 &&
        ((msg = "비밀번호를 입력해주세요"), (err = false));
      err &&
        !this.userPw2 &&
        ((msg = "비밀번호(확인)을 입력해주세요"), (err = false));
      err && !this.userName && ((msg = "이름을 입력해주세요"), (err = false));
      err && !this.userEmail && ((msg = "gmail을 입력해주세요"), (err = false));
      err &&
        !this.userGitURL &&
        ((msg = "Git URL을 입력해주세요"), (err = false));
      if (!err) {
        alert(msg);
        return;
      } else if (!this.isTerm) {
        alert("약관을 확인해 주세요.");
        return;
      }
      if (!this.chkEmail(this.userEmail)) {
        alert("올바른 e-mail형식이 아닙니다.");
        return;
      }
      if (this.userPw1 == this.userPw2) {
        let data = {
          userId: this.userId,
          userPw: this.userPw1,
          userName: this.userName,
          userNickname: this.userNickName,
          userEmail: this.userEmail,
          userGiturl: this.userGitURL
        };
        UserApi.requestJoin(
          data,
          res => {
            if (res.data == "success") {
              alert("회원가입 성공");
              this.$session.set("loginUserId", this.userId);
              this.$session.set("loginUserNickName", this.userNickName);
              this.$session.set(
                "loginUserProfileImage",
                "jobaraprofile//user.png"
              );
              this.$router.push("/");
            } else {
              alert("회원가입 실패");
            }
          },
          error => {
            console.log(error);
            alert("중복된 아이디입니다!");
          }
        );
      } else {
        alert("비밀번호를 확인하세요.");
      }
    }
  }
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
* {
  font-family: "Do Hyeon", sans-serif;
}
#join {
  height: 100vh !important;
}
.wrapC {
  margin-top: 100px;
  /* height: 80vh; */
}
.wrapC h3 {
  margin-bottom: 40px;
}

.user {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.label-text {
  margin-bottom: 50px;
}
.button-block {
  display: block;
  border-radius: 5px;
  width: 100%;
  height: 50px;
  background: radial-gradient(
      circle at 30% 0,
      rgba(255, 140, 94, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 197, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  /* margin-bottom: 100px; */
}
.button-block {
  border-radius: 5px;
  width: 100%;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 94, 156, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 59% 0,
      rgba(255, 242, 185, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 142, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
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
.button-block:hover {
  background: #fff;
  color: #fd9e45;
}
.button-block:before,
.button-block:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 94, 156, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 197, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  transition: 400ms ease all;
}
.button-block:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.button-block:hover:before,
.button-block:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
</style>
