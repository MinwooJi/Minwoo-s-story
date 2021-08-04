# JS 설명

## vue에서 jquery설치

```
npm i --save-dev expose-loader
npm i --save jquery

쓸 곳에
import $ from 'jquery';
```

> readmore에서 사용하려 했는데 jquery사용안함

### readmore기능

[참고]: https://www.youtube.com/watch?v=uzSkExQtPkg	"jquery를 이용한  readmore (태그나눔)"
[참고]: https://www.youtube.com/watch?v=uI18xGocVnw	"내가쓴방식-태그 안나눔(hidden)"



### vue에서 class 토글하기

> click 시 is ClickedReadMore에 있는 함수가 작동 되면서 isClicked를 true로 변경
>
> 변경 후 div태그에 showContent라는 class가 생성됨
>
> 클릭할 때마다 class유무 변경됨 

```html
<div v-bind:class="{showContent: isClicked}" class="feed-content">
	<p>{{item.content}}</p>
	<a @click="isClickedReadMore" class="readmore-btn">{{replaceText}}	  </a>
</div>
```





### jquery는 mounted에서만 접근가능

> 뷰의 라이프 사이클은 뷰의 인스턴스가 생성되고 소멸되기까지 과정이다
>
> 라이프 사이클 훅이란 것은 created / beforeMount / mounted가 있다
>
> 제이쿼리의 선택자로 HTML태그를 접근할 수 있는 시점은 mounted단계다

 

[참고]: https://joshua1988.github.io/vue-camp/legacy/jquery-to-vue.html#%EC%A0%9C%EC%9D%B4%EC%BF%BC%EB%A6%AC%EC%99%80-%EB%B7%B0%EB%A5%BC-%EA%B0%99%EC%9D%B4-%EC%93%B8-%EB%95%8C-%EC%A3%BC%EC%9D%98%ED%95%A0-%EC%A0%90	"vue와 jquery연동"

