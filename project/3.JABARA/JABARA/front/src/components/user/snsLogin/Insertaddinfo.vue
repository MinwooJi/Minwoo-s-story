<template>
<div id="add-info">
  <div class="user join wrapC">
    <h1 style="text-align:center;">추가정보 입력</h1>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="userName" id="userName" placeholder="이름을 입력하세요." type="text" />
        <label for="userName">이름</label>
      </div>
      <div class="input-with-label">
        <input v-model="userEmail" id="userEmail" placeholder="이메일을 입력하세요." type="text" />
        <label for="userEmail">E-mail</label>
      </div>
      <div class="input-with-label">
        <input v-model="userGitURL" id="userGitURL" placeholder="Git 주소를 입력하세요" type="text" />
        <label for="userGitURL">Git URL</label>
      </div>
      <button @click="insertinfo" class="input-button">입력하기</button>
    </div>
    <!-- <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
    </label> -->
    <!-- <span @click="termPopup = true">약관보기</span> -->
    
  </div>
</div>
</template>

<script>
import axios from "axios";
export default {
  name: "Insertaddinfo",
  data() {
    return {
      userName: "",
      userEmail: "",
      userGitURL: "",
      isTerm: false
    };
  },
  methods: {
    insertinfo() {
      // 공란 확인후 알림메시지 출력할것.
      let err = true;
      let msg = "";
      !this.userName && ((msg = "이름을 입력해주세요"), (err = false));
      err &&
        !this.userEmail &&
        ((msg = "이메일를 입력해주세요"), (err = false));
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

      axios
        .post("url", {
          userName: this.userName,
          userEmail: this.userEmail,
          userGiturl: this.userGitURL,
          accessToken: this.$session.get("KakaoJoinTempToken")
        })
        .then(response => {
          if (response.data == "success") {
            alert("회원가입(추가정보 입력) 성공");
            this.$session.remove("KakaoJoinTempToken");
            this.$session.set("loginUserId", response.userId);
            this.$session.set("loginUserNickName", response.userNickName);
            this.$router.push("/user/login");
          } else {
            alert("회원가입(추가정보 입력) 실패");
          }
        });
    }
  }
};
</script>
<style scoped>
#add-info {
  width: 50%;
  height: 100%;
  /* position: relative; */
}
.wrapC {
  position: absolute;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 0 auto;
}
.input-button,
.input-button::after {
  -webkit-transition: all 0.2s;
	-moz-transition: all 0.2s;
  -o-transition: all 0.2s;
	transition: all 0.2s;
}


.input-button {
  border:2px solid rgb(255, 191, 191);
  padding: 10px;
  height: 50px;
  margin-top: 10px;
  text-align: center;
  background: none;
  position:relative;
  width: 100%;
  border-radius: 5px;
}

.input-button::before,
.input-button::after {
  background: 
  radial-gradient(circle at 30% 0, rgba(255, 140, 94, 0.5), rgba(255,0,0,0) 70.71%),
  radial-gradient(circle at 6.7% 75%, rgba(255, 197, 89, 0.5), rgba(0,0,255,0) 70.71%), 
  radial-gradient(circle at 93.3% 75%, rgba(245, 14, 52, 0.5),rgba(0,255,0,0) 70.71%) beige;
  content: '';
  position: absolute;
  z-index: -1;
}

.input-button:hover {
  color: white;
}
button:focus {
  border: none !important;
}
button:active {
  border: none !important;
}
.input-button {
  overflow: hidden;
}

.input-button::after {
  /*background-color: #f00;*/
  height: 100%;
  left: -35%;
  top: 0;
  transform: skew(50deg);
  transition-duration: 0.6s;
  transform-origin: top left;
  width: 0;
}

.input-button:hover:after {
  height: 100%;
  width: 135%;
}
</style>