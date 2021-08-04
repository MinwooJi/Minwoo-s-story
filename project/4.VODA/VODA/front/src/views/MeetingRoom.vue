<template>
  <!-- <div id="meetingroom" :class="{sidemargin:sideOpen}">
    <div class="sidebar" :class="{sidewidth:sideOpen}"> -->
  <div class="all-box">
    <div class="meetingroom" :class="{ changeWidth: ismargin }">
      <div class="sidebar" :class="{ sidewidth: isOpen }">
        <div class="btn-zip">
          <input type="radio" id="chat-btn" name="button" checked="checked" />
          <label for="chat-btn" class="btn-item chat-btn" @click="setchat"
            >채팅</label
          >
          <input type="radio" id="parti-btn" name="button" />
          <label
            for="parti-btn"
            class="btn-item parti-btn"
            @click="setparticipant"
            >참가자</label
          >
          <input type="radio" id="record-btn" name="button" />
          <label for="record-btn" class="btn-item record-btn" @click="setrecord"
            >기록</label
          >
        </div>
        <div class="chosed-box" :style="{ height: boxHeight }">
          <div class="chat-box" v-if="this.status === 'chat'">
            <Chat />
          </div>
          <div class="partici-box" v-if="this.status === 'participants'">
            <Participants />
          </div>
          <div class="record-box" v-if="this.status === 'record'">
            <Record />
          </div>
        </div>
      </div>
      <div class="sign-box" :class="{ signVideoWidth: signVideo }">
        <div class="sign-lang-video">수화영상</div>
      </div>
      <div class="captionBox" v-if="caption">
        <p>안녕하세gggggg</p>
      </div>
      <div class="videoBox">
        <div class="video">
          <div class="video-one"></div>
          <div class="video-one"></div>
          <div class="video-one"></div>
          <div class="video-one"></div>
          <div class="video-one"></div>
          <div class="video-one"></div>
          <!-- <div style="padding-bottom: 56.26%; background-color:yellow;"></div> -->
          <!-- <div style="padding-bottom: 56.26%; background-color:orange;"></div> -->
          <!-- <div style="padding-bottom: 56.26%; background-color:purple;"></div> -->
          <!-- <div style="padding-bottom: 56.26%; background-color:blue;"></div> -->
        </div>
      </div>
    </div>
    <div class="plus-box"></div>
    <div class="meeting-bottom">
      <div class="bottom-btn-box">
        <div class="circle-btn">
          <i
            class="fas fa-camera fa-2x"
            @click="colorchangeCamera"
            :style="{ color: camC, background: camB }"
          ></i>
          <p v-if="camera" class="arrow_box">카메라 on</p>
          <p v-if="!camera" class="arrow_box">카메라 off</p>
        </div>
        <div class="onoff-mike circle-btn">
          <i
            class="fas fa-microphone fa-2x"
            @click="colorchangemike"
            :style="{ color: mikeC, background: mikeB }"
          ></i>
          <p v-if="mike" class="arrow_box">마이크 on</p>
          <p v-if="!mike" class="arrow_box">마이크 off</p>
        </div>
        <div class="onoff-caption circle-btn">
          <i
            class="fas fa-closed-captioning fa-2x"
            @click="colorchangeCaption"
            :style="{ color: capC, background: capB }"
          ></i>
          <p v-if="caption" class="arrow_box">자막 on</p>
          <p v-if="!caption" class="arrow_box">자막 off</p>
        </div>
        <div class="onoff-sign circle-btn">
          <i
            @click="signVideoOpen"
            class="fas fa-sign-language fa-2x"
            :style="{ color: viC, background: viB }"
          ></i>
          <p v-if="signVideo" class="arrow_box">수화영상 on</p>
          <p v-if="!signVideo" class="arrow_box">수화영상 off</p>
        </div>
        <div class="sidebar-btn circle-btn">
          <i
            class="far fa-eye fa-2x"
            @click="colorchangeSignRecog"
            :style="{ color: signC, background: signB }"
          ></i>
          <p v-if="signRecog" class="arrow_box">수화인식 on</p>
          <p v-if="!signRecog" class="arrow_box">수화인식 off</p>
        </div>
        <div class="sidebar-btn circle-btn">
          <i
            @click="sideOpen"
            class="fas fa-ellipsis-v fa-2x"
            :style="{ color: asC, background: asB }"
          ></i>
          <p class="arrow_box">채팅/참가자/기록</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Chat from "@/components/Chat.vue";
