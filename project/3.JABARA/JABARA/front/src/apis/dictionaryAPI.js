import http from "../http-common";
const selectDictionaryAll = (callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/dictionary/selectDictionaryAll")
    .then((Response) => {
      console.log("selectDictionaryAll");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectDictionaryAll error :" + Error);
      errorCallback(Error);
    });
};
const selectDictionaryByName = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .post("/jobara/api/dictionary/selectDictionaryByName/" + data)
    .then((Response) => {
      console.log("selectDictionaryByName");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectDictionaryByName error :" + Error);
      errorCallback(Error);
    });
};
const DictionaryAPI = {
  selectDictionaryAll: (callback, errorCallback) =>
    selectDictionaryAll(callback, errorCallback),
  selectDictionaryByName: (data, callback, errorCallback) =>
    selectDictionaryByName(data, callback, errorCallback),
};

export default DictionaryAPI;
