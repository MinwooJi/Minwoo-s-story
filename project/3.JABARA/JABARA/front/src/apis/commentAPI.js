/*
 User API 예시
 */

import http from "../http-common";
const requestInsertComment = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/comment/insertComment", data)
    .then((Response) => {
      console.log("comment insertEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("commnet insertEvent error :" + Error);
      errorCallback(Error);
    });
};

const selectComment = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/comment/searchComment/" + data)
    .then((Response) => {
      console.log("select comment Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("select comment Event error :" + Error);
      errorCallback(Error);
    });
};

const modifyComment = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log("수정데이터는 = ");
  console.log(data);
  http
    .post("/jobara/api/comment/updateComment", data)
    .then((Response) => {
      console.log("modify commment Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("modify commnet Event error :" + Error);
      errorCallback(Error);
    });
};
const deleteComment = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/comment/deleteComment/", data)
    .then((Response) => {
      console.log(data);
      console.log("delete comment Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log(data);
      console.log("deletec comment Event error :" + Error);
      errorCallback(Error);
    });
};
const CommentApi = {
  requestInsertComment: (data, callback, errorCallback) =>
    requestInsertComment(data, callback, errorCallback),

  selectComment: (data, callback, errorCallback) =>
    selectComment(data, callback, errorCallback),

  modifyComment: (data, callback, errorCallback) =>
    modifyComment(data, callback, errorCallback),

  deleteComment: (data, callback, errorCallback) =>
    deleteComment(data, callback, errorCallback),
};

export default CommentApi;
