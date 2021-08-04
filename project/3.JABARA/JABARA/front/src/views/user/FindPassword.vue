<template>
  <div class="user" id="findpassword">
    <div class="wrapC">
      <h1>&#128275;비밀번호 찾기</h1>
      <div ref="findpwbody">
        <div class="input-with-label">
          <input v-model="userId" id="userId" placeholder="아이디를 입력하세요." type="text" />
          <label for="userId">아이디</label>
        </div>
        <div class="input-with-label">
          <input v-model="userEmail" type="text" id="userEmail" placeholder="e-mail 을 입력하세요." />
          <label for="userEmail">이메일</label>
        </div>
        <button
          class="password-button"
          @click="findpassword"
          :disabled="isSubmit"
          :class="{ disabled: isSubmit }"
        >비밀번호 찾기</button>
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
      userEmail: "",
      error: {
        userId: false,
        userEmail: false
      },
      isSubmit: false
    };
  },

  methods: {
    findpassword() {
      // axios로 통신하고 일치하는 아이디를 찾는다면
      this.isSubmit = true;
      let data = {
        userId: this.userId,
        userEmail: this.userEmail
      };
      UserApi.requestFindPassword(
        data,
        res => {
          console.dir(res.data);
          if (res.data == "fail") {
            // 아이디가 다를시 500에러 생김
            alert("이메일을 확인해주세요");
            this.isSubmit = false;
          } else {
            this.$session.set("requestUserId", this.userId);
            this.$session.set("authCodeByServer", res.data);
            this.$router.push("/user/findpwauth");
          }
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
.user {
  margin-top: 7%;
} 
.password-button {
  display: block;
  border-radius: 5px;
  width: 100%;
  height: 50px;
  background: 
  radial-gradient(circle at 30% 0, rgba(255, 140, 94, 0.5), rgba(255,0,0,0) 70.71%),
  radial-gradient(circle at 6.7% 75%, rgba(255, 197, 89, 0.5), rgba(0,0,255,0) 70.71%), 
  radial-gradient(circle at 93.3% 75%, rgba(245, 14, 52, 0.5),rgba(0,255,0,0) 70.71%) beige;
  /* margin-bottom: 100px; */
}
.password-button{
  border-radius: 5px;
  width: 100%;
  background: 
  radial-gradient(circle at 50% 0, rgba(255, 94, 156, 0.5), rgba(255,0,0,0) 70.71%),
   radial-gradient(circle at 59% 0, rgba(255, 242, 185, 0.5), rgba(255,0,0,0) 70.71%),
  radial-gradient(circle at 6.7% 75%, rgba(255, 142, 89, 0.5), rgba(0,0,255,0) 70.71%), 
  radial-gradient(circle at 93.3% 75%, rgba(245, 14, 52, 0.5),rgba(0,255,0,0) 70.71%) beige;
  color:#fff;
  border:none;
  position:relative;
  height:50px;
  font-size:100%;
  font-weight: 500;
  padding:0 2em;
  cursor:pointer;
  transition:300ms ease all;
  outline:none;
}
.password-button:hover{
  background:#fff;
  color:#fd9e45;
}
.password-button:before,.password-button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: 
  radial-gradient(circle at 50% 0, rgba(255, 94, 156, 0.5), rgba(255,0,0,0) 70.71%),
  radial-gradient(circle at 6.7% 75%, rgba(255, 197, 89, 0.5), rgba(0,0,255,0) 70.71%), 
  radial-gradient(circle at 93.3% 75%, rgba(245, 14, 52, 0.5),rgba(0,255,0,0) 70.71%) beige;
  transition:300ms ease all;
}
.password-button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.password-button:hover:before,.password-button:hover:after{
  width:100%;
  transition:500ms ease all;
}
</style>