# 외부 라이브러리
Gradle에서는 의존성을 주입하면 그와 관련한 필요한 요소들을 자동으로 빌드해준다.

## Gradle의 의존성
왼쪽 맨 아래 네모 버튼을 누르면 여러가지 요소들을 확인할 수 있다.
![img.png](Desktop/Coding/spring_kyh_01/정리.01_library/img.png)
그 중 Gradle을 누르면 주입된 의존성을 확인할 수 있는데,
확인해보면 thymeleaf 하나만으로도 여러개로 파고파고파고
계층적으로 관련된 요소들이 함께 주입 된 것을 확인할 수 있다.
![img_1.png](Desktop/Coding/spring_kyh_01/정리.01_library/img_1.png)

보통 spring-boot-starter와 core와 같은 것들은 보통 모든 프로젝트에 주입되어 온다.

## log
log4j가 있고, logback이 존재하는데,
logback이 성능도 빠르고 기능들이 다양해서 더 많이 쓴다.
이 둘 라이브러리는 스프링부트 프로젝트를 생성하면 자동으로 딸려온다.

## test
junit이라는 라이브러리를 주로 사용하는데,
4버전을 주로 쓰다가 5버전으로 점점 넘어가고 있다.

# 정리
## 스프링 부트 라이브러리
### spring-boot-starter-web
 - spring-boot-starater-tomcat : 톰캣(Web Server)
 - spring-webmvc : spring web mvc
### spring-boot-starter-thymeleaf : 타임리프 템플릿 엔진 (view)
### spring-boot-starter(공통) : spring boot + spring core + loging
 - spring-boot
   - spring-core
 - spring-boot-starter-logging
   - log back, log4j

## 테스트 라이브러리
### spring-boot-starter-test
  - junit : 테스트 프레임워크
  - mockito : 목 라이브러리
  - assertj : 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test : 스프링 통합 테스트 지원