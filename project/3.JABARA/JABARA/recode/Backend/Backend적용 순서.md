# Backend적용 순서

## 1. java spring켜서 설정하기 (workplace변경됐을 때)

window -> preperance ->  encoding검색 4개 변경 (css, html, jsp, workspace)



## 2. Git clone해서 폴더 열기

오른쪽 마우스 -> Git -> import -> ...



## 3. Docker 열어서 mariaDB 실행하기

CLI창 열기 > 

```
# mysql -u root -p
Enter password:
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 4
Server version: 10.5.4-MariaDB-1:10.5.4+maria~focal mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> create database ssafy;
Query OK, 1 row affected (0.000 sec)
```



## 4. DBeaver 열어서 백엔드에서 보내준 sql넣기

![image-20200722140140659](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200722140140659.png)

> 1번 누르면 maria db에서 생성한 database넣고 비밀번호 넣기

![image-20200722140200085](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200722140200085.png)

>  3번에서 ;마다 ctrl + enter



## 5. STS application/properties 확인 

![image-20200722140407666](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200722140407666.png)

> password 는 mariadb에서 설정한 비번
>
> port번호 확인하기

최상위 폴더 오른쪽 마우스 > run as > spring boot app 



## 6. Swagger에 들어가서 실행,확인하고 DBeaver에서 ctrl + enter로 data들어온거 확인하기

> swagger : http://localhost:8090/jobara/swagger-ui.html

![image-20200722141141997](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200722141141997.png)

![image-20200722141157555](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200722141157555.png)



## maria DB 비밀번호 통일하기

```mariadb
> use mysql
> set password for root = password('ssafy')
```



##  Spring에 JWT적용하기

참고 : https://alwayspr.tistory.com/8



### Clinet code

**> Client ID** jvbs4eOy58wTrZuL4V_z



**> Client Secret** Q0N49BLMrD



