<template>
  <div class="postcontainer">
    <header>
      <div class="hjprofile">
        <div class="hjprofile-image">
          <img
            :src="
              'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage
            "
            onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
            alt="프로필사진"
            width="200"
            height="200"
          />
        </div>

        <div class="hjprofile-user-settings">
          <h1 v-text="profilename" class="hjprofile-user-name"></h1>
          <a
            v-if="loginUserId != pageUserId"
            v-text="followcheck"
            @click="followfunc"
            class="followbutton"
          ></a>
          <a
            v-if="loginUserId != pageUserId"
            @click="directtalkfunc"
            class="followbutton"
          >&#128237; Direct Talk</a>
        </div>
        <div class="hjprofile-stats">
          <ul>
            <li>
              <span v-text="followers" class="hjprofile-stat-count"></span>
              followers
            </li>
            <li>
              <span v-text="following" class="hjprofile-stat-count"></span>
              following
            </li>
          </ul>
        </div>
      </div>
    </header>
    <hr />
    <div class="codetitle">
      <h1 v-if="post.isSolved" class="codetitle">
        SOLVED
        <br />
        {{ cutTitle(post.postAlgoTitle) }}
      </h1>
      <h1 v-if="!post.isSolved" class="codetitle">
        UNSOLVED
        <br />
        {{ cutTitle(post.postAlgoTitle) }}
      </h1>
      <p>
        <em></em>
      </p>

      <p class="postsns">{{ post.postSnsContent }}</p>

      <div style="max-width:650px; margin: 0 auto;">
        <viewer :initialValue="viewerText" />
      </div>

      <hr />
      <div style="max-width:650px; margin: 0 auto;">
        <ul class="tageul">
          <li
            v-for="(tag, index) in TagList"
            :key="index + '_items'"
            class="tag-style"
          >#{{ tag.TAG_NAME }}</li>
        </ul>
      </div>
      <div class="feed-content-comment-title" style="margin: 0 auto;">
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
            <div v-else class="comment-etc" style="max-width:650px; margin: 0 auto;">
              <router-link class="commentUser" to>
                {{ comment.USER_ID }}
              </router-link>
              <!-- @click="deleteOneComment(comment.COMMENT_NUMBER)" -->

              <p
                v-if="
                  loginUserId == pageUserId || loginUserId == comment.USER_ID
                "
                class="comment-delete"
                @click="cAlert(comment.COMMENT_NUMBER)"
              >삭제</p>
              <p
                v-if="loginUserId == comment.USER_ID"
                class="comment-modify"
                @click="changeInputComment(comment)"
              >
                수정
              </p>
              <p style="margin-right:10px;" class="comment-clock">{{ calTime(comment.COMMENT_DATE) }}</p>
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
            style="
    width: 642px;"
          />
        </div>
      </div>
      <hr />
      <div class="algopost-div">
        <button class="algopost-button" @click="onhome">Home</button>
        <button class="algopost-button" @click="onfeed">Feed</button>
      </div>
    </div>
    <!-- <AlgoDetail /> -->
  </div>
</template>
<script>
import TagApi from "../../apis/tagAPI";
import UserApi from "../../apis/userAPI";
import PostApi from "../../apis/postAPI";
import FollowApi from "../../apis/followAPI";
import ChatApi from "../../apis/chatAPI";
import CommentApi from "@/apis/commentAPI";

// import AlgoDetail from "../../components/form/AlgoDetail.vue";
import "tui-editor/dist/tui-editor.css";
import "tui-editor/dist/tui-editor-contents.css";
import "codemirror/lib/codemirror.css";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import { Viewer } from "@toast-ui/vue-editor";

