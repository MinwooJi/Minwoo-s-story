import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueCookies from "vue-cookies";
import toast from "../src/assets/modules/vue-toast-notification";

import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.use(VueCookies);
Vue.use(toast);
Vue.config.productionTip = false;
// 쿠기 만료일 설정
// Vue.$cookies.config("7d"); 
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
