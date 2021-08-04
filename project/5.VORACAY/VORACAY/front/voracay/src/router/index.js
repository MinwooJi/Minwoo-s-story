import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Voracay from "../views/Login.vue";
import Meeting from "../views/Meeting.vue";
import Test from "../components/meeting/AddUserToMeeting.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Voracay",
    component: Voracay,
  },
  {
    path: "/main",
    name: "Main",
    component: Main
  },
  {
    path: "/meeting",
    name: "Meeting",
    component: Meeting,
  },
  {
    path: "/Test",
    name: "Test",
    component: Test,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
