import http from "../http-common";
const requestSelectAll = (callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/Hire/selectHire")
    .then((Response) => {
      console.log("SelectAll");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("SelectAll error :" + Error);
      errorCallback(Error);
    });
};
const requestInsertEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Hire/insertHire", data)
    .then((Response) => {
      console.log("InsertEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("InsertEvent error :" + Error);
      errorCallback(Error);
    });
};
const requestModifyEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Hire/updateHire", data)
    .then((Response) => {
      console.log("ModifyEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("ModifyEvent error :" + Error);
      errorCallback(Error);
    });
};
const requestDeleteEvent = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Hire/deleteHire/" + data)
    .then((Response) => {
      console.log("DeleteEvent");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("DeleteEvent error :" + Error);
      errorCallback(Error);
    });
};
const requestInsertMyApply = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Hire/insertMyApplyFromHire", data)
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
const requestInsertHireToMyCal = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/Hire/insertHireToMyCal", data)
    .then((Response) => {
      console.log("requestInsertHireToMyCa");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestInsertHireToMyCa error :" + Error);
      errorCallback(Error);
    });
};
const HireApi = {
  requestSelectAll: (callback, errorCallback) =>
    requestSelectAll(callback, errorCallback),
  requestInsertEvent: (data, callback, errorCallback) =>
    requestInsertEvent(data, callback, errorCallback),
  requestModifyEvent: (data, callback, errorCallback) =>
    requestModifyEvent(data, callback, errorCallback),
  requestDeleteEvent: (data, callback, errorCallback) =>
    requestDeleteEvent(data, callback, errorCallback),
  requestInsertHireToMyCal: (data, callback, errorCallback) =>
    requestInsertHireToMyCal(data, callback, errorCallback),
  requestInsertMyApply: (data, callback, errorCallback) =>
    requestInsertMyApply(data, callback, errorCallback),
};

export default HireApi;
