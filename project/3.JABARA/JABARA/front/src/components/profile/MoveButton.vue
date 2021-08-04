<template>
  <div class="move-button-div">
    <ul class="move-button-ul">
      <li @click="setExperience">
        <h5>경험정리</h5>
      </li>
      <li @click="setIntroduction">
        <h5>자기소개서</h5>
      </li>
      <li @click="setAlgorithm">
        <h5>알고리즘</h5>
      </li>
      <li @click="setCompany">
        <h5>지원한 회사</h5>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MoveButton",
  props: {
    status: String
  },
  data() {
    return {
      viewStatus: this.status
    };
  },
  methods: {
    setCompany() {
      this.viewStatus = "company";
      this.$emit("view-status", this.viewStatus);
    },
    setIntroduction() {
      this.viewStatus = "introduction";
      this.$emit("view-status", this.viewStatus);
    },
    setExperience() {
      this.viewStatus = "experience";
      this.$emit("view-status", this.viewStatus);
    },
    setAlgorithm() {
      this.viewStatus = "algorithm";
      this.$emit("view-status", this.viewStatus);
    }
  }
};
</script>

<style scoped>
.move-button-div {
  border-bottom: 2px solid rgb(243, 243, 243);
  text-align: center;
  font-size: 150%;
  margin: 0 auto;
  width: 95%;
  /* background: yellow; */
  height: 80px;
}
.move-button-div .move-button-ul {
  width: 100%;
  margin: 0 auto;
  height: inherit;
  margin-left: 5%;
  margin-right: 5%;
}
.move-button-ul > li {
  width: 22.5%;
  float: left;
  height: inherit;
}
.move-button-ul > li:hover {
  border-bottom: 2px solid pink;
  cursor: pointer;
}
.move-button-ul > li:focus {
  border-bottom: 2px solid pink;
}
.move-button-ul > li:active {
  border-bottom: 2px solid pink;
}
.move-button-ul > li:visited {
  border-bottom: 2px solid pink;
}
h5 {
  line-height: 100px;
  font-size: 110%;
}
</style>