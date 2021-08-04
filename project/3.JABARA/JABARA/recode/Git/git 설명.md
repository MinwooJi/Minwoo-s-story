# git 정리

## ❌주의점

1.  .git폴더는 여러개의 폴더가 있을 때 바깥에 존재하면 안됩니다 가장 안쪽에 1폴더 1 .git폴더가 존재해야 합니다

   ```
   ㄴpractice (여러 프로젝트들 저장해놓은 폴더)
   	ㄴ❌.git? => 바깥에 .git폴더 존재하면 안됩니다
   	ㄴaaaaa
   	ㄴbbbb
   		ㄴbb.txt
   		ㄴ[올리고 싶은 프로젝트 폴더]
   			ㄴ✔.git => 가장 안쪽에 위치해야 합니다
   			ㄴreadme.md
   			ㄴaa.txt
   			ㄴ부가적인 폴더들 허용가능! 
   ```

2. 1프로젝트 1git을 권장합니다

    ```
   practice
   	ㄴ프로젝트 모음
   		ㄴ ❌.git
   		ㄴ 올리고싶은 프로젝트1
   		ㄴ 올리고 싶은 프로젝트2
   	ㄴ222222
   		ㄴ✔.git
   		ㄴ올리고 싶은 프로젝트 프로젝트 1개
   	ㄴ333333
   		ㄴ✔.git
   		ㄴ올리고 싶은 프로젝트 프로젝트 1개		
   ```

   





## 개인

> 처음 git repository 생성 후 

 git에 올릴 폴더위치에서 git bash열기



### 순서

* Git global set up
* setting하기 위해 필요함

```bash
$git config --global user.name "유저네임"
$git config --global user.email "이메일"
```



* existing folder

```bash
#현재 위치에.git폴더 생성(숨김파일 확인)  
$git init

#git repository와 연동 => clone > HTTPS 주소 복붙(git bash에서 붙여넣기 shift+insert) 
$git remote add origin https://lab.ssafy.com/s03-webmobile2-sub1/s03p11d222.git

#.git 폴더에 현재 위치에 있는 파일들 추가하겠다(github나 gitlab에 올라간게 아님)
$git add .

#.git 폴더에 message남기기
$git commit -m "Initial commit"

#.git 연동되어 있는지 확인
$git remote -v

#gitlab or github에 추가하기
$git push -u origin master
```



> 🤞보세요🤞부분부분 commit하고 한꺼번에 push 하기

```bash
#코드를 수정하고 commit하고 싶은 부분에서 add 후 commit만 계속 반복 후  

$git add . 
$git commit -m "원하는 메세지"
///////////////////////////////////위 코드 계속 반복

#push한꺼번에 할 수 있음(add와 commit한번에 저장됨)
$git push origin master
```



> 🤞보세요🤞기초 작업 완료한 프로젝트의 경우

```bash
$git config --global user.name "유저네임"
$git config --global user.email "이메일"
$git init
$git remote add origin 주소
////////////////////////////////////////////위에거 전부 완료한 프로젝트일 경우에는
$git add .
$git commit -m '메세지'
$git push origin master
==============================> 만 반복하면 된다
```



# 팀

> 처음 git repository 생성 하는 경우에는 위와 같음
>
> 만약 누군가가 git repository를 생성하고 git에 올라가 있는 파일들을 현재 위치에 다운 받고 싶을 때



##### Create a new repository

```bash
# gitlab or hub에 저장된 파일 받아오기
git clone https://lab.ssafy.com/yeafla530/hi.git
=================================
git clone시 gitlab or hub의 프로젝트명인 폴더 안에 .git과 파일들이 저장되어 있음

** existing folder(위와 동일) **

$git init

$git remote add origin https://lab.ssafy.com/s03-webmobile2-sub1/s03p11d222.git

$git add .

$git commit -m "Initial commit"

$git remote -v

$git push -u origin master
```



## branch사용하기

## git을 이용한 공동 작업공간

> git branch 사용
>
> 팀원 : brchA
> 자기branch : brchB
>
> master는 모두가 공유하고 있는 branch
>
> brchB에서 파일 수정 수행하기 master에서 수정하고 add commit 하면 모두가 공유하고 있는 master파일이 바뀌어 버리는 것이니 각별히 주의할 것



### branch 명령어

```bash
#브랜치 생성
$git branch { 브랜치명 }

#브랜치 이동
$git checkout {브랜치명}

#브랜치 확인
$git branch

[참고사이트]
https://victorydntmd.tistory.com/91
```



### git 협업

* master : 최종 프로젝트가 올라갈 branch
* develop : 전체적으로 중간 중간 push할 branch 
* feature/front/merge :  front에서 중간중간 push할 branch
* feature/~~ : 기능 구현시 쓸 branch



#### frontend git

>  셋 다 feature/front/merge의 코드를 동일하게 가지고 있음
>
>  셋 다 작업공간과 git push공간을 가지고 있음 

1. #### 첫번째 사람이 작성한 기능을 git에 올릴 때

   ```
   1. git 공간에서 새로운 브런치를 생성 후 이동
   	git branch [브랜치명]
   	git checkout [브랜치명]
   2. git 공간에 전에 있던 파일을 삭제 후 작업공간에서 작성한 파일을 복붙
   3. git add / commit / push 후 git 사이트에서 [브랜치명]이 추가됐는지 확인
   4. git feature/front/merge랑 [브랜치명] Merge request
   	source[추가시키고 싶은 브랜치] : [브랜치명]
   	target[새 브랜치 코드가 추가될 영역] : feature/front/merge
   5. git 충돌 나서 merge안될 때는 [브랜치명]에서 feature/front/merge를 pull
   (충돌 안날 때는 바로 merge)
   	git pull origin feature/front/merge
   6. 충돌 난 코드들 고친 후 다시 feature/front/merge에 push하기(최신업데이트)
   	git push origin feature/front/[내브랜치]
   
   ```

   

2. #### 두번째 세번째 사람이 작성한 기능을 git에 올릴때(차례대로 진행)

   ```
   1. git 공간에서 새로운 브랜치를 생성 후 이동
   2. git 공간에 전에 있던 파일 삭제 후 작업공간에서 작성한 파일을 복붙
   3. git add / commit / push 후 git 사이트에서 [브랜치명]이 추가됐는지 확인
   4. git feature/front/merge랑 [브랜치명] Merge request
   5. git 충돌나서 merge안될 때 [브랜치명]에서 feature/front/merge를 pull 
   6. 충돌 난 코드 고친 후 다시 feature/front/[내브랜치]에 push하기
   ```

   



1. 기능을 구현하면 자기 feature branch에 git push 먼저 하기
2. 합치고 싶은 branch가 있으면 git 사이트에서 merge request신청하면 merging되서 pull해오면 됨
   * source:합치고 싶은 개발중인 branch
   * target: 합치는 것의 main대상이 되는 branch
3. merge request 충돌해서 실패시 합치고 싶은 branch pull 해와서 코드 수정후 다른 사람이 pull해올 수 있도록 자기 branch에 push
   * git bash에서 [브랜치명]|MERGING상태이면 add commit push 실시

