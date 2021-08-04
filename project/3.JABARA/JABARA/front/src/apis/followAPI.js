/*
followAPI
 */

import http from "../http-common";
const insertFollow = (data, callback, errorCallback) => {
  // "userId" "followingId"
  // return success
  http
    .post("/jobara/api/follow/insertFollow", data)
    .then((Response) => {
      console.log("insertFollow");
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertFollow error :" + Error);
      errorCallback(Error);
    });
};
const deleteFollow = (data, callback, errorCallback) => {
  // "userId" "followingId"
  // return success
  http
    .post("/jobara/api/follow/deleteFollow", data)
    .then((Response) => {
      console.log("deleteFollow");
      callback(Response);
    })
    .catch((Error) => {
      console.log("deleteFollow error :" + Error);
      errorCallback(Error);
    });
};
const IsFollow = (data, callback, errorCallback) => {
  // "userId" "followingId"
  // return  1 팔로잉 하고 있음
  http
    .post("/jobara/api/follow/IsFollow", data)
    .then((Response) => {
      console.log("IsFollow");
      callback(Response);
    })
    .catch((Error) => {
      console.log("IsFollow error :" + Error);
      errorCallback(Error);
    });
};
const selectFollowCountById = (data, callback, errorCallback) => {
  //자신의 팔로워 목록 반환 나를
  http
    .get("/jobara/api/follow/selectFollowCountById/" + data)
    .then((Response) => {
      console.log("selectFollowCountById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectFollowCountById error :" + Error);
      errorCallback(Error);
    });
};
const selectFollowingCountById = (data, callback, errorCallback) => {
  //자신의 팔로잉 숫자 반환 내가
  http
    .get("/jobara/api/follow/selectFollowingCountById/" + data)
    .then((Response) => {
      console.log("selectFollowingCountById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectFollowingCountById error :" + Error);
      errorCallback(Error);
    });
};
const selectFollowById = (data, callback, errorCallback) => {
  //id 입력 list 반환자신의 팔로워 목록 반환
  http
    .get("/jobara/api/follow/selectFollowById/" + data)
    .then((Response) => {
      console.log("selectFollowById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectFollowById error :" + Error);
      errorCallback(Error);
    });
};

const FollowApi = {
  insertFollow: (data, callback, errorCallback) =>
    insertFollow(data, callback, errorCallback),
  deleteFollow: (data, callback, errorCallback) =>
    deleteFollow(data, callback, errorCallback),
  IsFollow: (data, callback, errorCallback) =>
    IsFollow(data, callback, errorCallback),
  selectFollowCountById: (data, callback, errorCallback) =>
    selectFollowCountById(data, callback, errorCallback),
  selectFollowingCountById: (data, callback, errorCallback) =>
    selectFollowingCountById(data, callback, errorCallback),
  selectFollowById: (data, callback, errorCallback) =>
    selectFollowById(data, callback, errorCallback),
};

export default FollowApi;
