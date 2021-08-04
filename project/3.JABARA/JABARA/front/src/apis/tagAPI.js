/*
 User API 예시
 */

import http from "../http-common";
const searchPostByTag = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/tag/findPostByTag/" + data)
    .then((Response) => {
      console.log("searchPostByTag");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchPostByTag error :" + Error);
      errorCallback(Error);
    });
};
const insertPostTag = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/tag/insertTag", data)
    .then((Response) => {
      console.log("insertAlgoPostTag");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertAlgoPostTag error :" + Error);
      errorCallback(Error);
    });
};
const requestTag = (data, callback, errorCallback) => {
  //게시글 번호로 tag가져오기
  http
    .get(`/jobara/api/tag/findTagByPostNumber/${data}`)
    .then((Response) => {
      console.log("requestTag");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestTag error :" + Error);
      errorCallback(Error);
    });
};

const InsertTagApi = {
  insertPostTag: (data, callback, errorCallback) =>
    insertPostTag(data, callback, errorCallback),
  requestTag: (data, callback, errorCallback) =>
    requestTag(data, callback, errorCallback),
  searchPostByTag: (data, callback, errorCallback) =>
    searchPostByTag(data, callback, errorCallback),
};

export default InsertTagApi;
