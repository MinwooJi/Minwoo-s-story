<template>
  <div class="card-container pop-scroll">
    <div v-for="data in dataList" :key="data.MY_APPLY_NUMBER">
      <div class="flip">
        <div class="front" :style="image[data.MY_APPLY_NUMBER%12]">
          <h1 class="text-shadow">{{data.COMPANY_NAME}}</h1>
        </div>
        <div class="back" @click="popModal">
          <h2 style="text-align:center; margin-top: 20px;">{{data.COMPANY_NAME}}</h2>
          <div style="text-align:center;">
            <a :href="data.COMPANY_URL" style="text-align:center; margin-top: 30px;" :title="data.COMPANY_URL">{{data.COMPANY_URL}}</a>
          </div>
          <!-- <p>{{data.COMPANY_IMAGE}}</p> -->
          <!-- <img :src="data.COMPANY_IMAGE" alt="이미지" /> -->
        </div>
        <div class="back-btn-container">
          <button @click="deleteCard(data.MY_APPLY_NUMBER)" class="back-btn">
            <i style="color:tomato;" class="xi-close xi-2x"></i>
          </button>
        </div>
      </div>
      <div class="card-modal" v-if="show">
        <MyCardModal :show="show" :data="data" @modal-show="popModal"/>
      </div>
    </div>
  </div>
</template>
<script>
import MyApplyApi from "@/apis/myProfileApplyAPI";
import AWS from "aws-sdk";
import MyCardModal from "./MyCardModal.vue"
// import { error } from "jquery";
export default {
  data() {
    return {
      show:false,
      albumBucketName: "jobara",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:da2a969d-1c3a-4a24-8d65-81874b126d48",
      image: [
        "background-image: url(https://images.unsplash.com/photo-1554462307-f6296d218bb7?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80)",
        "background-image: url(https://images.unsplash.com/photo-1592782080385-f631279519eb?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60)",
        "background-image: url(https://cdn.univ20.com/wp-content/uploads/2019/01/9266cc9cd7dde8fd8a6d8439d21afedd.png)",
        "background-image: url(https://images.unsplash.com/31/8jLdwLg6TLKIQfJcZgDb_Freedom_5.jpg?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1061&q=80)",
        "background-image: url(https://images.unsplash.com/photo-1596491690105-2916ddc03050?ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80)",
        "background-image: url(https://cdn.pixabay.com/photo/2016/12/08/21/32/skyscraper-1893215_960_720.jpg)",
        "background-image: url(https://cdn.pixabay.com/photo/2019/01/26/11/18/chicago-3956018_960_720.jpg)",
        "background-image: url(https://images.unsplash.com/photo-1493966936727-e9e3da7326ea?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80)",
        "background-image: url(https://pds.joins.com/news/component/htmlphoto_mmdata/201810/17/c9afe9db-2231-4125-8a65-123eec974a08.jpg)",
        "background-image: url(https://s3-ap-northeast-2.amazonaws.com/mobiinsidecontent/kr/wp-content/uploads/2020/06/12160643/GettyImages-514799381-scaled.jpg)",
        "background-image: url(https://d2ilb6aov9ebgm.cloudfront.net/2020-03-02T19.32.59.869_818.jpg)",
        "background-image: url(https://www.kimyoubi.com/wp-content/uploads/2018/08/%E1%84%92%E1%85%AC%E1%84%89%E1%85%A1%E1%84%8B%E1%85%B4-%E1%84%8C%E1%85%A5%E1%86%B8%E1%84%83%E1%85%A2-%E1%84%86%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AA%E1%84%80%E1%85%A1-%E1%84%89%E1%85%B5%E1%86%B6%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%B5%E1%84%83%E1%85%A1-1280x640.jpg)"
      ]
    };
  },
  components: {
    MyCardModal,
  },
  props: {
    dataList: {
      type: Array
    }
  },
  methods: {
    popModal() {
      this.show = !this.show
    },
    deleteCard(MY_APPLY_NUMBER) {
      MyApplyApi.requestDeleteMyApply(
        MY_APPLY_NUMBER,
        res => {
          console.log(res);
          let postImagePath =
            "myhireinfo//" + this.$session.get("loginUserId") + MY_APPLY_NUMBER;
          this.deleteFile(postImagePath);
          this.$emit("deleteNewMyApply", MY_APPLY_NUMBER);
        },
        error => {
          console.log(error);
        }
      );
    },
    deleteFile(key) {
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
      console.log(key);
      s3.deleteObject({ Key: key }, (err, data) => {
        if (err) {
          return alert("There was an error deleting your photo: ", err.message);
        }
        console.log("삭제");
        console.log(key);
        console.log(data);
        alert("Successfully deleted photo.");
      });
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/assets/scss/card.scss";
</style>
<style lang="css" scoped>
.flip {
  word-wrap: break-word;
  word-break: break-all;

}
.flip:hover {
  cursor: pointer;
}
.pop-scroll {
  width: 100%;
  height: 90%;
  overflow-x: hidden;
  overflow-y: auto;
  -ms-overflow-style: none;
}
.pop-scroll::-webkit-scrollbar {
  display: none;
}
.card-container {
  margin-top: 5%;
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  height: 90%;
  display: flex;
  flex-wrap: wrap;
}
.back-btn-container {
  width: 100%;
  text-align: center;
  margin-top: 50px;
  /* position: sticky; */
  /* height: 50px; */
  /* float: center; */
  /* position: absolute; */
  /* bottom: 0px;
  right: 0px; */
}
/* .back-btn {
  width: 80%;
  height: 50%;
  display: inline-block;
  font: inherit;
  background: none;
  color: inherit;
  padding: 5px 20px;
  cursor: pointer;
  color: rgb(175, 164, 164);
  text-align: center;
  border: 2px solid white;

}
.back-btn:hover {
  color: #fff;
}
.back-btn {
	-webkit-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-moz-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-ms-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	-o-transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	transition: all 400ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	display: block;
	margin: 20px auto;
	max-width: 180px;
	text-decoration: none;
	padding: 10px;
}

.back-btn {
	color: rgb(175, 164, 164);
	box-shadow: rgba(255, 255, 255, 0.726) 0 0px 0px 0px inset;
  border: 1px solid rgba(255, 255, 255, 0.527);
}

.back-btn:hover {
	color: rgba(14, 14, 14, 0.932);
	box-shadow: rgba(255, 255, 255, 0.726) 0 0px 0px 40px inset;
} */
.back {
  position: relative;
  padding: 20px;
  height: 100%;
  width: 100%;
}

.back-btn {
  position: absolute;
  top: 3%;
  right: 3%;

}
.back-btn-container {
  width: 40px;
  height: 40px;
}
</style>