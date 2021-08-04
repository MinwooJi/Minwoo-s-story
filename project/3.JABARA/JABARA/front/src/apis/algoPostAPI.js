/*
 User API 예시
 */

import http from "../http-common";
const insertAlgoPost = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/post/insertCode/", data)
    .then((Response) => {
      console.log("insertAlgoPost");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertAlgoPost error :" + Error);
      errorCallback(Error);
    });
};

const InsertAlgoApi = {
  insertAlgoPost: (data, callback, errorCallback) =>
    insertAlgoPost(data, callback, errorCallback),
};

export default InsertAlgoApi;
