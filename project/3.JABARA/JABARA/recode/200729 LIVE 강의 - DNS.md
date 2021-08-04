## 200729 LIVE 강의

> DNS(Domain Name Server)

![image-20200729101422716](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200729101422716.png)



1. DNS Cache

   ```
   - 매번 DNS와 통신을 하는 비용을 줄이기 위한 수단
   - 저장 위치 : 브라우저, 운영체제, 라우터, ISP
   ```

2. TCP : Transmission Control Protocol

   ```
   - TCP 3-way-handshake (통화를 예로 들기)
   	1) SYN : 전화를 걸고 친구가 받기를 기다린다.
   	2) ACK + SYN : 친구가 전화를 받고 여보세요?
   	3) ACK : 전화통화 한다.
   ```

3. HTTP

   ![image-20200729102154627](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200729102154627.png)

4. 정리

   ```
   * 구글 홈페이지가 브라우저에 보이기 위한 단계
   	1) 브라우저에 www.google.com을 입력
   	2) DNS를 통해 IP주소 획득
   	3) 획득한 IP 주소에 있는 서버와 TCP 3 Way Handshake
   	4) 통신을 맺은 서버에 HTTP Request
   	5) 서버에서 보낸 HTTP Response를 통해 html 파일 받음
   	6) 브라우저가 html을 분석해서 화면으로 출력
   ```

   