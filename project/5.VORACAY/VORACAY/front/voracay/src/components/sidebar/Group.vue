<template>
  <div class="container_group">
    <div id="groupList">
      <div class="btnArea">
        <a v-if="toggleFlag==true" class="btn btn-dark btn-createToggle" @click="createToggle"> 그룹 만들기 </a>
        <a v-if="toggleFlag==false" class="btn btn-dark btn-createToggle" @click="createToggle"> 그룹 목록 </a>
      </div>
      <div v-if="toggleFlag==true" class="accordion">
          <dl
          v-for="(group, index) in groupList"
          :key="index + 'groupList'"
          >
          <dt><a href="#accordion1" aria-expanded="false" aria-controls="accordion1" class="accordion-title accordionTitle js-accordionTrigger" @click="switchAccordion">{{group.group_name}}</a>{{group.group_desc}}<a>  통화버튼  </a></dt>
          <dd class="accordion-content accordionItem is-collapsed" id="accordion1" aria-hidden="true">
            <div v-for="(member, index) in group.memberList" :key="index + group.memberList">
              <div>
                <label>{{member.user_number.user_name}}</label>
                <button v-if="userName == group.group_manager.user_name && userName != member.user_number.user_name" @click="userBan(group.group_id, member.user_number.user_number)">제외</button>
                <button v-else disabled>제외</button>
              </div>
            </div>
            <div>
              <button class="button button-primary" @click="deleteGroup(group.group_id)">
                <label v-if="userName != group.group_manager.user_name" >그룹 나가기 </label>
                <label v-if="userName == group.group_manager.user_name" >그룹 삭제 </label>
              </button>
            </div>
          </dd>
          </dl>
      </div>
      <div v-if="toggleFlag==false">
        <table class="table table-hover">
        <tr>
          <th> 그룹 이름 </th>
          <td><input v-model="groupSearch" type="text" @keyup.enter="nameCheck()" /></td>
          <td><a class="btn btn-dark" @click="nameCheck()"> 중복 검사 </a></td>
        </tr>
        <tr>
          <th> 그룹 이름 </th>
          <td> {{groupName}}  </td>
        </tr>
        <tr>
          <th> 그룹 설명</th>
          <td><input v-model="groupDesc" type="textarea"/></td>
        </tr>
        </table>
        <div>
          <AddGroup />
        </div>
        <a class="btn btn-dark" @click="createGroup()">그룹 만들기</a>
      </div>
    </div>
  </div>
</template>
<script>
import http from "../../http-common";
import AddGroup from "@/components/meeting/AddUserToMeeting.vue";

