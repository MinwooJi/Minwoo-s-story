<template>
  <div class="main--content">
    <div id="content">
      <div class="btn-res">
        <div v-if="mainRouter.isRecentCall">
          <div class="menu-title">
            <p><b>> 최근 통화</b></p>
          </div>
          <RecentCall />
        </div>
        <div v-else-if="mainRouter.isGroupCall">
          <div class="menu-title">
            <p><b>> 그룹 통화</b></p>
          </div>
          <GroupCall />
        </div>
        <div v-else-if="mainRouter.isContact">
          <div class="menu-title">
            <p><b>> 연락처</b></p>
          </div>
          <Contact />
        </div>
        <div v-else-if="mainRouter.isFav">
          <div class="menu-title">
            <p><b>> 즐겨 찾기</b></p>
          </div>
          <Fav />
        </div>
        <div v-else-if="mainRouter.isCallSet">
          <div class="menu-title">
            <p><b>> 회의 설정</b></p>
          </div>
          <CallSet />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import RecentCall from "@/components/sidebar/RecentCall.vue";
import Contact from "@/components/sidebar/Contact.vue";
import GroupCall from "@/components/sidebar/Group.vue";
import Fav from "@/components/sidebar/Favorite.vue";
import CallSet from "@/components/meeting/AddUserToMeeting.vue";

export default {
  data() {
    return {
      mainRouter: {},
    };
  },
  components: {
    RecentCall,
    GroupCall,
    Contact,
    Fav,
    CallSet,
  },
  created() {
    this.mainRouter = this.$store.state.mainRouter;
  },
  methods: {
    changPage(name) {
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", name);
      this.mainRouter = this.$store.state.mainRouter;
    },
    
  },
};
</script>
<style scoped>
.menu-title {
  margin-top: auto;
}
.btn-res p {
  float: left;
  color: rgb(66 69 76 / 41%);
}
#content {
  
  margin-left: 130px;
  margin-top: 100px;
  margin-right: 130px;

  flex: 3;
  padding: 0 4vw;
}

.hover-effect {
  padding: 5%;
  transition: 0.4s;
  font-family: "Fredoka One", cursive;
  font-family: "Jua", sans-serif;
  font-size: x-large;
}
.hover-effect:hover {
  background-color: #ceeefd;
  color: #2f42ac;
  padding: 10% 10%;
  cursor: pointer;
  font-family: "Fredoka One", cursive;
  font-family: "Jua", sans-serif;
  font-size: xx-large;
}
.pop-scroll {
  width: 100%;
  height: 90%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.main--content {
  display: flex;
  flex-direction: row;
  margin-top: 1px;
  height: 800px;
}

.left {
  flex: 0.8;
  border-right: 2px solid;
}
@media screen and (max-width: 736px) {
  #content {
  margin-left: 20px;
  margin-top: 80px;
  margin-right: 20px;
}
 b{
  margin-left: 30px;

}
}
</style>
