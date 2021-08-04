<template>
  <div class="mainchoose" ref="main" id="first">
    <div class="global-width">
      <h1 class="page-title">
        <span>V</span>
        <span>O</span>
        <span>D</span>
        <span>A</span>
        <!-- <span>인</span> -->
      </h1>
      <h1 class="page-title">
        <span>P</span>
        <span>R</span>
        <span>O</span>
        <span>L</span>
        <span>O</span>
        <span>G</span>
      </h1>
      <!-- <p>
				이 페이지는 BBC 비주얼저널리즘 팀에서 제작한 <a href="https://www.bbc.com/korean/resources/idt-48d3c9a7-4063-4289-9726-611b5ea9d7b5" target="_blank">'재택근무의 일상화'... 코로나19가 바꿀 사무실의 미래</a> 페이지를 비슷하게 구현해 본 개발 예제입니다. 시각적 기능만 비슷하게 만들어 본 것이므로, 개발 방식은 전혀 다를 수도 있습니다.<br>아래의 내용은 제주 바닷가에 사는 개발자인 저의 실제 일상이기도 하지만, 강의영상 예제로 만든 페이지이므로 내용에 큰 의미를 두고 보실 필요는 없습니다 ㅎㅎ<br>밑으로 스크롤 해봅시다.
			</p> -->
    </div>
    <section class="scroll-content">
      <div class="scroll-graphic">
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main1.png" alt="" />
        </div>
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main2.png" alt="" />
        </div>
        <div class="graphic-item" data-action="birdFlies">
          <!-- <img class="bird" src="../assets/images/bird.gif" alt=""> -->
          <img class="scene-img" src="../assets/main3.png" alt="" />
        </div>
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main4.png" alt="" />
        </div>
        <!-- <div class="graphic-item" data-action="birdFlies2"> -->
        <!-- <img class="bird" src="../assets/images/bird.gif" alt=""> -->
        <!-- <img class="scene-img" src="../assets/images/04.png" alt=""> -->
        <!-- </div> -->
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main5.png" alt="" />
        </div>
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main6.png" alt="" />
        </div>
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main7.png" alt="" />
        </div>
        <div class="graphic-item">
          <img class="scene-img" src="../assets/main8.png" alt="" />
        </div>
      </div>
      <div class="scroll-text global-width">
        <div class="step">
          <p>
            김보다씨를 소개합니다!<br /><br />김보다씨는 21살 청각장애인
            대학생입니다.<br />대학에서 수업을 들을 때 항상 속기사분과
            함께합니다.
          </p>
        </div>
        <div class="step">
          <p>그러다, 전 세계에 코로나 바이러스가 퍼지게 되었습니다.</p>
        </div>
        <div class="step">
          <p>
            사회적 거리두기를 위해 모든 대학에서는 <br />화상회의를 통한 비대면
            수업을 실시하게 됩니다.
          </p>
        </div>
        <div class="step">
          <p>
            청각장애인인 김보다씨는 작고 흐릿한 화면으로 <br />교수님의
            입모양으로만 수업 내용을 따라가려니 버겁습니다.
          </p>
        </div>
        <div class="step">
          <p>
            게다가 PPT 자료만 띄워 수업하시는 교수님과의 수업은 저절로 한숨이
            나옵니다.
          </p>
        </div>
        <div class="step">
          <p>
            그렇게 전 세계 김보다씨를 위해 청각장애인을 위한 화상회의 서비스,<br />
            VODA프로젝트를 기획하게 됐습니다
          </p>
        </div>
        <div class="step">
          <p>
            VODA서비스는 화상회의 서비스 위에 자막과 수화영상을 제공합니다
            <br />
            게다가 지화 인식까지!!
          </p>
        </div>
        <div class="step">
          <p>
            전 세계 김보다씨가 저희 서비스로 더 넓은 세상을 볼 수 있길 바랍니다!
          </p>
        </div>
        <!-- <div class="step">
          <p>
            자기 전, 가끔 <a href="https://www.youtube.com/channel/UCkTRKCuFrRiXQm_gMG1uDvg" target="_blank">요가소년</a> 채널을 보며 요가를 한다.
          </p>
        </div>
        <div class="step">
          <p>
            일과를 마치고 <span class="strikethrough">폰질을 한다</span> 잠자리에 든다.
          </p>
        </div> -->
      </div>
    </section>
    <div class="btn-boxing" id="last">
      <div class="three-choose" v-if="!isLoggedIn">
        <div class="choose-button">
          <div>
            <a href="/login"><button>로그인</button></a>
          </div>
          <div>
            <a href="/signup"><button>회원가입</button></a>
          </div>
          <div>
            <a href="/customer"><button>손님으로 사용</button></a>
          </div>
        </div>
      </div>
      <div class="main-box" v-if="isLoggedIn">
        <div class="meeting-button">
          <a href="https://j3d206.p.ssafy.io:8001/voda/create/"
            ><button>미팅룸 생성</button></a
          >
        </div>
        <div class="meeting-button">
          <a href="https://j3d206.p.ssafy.io:8001/voda/join/"
            ><button>미팅룸 참여</button></a
          >
        </div>
      </div>
    </div>
    <div class="up-down-btn">
      <div class="upBtn">
        <a href="#first"><i class="fas fa-arrow-up fa-2x"></i></a>
      </div>
      <div class="downBtn">
        <a href="#last"><i class="fas fa-arrow-down fa-2x"></i></a>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "MainChoose",
  title: "VODA | 청각장애인을 위한 화상회의 서비스",
  props: {
    isLoggedIn: Boolean,
  },
  data() {
    return {
      // isLoggedin: false,
    };
  },
  mounted() {
    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
      this.isLoggedIn = this.$cookies.isKey("token");
      // console.log("Login?", this.isLoggedIn);
    });

    const actions = {
      //true나 false가 들어옴
      birdFlies(key) {
        if (key) {
          document.querySelector(
            '[data-index="2"] .bird'
          ).style.transform = `translateX(${window.innerWidth}px)`;
        } else {
          document.querySelector(
            '[data-index="2"] .bird'
          ).style.transform = `translateX(-100%)`;
        }
      },
      birdFlies2(key) {
        if (key) {
          document.querySelector(
            '[data-index="5"] .bird'
          ).style.transform = `translate(${window.innerWidth}px, ${
            -window.innerHeight * 0.7
          }px)`;
        } else {
          document.querySelector(
            '[data-index="5"] .bird'
          ).style.transform = `translate(-100%)`;
        }
      },
    };

    const stepElems = document.querySelectorAll(".step");
    const graphicElems = document.querySelectorAll(".graphic-item");
    //현재 활성화된(visible 클래스가 붙은) .graphic-item을 지정
    let currentItem = graphicElems[0];
    let ioIndex;

    // IntersectionObserver
    //눈에 보이는지 사라지는지 확인 가능
    const io = new IntersectionObserver((entries) => {
      //문자열을 숫자열로 *1해서 바꿔주기
      ioIndex = entries[0].target.dataset.index * 1;
      console.log(ioIndex);
    });

    for (let i = 0; i < stepElems.length; i++) {
      //observer가 관찰하도록
      //사라지거나 나타날 때 시점 관찰
      io.observe(stepElems[i]);
      // stepElems[i].setAttribute('data-index', i);
      stepElems[i].dataset.index = i;
      graphicElems[i].dataset.index = i;
    }

    function activate(action) {
      currentItem.classList.add("visible");
      if (action) {
        actions[action](true);
      }
    }

    function inactivate(action) {
      currentItem.classList.remove("visible");
      if (action) {
        actions[action](false);
      }
    }

    window.addEventListener("scroll", () => {
      let step;
      let boundingRect;
      // let temp = 0;

      // for (let i = 0; i < stepElems.length; i++) {
      //그 전거 그 다음거
      //맨 처음에 ioIndex = 0이기 때문에 step이 값이 있을 때만 실행하도록
      for (let i = ioIndex - 1; i < ioIndex + 2; i++) {
        step = stepElems[i];
        // DomRect 위치와 너비
        if (!step) continue;
        boundingRect = step.getBoundingClientRect();

        // console.log(boundingRect.top);

        if (
          boundingRect.top > window.innerHeight * 0.1 &&
          boundingRect.top < window.innerHeight * 0.8
        ) {
          // 몇번째 인덱스에 몇번째 스텝이 있는지 확인
          // console.log(step.dataset.index);

          inactivate();
          currentItem = graphicElems[step.dataset.index];
          //data-action의 이름을 가져올 수 있음
          activate(currentItem.dataset.action);
        }
      }
      // console.log(temp);
    });
    //새로고침 했을 때 위로 올리는 작업
    window.addEventListener("load", () => {
      // (x, y), ms시간
      setTimeout(() => scrollTo(0, 0), 100);
    });
    // 첫번째 인덱스 자동으로 실행됨
    activate();
  },
  updated() {
    this.isLoggedIn = this.$cookies.isKey("token");
    // console.log("updated Login?", this.isLoggedIn);
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: rgb(73, 12, 73);
  font-weight: 800;
  font-family: "Jua", sans-serif;
}

