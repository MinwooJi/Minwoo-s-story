import http from "../http-common";
const requestSelectMyApply = (data, callback, errorCallback) => {
  http
    .get("/jobara/api/profile/selectMyApply/" + data)
    .then((Response) => {
      console.log("requestSelectMyApply");
      //   console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestSelectMyApply error :" + Error);
      errorCallback(Error);
    });
};
const requestInsertMyApply = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/insertMyApply", data)
    .then((Response) => {
      console.log("requestInsertMyApply");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestInsertMyApply error :" + Error);
      errorCallback(Error);
    });
};
// const requestModifyMyEvent = (data, callback, errorCallback) => {
//   //백앤드와 로그인 통신하는 부분
//   http
//     .post("/jobara/api/Mycal/updateMycal", data)
//     .then((Response) => {
//       console.log("requestModifyMyEvent");
//       console.log(Response);
//       callback(Response);
//     })
//     .catch((Error) => {
//       console.log("requestModifyMyEvent error :" + Error);
//       errorCallback(Error);
//     });
// };
const requestDeleteMyApply = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/profile/deleteMyApply/" + data)
    .then((Response) => {
      console.log("requestDeleteMyApply");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestDeleteMyApply error :" + Error);
      errorCallback(Error);
    });
};

const MyApplyApi = {
  requestSelectMyApply: (data, callback, errorCallback) =>
    requestSelectMyApply(data, callback, errorCallback),
  requestInsertMyApply: (data, callback, errorCallback) =>
    requestInsertMyApply(data, callback, errorCallback),
  // requestModifyMyEvent: (data, callback, errorCallback) =>
  //   requestModifyMyEvent(data, callback, errorCallback),
  requestDeleteMyApply: (data, callback, errorCallback) =>
    requestDeleteMyApply(data, callback, errorCallback),
};

export default MyApplyApi;