import Participants from "@/components/Participants.vue";
import Record from "@/components/Record.vue";

export default {
  name: "MeetingRoom",
  data() {
    return {
      isOpen: false,
      ismargin: false,
      status: "chat",

      // color
      camB: "none",
      camC: "white",
      mikeB: "none",
      mikeC: "white",
      capB: "none",
      capC: "white",
      viB: "none",
      viC: "white",
      signB: "none",
      signC: "white",
      asB: "none",
      asC: "white",

      camera: false,
      mike: false,
      caption: false,
      signVideo: false,
      signRecog: false,
      as: false,
      boxHeight: 100 + "%",
    };
  },
  components: {
    Chat,
    Participants,
    Record,
  },
  methods: {
    sideOpen() {
      this.isOpen = !this.isOpen;
      if (this.isOpen === true) {
        this.ismargin = true;
        this.asB = "white";
        this.asC = "#a887ff";
        // console.log('open???')
      } else {
        this.ismargin = false;
        this.asB = "none";
        this.asC = "white";
        // console.log("close???");
      }
      // console.log("isOpen", this.isOpen);
    },
    setchat() {
      this.status = "chat";
      // console.log("status", this.status);
    },
    setparticipant() {
      this.status = "participants";
      // console.log("status", this.status);
    },
    setrecord() {
      this.status = "record";
      // console.log("status", this.status);
    },
    signVideoOpen() {
      this.signVideo = !this.signVideo;
      // console.log("signVideo", this.signVideo);
      if (this.signVideo === true) {
        this.boxHeight = 64 + "%";
        this.viB = "white";
        this.viC = "#a887ff";
      } else {
        this.boxHeight = 100 + "%";
        this.viB = "none";
        this.viC = "white";
      }
    },
    // color
    colorchangeCamera() {
      this.camera = !this.camera;
      if (this.camera === true) {
        this.camB = "white";
        this.camC = "#a887ff";
      } else {
        this.camB = "none";
        this.camC = "white";
      }
      // console.log("camera", this.camera);
    },
    colorchangemike() {
      this.mike = !this.mike;
      if (this.mike === true) {
        this.mikeB = "white";
        this.mikeC = "#a887ff";
      } else {
        this.mikeB = "none";
        this.mikeC = "white";
      }
    },
    colorchangeCaption() {
      this.caption = !this.caption;
      if (this.caption === true) {
        this.capB = "white";
        this.capC = "#a887ff";
      } else {
        this.capB = "none";
        this.capC = "white";
      }
    },
    colorchangeSignRecog() {
      this.signRecog = !this.signRecog;
      if (this.signRecog === true) {
        this.signB = "white";
        this.signC = "#a887ff";
      } else {
        this.signB = "none";
        this.signC = "white";
      }
    },
  },
};
</script>

<style>
.changeWidth {
  margin-right: 300px;
  width: calc(100vw - 300px);
}
.all-box {
  font-family: "Jua", sans-serif;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  position: relative;
}
.meetingroom {
  background: rgba(14, 13, 13, 0.904);
  height: calc(100% - 80px);
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  color: rgb(255, 255, 255);
  font-size: 20px;
  /* overflow: hidden; */
}
.videoBox {
  padding: 50px;
  height: calc(100%-80px);
  position: relative;
  top: 0%;
}

.video {
  display: grid;
  grid-gap: 8px;
  grid-template-columns: repeat(auto-fit, minmax(30%, auto));
  grid-template-rows: repeat(auto-fit, minmax(50%, auto));
  justify-content: center;
  line-height: 100%;
  /* place-items: center; */
  white-space: nowrap;
  /* min-height: 100vh; */
}
.video div {
  box-sizing: border-box;
}
.video-one {
  padding-bottom: 56.26%;
  background-color: white;
}
.captionBox {
  /* margin-bottom: 100px; */
  position: relative;
  top: 0%;
  left: 50%;
  margin: 0 auto;
  transform: translate(-50%, 0%);
  /* background: rgba(107, 83, 136, 0.87); */
  width: 100%;
  height: 30px;
  padding: 5px 20px;
  /* 가운데 정렬 */
  margin: 0 auto 50px auto;
}
.captionBox p {
  background: rgba(107, 83, 136, 0.87);
  color: white;
  word-wrap: break-word;
  word-break: break-all;
  white-space: pre-line;
  padding: 10px 20px;
  /* width: 50%; */
  /* padding: 5px 20px;  */
  text-align: center;
  font-size: 120%;
  line-height: 30px;
}

