<template>
  <div class="customer">
    <div class="customer-input">
      <div class="voda-logo">
        <!-- <img alt="voda logo" src="../assets/logo_transparent.png" width="300" height="300"> -->
        <h1>CUSTOMER</h1>
      </div>
      <div class="customer-div">
        <label for="roomlink">ROOM LINK</label>
        <input type="text" id="roomlink" v-model="sessionid" />
      </div>
      <div class="customer-div">
        <label for="nickname">NICKNAME</label>
        <input type="text" id="nickname" v-model="userFullName" />
      </div>

      <div class="customer-button">
        <a :href="url"><button>다음</button></a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Customer",
  title: "VODA | 미팅룸 참여",
  data() {
    return {
      userFullName: "",
      sessionid: "",
    };
  },
  computed: {
    url: function () {
      return (
        "https://j3d206.p.ssafy.io:8001/voda/join/canvas-designer.html?open=false&sessionid=" +
        this.sessionid +
        "&publicRoomIdentifier=voda&userFullName=" +
        this.userFullName
      );
    },
  },
};
</script>

<style scoped>
.customer {
  /* background-image: linear-gradient(to left bottom, #f2aaf6, #ebb1fa, #e4b7fd, #ddbdfe, #d8c2ff, #d9c4ff, #dbc6ff, #dcc8ff, #e4c8ff, #ecc8ff, #f4c8ff, #fcc8ff); */
  background: linear-gradient(
    -45deg,
    #cddcff,
    rgb(232, 200, 253),
    #c966ff,
    #d8c2ff
  );
  background-size: 400% 400%;
  animation: gradient 10s ease infinite;
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
@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.customer-input {
  width: 300px;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.customer-div {
  display: inline-block;
  width: 100%;
  margin-bottom: 20px;
}
.customer-div input {
  width: 250px;
  height: 20px;
  border-radius: 5px;
  border: 2px solid rgba(255, 255, 255, 0.932);
  background: rgba(240, 255, 240, 0.185);
  font-family: "Viga", sans-serif;
  padding: 10px 20px;
  color: rgba(162, 49, 255, 0.863);
  font-size: 15px;
}
.customer-div input:hover {
  border: 2px solid rgba(145, 108, 245, 0.616);
}
.customer-div input:focus {
  /* input 클릭했을 때 검정 테두리 없애줌 */
  outline: none !important;
  border: 2px solid rgba(145, 108, 245, 0.616);
  box-shadow: 0 0 20px rgba(145, 108, 245, 0.616);
}
.customer-button button {
  justify-content: center;
  width: 295px;
  border-radius: 5px;
  height: 40px;
  transition: all ease 0.5s;
  border: 1px solid rgba(255, 255, 255, 0.932);
  background-color: rgba(255, 254, 255, 0.082);
  font-family: "Viga", sans-serif;
  color: white;
  font-size: 20px;
}
.customer-button button:hover {
  cursor: pointer;
  transform: scale(1.1);
  background-color: #b86ffc49;
}
.customer-button button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(255, 200, 255, 0.616);
  box-shadow: 0 0 20px rgba(255, 200, 255, 0.616);
}
.voda-logo {
  width: 200%;
  font-size: 130%;
  text-align: center;
  font-family: "Cairo", sans-serif;
  opacity: 0.9;
  text-shadow: 2px 3px 0px #fdffeca9;
  color: rgba(242, 177, 255, 0.733);
  display: flex;
  align-items: center;
  /* margin: 0 auto; */
}
</style>