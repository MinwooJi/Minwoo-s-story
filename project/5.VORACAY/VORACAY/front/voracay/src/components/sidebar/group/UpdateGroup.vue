<template>
  <div class="update-group">
    <div class="update-contents">
      <div v-if="manageGroupList.length">
        <div class="table-name">관리중인 그룹</div>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>no</th>
              <th>그룹명</th>
              <th>메모</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="groupInfo in paginatedData" :key="groupInfo.groupId">
              <td @click="detail(groupInfo.groupId)">
                {{ groupInfo.groupId }}
              </td>
              <td @click="detail(groupInfo.groupId)">
                {{ groupInfo.groupName }}
              </td>
              <td @click="detail(groupInfo.groupId)">{{ groupInfo.memo }}</td>
              <td class="delete-btn" @click="deleteGroup(groupInfo)">
                그룹삭제
              </td>
            </tr>
          </tbody>
        </table>
        <div class="btn-cover">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
            이전
          </button>
          <span class="page-count"
            >{{ pageNum + 1 }} / {{ pageCount }} 페이지</span
          >
          <button
            :disabled="pageNum >= pageCount - 1"
            @click="nextPage"
            class="page-btn"
          >
            다음
          </button>
        </div>
      </div>
      <div v-else>
        <p>관리중인 그룹이 없습니다.</p>
      </div>
      <div class="add-group-btn">
        <button @click="addGroup" class="btn btn-dark">그룹 추가</button>
      </div>
    </div>
    <div class="my-part-group">
      <div v-if="userGroupList.length">
        <div class="table-name">참가중인 그룹</div>
        <div class="pop-scroll">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>no</th>
                <th>그룹명</th>
                <th>메모</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="groupInfo in userGroupList" :key="groupInfo.groupId">
                <td>
                  {{ groupInfo.groupId }}
                </td>
                <td>
                  {{ groupInfo.groupName }}
                </td>
                <td>{{ groupInfo.memo }}</td>
                <td class="delete-btn" @click="withdrawGroup(groupInfo)">
                  그룹탈퇴
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      pageSize: this.$store.state.pageSize,
      pageNum: 0,
      manageGroupList: [
        {
          groupId: 1,
          groupName: "삼정SDI 제조라인",
          memo: "소리가 잘안들릴때 많음 텍스트 위주로 전달",
        },
        {
          groupId: 3,
          groupName: "SDI 기획 사무실",
          memo: "회의내용 잘기록해놔야함",
        },
      ],
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
    };
  },
  computed: {
    pageCount() {
      let listLeng = this.manageGroupList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.manageGroupList.slice(start, end);
    },
  },
  methods: {
    withdrawGroup(group) {
      // 그룹탈퇴
      if (!confirm("정말 " + group.groupName + " 그룹을 탈퇴하시겠습니까?")) {
        return;
      } else {
        for (let i = 0; i < this.userGroupList.length; i++) {
          if (this.userGroupList[i].groupId == group.groupId) {
            this.userGroupList.splice(i, 1);
          }
        }
      }
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    addGroup() {
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", "isGroupInsert");
    },
    detail(id) {
      console.log("click");
      this.$store.commit("setDetailId", id);
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", "isGroupDetail");
      // this.$emit("showGroupDetail");
    },
    deleteGroup(group) {
      //그룹삭제
      if (!confirm("정말 " + group.groupName + " 그룹을 삭제하시겠습니까?")) {
        return;
      } else {
        for (let i = 0; i < this.manageGroupList.length; i++) {
          if (this.manageGroupList[i].groupId == group.groupId) {
            this.manageGroupList.splice(i, 1);
          }
        }
      }
    },
  },
};
</script>
<style scoped>
@font-face {
  font-family: "Viga";
  src: url("../../../assets/font/updateGroup/DoHyeon-Regular.ttf");
}
.table-name {
  font-family: "Do Hyeon", sans-serif;
  color: rgba(29, 29, 29, 0.377);
  font-weight: bold;
}
.my-part-group {
  margin-top: 5%;
}
.pop-scroll {
  width: 100%;
  height: 350px;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.delete-btn {
  font-weight: bold;
}
.delete-btn:hover {
  color: red;
  font-weight: bold;
}

.update-contents {
  margin-top: 7%;
  width: 100%;
}
.update-group {
  padding-top: 5%;
}
.table-hover {
  width: 100%;
}
.right-btn button {
  float: right;
  margin: 1%;
  /* margin-right: 5%; */
}
.add-group-btn {
  float: right;
  /* margin-right: 5%; */
}
</style>