.sidebar {
  font-family: "Jua", sans-serif;
  height: calc(100% - 80px);
  width: 0;
  position: fixed;
  z-index: 3;
  top: 0;
  right: 0;
  /* opacity: 0.9; */
  overflow: hidden;
}

.sidewidth {
  width: 300px;
  background: rgb(14, 13, 13);
  height: calc(100vh - 80px);
  z-index: 3;
}
.sidemargin {
  width: (100vw - 300px);
}
.sign-box {
  width: 300px;
  height: 200px;
  background: rgb(255, 218, 246);
  z-index: 4;
  position: absolute;
  right: 0;
  bottom: 80px;
  display: none;
}

.signVideoWidth {
  display: block;
}

.btn-zip {
  font-family: "Jua", sans-serif;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  text-align: center;
  justify-content: center;
}

.btn-zip > input[type="radio"] {
  opacity: 0;
  position: fixed;
  width: 0;
}

.btn-zip label {
  display: inline-block;
  background-color: rgb(207, 196, 255);
  padding: 10px;
  font-family: "Jua", sans-serif;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 16px;
  /* border: 2px solid #444; */
  border-radius: 15px 15px 0 0;
}
.btn-zip input[type="radio"]:checked + label {
  background-image: linear-gradient(
    to right top,
    #7fa8f9,
    #82a0fe,
    #8a97ff,
    #958cff,
    #a380ff,
    #a87dff,
    #ad7bff,
    #b278ff,
    #ac80ff,
    #a887ff,
    #a38eff,
    #a094ff
  );
}

.btn-zip label:hover {
  cursor: pointer;
  background-color: rgb(211, 134, 255);
}

.btn-item {
  display: flex;
  align-items: center;
  justify-content: center;
}
.chat-btn {
  animation: move 1s;
}

.chosed-box {
  width: 300px;
  height: 100%;
  background: whitesmoke;
}
.plus-box {
  width: 100%;
  height: 160px;
  background-image: linear-gradient(
    to right top,
    #b7b1fa,
    #b4a6fd,
    #b39bff,
    #b38eff,
    #b581ff,
    #b47eff,
    #b27bff,
    #b178ff,
    #ab80ff,
    #a587ff,
    #a08eff,
    #9c94ff
  );
  z-index: 2;
  /* position:absolute; */
  margin-bottom: 80px;
  left: 0;
}

.meeting-bottom {
  width: 100%;
  height: 120px;
  /* padding:20px; */

  position: fixed;
  bottom: 0;
  left: 0;
  z-index: 4;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

.bottom-btn-box {
  padding-top: 20px;
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.circle-btn {
  position: relative;
  /* display: block; */
  align-items: center;
  width: 100%;
  /* height: 100%; */
  margin: 15px;
}
.arrow_box {
  z-index: 5;
  text-align: center;
  display: none;
  position: absolute;
  width: 120px;
  padding: 8px;
  top: -80%;
  left: -35%;
  -webkit-border-radius: 8px;
  -moz-border-radius: 8px;
  border-radius: 8px;
  background: rgba(169, 94, 255, 0.925);
  color: #fff;
  font-size: 14px;
  opacity: 0.8;
}

.arrow_box:after {
  position: absolute;
  top: 100%;
  left: 50%;
  width: 0;
  height: 0;
  margin-left: -10px;
  border: solid transparent;
  border-color: rgba(51, 51, 51, 0);
  border-top-color: rgba(250, 195, 255, 0.685);
  border-width: 10px;
  pointer-events: none;
  content: " ";
}

/* .circle-btn:hover > p.arrow_box {
  display: block;
} */
.circle-btn:hover > p.arrow_box {
  display: block;
}
.circle-btn:hover {
  cursor: pointer;
}

.circle-btn i {
  display: block;
  /* color:white; */
  /* padding: 20px; */
  width: 62px;
  height: 62px;
  border-radius: 50%;
  border: 2px solid rgba(255, 255, 255, 0.562);
  justify-content: center;
  align-items: center;
  display: flex;
}

.hello {
  display: grid;
  grid-template-columns: auto auto auto;
}
</style>