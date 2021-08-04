# CSS 설명

[TOC]

### CSS적용 순서

[태그순서]: https://ofcourse.kr/css-course/%EC%A0%81%EC%9A%A9-%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84	"css 적용순서"



### css 레이아웃 강의

[참고]: https://wtss.tistory.com/206	"강의링크"



### bootstrap 추가

```
npm install vue bootstrap-vue bootstrap

main.js에 추가
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
```

[참고]: https://where-i-go.tistory.com/135	"bootstrap-vue적용"



### 가운데 정렬

[참고]: http://pager.kr/x/index.php?mid=board_MBnO39&amp;document_srl=696	" display, float..."



### 더보기란

[참고]: https://stove99.tistory.com/103	"개념설명"
[참고]: https://jedfoster.com/Readmore.js/	"readme.js 설치 "



### 상단바 고정

> header에 추가

``` css
position: fixed;
top: 0;
left: 0;
right: 0;
```



> 글자 겹침 현상 발생

![image-20200726160616230](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200726160616230.png)

[참고]: https://ojji.wayful.com/2014/01/HTML-DIV-Layer-Position-Absolute-Relative-Z-index-Visibility.html	"z-index"

z-index를 통해 순서 설정



### 클래스와 스타일 바인딩

[참고]: https://kr.vuejs.org/v2/guide/class-and-style.html	"클래스와 스타일 바인딩"



 ### issue: body height가 100%가 아닐때

[참고]: https://dev.to/lennythedev/css-gotcha-how-to-fill-page-with-a-div-270j	"body height 해결방법"

```
1vh : 전체 height의 1%를 뜻함
1wh : 전체 width의 1%를 뜻함

전체를 감싸는 body에 100vh, 100vw를 부여
그 다음 안쪽을 감싸는 태그에 height, width를 100%부여
margin과 padding으로 스크롤이 생기기 때문에  
전체(*)에 box-sizing: border-box로 지정
```



### radio button custom

[참고]: https://kr.vuejs.org/v2/guide/forms.html	"v-model을 이용한 value값 바인딩"

>  radio button은 input 태그와 label태그로 이루어져 있어야 하며 
>
> input type="radio", id="one", value="One"이면 label의 for은 id값과 연결해주면됨
>
> v-model은 checked를 통해 어떤 data값이 선택됐는지 알려줌



### 반응형 상단바

[참고]: https://www.youtube.com/watch?v=wlCPOwBr7no	"반응형 상단바 유튜브"

위의 영상을 참고해서 상단바 만듦



### z-index오류 해결

[참고]: https://mytory.net/archives/10997	"z-index는 그냥 우선순위가 아니야"



### flex구조 (굉장히 쉽게 설명)

[참고]: https://studiomeal.com/archives/197	"flex구조 설명한 블로그 그림으로 귀엽게 설명"



### scroll style custom

스크롤 안보이게 하기

```
해당 class에 style적용

.comment-u{
	-ms-overflow-style:none;
}
.comment-ul::-webkit-scrollbar { 
 	display: none;

} 
```

외에 style바꾸는 법

[티스토리]: https://codingbroker.tistory.com/66	"scroll변경"

