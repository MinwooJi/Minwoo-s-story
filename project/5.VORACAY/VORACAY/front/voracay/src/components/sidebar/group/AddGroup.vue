<template>
  <div>
    <div v-if="isOverlap">
      <div class="group-name">
        <div class="form-group row">
          <div class="col-xs-3">
            <input
              class="form-control"
              type="text"
              v-model="groupName"
              placeholder="그룹명 입력"
              @keyup.enter="overlapCheck"
            />
          </div>
          <div class="col-xs-4">
            <button @click="overlapCheck" class="btn btn-dark">중복검사</button>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="noOverlap">
      <br />
      <hr />
      <div class="search">
        <p>새 그룹에 추가할 사용자를 선택하세요.</p>
        <div class="form-group row">
          <div class="col-xs-2">
            <select
              class="form-control"
              name="searchCategory"
              v-model="searchCategory"
            >
              <option disabled value>카테고리 설정</option>
              <option value="memberName" selected>사용자 이름</option>
              <option value="memberNumber">사번</option>
              <option value="position">직위</option>
              <option value="memberEmail">e-mail</option>
              <option value="memberDept">부서</option>
            </select>
          </div>
          <div class="col-xs-3">
            <input
              class="form-control"
              type="text"
              v-model="searchText"
              placeholder="회원 검색"
              @keyup.enter="search"
            />
          </div>
          <div class="col-xs-4">
            <button @click="search" class="btn btn-dark">검색</button>
          </div>
        </div>
      </div>
      <div v-if="searchResult.length">
        <div class="pop-scroll">
          <table class="table">
            <thead>
              <tr>
                <th></th>
                <th>사용자</th>
                <th>사번</th>
                <th>레벨</th>
                <th>이메일</th>
                <th>부서</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="userInfo in searchResult" :key="userInfo.memberNumber">
                <td>
                  <input
                    type="checkbox"
                    :value="userInfo"
                    v-model="checkedInfo"
                  />
                </td>
                <td>{{ userInfo.memberName }}</td>
                <td>{{ userInfo.memberNumber }}</td>
                <td>{{ userInfo.position }}</td>
                <td>{{ userInfo.memberEmail }}</td>
                <td>{{ userInfo.memberDept }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="right-btn">
          <p>
            <button @click="addToList" class="btn btn-dark">추가</button>
          </p>
        </div>
      </div>
      <hr />
      <div v-if="addMemberList.length">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>사용자</th>
              <th>사번</th>
              <th>레벨</th>
              <th>이메일</th>
              <th>부서</th>
              <th>권한</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="userInfo in paginatedData" :key="userInfo.memberNumber">
              <td>{{ userInfo.memberName }}</td>
              <td>{{ userInfo.memberNumber }}</td>
              <td>{{ userInfo.position }}</td>
              <td>{{ userInfo.memberEmail }}</td>
              <td>{{ userInfo.memberDept }}</td>
              <td
                class="group-auth"
                v-if="userInfo.isManager == true"
                @click="toggleAuth(userInfo)"
              >
                관리자
              </td>
              <td class="group-auth" v-else @click="toggleAuth(userInfo)">
                그룹원
              </td>
              <td
                class="delete-btn"
                @click="deleteMember(userInfo.memberNumber)"
              >
                삭제
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
        <div class="right-btn">
          <p>
            <button @click="saveNewGruop" class="btn btn-dark">저장</button>
          </p>
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
      groupName: "",
      searchText: "",
      searchCategory: "",
      searchResult: [],
      addMemberList: [], // result
      checkedInfo: [],
      isOverlap: true,
    };
  },
  created() {},
  computed: {
    pageCount() {
      let listLeng = this.addMemberList.length,
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
      return this.addMemberList.slice(start, end);
    },
  },
  methods: {
    toggleAuth(userInfo) {
      // 직접 group에 새로 push된 ele를 바꾸게되면 re-rendering이 발생하지 않음
      let list = [...this.addMemberList];
      list.forEach((ele) => {
        if (ele.memberNumber == userInfo.memberNumber) {
          ele.isManager = !userInfo.isManager;
          this.addMemberList = list; // this.addMemberList 주소값을 바꿔서 re-rendering유도
          return;
        }
      });
    },

    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    overlapCheck() {
      // 그룹명 중복검사
      this.isOverlap = !confirm(this.groupName + "으로 그룹을 만드시겠습니까?");
    },
    saveNewGruop() {
      console.log("data : ", this.addMemberList);
      // isManage가 있을수도 있고 없을수도있음 없으면 false처리하도록 backend에 말해줄것
      this.searchResult = [];
      this.addMemberList = [];
      this.checkedInfo = [];
      alert("새 그룹이 생성되었습니다.");
      this.$store.commit("setMainRouterFalse");
      this.$store.commit("setMainRouterTrue", "isGroupCall");
    },
    deleteMember(memberNumber) {
      // BackEND >> 삭제 코드 this.groupDetail.groupId

      for (let i = 0; i < this.addMemberList.length; i++) {
        if (this.addMemberList[i].memberNumber == memberNumber) {
          this.addMemberList.splice(i, 1);
        }
      }
    },
    addToList() {
      this.addMemberList.push(...this.checkedInfo);
      this.addMemberList = Array.from(new Set(this.addMemberList));
      console.log(this.addMemberList);
    },
    search() {
      console.log(this.searchText);
      console.log(this.searchCategory);
      if (!this.searchText) {
        alert("검색어를 입력하세요");
        return;
      } else if (!this.searchCategory) {
        alert("검색 카테고리를 입력하세요");
        return;
      }
      // sample!
      this.searchResult = [
        {
          memberName: "춘자",
          memberNumber: 11,
          position: "반장", // level
          memberEmail: "test01@gmail.com",
          memberDept: "제조혁신",
        },
        {
          memberName: "순자",
          memberNumber: 22,
          position: "부장", // level
          memberEmail: "test02@gmail.com",
          memberDept: "제조혁신",
        },
        {
          memberName: "용자",
          memberNumber: 31,
          position: "사원", // level
          memberEmail: "test03@gmail.com",
          memberDept: "제조기획",
        },
        {
          memberName: "돌쇠",
          memberNumber: 12345,
          position: "인턴", // level
          memberEmail: "test04@gmail.com",
          memberDept: "제조기획",
        },
        {
          memberName: "영희",
          memberNumber: 0,
          position: "사장", // level
          memberEmail: "test000@gmail.com",
          memberDept: "총괄기획",
        },
      ];

      //
    },
  },
};
</script>
<style scoped>
.delete-btn {
  font-weight: bold;
}
.delete-btn:hover {
  color: red;
  font-weight: bold;
}
.group-name {
  padding-top: 10%;
}

.table-hover {
  width: 100%;
}
.table-hover > thead {
  background-color: lightblue;
}
/* button{
        float:right;
        margin: 1%;
        margin-right: 5%;
    } */
.right-btn button {
  float: right;
  margin: 1%;
  margin-right: 5%;
}
.btn-cover {
  display: inline-flex;
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.pop-scroll {
  width: 100%;
  height: 300px;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.group-auth:hover {
  font-weight: bold;
  color: rgb(85, 193, 255);
}
</style>