h1 {
  height: 100px;
}

h1 span {
  font-family: "Jua", sans-serif;
  position: relative;
  top: 20px;
  display: inline-block;
  animation: bounce 0.3s ease 4 alternate;
  font-size: 80px;
  color: #fff;
  text-shadow: 0 1px 0 #ccc, 0 2px 0 #ccc, 0 3px 0 #ccc, 0 4px 0 #ccc,
    0 5px 0 #ccc, 0 6px 0 transparent, 0 7px 0 transparent, 0 8px 0 transparent,
    0 9px 0 transparent, 0 10px 10px rgba(0, 0, 0, 0.4);
}

h1 span:nth-child(2) {
  animation-delay: 0.1s;
}
h1 span:nth-child(3) {
  animation-delay: 0.2s;
}
h1 span:nth-child(4) {
  animation-delay: 0.3s;
}
h1 span:nth-child(5) {
  animation-delay: 0.4s;
}
h1 span:nth-child(6) {
  animation-delay: 0.5s;
}
h1 span:nth-child(7) {
  animation-delay: 0.6s;
}
h1 span:nth-child(8) {
  animation-delay: 0.7s;
}

@keyframes bounce {
  100% {
    top: -20px;
    text-shadow: 0 1px 0 #ccc, 0 2px 0 #ccc, 0 3px 0 #ccc, 0 4px 0 #ccc,
      0 5px 0 #ccc, 0 6px 0 #ccc, 0 7px 0 #ccc, 0 8px 0 #ccc, 0 9px 0 #ccc,
      0 50px 25px rgba(0, 0, 0, 0.2);
  }
}

