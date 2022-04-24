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
- Springboot 
- Java
- Jquery
- BootStrap
- MySQL Or MariDb
- WebSocket
- Security
- SMTP
---
### 페이지 
+ 로그인페이지
+ 회원가입페이지
+ 메인페이지
    + 채팅페이지
    + 질문게시판페이지
    + 그린랜덤알피지
        + 전투준비페이지
        + 전투페이지
        + 상점페이지
    + 응가피하기
        + 랭킹페이지
---
### 기능
- WebSocket을 활용한 서버 채팅
- 회원가입 유효성 검사.
- 로그인 아이디 저장 체크버튼
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