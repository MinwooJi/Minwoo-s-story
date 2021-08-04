import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// 추가

import VueGoogleCharts from "vue-google-charts";
import VueSession from "vue-session";
// import GAuth from 'vue-google-oauth2'
import axios from "axios";

var sessionoptions = {
  persist: true,
};
import VCalendar from "v-calendar";
import moment from "moment";
import VueMomentJS from "vue-momentjs";

import VModal from "vue-js-modal";
Vue.use(VueGoogleCharts);
Vue.use(VModal, { dynamic: true });
Vue.use(VueMomentJS, moment);
// Use v-calendar & v-date-picker components
Vue.use(VCalendar, {
  componentPrefix: "vc", // Use <vc-calendar /> instead of <v-calendar />
  // ...other defaults
});

Vue.use(VueSession, sessionoptions);
// Vue.use(GAuth, {clientId: '4XXXXXXXX93-2gqknkvdjfkdfkvb8uja2k65sldsms7qo9.apps.googleusercontent.com', scope: 'profile email https://www.googleapis.com/auth/plus.login'})
Vue.prototype.$axios = axios;

import { BootstrapVue } from "bootstrap-vue";
// import { library } from '@fortawesome/fontawesome-svg-core'
// import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
// import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// import BootstrapVue from 'bootstrap-vue'
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'

// Vue.use(BootstrapVue)

// 추가

// import GAuth from 'vue-google-oauth2'

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
// library.add(faUserSecret)
// Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.use(BootstrapVue);

// var sessionoptions = {
//   persist: true,
// };
Vue.use(VueSession, sessionoptions);
// Vue.use(GAuth, {clientId: '4XXXXXXXX93-2gqknkvdjfkdfkvb8uja2k65sldsms7qo9.apps.googleusercontent.com', scope: 'profile email https://www.googleapis.com/auth/plus.login'})
Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
