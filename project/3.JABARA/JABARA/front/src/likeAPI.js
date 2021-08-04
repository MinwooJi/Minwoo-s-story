/*
Like API 예시
 */

import http from "../http-common";
const insertLike = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .post("/jobara/api/alrim/clickLike",data)
    .then((Response) => {
      console.log("insertLike");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertLike error :" + Error);
      errorCallback(Error);
    });
};

const LikeAPI = {
  insertLike: (data, callback, errorCallback) =>
  insertLike(data, callback, errorCallback),
};

export default LikeAPI;
