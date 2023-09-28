# springbootAndAWSPractice

### Description
- `책 <스프링 부트와 AWS로 혼자 구현하는 웹 서비스>` 를 따라하며 공부한 프로젝트
- 주요내용 : JPA, Junit테스트, 소셜로그인(구글, 네이버), Spring security & OAuth2.0, AWS 배포, Travis CI

---

### Tech Stack
- Spring boot ~2.7.15~ 2.6.7
- Java 11
- MySQL
- Gradle

---

### Devlog
- [전체 기간] : 2023.09.07 ~ 진행 중
- [1차개발] - 책에서 다루는 내용을 학습하여 AWS 배포를 목표로 함 : 2023.09.07~2023.09.15 완료
- 2023.09.07 ~ 2023.09.12 : 05장 백엔드 기능구현, 프론트 완료
- 2023.09.13 : 06장 - AWS 서버환경 만들기(AWS EC2) / 07장 - AWS 데이터베이스 환경 만들기(AWS RDS)
- 2023.09.14 ~ 15 : 08장 - AWS 환경에서 배포하기 (배포완료)
- [2차개발] - 1차개발에서 다루지 않은 필요기능 추가개발
- 2023.09.20 ~ 진행 중
 - 가입이후에 부여된 권한 및 글쓰기 권한 조정
 - parameter validation
 - Common Response 형식 생성 에러반환 (json에러 형식)
 - 글 작성 페이지 수정
    - 작성자에 로그인한 사람 이름
 - Oauth로그인 페이지 수정 (/login)
