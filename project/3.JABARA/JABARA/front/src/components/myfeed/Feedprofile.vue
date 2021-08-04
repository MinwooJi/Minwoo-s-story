<template>
  <div class="hjprofile">
    <div class="hjprofile-image">
      <img
        :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage"
        onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
        alt="프로필사진"
        width="200"
        height="200"
      />
    </div>
    <div class="hjprofile-user-settings">
      <h1 v-text="profilename" class="hjprofile-user-name"></h1>
      <!-- <a class="followbutton">follow</a> -->
    </div>
    <div class="hjprofile-stats">
      <ul>
        <li style="font-size:150%;">
          <span v-text="followers" class="hjprofile-stat-count"></span> followers
        </li>
        <li style="font-size:150%;">
          <span v-text="following" class="hjprofile-stat-count"></span> following
        </li>
      </ul>
    </div>
    <div class="hjprofile-user-settings">
      <h1 class="hjprofile-user-name"></h1>
      <AlgoCalendar />
    </div>
  </div>
</template>

<script>
import UserApi from "../../apis/userAPI";
import FollowApi from "../../apis/followAPI";
import AlgoCalendar from "@/components/myfeed/AlgoCalendarChart.vue";
export default {
  name: "Feedprofile",
  components: {
    AlgoCalendar
  },
  data() {
    return {
      loginUserId: "",
      userNickname: "",
      profileimage: "",
      profilename: "",
      followers: "0",
      following: "0"
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.userNickname = this.$session.get("loginUserNickName");

    this.profilename = this.loginUserId + " [ " + this.userNickname + " ] ";
    this.profileimage = this.$session.get("loginUserProfileImage");
    console.log("별명" + this.profilename);
    UserApi.requestAWSUserImage(
      this.loginUserId,
      res => {
        console.log("이미지" + res.data);
        this.profileimage = res.data;
      },
      error => {
        console.log(error);
      }
    );
    this.selectfollowers();
    this.selectfollowing();
  },
  methods: {
    selectfollowers() {
      FollowApi.selectFollowCountById(
        this.loginUserId,
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
        this.loginUserId,
        res => {
          console.log(res);
          this.following = res.data;
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};
</script>
<style scoped>
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
  font-family: "Do Hyeon", sans-serif;
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
  padding-top: 150px;
  padding-bottom: 0px;
  padding: 100px 0px 0px 200px;
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
  /* margin-right: 3rem; */
}

.hjprofile-image img {
  border-radius: 50%;
}

.hjprofile-user-settings,
.hjprofile-stats {
  float: left;
  width: calc(66.666% - 2rem);
}
.hjprofile-stats ul {
  margin-top: 25px;
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
  .hjprofile-image {
    padding-bottom: 100px;
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
</style>
