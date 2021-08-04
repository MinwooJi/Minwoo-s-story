<template>
  <div id="app">
    <KakaoLogin
      api-key="048201aff51ee6b2ae1df6a2c9b847fc"
      image="kakao_login_btn_medium"
      :on-success="onSuccess"
      :on-failure="onFailure"
    />
  </div>
</template>


<script type="text/javascript"></script>
<script>
import KaKaoAPI from "@/apis/kakaoAPI";
import KakaoLogin from "vue-kakao-login";

let onFailure = data => {
  console.log(data);
  console.log("failure");
};

export default {
  name: "KaKaoAPI",
  // data() {
  //   return {
  //     accessToken: "",
  //   };
  // },
  components: {
    KakaoLogin
  },
  methods: {
    onSuccess(data) {
      console.log(data);
      //this.accessToken = data.access_token;
      console.log(data.access_token);
      // alert(data.access_token);
      let check = "";
      check = data.access_token;

      KaKaoAPI.requestKakaoLogin(
        check,
        res => {
          console.dir(res);
          console.log("success");
          this.$session.set("loginUserId", res.data.userId);
          this.$session.set("kakaoAccessToken", check);
          this.$router.push("/jobara");
        },
        error => {
          console.log(error);
        }
      );
    },
    onFailure
  }
};
</script>

<style></style>
