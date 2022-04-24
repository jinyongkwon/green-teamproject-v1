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
+ WebSocket
    + STOMP
    + SOCKJS
- SMTP
<div>
    <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
    <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
    <img src="https://img.shields.io/badge/JavaScript-3F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
    <img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white">
</div>
<div>
    <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white">
    <img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=MariaDB&logoColor=white">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
    <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">
</div>

---
### 페이지 
+ 메인
    + 로그인
        + 아이디찾기
        + 비밀번호찾기
    + 회원가입
    + 채팅
    + 질문게시판
        + 질문등록
        + 질문 목록보기
        + 질문 상세보기
    + 그린랜덤알피지
        + 전투준비
        + 전투
        + 상점
    + 응가피하기
        + 게임진행
        + 랭킹
---
|기능|활용기술|활용페이지|샘플링크|
|---|----|-----|----|
|채팅|WebSocket|채팅|[스프링 채팅 샘플 깃](https://github.com/jinyongkwon/Spring-Websocket-sample)|
|채팅도배금지|-|채팅|-|
|욕설필터링|-|채팅|-|
|유효성검사|JPA|회원가입|[블로그버전2](https://github.com/jinyongkwon/BlogProject-V2)|
|중복확인|JPA|회원가입, 로그인, 아이디찾기, 비밀번호찾기|[블로그버전2](https://github.com/jinyongkwon/BlogProject-V2)|
|메일보내기|SMTP|아이디찾기, 비밀번호찾기|[메일보내기샘플깃](https://github.com/codingspecialist/Springboot-mail-test)|
|보안|Security|모든페이지|[블로그버전3](https://github.com/jinyongkwon/BlogProject-V3)|
|사진등록및 확인|JPA|회원가입, 로그인, 아이디찾기, 비밀번호찾기|[파일업로드샘플깃](https://github.com/jinyongkwon/Spring-Fileupload-sample), [블로그버전3](https://github.com/jinyongkwon/BlogProject-V3)|
|애니메이션|JQuery|그린랜덤알피지, 응가피하기|-|
|랜덤값활용|JavaScript|그린랜덤알피지|-|
---
### DB모델링및 관계
- id, nickname, username, password , email , coin ,createDate, updateDate, rpgId => user
- id, attack, maxHp, hp, html, java, jsp, spring, freeTIme => rpg
- id, attack, hp, maxHp, name => monster
- id, title, content, file, createDate, updateDate, userId => question
- id, content, createDate, questionId, userId => comment
- id, name, image, url => game
- id, createDate, email, password, updateDate, username => manager
---
### 매니저 서버
- [매니저서버 깃](https://github.com/han-youngin/manager)
---
### 프로젝트 완성후 느낀점 및 소감
**권진용**
    대학교때 프로젝트를 한 이후로 오랜만에 프로젝트를 하면서 코딩의 재미를 많이 느끼고 팀장이라는 자리가 얼마나 책임감이 큰지 느끼게된 프로젝트였습니다. 다음 프로젝트에는 조금더 타이트하게 해서 실제로 사용할수 있겠다고 느낄 정도로 잘 만들고 싶은 생각이 들었습니다. 조원들도 말을 너무나 잘따라주고 다들 노력해주어서 성공적으로 마무리 지을수 있었던것 같습니다.

**유승현**
    코딩의 “코”도 모르던 학생이 프로젝트 하면서 어렵기도 했고, 코드를 잘 짜지 못하는 모습에 어려움도 있었습니다. 그래도 프로젝트를 마무리 할 수 있어서 기분이 좋습니다. 그리고 팀을 잘 이끌어준 팀장 진용이에게 respect 보냅니다.!

**한영인**
    첫 팀 프로젝트에서 좋은 팀을 만나서 순조롭게 진행되었던 것 같습니다. 제 실력이 많이 부족했는데 항상 흔쾌히 도와준 팀원분들 감사합니다!! 그리고 팀장님 정말 수고 많으셨습니다~!
    
**김무진**
    아직 실력이 부족합니다. 이번프로젝트로 인해 레디스 웹소켓 루비 AngularJs등을 경험했고 조금더 많은 부분을 경험하고 실력을 향상시키고 싶습니다.
---