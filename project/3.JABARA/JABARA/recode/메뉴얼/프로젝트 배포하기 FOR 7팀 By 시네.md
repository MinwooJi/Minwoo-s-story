#  🎀프로젝트 배포하기 for 7조 by 시네쟝🎀 

> Host Name(IP) : ubuntu@i3d207.p.ssafy.io
>
> FileZilla Port : 14147

[TOC]

## BackEnd 배포

1. 필요한 것들 검색해서 설치 ( nginx, java, spring boot , git lab. . .  ) + 설정
2. git clone 해오기
3. 클론 된 경로에서 spring-boot 실행 명령어 입력한다.

## FrontEnd 배포

- https://brunch.co.kr/@springboot/188

## DB 공유

1. docker 설치
2. 설치된 도커에 mariadb image 올린다.
3. dbeaver(외부 DB 툴)에 mariadb를 연결한다.

- https://firework-ham.tistory.com/105 (매우 잘 설명되어있으니 참고하세욥)



## 기타

🔔 ubuntu에서 설치하다 깨지거나 실패했을 때 정리

- https://qastack.kr/unix/389156/how-to-fix-held-broken-packages

🔔 npm 삭제

- https://wrjeoung.tistory.com/30

- 안될 때 ->

  ```
  - sudo apt install aptitude
  - sudo aptitude install npm
  ```
