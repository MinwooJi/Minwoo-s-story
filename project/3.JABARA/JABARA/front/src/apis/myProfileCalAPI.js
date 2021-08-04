import http from "../http-common";
const requestSelectMyCalAll = (data, callback, errorCallback) => {
  http
    .get("/jobara/api/Mycal/selectMycal/" + data)
    .then((Response) => {
      console.log("requestSelectMyCalAll");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestSelectMyCalAll error :" + Error);
      errorCallback(Error);
    });
};
const requestInsertMyEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Mycal/insertMycal", data)
    .then((Response) => {
      console.log("requestInsertMyEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestInsertMyEvent error :" + Error);
      errorCallback(Error);
    });
};
const requestModifyMyEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Mycal/updateMycal", data)
    .then((Response) => {
      console.log("requestModifyMyEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestModifyMyEvent error :" + Error);
      errorCallback(Error);
    });
};
const requestDeleteMyEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Mycal/deleteMycal", data)
    .then((Response) => {
      console.log("requestDeleteMyEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestDeleteMyEvent error :" + Error);
      errorCallback(Error);
    });
};

const MyCalAPI = {
  requestSelectMyCalAll: (data, callback, errorCallback) =>
    requestSelectMyCalAll(data, callback, errorCallback),
  requestInsertMyEvent: (data, callback, errorCallback) =>
    requestInsertMyEvent(data, callback, errorCallback),
  requestModifyMyEvent: (data, callback, errorCallback) =>
    requestModifyMyEvent(data, callback, errorCallback),
  requestDeleteMyEvent: (data, callback, errorCallback) =>
    requestDeleteMyEvent(data, callback, errorCallback),
};

export default MyCalAPI;
