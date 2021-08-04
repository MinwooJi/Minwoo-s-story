<template>
  <div class="user" id="findpassword">
    <div class="wrapC">
      <h1>&#128233;이메일 인증번호 입력</h1>
      <div ref="findpwbody">
        <div class="input-with-label">
          <input v-model="authCode" id="authCode" placeholder="이메일로 전송된 인증코드를 입력하세요." type="text" />
          <label for="authCode">인증입력</label>
        </div>
        <button class="admin-button" @click="setnewpassword">인증하기</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data: () => {
    return {
      authCode: "",
      authCodeByServer: ""
    };
  },
  created: function() {
    this.authCodeByServer = this.$session.get("authCodeByServer");
  },
  methods: {
    setnewpassword() {
      if (this.authCodeByServer == this.authCode) {
        alert("인증번호 일치!!");
        this.$router.push("/user/setnewpassword");
      } else {
        alert("인증번호 불일치!! 인증번호 입력을 확인하세요");
      }
    }
  }
};
</script>
<style scoped>
.user {
  margin-top: 10%;
}
.admin-button {
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
.admin-button{
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
.admin-button:hover{
  background:#fff;
  color:#fd9e45;
}
.admin-button:before,.admin-button:after{
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
.admin-button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.admin-button:hover:before,.admin-button:hover:after{
  width:100%;
  transition:500ms ease all;
}
</style>
