<template>
  <!-- v-for사용 -->
  <div id="feed-item">
    <div class="feed-header">
      <div class="header-com">
        <div>
          <img
            :src="
              'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage
            "
            onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
            class="profile"
          />
        </div>
        <div class="user-id">
          <router-link
            v-if="item.userId == loginUserId"
            class="user-id-router"
            to="/post/postmyhome"
          >{{ item.userId }}</router-link>
          <router-link
            v-if="item.userId != loginUserId"
            class="user-id-router"
            :to="`/post/posthome?userId=${item.userId}`"
          >{{ item.userId }}</router-link>
        </div>
        <div class="post-time">
          <p>{{ calTime(item.postDate) }}</p>
          <p
            v-if="item.userId == loginUserId"
            @click="deletePost(item)"
            class="delete-item-button"
          >삭제</p>
        </div>
      </div>
    </div>
    <div v-if="item.postCategory == 'SNS'" class="feed-img">
      <img
        :src="
          'https://jobara.s3.ap-northeast-2.amazonaws.com/snspost//' +
            item.postNumber
        "
        alt="feed-img"
        width="598"
        height="auto"
      />
    </div>
    <div v-else>
      <!-- <canvas id="canvas" height="500" width="700"></canvas> -->
      <div class="img">
        <div class="content">
          <!-- <h1>{{item.postAlgoTitle}}</h1> -->
          <h1>{{ cutTitle(item.postAlgoTitle) }}</h1>
        </div>
        <div class="img-cover"></div>
      </div>
    </div>

    <div class="feed-content-header">
      <div class="heart emoji">
        <i :style="{ display: emptyHEmo }" @click="isEmptyHEmo" class="far fa-heart empty-heart"></i>
        <i
          :style="{ display: fullHEmo }"
          style="color: tomato;"
          @click="isFullHEmo"
          class="fas fa-heart full-heart animate__animated animate__heartBeat"
        ></i>
      </div>
      <!-- <div class="message emoji"><i class="far fa-comment-alt"></i></div> -->
      <div class="save emoji">
        <i :style="{ display: emptyBEmo }" @click="isEmptyBEmo" class="far fa-bookmark"></i>
        <i
          :style="{ display: fullBEmo }"
          style="color: rgb(98, 166, 255);"
          @click="isFullBEmo"
          class="fas fa-bookmark"
        ></i>
      </div>
      <div class="feed-content-like">
        <div class="like-count">
          <p>좋아요: {{ item.postLike }}개</p>
        </div>
      </div>
    </div>

    <div v-if="item.postCategory == 'SNS'" :class="{ showContent: isClicked }" class="feed-content">
      <p class="feed-post-content">{{ item.postSnsContent }}</p>
      <div>
        <ul>
          <li
            v-for="(tag, index) in TagList"
            :key="index + '_items'"
            class="tag-style"
          >#{{ tag.TAG_NAME }}</li>
        </ul>
      </div>
      <a @click="isClickedReadMore" class="readmore-btn ml-2">{{replaceText}}</a>
    </div>
    <div v-else class="feed-content">
      <div>
        <ul>
          <li
            v-for="(tag, index) in TagList"
            :key="index + '_items'"
            class="tag-style"
          >#{{ tag.TAG_NAME }}</li>
        </ul>
      </div>
      <router-link
        :to="`/post/poatalgo?userId=${item.userId}&postNumber=${item.postNumber}` "
        class="feed-content"
        style="color: black;"
      >...전체 글 보기</router-link>
    </div>
    <div class="feed-content-comment-title">
      <p class="comment-title">댓글</p>
    </div>
    <div class="feed-content-comment">
      <ul class="comment-ul" :class="{ commentAdd: heightauto }">
        <p v-if="CommentDataList.length == 0">댓글이 없습니다</p>
        <li v-else v-for="(comment, index) in CommentDataList.slice(0, CFnum)" :key="index">
          <div
            v-if="comment.COMMENT_NUMBER == modifyCommentNumber"
            class="comment-modify-button"
            @click="modifyCommentData(comment)"
          >
            <p>수정하기</p>
          </div>
          <div v-else class="comment-etc">
            <router-link class="commentUser" to>
              {{
              comment.USER_ID
              }}
            </router-link>
            <!-- @click="deleteOneComment(comment.COMMENT_NUMBER)" -->

            <p
              v-if="
                loginUserId == item.userId || loginUserId == comment.USER_ID
              "
              class="comment-delete"
              @click="cAlert(comment.COMMENT_NUMBER)"
            >삭제</p>
            <p
              v-if="loginUserId == comment.USER_ID"
              class="comment-modify"
              @click="changeInputComment(comment)"
            >수정</p>
            <p class="comment-clock">{{ calTime(comment.COMMENT_DATE) }}</p>
          </div>

          <!-- 어떻게 받아올까 -->
          <!-- {{calTime(comment.commentDate)}} -->

          <p v-if="comment.COMMENT_NUMBER == modifyCommentNumber">
            <input type="text" v-model="commentPrev" class="comment-modify-input" />
          </p>
          <p v-else>{{ comment.COMMENT_CONTENT }}</p>
        </li>
      </ul>
      <a
        class="more-comment"
        @click="commentClickedReadMore"
        v-if="CommentDataList.length > 2"
      >댓글 {{ CommentDataList.length - CFnum }}개 더보기</a>
    </div>
    <div class="feed-bottom">
      <div class="comment-input-wrap">
        <input
          class="comment-input"
          type="text"
          placeholder="댓글 달기"
          v-model="commentContent"
          @keyup.enter="submitComment"
        />
      </div>
    </div>
  </div>
