<template>
  <div class="row"></div>
</template>
<script>
import DelPopup from "./test2.vue";
export default {
  mounted() {
    this.doc_del_rendar();
  },
  methods: {
    doc_del_rendar() {
      this.$modal.show(
        DelPopup,
        {
          hot_table: "data",
          modal: this.$modal
        },
        {
          name: "dynamic-modal",
          width: "330px",
          height: "130px"
          // draggable: true,
        }
      );
    }
  }
};
</script>