.page-title {
  padding-top: 100px;
  font-weight: 700;
  font-size: 400%;
  color: white;
  font-family: "Noto Sans KR", sans-serif;
}

.mainchoose {
  background: #d4a3ff;
  /* background-image: linear-gradient(to right bottom, #d4a3ff, #d098ff, #cc8cff, #c980ff, #c574ff, #ca74ff, #ce75fe, #d375fe, #df83fe, #ea92fe, #f3a1fe, #fbb0ff); */
  width: 100vw;
  height: 100vh;
}
div,
section,
header,
p,
h1,
h2 {
  box-sizing: border-box;
}
img {
  max-width: 100vw;
  height: auto;
}
.global-width {
  max-width: 620px;
  margin: 0 auto;
  padding: 0 15px;
}
.scroll-content {
  /* background-image: linear-gradient(to right bottom, #d4a3ff, #d098ff, #cc8cff, #c980ff, #c574ff, #ca74ff, #ce75fe, #d375fe, #df83fe, #ea92fe, #f3a1fe, #fbb0ff); */
  background: #d4a3ff;
  /* overflow-x: hidden; */
}
.scroll-graphic {
  /* 사진이 닿았을 때 고정됨 */
  position: sticky;
  top: 0;
  overflow: hidden;
  /* sticky는 heigh가 없어서 height값 정해줌 */
  height: 100vh;
}
.graphic-item {
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: 0.5s;
  /* 바뀔거 미리 알려줌 */
  /* 좀 더 효율적으로 바꿔줌 */
  will-change: opacity;
}
.visible {
  opacity: 1;
}
.scene-img {
  max-height: 100vh;
}

