package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  @Configuration 의 비밀
 *  - @Bean 만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
 *  - memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
 *  - 크게 고민할 것이 없다. 스프링 설정 정보는 항상 @Configuration 을 사용하자.
 */

public class ConfigurationSingletonTest {
    
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("OrderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);


    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        /**
         * 순수한 클래스라면 다음과 같이 출력되어야 한다.
         * class hello.core
         * 하지만
         * class hello.core.AppConfig$$EnhancerBySpringCGLIB$$2e5400a5
         * 이렇게 출력된다.
         * -> 이유 : 스프링이 CGLIB 이라는 바이트 코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
         *          이렇게 만들어진 다른 클래스를 스프링 Bean 으로 등록한 것이다.
         *          다른 클래스가 싱글톤이 되도록 보장해주는 것이다.
         *
         * 예상코드 (CGLIB)
         * @Bean
         * public MemoryRepository memberRepository() {
         *
         *      if(memoryMemberRepository 가 이미 스프링 컨테이너에 등록되어 있다면?) {
         *          return 스프링 컨테이너에서 찾아서 반환;
         *      } else {
         *          기존 로직을 호출해서 MemoryMemberRepository 를 생성하고 스프링 컨테이너에 등록
         *          return 반환;
         *      }
         * }
         */
    }

}
