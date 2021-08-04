/*
 User API 예시
 */

import http from "../http-common";
const requestInsertExp = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/insertExp", data)
    .then((Response) => {
      console.log("insertEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertEvent error :" + Error);
      errorCallback(Error);
    });
};

const selectExp = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/selectExpList", data)
    .then((Response) => {
      console.log("select Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("select Event error :" + Error);
      errorCallback(Error);
    });
};

const modifyExp = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log("수정데이터는 = ");
  console.log(data);
  http
    .put("/jobara/api/profile/modifyExpList", data)
    .then((Response) => {
      console.log("modify Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("modify Event error :" + Error);
      errorCallback(Error);
    });
};
const deleteExp = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .delete("/jobara/api/profile/deleteExp", data)
    .then((Response) => {
      console.log("delete Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("delete Event error :" + Error);
      errorCallback(Error);
    });
};
const ExpApi = {
  requestInsertExp: (data, callback, errorCallback) =>
    requestInsertExp(data, callback, errorCallback),

  selectExp: (data, callback, errorCallback) =>
    selectExp(data, callback, errorCallback),

  modifyExp: (data, callback, errorCallback) =>
    modifyExp(data, callback, errorCallback),

  deleteExp: (data, callback, errorCallback) =>
    deleteExp(data, callback, errorCallback),
};

export default ExpApi;
