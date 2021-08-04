<template>
  <div id="algoform">
    <editor
      value="editorText"
      :options="editorOptions"
      :html="editorHtml"
      :visible="editorVisible"
      previewStyle="vertical"
      height="500px"
      mode="WYSIWYG"
      ref="toastuiEditor"
    ></editor>
  </div>
</template>

<script>
import "tui-editor/dist/tui-editor.css";
import "tui-editor/dist/tui-editor-contents.css";
import "codemirror/lib/codemirror.css";
import { Editor } from "@toast-ui/vue-editor";

export default {
  name: "TextEditor",
  data() {
    return {
      editorText: "",
      editorOptions: {
        hideModeSwitch: true,
        minHeight: "500px;",
        language: "ko_Kr",
        userCommandShortcut: true,
        userDefaultHTMLSanitizer: true,
        usageStatistics: true,
        toolbarItems: ["codeblock", "code"],
      },
      editorHtml: "",
      editorVisible: true,
    };
  },
  components: {
    editor: Editor,
  },
  methods: {
    createAction() {
      return this.$refs.toastuiEditor.invoke("getMarkdown"); // content를 저장하는 액션 처리
    },
  },
};
</script>

<style></style>
