/*
ranking API
 */

import http from "../http-common";
const rankingTotal = (callback, errorCallback) => {
  //전체 랭킹
  http
    .post("/jobara/api/post/rankingTotal")
    .then((Response) => {
      console.log("rankingTotal");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("rankingTotal error :" + Error);
      errorCallback(Error);
    });
};
const rankingToday = (callback, errorCallback) => {
  //TODAY RANKING
  http
    .post("/jobara/api/post/rankingtoday")
    .then((Response) => {
      console.log("rankingToday");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("rankingToday error :" + Error);
      errorCallback(Error);
    });
};
const myRankingTotal = (data, callback, errorCallback) => {
  http
    .post("/jobara/api/post/myRankingTotal/" + data)
    .then((Response) => {
      console.log("myRankingTotal");
      console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("myRankingTotal error :" + Error);
      errorCallback(Error);
    });
};

const RankingApi = {
  rankingTotal: (callback, errorCallback) =>
    rankingTotal(callback, errorCallback),
  rankingToday: (callback, errorCallback) =>
    rankingToday(callback, errorCallback),
  myRankingTotal: (data, callback, errorCallback) =>
    myRankingTotal(data, callback, errorCallback),
};

export default RankingApi;
