<template>
  <div id="CalenderChart">
    <div id="calendar_basic" style="width: 1000px; height: 250px;"></div>
  </div>
</template>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
import PostApi from "@/apis/postAPI";
export default {
  name: "AlgoCalendar",
  data() {
    return {
      show: false,
      algoCalendarCount: "",
      algolen: ""
    };
  },
  // created: function() {
  mounted() {
    PostApi.searchAlgoCalendarChart(
      this.$session.get("loginUserId"),
      res => {
        this.algoCalendarCount = res.data;
        console.log(this.algoCalendarCount);
        this.algolen = this.algoCalendarCount.length;
        console.log(this.algoCalendarCount.length);
      },
      error => {
        console.log(error);
      }
    );
    setTimeout(() => {
      google.charts.load("current", { packages: ["calendar"] });
      google.charts.setOnLoadCallback(this.drawChart);
    }, 400);
  },
  methods: {
    //       COUNT_SOLVED_ALGO: 6
    // POST_DATE: "2020-08-17"
    drawChart() {
      var dataTable = new google.visualization.DataTable();
      dataTable.addColumn({ type: "date", id: "Date" });
      dataTable.addColumn({ type: "number", id: "Won/Loss" });

      for (let i = 0; i < this.algoCalendarCount.length; i++) {
        let algoDate = this.algoCalendarCount[i].POST_DATE;
        let algocnt = this.algoCalendarCount[i].COUNT_SOLVED_ALGO;
        dataTable.addRows([[new Date(algoDate), algocnt]]);
      }
      //   console.log(solvedCategoryData);
      let chart = new google.visualization.Calendar(
        document.getElementById("calendar_basic")
      );

      let options = {
        title: "1 DAY 1 ALGO POSTING",
        // title: "1일 1 ALGO 포스팅",
        height: 200,
        calendar: {
          cellSize: 12,
          yearLabel: {
            fontName: "Times-Roman",
            fontSize: 32,
            color: "#feca9f",
            bold: true,
            italic: true
          },
          dayOfWeekLabel: {
            fontName: "Times-Roman",
            fontSize: 12,
            color: "#2104607d",
            bold: true,
            italic: true
          }
        },
        colorAxis: { colors: ["#f1c7b9", "#d84514"] }
      };

      chart.draw(dataTable, options);
    }
  }
};
</script>
<style scoped>
</style>
