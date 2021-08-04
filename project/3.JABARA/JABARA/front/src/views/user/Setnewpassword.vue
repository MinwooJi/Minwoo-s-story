<template>
  <div class="user" id="findpassword">
    <div class="wrapC">
      <h1>비밀번호 찾기</h1>
      <div ref="findpwbody">
        <div class="input-with-label">
          <input v-model="userPw1" id="userPw1" placeholder="새로운 비밀번호를 입력하세요." type="password" />
          <label for="userPw1">새로운 비밀번호</label>
        </div>
        <div class="input-with-label">
          <input v-model="userPw2" id="userPw2" placeholder="비밀번호 확인." type="password" />
          <label for="userPw1">비밀번호 확인</label>
        </div>
        <button @click="findpassword" class="btn btn--back btn--login">비밀번호 변경</button>
      </div>
    </div>
  </div>
</template>
<script>
import UserApi from "../../apis/userAPI";
export default {
  data: () => {
    return {
      userId: "",
      userPw1: "",
      userPw2: ""
    };
  },
  created: function() {
    this.userId = this.$session.get("requestUserId");
  },
  methods: {
    findpassword() {
      // axios통신으로 비밀번호 변경이 완료되었다면 세션으로 넘겼던 리퀘스트 아이디 정보는 페기 하고 로그인 유지
      if (this.userPw1 == this.userPw2) {
        let data = {
          userId: this.userId,
          userPw: this.userPw1
        };
        UserApi.requestSetNewPassword(
          data,
          res => {
            console.dir(res.data);
            this.$session.remove("requestUserId");
            this.$session.remove("authCodeByServer");
            this.$session.set("loginUserId", this.userId);
            alert("비밀번호가 변경되었습니다.");
            this.$router.push("/jobara");
          },
          error => {
            console.log(error);
          }
        );
      } else {
        alert("비밀번호가 일치하지 않습니다.");
      }
    },
    logout() {
      this.$session.destroy();
      location.reload();
    }
  }
};
</script>
