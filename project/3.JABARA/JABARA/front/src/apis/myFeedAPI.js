/*
 myFeedApi
 */
//게시글 들고 오기 (by userid)
import http from "../http-common";
const searchPostById = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get(`/jobara/api/post/searchPostById/${data}`)
    .then((Response) => {
      console.log("searchPostById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchPostByIdt error :" + Error);
      errorCallback(Error);
    });
};

const MyFeedApi = {
  searchPostById: (data, callback, errorCallback) =>
    searchPostById(data, callback, errorCallback),
};

export default MyFeedApi;
