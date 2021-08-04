/*
like API / 좋아요 즐겨찾기
 */

import http from "../http-common";
const checkStarPost = (data, callback, errorCallback) => {
  //즐겨찾기 등록 확인 data:"postNumber":  "userId":
  http
    .post("/jobara/api/post/checkStarPost/", data)
    .then((Response) => {
      console.log("checkStarPost");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("login error :" + Error);
      errorCallback(Error);
    });
};
const insertStarPost = (data, callback, errorCallback) => {
  //즐겨찾기 등록  data:"postNumber":  "userId":
  http
    .post("/jobara/api/post/insertStarPost/", data)
    .then((Response) => {
      console.log("insertStarPost");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertStarPost error :" + Error);
      errorCallback(Error);
    });
};
const deleteStarPost = (data, callback, errorCallback) => {
  //즐겨찾기 해제  data:"postNumber":  "userId":
  http
    .post("/jobara/api/post/deleteStarPost/", data)
    .then((Response) => {
      console.log("deleteStarPos");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("deleteStarPos error :" + Error);
      errorCallback(Error);
    });
};
const selectislike = (data, callback, errorCallback) => {
  //좋아요 여부  data:"postNumber":글번호  "targetuserId":나  "userId":글작성자
  // return 1 좋아요 누른것
  http
    .post("/jobara/IsLike/", data)
    .then((Response) => {
      console.log("IsLike");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("IsLike error :" + Error);
      errorCallback(Error);
    });
};
const clickLike = (data, callback, errorCallback) => {
  //좋아요  data:"postNumber":글번호  "targetuserId":나  "userId":글작성자
  //return 1이면 좋아요
  //return 0 이면 좋아요 취소된것
  http
    .post("/jobara/clickLike/", data)
    .then((Response) => {
      console.log("clickLike");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("clickLike error :" + Error);
      errorCallback(Error);
    });
};

const LikeApi = {
  checkStarPost: (data, callback, errorCallback) =>
    checkStarPost(data, callback, errorCallback),
  insertStarPost: (data, callback, errorCallback) =>
    insertStarPost(data, callback, errorCallback),
  deleteStarPost: (data, callback, errorCallback) =>
    deleteStarPost(data, callback, errorCallback),
  selectislike: (data, callback, errorCallback) =>
    selectislike(data, callback, errorCallback),
  clickLike: (data, callback, errorCallback) =>
    clickLike(data, callback, errorCallback),
};

export default LikeApi;
