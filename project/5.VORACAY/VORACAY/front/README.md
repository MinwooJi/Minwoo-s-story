# VORACAY FRONT-END

## 실행방법

1. front end

   ```
   git clone https://lab.ssafy.com/s03-final/s03p31d106.git
   cd s03p31d106.
   cd /front/voracay
   npm i
   npm run serve
   ```

## 디렉토리 구조도

```
front/VORACAY
  └───apis               	: 백앤드서버와 통신을 위한 코드를 위한 폴더
  │
  └───assets                : 각종 참조 파일
  │     └───scss/css        : 외부 css 저장소
  │     └───logo            : logo 이미지 파일 저장소
  │     └───iamge
  │     └───font
  └───store                 : vuex를 통한 상태관리 (변수를 통해 화면 컴포넌트 제어)
  │
  └───router                : 화면이동을 위한 url 관리
  │
  └───http-common.js        : BackEnd server와 통신에서 사용될 base URL/Header관리
  │
  └───components
  │     └───meeting         : 화상회의 화면에서 사용될 컴포넌트
  │     └───modal           : 모달창 화면 컴포넌트
  │     └───sidebar    	    : 사이드바 제어시 사용될 컴포넌트들
  │     	└───groupCall
  │     	└───recentCall
  │     	└───contact
  │     └───TopHeader.vue   : 화면상단 헤더관리
  │
  └───views					: router를 통해 이동할 페이지 목록
  │     └───Login
  │     └───Main
  └─────────Meeting
```

## 현재 구현상황

### 공통

- 현재 그룹 구성원 테이블 페이징 처리
  - 페이징 크기는 vuex를 통해 관리
- 검색결과와 카드를 통한 그룹관리창은 투명스크롤 처리

### 헤더 상단바 (TopHeader.vue)

- 로고 클릭시 main/ login 페이지 이동
- 환영메시지 / 로그아웃

### 로그인 페이지 (Login.vue)

- 애니메이션(버블) 기반 로그인 폼
- 사번/비밀번호를 통한 로그인
- vuex와 쿠키를 이용한 토큰 상태관리 로직

### 메인 페이지 (Main.vue)

- 좌측 네비게이션 바

  - 최근통화 / 그룹통화 / 작업지시 - 마우스 호버를 애니메이션 효과

- 우측 contents 컴포넌트

  - 최근통화 (RecentCall.vue)
    - 최근 통화내역을 테이블로 표현
    - 체크박스를 통해 다중선택하여 다수와 영상통화 진행하는 샘플로직 구현

  <<<<<<< HEAD
  ======= - 테이블 상단 토글버튼 클릭하여 최근 통화방으로 조회

        - 통화방 클릭시 전의 참가자들과 통화

  > > > > > > > 8c99cc7693aed9724eacbf159df083a9df92efdb
  >
  > - 최근통화 테이블 페이징 처리
  > - 그룹통화
  > - 사용자 등록된 그룹조회 (GroupCall.vue)
  >   - 카드구조를 통해 그룹조회, 마우스 오버시 카드 뒤집히며 그룹 메모정보 조회
  >     <<<<<<< HEAD
  >     ======= - 사진출처 : https://pixabay.com/photos/laptop-office-hand-writing-3196481/
  >     (상업용 무료이미지)
  >     > > > > > > 8c99cc7693aed9724eacbf159df083a9df92efdb
  >   - 카드 컨테이너에 투명 스크롤 처리
  >   - 조회된 그룹중 이름 검색을 통해 사용자가 등록한 그룹(카드)조회 샘플로직
  >   - 여러 그룹(카드) 클릭시 상단에 선택한 그룹정보 출력
  >     - 선택된 그룹정보는 이름옆의 삭제버튼을 통해 삭제가능
  >     - 통화하기 버튼을 사용해 선택된 인원들과 다중통화 샘플로직
  > - 그룹 관리 버튼
  >   - 그룹 수정 (UpdateGroup.vue)
  >     <<<<<<< HEAD
  >     ======= - 관리중인 그룹 삭제 & 참가중인 그룹 탈퇴 가능 - 그룹명, 메모 수정 폼 추가 - 그룹 생성/수정 시 그룹관리 권한 부여
  >     > > > > > > 8c99cc7693aed9724eacbf159df083a9df92efdb
  >          - 선택한 그룹 참여인원 삭제
  >          - 카테고리 별 검색을 통한 새로운 인원 추가
  >          - 현재 선택되어있는 그룹 참여원 하단 테이블로 표시
  >   - 그룹 상세 (GroupDetail.vue)
  >     - 선택한 그룹의 참여 인원 테이블로 표시
  >     - 테이블은 페이징 처리
  >   - 새 그룹 작성 (AddGroup.vue) - 그룹명 입력후 중복검사하는 샘플로직 - 카테고리 별 검색을 통한 새로운 인원추가 - 체크박스를 선택하여 검색한 인원 리스트에 추가 - 검색창 하단에 현재 선택된 그룹인원 테이블 표시
  >     <<<<<<< HEAD
  > - # 작업지시 (TaskOrder.vue)
  > - <s>작업지시 (TaskOrder.vue)</s>
  > - 연락처(Contact.vue)
  >   > > > > > > 8c99cc7693aed9724eacbf159df083a9df92efdb

## 구현할 사항

- <s>그룹관리>그룹수정에서 그룹명 변경 창</s>
- Backend와 연동하여 샘플데이터로 채워진 data 받아오고 관리
  - axios통신을 수행할 http-common.js 작성
  - apis 폴더에 통신코드 폴더로 분류하여 작성
  - backend와 맞추어 인증을 통한 로그인 정보 관리
- WebRTC를 이용한 화상통화 화면 구현
- WebRTC front환경 이식
- 연락처 사이드바 내용 구성
- <s>아웃바운드 대비 외부 참조 코드들 수정</s>

  - <s>부트스트랩 / 폰트 / 이미지 </s>
