<template>
  <div id="profile-wrap">
    <div class="three-wrap">
      <Myinfo />
      <MoveButton :status="status" @view-status="receiveData" />
      <div class="my-company" v-if="this.status === 'company'">
        <Mycompany />
      </div>
      <div class="my-introduction" v-if="this.status === 'introduction'">
        <Myintroduction />
      </div>
      <div class="my-experience" v-if="this.status === 'experience'">
        <Myexperience />
      </div>
      <div class="my-algorithm" v-if="this.status === 'algorithm'">
        <Myalgorithm />
      </div>
    </div>
  </div>
</template>

<script>
import Myinfo from "@/components/profile/Myinfo.vue";
import MoveButton from "@/components/profile/MoveButton.vue";
import Mycompany from "@/components/profile/company/MyCompany.vue";
import Myintroduction from "@/components/profile/introduction/Myintroduction.vue";
import Myexperience from "@/components/profile/experience/Myexperience.vue";
import Myalgorithm from "@/components/profile/algorithm/Myalgorithm.vue";
// import { mapMutations } from "vuex";
export default {
  name: "Myprofile",
  components: {
    Myinfo,
    MoveButton,
    Mycompany,
    Myintroduction,
    Myexperience,
    Myalgorithm
  },
  data() {
    // return {
    //   status: "company"
    // };
    return {
      status: this.$store.state.viewStatus
    };
  },
  methods: {
    // ...mapMutations(["getViewStatus", "setViewStatus"]),
    receiveData(viewStatus) {
      this.status = viewStatus;
    }
  }
};
</script>

<style scped>
#profile-wrap {
  width: 95%;
  margin: 0 auto;
  /* background: #ef3423; */
  /* clear: both; */
}
.three-wrap {
  margin-top: 120px;
}
</style>