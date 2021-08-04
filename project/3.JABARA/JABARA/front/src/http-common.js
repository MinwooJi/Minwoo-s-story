import axios from "axios";

export default axios.create({
  // baseURL: "http://i3d207.p.ssafy.io:8090/",
  baseURL: "http://localhost:8090/",
  headers: {
    "Content-type": "application/json; charset=utf-8",
  },
});