export default {
  name: "AlgoPost",
  components: {
    // AlgoDetail,
    viewer: Viewer
  },
  data() {
    return {
      loginUserId: "",
      pageUserId: "",
      postNumber: "",
      pageUserNickname: "",
      profileimage: "",
      profilename: "",
      followcheck: "Follow",
      followers: "0",
      following: "0",
      post: "",
      TagList: [],
      commentContent: "",
      commentNumber: 0,
      CommentDataList: [],
      modifyCommentNumber: -1,
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
      fullBEmo: "none",
      /////
      viewerText: "# This is Viewer.\n Hello World. \n ## hey"
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.pageUserId = this.$route.query.userId;
    this.postNumber = this.$route.query.postNumber;
    console.log("userId" + this.pageUserId);
    console.log("postNumber" + this.postNumber);
    UserApi.requestAWSUserImage(
      this.pageUserId,
      res => {
        this.profileimage = res.data;
      },
      error => {
        console.log(error);
      }
    );
    this.checkfollow();
    this.selectnickname();
    this.selectfollowers();
    this.selectfollowing();
    this.selectpost();
    this.getTags();
    this.reloadcommentDataList();
  },
  methods: {
    onhome() {
      this.$router.push("/jobara");
    },
    onfeed() {
      this.$router
        .push(`/post/posthome?userId=${this.pageUserId}`)
        .catch(() => {});
    },
    createAction() {
      return this.$refs.toastuiEditor.invoke("getMarkdown"); // content를 저장하는 액션 처리
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
    reloadcommentDataList() {
      // console.log("comment 리로드");
      CommentApi.selectComment(
        this.postNumber,
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
        postNumber: this.postNumber,
        userId: this.$session.get("loginUserId"),
        targetuserId: this.pageUserId
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
        postNumber: this.postNumber,
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
        postNumber: this.postNumber,
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
    getTags() {
      TagApi.requestTag(
        this.postNumber,
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
    },
    selectpost() {
      PostApi.searchPostByNo(
        this.postNumber,
        res => {
          this.post = res.data;
          console.log(this.post);
          this.viewerText = this.post.postCodeContent;
        },
        error => {
          console.log(error);
        }
      );
    },
    checkfollow() {
      let data = {
        userId: this.loginUserId,
        followingId: this.pageUserId
      };
      FollowApi.IsFollow(
        data,
        res => {
          if (res.data > 0) {
            this.followcheck = "Follow";
          } else if (res.data == 1) {
            this.followcheck = "Follow X";
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    directtalkfunc() {
      let data = {
        otherId: this.pageUserId,
        userId: this.loginUserId
      };
      ChatApi.insertChat(
        data,
        res => {
          //좋아요 눌렀음
          console.log("chat 등록 성공!!!" + res);
        },
        error => {
          console.log(error);
        }
      );
      this.$router.push("/directtalk?otherId=" + this.pageUserId);
    },
    selectfollowers() {
      FollowApi.selectFollowCountById(
        this.pageUserId,
        res => {
          console.log(res);
          this.followers = res.data;
        },
        error => {
          console.log(error);
        }
      );
    },
    selectfollowing() {
      FollowApi.selectFollowingCountById(
        this.pageUserId,
        res => {
          console.log(res);
          this.following = res.data;
        },
        error => {
          console.log(error);
        }
      );
    },
    selectnickname() {
      UserApi.requestDetail(
        this.pageUserId,
        res => {
          console.log(res);
          this.pageUserNickname = res.data.userNickname;
          this.profilename =
            this.pageUserId + " [ " + this.pageUserNickname + " ]";
        },
        error => {
          console.log(error);
        }
      );
    },
    followfunc() {
      console.log("팔로우!!!!!!!");
      if (this.followcheck == "Follow") {
        //팔로우하기
        let data = {
          userId: this.loginUserId,
          followingId: this.pageUserId
        };
        FollowApi.insertFollow(
          data,
          res => {
            if (res.data == "success") {
              this.followcheck = "Follow X";
              this.followers += 1;
            }
          },
          error => {
            console.log(error);
          }
        );
      } else {
        //언팔로우하기
        let data = {
          userId: this.loginUserId,
          followingId: this.pageUserId
        };
        FollowApi.deleteFollow(
          data,
          res => {
            if (res.data == "success") {
              this.followcheck = "Follow";
              this.followers -= 1;
            }
          },
          error => {
            console.log(error);
          }
        );
      }
    }
  }
};
</script>
<style scoped>
/* button */
.algopost-div {
  max-width: 650px;
  margin: 50px auto;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.algopost-button {
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 40px;
  border-radius: 5px;
  width: 300px;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 38, 0, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 187, 99, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(255, 54, 104, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  color: #fff;
  border: none;
  position: relative;
  height: 50px;
  font-size: 100%;
  font-weight: 500;
  padding: 0 2em;
  cursor: pointer;
  transition: 800ms ease all;
  outline: none;
}
.algopost-button:hover {
  background: #fff;
  color: #fd6145;
}
.algopost-button:before,
.algopost-button:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 38, 0, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 187, 99, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(255, 54, 104, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  transition: 400ms ease all;
}
.algopost-button:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.algopost-button:hover:before,
.algopost-button:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
/*  */
textarea {
  resize: none;
  border: 2px dashed orange;
  outline: none;
}
.info {
  height: 400px;
  width: 100%;
  background-color: #dbdbdb;
}
input .comment-input-wrap {
  width: 1042.13334px;
}
div .comment-input-wrap {
  text-align: center;
}
input .comment-input {
  width: 600px;
}
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
  width: 1042.13334px;
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
.postsns {
  white-space: pre-line;
}
.postalgo {
  white-space: pre-line;
}
.tag-style {
  display: inline;
  margin-right: 15px;
  background-color: #ffe5dc;
  border-radius: 5px;
  padding: 1px;
  font-size: 15px;
}
.tageul {
  margin: auto;

  margin-bottom: 10px;
}
*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: "Open Sans", Arial, sans-serif;
  min-height: 100vh;
  background-color: #fafafa;
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
}

/*profil */

.followbutton {
  display: inline-block;

  font: normal normal 300 1.3em "Open Sans";
  text-decoration: none;

  color: #e0996d;
  border: 2px solid #eeb170;
  border-radius: 100px;

  padding: 0.1em 0.5em;
  margin: 5px;
  margin-left: 30px;
  background-size: 200% 100%;
  background-image: linear-gradient(to right, transparent 50%, #eeb170 50%);
  transition: background-position 0.3s cubic-bezier(0.19, 1, 0.22, 1) 0.1s,
    color 0.5s ease 0s, background-color 0.5s ease;
}

.followbutton:hover {
  color: rgba(255, 255, 255, 1);
  background-color: #f5d6a2;
  background-position: -100% 100%;
}
/* Profile Section */

*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: "Open Sans", Arial, sans-serif;
  min-height: 100vh;
  background-color: #fafafa;
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
}
.postcontainer {
  padding-top: 80px;
  max-width: 1000px;
  margin: 0 auto;
  padding: 0 2rem;
}

.visually-hidden {
  position: absolute !important;
  height: 1px;
  width: 1px;
  overflow: hidden;
  clip: rect(1px, 1px, 1px, 1px);
}

.hjprofile {
  padding-top: 100px;
  padding-bottom: 0px;
  /* padding: 5rem 0; */
}

.hjprofile::after {
  content: "";
  display: block;
  clear: both;
}

.hjprofile-image {
  float: left;
  width: calc(33.333% - 1rem);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 3rem;
}

.hjprofile-image img {
  border-radius: 50%;
}

.hjprofile-user-settings,
.hjprofile-stats {
  float: left;
  width: calc(66.666% - 2rem);
}

.hjprofile-user-settings {
  margin-top: 1.1rem;
}

.hjprofile-user-name {
  display: inline-block;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 25px;
}

.hjprofile-edit-btn {
  font-size: 1.4rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 2rem;
}

.hjprofile-settings-btn {
  font-size: 2rem;
  margin-left: 1rem;
}

.hjprofile-stats {
  margin-top: 2.3rem;
}

.hjprofile-stats li {
  display: inline-block;
  font-size: 18px;
  line-height: 1.5;
  margin-right: 4rem;
  cursor: pointer;
}

.hjprofile-stats li:last-of-type {
  margin-right: 0;
}

.hjprofile-real-name,
.hjprofile-stat-count,
.hjprofile-edit-btn {
  font-weight: 600;
}

/* Media Query */

@media screen and (max-width: 1000px) {
  .hjprofile {
    display: flex;
    flex-wrap: wrap;
    padding: 4rem 0;
  }

  .hjprofile::after {
    display: none;
  }

  .hjprofile-image,
  .hjprofile-user-settings,
  .hjprofile-stats {
    float: none;
    width: auto;
  }

  .hjprofile-image img {
    width: 7.7rem;
  }

  .hjprofile-user-settings {
    flex-basis: calc(100% - 10.7rem);
    display: flex;
    flex-wrap: wrap;
    margin-top: 1rem;
  }

  .hjprofile-user-name {
    font-size: 2.2rem;
  }

  .hjprofile-edit-btn {
    order: 1;
    padding: 0;
    text-align: center;
    margin-top: 1rem;
  }

  .hjprofile-edit-btn {
    margin-left: 0;
  }

  .hjprofile-edit-btn,
  .hjprofile-stats {
    flex-basis: 100%;
  }

  .hjprofile-stats {
    order: 1;
    margin-top: 1.5rem;
  }

  .hjprofile-stats ul {
    display: flex;
    text-align: center;
    padding: 1.2rem 0;
    border-top: 0.1rem solid #dadada;
    border-bottom: 0.1rem solid #dadada;
  }

  .hjprofile-stats li {
    font-size: 1.4rem;
    flex: 1;
    margin: 0;
  }

  .hjprofile-stat-count {
    display: block;
  }
}

@supports (display: grid) {
  .hjprofile {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-rows: repeat(3, auto);
    grid-column-gap: 3rem;
    align-items: center;
  }

  .hjprofile-image {
    grid-row: 1 / -1;
  }

  .hjprofile-image,
  .hjprofile-user-settings,
  .hjprofile-stats,
  .postgallery-item,
  .postgallery {
    width: auto;
    margin: 0;
  }

  @media (max-width: 1000px) {
    .hjprofile {
      grid-template-columns: auto 1fr;
      grid-row-gap: 1.5rem;
    }

    .hjprofile-image {
      grid-row: 1 / 2;
    }

    .hjprofile-user-settings {
      display: grid;
      grid-template-columns: auto 1fr;
      grid-gap: 1rem;
    }

    .hjprofile-edit-btn,
    .hjprofile-stats {
      grid-column: 1 / -1;
    }

    .hjprofile-user-settings,
    .hjprofile-edit-btn,
    .hjprofile-settings-btn,
    .hjprofile-stats {
      margin: 0;
    }
  }
}
/*tabs css */
button:focus,
input:focus,
textarea:focus,
select:focus {
  outline: none;
}

.feedtabs {
  display: block;
  display: -webkit-flex;
  display: -moz-flex;
  display: flex;
  -webkit-flex-wrap: wrap;
  -moz-flex-wrap: wrap;
  flex-wrap: wrap;
  margin: 0;
  overflow: hidden;
}
.feedtabs [class^="feedtab"] label,
.feedtabs [class*=" feedtab"] label {
  color: #e0996d;
  cursor: pointer;
  display: block;
  font-size: 1.1em;
  font-weight: 300;
  line-height: 1em;
  padding: 2rem 0;
  text-align: center;
}
/* .feedtabs [class^="feedtab"] [type="radio"],
.feedtabs [class*=" feedtab"] [type="radio"] { */
.feedtabs label {
  border-bottom: 1px solid rgba(239, 237, 239, 0.5);
  cursor: pointer;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  display: block;
  width: 100%;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}
/* .feedlabel label :hover,
.feedlabel label :focus  */
.feedtabs label :hover,
.feedtabs label :focus {
  border-bottom: px solid #fd264f;
}

.feedtabs [class^="feedtab"] [type="radio"]:checked,
.feedtabs [class*=" feedtab"] [type="radio"]:checked {
  border-bottom: 2px solid #fd264f;
}
.feedtabs [class^="feedtab"] [type*="radio"]:checked + div,
.feedtabs [class*=" feedtab"] [type="radio"]:checked + div {
  opacity: 1;
}
.feedtabs [class^="feedtab"] [type="radio"] + div,
.feedtabs [class*=" feedtab"] [type="radio"] + div {
  display: block;
  opacity: 0;
  padding: 2rem 0;
  width: 90%;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}
.feedtabs .feedtab-2 {
  width: 50%;
}
.feedtabs .feedtab-2 [type="radio"] + div {
  width: 200%;
  margin-left: 200%;
}
.feedtabs .feedtab-2 [type="radio"]:checked + div {
  margin-left: 0;
}
.feedtabs .feedtab-2:last-child [type="radio"] + div {
  margin-left: 100%;
}
.feedtabs .feedtab-2:last-child [type="radio"]:checked + div {
  margin-left: -100%;
}
/* CONTAINER*/
.postcontainer {
  padding-top: 80px;
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}

/* 
 */
h1.codetitle {
  padding-right: 100px;
}
.codetitle body {
  padding: 3em 1em;
  font-family: "Ubuntu Mono", sans-serif;
  line-height: 1.6em;
  color: white;
  background: #00005a;
}
.codetitle em {
  font-style: normal;
  font-size: 1.2em;
}
.codetitle i {
  font-style: normal;
  color: #00ff77;
}
.codetitle h1,
.codetitle p,
.codetitle hr
 {

  max-width: 40rem;
  margin: 0 auto;
}
.codetitle p {
  padding-bottom: 1em;
}
.codetitle h1 {
  position: relative;
  padding: 3rem 0 6rem 0;
  font-size: 3em;
  line-height: 1em;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 5px;
}
.codetitle h1:before {
  content: "\3C\21\2013\2013";
  position: absolute;
  top: 0;
  left: 0;
  color: #efa23c;
}
.codetitle h1:after {
  content: "\2013\2013\3E";
  position: absolute;
  bottom: 3rem;
  left: 0;
  color: #efa23c;
}
.codetitle .hr {
  position: relative;
  padding: 3rem 0;
  border: none;
}
.codetitle hr:before {
  content: "\2044\2217\2013\2013\2013\2013\2013\2217\2044";
  position: absolute;
  top: 1.2rem;
  right: 0;
  left: 0;
  font-size: 1.5em;
  color: #efa23c;
  text-align: center;
}
.codetitle .etc {
  position: relative;
  padding: 6rem 0 1rem 0;
  text-align: center;
  color: #efa23c;
}
.codetitle .etc a {
  color: #efa23c;
}
.codetitle .etc:before {
  content: "\2022";
  position: absolute;
  top: 1rem;
  left: 50%;
  color: white;
  text-shadow: 0 1rem 0 white, 0 2rem 0 white;
}
</style>
