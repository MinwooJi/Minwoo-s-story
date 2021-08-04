<template>
  <div class="license-table">
    <h3 class="license-table-title">자격증</h3>
    <table class="license-table-wrap">
      <thead class="license-thead">
        <tr>
          <th class="license-name">자격증명</th>
          <th class="license-number">자격 번호</th>
          <th class="license-date">만료기간</th>
          <th class="license-inst">출제기관</th>
          <th class="license-score">점수,등급</th>
          <th class="license-etc">기타</th>
          <th class="license-del-update">-</th>
        </tr>
      </thead>
      <tbody>
        <!-- <tr v-for="(expTableData, index) in paginatedData" :key="expTableData.index"> -->
        <tr v-for="p in paginatedData" :key="p.LICENSE_NUMBER">
          <td>{{ p.LICENSE_NAME }}</td>
          <td>{{ p.LICENSE_NUMBER }}</td>
          <td>{{ p.LICENSE_DATE }}</td>
          <td>{{ p.LICENSE_INST }}</td>
          <td>{{ p.LICENSE_SCORE }}</td>
          <td>{{ p.LICENSE_ETC }}</td>
          <td>
            <button @click="clickModifyLicense(p)" class="update-btn">수정</button>
            <button @click="clickDeleteLicense(p.LICENSE_NUMBER)" class="del-btn">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </div>
</template>

<script>
import LicenseApi from "@/apis/licenseAPI";
// import { error } from "jquery";
export default {
  name: "MycertiTable",

  props: {
    certiDataList: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 3
    }
  },
  // 시험
  data() {
    return {
      pageNum: 0
    };
  },
  methods: {
    clickModifyLicense(p) {
      this.$emit("is-clicked-modify", p);
    },
    clickDeleteLicense(num) {
      console.log(num);
      let data = {
        licenseNumber: num,
        userId: this.$session.get("loginUserId")
      };
      console.log(data);
      LicenseApi.deleteLicense(
        { data },
        res => {
          console.log(res);
          this.$emit("deleteCertiData", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.certiDataList.length,
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
      return this.certiDataList.slice(start, end);
    }
  }
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
th {
  background: #ee8572;
}
.license-thead {
  height: 15%;
}
.license-name {
  font-size: 2vmin;
  width: 18%;
  font-size: 100%;
  overflow: hidden;
}
.license-number {
  font-size: 2vmin;
  width: 15%;
  font-size: 100%;
  overflow: hidden;
}
.license-date {
  width: 10%;
  font-size: 100%;
}
.license-inst {
  width: 15%;
  font-size: 100%;
  overflow: hidden;
}
.license-score {
  width: 10%;
}
.license-etc {
  width: 8%;
}
.license-del-update {
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

.license-table-title {
  padding-left: 8%;
  width: 100%;
  height: 15%;
  text-align: left;
}
.license-table-title h3 {
  margin-bottom: 10%;
  font-size: 150%;
}
.license-table {
  height: 50%;
  /* background: blue; */
}
.license-table-wrap {
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
