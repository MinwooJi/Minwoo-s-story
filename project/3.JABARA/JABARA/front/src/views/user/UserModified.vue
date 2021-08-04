<template>
  <div class="user join wrapC">
    <h1 style="text-align:center">회원 정보 수정</h1>
    <div class="top-wrap">
      <div class="hjprofile-image">
        <!-- <img style="width:100%; height:100%; background-color:red;" /> -->
        <img
          :src="'https://jobara.s3.ap-northeast-2.amazonaws.com/' + profileimage"
          onerror="this.src='https://jobara.s3.ap-northeast-2.amazonaws.com/jobaraprofile//user.png'"
          alt="프로필사진"
          width="100%"
          height="100%"
        />
      </div>
      <div class="button-wrap">
        <router-link tag="button" class="hjbtn hjprofile-edit-btn" to="/user/userimage">Edit Profile</router-link>
      </div>
    </div>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="loginUserId" id="loginUserId" type="text" readonly />
        <label for="loginUserId">ID</label>
      </div>
      <div class="input-with-label">
        <input v-model="userName" id="userName" placeholder="이름" type="text" />
        <label for="userName">이름</label>
      </div>
      <div class="input-with-label">
        <input v-model="userNickname" id="userNickname" placeholder="새 닉네임 입력" type="text" />
        <label for="userNickname">닉네임</label>
      </div>
      <div class="input-with-label">
        <input v-model="userPw1" id="userPw1" placeholder="새 비밀번호 입력" type="password" />
        <label for="userPw1">비밀번호</label>
      </div>
      <div class="input-with-label">
        <input v-model="userPw2" id="userPw2" placeholder="비밀번호를 다시한번 입력하세요." type="password" />
        <label for="userPw2">비밀번호 확인</label>
      </div>

      <div class="input-with-label">
        <input v-model="userEmail" id="userEmail" placeholder="E-mail 입력" type="text" />
        <label for="userEmail">E-mail</label>
      </div>
      <div class="input-with-label">
        <input v-model="userGiturl" id="userGiturl" placeholder="Git URL" type="text" />
        <label for="userGiturl">Git URL</label>
      </div>
    </div>
    <button @click="modified" class="modify-button">수정하기</button>
  </div>
</template>

<script>
import UserApi from "../../apis/userAPI";
export default {
  name: "Modified",
  data() {
    return {
      loginUserId: "",
      userPw: "",
      userPw1: "",
      userPw2: "",
      userName: "",
      userNickname: "",
      userEmail: "",
      userGiturl: "",
      profileimage: "",
      imagechange: ""
    };
  },
  created: function() {
    this.loginUserId = this.$session.get("loginUserId");
    this.profileimage = this.$session.get("loginUserProfileImage");
    UserApi.requestDetail(
      this.loginUserId,
      res => {
        console.log(res);
        this.userPw1 = res.data.userPw;
        this.userName = res.data.userName;
        this.userNickname = res.data.userNickname;
        this.userEmail = res.data.userEmail;
        this.userGiturl = res.data.userGiturl;
      },
      error => {
        console.log(error);
      }
    );
  },
  methods: {
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;

      let reader = new FileReader();
      reader.readAsDataURL(files[0]);
      reader.onload = e => {
        console.log(e.target.result);
      };
      console.log(files);
      this.createImage(files[0]);
    },
    createImage(file) {
      // var image = new Image();
      var reader = new FileReader();

      reader.onload = e => {
        this.profileimage = e.target.result;
        this.imagechange = "change";
      };
      reader.readAsDataURL(file);
    },
    removeImage: function() {
      this.profileimage = this.$session.get("loginUserProfileImage");
      this.image = "";
    },
    modifiedImage() {
      // 공란 확인후 알림메시지 출력할것.
      let err = true;
      let msg = "";
      console.log("수정");
      !this.image && ((msg = "프로필 사진을 선택해주세요"), (err = false));
      if (!err) {
        alert(msg);
        return;
      }
      const formData = new FormData();
      formData.append("userimage", this.profileimage);
      formData.append("userId", this.loginUserId);

      // formData data = this.profileimage;
      // let data = {
      //   userId: this.loginUserId
      //   // userimage: this.profileimage
      // };
      console.log("수정2");
      UserApi.requestImage(
        formData,
        res => {
          if (res.data == "success") {
            console.log("수정3");
            alert("프로필 이미지 수정 성공");
            this.$session.set("loginUserProfileImage", this.profileimage);
            this.movePage();
          } else {
            alert("프로필 이미지 수정 실패");
          }
        },
        error => {
          console.log(error);
        }
      );
    },
    modified() {
      // 공란 확인후 알림메시지 출력할것.

      let err = true;
      let msg = "";
      !this.userNickname && ((msg = "닉네임을 입력해주세요"), (err = false));
      err &&
        !this.userPw1 &&
        ((msg = "비밀번호를 입력해주세요"), (err = false));
      err &&
        !this.userPw2 &&
        ((msg = "비밀번호(확인)을 입력해주세요"), (err = false));
      err && !this.userName && ((msg = "이름을 입력해주세요"), (err = false));
      err &&
        !this.userEmail &&
        ((msg = "이메일를 입력해주세요"), (err = false));
      err &&
        !this.userGiturl &&
        ((msg = "Git URL을 입력해주세요"), (err = false));
      if (!err) {
        alert(msg);
        return;
      }

      if (this.userPw1 == this.userPw2) {
        let data = {
          userId: this.loginUserId,
          userPw: this.userPw1,
          userName: this.userName,
          userNickname: this.userNickname,
          userEmail: this.userEmail,
          userGiturl: this.userGiturl
        };
        UserApi.requestModified(
          data,
          res => {
            if (res.data == "success") {
              this.$session.set("loginUserNickname", this.userNickname);
              alert("회원정보 수정 성공");
              this.movePage();
            } else {
              alert("회원정보 수정 실패");
            }
          },
          error => {
            console.log(error);
          }
        );
      } else {
        alert("비밀번호를 확인하세요.");
      }
    },
    movePage() {
      this.$router.push("/jobara");
    }
  }
};
</script>
<style scoped>
.wrapC {
  margin-top: 100px;
  height: 100vh;
}
.top-wrap {
  padding: 5% 5% 7% 10%;
  height: 30%;
}
.button-wrap {
  height: 100%;
  width: 50%;
  float: left;
  /* width: calc(33.333% - 1rem); */
  position: relative;
}
.button-wrap button {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-40%, -50%);
}
.hjbtn {
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 0;
  cursor: pointer;
}

