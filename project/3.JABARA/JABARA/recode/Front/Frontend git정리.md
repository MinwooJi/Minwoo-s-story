# frontend git

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
   	git push origin feature/front/merge
   
   ```

   

2. #### 두번째 세번째 사람이 작성한 기능을 git에 올릴때(차례대로 진행)

   ```
   1. git 공간에서 새로운 브랜치를 생성 후 이동
   2. git 공간에 전에 있던 파일 삭제 후 작업공간에서 작성한 파일을 복붙
   3. git add / commit / push 후 git 사이트에서 [브랜치명]이 추가됐는지 확인
   4. git feature/front/merge랑 [브랜치명] Merge request
   5. git 충돌나서 merge안될 때 [브랜치명]에서 feature/front/merge를 pull 
   6. 충돌 난 코드 고친 후 다시 feature/front/merge에 push하기
   ```

   