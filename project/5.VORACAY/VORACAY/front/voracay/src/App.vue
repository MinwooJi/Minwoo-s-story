<template>
  <div id="app">
    <TopHeader
      id="top_header"
      v-if="$route.name != 'Voracay'"
      :isLoggedIn="isLoggedIn"
      :memberInfo="memberInfo"
    />

    <div id="contents">
      <router-view />
    </div>
  </div>
</template>
<script>
import TopHeader from "@/components/TopHeader.vue";
import http from "../src/http-common";

export default {
  name: "app",

  components: {
    TopHeader,
  },
  data() {
    return {
      isLoggedIn: true,
      memberInfo: {
        id: "test",
        email: "test@test",
        username: "tester",
      },
    };
  },
  created(){
    this.$store.commit("setUserToken", sessionStorage.getItem("token"));
    this.$store.commit("setUserName", sessionStorage.getItem("userName"));
    http.defaults.headers.common['Bearer'] = this.$store.state.userToken;
  },
};
</script>
<style scoped>
body {
  /* vh, vw단위는 height, width의 1% */
  height: 100vh;
  width: 100vw;
  padding: 0;
  z-index: 1;
  margin: 0px;
  background-color: rgb(255, 255, 255) !important;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100vh;
}
</style>