</template>

<script>
// import $ from 'jquery';
import UserApi from "../../apis/userAPI";
import LikeApi from "../../apis/likeAPI";
import TagApi from "../../apis/tagAPI";
import CommentApi from "@/apis/commentAPI";
import PostApi from "@/apis/postAPI";
export default {
  name: "Feedlist",
  props: {
    item: Object
  },
  data() {
    return {
      loginUserId: "",
      profileimage: "", //이미지
      TagList: [],
      commentPrev: "",
      modifyCommentNumber: -1,

      // comment
      commentContent: "",
      commentNumber: 0,
      CommentDataList: [],
      // modify comment

      heightauto: false,
      CFnum: 2,
      isClicked: false,
      replaceText: "...더보기",
      moreComment: "댓글 더보기",
      isHeartClicked: false,
      moreClicked: false,
      emptyHEmo: "",
      fullHEmo: "none",
      emptyBEmo: "",
      fullBEmo: "none"
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.reLoadprofileimage();
    this.reloadcommentDataList();
    this.checkkeep();
    this.checklike();
    this.getTags();
  },
  methods: {
    deletePost(item) {
      console.log("##DEL ITEM: ", item);
      PostApi.deletePost(
        item.postNumber,
        res => {
          if (res.data == "success") {
            this.$emit("deleteitem", item);
            console.log(res);
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    getTags() {
      TagApi.requestTag(
        this.item.postNumber,
        res => {
          let tagtempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            tagtempArr.push(res.data[i]);
          }
          this.TagList = tagtempArr;
        },
        error => {
          console.log(error);
        }
      );
    },
    checklike() {
      let data = {
        postNumber: this.item.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.item.userId
      };
      LikeApi.selectislike(
        data,
        res => {
          if (res.data == 1) {
            //좋아요 눌렀음
            this.fullHEmo = "";
            this.emptyHEmo = "none";
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    checkkeep() {
      let data = {
        postNumber: this.item.postNumber,
        userId: this.$session.get("loginUserId")
      };
      LikeApi.checkStarPost(
        data,
        res => {
          // console.log("이미지" + res.data);
          if (res.data == "success") {
            //보관되어있음
            this.fullBEmo = "";
            this.emptyBEmo = "none";
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    reLoadprofileimage() {
      UserApi.requestAWSUserImage(
        this.item.userId,
        res => {
          // console.log("이미지" + res.data);
          this.profileimage = res.data;
        },
        error => {
          console.log(error);
        }
      );
    },
    reloadcommentDataList() {
      // console.log("comment 리로드");
      CommentApi.selectComment(
        this.item.postNumber,
        res => {
          // console.log("comment data확인", res);
          let commenttempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            commenttempArr.push(res.data[i]);
          }
          this.CommentDataList = commenttempArr;
          // console.log("commment hi" + this.CommentDataList);
        },
        error => {
          console.log(error);
        }
      );
    },
    pushNewCommentData(data) {
      // console.log("comment안에는?", data);
      if (data.COMMENT_NUMBER) {
        this.CommentDataList.push(data);
      }
      // console.log("comment push됐다");
      this.CommentDataList.sort(function(a, b) {
        return b.COMMENT_NUMBER - a.COMMENT_NUMBER;
      });
    },
    submitComment() {
      // console.log("댓글보내기");
      let data = {
        commentContent: this.commentContent,
        postNumber: this.item.postNumber,
        userId: this.$session.get("loginUserId"),
        targetuserId: this.item.userId
        // commentNumber: 0,
      };
      // console.log(data);
      let CtempData = {
        POST_NUMBER: data.postNumber,
        USER_ID: data.userId,
        COMMENT_NUMBER: -1,
        COMMENT_CONTENT: data.commentContent,
        TARGETUSER_ID: data.targetuserId
      };
      CommentApi.requestInsertComment(
        data,
        res => {
          // console.log("comment number는" + res.data);
          CtempData.COMMENT_NUMBER = res.data;
          if (CtempData != -1) {
            this.pushNewCommentData(CtempData);
          }
        },
        error => {
          console.log(error);
        }
      );
      this.commentContent = "";
    },
    changeInputComment(comment) {
      this.modifyCommentNumber = comment.COMMENT_NUMBER;
      this.commentPrev = comment.COMMENT_CONTENT;
    },
    modifyCommentData(comment) {
      let data = {
        postNumber: this.item.postNumber,
        commentContent: this.commentPrev,
        commentNumber: comment.COMMENT_NUMBER,
        userId: this.$session.get("loginUserId")
      };
      let CMtempData = {
        POST_NUMBER: data.postNumber,
        COMMENT_NUMBER: -1,
        COMMENT_CONTENT: data.commentContent,
        USER_ID: data.userId
      };
      CommentApi.modifyComment(
        data,
        res => {
          if (res.data == "success") {
            CMtempData.COMMENT_NUMBER = data.commentNumber;
            this.modifyOrderComment(CMtempData);
            // console.log("CMDatalist는?" + CMtempData);
          }
          this.modifyCommentNumber = -1;
        },

        error => {
          console.log(error);
        }
      );
    },
    modifyOrderComment(data) {
      for (let i = 0; i < this.modifyCommentData.length; i++) {
        if (this.CommentDataList[i].COMMENT_NUMBER == data.COMMENT_NUMBER) {
          this.CommentDataList.splice(i, 1, data);
          console.log(this.CommentDataList);
        }
      }
    },
    cAlert(number) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        this.deleteOneComment(number);
      }
    },
    deleteOneComment(number) {
      let data = {
        postNumber: this.item.postNumber,
        commentNumber: number
        // userId: this.$session.get("loginUserId"),
      };
      CommentApi.deleteComment(
        // number 만으로 삭제가능? usercheck필요?
        data,
        res => {
          console.log(res);
          this.deleteOrderComment(data);
        },
        err => {
          console.log(err);
        }
      );
    },
    deleteOrderComment(data) {
      for (let i = 0; i < this.CommentDataList.length; i++) {
        if (this.CommentDataList[i].COMMENT_NUMBER == data.commentNumber) {
          this.CommentDataList.splice(i, 1);
        }
      }
    },
    isClickedReadMore() {
      this.isClicked = !this.isClicked;
      // console.log(this.isClicked)
      if (this.isClicked) {
        this.replaceText = "접기";
      } else {
        this.replaceText = "더보기";
      }
    },
    commentClickedReadMore() {
      this.heightauto = true;
      let lastCom = this.CommentDataList.length - this.CFnum;
      if (lastCom < 3) {
        // console.log("3미만이면=>");
        // console.log(this.CFnum);
        // console.log("lastCom은?=>");
        // console.log(this.lastCom);
        this.CFnum = this.CommentDataList.length;
      }
      if (lastCom >= 4) {
        this.CFnum += 3;
        // console.log(this.CFnum);
      }
    },

    isEmptyHEmo() {
      //좋아요 추가
      let data = {
        postNumber: this.item.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.item.userId
      };
      LikeApi.clickLike(
        data,
        res => {
          console.log("좋아요" + res.data);
          if (res.data == "1") {
            this.emptyHEmo = "none";
            this.fullHEmo = "";
            this.item.postLike += 1;
          }
        },
        error => {
          console.log(data);
          console.log(error);
        }
      );
    },
    isFullHEmo() {
      //좋아요 삭제
      let data = {
        postNumber: this.item.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.item.userId
      };
      LikeApi.clickLike(
        data,
        res => {
          // console.log("이미지" + res.data);
          if (res.data == "0") {
            this.emptyHEmo = "";
            this.fullHEmo = "none";
            this.item.postLike -= 1;
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    isEmptyBEmo() {
      //즐겨찾기 추가
      let data = {
        postNumber: this.item.postNumber,
        userId: this.$session.get("loginUserId")
      };
      LikeApi.insertStarPost(
        data,
        res => {
          // console.log("이미지" + res.data);
          if (res.data == "success") {
            this.emptyBEmo = "none";
            this.fullBEmo = "";
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    isFullBEmo() {
      //즐겨찾기 해제
      let data = {
        postNumber: this.item.postNumber,
        userId: this.$session.get("loginUserId")
      };
      LikeApi.deleteStarPost(
        data,
        res => {
          // console.log("이미지" + res.data);
          if (res.data == "success") {
            this.emptyBEmo = "";
            this.fullBEmo = "none";
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    //////////////////////////////////
    profileimg() {
      UserApi.requestSelectImage(
        this.$session.get("loginUserId"),
        res => {
          // console.log(res.data);
          this.profileimage = res.data;
        },
        error => {
          console.log(error);
        }
      );
    },
    calTime(regtime) {
      let today = new Date();
      let timeValue = new Date(regtime);

      let betweenTime = Math.floor(
        (today.getTime() - timeValue.getTime()) / 1000 / 60
      );
      if (betweenTime < 1 || regtime == null) return "방금전";
      if (betweenTime < 60) {
        return `${betweenTime}분전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }

      return `${Math.floor(betweenTimeDay / 365)}년전`;
    },
    cutTitle(title) {
      let temp = title.split("_");
      let result;
      for (let i = 0; i < temp.length; i++) {
        if (i == 0) {
          result = "#" + temp[i];
        } else {
          result = result + "#" + temp[i];
        }
      }
      return `${result}`;
    }
    // import moment from "moment";
    // getFormatDate(regtime) {
    //   // moment 라이브러리를 이용하여 날짜 정보를 년.월.일 시:분:초 의 형태로 반환한다.
    //   //return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");

    // }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.user-id-router {
  font-family: "Do Hyeon", sans-serif;
}
* {
  /* font-family: "Do Hyeon", sans-serif; */
}
.feed-post-content {
  white-space: pre-line;
}
/*  */
.delete-item-button {
  padding-left: 10%;
}
.delete-item-button:hover {
  cursor: pointer;
}
.tag-style {
  display: inline;
  margin-right: 15px;
  background-color: #ffe5dc;
  border-radius: 5px;
  padding: 1px;
}
.img {
  position: relative;
  background-image: url(https://jobara.s3.ap-northeast-2.amazonaws.com/else//ALGO.webp);
  height: 500px;
  background-size: cover;
  white-space: pre-line;
}

.img-cover {
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1;
}

.img .content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 5rem;
  color: white;
  z-index: 2;
  text-align: center;
}
/* 안돼서 style toggle로 해결 */
/* .isClicked {
  display: none;
} */
.comment-modify-button {
  float: right;
  margin-right: 20px;
  z-index: 15;
  position: relative;
}
.comment-modify-button:hover {
  cursor: pointer;
}
.comment-modify {
  float: right;
  margin-right: 10px;
  width: 5%;
  height: 100%;
}
p.comment-modify:hover {
  cursor: pointer;
}
.comment-clock {
  font-size: 90%;
  float: right;
  margin-right: 10px;
  margin-bottom: 0px;
  margin-top: 2px;
}
.comment-delete {
  float: right;
  margin-right: 20px;
  width: 5%;
}
p.comment-delete:hover {
  cursor: pointer;
}
.comment-modify-input {
  font-size: 100%;
  border: 0;
  padding: 0;
  caret-color: #f78e6d;
  width: 100%;
}
.commentUser {
  text-decoration: none;
  color: white;
  padding: 2px;
  border-radius: 4px;
  background-color: #f78e6d;
  margin-right: 20px;
}
.comment-p {
  margin-bottom: 5px;
}
.more-comment {
  margin-left: 8px;
}
.comment-ul {
  font-size: 90%;
  line-height: 30px;
  height: auto;
  /* overflow-x: auto; */
  /* overflow-y: auto; */
  margin-left: 8px;
  /* -ms-overflow-style:none; */
}

.comment-ul li p {
  white-space: normal;
  word-break: break-all;
  word-wrap: break-word;
}
.commentAdd {
  height: 150px;
  overflow-x: auto;
  overflow-y: scroll;
  -ms-overflow-style: none;
}
.comment-ul::-webkit-scrollbar {
  /* display: none; */
  width: 10px;
}
.comment-ul::-webkit-scrollbar-thumb {
  background-color: #f78d6d80;
  border-radius: 10px;
  background-clip: padding-box;
  border: 3px solid transparent;
  height: calc(100% - 100px);
}
.comment-ul::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.feed-content-comment {
  margin: 3px;
  margin-left: 20px;
}
.feed-content-comment ul {
  text-align: left;
  /* font-weight: 900; */
}

.comment-input {
  border: 0;
  width: 100%;
}
.feed-content-comment-title {
  height: 30px;
  width: 100px;
  margin: 30px 3px 3px 23px;
}

.feed-content-comment-title .comment-title {
  text-align: left;
  font-weight: 900;
}

.comment-input-wrap {
  margin: 10px 10px;
}
.feed-bottom {
  border-top-width: 1px;
  border-top-style: solid;
  border-top-color: rgb(231, 231, 231);
}

#feed-item {
  margin-bottom: 30px;
  background-color: white;
  border-radius: 0.5rem;
  border: solid 1px rgb(231, 231, 231);
}
img {
  width: 100%;
}
.feed-header {
  border-bottom-width: 1px;
  border-bottom-color: rgb(231, 231, 231);
  border-bottom-style: solid;
  padding: 1rem 1rem;
  height: 5rem;
}
.profile {
  width: 50px;
  height: 50px;
  background-color: mediumaquamarine;
  display: inline-table;
  float: left;
  border-radius: 50px;
  position: relative;
}
.user-id {
  font-family: "Do Hyeon", sans-serif;
  float: left;
  display: inline-table;
  width: 70px;
  height: 50px;
  margin-left: 10px;
}
.user-id p {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}
.user-id a {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  color: #001123;
}

.post-time {
  float: right;
  display: inline-table;
  width: 70px;
  height: 50px;
}
.post-time p {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}
.feed-content-header {
  height: 2.5rem;
  margin: 10px 3px 10px 5px;
}
.feed-content-header .heart {
  width: 50px;
  height: 100%;
  display: inline-table;
  float: left;
}
.feed-content-header .message {
  width: 50px;
  height: 100%;
  display: inline-table;
  float: left;
}
.feed-content-header .save {
  width: 50px;
  height: 100%;
  display: inline-table;
  float: left;
}

.feed-content-header .emoji i {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  font-size: 25px;
}
.feed-content-header .emoji i:hover {
  cursor: pointer;
}
.feed-content-like {
  height: 30px;
  margin: 3px;
  margin-left: 20px;
}
.feed-content-like .like-count {
  width: 200px;
  height: 30px;
  display: inline-table;
  float: right;
}
.feed-content-like .like-count p {
  display: table-cell;
  vertical-align: middle;
  text-align: right;
  font-weight: 900;
  padding-right: 10px;
}

.feed-content {
  height: 100%;
  margin: 5px 13px;
  text-align: left;
  word-wrap: break-word;
}
.feed-content p {
  line-height: 30px;
  height: 26px;
  overflow: hidden;
  margin-left: 8px;
}
.showContent p {
  height: auto;
}
.showContent {
  height: auto;
  margin-bottom: 30px;
}
.showMoreComment {
  height: auto;
  margin-bottom: 30px;
}
.showMoreComment p {
  height: auto;
}
.showMoreComment p a {
  text-decoration: none !important;
  color: black;
}
.showMoreComment p a:hover {
  color: black;
  font-weight: 700;
}
.feed-content-comment {
  display: block;
  height: 100%;
  margin: 5px 13px;
  text-align: left;
}
.readmore-btn {
  display: inline-block;
  color: rgb(44, 43, 43);
  text-decoration: none;
}
.readmore-btn:hover {
  text-decoration: none;
  color: black;
  font-weight: 700;
}
.readmore-btn:after {
  text-decoration: none;
  color: black;
}
@media screen and (max-width: 960px) {
  .header-com {
    height: inherit;
  }
  .feed-header {
    height: 4rem;
  }
  .profile {
    width: 40px;
    height: 40px;
  }
  .user-id {
    height: 40px;
    font-size: 90%;
    margin-left: 10px;
    line-height: 50px;
  }
  .feed-content p {
    margin-bottom: 0;
  }
  .user-id p {
    text-align: left;
  }
  .feed-img {
    height: auto;
    width: 100%;
  }
  .feed-img img {
    max-width: 100%;
    height: inherit;
  }
  .post-time {
    height: 40px;
    font-size: 90%;
  }
  .comment-input-wrap {
    margin: 0;
  }
  .feed-bottom {
    height: 50px;
  }
  /* .comment-ul {
    height: 150px
    ;
  } */
  .feed-content-header .emoji i {
    font-size: 130%;
  }

  .feed-content-header .emoji .fa-bookmark {
    /* width: 30px; */
    text-align: left;
  }
  p {
    font-size: 90%;
  }
  a {
    font-size: 80%;
  }
  input {
    font-size: 80%;
  }
}
@media screen and (max-width: 576px) {
  #feed-item {
    border-radius: 0;
  }
}
</style>
