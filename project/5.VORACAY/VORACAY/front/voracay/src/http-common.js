import axios from "axios";
import store from "../src/store/index";

export default axios.create({
  baseURL: "https://k3d106.p.ssafy.io/api/",
  // baseURL: "http://localhost:8090/",
  headers: {
    "Content-type": "application/json; charset=utf-8",
    // "Bearer": store.state.userToken,
    // "Access-Control-Allow-Headers": "*",
    // authorization 어케 처리할지 생각해봅시다..?
  },
});
