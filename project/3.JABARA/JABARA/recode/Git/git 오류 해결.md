# git 오류 해결

### git 기본설명

https://jeonghwan-kim.github.io/dev/2020/02/10/git-usage.html



## refusing to merge unrelated histories 오류

```bash
git pull origin 브런치명 --allow-unrelated-histories
명령어 입력
```

> --allow-unrelated-histories  이 명령 옵션은 이미 존재하는 두 프로젝트의 기록(history)을 저장하는 드문 상황에 사용된다고 한다. 즉, git에서는 서로 관련 기록이 없는 이질적인 두 프로젝트를 병합할 때 기본적으로 거부하는데, 이것을 허용해 주는 것이다.

[참고]: https://gdtbgl93.tistory.com/63



## pull이 안될 때

![image-20200724103322715](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200724103322715.png)



>  빈 폴더의 branch push 후 다른 브랜치 pull 해오는데 오류 발생

#### 해결방법

1. 빈 폴더를 push해서 remote에 반영이 안된 상황이기 때문에 branch에 파일 하나 생성 후 push한 후 pull해오기

2. git 에서 branch만들고 local에서 추적하기

   [참고]: https://cjh5414.github.io/get-git-remote-branch/	" git branch 추적하는 법"

   

![image-20200724103531742](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200724103531742.png)

![image-20200724103537218](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200724103537218.png)



