<template>
  <div class="exp-table">
    <h3 class="exp-table-title">경험정리</h3>
    <table class="exp-table-wrap">
      <thead class="exp-thead">
        <tr>
          <th class="exp-no">No</th>
          <th class="exp-date">언제(기간)</th>
          <th class="exp-working">과목(활동내용)</th>
          <th class="exp-subject">주제</th>
          <th class="exp-del-up">-</th>
        </tr>
      </thead>
      <tbody>
        <!-- <tr v-for="(expTableData, index) in paginatedData" :key="expTableData.index"> -->
        <tr v-for="exp in paginatedData" :key="exp.EXP_NUMBER">
          <td>{{ exp.EXP_NUMBER }}</td>
          <td>{{ exp.EXP_DATE }}</td>
          <td>{{ exp.EXP_WORKING }}</td>
          <td>{{ exp.EXP_SUBJECT }}</td>
          <td>
            <button @click="clickModifyExp(exp)" class="update-btn">수정</button>
            <button @click="clickDeleteExp(exp.EXP_NUMBER)" class="del-btn">삭제</button>
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
  </div>
</template>

<script>
import ExpApi from "@/apis/expAPI";
export default {
  name: "MyexperienceTable",
  props: {
    expDataList: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 3,
    },
  },
  // 시험
  data() {
    return {
      pageNum: 0,
    };
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    clickModifyExp(exp) {
      this.$emit("is-clicked-exp-modify", exp);
    },
    clickDeleteExp(num) {
      console.log(num);
      let data = {
        expNumber: num,
        userId: this.$session.get("loginUserId"),
      };
      console.log(data);
      ExpApi.deleteExp(
        { data },
        res => {
          console.log(res);
          this.$emit("deleteExpData", data);
        },
        err => {
          console.log(err);
        }
      );
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.expDataList.length,
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
      return this.expDataList.slice(start, end);
    },
  },
};
</script>

<style scoped>
.update-btn {
  border-radius: 4px;
  padding: 2px 5px;
  background: #EDBF68;
  margin-right: 10px;
  font-size: 80%;
  color: white;
}
.del-btn {
  border-radius: 4px;
  padding: 2px 5px;
  background: #F78E6D;
  font-size: 80%;
  color: white;

}
.exp-no {
  width: 7%;
}
.exp-date {
  width: 18%;
  overflow: hidden;
}
.exp-working {
  width: 32%;
}
.exp-subject {
  width: 33%;
}
.exp-del-up {
  width: 10%;
}
th,
td {
  color: black;
  padding-top: 10px;
  padding-bottom: 10px;
  border: 1px solid #eee;
  border-collapse: collapse;
}
th {
  background: #ee8572;
}
.exp-table-title {
  padding-left: 8%;
  width: 100%;
  height: 15%;
  text-align: left;
}
.exp-table-title h3 {
  margin-bottom: 10%;
  font-size: 150%;
}
.exp-table {
  height: 50%;
  /* background: teal; */
}
.exp-table-wrap {
  border-collapse: collapse;
  border-bottom: 2px solid #ed7568;
  border-radius: 5px;
  margin: auto;
  width: 1200px;
  background: white;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1), 0px 10px 20px rgba(0, 0, 0, 0.05),
    0px 20px 20px rgba(0, 0, 0, 0.05), 0px 30px 20px rgba(0, 0, 0, 0.05);
  /* height: 80%; */
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>
