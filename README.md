# 그린 아카데미 TeamProject - v1 1조

## 통합 웹게임 사이트

## 조원
- 권진용(팀장)
- 김무진
- 유승현
- 한영인

### 1. 의존성
- devtools
- spring web (mvc)
- mustache
- lombok
- jpa
- mysqldb

### 2. 활용기술
- Springboot
- Java
- Jquery
- BootStrap
- MySQL
- WebSocket
- Redis

### 3. 페이지 
- 메인페이지
- 로그인페이지
- 회원가입페이지
- 그린랜덤알피지 => 전투준비페이지 (진행중)
- 그린랜덤알피지 => 전투페이지
- 그린랜덤알피지 => 상점페이지
- 책피하기 - (개발예정)
- 쿠키런(가명) - (개발예정)

### 4. 기능
- WebSocket을 활용한 서버 채팅
- 회원가입 유효성 검사.
- 로그인 아이디 저장 체크버튼
- Redis를 활용한 전투준비 페이지에서의 위치를 기억
- Random값을 활용해서 상점 및 적 처치시 보상 구현
- JQuery를 활용한 각종 애니메이션 효과 구현.

### 5. DB모델링및 관계
- id, nickname, username, password , email , coin ,createDate, updateDate => 통합계정
- id, atk, Maxhp, hp, userId = > 랜덤알피지 1 = 통합계정 1
- id, name, atk, Maxhp, hp => 랜덤알피지 몬스터
- id, randomrpgId, html, jsp, java, spring => 무기 DB 1 = 랜덤알피지 1
- id, userId, score => 책피하기 1 = 통합계정 1