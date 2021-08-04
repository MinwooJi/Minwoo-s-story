<template>
  <div id="app">
    <testHeader v-if="$route.name !== 'jobaramain'" v-bind:loginUserId="loginUserId" />
    <router-view v-bind:loginUserId="loginUserId"></router-view>
  </div>
</template>
<script>
import "./components/css/style.css";
import testHeader from "@/components/common/testHeader.vue";

export default {
  name: "app",
  components: {
    testHeader
  },
  data() {
    return {
      loginUserId: this.$session.get("loginUserId")
    };
  },
  beforeUpdate() {
    console.log("updated");
    this.loginUserId = this.$session.get("loginUserId");
    console.log(this.loginUserId);
  }
};
</script>
<style>
body {
  /* vh, vw단위는 height, width의 1% */
  height: 100vh;
  width: 100vw;
  padding: 0;
  z-index: 1;
  background-color: rgb(255, 255, 255) !important;
}

body::-webkit-scrollbar {
  width: 10px;
}
body::-webkit-scrollbar-thumb {
  background: 
  radial-gradient(circle at 30% 0, rgba(255, 55, 41, 0.5), rgba(255,0,0,0) 70.71%),
  radial-gradient(circle at 6.7% 75%, rgba(255, 208, 105, 0.733), rgba(0,0,255,0) 70.71%), 
  radial-gradient(circle at 93.3% 75%, rgba(228, 61, 89, 0.5),rgba(0,255,0,0) 70.71%) beige;
}
body::-webkit-scrollbar-track {
  background-color: rgba(255, 239, 236, 0.815);
}
* {
  box-sizing: border-box;
}

</style>