<template>
  <div id="algo-editor">
    <div class="title">
      <h1>POST</h1>
    </div>

    <div id="fileupload">
      <div v-if="!changeimage" class="file-select">
        <h3>사진을 올려주세요&#128248;</h3>
        <input ref="file" type="file" @change="onFileChange" />
      </div>
      <div v-else >
        <img :src="changeimage" width="599" height="auto" />
        <button class="del-button" @click="removeImage">사진 삭제</button>
      </div>
    </div>

    <div id="text-box">
      <textarea v-model="message" placeholder="내용 입력"></textarea>
    </div>

    <div class="auto-tags">
      <ul>
        <li v-for="(tag,index) in tags" :key="tag" class="tag-list">
          {{tag}}
          <button @click="deleteTag(index)">&#10006;</button>
        </li>
      </ul>
    </div>
    <div class="tag-input">
      <input
        @keyup.space="addNewTag"
        @keyup.enter="addNewTag"
        type="text"
        placeholder="태그 내용"
        v-model="newTag"
      />
    </div>
    <button @click="postSNS" class="post-button">
      <p>Upload</p>
    </button>
  </div>
</template>

<script>
import AWS from "aws-sdk";
import insertPostAPI from "@/apis/insertPostAPI.js";
import InsertTagApi from "@/apis/tagAPI.js";
export default {
  name: "PostEditor",
  components: {},
  data() {
    return {
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48",
      loginUserId: "",
      file: null,
      tags: [],
      newTag: "",
      changeimage: "",
      message: "",
      postNumber: "",
      postImagePath: ""
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
  },
  methods: {
    onFileChange() {
      this.file = this.$refs.file.files[0];
      let reader = new FileReader();
      reader.readAsDataURL(this.file);
      reader.onload = e => {
        console.log(e.target.result);
      };
      this.createImage(this.file);
    },
    createImage(file) {
      // var image = new Image();
      var reader = new FileReader();

      reader.onload = e => {
        this.changeimage = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function() {
      this.changeimage = "";
    },
    postSNS() {
      console.log("postSNS");
      let err = true;
      let msg = "";
      !this.changeimage && ((msg = "포스팅사진을 선택해주세요"), (err = false));
      err && !this.message && ((msg = "내용을 입력해주세요"), (err = false));

      if (!err) {
        alert(msg);
        return;
      }

      let data = {
        postCategory: "SNS",
        postSnsContent: this.message,
        userId: this.loginUserId
      };
      insertPostAPI.insertSNSPost(
        data,
        res => {
          if (res.data != "fail") {
            this.postNumber = res.data + 0;
            console.log(this.postNumber);
            // tag 전송
            let tagStr = "";
            this.tags.forEach(element => {
              tagStr += element;
            });
            let tag = {
              postNumber: res.data + 0,
              tagStr: tagStr
            };
            let data = {
              tag,
              userId: this.loginUserId
            };
            InsertTagApi.insertPostTag(
              data,
              res => {
                if (res.status == 200) {
                  // this.$router.push("/jobara");
                } else {
                  alert("ERROR BY INSERT_ALGOPOST_TAG(서버통신)");
                }
              },
              error => {
                console.log(error);
              }
            );
            this.postImagePath = "snspost" + "//" + this.postNumber;
            this.uploadImage();
          }

          console.log(res);
        },
        error => {
          console.log(error);
        }
      );
    },
    uploadImage() {
      //aws접속하기 위해 이런 것들이 필요
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { Bucket: this.albumBucketName }
      });
      /* postnum이 정해지면 post num을 file명으로 수정*/
      let photoKey = this.postImagePath;

      console.log(photoKey);
      s3.upload(
        {
          Bucket: this.albumBucketName,
          Key: photoKey,
          Body: this.file,
          ContentType: "image/jpeg", //<-- this is what you need!,
          ACL: "public-read"
        },
        (err, data) => {
          if (err) {
            console.log(err);

            return alert(
              "There was an error uploading your photo: ",
              err.message
            );
          }
          console.log(data);
          console.log("aws s3 사진 업로드 완료" + photoKey);
          this.insertImageDB();
        }
      );
    },
    insertImageDB() {
      let data = {
        postNumber: this.postNumber,
        imagePath: this.postImagePath
      };
      insertPostAPI.insertPostImage(
        data,
        res => {
          if (res.data != "fail") {
            console.log("DB 사진 업로드 완료");
          }
          console.log(res);
        },
        error => {
          console.log("DB실패" + this.postImagePath + "|" + this.postNumber);
          console.log(error);
        }
      );
      this.movePage();
    },

    colorChange() {
      console.log("color change");
      this.color = "gold";
    },
    deleteTag(index) {
      this.tags.splice(index, 1);
    },
    addNewTag() {
      let regExp = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/gi;
      if (regExp.test(this.newTag)) {
        this.newTag = "";
        alert("태그는 특수문자를 포함할수없습니다!");
        return;
      }
      this.tags.push("#" + this.newTag);
      this.tags = Array.from(new Set(this.tags));
      this.newTag = "";
    },
    clicked() {
      console.log(this.checked);
    },
    movePage() {
      this.$router.push("/jobara");
    }
  }
};
</script>

<style scoped>

.del-button {
  width: 30%;
  background: #ee8572;
  margin-bottom: 30px;
  padding: 10px 0;
  color:white;
  font-weight: bold;

}
.file-select input{
  background: rgba(230, 230, 230, 0.575);
  border: 0;
  border-radius: 5px;
  padding-top: 10px;
  padding-left: 10%;
  margin-bottom: 20px;
}
#fileupload {
  text-align: center;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
/* .row {
  display: table-row;
} */
.tag-list {
  display: inline;
  margin-right: 15px;
  /* border: 1px solid black; */
  background-color: rgba(243, 255, 131, 0.349);
  border-radius: 5px;
  padding: 4px;
}

.auto-tags {
  margin-top: 15px;
}
.auto-tags p {
  display: inline;
  margin-right: 15px;
}
.solution-form {
  width: 100%;
  display: inline-block;
  margin-bottom: 15px;
}
.input-forms {
  width: 30%;
  border: 2px solid white;
  border-radius: 4px;
}
.create-tag-btn {
  width: 10%;
  border-radius: 4px;
  /* border: 1px solid red; */
  padding: 10px 0px;
  /* background-color: #EDBF68; */
  /* color: white; */
}
.create-tag-btn:hover {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.219);
  transition: box-shadow 400ms;
}
.create-tag-btn:focus {
  border: 2px solid #ddd;
}
.input-forms:hover {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.219);
  transition: box-shadow 400ms;
}
.input-forms:focus {
  border: 2px solid rgba(0, 0, 0, 0.178);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
  transition: box-shadow 400ms;
}
.site-select {
  padding: 14px;
  border-radius: 4px;
}

