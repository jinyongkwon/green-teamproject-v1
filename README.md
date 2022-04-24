# 통합 웹게임 사이트  - 헤헤
---
### 조원
- 권진용(팀장)
- 김무진
- 유승현
- 한영인
---
### 의존성
+ devtools (Spring)
    ```
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    ```
+ spring web (mvc)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-web'
    ```
+ mustache (View)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-mustache'
    ```
+ lombok 
    ```
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    ```
+ jpa (DB접근)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    ```
+ mysqldb or mariadb (DB)
    ```
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    runtimeOnly 'mysql:mysql-connector-java'
    ```
+ websocket (채팅)
    ```
    implementation 'org.webjars:webjars-locator-core'
    implementation 'org.webjars:sockjs-client:1.0.2'
    implementation 'org.webjars:stomp-websocket:2.3.3'
    implementation 'org.springframework.boot:spring-boot-starter-websocket'
    ```
+ mail (메일보내기)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-mail'
    ```
+ security (보안)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-security'
    ```
+ validation (yml 자기가 만든변수 가져오기)
    ```
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    ```
---
### 활용기술
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-3F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white">
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=MariaDB&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">
+ WebSocket
    + STOMP
    + SockJS
- SMTP
---
### 페이지 
+ 메인
    + 로그인
        + 아이디찾기
        + 비밀번호찾기
    + 회원가입
    + 채팅
    + 질문게시판
    + 그린랜덤알피지
        + 전투준비
        + 전투
        + 상점
    + 응가피하기
        + 게임진행
        + 랭킹
---
### 기능
|기능|
- WebSocket을 활용한 서버 채팅
- 회원가입 유효성 검사.
- Redis를 활용한 전투준비 페이지에서의 위치를 기억
- Random값을 활용해서 상점 및 적 처치시 보상 구현
- JQuery를 활용한 각종 애니메이션 효과 구현.
---
### DB모델링및 관계
- id, nickname, username, password , email , coin ,createDate, updateDate => 통합계정
- id, atk, Maxhp, hp, userId = > 랜덤알피지 1 = 통합계정 1
- id, name, atk, Maxhp, hp => 랜덤알피지 몬스터
- id, randomrpgId, html, jsp, java, spring => 무기 DB 1 = 랜덤알피지 1
- id, userId, score => 책피하기 1 = 통합계정 1