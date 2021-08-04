[TOC]



## Vue 설명

#### 주요 명령어

```
$ npm i
-git에 올리기 위해 node-modules 삭제 후 node-modules를 다시 생성하기 위한 명령어

$npm install -g @vue/cli
-vue 설치

$vue create <프로젝트명>
-vue 프로젝트 생성

$npm run serve 
-vue 서버 실행하기

$vue add router
-url 변동 할 수 있도록 실행 (vue create후 바로 실행하는것 추천)

$npm i axios
-axios설치 (axios사용할 파일에 import axios from 'axios'작성하기)
✔axios는 JSON형식 파일 사용할 때 씀

$yarn global add @vue/cli
-vue에 yarn 설정
```



## 기본적인 문법

````
😋1. data - function, return형식으로 작성
data () {
	return {
		... 
	}
}

😋2. methods - 이벤트 함수 작성
methods: {
	함수이름() {
		...
	}
}

😋3. data값이 [{name:..., id:1},{name:..., id:2},...] 인자가 여러개인 경우
data() {
	return {
		posts: [{name:..., id:1},{name:..., id:2},...]
	}
}
✔<template>에서 v-for문 사용할 때 주의
<p v-for="post in posts" :key="post.id"> 로 입력해야 결과물 출력됨

😋4. url에 가변값을 주고 싶을 때 
* {path: 'hello/:name'...}

````



## 기본적인 개념

```
😋1. el == $mount
el과 $mount는 목적이 동일 클래스와 mount(연결)하는 것이 목적
ex) el: #app == $mount('#app')

😋2. SFC와 SPA
*SFC : Single Flie Component -> vue에서 components폴더에 있는 파일 하나하나를 일컫음
*SPA : Single Page Application -> SFC가 APP에 모여 등록되어 여러개의 컴포넌트가 하나의 페이지처럼 보임(새로고침x)

😋3. vue의 장점 : 새로고침이 없다!
*vue-router를 실행해야만 url이 새로고침없이, component가 변경되고, v-if나 v-show따위로 component를 변경하는 일이 없음

😋4. router대신 <a>?
-router대신 a태그를 사용하게 되면 새로고침이 일어남 -> 그럼 vue사용하는게 무의미

😋5. ping이 views폴더 안의 파일, pong이 components안의 파일일 때 => ping에서 작성한 값을 pong으로 보내고 싶을 때

ping파일 methods에 this.$router.push({name:'Pong', query: {message:this.보낼값}})
pong파일 data에 messageFromPong: this.#route.query.message 작성

=> F12 vue를 보고 확인 할 수 있음
```





## App.vue

* router-link와 router-viewer로 이루어짐
* router-viewer가 내용이 보여지는 부분
* views폴더에 있는 파일들과 연결 되어있음
* App.vue에 router표시 ->  index.js에 router부분 설정 -> views > Home.vue에 코드작성



## router > index.js

* path,name,component들로 url등록 하고 import
* url에 가변값을 주고 싶을 때 : {path: 'hello/:name'...}



## Views폴더 안의 파일들

* url로 연동될 파일들이 들어있어야함
* 위 파일들은 components들과 연결되어있음
* import/등록/사용으로 components연결



## Components폴더 안의 파일들

* views폴더 안의 파일들과 연동됨(하위파일)
* 파일 작성 시 하위폴더에서 상위폴더 흐름 순으로 작성하기



## youtube 페이지 만들기

### 단방향 데이터

#### props와 emit

* props는 부모에서 자식으로 데이터를 내려주는 것

* emit은 자식에서 일어나는 이벤트 부모로 올려주는 것

  ```
  *props*
  1. 부모 파일에서 보내줄 데이터 명 default값 지정 
  <자식파일명 :data이름(자식파일에 쓸것)="data이름(부모 파일에 쓰인 것)"/> => 둘이 같게 설정하는게 좋은 코드
  2. 자식 파일에서 props를 사용해 data 형태 받음
  props: {
  	data명: Array(데이터 형식),
  }
  3. 자식 파일에서 data표시 {{}}
  
  *emit*
  1. 자식 파일에서 이벤트 생성 시 쓸 함수 지정(emit사용)
  ex) 클릭 시 input-changed라는 이벤트 커스텀 명으로 event.target.value값을 담아 부모 파일로 올리겠다
  
  onInput(event) {
              this.$emit('input-changed', event.target.value) 
  	}
       
  2. 부모 파일에서 emit의 외침을 @를 통해 듣는다
  ex) <SearchBar @input-changed="inputChange"/>
  
  3. 외침을 들었을 때 사용할 함수 부모 파일에서 정의
  methods: {
      inputChange(inputText(보내온 data값 받을 변수)) {
        this.inputValue=inputText(보내온 data값 받을 변수)
    }
  ```



### 유튜브 api받기

> 참고 사이트
>
> google developer console : https://console.developers.google.com/apis/dashboard?hl=ko&project=project-prepare-0716
>
> youtube developer(search로 사용할 url사이트): https://developers.google.com/youtube/v3/docs/search/list 



#### api 사용 과정

```
youtube developer -> Refer -> Search -> list

google developer console -> 새 프로젝트 만들기 -> 프로젝트 -> 대시보드 -> 시작하기 ->  사용자 인증정보 -> 키생성 -> 인증정보 키 저장
```

