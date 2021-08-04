/*
 Activity API 예시
 */

import http from "../http-common";
const selectActivity = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .post("/jobara/api/chat/selectActivity/"+data)
    .then((Response) => {
      console.log("selectActivity");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectActivity error :" + Error);
      errorCallback(Error);
    });
};

const ActivityAPI = {
  selectActivity: (data, callback, errorCallback) =>
  selectActivity(data, callback, errorCallback),
};

export default ActivityAPI;
