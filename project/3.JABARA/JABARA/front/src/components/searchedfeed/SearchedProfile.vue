<template>
  <div class="searched-profile">
    <div v-if="samplePost">
      <div class="searched-sample-image">
        <img
          :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/snspost//' + samplePost.POST_NUMBER"
          onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/else//ALGO.webp'"
          class="postgallery-image"
          alt
          width="250"
          height="250"
        />
      </div>
      <div class="searched-result">
        <p class="searched-word">#{{searchword}}</p>
        <p class="searched-post-count">게시물 {{comma(postCount)}}</p>
      </div>
    </div>
    <div v-else>
      <meta http-equiv="refresh" content="0;url=/search/notfound" />
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchedProfile",
  components: {},
  data() {
    return {
      // postCount: 1002,
      sampleImage: "" // 제일 상단 왼쪽에 샘플이미지 게시글중 하나..
    };
  },
  props: [],
  computed: {
    searchword: function() {
      return this.$store.state.searchTagName;
    },
    samplePost: function() {
      return this.$store.state.samplePost;
    },
    postCount: function() {
      return this.$store.state.postCount;
    }
  },
  watch: {
    samplePost(val) {
      if (val) {
        console.log("SearchProfile WATHCK SAMPLEPOST:", val);
      }
    }
  },
  created() {
    // if (this.samplePost) {
    //   this.$router.push("/search/notfound").catch(() => {});
    // }
  },
  methods: {
    comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    }
  }
};
</script>
<style scoped>
/* Profile Section */

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
.searched-word {
  font-size: xxx-large;
}
.searched-post-count {
  font-size: x-large;
}
.searched-result {
  display: inline-block;
  padding: 8%;
}
.postcontainer {
  padding-top: 80px;
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}

.searched-profile {
  width: 80%;
  padding-top: 100px;
  padding-bottom: 0px;
  margin-left: 5%;
  margin-top: 5%;
  display: inline-block;
  /* padding: 5rem 0; */
}
.searched-sample-image {
  float: left;
}
.searched-sample-image img {
  border-radius: 50%;
}
</style>
