<template>
  <div class="group-body">
    <!-- <img
      src="../../../assets/image/card/1.jpg"
      alt="voda 로고"
      width="150"
      height="50"
    /> -->
    <div class="test"></div>
    <div class="auto-groups">
      <ul>
        <li
          v-for="(group, index) in groups"
          :key="group.groupId"
          class="group-list"
        >
          {{ group.groupName }}
          <button @click="deleteGroup(index)">&#10006;</button>
        </li>
      </ul>
    </div>
    <div v-if="!groups.length">
      <div>통화할 그룹을 선택해주세요.</div>
    </div>
    <div v-else class="top_btns">
      <button @click="callGroups" class="btn btn-dark">통화하기</button>
    </div>
    <div class="group-update-btn">
      <button @click="updateGroup" class="btn btn-dark">그룹관리</button>
    </div>
    <div class="search-group">
      <label for="search">
        Search
        <input
          class="dict-search"
          type="text"
          id="search"
          v-model="searchText"
          placeholder="통화할 그룹을 검색하세요"
          @keyup.enter="searchGroup"
        />
      </label>
    </div>
    <div class="card-container pop-scroll">
      <div class="cards" v-for="data in showList" :key="data.groupId">
        <div class="flip">
          <div class="front" :class="'front-' + (data.groupId % 23)">
            <h1 class="text-shadow">{{ data.groupName }}</h1>
          </div>
          <div class="back" @click="clickGroup(data)">
            <h2 style="text-align:center; margin-top: 20px;">
              {{ data.groupName }}
            </h2>
            <div style="text-align:center;">
              <h3
                :href="data.memo"
                style="text-align:center; margin-top: 30px;"
                :title="data.memo"
              >
                {{ data.memo }}
              </h3>
            </div>
            <!-- <p>{{data.COMPANY_IMAGE}}</p> -->
            <!-- <img :src="data.COMPANY_IMAGE" alt="이미지" /> -->
          </div>
          <!-- <div class="back-btn-container">
            <button @click="deleteCard(data.groupId)" class="back-btn">
              <i style="color:tomato;" class="xi-close xi-2x"></i>
            </button>
          </div> -->
        </div>
        <!-- <div class="card-modal" v-if="show">
                <MyCardModal :show="show" :data="data" @modal-show="popModal"/>
            </div> -->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      groups: [],
      searchText: "",
      searchedGroups: [],
      showList: [],
      userGroupList: [
        {
          groupId: 1,
          groupName: "삼정SDI 제조라인",
          memo: "소리가 잘안들릴때 많음 텍스트 위주로 전달",
        },
        {
          groupId: 2,
          groupName: "생산라인 과장들",
          memo: "생산일정 짜낼떄 김과장님 의견 먼저 듣어보는게 조음",
        },
        {
          groupId: 3,
          groupName: "SDI 기획 사무실",
          memo: "회의내용 잘기록해놔야함",
        },
        {
          groupId: 4,
          groupName: "전자 기획팀",
          memo: "회의전 복장 외모 확인하고 들어갈것",
        },
      ],
      // image: [
      //   // "background-color: red",
      //   "background-image:url(./1.jpg)",
      //   "background-image: url(https://images.unsplash.com/photo-1554462307-f6296d218bb7?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80)",
      //   "background-image: url(https://images.unsplash.com/photo-1592782080385-f631279519eb?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60)",
      //   "background-image: url(https://images.unsplash.com/photo-1596491690105-2916ddc03050?ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80)",
      //   "background-image: url(https://cdn.pixabay.com/photo/2016/12/08/21/32/skyscraper-1893215_960_720.jpg)",
      //   "background-image: url(https://cdn.pixabay.com/photo/2019/01/26/11/18/chicago-3956018_960_720.jpg)",
      //   "background-image: url(https://images.unsplash.com/photo-1493966936727-e9e3da7326ea?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80)",
      //   "background-image: url(https://pds.joins.com/news/component/htmlphoto_mmdata/201810/17/c9afe9db-2231-4125-8a65-123eec974a08.jpg)",
      //   "background-image: url(https://s3-ap-northeast-2.amazonaws.com/mobiinsidecontent/kr/wp-content/uploads/2020/06/12160643/GettyImages-514799381-scaled.jpg)",
      //   "background-image: url(https://d2ilb6aov9ebgm.cloudfront.net/2020-03-02T19.32.59.869_818.jpg)",
      //   "background-image: url(https://www.kimyoubi.com/wp-content/uploads/2018/08/%E1%84%92%E1%85%AC%E1%84%89%E1%85%A1%E1%84%8B%E1%85%B4-%E1%84%8C%E1%85%A5%E1%86%B8%E1%84%83%E1%85%A2-%E1%84%86%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AA%E1%84%80%E1%85%A1-%E1%84%89%E1%85%B5%E1%86%B6%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%B5%E1%84%83%E1%85%A1-1280x640.jpg)",
      // ],
    };
  },
  created() {
    console.log("GROUP created");
    this.showList = this.userGroupList;
  },
  mounted() {
    console.log("GROUP mounted");
  },
  // watch: {
  //   searchedGroups: function(newVal, oldVal) {
  //     if (newVal.length) {
  //       this.showList = newVal;
  //     } else {
  //       this.showList = this.userGroupList;
  //     }
  //   },
  // },
  methods: {
    // bindImage(index) {
    //   console.log("제대로 실행됨!");
    //   console.log(
    //     `background-image: url(../../../assets/image/card/${index}.jpg)`
    //   );
    //   return `background-image: url('1.jpg')`;
    // },
    async searchGroup() {
      if (!this.searchText) {
        // alert("그룹명을 입력해주세요");
        this.showList = this.userGroupList;
        return;
      }
      this.searchedGroups = [];
      await this.find();
      if (this.searchedGroups.length) {
        this.showList = this.searchedGroups;
      } else {
        alert("검색결과가 없습니다!");
        this.showList = this.userGroupList;
      }
    },
    find() {
      this.userGroupList.forEach((val) => {
        // console.log("찾을데이터 ", this.searchText);
        // console.log("비교할 데이터 ", val.groupName);
        if (val.groupName.includes(this.searchText)) {
          this.searchedGroups.push(val);
        }
      });
    },
    async callGroups() {
      if (!this.groups.length) {
        alert("통화 대상이 없습니다!");
        return;
      }
      // let permitCall;
      // if (this.groups.length <= 1) {
      //   permitCall = confirm(temp + " 그룹에게 전화하시겠습니까?");
      // } else {
      //   permitCall = confirm(
      //     temp +
      //       "외 " +
      //       this.groups.length -
      //       1 +
      //       "개 그룹에게 전화하시겠습니까?"
      //   );
      // }
      // if (permitCall) {
      this.groups = [];
      alert("전화합니다");
      // }
    },
    deleteGroup(index) {
      this.groups.splice(index, 1);
      console.log(this.groups.index);
    },
    clickGroup(group) {
      if (!this.groups.includes(group)) {
        this.groups.push(group);
      }
    },
    updateGroup() {
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", "isGroupUpdate");
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/assets/scss/card.scss";
</style>
<style lang="css" scoped>
/* .test {
  width: 200px;
  height: 300px;
  background-image: url("./1.jpg");
} */
.group-list {
  display: inline;
  margin-right: 15px;
  /* border: 1px solid black; */
  background-color: rgba(165, 201, 223, 0.349);
  border-radius: 5px;
  padding: 4px;
}
.auto-groups {
  margin-top: 15px;
  background-color: rgb(236, 236, 236);
}
.auto-groups p {
  display: inline;
  margin-right: 15px;
}

/* .group-list-call {
  display: inline;
}
.group-list {
  width: 80%;
  float: left;
}
.group-list-btn {
  float: left;
} */
.group-body {
  padding-top: 5%;
}
.cards {
  margin-left: 5%;
}
.flip {
  word-wrap: break-word;
  word-break: break-all;
}
.flip:hover {
  cursor: pointer;
}
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
.card-container {
  margin-top: 10%;
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  height: 540px;
  display: flex;
  flex-wrap: wrap;
}
.back-btn-container {
  width: 100%;
  text-align: center;
  margin-top: 50px;
}
.back {
  position: relative;
  padding: 20px;
  height: 100%;
  width: 100%;
}

.back-btn {
  position: absolute;
  top: 3%;
  right: 3%;
}
.back-btn-container {
  width: 40px;
  height: 40px;
}
.table-hover {
  width: 90%;
}
.table-hover > thead {
  background-color: lightblue;
}
.top_btns {
  float: right;
  margin-right: 5%;
}
.group-update-btn {
  margin-right: 2px;
  float: right;
}
.search-group {
  padding-top: 5%;
  margin: auto;
}
</style>
<style scoped>
/* 카드디자인 */
.front-1 {
  background-image: url("../../../assets/image/card/1.jpg");
}
.front-2 {
  background-image: url("../../../assets/image/card/2.jpg");
}
.front-3 {
  background-image: url("../../../assets/image/card/3.jpg");
}
.front-4 {
  background-image: url("../../../assets/image/card/4.jpg");
}
.front-5 {
  background-image: url("../../../assets/image/card/5.jpg");
}
.front-6 {
  background-image: url("../../../assets/image/card/6.jpg");
}
.front-7 {
  background-image: url("../../../assets/image/card/7.jpg");
}
.front-8 {
  background-image: url("../../../assets/image/card/8.jpg");
}
.front-9 {
  background-image: url("../../../assets/image/card/9.jpg");
}
.front-0 {
  background-image: url("../../../assets/image/card/10.jpg");
}
.front-11 {
  background-image: url("../../../assets/image/card/11.jpg");
}
.front-12 {
  background-image: url("../../../assets/image/card/12.jpg");
}
.front-13 {
  background-image: url("../../../assets/image/card/13.jpg");
}
.front-14 {
  background-image: url("../../../assets/image/card/14.jpg");
}
.front-15 {
  background-image: url("../../../assets/image/card/15.jpg");
}
.front-16 {
  background-image: url("../../../assets/image/card/16.jpg");
}
.front-17 {
  background-image: url("../../../assets/image/card/17.jpg");
}
.front-18 {
  background-image: url("../../../assets/image/card/18.jpg");
}
.front-19 {
  background-image: url("../../../assets/image/card/19.jpg");
}
.front-20 {
  background-image: url("../../../assets/image/card/20.jpg");
}
.front-21 {
  background-image: url("../../../assets/image/card/21.jpg");
}
.front-22 {
  background-image: url("../../../assets/image/card/22.jpg");
}
.front-10 {
  background-image: url("../../../assets/image/card/23.jpg");
}
</style>
