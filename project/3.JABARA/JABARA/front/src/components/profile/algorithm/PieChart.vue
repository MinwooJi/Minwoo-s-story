<template>
  <div id="pieChart">
    <div id="piechart_3d" style="width: 600px; height: 400px;"></div>
  </div>
</template>
<script
  type="text/javascript"
  src="https://www.gstatic.com/charts/loader.js"
></script>
<script>
import MyProfileGraphApi from "@/apis/myProfileGraphAlgoAPI";
export default {
  name: "PieChart",
  props: {
    sovledAlgoCount: Object
  },
  data() {
    return {
      // show: false,
      // sovledAlgoCount: {},
    };
  },
  mounted() {
    console.log("pie chart create");
    google.charts.load("current", { packages: ["corechart"] });
    google.charts.setOnLoadCallback(this.drawChart);
  },
  methods: {
    drawChart() {
      console.log("drawChart 실행됨");
      let solvedCategoryData = [["Problem", "Number of solved problems"]];
      solvedCategoryData.push(
        ["이분탐색", this.sovledAlgoCount.binary],
        ["브루투포스", this.sovledAlgoCount.brouteForce],
        ["DFS/BFS", this.sovledAlgoCount.dfsBfs],
        ["DP", this.sovledAlgoCount.dynamic],
        ["기타", this.sovledAlgoCount.etc],
        ["그래프 이론", this.sovledAlgoCount.graph],
        ["탐욕법", this.sovledAlgoCount.greedy],
        ["해시", this.sovledAlgoCount.hash],
        ["힙(Heap)", this.sovledAlgoCount.heap],
        ["시뮬레이션", this.sovledAlgoCount.simulation],
        ["정렬", this.sovledAlgoCount.sort],
        ["스택/큐", this.sovledAlgoCount.stackQueue]
      );
      console.log(solvedCategoryData);
      let data = google.visualization.arrayToDataTable(solvedCategoryData);

      let options = {
        title: "",
        is3D: true,
        height: 400,
        width: 650,
        // 하늘+보라
        // colors: [
        //   "#f0e2ee",
        //   "#e1dcec",
        //   "#dbc7d9",
        //   "#d1c3d8",
        //   "#bcbbd6",
        //   "#bdcde9",
        //   "#a6b3d3",
        //   "#b2c8e7",
        //   "#bdcde9",
        //   "#dddaec",
        //   "#c7d1ea",
        //   "#c7c8e4"
        // ]
        //하늘+주황
        // colors: [
        //   "#99dfff",
        //   "#bee1f1",
        //   "#6f9eb3",
        //   "#386477",
        //   "#36b7bd",
        //   "#a0d49c",
        //   "#36bd8c",
        //   "#aee080",
        //   "#f1f3a4",
        //   "#ffd694",
        //   "#ffc569",
        //   "#ffe7cc"
        // ]
        // 분홍+노랑
        colors: [
          "#fa8184",
          "#ff9093",
          "#f99a96",
          "#ffa1a4",
          "#f7a99a",
          "#f9b299",
          "#ffbabc",
          "#ffc8b4",
          "#ffbb97",
          "#f9d297",
          "#f8d7a7",
          "#f8e5a7"
        ]
      };

      let chart = new google.visualization.PieChart(
        document.getElementById("piechart_3d")
      );
      chart.draw(data, options);
    }
  }
};
</script>
<style scoped>
.license-date {
  /* color: #e68c6f;
  color: #e2b1a0;
  color: #e4724c;

  color: #e6b26f;
  color: #e6caa7;
  color: #dd9c47;

  color: #e4d2a1;
  color: #e6c66f;
  color: #e4b942;

  color: #daa9e9;
  color: #e188fc;
  color: #c768e4; */

  color: #e2c5b4;
  color: #e4b295;
  color: #e49f78;
  color: #e08754;
  color: #df7437;
  color: #db6520;
  color: #fd792d;
  color: #ff8f4f;
  color: #ffa36e;
  color: #fdb78f;
  color: #ffc7a7;
  color: #ffd6be;

  color: #6fe6af;
  color: #6f99e6;
  color: #ca6fe6;
  color: #e66fc8;
  color: #e66f75;
  color: #e9b19f;
  color: #e9db9f;
  color: #d3e99f;

  color: #e68b6d;
  color: #e6b13e;
  color: #df9f15;
  color: #dbdd42;
  color: #ccce7c;

  color: #e48ddf;
  color: #da49df;
  color: #6adb79;
  color: #2fda45;
  color: #aee0b5;
  color: #6d6fd8;

  color: #aee0e0;
  color: #aee0e0;

  color: #aee0e0;
  color: #e6b13e;

  color: #e6693e;
  color: #e6693e;
  color: #e6693e;
  color: #e6693e;
  color: #e6693e;
  color: #e6693e;
}
</style>
