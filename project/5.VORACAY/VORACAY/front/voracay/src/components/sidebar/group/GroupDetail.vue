<template>
  <div>
    <div class="group-name"></div>
    <div class="form-group row ">
      <div class="col-xs-3">
        <input
          class="form-control"
          type="text"
          v-model="groupDetail.groupName"
          placeholder="새 그룹명을 입력해주세요"
          @keyup.enter="overlapCheck"
        />
      </div>
      <div class="col-xs-1">
        <button @click="overlapCheck" class="btn btn-dark">중복검사</button>
      </div>
    </div>
    <div id="text-box">
      <textarea
        v-model="groupDetail.groupMemo"
        placeholder="새 메모를 입력해주세요"
      ></textarea>
    </div>
    <div>
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
        <div>
          <p>
            <button @click="addToList" class="btn btn-dark">추가</button>
          </p>
        </div>
      </div>
      <hr />
    </div>
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

          <td class="delete-btn" @click="deleteMember(userInfo.memberNumber)">
            삭제
          </td>
        </tr>
      </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >
        다음
      </button>
    </div>
    <div>
      <p>
        <button @click="updateGruop" class="btn btn-dark">수정하기</button>
      </p>
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
      checkedInfo: [],
      groupDetail: {
        groupId: -1,
        groupName: "testGroup",
        groupMemo: "testmemotestmemotestmemotestmemotestmemo",
      },
      groupMembers: [
        {
          memberName: "춘자1",
          memberNumber: 111,
          position: "반장1", // level
          memberEmail: "test01@gmail.com1",
          memberDept: "제조혁신1",
          isManager: true,
        },
        {
          memberName: "순자1",
          memberNumber: 221,
          position: "부장1", // level
          memberEmail: "test02@gmail.com1",
          memberDept: "제조혁신1",
          isManager: true,
        },
        {
          memberName: "용자1",
          memberNumber: 311,
          position: "사원1", // level
          memberEmail: "test03@gmail.com1",
          memberDept: "제조기획1",
          isManager: true,
        },
        {
          memberName: "돌쇠1",
          memberNumber: 123451,
          position: "인턴1", // level
          memberEmail: "test04@gmail.com1",
          memberDept: "제조기획1",
          isManager: true,
        },
        {
          memberName: "영희1",
          memberNumber: -1,
          position: "사장1", // level
          memberEmail: "test000@gmail.com1",
          memberDept: "총괄기획1",
          isManager: false,
        },
      ],
    };
  },
  created() {
    console.log("state 접근 : ", this.$store.state.detailId);
    // groupDetail <-> BackEND 에서 리스트 값 가져오기
    this.groupDetail.groupId = this.$store.state.detailId;
  },
  computed: {
    pageCount() {
      let listLeng = this.groupMembers.length,
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
      return this.groupMembers.slice(start, end);
    },
  },
  methods: {
    toggleAuth(userInfo) {
      // 직접 group에 새로 push된 ele를 바꾸게되면 re-rendering이 발생하지 않음
      let list = [...this.groupMembers];
      list.forEach((ele) => {
        if (ele.memberNumber == userInfo.memberNumber) {
          ele.isManager = !userInfo.isManager;
          this.groupMembers = list; // this.groupMembers의 주소값을 바꿔서 re-rendering유도
          return;
        }
      });
    },
    overlapCheck() {
      // 그룹명 중복검사
      this.isOverlap = !confirm(this.groupName + "으로 그룹을 만드시겠습니까?");
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    deleteMember(memberNumber) {
      // BackEND >> 삭제 코드 this.groupDetail.groupId

      for (let i = 0; i < this.groupMembers.length; i++) {
        if (this.groupMembers[i].memberNumber == memberNumber) {
          this.groupMembers.splice(i, 1);
        }
      }
    },
    addToList() {
      this.groupMembers.push(...this.checkedInfo);
      this.groupMembers = Array.from(new Set(this.groupMembers));
      this.checkedInfo = [];
      (this.searchResult = []),
        (this.searchText = ""),
        (this.searchCategory = ""),
        console.log(this.groupMembers);
      alert("추가 되었습니다!");
    },
    updateGruop() {
      // BackEnd>>그룹 수정 !!
      this.searchResult = [];
      this.checkedInfo = [];
      alert("수정되었습니다");
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
p button {
  float: right;
  margin: 1%;
  margin-right: 5%;
}
.row {
  margin-left: 0px;
}
#text-box {
  margin-bottom: 15px;
}
textarea {
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 100%;
  padding: 30px;
  height: 90px;
}
.group-auth:hover {
  font-weight: bold;
  color: rgb(85, 193, 255);
}
</style>
