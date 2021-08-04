<template>
  <div class="meeting-main">
    <div class="main-box">
      <div class="meeting-button">
        <a href="https://j3d206.p.ssafy.io:8001/voda/create/"
          ><button>미팅룸 생성</button></a
        >
        <a href="https://j3d206.p.ssafy.io:8001/voda/join/"
          ><button>미팅룸 참여</button></a
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MeetingMain",
  title: "VODA | Choose",
};
</script>

<style scoped>
.meeting-main {
  background-image: linear-gradient(
    to right top,
    #ffb9f0,
    #fcb8f2,
    #f9b6f4,
    #f5b5f7,
    #f1b4f9,
    #f0b2f9,
    #efb0fa,
    #eeaefa,
    #f1aaf9,
    #f3a6f7,
    #f6a2f5,
    #f99ef3
  );
  width: 100vw;
  height: 100vh;
  position: fixed;
  margin: 0;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1;
  font-family: "Viga", sans-serif;
  color: rgb(255, 255, 255);
  font-size: 20px;
}
.main-box {
  width: 100vh;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.meeting-button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
button {
  font-weight: bolder;
  width: 100%;
  margin-bottom: 20px;
  padding: 50px 200px;
  border-radius: 5px;
  /* height: 40px; */
  transition: all ease 0.5s;
  border: 1px solid rgba(255, 255, 255, 0.932);
  background-color: rgba(250, 176, 250, 0.233);
  font-family: "DoHeyeon", sans-serif;
  color: white;
  font-size: 30px;
}
button:hover {
  cursor: pointer;
  transform: scale(1.1);
  font-weight: bolder;
  background-color: #cd9af1b7;
  color: rgba(83, 35, 95, 0.76);
  box-shadow: 0 0 20px rgba(225, 65, 247, 0.616);
}
button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(167, 47, 236, 0.616);
  box-shadow: 0 0 20px rgba(225, 65, 247, 0.616);
}
</style>
