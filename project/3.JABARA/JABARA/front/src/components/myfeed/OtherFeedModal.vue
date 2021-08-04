<template>
  <div class="MyfeedModal">
    <div class="cancle-button">
      <button @click="cancleWrite">
        <i style="color: white;" class="fas fa-times fa-2x"></i>
      </button>
    </div>
    <div class="form-box">
      <div class="real">
        <div class="mfeed-image">
          <!-- <div class="mimage-wrap"> -->
            <!-- :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/snspost//' + post.POST_NUMBER" -->
            <img
              :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/snspost//' + detailPost.postNumber"
              alt="메인피드사진"
              width="100%"
              height="auto"
            />
          <!-- </div> -->
        </div>
      </div>
      <div class="info-wrap">
        <div class="user-info">
          <div class="user-img">
            <div class="img-shape">
              <img
                :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage"
                alt="프로필사진"
                width="200"
                height="200"
                class="profile"
              />
            </div>
          </div>

          <div class="user-name">
            <p @click="otherUserDetail(detailPost.userId)">{{detailPost.userId}}</p>
          </div>
        </div>
        <div class="post-delete">
          <p
            v-if="detailPost.userId == loginUserId"
            @click="deletePost(detailPost)"
            class="delete-detailPost-button"
          >삭제</p>
        </div>
      </div>

      <div class="text-comment">
        <div class="feed-content-header">
          <div class="heart emoji">
            <i
              :style="{ display: emptyHEmo }"
              @click="isEmptyHEmo"
              class="far fa-heart empty-heart"
            ></i>
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
              <p>좋아요: {{ detailPost.postLike }}개</p>
            </div>
          </div>
        </div>

        <div class="m-text">{{detailPost.postSnsContent}}</div>
        <div>
          <ul>
            <li
              v-for="(tag, index) in tagList"
              :key="index + '_items'"
              class="tag-style"
            >#{{ tag.TAG_NAME }}</li>
          </ul>
        </div>
        <hr />
        <p style="text-align:left; font-weight:700;">댓글</p>
        <div class="comment-list">
          <ul class="comment-ul">
            <p v-if="CommentDataList.length == 0" style="color:gray">댓글이 없습니다</p>
            <li v-else v-for="(comment, index) in CommentDataList" :key="index">
              <div
                v-if="comment.COMMENT_NUMBER==modifyCommentNumber"
                class="comment-modify-button"
                @click="modifyCommentData(comment)"
              >
                <p>수정하기</p>
              </div>
              <div v-else class="comment-etc">
                <!-- @click="deleteOneComment(comment.COMMENT_NUMBER)" -->
                <div v-if="comment.USER_ID == loginUserId">
                  <p class="comment-delete" @click="cAlert(comment.COMMENT_NUMBER)">삭제</p>
                  <p class="comment-modify" @click="changeInputComment(comment)">수정</p>
                </div>
              </div>
              <router-link class="commentUser" to>{{comment.USER_ID}}</router-link>
              <!-- 어떻게 받아올까 -->
              <!-- {{calTime(comment.commentDate)}} -->
              <p v-if="comment.COMMENT_NUMBER==modifyCommentNumber">
                <input type="text" v-model="commentPrev" class="comment-modify-input" />
              </p>
              <p v-else>{{comment.COMMENT_CONTENT}}</p>
            </li>
          </ul>
        </div>
      </div>
      <div class="add-commet">
        <div class="input-wrap">
          <input
            type="text"
            v-model="commentContent"
            @keyup.enter="submitComment"
            placeholder="댓글 달기"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommentApi from "@/apis/commentAPI";
