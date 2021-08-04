import http from "../http-common";
const requestKakaoLogin = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/kakao/login/", data)
    .then((Response) => {
      console.log("kakologin");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("kakologin error :" + Error);
      errorCallback(Error);
    });
};
const requestKakaoLogout = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/kakao/logout/", data)
    .then((Response) => {
      console.log("kakologout");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("kakologout error :" + Error);
      errorCallback(Error);
    });
};
const KaKaoApi = {
  requestKakaoLogin: (data, callback, errorCallback) =>
    requestKakaoLogin(data, callback, errorCallback),
  requestKakaoLogout: (data, callback, errorCallback) =>
    requestKakaoLogout(data, callback, errorCallback),
};

export default KaKaoApi;
