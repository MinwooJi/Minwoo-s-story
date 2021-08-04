<template>
  <div class="rank-list-wrap">
    <div class="my-rank">
      <h6>{{ addfollowplz }}</h6>
    </div>
    <div v-if="!this.myRanking "><h6>&#128588; 나의 algo 풀이를 올려보세요</h6></div>
    <div v-else class="my-rank">
      <h6>&#128588;나의 랭킹 {{ myRanking }}위</h6>
    </div>
    <div class="today-rank">
      <h4>Today Ranking &#127942;</h4>
      <ul class="tdrank-list rank-ul">
        <li v-for="(rank, index) in todayRankList" :key="index">
          <p v-if="rank.RANK == 1" style="font-size:160%;">&#129351; {{ rank.USER_ID }}</p>
          <p v-if="rank.RANK == 2" style="font-size:150%;">&#129352; {{ rank.USER_ID }}</p>
          <p v-if="rank.RANK == 3" style="font-size:140%;">&#129353; {{ rank.USER_ID }}</p>
          <p v-if="rank.RANK == 4" style="font-size:120%; margin-left: 2px;">4th {{ rank.USER_ID }}</p>
          <p v-if="rank.RANK == 5" style="font-size:120%; margin-left: 2px;">5th {{ rank.USER_ID }}</p>
        </li>
      </ul>
    </div>
    <div class="montly-rank">
      <h4>Total Ranking &#127942;</h4>
      <ul class="mtrank-list rank-ul">
        <li v-for="(ranktotal, index) in totalRankList" :key="index">
          <p v-if="ranktotal.RANK == 1" style="font-size:160%;">&#129351; {{ ranktotal.USER_ID }}</p>
          <p v-if="ranktotal.RANK == 2" style="font-size:150%;">&#129352; {{ ranktotal.USER_ID }}</p>
          <p v-if="ranktotal.RANK == 3" style="font-size:140%;">&#129353; {{ ranktotal.USER_ID }}</p>
          <p
            v-if="ranktotal.RANK == 4"
            style="font-size:120%; margin-left: 2px;"
          >4th {{ ranktotal.USER_ID }}</p>
          <p
            v-if="ranktotal.RANK == 5"
            style="font-size:120%; margin-left: 2px;"
          >5th {{ ranktotal.USER_ID }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import RankingApi from "../../apis/rankingAPI";
import FollowApi from "../../apis/followAPI";
export default {
  name: "Ranklist",
  data() {
    return {
      loginUserId: "",
      myRanking: "",
      todayRankList: [],
      todayRanklen: "",
      totalRankList: [],
      totalRanklen: "",
      following: "",
      addfollowplz: ""
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.followingfnc();
    RankingApi.rankingToday(
      res => {
        let todaytempArr = [];
        this.todayRanklen = res.data.length;
        for (let i = 0; i < res.data.length; i++) {
          todaytempArr.push(res.data[i]);
        }
        this.todayRankList = todaytempArr;
      },
      error => {
        console.log(error);
      }
    );
    RankingApi.rankingTotal(
      res => {
        let totaltempArr = [];
        this.totalRanklen = res.data.length;
        for (let i = 0; i < res.data.length; i++) {
          totaltempArr.push(res.data[i]);
        }
        this.totalRankList = totaltempArr;
      },
      error => {
        console.log(error);
      }
    );
    RankingApi.myRankingTotal(
      this.loginUserId,
      res => {
        this.myRanking = res.data;

        console.log("!!!!!!!!myranking" + res.data);
      },
      error => {
        console.log("!!!!myranking" + error);
      }
    );
  },
  methods: {
    followingfnc() {
      FollowApi.selectFollowingCountById(
        this.loginUserId,
        res => {
          console.log(res);
          this.following = res.data;
          if (this.following <= 5) {
            this.addfollowplz = "🧐follow 할 사람을 검색해 주세요";
          } else {
            this.addfollowplz = "Algo posting 으로 랭킹 up 🔼";
          }
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
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

p {
  margin-bottom: 0px;
  padding-left: 10px;
  font-family: "Do Hyeon", sans-serif;

}
h6 {
  margin-bottom: 20px;
  padding-left: 10px;
  font-family: "Do Hyeon", sans-serif;
  width: 250px;
}
.rank-list-wrap {
  /* float: right;
  position: fixed;
  display: block;
  left: calc(50% + 300px);
  top: 180px;
  width: 20%; */
  width: 100%;
}

.rank-list-wrap h4 {
  font-size: 1em;
  padding-bottom: 5px;
  border-bottom: 1px solid #000;
  margin-bottom: 10px;
  font-weight: 600;
  width: 200px;
  font-family: "Do Hyeon", sans-serif;

}

.rank-list-wrap ul.tdrank-list {
  width: 100%;
  float: left;
  margin-bottom: 20px;
  font-family: "Do Hyeon", sans-serif;

}

.rank-list-wrap ul.rank-ul li {
  margin: 5px 0px;
  font-size: 12px;
}
</style>