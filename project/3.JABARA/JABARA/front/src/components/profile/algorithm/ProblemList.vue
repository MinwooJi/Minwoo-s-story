  <template>
  <div class="problem-list-body">
    <div class="mdl-card mdl-shadow--2dp demo-card-square">
      <div class="mdl-card__title mdl-card--expand">
        <h2 class="mdl-card__title-text">푼 문제</h2>
      </div>
      <div class="mdl-card__supporting-text">
        <span v-for="(problem,index) in solvedList" :key="index">{{problem}},</span>
      </div>
    </div>
    <!-- Square card -->
    <div class="mdl-card mdl-shadow--2dp demo-card-square">
      <div class="mdl-card__title mdl-card__accent mdl-card--expand">
        <h2 class="mdl-card__title-text">못 푼문제</h2>
      </div>
      <div class="mdl-card__supporting-text">
        <span v-for="(problem,index) in unSolvedList" :key="index">{{problem}},</span>
      </div>
    </div>
  </div>
</template>
<script>
import GetPostsApi from "@/apis/postAPI";
export default {
  name: "ProblemList",
  data() {
    return {
      solvedList: [],
      unSolvedList: []
    };
  },
  created() {
    GetPostsApi.getAlgoSolved(
      this.$session.get("loginUserId"),
      res => {
        this.solvedList = res.data;
        console.log(this.solvedList);
      },
      error => {
        console.log(error);
      }
    );
    GetPostsApi.getAlgoUnsolved(
      this.$session.get("loginUserId"),
      res => {
        this.unSolvedList = res.data;
        console.log(this.unSolvedList);
      },
      error => {
        console.log(error);
      }
    );
  }
};
</script>
<style scoped>
.demo-card-square{
  width:40%;
}
.demo-card-square.mdl-card {
  width: 100%;
  height: 20%;
  float: left;
  margin: 1rem;
  position: relative;
  scrollbar-width: none; /* Firefox */
  overflow-x: hidden;
  overflow-y: auto;
}

.demo-card-square.mdl-card:hover {
  box-shadow: 0 8px 10px 1px rgba(0, 0, 0, 0.14),
    0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
}

.demo-card-square > .mdl-card__title {
  color: #fff;
  /* background: #f88782; */
  background: linear-gradient(120deg,#ff928d, #fde5e4);
  width: 80%;
  margin-top: 5%;
}

.demo-card-square > .mdl-card__accent {
  /* background: #f9d297; */
  background: linear-gradient(120deg,#ffc46a, #ffe8c5);
  width:80%;
}
.mdl-card__supporting-text{
  width: 80%;
}
problem-list-body {
  padding: 20px;
  background: #fafafa;
  position: relative;
}
</style>