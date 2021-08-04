import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    pageSize : 4,
    mainRouter:{
      isRecentCall :true,
      isGroupCall : false,
      isGroupDetail : false,
      isGroupInsert: false,
      isGroupUpdate: false,
      isContact : false,
      isFav : false,
      isCallSet : false,
    },
    detailId:-1,
    isLogin:false,
    userToken:"",
    userName:"",
    userList: [],
  },
  mutations: {
    setIsLogin(state,payload){
      state.isLogin = payload;
    },
    setUserToken(state,payload){
      state.userToken = payload;
    },
    setDetailId(state, payload) {
      state.detailId = payload;
    },
    setMainRouterFalse(state){
      state.mainRouter.isRecentCall = false;
      state.mainRouter.isGroupCall = false;
      state.mainRouter.isGroupDetail = false;
      state.mainRouter.isGroupInsert = false;
      state.mainRouter.isGroupUpdate = false;
      state.mainRouter.isContact = false;
      state.mainRouter.isFav = false;
    },
    setMainRouterTrue(state,payload){
      state.mainRouter[payload] = true;
      // console.log("ROUTER" , state.mainRouter)
    },
    setUserName(state,payload){
      state.userName = payload;
    },
    setUserList(state,payload){
      state.userList = payload;
    },
  },
  actions: {},
  modules: {}
});
