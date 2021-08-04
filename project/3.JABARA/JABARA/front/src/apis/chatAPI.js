/*
Chat API 예시
 */

import http from "../http-common";
const insertChat = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .post("/jobara/api/chat/insertChat", data)
    .then((Response) => {
      console.log("insertChat");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("insertChat error :" + Error);
      errorCallback(Error);
    });
};

const selectChat = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .post("/jobara/api/chat/selectChat", data)
    .then((Response) => {
      console.log("selectChat");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectChat error :" + Error);
      errorCallback(Error);
    });
};

const selectChatList = (data, callback, errorCallback) => {
  //백앤드와 로그인 통신하는 부분
  console.log(data);
  http
    .post("/jobara/api/chat/selectChatList", data)
    .then((Response) => {
      console.log("selectChatList");
      // console.log(Response);
      callback(Response);
    })
    .catch((Error) => {
      console.log("selectChatList error :" + Error);
      errorCallback(Error);
    });
};

const ChatAPI = {
  insertChat: (data, callback, errorCallback) =>
    insertChat(data, callback, errorCallback),

  selectChat: (data, callback, errorCallback) =>
    selectChat(data, callback, errorCallback),

  selectChatList: (data, callback, errorCallback) =>
    selectChatList(data, callback, errorCallback),
};

export default ChatAPI;