body {
  background-color: black;
  margin-top: 100px;
}
#algo-editor {
  width: 50%;
  margin: 0 auto;
  margin-bottom: 100px;
  /* margin: 50px 20px; */
  background-color: #fff;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.15);
}
#algo-editor {
  height: 80%;
  margin-top: 130px;
}
#myeditor {
  background-color: #f7b6a459;
}
.title {
  margin-bottom: 40px;

}
h1 {
  text-align: center;
}
.title-input {
  width: 100%;
  margin-bottom: 10px;
  border-radius: 4px;
  border: 2px solid #ddd;
}
.title-input:focus {
  border: 3px solid rgb(251, 231, 253);
  box-shadow: 0 1px 2px rgba(255, 1, 221, 0.15);
  transition: box-shadow 400ms;
}
.radio-group {
  width: 100%;
  margin-bottom: 10px;
}
.clickbox {
  margin: 0 0.25rem;
}
.row {
  display: flex;
  margin: 10px 0;
}
label {
  height: 90px;
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 1rem 1.25rem;
  border-radius: 5px;
  cursor: pointer;
  color: #444;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
  transition: box-shadow 400ms;
  text-align: center;
  flex: 6;
  display: inline-table;
}

label:hover {
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.12);
}
label > p {
  margin: 0.15rem 0;
  display: table-cell;
  vertical-align: middle;
}
input[type="radio"] {
  display: none;
}
input:checked + label {
  background: #ee8572;
  color: white;
}
#text-box {
  margin-bottom: 15px;
}
textarea {
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 100%;
  padding: 30px;
  height: 250px;
}
.tag-input {
  margin-top: 15px;
}
.tag-input > input {
  border-radius: 4px;
  border: 2px solid #ddd;
  width: 100%;
}
.post-button {
  border-radius: 4px;
  margin-top: 15px;
  width: 50%;
  font-size: 30px;
  position: relative;
  left: 50%;
  transform: translate(-50%, 0%);
  background-color: #ee8572;
}
.post-button p {
  text-align: center;
  margin: auto 0;
  font-weight: 600;
  color: white;
}
</style>