import UserApi from "@/apis/userAPI";
import TagApi from "@/apis/tagAPI";
import LikeApi from "../../apis/likeAPI";
import PostApi from "@/apis/postAPI";
export default {
  name: "OtherFeedModal",
  props: {
    detailPost: Object
  },
  data() {
    return {
      loginUserId: this.$session.get("loginUserId"),
      profileimage: "",
      tagList: [],
      //   openModal: this.Fshow,
      emptyHEmo: "",
      fullHEmo: "none",
      emptyBEmo: "",
      fullBEmo: "none",

      testText: "",
      commentPrev: "",
      modifyCommentNumber: -1,

      // comment
      commentContent: "",
      commentNumber: 0,
      CommentDataList: []
    };
  },
  created() {
    console.log("####", this.detailPost);
    // UserApi.requestAWSUserImage(
    //   this.loginUserId,
    //   res => {
    //     console.log("이미지" + res.data);
    //     this.profileimage = res.data;
    //   },
    //   error => {
    //     console.log(error);
    //   }
    // );
    this.reloadcommentDataList();
    this.reLoadprofileimage();
    this.getTags();
    this.checkkeep();
    this.checklike();
  },
  methods: {
    deletePost(detailPost) {
      PostApi.deletePost(
        detailPost.postNumber,
        res => {
          if (res.data == "success") {
            this.$emit("deletePost", detailPost);
            // this.$emit("changeDetailshow");
            console.log(res);
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    checklike() {
      let data = {
        postNumber: this.detailPost.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.detailPost.userId
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
        postNumber: this.detailPost.postNumber,
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
    isEmptyHEmo() {
      //좋아요 추가
      let data = {
        postNumber: this.detailPost.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.detailPost.userId
      };
      LikeApi.clickLike(
        data,
        res => {
          console.log("좋아요" + res.data);
          if (res.data == "1") {
            this.emptyHEmo = "none";
            this.fullHEmo = "";
            this.detailPost.postLike += 1;
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
        postNumber: this.detailPost.postNumber,
        targetuserId: this.$session.get("loginUserId"),
        userId: this.detailPost.userId
      };
      LikeApi.clickLike(
        data,
        res => {
          // console.log("이미지" + res.data);
          if (res.data == "0") {
            this.emptyHEmo = "";
            this.fullHEmo = "none";
            this.detailPost.postLike -= 1;
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
        postNumber: this.detailPost.postNumber,
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
        postNumber: this.detailPost.postNumber,
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

    getTags() {
      TagApi.requestTag(
        this.detailPost.postNumber,
        res => {
          let tagtempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            tagtempArr.push(res.data[i]);
          }
          this.tagList = tagtempArr;
        },
        error => {
          console.log(error);
        }
      );
    },
    otherUserDetail(userId) {
      if (userId == this.loginUserId) {
        this.$router.push("/post/postmyhome");
      } else {
        this.$router.push("/post/posthome?userId=" + userId).catch(() => {});
      }
    },
    reLoadprofileimage() {
      UserApi.requestAWSUserImage(
        this.detailPost.userId,
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
      console.log("MODALTEST", this.detailPost);
      CommentApi.selectComment(
        this.detailPost.postNumber,
        res => {
          // console.log("comment data확인", res)
          let commenttempArr = [];
          for (let i = res.data.length - 1; i >= 0; i--) {
            commenttempArr.push(res.data[i]);
          }
          this.CommentDataList = commenttempArr;
          // console.log("commment hi"+this.CommentDataList)
        },
        error => {
          console.log(error);
        }
      );
    },
    pushNewCommentData(data) {
      // console.log("comment안에는?",data)
      if (data.COMMENT_NUMBER) {
        this.CommentDataList.push(data);
      }
      // console.log("comment push됐다", this.CommentDataList)
      this.CommentDataList.sort(function(a, b) {
        return b.COMMENT_NUMBER - a.COMMENT_NUMBER;
      });
    },
    submitComment() {
      // console.log("댓글보내기")
      let data = {
        commentContent: this.commentContent,
        postNumber: this.detailPost.postNumber,
        userId: this.$session.get("loginUserId"),
        targetuserId: this.detailPost.userId
        // commentNumber: 0,
      };
      // console.log(data)
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
          // console.log("comment number는" + res.data)
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
      // console.log("######################",comment)
      let data = {
        postNumber: this.detailPost.postNumber,
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
            // console.log("CMDatalist는?"+CMtempData)
            this.modifyCommentNumber = -1;
          }
        },

        error => {
          console.log(error);
        }
      );
    },
    modifyOrderComment(data) {
      // console.log("&&&&&&&&&&&&&&&&&",data)
      for (let i = 0; i < this.CommentDataList.length; i++) {
        // console.log("욕빡대",this.CommentDataList[i])
        if (this.CommentDataList[i].COMMENT_NUMBER == data.COMMENT_NUMBER) {
          this.CommentDataList.splice(i, 1, data);
          // console.log("@@@@@@",this.CommentDataList)
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
        postNumber: this.detailPost.postNumber,
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
    // ...mapMutations(["setViewStatus"]),
    cancleWrite() {
      this.openModal = !this.openModal;
      this.$emit("modal-show");
    }
  }
};
</script>

<style scoped>
/* 전체화면영역 */
.post-delete {
  float: right;
  display: inline-table;
  padding: 2%;
}
.delete-detailPost-button:hover {
  cursor: pointer;
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

.tag-style {
  display: inline;
  margin-right: 15px;
  background-color: #ffe5dc;
  border-radius: 5px;
  padding: 1px;
}
.profile {
  width: 50px;
  height: 50px;
  background-color: mediumaquamarine;
  display: inline-table;
  float: left;
  border-radius: 50%;
}
.MyfeedModal {
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  position: fixed;
  z-index: 9999;
  background: rgba(0, 0, 0, 0.5);
  transition: 0.5s;
  /* position: relative; */
}
.form-box {
  border-radius: 5px;
  position: absolute;
  /* 정가운데 정렬 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 80%;

  background: white;
  margin: 0 auto;
}

.info-wrap {
  width: 40%;
  height: 12%;
  float: left;
  border-bottom: 1px solid rgba(128, 128, 128, 0.548);
  /* margin-bottom: 2%; */
}
.user-info {
  width: 80%;
  float: left;
  height: 100%;
  margin-bottom: 0;
}
.user-img {
  width: 72px;
  height: 100%;
  float: left;
  position: relative;
}
.img-shape {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* float:right; */
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: lawngreen;
}

.user-name {
  padding-left: 2%;
  width: 50%;
  height: 100%;
  float: left;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-top: 20px;
}
.cancle-button {
  right: 0;
  top: 0;
}
.cancle-button button {
  padding: 20px;
  float: right;
}

/* .img-wrap {
  width: 60%;
  height: 100%;
} */
.real {
  /* position: relative; */
  height: 100%;
  width: 60%;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  float: left;
}
.mfeed-image {
  /* float:left; */
  position: relative;
  /* border-radius: 5px; */
  /* border-top-left-radius: 5px; */
  /* border-bottom-left-radius: 5px; */
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  /* overflow-x: hidden;
  overflow-y: auto; */
  background: rgba(206, 206, 206, 0.445);
  border-right: 1px solid rgba(128, 128, 128, 0.548);
  -ms-overflow-style: none;
}
.mfeed-image img{
  position: absolute;
  width: 100%;
  height: auto;
  top: 50%;
  left: 50%;
  /* padding-top: 75%;  */
  transform: translate(-50%, -50%);
}
.mfeed-image::-webkit-scrollbar {
  display: none;
}
/* 
.mimage-wrap img {
  position: absolute;
  top: 0;
  left: 0;
  max-width: 100%;
  height: auto;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
} */
.text-comment {
  font-size: 90%;
  float: left;
  width: 40%;
  height: 79%;
  padding: 4%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.text-comment::-webkit-scrollbar {
  display: none;
}
.m-text {
  padding-bottom: 20px;
}
.add-commet {
  width: 40%;
  float: left;
  height: 8%;
  border-top: 1px solid rgba(128, 128, 128, 0.548);
}
.input-wrap {
  display: flex;
}
.input-wrap input {
  border: 0;
}
.comment-modify-button {
  float: right;
  margin-right: 20px;
}
.comment-modify-button:hover {
  cursor: pointer;
}
.comment-modify {
  float: right;
  margin-right: 10px;
  width: 30px;
  height: 100%;
}
p.comment-modify:hover {
  cursor: pointer;
}
.comment-delete {
  float: right;
  margin-right: 20px;
  width: 30px;
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
.comment-list {
  width: 100%;
  margin: 0;
}
.comment-ul {
  width: 100%;
  font-size: 90%;
  line-height: 30px;
  height: auto;
  /* margin-left: 8px; */
}
.comment-ul li p {
  white-space: normal;
  word-break: break-all;
  word-wrap: break-word;
}
</style>