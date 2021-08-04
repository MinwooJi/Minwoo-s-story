import http from "../http-common";
const getSovedCategory = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/profile/graphalgolist", data)
    .then((Response) => {
      console.log("getSovedCategory");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("getSovedCategory error :" + Error);
      errorCallback(Error);
    });
};

const MyProfileGraphApi = {
  getSovedCategory: (data, callback, errorCallback) =>
    getSovedCategory(data, callback, errorCallback),
};

export default MyProfileGraphApi;