> 사용코드

```
import axios from 'axios'

const API_KEY='AIzaSyB7mjD99dHAPdFlyTmSSIE6X9qdUx_SrsA'
const API_URL='https://www.googleapis.com/youtube/v3/search'

...

etag가 유일무이한 키를 뜻한다 

 methods: {
    inputChange(inputText) {
      // console.log(inputText)
      this.inputValue=inputText
      //axios 함수안에 들어가 같이 실행되야 함
      axios.get(API_URL, {
        params: {
          key: API_KEY,
          part: 'snippet',
          type: 'video',
          q: this.inputValue
        }  
      })
      .then(res => 
        // console.log(res.data)
        this.videos=res.data.items
      )
      .catch(err => console.log(err))
    },


<ul>
<!-- etag가 유일무이한 키 -->
<li v-for="video in videos" :key="video.etag">{{video.snippet.description}}</li>
</ul>
```



### vue session 사용

참고자료 : https://sjwiq200.tistory.com/37



### Vue Cookie

>  vue cookies 사이트 참고

```
1. npm i vue-cookies 설치

2. main.js에 코드 작성
	import VueCookies from 'vue-cookies'
	
	Vue.use(VueCookies)
	
3. App.vue에 아래와 같이 코드 작성
	'https://localhost:8000...'은 post맨에서 json으로 login data 받는 주소적은 것
	loginData는 LoginView에서 작성한 login에 대한 정보(id, password) Data값이 들어간 것
	isLoggedIn을 통해 flag처럼 key값 받으면 로그인 됐다는 것 표시
	
4. https://localhost... 공통 url은 const SERVE_URL로 따로 저장 
   logout부분 참고하기(복잡)
   
```

> loginData에 대한 코드

![image-20200722013921255](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722013921255.png)

> App.vue 코드

![image-20200722013339409](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722013339409.png)

성공했을 때 Home으로 보내주기

![image-20200722014116194](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722014116194.png)

> logout 부분 :  cookies remove사용
>
> res 밑에서 쓰지 않기 때문에 ()안에 res안씀
>
> headers에 key값을 잡아서 넣어 놓고 post방식으로 보낼 때 key값 넣어서 cookie삭제(db에서 확인)
>
> axios.post(URL, BODY, HEADER )순이어서 가운데 null값 삽입

![image-20200722015408419](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722015408419.png)

> vue life cycle의 mounted는 index.js에서 div부분으로 들어갔을 때
>
> 그 때에 실행할 함수들 넣기
>
> 여기서는 키가 있고 없고에 따라 login/logout이 보여지는지를 확인 

![image-20200722020415310](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722020415310.png)

![image-20200722020440311](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200722020440311.png)



### v-for 상세설명

[참고]: https://beomy.tistory.com/52	"v-for잘 설명되어있는 블로그"



### vue v-model을 이용한 data 받기

[참고사이트]: https://kr.vuejs.org/v2/guide/forms.html	"vue v-model을 이용해 data받기"



### 클래스와 스타일 바인딩

[참고]: https://kr.vuejs.org/v2/guide/class-and-style.html	"클래스와 스타일 바인딩"

```
v-bind:class="{ active: isActive } 를 통해 class 넣었다 뺐다 할 수 있음

```



### 해당 index의 value를 삭제하고 싶은 경우 splice사용하기

[https://rintiantta.github.io/jpub-vue/guide/chapter2.html#%ED%81%B4%EB%A6%AD%EC%9C%BC%EB%A1%9C-%EC%B9%B4%EC%9A%B4%ED%84%B0%EC%9D%98-%EC%88%98-%EB%8A%98%EB%A6%AC%EA%B8%B0](https://rintiantta.github.io/jpub-vue/guide/chapter2.html#클릭으로-카운터의-수-늘리기)





### 백엔드와의 통신 중 delete

> 스웨거에 delete로 되어있으면 data를 객체 안으로 넣어서 보내야함

![image-20200808184250451](C:\Users\multicampus\Desktop\SSAFY 2학기\첫번째 프로젝트\기술 설명\Vue 설명 - image\image-20200808184250451.png)



## select option for loop

[블로그]: https://beomy.tistory.com/54	"option for문돌리는법"



## insert해서 success했을 때 함수 실행하기

```script
submitIinfo() {
      let data= {
        resumNumber: this.resumNumber,
        resumePass: this.resumePass,
        resumeDate: this.resumeDate,
        companyName: this.companyName,
        userId: this.$session.get("loginUserId")
      }
      console.log(data);
      IntroductionApi.requestInsertIntroduction(
        data,
        this.testCallBack,
        error => {
          console.log(error);
        }
      );



 testCallBack(res){
      if (res.status == 200) {
        this.btnDisabled = true
        this.addresumeQuestion();
        console.log("콜백끝")
      }
    },
```

callback함수 따로 지정해주기

그래야 callback함수 안에 있는 것 실행됨

callback함수로 안해주니까 실행이 안됐음

[콜백지옥]: https://joshua1988.github.io/web-development/javascript/javascript-asynchronous-operation/	"콜백함수 정리"



#### confirm창 사용하기

```
cAlert(number) {
	//yes누르면 true cancle은 false
    if (confirm("댓글을 삭제하시겠습니까?") ) {
    	this.deleteOneComment(number);
    }
},
```

