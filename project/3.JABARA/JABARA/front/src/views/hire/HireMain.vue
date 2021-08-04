<template>
  <div class="demo-app">
    <div class="demo-app-sidebar">
      <div class="demo-app-sidebar-section">
        <h4 style="color:white;">진행/진행 예정인 채용 ({{ notEndEvents.length }}건)</h4>
        <ul>
          <li v-for="event in notEndEvents" :key="event.id">
            <b>{{ event.startStr }}</b>
            <b v-if="event.endStr">- {{ dateAddDel(event.endStr, -1, "d") }}</b>
            <br />
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
      <div class="demo-app-sidebar-section">
        <h4 style="color:white;">종료된 채용 ({{ endEvents.length }}건)</h4>
        <ul>
          <li v-for="event in endEvents" :key="event.id">
            <b>{{ event.startStr }}</b>
            <b v-if="event.endStr">- {{ dateAddDel(event.endStr, -1, "d") }}</b>
            <br />
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div>
    <div class="demo-app-main">
      <FullCalendar class="demo-app-calendar" :options="calendarOptions">
        <template v-slot:eventContent="arg">
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
      <div v-if="show">
        <PopUpInput
          @showfalse="showfalse"
          v-bind:readyCal="readyCal"
          v-bind:selectInfo="selectInfo"
        ></PopUpInput>
      </div>
      <div v-if="showDetail">
        <PopUpDetail
          @showDetailFalse="showDetailFalse"
          v-bind:readyCal="readyCal"
          v-bind:clickInfo="clickInfo"
        ></PopUpDetail>
      </div>
    </div>
  </div>
</template>

<script>
import PopUpInput from "@/components/hire/PopUpInput.vue";
import PopUpDetail from "@/components/hire/PopUpDetail.vue";

