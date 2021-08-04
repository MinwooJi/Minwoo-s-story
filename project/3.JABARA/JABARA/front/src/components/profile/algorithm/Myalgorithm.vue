<template>
  <div id="my-algorithm">
    <div id="algorithm-box">
      <div v-if="totalCnt <= 0" class="non-submit">
        <h1>알고리즘 게시물을 올리지 않았습니다</h1>
      </div>
      <div v-else class="submit">
          <div class="algo-piechart">
            <PieChart :sovledAlgoCount="sovledAlgoCount" v-if="sovledAlgoCount.binary != -1"></PieChart>
            <!-- <VegaChart :sovledAlgoCount="sovledAlgoCount" v-if="sovledAlgoCount.binary != -1"></VegaChart> -->
            <div class="problem-list">
            <ProblemList />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PieChart from "@/components/profile/algorithm/PieChart.vue";
// import VegaChart from "@/components/profile/algorithm/VegaChart.vue";
import ProblemList from "@/components/profile/algorithm/ProblemList.vue";
import MyProfileGraphApi from "@/apis/myProfileGraphAlgoAPI";
export default {
  name: "Myalgorithm",
  components: {
    PieChart,
    ProblemList,
    // VegaChart
  },
  data() {
    return {
      show: true,
      totalCnt: 0,
      sovledAlgoCount: {
        binary: -1
      }
    };
  },
  created() {
    console.log("algo data 준비");
    // google.charts.load("49", { packages: ["vegachart"] }).then(this.drawChart);
    if (this.$session.get("loginUserId")) {
      MyProfileGraphApi.getSovedCategory(
        this.$session.get("loginUserId"),
        res => {
          console.log("알고메인 데이터 준비끝");
          this.sovledAlgoCount = res.data;
          this.totalCnt =
            res.data.binary +
            res.data.brouteForce +
            res.data.dfsBfs +
            res.data.dynamic +
            res.data.etc +
            res.data.graph +
            res.data.greedy +
            res.data.hash +
            res.data.heap +
            res.data.simulation +
            res.data.sort +
            res.data.stackQueue;
          console.log(this.totalCnt);
          console.log("###########algoCREATE", this.sovledAlgoCount);
        },
        error => {
          console.log(error);
        }
      );
    }
  },
  methods: {}
};
</script>

<style scoped>
#my-algorithm {
  margin: 0 auto;
  width: 100%;
  height: 700px;
  /* background: blue; */
}
#algorithm-box {
  margin: 10px;
  padding: 2% 0;
  /* background: #fd93da; */
  height: inherit;
}
.algo-piechart {
  height: 65%;
  display: inline-flex;
  /* background: teal; */
}
.problem-list {
  height: 40%;
}
.submit{
  width:100%;
  margin:0;
}
.non-submit {
  text-align: center;
  clear: both;
  height: 90%;
}
.non-submit h1 {
  line-height: 578px;
}
</style>
