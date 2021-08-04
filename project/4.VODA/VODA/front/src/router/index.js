import Vue from "vue";
import VueRouter from "vue-router";
import MainChoose from "../views/MainChoose.vue";
import Login from "../views/accounts/Login.vue";
import Signup from "../views/accounts/Signup.vue";
import Customer from "../views/Customer.vue";
import MeetingMain from "../views/MeetingMain.vue";
// import MeetingStart from "../views/MeetingStart.vue";
import MeetingRoom from "../views/MeetingRoom.vue";
// import Mypage from "../views/Mypage.vue";
// import Setting from "../views/Setting.vue";

Vue.use(VueRouter);
export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "*",
      redirect: "/",
    },
    {
      path: "/",
      name: "MainChoose",
      component: MainChoose,
      meta: { title: " VODA | 청각자애인을 위한 화상회의 서비스" },
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/signup",
      name: "Signup",
      component: Signup,
    },
    {
      path: "/customer",
      name: "Customer",
      component: Customer,
    },
    {
      path: "/meeting",
      name: "MeetingMain",
      component: MeetingMain,
    },
    // {
    //   path: "/voda/mypage",
    //   name: "Mypage",
    //   component: Mypage,
    // },
    // {
    //   path: "/meeting/start",
    //   name: "MeetingStart",
    //   component: MeetingStart,
    // },
    // {
    //   path: "/voda/meeting/setting",
    //   name: "Setting",
    //   component: Setting,
    // },
    {
      path: "/meeting/room",
      name: "MeetingRoom",
      component: MeetingRoom,
    },
  ],
});

// const router = new VueRouter({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   routes
// })

// export default router
