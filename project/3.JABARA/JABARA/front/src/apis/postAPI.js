import http from "../http-common";
const getAlgoSolved = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .get("/jobara/api/post/searchAlgoSolved/" + data)
    .then((Response) => {
      console.log("getAlgoSolved");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("getAlgoSolved error :" + Error);
      errorCallback(Error);
    });
};
const getAlgoUnsolved = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .get("/jobara/api/post/searchAlgoUnSolved/" + data)
    .then((Response) => {
      console.log("getAlgoUnsolved");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("getAlgoUnsolved error :" + Error);
      errorCallback(Error);
    });
};
const getPosts = (callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/post/searchPost")
    .then((Response) => {
      console.log("getPosts");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("getPosts error :" + Error);
      errorCallback(Error);
    });
};
const searchPostById = (data, callback, errorCallback) => {
  //id로 게시글 검색
  http
    .get("/jobara/api/post/searchPostById/" + data)
    .then((Response) => {
      console.log("searchPostById");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchPostById error :" + Error);
      errorCallback(Error);
    });
};
const searchAlgoCalendarChart = (data, callback, errorCallback) => {
  //모내기 userId 보내주기
  http
    .get("/jobara/api/post/searchAlgoCalendarChart/" + data)
    .then((Response) => {
      console.log("searchAlgoCalendarChart");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchAlgoCalendarChart error :" + Error);
      errorCallback(Error);
    });
};
const searchPostByNo = (data, callback, errorCallback) => {
  //모내기 userId 보내주기
  http
    .get("/jobara/api/post/searchPostByNo/" + data)
    .then((Response) => {
      console.log("searchPostByNo");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchPostByNo error :" + Error);
      errorCallback(Error);
    });
};
const selectStarPost = (data, callback, errorCallback) => {
  //id전송 보관게시물 return
  http
    .get("/jobara/api/post/selectStarPost/" + data)
    .then((Response) => {
      console.log("selectStarPost");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectStarPost error :" + Error);
      errorCallback(Error);
    });
};
const deletePost = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/post/deletePost", data)
    .then((Response) => {
      console.log("deletePost");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("deletePost error :" + Error);
      errorCallback(Error);
    });
};
const getPostsByInf = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  http
    .get("/jobara/api/post/selectPostInfLoading/" + data)
    .then((Response) => {
      console.log("getPostsByInf");
      callback(Response);
    })
    .catch((Error) => {
      console.log("getPostsByInf error :" + Error);
      errorCallback(Error);
    });
};
const searchPostByFollowing = (data, callback, errorCallback) => {
  //id보내면 팔로잉 기반 게시글 줌
  http
    .get("/jobara/api/post/searchPostByFollowing/" + data)
    .then((Response) => {
      console.log("searchPostByFollowing");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("searchPostByFollowing error :" + Error);
      errorCallback(Error);
    });
};
const GetPostsApi = {
  getPosts: (callback, errorCallback) => getPosts(callback, errorCallback),
  getPostsByInf: (data, callback, errorCallback) =>
    getPostsByInf(data, callback, errorCallback),
  getAlgoSolved: (data, callback, errorCallback) =>
    getAlgoSolved(data, callback, errorCallback),
  getAlgoUnsolved: (data, callback, errorCallback) =>
    getAlgoUnsolved(data, callback, errorCallback),
  searchAlgoCalendarChart: (data, callback, errorCallback) =>
    searchAlgoCalendarChart(data, callback, errorCallback),
  searchPostById: (data, callback, errorCallback) =>
    searchPostById(data, callback, errorCallback),
  searchPostByNo: (data, callback, errorCallback) =>
    searchPostByNo(data, callback, errorCallback),
  selectStarPost: (data, callback, errorCallback) =>
    selectStarPost(data, callback, errorCallback),
  deletePost: (data, callback, errorCallback) =>
    deletePost(data, callback, errorCallback),
  searchPostByFollowing: (data, callback, errorCallback) =>
    searchPostByFollowing(data, callback, errorCallback),
};

export default GetPostsApi;
