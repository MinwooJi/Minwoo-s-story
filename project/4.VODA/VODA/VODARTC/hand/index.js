/**
 * @license
 * Copyright 2020 Google LLC. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =============================================================================
 */

import * as handpose from "@tensorflow-models/handpose";
import * as tf from "@tensorflow/tfjs-core";
// import * as tf from "@tensorflow/tfjs-core";
// import * as tf1 from "@tensorflow/tfjs-layers"; // 하..
import * as tfjsWasm from "@tensorflow/tfjs-backend-wasm";
import "@tensorflow/tfjs-backend-webgl";
import * as Hangul from "hangul-js";

tfjsWasm.setWasmPath(
  `https://cdn.jsdelivr.net/npm/@tensorflow/tfjs-backend-wasm@${tfjsWasm.version_wasm}/dist/tfjs-backend-wasm.wasm`
);

function isMobile() {
  const isAndroid = /Android/i.test(navigator.userAgent);
  const isiOS = /iPhone|iPad|iPod/i.test(navigator.userAgent);
  return isAndroid || isiOS;
}

let videoWidth,
  videoHeight,
  rafID,
  ctx,
  canvas,
  ANCHOR_POINTS,
  scatterGLHasInitialized = false,
  scatterGL,
  fingerLookupIndices = {
    thumb: [0, 1, 2, 3, 4],
    indexFinger: [0, 5, 6, 7, 8],
    middleFinger: [0, 9, 10, 11, 12],
    ringFinger: [0, 13, 14, 15, 16],
    pinky: [0, 17, 18, 19, 20],
  }; // for rendering each finger as a polyline
let temp;
let SERVER_URL = "https://j3d206.p.ssafy.io:8000/";
const VIDEO_WIDTH = 640;
const VIDEO_HEIGHT = 500;
const mobile = isMobile();
// Don't render the point cloud on mobile in order to maximize performance and
// to avoid crowding limited screen space.
const renderPointcloud = mobile === false;
let chars = [];
let cnt_temp = 0;
let prev_temp = -2;
const state = {
  backend: "webgl",
};

// const stats = new Stats();
// stats.showPanel(0);
// document.body.appendChild(stats.dom);

if (renderPointcloud) {
  state.renderPointcloud = true;
}

function setupDatGui() {
  const gui = new dat.GUI();
  gui.add(state, "backend", ["webgl", "wasm"]).onChange(async (backend) => {
    window.cancelAnimationFrame(rafID);
    await tf.setBackend(backend);
    landmarksRealTime(video);
  });

  if (renderPointcloud) {
    gui.add(state, "renderPointcloud").onChange((render) => {
      document.querySelector("#scatter-gl-container").style.display = render
        ? "inline-block"
        : "none";
    });
  }
}

function drawPoint(y, x, r) {
  ctx.beginPath();
  ctx.arc(x, y, r, 0, 2 * Math.PI);
  ctx.fill();
}

function drawKeypoints(keypoints) {
  const keypointsArray = keypoints;

  for (let i = 0; i < keypointsArray.length; i++) {
    const y = keypointsArray[i][0];
    const x = keypointsArray[i][1];
    drawPoint(x - 2, y - 2, 3);
  }

  const fingers = Object.keys(fingerLookupIndices);
  for (let i = 0; i < fingers.length; i++) {
    const finger = fingers[i];
    const points = fingerLookupIndices[finger].map((idx) => keypoints[idx]);
    drawPath(points, false);
  }
}

function drawPath(points, closePath) {
  const region = new Path2D();
  region.moveTo(points[0][0], points[0][1]);
  for (let i = 1; i < points.length; i++) {
    const point = points[i];
    region.lineTo(point[0], point[1]);
  }

  if (closePath) {
    region.closePath();
  }
  ctx.stroke(region);
}

let model;

async function setupCamera() {
  if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) {
    throw new Error(
      "Browser API navigator.mediaDevices.getUserMedia not available"
    );
  }

  const video = document.getElementById("video");
  const stream = await navigator.mediaDevices.getUserMedia({
    audio: false,
    video: {
      facingMode: "user",
      // Only setting the video to a specified size in order to accommodate a
      // point cloud, so on mobile devices accept the default size.
      width: mobile ? undefined : VIDEO_WIDTH,
      height: mobile ? undefined : VIDEO_HEIGHT,
    },
  });
  video.srcObject = stream;

  return new Promise((resolve) => {
    video.onloadedmetadata = () => {
      resolve(video);
    };
  });
}

async function loadVideo() {
  const video = await setupCamera();
  video.play();
  return video;
}

