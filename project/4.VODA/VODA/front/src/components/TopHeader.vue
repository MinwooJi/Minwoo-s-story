<template>
  <div class="header">
    <a href="/">
      <div calss="home-url">
        <img
          src="@/assets/side_logo.png"
          alt="voda 로고"
          width="150"
          height="70"
        />
      </div>
    </a>
    <ul class="header-ul" v-if="isLoggedIn">
      <li>
        <p>{{ memberInfo.username }}님 환영합니다.</p>
      </li>
      <li @click="logout">
        <router-link to="/">Logout</router-link>
      </li>
    </ul>
    <ul class="header-ul" v-if="!isLoggedIn">
      <li>
        <router-link to="/login/">Login</router-link>
      </li>
      <li>
        <router-link to="/signup/">Signup</router-link>
      </li>
      <li>
        <!-- <router-link to="/customer/">Customer</router-link> -->
        <!-- <a href="https://j3d206.p.ssafy.io:8001/voda/join/">Customer</a> -->
        <a href="/customer">Customer</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    isLoggedIn: Boolean,
    memberInfo: {
      id: String,
      email: String,
      username: String,
    },
  },
  data() {
    return {};
  },
  methods: {
    logout() {
      this.$emit("submit-logout");
    },
  },
};
</script>


<style scoped>
a,
p {
  text-decoration: none;
  font-family: "Viga", sans-serif;
  color: white;
}
.header {
  font-family: "Viga", sans-serif;
  position: fixed;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100vw;
  height: 70px;
  /* top: 0; */
  /* left: 0;  */
  z-index: 2;
  padding: 10px 50px;
}
.header-ul {
  display: flex;
  margin-right: 130px;
  padding: 0;
  list-style: none;
}
.header-ul li {
  display: flex;
  padding: 10px 12px;
  justify-content: center;
  align-items: center;
}
.header-ul li a {
  display: flex;
  align-items: center;
}
</style>
