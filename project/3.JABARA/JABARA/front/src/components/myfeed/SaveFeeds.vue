<template>
  <div>
    <div v-if="items.length==0">
      <p style="text-align: center;">보관된 게시글이 없습니다</p>
    </div>
    <div class="postcontainer">
      <div class="postgallery">
        <span
          v-for="(item, index) in items"
          :key="index + '_items'"
          @click="changeDetailshow(item)"
        >
          <div class="postgallery-item" tabindex="0">
            <div v-if="item.POST_CATEGORY == 'SNS'" class="feed-img">
              <img
                v-bind:src="'https://jobara.s3.ap-northeast-2.amazonaws.com/snspost//' + item.POST_NUMBER"
                class="postgallery-image"
                alt
                width="500"
                height="500"
              />
            </div>
            <div v-else-if="item.POST_CATEGORY == 'CODE'" class="feed-img">
              <div class="img">
                <div class="content">
                  <h1 style="z-index:-1; color:#cce0f4;">{{ cutTitle(item.POST_ALGO_TITLE) }}</h1>
                </div>
                <div class="img-cover"></div>
              </div>
            </div>
            <div class="postgallery-item-info">
              <ul>
                <li class="postgallery-item-likes">
                  <span class="postvisually-hidden"></span>
                  <i style="color:red;" class="fas fa-heart" aria-hidden="true"></i>
                  {{item.POST_LIKE}}
                </li>
              </ul>
            </div>
          </div>
        </span>
      </div>
      <div v-if="detailShow">
        <SaveFeedModal :detailPost="detailPost" @updateBookMark="updateBookMark" />
      </div>
      <!-- End of gallery -->

      <!-- <div class="loader"></div> -->
    </div>
    <!-- End of container -->
  </div>
</template>

<script>
import PostApi from "../../apis/postAPI";
import SaveFeedModal from "./SaveFeedModal.vue";

export default {
  name: "SaveFeeds",
  components: {
    SaveFeedModal
  },
  data() {
    return {
      loginUserId: "",
      pageUserId: "",
      items: "",

      detailPost: {},

      Feeditems: this.items,
      detailShow: false,
      hello: "sunburn300.png",
      heelo2: "code-1839406_1280.jpg"
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    console.log("여기save에 들어왔다" + this.loginUserId);
    PostApi.selectStarPost(
      this.searchpageUser(),
      res => {
        let tempArr = [];
        console.log(this.searchpageUser());
        for (let i = 0; i < res.data.length; i++) {
          console.log("여기보관!!!!!!!!!" + res.data[i]);

          tempArr.push(res.data[i]);
        }
        this.items = tempArr;
        console.log("목록 items : ", this.items);
        // this.items = res.data;
        this.$store.commit("setPost", this.items);
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    updateBookMark(post) {
      console.log(post);
      console.log("update 실행됨", post);
      if (post) {
        for (let i = 0; i < this.items.length; i++) {
          if (this.items[i].POST_NUMBER == post.POST_NUMBER) {
            this.items.splice(i, 1);
            alert("북마크가 삭제되었습니다.");
            this.detailShow = false;
            break;
          }
        }
      } else {
        this.detailShow = !this.detailShow;
      }
    },
    changeDetailshow(post) {
      console.log("SAVEFEED# ", post);
      if (post) {
        if (post.POST_CATEGORY == "SNS") {
          this.detailPost = post;
        } else {
          this.$router
            .push(
              `/post/poatalgo?userId=${post.USER_ID}&postNumber=${post.POST_NUMBER}`
            )
            .catch(() => {});
        }
      }
      this.detailShow = !this.detailShow;
    },
    searchpageUser: function() {
      console.log("searchpageUser at vuex!!!");
      this.pageUserId = this.$store.state.pageUserId;
      console.log("!!!!!!!!username" + this.pageUserId);
      return this.$store.state.pageUserId;
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
  }
};
</script>
<style scoped>
*,
*::before,
*::after {
  box-sizing: border-box;
}
/* algo */
.feed-img {
  width: 390px;
  height: 390px;
}
.img {
  position: relative;
  background-image: url(https://jobara.s3.ap-northeast-2.amazonaws.com/else//ALGO.webp);
  width: 390px;
  height: 390px;
  z-index: -2;
  background-size: cover;
  overflow: hidden;
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
/*  */
body {
  font-family: "Open Sans", Arial, sans-serif;
  min-height: 100vh;
  background-color: #fafafa;
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
  object-fit: cover;
  width: 100%;
  height: 250px;
} /* Gallery Section */

.postgallery {
  display: flex;
  flex-wrap: wrap;
  margin: -1rem -1rem;
  padding-bottom: 3rem;
}

.postgallery-item {
  position: relative;
  flex: 1 0 22rem;
  margin: 1rem;
  color: #fff;
  cursor: pointer;
}

.postgallery-item:hover .postgallery-item-info,
.postgallery-item:focus .postgallery-item-info {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  object-fit: cover;
  width: 390px;
  height: 390px;
}

.postgallery-item-info {
  display: none;
}

.postgallery-item-info li {
  display: inline-block;
  font-size: 1.7rem;
  font-weight: 600;
}

.postgallery-item-likes {
  margin-right: 2.2rem;
}

.postgallery-item-type {
  position: absolute;
  top: 1rem;
  right: 1rem;
  font-size: 2.5rem;
  text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
  object-fit: cover;
  width: 390px;
  height: 390px;
}

.fa-clone,
.fa-comment {
  transform: rotateY(180deg);
}

.postgallery-image {
  width: 100%;

  height: auto;

  object-fit: cover;
  object-fit: cover;
  width: 390px;
  height: 390px;
}

/* Loader */

/* .loader {
  width: 5rem;
  height: 5rem;
  border: 0.6rem solid #999;
  border-bottom-color: transparent;
  border-radius: 50%;
  margin: 0 auto;
  animation: loader 500ms linear infinite;
} */

/* Spinner Animation */

@keyframes loader {
  to {
    transform: rotate(360deg);
  }
}

/*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling. 

*/

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

  .postgallery {
    /* display: grid;
    grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr)); */
    grid-gap: 2rem;
  }

  .hjprofile-image,
  .hjprofile-user-settings,
  .hjprofile-stats,
  .postgallery-item,
  .postgallery {
    width: auto;
    margin: 0;
  }

  @media (max-width: 40rem) {
    .hjprofile {
      grid-template-columns: auto 1fr;
      grid-row-gap: 1.5rem;
    }

    .hjprofile-image {
      /* grid-row: 1 / 2; */
    }

    .hjprofile-user-settings {
      display: grid;
      /* grid-template-columns: auto 1fr; */
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
</style>