async function main() {
  await tf.setBackend(state.backend);
  model = await handpose.load();
  let video;

  try {
    video = await loadVideo();
  } catch (e) {
    let info = document.getElementById("info");
    info.textContent = e.message;
    info.style.display = "block";
    throw e;
  }

  // setupDatGui();

  videoWidth = video.videoWidth;
  videoHeight = video.videoHeight;

  canvas = document.getElementById("output");
  canvas.width = videoWidth;
  canvas.height = videoHeight;
  video.width = videoWidth;
  video.height = videoHeight;

  ctx = canvas.getContext("2d");
  ctx.clearRect(0, 0, videoWidth, videoHeight);
  ctx.strokeStyle = "red";
  ctx.fillStyle = "red";

  ctx.translate(canvas.width, 0);
  ctx.scale(-1, 1);

  // These anchor points allow the hand pointcloud to resize according to its
  // position in the input.
  ANCHOR_POINTS = [
    [0, 0, 0],
    [0, -VIDEO_HEIGHT, 0],
    [-VIDEO_WIDTH, 0, 0],
    [-VIDEO_WIDTH, -VIDEO_HEIGHT, 0],
  ];

  if (renderPointcloud) {
    document.querySelector(
      "#scatter-gl-container"
    ).style = `width: ${VIDEO_WIDTH}px; height: ${VIDEO_HEIGHT}px;`;

    scatterGL = new ScatterGL(document.querySelector("#scatter-gl-container"), {
      rotateOnStart: false,
      selectEnabled: false,
    });
  }

  landmarksRealTime(video);
  test();
  listener();
}
let i = 3;
let num = 1;
const landmarksRealTime = async (video) => {
  async function frameLandmarks() {
    // stats.begin();
    // let img = new Image();
    // img.addEventListener(
    //   "load",
    //   function () {
    //     // execute drawImage statements here
    //   },
    //   false
    // );
    // img.src = "../schech.JPG";

    ctx.drawImage(
      // img,
      video,
      0,
      0,
      videoWidth,
      videoHeight,
      0,
      0,
      canvas.width,
      canvas.height
    );
    const predictions = await model.estimateHands(video);
    if (predictions.length > 0) {
      const result = predictions[0].landmarks;
      // 왼쪽에 빨간 리드선 그리는 함수
      drawKeypoints(result, predictions[0].annotations);

      if (renderPointcloud === true && scatterGL != null) {
        const pointsData = result.map((point) => {
          return [-point[0], -point[1], -point[2]];
        });

        const dataset = new ScatterGL.Dataset([
          ...pointsData,
          ...ANCHOR_POINTS,
        ]);

        if (!scatterGLHasInitialized) {
          scatterGL.render(dataset);

          const fingers = Object.keys(fingerLookupIndices);

          scatterGL.setSequences(
            fingers.map((finger) => ({ indices: fingerLookupIndices[finger] }))
          );
          scatterGL.setPointColorer((index) => {
            if (index < pointsData.length) {
              return "steelblue";
            }
            return "white"; // Hide.
          });
        } else {
          scatterGL.updateDataset(dataset);
        }
        html2canvas(document.querySelector("#scatter-gl-container")).then(
          (ctx) => {
            temp = ctx;
            // console.log("check2 : ", temp);
          }
        );
        num++;
        scatterGLHasInitialized = true;
      }
    }
    // stats.end();
    rafID = requestAnimationFrame(frameLandmarks);
    // let load_model = await tf2.loadLayersModel(".file://../model1.h5");
    // console.log(load_model);
  }

  frameLandmarks();
};
function listener() {
  // console.log("wait.....");
  // console.log("wait temp :  ", temp);
  if (temp) {
    loader();
  } else {
    setTimeout(listener, 100);
  }
}
function loader() {
  console.log("loader() 실행됨!");

  var counter = 1;
  setInterval(function () {
    //clear previous segment variable counter
    var revCounter = counter - 1;
    if (cnt_temp == prev_temp + 1) {
      prev_temp = -2;
      counter = 1;
      revCounter = counter - 1;
      document.getElementById("segment-1").style.background = "#eaeaea";
      document.getElementById("segment-2").style.background = "#eaeaea";
      document.getElementById("segment-3").style.background = "#eaeaea";
      document.getElementById("segment-4").style.background = "#eaeaea";
      document.getElementById("segment-5").style.background = "#eaeaea";
    }
    // console.log("cnt_temp : ", cnt_temp);
    // console.log("prev_temp : ", prev_temp);
    // if (counter < 6) {
    //   console.log("setting segment " + counter + " = red");
    // }

    //set next segment red
    if (counter >= 1 && counter <= 5)
      document.getElementById("segment-" + counter).style.background = "red";
    counter++;
    if (revCounter >= 1 && revCounter <= 5)
      document.getElementById("segment-" + revCounter).style.background =
        "#eaeaea";
  }, 500);
}
function StartClock() {
  // html2canvas(document.querySelector("#scatter-gl-container")).then((ctx) => {
  console.log("temp! : ", temp);
  if (temp) {
    console.log("check!: ", temp);
    let dataUrl = temp.toDataURL("image/jpeg");
    // console.log(dataUrl);
    let blob = dataURItoBlob(dataUrl);
    // console.log(blob);
    var formData = new FormData();
    formData.append("file", blob);
    // console.log(formData);
    prev_temp = cnt_temp;
    cnt_temp++;
    $.ajax({
      url: SERVER_URL + "api/hand/recognize/",
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      dataType: "json", //xml, json,  file을 받을 수 없음
      enctype: "multipart/form-data", //매 우 중 요
      success: function (html) {
        console.log("success");
      },
      error: function (error) {},
    }).done(function (data) {
      //server에서 데이터 반환된 뒤 실행되는 함수
      console.log(data);
      if (data == "ㅒ") {
        // 줄넘김
        // start_flag = true;
        let temp = Hangul.assemble(chars);
        let rst = "";
        for (let i = 0; i < temp.length; i++) {
          console.log(temp[i]);
          if (Hangul.isComplete(temp[i])) {
            rst += temp[i];
          } else {
            console.log("필터링된 문자 : ", temp[i]);
          }
        }
        console.log("chars : ", chars);
        console.log("rst : ", rst);
        chars = [];
        localStorage.setItem("handjesture-message", rst);
      } else {
        if (Hangul.isVowel(data)) {
          // 모음은 연속해서 두번나올수없음 , 첫글자부터 모음일수 없음
          if (chars.length > 0 && !Hangul.isVowel(chars[chars.length - 1])) {
            chars.push(data);
          } else {
            console.log("필터링된 모음 : ", data);
          }
        } else {
          // 자음일경우 3번보다 많은 자음이 들어오면 걸름
          // ㅃ ㅄ ㄳ 등 이중자음 + 자음일경우 3번가능 // 이중자음 + 된소리 => 4번까지가능 값쑤 => 일단 된소리조합이 안되기떄문에 빼자..
          // 일반적인 경우 2번까지 가능
          if (chars.length >= 3) {
            // 일단 3번까지 고려
            if (
              Hangul.isConsonant(chars[chars.length - 1]) &&
              Hangul.isConsonant(chars[chars.length - 2]) &&
              Hangul.isConsonant(chars[chars.length - 3])
            ) {
              console.log("필터링된 자음 : ", data);
            } else {
              chars.push(data);
            }
          } else {
            chars.push(data);
          }
        }
      }
      // let temparr;
      // if (!localStorage.getItem("handjesture-message")) {
      //   temparr = [];
      // } else {
      //   temparr = JSON.parse(localStorage.getItem("handjesture-message"));
      // }
      // temparr.push(data);
      // console.log("ARRAY : ", temparr);
    });
    // });

    // var request = new XMLHttpRequest();
    // request.onload = completeRequest;

    // request.open("POST", "IdentifyFood");
    // request.send(formData);
  }
  let timerId = setTimeout(StartClock, 2500);
}
function dataURItoBlob(dataURI) {
  var byteString = atob(dataURI.split(",")[1]);
  var mimeString = dataURI.split(",")[0].split(":")[1].split(";")[0];
  var ab = new ArrayBuffer(byteString.length);
  var ia = new Uint8Array(ab);
  for (var i = 0; i < byteString.length; i++) {
    ia[i] = byteString.charCodeAt(i);
  }

  var bb = new Blob([ab], { type: mimeString });
  return bb;
}
async function test() {
  StartClock();

  // const { PythonShell } = require("python-shell");
  // let options = {
  //   args: ["value1", "value2", "value3"],
  // };
  // PythonShell.PythonShell.run("my_script.py", options, function (err, data) {
  //   if (err) throw err;
  //   console.log(data);
  // });
  // console.log(PythonShell.PythonShell.run);
  // let img = new Image();
  // img.addEventListener(
  //   "load",
  //   function () {
  //     // execute drawImage statements here
  //   },
  //   false
  // );
  // img.src = "../test.jpg";
  // let load_model = await tf1.loadLayersModel(
  //   "https://catheroes.s3.ap-northeast-2.amazonaws.com/mymodel/model.json"
  // );
  // console.log("img : ", img);
  // console.log(load_model);
  // console.log("판별결과는(ㄴ) : ", load_model.predict(img));
  // $.ajax({
  //   type: "POST",
  //   url: "/reverse_pca.py",
  //   data: { param: "test" },
  // }).done(function (o) {
  //   console.log(data);
  //   console.log(text);
  // });
}

// function saveAs(uri, filename) {
//   // 캡쳐된 파일을 이미지 파일로 내보낸다.
//   var link = document.createElement("a");
//   if (typeof link.download === "string") {
//     link.href = uri;
//     link.download = filename;
//     document.body.appendChild(link);
//     link.click();
//     document.body.removeChild(link);
//   } else {
//     window.open(uri);
//   }
// }

navigator.getUserMedia =
  navigator.getUserMedia ||
  navigator.webkitGetUserMedia ||
  navigator.mozGetUserMedia;

main();
