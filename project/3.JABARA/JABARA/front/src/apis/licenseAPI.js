/*
프론트 => 백
 */

import http from "../http-common";
const requestInsertLicense = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/insertLicense", data)
    .then((Response) => {
      console.log("insertEvent");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertEvent error :" + Error);
      errorCallback(Error);
    });
};
// const deleteLicense = (data, callback, errorCallback) => {
//   //백앤드와 로그인 통신하는 부분
//   http
//     .delete("/jobara/api/profile/deleteLicense", data)
//     .then((Response) => {
//       console.log("deleteLicense");
//       console.log(Response);
//       callback(Response);
//     })
//     .catch((Error) => {
//       console.log("deleteLicense error :" + Error);
//       errorCallback(Error);
//     });
// };

// const modifyLicense = (data, callback, errorCallback) => {
//   http
//     .put("/jobara/api/profile/modifyLicenseList", data)
//     .then((Response) => {
//       console.log("modifyLicense");
//       console.log(Response);
//       callback(Response);
//     })
//     .catch((Error) => {
//       console.log("modifyLicense error :" + Error);
//       errorCallback(Error);
//     });
// };

// const selectLicense = (data, callback, errorCallback) => {
//   //백앤드와 로그인 통신하는 부분
//   http
//     .post("/jobara/api/profile/selectLicenseList", data)
//     .then((Response) => {
//       console.log("selectLicense");
//       console.log(Response);
//       callback(Response);
//     })
//     .catch((Error) => {
//       console.log("selectLicense error :" + Error);
//       errorCallback(Error);
//     });
// };

// /*
// 백 => 프론트
//  */

// const SelectLicense = (data, callback, errorCallback) => {
//   //백앤드와 로그인 통신하는 부분
//   http
//     .post("/jobara/api/profile/selectLicenseList", data)
//     .then((Response) => {
//       console.log("SelectLicense");
//       console.log(Response);
//       callback(Response);
//     })
//     .catch((Error) => {
//       console.log("SelectLicense error :" + Error);
//       errorCallback(Error);
//     });
// };

/*
백 => 프론트
 */

const deleteLicense = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .delete("/jobara/api/profile/deleteLicense", data)
    .then((Response) => {
      console.log("deleteLicense");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("deleteLicense error :" + Error);
      errorCallback(Error);
    });
};

const modifyLicense = (data, callback, errorCallback) => {
  http
    .put("/jobara/api/profile/modifyLicenseList", data)
    .then((Response) => {
      console.log("modifyLicense");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("modifyLicense error :" + Error);
      errorCallback(Error);
    });
};

const selectLicense = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/selectLicenseList", data)
    .then((Response) => {
      console.log("selectLicense");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectLicense error :" + Error);
      errorCallback(Error);
    });
};

const LicenseApi = {
  requestInsertLicense: (data, callback, errorCallback) =>
    requestInsertLicense(data, callback, errorCallback),

  selectLicense: (data, callback, errorCallback) =>
    selectLicense(data, callback, errorCallback),

  deleteLicense: (data, callback, errorCallback) =>
    deleteLicense(data, callback, errorCallback),

  modifyLicense: (data, callback, errorCallback) =>
    modifyLicense(data, callback, errorCallback),
};

export default LicenseApi;
