<template>
  <div id="feed-wrap">
    <div id="feed">
      <div class="allfeed-list-wrap">
        <Feedlist
          v-for="item in items"
          @deleteitem="deleteitem"
          :key="item.postNumber"
          :item="item"
        />
        <div v-if="isBaseFollow=='false'">
          <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
        </div>
      </div>
      <div class="allrank-list-wrap">
        <Ranklist class="allrank-list" />
      </div>
      <!-- <div style="display:none">
        <MyFeeds :items="items"/>
      </div>-->
    </div>
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import Feedlist from "@/components/feed/Feedlist.vue";
import Ranklist from "@/components/feed/Ranklist.vue";
import FollowApi from "../../apis/followAPI";
// import MyFeeds from "@/components/myfeed/MyFeeds.vue";
import KaKaoAPI from "@/apis/kakaoAPI";
import GetPostsApi from "@/apis/postAPI";
export default {
  namd: "Allfeed",
  components: {
    Feedlist,
    Ranklist,
    InfiniteLoading
    // MyFeeds,
  },

  data() {
    return {
      limit: 0,
      loginUserId: "",
      following: "",
      isBaseFollow: "",
      items: [
        // {
        //   message: "hello",
        //   img: "https://picsum.photos/id/1018/598/399",
        //   content:
        //     "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatatnon proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        // },
        // {
        //   message: "bye",
        //   img: "https://images.mypetlife.co.kr/content/uploads/2019/09/04222847/dog-panting.jpg",
        //   content:
        //     "Hello Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatatnon proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Hello Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatatnon proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        // }
      ]
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    FollowApi.selectFollowingCountById(
      this.loginUserId,
      res => {
        console.log(res);
        this.following = res.data;
        if (this.following == 0) {
          GetPostsApi.getPostsByInf(
            this.limit,
            res => {
              console.dir(res);
              this.isBaseFollow = "false";
              this.items = res.data;
              console.log("FEED", this.items);
            },
            error => {
              console.log(error);
            }
          );
          // this.getPost();
        } else {
          GetPostsApi.searchPostByFollowing(
            this.loginUserId,
            res => {
              console.dir(res);
              console.log("팔로 0000000000000000000  아님아님");
              this.isBaseFollow = "true";
              this.items = res.data;
              console.log(this.items);
            },
            error => {
              console.log(error);
            }
          );
        }
      },
      error => {
        console.log(error);
      }
    );

    // console.log("created");
    // GetPostsApi.getPostsByInf(
    //   this.limit,
    //   res => {
    //     console.dir(res);
    //     this.items = res.data;
    //     console.log("FEED", this.items);
    //   },
    //   error => {
    //     console.log(error);
    //   }
    // );
    // this.getPost();

    // this.testItems;
  },
  methods: {
    infiniteHandler($state) {
      console.log("INFINFIFNIFNIFNIFNINF");
      GetPostsApi.getPostsByInf(
        this.limit + 10,
        res => {
          setTimeout(() => {
            if (res.data.length) {
              for (let i = 0; i < res.data.length; i++) {
                this.items.push(res.data[i]);
              }
              console.log("FEEDLOAD", this.items);
              $state.loaded();
              this.limit += 10;
              if (this.items.length / 10 == 0) {
                $state.complete();
              }
            } else {
              $state.complete();
            }
          }, 1000);
        },
        error => {
          console.log(error);
        }
      );
    },
    getPost() {
      FollowApi.selectFollowingCountById(
        this.loginUserId,
        res => {
          console.log(res);
          this.following = res.data;
          if (this.following == 0) {
            GetPostsApi.getPosts(
              res => {
                console.log("팔로 0000000000000000000");
                this.isBaseFollow = false;
                this.items = res.data;
              },
              error => {
                console.log(error);
              }
            );
          } else {
            GetPostsApi.searchPostByFollowing(
              this.loginUserId,
              res => {
                console.dir(res);
                console.log("팔로 0000000000000000000  아님아님");
                this.isBaseFollow = true;
                this.items = res.data;
                console.log(this.items);
              },
              error => {
                console.log(error);
              }
            );
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteitem(item) {
      for (let i = 0; i < this.items.length; i++) {
        if (this.items[i].postNumber == item.postNumber) {
          this.items.splice(i, 1);
        }
      }
      //   location.reload();
      console.log(item);
      alert("게시글이 삭제되었습니다.");
    },
    logout() {
      let kakaoToken = this.$session.get("kakaoAccessToken");
      if (kakaoToken) {
        alert("카카오 계정 로그아웃!");
        KaKaoAPI.requestKakaoLogout(
          kakaoToken,
          res => {
            console.log("카카오 로그아웃 요청성공");
            console.log(kakaoToken);
            console.dir(res);
          },
          error => {
            console.log(error);
          }
        );
      }
      this.$session.destroy();
      location.reload();
    }
  }
};
</script>

<style scoped>
#feed-wrap {
  /* margin: 150px auto; */
  /* position:relative; */
  margin: 150px auto;
  width: 1024px;
  height: 100vh;
}
#feed {
  /* position:absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%); */
  /* clear: both; */
  height: 100%;
  margin: 150px auto;
  width: 90%;
}
.allfeed-list-wrap {
  float: left;
  width: 70%;
  margin-right: 5%;
  margin-bottom: 100px;
}
.allrank-list-wrap {
  position: relative;
  float: left;
  width: 25%;
}
.allrank-list {
  position: fixed;
  width: 15%;
  height: 100%;
}

/* 1280 1024 960 768 576 */
@media screen and (max-width: 960px) {
  * {
    font-size: 100%;
  }
  /* input {
    width: 50%;
  } */
  .allrank-list-wrap {
    display: none;
  }
  #feed-wrap {
    width: 70%;
    margin: 0 auto;
  }
  #feed {
    width: 100%;
    margin-top: 100px;
    margin-bottom: 60px;
  }
  .allfeed-list-wrap {
    width: 100%;
  }
}
@media screen and (max-width: 576px) {
  #feed-wrap {
    width: 100%;
    margin: 0 auto;
  }
}
</style>
