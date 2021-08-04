import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/user/Login.vue";
import Join from "../views/user/Join.vue";
import UserModified from "../views/user/UserModified.vue";
import UserImage from "../views/user/UserImage.vue";
import AxiosPlugin from "vue-axios-cors";
import InsertAddInfo from "../components/user/snsLogin/Insertaddinfo.vue";
import HireMain from "../views/hire/HireMain.vue";
import ITDictionary from "../views/dictionary/ITDictionary.vue";
import CardTest from "../views/CardTest.vue";
Vue.use(AxiosPlugin);
// import Kakao from "@/components/user/snsLogin/Kakao.js";
import jobaramain from "../views/main/jobaramain.vue";
//add post
import AlgoEditor from "../views/addpost/AlgoEditor.vue";
// import BasicPost from "../views/addpost/BasicPost.vue";
import PostEditor from "../views/addpost/PostEditor.vue";
//Feed
import AllFeed from "../views/feed/Allfeed.vue";
// import AllFeed from "../views/InfScrollTest.vue";

//password
import FindPassword from "../views/user/FindPassword.vue";
import Findpwauth from "../views/user/Findpwauth.vue";
import Setnewpassword from "../views/user/Setnewpassword.vue";

//test
// import test from "../views/post/AlgoPost2.vue";
// post
import PostMyHome from "../views/post/PostMyHome.vue";
import PostHome from "../views/post/PostOtherHome.vue";
import PoatAlgo from "../views/post/AlgoPost.vue";
//profile
import Myprofile from "../views/profile/Myprofile.vue";
//notice
import AddNotice from "../views/notice/AddNotice.vue";
//searchPost
import PostSearched from "../views/post/PostSearched.vue";
import NotFound from "../views/notFound/NotFoundPage.vue";
//chat
import DirectTalk from "../views/directtalk/chat.vue";

//searchPost
// import PostSearched from "../views/post/PostSearched.vue";

Vue.use(VueRouter);
export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/search/notfound",
      name: "NotFound",
      component: NotFound,
    },
    {
      path: "/test/card",
      name: "CardTest",
      component: CardTest,
    },
    {
      path: "/notice",
      name: "AddNotice",
      component: AddNotice,
    },
    {
      path: "/hire/main",
      name: "HireMain",
      component: HireMain,
    },
    {
      path: "/profile/",
      name: "Myprofile",
      component: Myprofile,
    },
    {
      path: "/user/join",
      name: "Join",
      component: Join,
    },
    {
      path: "/user/insertaddinfo",
      name: "Insertaddinfo",
      component: InsertAddInfo,
    },

    {
      path: "/user/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/user/findpassword",
      name: "findpassword",
      component: FindPassword,
    },
    {
      path: "/user/findpwauth",
      name: "findpwauth",
      component: Findpwauth,
    },
    {
      path: "/user/usermodified",
      name: "usermodified",
      component: UserModified,
    },
    {
      path: "/user/userimage",
      name: "userimage",
      component: UserImage,
    },
    {
      path: "/user/setnewpassword",
      name: "setnewpassword",
      component: Setnewpassword,
    },
    {
      path: "/jobara",
      name: "jobara",
      component: AllFeed,
    },
    {
      path: "/add/post/algorithm",
      name: "AlgoEditor",
      component: AlgoEditor,
    },
    {
      path: "/add/post/addpost",
      name: "PostEditor",
      component: PostEditor,
    },
    {
      path: "/post/postmyhome",
      name: "PostMyHome",
      component: PostMyHome,
    },
    {
      path: "/post/posthome",
      name: "PostHome",
      component: PostHome,
    },
    {
      path: "/post/search",
      name: "PostSearched",
      component: PostSearched,
    },
    {
      path: "/directtalk",
      name: "directtalk",
      component: DirectTalk,
    },
    {
      path: "/it/dictionary",
      name: "ITDictionary",
      component: ITDictionary,
    },

    {
      path: "/post/poatalgo",
      name: "poatalgo",
      component: PoatAlgo,
    },
    {
      path: "/",
      name: "jobaramain",
      component: jobaramain,
    },
    // {
    //   //test
    //   path: "/test",
    //   name: "test",
    //   component: test,
    // },
  ],
});
