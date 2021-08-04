<template>
  <div id="app">
    <TopHeader
      v-if="$route.name != 'MeetingRoom'"
      :isLoggedIn="isLoggedIn"
      @submit-logout="logout"
      :memberInfo="memberInfo"
    />
    <router-view @submit-login-data="login" :isLoggedIn="isLoggedIn" />
  </div>
</template>
<script>
import axios from "axios";
import TopHeader from "@/components/TopHeader.vue";

export default {
  name: "app",

  components: {
    TopHeader,
  },
  methods: {
    login(loginData) {
      axios
        .post(
          process.env.VUE_APP_DJANGO_API_SERVER_URL + "api/users/login/",
          loginData
        )
        .then((res) => {
          if (res.data.message === "True") {
            this.$cookies.set("token", res.data.token); //토큰 날라오는거 설정해줘야함!!
            this.isLoggedIn = true;
            this.$router.push("/meeting");
            this.info();
            alert("로그인 되었습니다.");
          } else {
            alert("아이디와 비밀번호를 다시 확인해주세요.");
          }
        })
        .catch(() => {
          // console.log(err);
          alert("서버에 문제가 생겼습니다. 관리자에게 문의하세요.");
        });
    },
    logout() {
      this.$cookies.remove("token");
      this.isLoggedIn = false;
      alert("로그아웃 되었습니다.");
    },
    info() {
      if (this.$cookies.get("token")) {
        const config = {
          headers: {
            Authorization: `Bearer ${this.$cookies.get("token")}`,
          },
        };
        axios
          .post(
            process.env.VUE_APP_DJANGO_API_SERVER_URL + "api/users/info/",
            null,
            config
          )
          .then((res) => {
            console.log(res.data);
            this.memberInfo.id = res.data.id;
            this.memberInfo.username = res.data.username;
            this.memberInfo.email = res.data.email;
          })
          .catch(() => {
            // console.log(err);
          });
      }
    },
  },
  mounted() {
    this.isLoggedIn = this.$cookies.isKey("token");
    this.info();
  },
  updated() {
    this.isLoggedIn = this.$cookies.isKey("token");
  },
  data() {
    return {
      isLoggedIn: false,
      memberInfo: {
        id: "",
        email: "",
        username: "",
      },
    };
  },
};
</script>
<style>
body {
  width: 100vh;
  height: 100vh;
}
::selection {
  color: rgb(203, 37, 253);
  background: rgb(255, 255, 255);
}
html {
  scroll-behavior: smooth;
}
</style>
