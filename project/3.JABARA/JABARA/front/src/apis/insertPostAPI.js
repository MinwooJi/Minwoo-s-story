/*post upload api */

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
const insertSNSPost = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/post/insertSns/", data)
    .then((Response) => {
      console.log("insertSNSPost");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertSNSPost error :" + Error);
      errorCallback(Error);
    });
};
const insertPostImage = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/post/insertPostImage/", data)
    .then((Response) => {
      console.log("insertPostImage");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertPostImage error :" + Error);
      errorCallback(Error);
    });
};

const InsertPostApi = {
  insertAlgoPost: (data, callback, errorCallback) =>
    insertAlgoPost(data, callback, errorCallback),
  insertSNSPost: (data, callback, errorCallback) =>
    insertSNSPost(data, callback, errorCallback),
  insertPostImage: (data, callback, errorCallback) =>
    insertPostImage(data, callback, errorCallback),
};

export default InsertPostApi;
