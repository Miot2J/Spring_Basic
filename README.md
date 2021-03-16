# Spring_Basic
###스프링의 기본개념과 객체지향적 코드를 학습하고 기록하기 위한 repo입니다.

###  <java Folder 코드 작성 과정>
##### SOLID(좋은 객체지향 5원칙) 지키기 위한 코드를 작성해 보았다
1.Discount 인터페이스 생성 후 2개의 정책 클래스(Fix DC, Rate DC) 작성

2.Order 인터페이스 생성 후 주문 클래스 작성

3.Member Repo, Member Service 인터페이스 작성후 클래스 생성

4.AppConfig로 @Bean 수동생성 코드 작성 

* 스프링 컨테이너 개념 학습, (DI) 의존관계 주입

5.AutoAppConfig로 @Bean 자동 생성 코드 작성 (@ComponentScan 사용)

6.LomBok으로 setter getter toString 사용 코드 작성

------------
### <Test Folder 코드 작성 과정>

1. singleton Package :
    + 패턴이 없는 StatefulService 코드 작성
    + Singleton 패턴을 구현한 SingletonService 코드 작성
    + SingletonTest 코드로 패턴없는Class, 구현된 SingletonClass, Spring이 지원하는 Singleton의 차이점 확인


2.autowired Package : 같은 Bean타입 여러개 불러올때, 빈값이 널일때 해결 과정 및 테스트 코드 작성

3.beanDefinition Package : Bean Definition 정보 및 @display annotation 출력 코드 및 테스트

4. beanfind Package: Bean 을 조회하는 여러방식의 코드 작성 
    + 이름으로 Bean 조회
    + 타입으로 Bean 조회
    + 구체타입으로 조회
    + Bean 타입 중복시 @Primary로 우선순위 정하기
    

5. xml Package : xml로 @Configuration 등록하는 코드 작성
   +        appConfig.xml
    + xml방식이 아닌 @Configuration 작성 코드와 이 과정을 생략하기 위한 @Component, @ComponentScan활용

6. lifecycle Package : 빈의 생명주기를 알아보기위한 코드 @PostConstruct , @PreDestroy 이용해 Bean 생성과 서비스 시작, 빈 종료 시점에 관란 코드 작성

7.scope Package: @Scope("singleton")과 @Scope("prototype") 의 차이를 보고 singleton 에 prototype을 DI 했을때 의 문제점을 JPA의 Provider 와 Spring의 objectProvider로 해결 하는 코드 *(JPA는 build.gradle에 추가해야  사용가능하다)

8. scan Package: Bean을 찾기 위한 @ComponentScan시 @Filter를 이용하여 필터링할 Bean 선택 (직접 만든 Annotation @ 사용 )

---------
### < 사용 Annotation >
1.@Component

2.@Qualifier("fixDiscountPolicy") : default 값은 클래스 첫 글자만 소문자로 변경한 것
(@Autowired와 같이 쓰이며, 같은 타입의 빈 객체가 있을 때 해당 아이디를 적어 원하는 빈이 주입)

3.@Primary : Bean 타입 같을때 우선 순위 지정

4.@Component: Spring이 자동으로 Bean 등록해줌

5.@Autowired : ac.getBean(MemberRepository.class) <- 해당 변수에 Bean을 자동 매핑해준다.

6.@Bean : 스프링 컨테이너에 등록될 Bean임을 명시

7.@Configuration : 빈 환경설정을 위한 Annotation, 자체로도 @Component 속성을 가진다

8.@ComponentScan : @Component Annotation 붙은 클래스들 찾아서 스프링 컨데이너에 등록

9.@Test : 테스트를 수행하기 위한 Annotation @Test 가 붙은 method는 테스트를 수행하는 method 가 된다

10.@DisplayName : 실행시 지정된 메세지를 출력해준다.

11.@Scope: default 는 singleton 이며 현재 package에선 prototype 으로 설정 해보기 위해 사용한다
+ @Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS):
  proxyMode = ScopedProxyMode.TARGET_CLASS
  적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS 를 선택
  적용 대상이 인터페이스면 INTERFACES 를 선택


12.@PostConstruct : Bean lifecycle에선  Bean 생성 후 초기화 작업 전에 실행된다.

13.@PreDestroy : Bean lifecycle에선 Bean 종료 직전에 호출된다. 

14.@BeforeEach : Test 코드 실행 전 호출

15.@Filter : 필터를 통해 원하지 않는 Bean 이나 Annotation을 걸러 낼 수있다. 

```
@ComponentScan(
// 검색할 루트 폴더 지정가능(basePackages) , defalut값 componentscan 현재 자바클래스 패키지부터 시작
    basePackages = "hello.core",
    basePackageClasses = AutoAppConfig.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
```




### < LomBok Library >
1.@RequiredArgsConstructor : 클래스내의 final 붙은 변수를 가지고 생성자 자동 실행해줌 (생성자 선언 불필요)

2.@Getter : Getter method를 선언 안하고 get 사용가능

3.@Setter : Setter method를 선언 안하고 get 사용가능

4.@ToString : ToString method를 선언 안하고 get 사용가능

------

### < 웹 스코프 >

#### 특징
+ 웹 환경에서만 동작
+ 프로토 타입과 달리 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드 호출

#### 종류

+ Request : Http 요청 하나가 들어오고 나갈 때 까지 유지된다. Http 요청마다 별도의 빈 인스턴스 생성

+ Session : Http Session과 동일한 생명 주기 가진다.

+ Application : Servlet Context와 동일한 생명 주기 가진다.

+ Websocket : Web Socket과 동일한 생명주기 가진다.

-------------
### < Provider >

+ DL을 위해 사용한다 
  
+ ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의
  생성을 지연할 수 있다. in LogDemoController.class , LogDemoService.class
