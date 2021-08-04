<template>
  <div class="postcontainer">
    <header>
      <SearchedProfile />
    </header>
    <hr />

    <SearchedFeed />
  </div>
</template>
<script>
import SearchedProfile from "@/components/searchedfeed/SearchedProfile.vue";
import SearchedFeed from "@/components/searchedfeed/SearchedFeeds.vue";
import tagAPI from "@/apis/tagAPI";
export default {
  name: "PostSearched",

  components: {
    SearchedProfile,
    SearchedFeed
  },
  data() {
    return {
      // postCount: -1,
      checkPostCount: false,
      seachAnother: ""
    };
  },
  created() {
    console.log("####tester", this.searchword);
    tagAPI.searchPostByTag(
      this.searchword,
      res => {
        console.log(res);
        this.$store.commit("setSearchedDatas", res.data);
        this.$store.commit("setsamplePost", res.data[0]);
        this.$store.commit("setPostCount", res.data.length);
      },
      error => {
        this.$router.push("/search/notfound").catch(() => {});
        console.log(error);
      }
    );
  },
  computed: {
    searchword: function() {
      console.log("computed excute!!!!");
      return this.$store.state.searchTagName;
    },
    postCount: function() {
      return this.$store.state.postCount;
    }
  },
  watch: {
    postCount(value) {
      console.log("SEARCHEDWATCH POSTCNT: ", value);
      if (value == 0) {
        this.$router.push("/search/notfound").catch(() => {});
      }
    },
    searchword(value) {
      console.log("SEARCHEDWATCH SEARCHWORD: ", value);
      tagAPI.searchPostByTag(
        value,
        res => {
          console.log(res);
          this.$store.commit("setSearchedDatas", res.data);
          this.$store.commit("setsamplePost", res.data[0]);
          this.$store.commit("setPostCount", res.data.length);
        },
        error => {
          console.log(error);

          this.$router.push("/search/notfound").catch(() => {});
        }
      );
    }
  },

  methods: {}
};
</script>
<style scoped>
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
  max-width: 93.5rem;
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

@media screen and (max-width: 40rem) {
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

  @media (max-width: 40rem) {
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
</style>
