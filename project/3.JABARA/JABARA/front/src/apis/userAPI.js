/*
 User API 예시
 */

import http from "../http-common";
const requestLogin = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/user/login/", data)
    .then((Response) => {
      console.log("login");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("login error :" + Error);
      errorCallback(Error);
    });
};
const requestFindPassword = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/user/passwordfind/", data)
    .then((Response) => {
      console.log("findpassword");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("findpassword error :" + Error);
      errorCallback(Error);
    });
};
const requestSetNewPassword = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/user/passwordchangeByEmail/", data)
    .then((Response) => {
      console.log("setnewpassword");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("setnewpassword error :" + Error);
      errorCallback(Error);
    });
};
const requestJoin = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/user/", data)
    .then((Response) => {
      console.log("setnewpassword");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("setnewpassword error :" + Error);
      errorCallback(Error);
    });
};
const requestDetail = (data, callback, errorCallback) => {
  //사용자 정보
  http
    .get(`/jobara/api/user/userDetailById/${data}`)
    .then((Response) => {
      console.log(data);
      console.log("userDetailById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log(data);
      console.log("userDetailById error :" + Error);
      errorCallback(Error);
    });
};
const requestModified = (data, callback, errorCallback) => {
  http
    .put("/jobara/api/user/modifyuser/", data)
    .then((Response) => {
      console.log("Modified");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestModified error :" + Error);
      errorCallback(Error);
    });
};
const requestImage = (data, callback, errorCallback) => {
  http
    .put("/jobara/api/user/modify/userImage/", data)
    .then((Response) => {
      console.log("ModifiedImage");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("requestImage error :" + Error);
      errorCallback(Error);
    });
};
const requestAWSUserImage = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/user/selectAWSUserImage", data)
    .then((Response) => {
      callback(Response);
    })
    .catch((Error) => {
      console.log(data);
      console.log("requestAWSUserImage error :" + Error);
      errorCallback(Error);
    });
};
const updateAWSUserImage = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/user/updateAWSUserImage", data)
    .then((Response) => {
      console.log("updateAWSUserImage");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log(data);
      console.log("updateAWSUserImage error :" + Error);
      errorCallback(Error);
    });
};

const UserApi = {
  requestLogin: (data, callback, errorCallback) =>
    requestLogin(data, callback, errorCallback),
  requestFindPassword: (data, callback, errorCallback) =>
    requestFindPassword(data, callback, errorCallback),
  requestSetNewPassword: (data, callback, errorCallback) =>
    requestSetNewPassword(data, callback, errorCallback),
  requestJoin: (data, callback, errorCallback) =>
    requestJoin(data, callback, errorCallback),
  requestDetail: (data, callback, errorCallback) =>
    requestDetail(data, callback, errorCallback),
  requestModified: (data, callback, errorCallback) =>
    requestModified(data, callback, errorCallback),
  requestImage: (data, callback, errorCallback) =>
    requestImage(data, callback, errorCallback),
  requestAWSUserImage: (data, callback, errorCallback) =>
    requestAWSUserImage(data, callback, errorCallback),
  updateAWSUserImage: (data, callback, errorCallback) =>
    updateAWSUserImage(data, callback, errorCallback),
};

export default UserApi;
