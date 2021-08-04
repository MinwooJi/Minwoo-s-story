<template>
  <div id="vegaChart">
    <div id="chart-div" style="width: 700px; height: 250px;"></div>
  </div>
</template>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
import MyProfileGraphApi from "@/apis/myProfileGraphAlgoAPI";
export default {
  name: "VegaChart",
  props: {
    sovledAlgoCount: Object
  },
  data() {
    return {
      // sovledAlgoCount: {}
    };
  },
  mounted() {
    console.log("vega chart create");
    google.charts.load("49", { packages: ["vegachart"] }).then(this.drawChart);
  },
  methods: {
    drawChart() {
      console.log("VegaChart 실행됨");
      let solvedCategoryData = [];
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
      const dataTable = new google.visualization.DataTable();
      dataTable.addColumn({ type: "string", id: "category" });
      dataTable.addColumn({ type: "number", id: "amount" });
      dataTable.addRows(solvedCategoryData);

      const options = {
        color: "#e2c5b4",
        vega: {
          $schema: "https://vega.github.io/schema/vega/v4.json",
          width: 650,
          height: 380,
          //   padding: 5,

          data: [{ name: "table", source: "datatable" }],

          signals: [
            {
              name: "tooltip",
              value: {},
              on: [
                { events: "rect:mouseover", update: "datum" },
                { events: "rect:mouseout", update: "{}" }
              ]
            }
          ],

          scales: [
            {
              name: "xscale",
              type: "band",
              domain: { data: "table", field: "category" },
              range: "width",
              padding: 0.05,
              round: true
            },
            {
              name: "yscale",
              domain: { data: "table", field: "amount" },
              nice: true,
              range: "height"
            }
          ],

          axes: [
            { orient: "bottom", scale: "xscale" },
            { orient: "left", scale: "yscale" }
          ],

          marks: [
            {
              type: "rect",
              from: { data: "table" },
              encode: {
                enter: {
                  x: { scale: "xscale", field: "category" },
                  width: { scale: "xscale", band: 1 },
                  y: { scale: "yscale", field: "amount" },
                  y2: { scale: "yscale", value: 0 }
                },
                update: {
                  fill: { value: "steelblue" }
                },
                hover: {
                  fill: { value: "red" }
                }
              }
            },
            {
              type: "text",
              encode: {
                enter: {
                  align: { value: "center" },
                  baseline: { value: "bottom" },
                  fill: { value: "#333" }
                },
                update: {
                  x: { scale: "xscale", signal: "tooltip.category", band: 0.5 },
                  y: { scale: "yscale", signal: "tooltip.amount", offset: -2 },
                  text: { signal: "tooltip.amount" },
                  fillOpacity: [
                    { test: "datum === tooltip", value: 0 },
                    { value: 1 }
                  ]
                }
              }
            }
          ]
        }
      };

      const chart = new google.visualization.VegaChart(
        document.getElementById("chart-div")
      );
      chart.draw(dataTable, options);
    }
  }
};
</script>
