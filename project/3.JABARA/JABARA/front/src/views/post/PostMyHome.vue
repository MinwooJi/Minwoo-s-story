<template>
  <div class="postcontainer">
    <header>
      <Feedprofile />
    </header>
    <section class="profile-tabs">
      <input id="tab-one" type="radio" name="grp" checked="checked" />
      <label class="tab-label" for="tab-one">
        <i class="fas fa-th">&emsp;&emsp;&emsp;게시물</i>
      </label>
      <div class="tab-div">
        <OtherFeeds />
      </div>
      <input id="tab-two" type="radio" name="grp" />
      <label class="tab-label" for="tab-two">
        <i class="fas fa-laptop-code">ALGO</i>
      </label>
      <div class="tab-div">
        <AlgoFeeds />
      </div>

      <!-- Collection View Tab for product image thumbs -->

      <input id="tab-three" type="radio" name="grp" />
      <label class="tab-label" for="tab-three">
        <i class="fas fa-save"></i> 저장됨
      </label>
      <div class="tab-div">
        <SaveFeeds />
      </div>
    </section>
  </div>
</template>
<script>
import Feedprofile from "@/components/myfeed/Feedprofile.vue";
import OtherFeeds from "@/components/myfeed/OtherFeeds.vue";
import AlgoFeeds from "@/components/myfeed/AlgoFeeds.vue";
import SaveFeeds from "@/components/myfeed/SaveFeeds.vue";
export default {
  name: "MyFeedHome",

  components: {
    Feedprofile,
    OtherFeeds,
    AlgoFeeds,
    SaveFeeds
  },
  data() {
    return {
      loginUserId: ""
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.$store.commit("setpageUserId", this.loginUserId);
  },
  methods: {}
};
</script>
<style scoped>
.profile-tabs {
  display: -webkit-flex;
  display: flex;
  -webkit-flex-wrap: wrap;
  flex-wrap: wrap;
  justify-content: center;
}

label.tab-label {
  padding: 0.5rem 10%;
  cursor: pointer;
  z-index: 1;
  margin-left: -1px;
}

label.tab-label:first-of-type {
  margin-left: 0;
}

.tab-div {
  width: 90%;
  margin-top: -1px;
  padding: 1em;
  -webkit-order: 1;
  order: 1;
}

input[type="radio"],
.tab-div {
  display: none;
}

input[type="radio"]:checked + label.tab-label {
  background: #fff;
  border-bottom: 1px solid #000;
}

input[type="radio"]:checked + label.tab-label + .tab-div {
  display: block;
}

.collection-block {
  display: flex;
  flex-flow: wrap;
  justify-content: center;
  width: 100%;
  max-height: none;
  margin-top: 0;
  padding: 0;
  border: none;
}

.collection-block .collection-img {
  padding: 0.2em;
}

.review-block {
  display: flex;
  align-items: center;
  width: 100%;
  max-height: none;
  margin-top: 0;
  padding: 0;
  border: none;
}

.review-avatar {
  margin-right: 1em;
}

.review-body {
  flex: 1;
}

.review-vote {
  margin-left: 1em;
  display: flex;
  flex-direction: column;
}

@media only screen and (min-width: 320px) and (max-width: 444px) {
  label.tab-label {
    padding: 0.5rem 1.3rem;
  }
  .tab-div {
    height: 100%;
  }
  .review-body {
    font-size: 0.8em;
  }
  .collection-img {
    height: 100px;
  }
}

/*  */
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
/* CONTAINER*/
.postcontainer {
  padding-top: 80px;
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}
</style>