.hjbtn:focus {
  outline: 0.5rem auto #4d90fe;
}

.hjprofile-edit-btn {
  font-size: 1.4rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 2rem;
}

.hjprofile-settings-btn {
  font-size: 2rem;
  margin-left: 1rem;
}

.user {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.label-text {
  margin-bottom: 50px;
}
.button-block {
  display: block;
  margin-bottom: 100px;
}
.hjprofile-image {
  float: left;
  width: calc(33.333% - 1rem);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 0rem;
  margin-left: 1rem;
  width: 180px;
  height: 180px;
}

.hjprofile-image img {
  float: right;
  margin-right: 0;
  border-radius: 50%;
}

.hjbtn {
  -webkit-transition: all 800ms cubic-bezier(0.39, 0.5, 0.15, 1.36);
  -moz-transition: all 800ms cubic-bezier(0.39, 0.5, 0.15, 1.36);
  -ms-transition: all 800ms cubic-bezier(0.39, 0.5, 0.15, 1.36);
  -o-transition: all 800ms cubic-bezier(0.39, 0.5, 0.15, 1.36);
  transition: all 200ms cubic-bezier(0.39, 0.5, 0.15, 1.36);
  display: block;
  margin: 20px auto;
  max-width: 180px;
  text-decoration: none;
  border-radius: 5px;
  padding: 0 1.3rem;
}

.hjbtn.hjprofile-edit-btn {
  color: rgba(30, 22, 54, 0.6);
  box-shadow: rgba(255, 186, 207, 0.863) 0 0px 0px 0px inset;
}

.hjbtn.hjprofile-edit-btn:hover {
  color: rgba(255, 255, 255, 0.932);
  box-shadow: rgba(255, 131, 127, 0.726) 0 0px 0px 40px inset;
}

.modify-button {
  display: block;
  border-radius: 5px;
  width: 100%;
  height: 50px;
  background: radial-gradient(
      circle at 30% 0,
      rgba(255, 140, 94, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 197, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  /* margin-bottom: 100px; */
}
.modify-button {
  margin-bottom: 10%;
  border-radius: 5px;
  width: 100%;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 94, 156, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 59% 0,
      rgba(255, 242, 185, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 142, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  color: #fff;
  border: none;
  position: relative;
  height: 50px;
  font-size: 100%;
  font-weight: 500;
  padding: 0 2em;
  cursor: pointer;
  transition: 300ms ease all;
  outline: none;
}
.modify-button:hover {
  background: #fff;
  color: #fd9e45;
}
.modify-button:before,
.modify-button:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: radial-gradient(
      circle at 50% 0,
      rgba(255, 94, 156, 0.5),
      rgba(255, 0, 0, 0) 70.71%
    ),
    radial-gradient(
      circle at 6.7% 75%,
      rgba(255, 197, 89, 0.5),
      rgba(0, 0, 255, 0) 70.71%
    ),
    radial-gradient(
        circle at 93.3% 75%,
        rgba(245, 14, 52, 0.5),
        rgba(0, 255, 0, 0) 70.71%
      )
      beige;
  transition: 300ms ease all;
}
.modify-button:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
.modify-button:hover:before,
.modify-button:hover:after {
  width: 100%;
  transition: 500ms ease all;
}
</style>