import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
// import { INITIAL_EVENTS } from "./event-utils";
import HireAPI from "@/apis/hireAPI";
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
  components: {
    FullCalendar, // make the <FullCalendar> tag available
    PopUpInput,
    PopUpDetail
  },
  // 백 갖춰지면 할것 :
  // 1. 통신 (이미지 통신 캘린더 crud 관심정보/지원회사 추가삭제)
  // 2. 이미지 클릭하면 팝업창으로 띄우는걸로
  // 3. 받아올떄 관심/ 지원했던 회사인지 확인할방법 생각
  // 4. 관리자 유저 등록되면 -> 관리자권한일때만 채용일정 추가 수정 삭제
  data: function() {
    return {
      loginUserId: "",
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
      endEvents: [],
      notEndEvents: [],
      num: 0
    };
  },
  // computed(){

  // },
  created() {
    this.loginUserId = this.$session.get("loginUserId");
    this.today = this.$moment(new Date()).format("YYYYMMDD");
    this.reloadHireInfo();
  },
  // mounted() {
  //   console.log("마운트");
  //   console.log(this.calendarOptions.events);
  // },
  methods: {
    dateAddDel(sDate, nNum, type) {
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
    },
    reloadHireInfo() {
      console.log("reloadHireInfo");
      HireAPI.requestSelectAll(
        res => {
          console.log("hire 조회!");
          let tempData = [];
          res.data.forEach(function(element) {
            console.log(element);
            let aJson = new Object();
            aJson.hireNumber = element.HIRE_NUMBER;
            aJson.companyName = element.COMPANY_NAME;
            aJson.startDate = dateAddDel(
              new Date(element.START_DATE).toISOString().replace(/T.*$/, ""),
              0,
              "d"
            );

            aJson.endDate = dateAddDel(
              new Date(element.END_DATE).toISOString().replace(/T.*$/, ""),
              0,
              "d"
            );
            aJson.companyURL = element.COMPANY_URL;
            aJson.companyImage = element.COMPANY_IMAGE;
            tempData.push(aJson);
          });
          this.hireInfo = tempData;

          console.log(this.hireInfo);
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
      // let tempNum = 0;
      for (let i = 0; i < data.length; i++) {
        let event = {
          id: data[i].hireNumber,
          title: data[i].companyName,
          startStr: data[i].startDate.replace(/-/g, ""),
          endStr: data[i].endDate.replace(/-/g, ""),
          start: data[i].startDate,
          end: data[i].endDate,
          allow: data[i].companyImage,
          groupId: data[i].companyURL,
          // overlap: "wow", // 관심 & 지원한
          backgroundColor: color[this.num++ % 10]
        };
        console.log(typeof data.startDate);
        events.push(event);
      }
      // data.forEach(function(data) {
      //   let event = {
      //     id: data.hireNumber,
      //     title: data.companyName,
      //     startStr: data.startDate.replace(/-/g, ""),
      //     endStr: data.endDate.replace(/-/g, ""),
      //     start: data.startDate,
      //     end: data.endDate,
      //     allow: data.companyImage,
      //     groupId: data.companyURL,
      //     // overlap: "wow", // 관심 & 지원한
      //     backgroundColor: color[tempNum++ % 10]
      //   };
      //   console.log(typeof data.startDate);
      //   events.push(event);
      // });
      return events;
    },
    check() {
      // console.log(this.calendarOptions.eventsSet);
      console.log(this.calendarOptions.events);
      // console.log(this.currentEvents);
    },
    showTrue() {
      console.log("showtrue()실행");
      this.$store.commit("showTrue");
      this.show = this.$store.state.show;
    },
    showfalse(event) {
      // console.log("##############################!!@@#@#@#", event);
      if (event) {
        event.backgroundColor = color[this.num++ % 10];
        this.calendarOptions.events.push(event);
      }
      // this.readyCal = false;
      this.show = false;
      // this.reloadHireInfo();
      console.log("showfalse 호출됨");
    },
    showDetailFalse(event) {
      // this.readyCal = false;
      // console.log("#####check####", typeof event);
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
      this.showDetail = false;
      // this.reloadHireInfo();
      console.log("showDetailFalse 호출됨");
    },
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },
    // !!!!!!!!!! 만들고 바로 axios통신!!!!

    handleDateSelect(selectInfo) {
      if (this.loginUserId == "admin") {
        console.log("드래그 함수 실행");
        this.selectInfo = selectInfo;
        this.showTrue();
      }
    },

    handleEventClick(clickInfo) {
      this.clickInfo = clickInfo;
      this.showDetail = true;
      // if (
      //   confirm(
      //     `Are you sure you want to delete the event '${clickInfo.event.title}'`
      //   )
      // ) {
      //   console.log(clickInfo.event);
      //   // clickInfo.event.url();
      //   clickInfo.event.remove();
      // }
    },

    handleEvents(events) {
      let temp;
      this.endEvents = [];
      this.notEndEvents = [];
      for (let i = 0; i < events.length; i++) {
        if (!this.$moment(events[i].endStr).format("YYYYMMDD")) {
          temp = this.$moment(events[i].startStr).format("YYYYMMDD");
        } else {
          temp = this.$moment(events[i].endStr).format("YYYYMMDD");
        }
        if (this.today >= temp) {
          this.endEvents.push(events[i]);
          // if (i == 0) {
          //   this.endEvents[i].backgroundColor = "red";
          // }
        } else {
          this.notEndEvents.push(events[i]);
        }
      }
      this.currentEvents = events;
    }
  }
};
</script>

<style lang="css" scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
* {
  font-family: "Do Hyeon", sans-serif;
}

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
  /* display: flex; */
  /* min-height: 100%; */
  width: 100vw;
  /* height: 100vh; */
  margin: 0 auto;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 0, 34, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 228, 108, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 113, 113, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  width: 23%;
  float: left;
  height: 100vh;
  /* line-height: 1.5; */
  /* background: #ff8431; */
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 3em 2em;
  margin: 0;
  height: 50%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.demo-app-sidebar-section::-webkit-scrollbar {
  display: none;
}

.demo-app-main {
  margin-left: 10%;
  width: 58%;
  /* margin: 0 auto; */
  /* height: 80%; */
  /* margin-bottom: 30%;  */
  float: left;
  /* flex-grow: 1; */
  padding: 2em 1em;
}

.fc {
  /* the calendar root */
  /* max-width: 1100px; */
  margin: 0 auto;
}
.demo-app {
  margin-top: 73px;
}
/* .radio-group {
  margin-top: 110px;
} */
</style>
