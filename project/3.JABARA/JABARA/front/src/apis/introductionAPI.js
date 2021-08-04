/*
프론트 => 백
 */

import http from "../http-common";
const requestInsertIntroduction = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/insertResume", data)
    .then((Response) => {
      console.log("resume insert Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("resume insertEvent error :" + Error);
      errorCallback(Error);
    });
};

const requestInsertIntroductionQuestion = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/insertQuestion", data)
    .then((Response) => {
      console.log("resumeQ insert Event");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("resumeQ insertEvent error :" + Error);
      errorCallback(Error);
    });
};
/*
백 => 프론트
 */

const deleteResume = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/deleteResume", data)
    .then((Response) => {
      console.log("deleteResume");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("deleteResume error :" + Error);
      errorCallback(Error);
    });
};

const searchQuestion = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/selectExpByQ/" + data)
    .then((Response) => {
      console.log("searchQuestion");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchQuestion error :" + Error);
      errorCallback(Error);
    });
};

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

const selectIntroduction = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    // /api/profile/selectMyResume/{userId} 형태일 때는 + data사용 그리고 data가 들어가는 자리에 $session.get("userID")처리
    .get("/jobara/api/profile/selectMyResume/" + data)
    .then((Response) => {
      console.log("select resume");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("select resume error :" + Error);
      errorCallback(Error);
    });
};
const selectIntroductionQA = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분

  http
    // /api/profile/selectMyResume/{userId} 형태일 때는 + data사용 그리고 data가 들어가는 자리에 $session.get("userID")처리
    .post("/jobara/api/profile/selectResumeDetail/", data)
    .then((Response) => {
      console.log("selectIntroductionQA resume");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectIntroductionQA resume error :" + Error);
      errorCallback(Error);
    });
};

const IntroductionApi = {
  requestInsertIntroduction: (data, callback, errorCallback) =>
    requestInsertIntroduction(data, callback, errorCallback),

  requestInsertIntroductionQuestion: (data, callback, errorCallback) =>
    requestInsertIntroductionQuestion(data, callback, errorCallback),

  selectIntroduction: (data, callback, errorCallback) =>
    selectIntroduction(data, callback, errorCallback),
  selectIntroductionQA: (data, callback, errorCallback) =>
    selectIntroductionQA(data, callback, errorCallback),
  searchQuestion: (data, callback, errorCallback) =>
    searchQuestion(data, callback, errorCallback),

  deleteResume: (data, callback, errorCallback) =>
    deleteResume(data, callback, errorCallback),

  // modifyLicense: (data, callback, errorCallback) =>
  //   modifyLicense(data, callback, errorCallback),
};

export default IntroductionApi;
