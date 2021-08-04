<template>
<div>

	<!-- Header -->
  <header id="header">
    <div class="header-top">
      <div class="logo">
      <a href="/"><img src="@/assets/NAMU.png" alt="voda 로고" width="30" height="40" />VORACAY</a>
      </div>
      
      <div >
      <ul class="header-mem" v-if="isLoggedIn">
        <li class="navbar-id">
          <p ><span>{{ userName }}</span>님 환영합니다</p>
      </li>
      <li class="logout" @click="logout">
          <router-link to="/">로그아웃 </router-link>
      </li>
      </ul>
      <ul class="header-mem" v-if="isLoggedIn == false">
        <li class="navbar-id">
          <p >로그인이 필요합니다</p>
      </li>
      <li class="logout" @click="login">
          <router-link to="/">로그인 </router-link>
      </li>
      </ul>
      </div>
    </div>
			


  <nav role="navigation">
    <div id="menuToggle">
    <input type="checkbox"
    id="checkbox" v-model="checkboxB" />
    <span></span>
    <span></span>
    <span></span>
    <ul id="menu">
      <li><p @click="changPage('isRecentCall')">최근통화&nbsp;<img src="@/assets/image/card/menu/통화.png" width="25" height="25" /></p></li>
					<li><p @click="changPage('isGroupCall')">그룹통화&nbsp;<img src="@/assets/image/card/menu/그룹.png" width="25" height="25" /></p></li>
					<li><p @click="changPage('isContact')">연락처&nbsp;<img src="@/assets/image/card/menu/연락처.png" width="25" height="25" /></p></li>
          <li><p @click="changPage('isFav')">즐겨찾기&nbsp;<img src="@/assets/image/card/menu/즐겨찾기.png" width="30" height="30" /></p></li>
    </ul>
   </div>
  </nav>
  </header>
</div>
</template>

<script>

export default {
  name: "Header",
  data() {
    return {
       mainRouter: {},
       userName: "",      
       checkboxB: false,
       isLoggedIn: false,
    };
  },
  created() {
    this.mainRouter = this.$store.state.mainRouter;
    this.userName = this.$store.state.userName;
    if(this.$store.state.userToken){
      this.isLoggedIn = true;
    }
  },
  methods: {
    logout() {
      this.$emit("submit-logout");
    },
    login(){
      this.changPage("isLogin");
    },
    changPage(name) {
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", name);
      if(this.checkboxB==true){
        this.checkboxB=false;
      }
      this.mainRouter = this.$store.state.mainRouter;
    },
  },
};
</script>

<style scoped>
/* hamburger */
#menu img {
  color:#fff;
}
#menuToggle
{
  display: block;
  position: relative;
  top: 50px;
  left: 50px;
  
  z-index: 1;
  
  -webkit-user-select: none;
  user-select: none;
}

#menuToggle p
{
  text-decoration: none;
  color: #c0c4f5;
  font-family: "Fredoka One";
  transition: color 0.3s ease;
}

#menuToggle p:hover
{
  color: rgb(255, 255, 255);
}


#menuToggle input
{
  display: block;
  width: 40px;
  height: 32px;
  position: absolute;
  top: -7px;
  left: -5px;
  
  cursor: pointer;
  
  opacity: 0; /* hide this */
  z-index: 2; /* and place it over the hamburger */
  
  -webkit-touch-callout: none;
}

/*
 * Just a quick hamburger
 */
#menuToggle span
{
  display: block;
  width: 33px;
  height: 4px;
  margin-bottom: 5px;
  position: relative;
  
  background: #280074fb;
  border-radius: 3px;
  
  z-index: 1;
  
  transform-origin: 4px 0px;
  
  transition: transform 0.5s cubic-bezier(0.77,0.2,0.05,1.0),
              background 0.5s cubic-bezier(0.77,0.2,0.05,1.0),
              opacity 0.55s ease;
}

#menuToggle span:first-child
{
  transform-origin: 0% 0%;
}

#menuToggle span:nth-last-child(2)
{
  transform-origin: 0% 100%;
}

/* 
 * Transform all the slices of hamburger
 * into a crossmark.
 */
#menuToggle input:checked ~ span
{
  opacity: 1;
  transform: rotate(45deg) translate(-2px, -1px);
  background: #b7bcff;
}

/*
 * But let's hide the middle one.
 */
#menuToggle input:checked ~ span:nth-last-child(3)
{
  opacity: 0;
  transform: rotate(0deg) scale(0.2, 0.2);
}

/*
 * Ohyeah and the last one should go the other direction
 */
#menuToggle input:checked ~ span:nth-last-child(2)
{
  transform: rotate(-45deg) translate(0, -1px);
}

