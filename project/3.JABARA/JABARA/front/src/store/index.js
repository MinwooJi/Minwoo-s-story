import Vue from "vue";
import Vuex from "vuex";
import MyProfileGraphApi from "@/apis/myProfileGraphAlgoAPI";
// import TagAPI from "@/apis/tagAPI";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // PopUpInpu.vue
    show: true,
    companyName: "",
    companyURL: "",
    companyImage: "",
    sovledAlgoCount: {},
    viewStatus: "company",
    //MycomponyModal.vue

    //search:"",
    searchTagName: "",
    samplePost: {},
    postCount: 0,
    searchedDatas: [],
    //ohterspost
    posts: "",
    pageUserId: "",
  },
  mutations: {
    // search post
    setSearchedDatas(state, payload) {
      console.log("mutation - setSearchedDatas ", payload);
      state.searchedDatas = payload;
    },
    setsamplePost(state, payload) {
      console.log("mutation - samplePost ", payload);
      state.samplePost = payload;
    },
    setPostCount(state, payload) {
      console.log("mutation - setPostCount ", payload);
      state.postCount = payload;
    },
    setSearchTagName(state, payload) {
      console.log("mutation - setSearchTagName ", payload);
      state.searchTagName = payload;
      console.log(state.searchTagName);
    },

    //
    setSolvedAlgoCount(state, payload) {
      console.log("mutation - setSolvedAlgoCount");
      state.sovledAlgoCount = payload;
    },
    setViewStatus(state, payload) {
      console.log("setter 실행됨");
      state.viewStatus = payload;
      console.log(payload);
    },
    setcompanyName(state, payload) {
      state.companyName = payload;
    },
    setcompanyURL(state, payload) {
      state.companyURL = payload;
    },
    setcompanyImage(state, payload) {
      state.companyImage = payload;
    },
    showTrue(state) {
      console.log("openForm 호출!");
      state.show = true;
    },
    showFalse(state) {
      state.show = false;
    },
    //////othersfeeds//////////
    setPost(state, payload) {
      state.posts = payload;
    },
    setpageUserId(state, payload) {
      state.pageUserId = payload;
    },
  },
  actions: {
    // getSearchedPostByAxios({ commit, state }) {
    //   console.log("action : ", state.searchTagName);
    //   TagAPI.searchPostByTag(
    //     state.searchTagName,
    //     (res) => {
    //       commit("setSearchedDatas", res.data);
    //       console.log("action res :", state.searchedDatas);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },

    //
    getsovledAlgoCountByAxios({ commit /*state*/ }) {
      if (this.$session.get("loginUserId")) {
        MyProfileGraphApi.getSovedCategory(
          this.$session.get("loginUserId"),
          (res) => {
            console.log("mutation - 알고메인 데이터 준비끝");
            console.log(res.data);
            commit("setSolvedAlgoCount", res.data);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
  modules: {},
});
