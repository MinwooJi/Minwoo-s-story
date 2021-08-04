<template>
  <div class="demo-app">
    <div class="demo-app-main">
      <FullCalendar class="demo-app-calendar" :options="calendarOptions">
        <template v-slot:eventContent="arg">
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
      <div v-if="show">
        <MyCalInput
          @showfalse="showfalse"
          v-bind:readyCal="readyCal"
          v-bind:selectInfo="selectInfo"
        ></MyCalInput>
      </div>
      <div v-if="showDetail">
        <MyCalDetail
          @showDetailFalse="showDetailFalse"
          v-bind:readyCal="readyCal"
          v-bind:clickInfo="clickInfo"
        ></MyCalDetail>
      </div>
    </div>
  </div>
</template>

<script>
import MyCalInput from "@/components/profile/calendar/MyCalInput.vue";
import MyCalDetail from "@/components/profile/calendar/MyCalDetail.vue";

import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import MyCalAPI from "@/apis/myProfileCalAPI";
let color = [
  "rgba(255, 0, 34, 0.4)",
  "rgba(252, 133, 133, 0.4)",
  "rgba(255, 151, 191, 0.507)",
  "rgba(255, 151, 212, 0.507)",
  "rgba(255, 134, 118, 0.507)",
  "rgba(255, 118, 255, 0.342)",
  "rgba(255, 78, 78, 0.342)",
  "rgba(255, 118, 100, 0.651)",
  "rgba(255, 122, 144, 0.651)",
  "rgba(255, 122, 173, 0.651)"
];
function dateAddDel(sDate, nNum, type) {
  var yy = parseInt(sDate.substr(0, 4), 10);
  var mm = parseInt(sDate.substr(5, 2), 10);
  var dd = parseInt(sDate.substr(8), 10);
  let d = new Date();
  if (type == "d") {
    d = new Date(yy, mm - 1, dd + nNum);
  } else if (type == "m") {
    d = new Date(yy, mm - 1, dd + nNum * 31);
  } else if (type == "y") {
    d = new Date(yy + nNum, mm - 1, dd);
  }

  yy = d.getFullYear();
  mm = d.getMonth() + 1;
  mm = mm < 10 ? "0" + mm : mm;
  dd = d.getDate();
  dd = dd < 10 ? "0" + dd : dd;

  return "" + yy + "-" + mm + "-" + dd;
}
export default {
  name: "mycalendar",
  components: {
    FullCalendar, // make the <FullCalendar> tag available
    MyCalInput,
    MyCalDetail
  },
  data: function() {
    return {
      readyCal: false,
      showDetail: false,
      clickInfo: "",
      hireInfo: "",
      selectInfo: "",
      show: false,
      today: "",
      checked: "",
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay"
        },
        initialView: "dayGridMonth",
        events: "", // alternatively, use the `events` setting to fetch from a feed //여기서 통신해보자.....
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      num: 0
    };
  },
  // computed(){

  // },
  created() {
    this.today = this.$moment(new Date()).format("YYYYMMDD");
    this.reloadHireInfo();
  },
  // mounted() {
  //   console.log("마운트");
  //   console.log(this.calendarOptions.events);
  // },
  methods: {
    getLater7day() {
      let newDt = new Date();
      newDt.setDate(newDt.getDate() + 7);
      return newDt;
    },
    getDt9(s, i) {
      let newDt = new Date(s);
      newDt.setDate(newDt.getDate() - i);
      return newDt.toISOString();
    },
    reloadHireInfo() {
      // console.log("reloadHireInfo");
      MyCalAPI.requestSelectMyCalAll(
        this.$session.get("loginUserId"),
        res => {
          console.log("MYHIRE 조회!", res);
          let tempData = [];
          res.data.forEach(function(element) {
            // console.log(element);
            let aJson = new Object();
            aJson.calNumber = element.CAL_NUMBER;
            aJson.hireNumber = element.HIRE_NUMBER;
            aJson.companyName = element.CAL_TITLE;
            aJson.startDate = dateAddDel(
              new Date(element.START_DATE).toISOString().replace(/T.*$/, ""),
              0,
              "d"
            );
            aJson.contents = element.CAL_CONTENT;
            aJson.endDate = dateAddDel(
              new Date(element.END_DATE).toISOString().replace(/T.*$/, ""),
              0,
              "d"
            );

            tempData.push(aJson);
          });
          this.hireInfo = tempData;

          // console.log(this.hireInfo);
          this.calendarOptions.events = this.makeEvents(this.hireInfo);
        },
        error => {
          console.log(error);
        }
      );
      this.readyCal = true;
    },
    makeEvents(data) {
      let events = [];
      // let num = 0;
      console.log("MAKEEVENT!!", data);
      for (let i = 0; i < data.length; i++) {
        let event = {
          id: data[i].calNumber,
          title: data[i].companyName,
          startStr: data[i].startDate.replace(/-/g, ""),
          endStr: data[i].endDate.replace(/-/g, ""),
          start: data[i].startDate,
          end: data[i].endDate,
          allow: data[i].hireNumber,
          groupId: data[i].contents,
          // overlap: "wow", // 관심 & 지원한
          backgroundColor: color[this.num++ % 10]
        };
        // console.log(typeof data.startDate);
        // console.log("!!maked", event);
        events.push(event);
      }
      return events;
    },
    showTrue() {
      console.log("showtrue()실행");
      this.$store.commit("showTrue");
      this.show = this.$store.state.show;
    },
    showfalse(event) {
      // this.readyCal = false;
      // this.show = show;
      // this.reloadHireInfo();
      if (event) {
        event.backgroundColor = color[this.num++ % 10];
        this.calendarOptions.events.push(event);
      }
      // console.log(this.calendarOptions.events);
      console.log("showfalse 호출됨", this.calendarOptions.events);
      this.show = false;
    },
    showDetailFalse(event) {
      console.log("수정/삭제 데이터: ", event);
      if (event) {
        if (typeof event == "string") {
          console.log("delete");
          for (let i = 0; i < this.calendarOptions.events.length; i++) {
            if (this.calendarOptions.events[i].id == event) {
              this.calendarOptions.events.splice(i, 1);
            }
          }
        } else {
          event.backgroundColor = color[this.num++ % 10];
          for (let i = 0; i < this.calendarOptions.events.length; i++) {
            if (this.calendarOptions.events[i].id == event.id) {
              this.calendarOptions.events.splice(i, 1, event);
            }
          }
        }
      }
      // this.readyCal = false;
      this.showDetail = false;
      // this.reloadHireInfo();
      console.log("showDetailFalse 호출됨");
    },
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },
    // !!!!!!!!!! 만들고 바로 axios통신!!!!

    handleDateSelect(selectInfo) {
      console.log("드래그 함수 실행");
      this.selectInfo = selectInfo;
      this.showTrue();
    },

    handleEventClick(clickInfo) {
      this.clickInfo = clickInfo;
      this.showDetail = true;
    },

    handleEvents(events) {
      console.log("EVENT_ALL", events);
      let temp;
      let tempEvts = [];
      for (let i = 0; i < events.length; i++) {
        if (!this.$moment(events[i].endStr).format("YYYYMMDD")) {
          temp = this.$moment(events[i].startStr).format("YYYYMMDD");
        } else {
          temp = this.$moment(events[i].endStr).format("YYYYMMDD");
        }
        // let weekLater = this.getDt9(this.today, -7);
        let weekLater = this.$moment(this.getLater7day()).format("YYYYMMDD");
        console.log("WEEKLATER:", weekLater);
        // console.log("TEMP:", temp);
        console.log("TODAY:", this.today);
        console.log(weekLater >= temp);
        console.log(this.today < temp);
        if (
          weekLater >= this.$moment(events[i].startStr).format("YYYYMMDD") &&
          this.today < this.$moment(events[i].endStr).format("YYYYMMDD")
        ) {
          tempEvts.push(events[i]);
        }
        console.log("tempEVENT:", tempEvts);
      }
      this.currentEvents = tempEvts;
      // console.log("#######tempEvts:", tempEvts);

      console.log("UPCOMMING:", tempEvts);
      this.$emit("pushSchedule", tempEvts);
    }
  }
};
</script>
<style lang="css" scoped>
h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b {
  /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  z-index: 1;
  display: inline-flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
  width: 100%;
}

.demo-app-main {
  /* flex-grow: 1; */
  width: 100%;
  height: 100%;
  padding: 2em;
  overflow-x: hidden;
  overflow-y: auto;
}

.fc {
  /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
</style>
