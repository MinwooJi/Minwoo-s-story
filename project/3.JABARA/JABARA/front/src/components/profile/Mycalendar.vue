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
        <PopUpInput @showfalse="showfalse" v-bind:selectInfo="selectInfo"></PopUpInput>
      </div>
      <div v-if="showDetail">
        <PopUpDetail @showDetailFalse="showDetailFalse" v-bind:clickInfo="clickInfo"></PopUpDetail>
      </div>
    </div>
    <!-- <div class="demo-app-sidebar">
      <div class="demo-app-sidebar-section pre-scrollable">
        <h3>진행/진행 예정인 채용 ({{ notEndEvents.length }}건)</h3>
        <ul>
          <li v-for="event in notEndEvents" :key="event.id">
            <b>{{ event.startStr }} - {{event.endStr}}</b>
            <br />
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
      <div class="demo-app-sidebar-section pre-scrollable">
        <h3>종료된 채용 ({{ endEvents.length }}건)</h3>
        <ul>
          <li v-for="event in endEvents" :key="event.id">
            <b>{{ event.startStr }} - {{event.endStr}}</b>
            <br />
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div>-->
  </div>
</template>

<script>
import PopUpInput from "@/components/hire/PopUpInput.vue";
import PopUpDetail from "@/components/hire/PopUpDetail.vue";

import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { INITIAL_EVENTS, dataFromServe } from "@/views/hire/event-utils";
// let colors = ["red"];
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
  props: ["upcomingSchedule"],
  data: function() {
    return {
      showDetail: false,
      clickInfo: "",
      hireInfo: dataFromServe,
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
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed //여기서 통신해보자.....
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
      currentEvents: []
    };
  },
  // computed(){

  // },
  created() {
    this.today = this.$moment(new Date()).format("YYYYMMDD");
    // console.log("오늘의 날짜는");
    // console.log(this.today * 1);
    let tempStartStr;
    let tempEndStr;
    let events = INITIAL_EVENTS;
    // let upcoming = [];
    for (let i = 0; i < events.length; i++) {
      if (!this.$moment(events[i].end).format("YYYYMMDD")) {
        tempEndStr = this.$moment(events[i].start).format("YYYYMMDD") * 1;
      } else {
        tempEndStr = this.$moment(events[i].end).format("YYYYMMDD") * 1 - 1;
      }
      tempStartStr = this.$moment(events[i].start).format("YYYYMMDD") * 1;
      // console.log(i);
      // console.dir(events[i]);
      // console.log(tempStartStr);
      // console.log(tempEndStr);
      if (this.today * 1 >= tempStartStr && this.today * 1 <= tempEndStr) {
        this.$emit("pushSchedule", events[i]);
      }
    }
    // console.log("일정확인");
    // console.log(upcoming);
  },
  methods: {
    showTrue() {
      console.log("showtrue()실행");
      this.$store.commit("showTrue");
      this.show = this.$store.state.show;
    },
    showfalse(show) {
      this.show = show;
      console.log("showfalse 호출됨");
    },
    showDetailFalse(showDetail) {
      this.showDetail = showDetail;
      console.log("showDetailFalse 호출됨");
    },
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },
    // !!!!!!!!!! 만들고 바로 axios통신!!!!

    handleDateSelect(selectInfo) {
      console.log("드래그 함수 실행");
      this.selectInfo = selectInfo;
      console.log(this.selectInfo);
      console.log(this.selectInfo.startStr.replace(/-/g, ""));
      this.showTrue();
    },

    handleEventClick(clickInfo) {
      this.clickInfo = clickInfo;
      this.showDetail = true;
    },

    handleEvents(events) {
      this.currentEvents = events;
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
.demo-app-sidebar {
  padding-top: 1%;
  width: auto;
  line-height: 1.5;
  /* background: #ffdea2; */
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  /* padding: 2em; */
  width: 100%;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  overflow-x: hidden;
  overflow-y: auto;
  height: 45%;
}
.demo-app-sidebar-section::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.fc {
  /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
</style>