/*
 * Make this absolute positioned
 * at the top left of the screen
 */
 @media screen and (max-width: 768px) {
   nav{
     height: 0px;
   }
   #menuToggle{
     top:0px;
   }
   #menu li
  {
    padding: 5px 0;
    font-size: 2px;
  }
 }
#menu
{
  position: absolute;
  top:40px;
  width: 300px;
  height: 750px;
  margin: -100px 0 0 -50px;
  padding: 50px;
  padding-top: 125px;
  background: #341186;
  list-style-type: none;
  -webkit-font-smoothing: antialiased;
  /* to stop flickering of text in safari */
  
  transform-origin: 0% 0%;
  transform: translate(-100%, 0);
  
  transition: transform 0.5s cubic-bezier(0.77,0.2,0.05,1.0);
}

#menu li
{
  padding: 10px 0;
  font-size: 22px;
}

/*
 * And let's slide it in from the left
 */
#menuToggle input:checked ~ ul
{
  transform: none;
}
@font-face {
  font-family: "Noto Sans KR";
  src: url("../assets/font/headerFont/NotoSansKR-Black.otf");
}
@font-face {
  font-family: "Viga";
  src: url("../assets/font/headerFont/Viga-Regular.ttf");
}
@font-face {
  font-family: "Dokdo";
  src: url("../assets/font/headerFont/EastSeaDokdo-Regular.ttf");
}
@font-face {
  font-family: "Fredoka One";
  src: url("../assets/font/login/FredokaOne-Regular.ttf");
}

.header-top {
  display:flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-left: 30px;
  /* padding:8px 12px; */
  /* height: 45px; */
}
.header-top a{
  text-decoration: none;
  color: black;
}

/* logo 글자 */
.logo a{ 
  font-size: 24px;
  color:rgb(41 58 151);
  font-family: "Fredoka One"
}

.header-mem{
  font-family: "Viga";
  list-style: none;
  line-height: 1em;
  padding: 2px 5px;
}
.header-mem span{
  color:#341186;
  font-weight:bold;
}
.header-mem p {

  margin-top: 20px;
  margin-bottom: 10px;
  margin-right: 50px;
} 
.logout {
 margin-right: 50px
}
.logout :hover {
 text-decoration: none !important;
   color:#341186;
}
@media screen and (max-width: 768px) {
  #menuToggle{
    left: 20px;
  }
  .header-top {
     margin-left: 5px;
  }
  .header-mem p {
 margin-right: 20px
} 
  .logout {
 margin-right: 20px
}
}

/* Header */

/* body.subpage {
  padding-top: 3.125em;
} */


#header {
  background: white;
  color: #000000;
  cursor: default;
  height: 3.25em;
  left: 0;
  line-height: 3.25em;
  position: fixed;
  text-align: right;
  top: 0;
  width: 100%;
  z-index: 10001;
}

#header .toggle span {
  display: none;
}

#header > .logo {
  display: inline-block;
  height: inherit;
  left: 1.25rem;
  line-height: inherit;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
}

#header > .logo a {
  font-size: 1.25rem;
  color: rgb(10, 10, 10);
  text-decoration: none;
}

#header > .logo span {
  font-weight: 300;
  font-size: 0.75rem;
  color: rgba(255, 255, 255, 0.5);
}

#header > a {
  -moz-transition: color 0.2s ease-in-out;
  -webkit-transition: color 0.2s ease-in-out;
  -ms-transition: color 0.2s ease-in-out;
  transition: color 0.2s ease-in-out;
  display: inline-block;
  padding: 0 0.75em;
  color: inherit;
  text-decoration: none;
  color: #fff;
}


#header > a:last-child {
  padding-right: 1.25em;
}

@media screen and (max-width: 736px) {
  #header > a {
    padding: 0 0.5em;
  }


  #header > a:last-child {
    padding-right: 1em;
  }
}

#header.reveal {
  -moz-animation: reveal-header 0.5s ease;
  -webkit-animation: reveal-header 0.5s ease;
  -ms-animation: reveal-header 0.5s ease;
  animation: reveal-header 0.5s ease;
}

#header.alt {
  -moz-animation: none;
  -webkit-animation: none;
  -ms-animation: none;
  animation: none;
  background-color: transparent;
  box-shadow: none;
  overflow: hidden;
  position: absolute;
  top: 1.5em;
}

#header.alt h1 {
  left: 2.5em;
}

#header.alt nav {
  right: 1em;
}

@media screen and (max-width: 980px) {
  body.subpage {
    padding-top: 44px;
  }

  #header {
    height: 44px;
    line-height: 44px;
  }

  #header > h1 {
    left: 1em;
  }

  #header > h1 a {
    font-size: 1em;
  }
}

@media screen and (max-width: 480px) {
  #header {
    min-width: 320px;
  }
}

</style>