.scroll-text {
  /* 기본값은 static인데 relative를 사용 */
  /* z-index를 따로 줘야함 */
  /* scroll-graphic이 scroll-text보다 뒤에 있기 때문에 앞에 보임 */
  position: relative;
}
.step {
  margin-bottom: 60vh;
  border-radius: 10px;
  padding: 0.5rem 1rem;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: rgba(0, 0, 0, 0.3) 0 0 3px;
  text-align: center;
}
.step p {
  font-weight: 800;
  font-family: "Jua", sans-serif;
}

.bird {
  position: absolute;
  left: 0;
  bottom: 70%;
  width: 100px;
  /* 재생시간 delay 속도일정 */
  transform: translateX(-100%);
  transition: 1.5s 0.5s linear;
}
[data-index="5"] .bird {
  left: 30%;
  bottom: 30%;
}

.global-width {
  padding: 2rem 0;
  text-align: center;
}

.strikethrough {
  text-decoration: line-through;
}

.btn-boxing {
  background: #d4a3ff;

  /* width: 100vh;
  height: auto; */

  position: sticky;
  top: 0;
  overflow: hidden;
  /* sticky는 heigh가 없어서 height값 정해줌 */
  height: 100vh;
}

.three-choose {
  background: #d4a3ff;
  margin: 0 auto;
  width: 300px;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}
button {
  width: 100%;
  margin-bottom: 20px;
  padding: 10px;
  border-radius: 5px;
  /* height: 40px; */
  transition: all ease 0.5s;
  border: 1px solid rgba(255, 255, 255, 0.932);
  background-color: rgba(255, 254, 255, 0.082);
  font-family: "Viga", sans-serif;
  color: white;
  font-size: 20px;
}
button:hover {
  cursor: pointer;
  transform: scale(1.1);

  background-color: #b86ffc49;
}
button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(255, 200, 255, 0.616);
  box-shadow: 0 0 20px rgba(255, 200, 255, 0.616);
}
.meeting-button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.meeting-button button {
  font-weight: bolder;
  width: 100%;
  margin-bottom: 30px;
  padding: 50px 200px;
  border-radius: 5px;
  /* height: 40px; */
  transition: all ease 0.5s;
  border: 1px solid rgba(255, 255, 255, 0.932);
  background-color: rgba(250, 176, 250, 0.233);
  font-family: "Viga", sans-serif;
  color: white;
  font-size: 20px;
}
.meeting-button button:hover {
  cursor: pointer;
  transform: scale(1.1);
  font-weight: bolder;
  background-color: #c98af391;
  color: rgba(163, 101, 179, 0.76);
  box-shadow: 0 0 20px rgba(225, 65, 247, 0.616);
}
.meeting-button button:focus {
  outline: none !important;
  border: 0;
  border: 2px solid rgba(167, 47, 236, 0.616);
  box-shadow: 0 0 20px rgba(225, 65, 247, 0.616);
}
.main-box {
  width: 100vh;
  position: absolute;
  top: 47%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.up-down-btn {
  position: fixed;
  bottom: 50px;
  right: 50px;
}
.upBtn {
  background: rgba(255, 255, 255, 0.473);
  border-radius: 50%;
  width: 80px;
  height: 80px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
}
.downBtn {
  margin-top: 10px;
  background: rgba(255, 255, 255, 0.473);
  border-radius: 50%;
  width: 80px;
  height: 80px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
}
</style>