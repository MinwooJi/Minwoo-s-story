<template>
  <div id="info-wrap">
    <div class="my-info">
      <!-- <div class="my-info-wrap">
        <div class="my-img">
          <img
            class="profile-img"
            src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=50&h=50&fit=crop"
            alt="내 프로필"
          />
        </div>
        <div class="info-list-wrap">
          <ul class="info-list">
            <li>ID: yeafla530</li>
            <li>Nickname: 예리미</li>
            <li style="font-size: 100%;">
              Git Url:
              <br />https://fefefefeasdfsdafasf.com
            </li>
          </ul>
        </div>
      </div> -->
      <div class="today-schedule">
        <h3>Upcomming ({{upcomingSchedule.length }})</h3>
        <TodaySchedule :upcomingSchedule="upcomingSchedule" />
        <!-- <h3>Upcomming schedule ({{ upcomingSchedule.length }})</h3>
        <div class="pop-scroll">
          <ul>
            <li v-for="schedule in upcomingSchedule" :key="schedule.id">
              <b>{{ schedule.startStr }}</b>
              <b v-if="schedule.endStr">- {{ dateAddDel(schedule.endStr, -1, "d") }}</b>
              <br />
              <i>{{ schedule.title }}</i>
            </li>
          </ul>
        </div>-->
      </div>
    </div>
    <div class="calendar">
      <div class="mini-calendar">
        <!-- <div class="itscanledar"> -->
        <MyCalendar v-bind:upcomingSchedule="upcomingSchedule" @pushSchedule="pushSchedule"></MyCalendar>
        <!-- </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import MyCalendar from "@/components/profile/calendar/MyCalendar.vue";
import TodaySchedule from "@/components/profile/schedule/TodaySchedule.vue";
export default {
  name: "Myprofile",
  components: {
    MyCalendar,
    TodaySchedule
  },
  data: function() {
    return {
      upcomingSchedule: []
    };
  },
  // mounted() {
  //   // console.log("$$$ MyInfo: ", this.upcomingSchedule);
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
    pushSchedule(schedules) {
      this.upcomingSchedule = [];
      // console.log("#######schedules:", schedules);
      for (let i = 0; i < schedules.length; i++) {
        this.upcomingSchedule.push(schedules[i]);
      }
      this.upcomingSchedule.sort(function(a, b) {
        // 오름차순
        return a.endStr.replace(/-/g, "") - b.endStr.replace(/-/g, "");
      });
      console.log("sort?", this.upcomingSchedule);
    }
  }
};
</script>

<style scoped>
/* 달력 */
.pop-scroll {
  width: 100%;
  height: 90%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.c-title {
  margin: 2%;
}
.c-title .mc-h1 {
  margin-left: 4%;
}
.mini-calendar {
  /* overflow: auto; */
  padding-top: 2rem;
  /* margin: 0 auto; */
  height: 100%;
  width: 100%;
  /* background: #ed7568; */
}

.today-schedule {
  margin: 6% 0;
  width: 100%;
  height: 90%;
  /* background: #ed7568; */
}

/* 내 인적사항  */
#info-wrap {
  top: 0;
  width: 95%;
  height: 700px;
  margin: 0 auto;
  /* margin-top: 150px; */
}

.my-info {
  float: left;
  width: 45%;
  height: inherit;
  /* background: green; */
}

.my-info-wrap {
  height: 50%;
  width: 100%;
}
.calendar {
  float: left;
  width: 55%;
  height: 540px;
  /* background: orangered; */
}
.my-img {
  float: left;
  height: 250px;
  width: 250px;
  padding: 5%;
  margin-left: 5%;
  /* profile-img의 기준 */
  position: relative;
}
.profile-img {
  /* 기준을 중심으로 이동 */
  position: absolute;
  /* float: left; */
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  width: 70%;
  height: 70%;
}
.info-list {
  position: absolute;
  top: 25%;
  left: 0;
  transform: translate(0%, 0%);
  font-size: 120%;
  box-sizing: border-box;
  /* margin: 7% 8%; */
  margin: 0 auto;
}
.info-list-wrap {
  position: relative;
  float: left;
  height: 100%;
  width: 50%;
}
.info-list li {
  /* 글자가 넘쳤을때 다음줄로 */
  word-break: break-all;
  text-align: left;
  font-weight: 700;
  margin-bottom: 4%;
}
</style>