export default {
  data() {
    return {
      userName: "",
      toggleFlag: true,
      groupSearch: "",
      groupName: "",
      groupDesc: "",
      groupList:[],
      }
    },
    components:{
      AddGroup,
    },
    created(){
      this.getGroups();
      this.userName = this.$store.state.userName;
    },
    methods: {
        getGroups(){
          http.get("v2/groups/")
          .then((res)=>{
            let content = res.data.content;
            let groups = content.group;
            let members = content.members;
            
            for(let i = 0; i < groups.length; i++){
              groups[i].memberList = [];
              for(let j = 0; j < members.length; j++){
                if(groups[i].group_id == members[j][0].group_id){
                  groups[i].memberList = members[j];
                  break;
                }
              }
            }
            this.groupList = groups;
          })
          .catch((error)=>{

          });
        },
        skipClickDelay(e){
            e.preventDefault();
            e.target.click();
        },
        setAriaAttr (el, ariaType, newProperty) {
            el.setAttribute(ariaType, newProperty);
        },
        setAccordionAria (el1, el2, expanded) {
            switch (expanded) {
            case "true":
                this.setAriaAttr(el1, "aria-expanded", "true");
                this.setAriaAttr(el2, "aria-hidden", "false");
                break;
            case "false":
                this.setAriaAttr(el1, "aria-expanded", "false");
                this.setAriaAttr(el2, "aria-hidden", "true");
                break;
            default:
                break;
            }
        },
        switchAccordion (e) {
            e.preventDefault();
            var thisAnswer = e.target.parentNode.nextElementSibling;
            var thisQuestion = e.target;
            if (thisAnswer.classList.contains("is-collapsed")) {
            this.setAccordionAria(thisQuestion, thisAnswer, "true");
            } else {
            this.setAccordionAria(thisQuestion, thisAnswer, "false");
            }
            thisQuestion.classList.toggle("is-collapsed");
            thisQuestion.classList.toggle("is-expanded");
            thisAnswer.classList.toggle("is-collapsed");
            thisAnswer.classList.toggle("is-expanded");

            thisAnswer.classList.toggle("animateIn");
        },
        createToggle(){
          this.toggleFlag = !this.toggleFlag;
          if(this.toggleFlag) this.getGroups();
        },
        createGroup(){
          if(this.groupName==""){
            this.toast("그룹 이름 중복검사가 필요합니다.", "info");
            return;
          }

          let formData = new FormData();
          let group = {
            "group_name" : this.groupName,
            "group_desc":  this.groupDesc,
          }
          let members = [];
          let userList = this.$store.state.userList;
          for(let i = 0; i < userList.length; i++){
            members.push({
              user_number : userList[i].user_number,
            });
          }

          formData.append('group', group);
          formData.append('members', members);
          http.post("/v2/groups/", {
            group : group,
            members : members,
          })
          .then((res) =>{
            console.log(res);
            this.groupSearch = "";
            this.groupName = "";
            this.groupDesc = "";
            this.createToggle();
          })
          .catch((err) => {
            console.log(err);
          })
        },
        deleteGroup(no){
          http.delete("/v2/groups/" + no, 
          )
          .then((res) => {
            this.getGroups();
          })
          .catch((err)=>{
          })
        },
        nameCheck(){
          let category = "group_name";
          let groupName = this.groupSearch;
          if(groupName == ""){
            this.toast("그룹 이름을 입력한 후 중복 검사해주세요.", "error");
            return;
          }
          http.post("/v2/check/",{
            category: category,
            search: groupName,
          })
          .then((res) => {
            if(res.data.message == "DUPLICATE_GROUP_NAME"){
              this.toast("존재하는 그룹 이름입니다.", "error");
            }else{
              this.groupName = groupName;
              this.toast("중복 검사 완료", "success");
            }
          })
          .catch((err) => {
          });
        },
        userBan(group_id, user_number){
          http.delete("v2/groups/" + group_id,{
            params:{
              user_number:user_number,
            }
          }).then((res)=>{
            this.getGroups();
          }).catch((err)=>{
          })
        },
        toast(msg, type){
          this.$toast.open({
            message: msg,
            type: type,
            position: "top-right",
          });
        },
    },
}
</script>
<style lang="scss" scoped>
//updated ver
* {
  box-sizing:border-box;
}
@import url(https://fonts.googleapis.com/css?family=Lato:400,700);

body { 
  font-family:'Lato';
}

.btnArea{
  position: relative;
  height: 70px;
  z-index: 9999;
}

.btn-createToggle{
  float: right;
}

.heading-primary {
  font-size:2em;
  padding:2em;
  text-align:center;
}
.accordion dl,
.accordion-list {
   border:1px solid #ddd;
   &:after {
       content: "";
       display:block;
       height:1em;
       width:100%;
       background-color:darken(#6643e6, 10%);
     }
}
.accordion dd,
.accordion__panel {
   background-color:#eee;
   font-size:1em;
   line-height:1.5em; 
}
.accordion p {
  padding:1em 2em 1em 2em;
}

.accordion {
    position: relative;;
    background-color:#eee;
}
.container {
  max-width:960px;
  margin:0 auto;
  padding:2em 0 2em 0;
}
.accordionTitle,
.accordion__Heading {
 background-color:#6038cc; 
   text-align:center;
     font-weight:700; 
          padding:2em;
          display:block;
          text-decoration:none;
          color:#fff;
          transition:background-color 0.5s ease-in-out;
  border-bottom:1px solid darken(#1a2585, 5%);
  &:before {
   content: "+";
   font-size:1.5em;
   line-height:0.5em;
   float:left; 
   transition: transform 0.3s ease-in-out;
  }
  &:hover {
    background-color:darken(#8538cc, 10%);
  }
}
.accordionTitleActive, 
.accordionTitle.is-expanded {
   background-color:darken(#9638cc, 10%);
    &:before {
     
      transform:rotate(-225deg);
    }
}
.accordionItem {
    height:auto;
    overflow:hidden; 
    //SHAME: magic number to allow the accordion to animate
    
     max-height:30em;
    transition:max-height 1s;   
 
    
    @media screen and (min-width:48em) {
         max-height:15em;
        transition:max-height 0.5s
        
    }
    
   
}
 
.accordionItem.is-collapsed {
    max-height:0;
}
.no-js .accordionItem.is-collapsed {
  max-height: auto;
}
.animateIn {
     animation: accordionIn 0.45s normal ease-in-out both 1; 
}
.animateOut {
     animation: accordionOut 0.45s alternate ease-in-out both 1;
}
@keyframes accordionIn {
  0% {
    opacity: 0;
    transform:scale(0.9) rotateX(-60deg);
    transform-origin: 50% 0;
  }
  100% {
    opacity:1;
    transform:scale(1);
  }
}

@keyframes accordionOut {
    0% {
       opacity: 1;
       transform:scale(1);
     }
     100% {
          opacity:0;
           transform:scale(0.9) rotateX(-60deg);
       }
}
</style>