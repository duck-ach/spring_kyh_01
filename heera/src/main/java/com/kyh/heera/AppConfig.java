package com.kyh.heera;

import com.kyh.heera.discount.DiscountPolicy;
import com.kyh.heera.discount.RateDiscountPolicy;
import com.kyh.heera.member.MemberRepository;
import com.kyh.heera.member.MemberService;
import com.kyh.heera.member.MemberServiceImpl;
import com.kyh.heera.member.MemoryMemberRepository;
import com.kyh.heera.order.OrderService;
import com.kyh.heera.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration
 * 구성정보(설정정보) 를 담당하는 파일이라고 지정해줄 때 사용하는 Annotation
 */
@Configuration
public class AppConfig {

    /**
     * AppConfig
     * 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
     */

    /**
     * [Refactoring]
     * 아래와 같이 AppConfig를 구성하면
     * Method 를 호출하는 순간 역할이 다 드러난다.
     */

    /**
     * @Bean
     * Bean 등록하여 해당 메소드를 사용한다는 의미
     * @Bean이 붙은 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
     * 이렇게 스프링 컨테이너에 등록된 객체를 '스프링 빈' 이라고 한다.
     * 스프링 빈은 @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. (memberService, orderService)
     *
     * 이전에는 개발자가 필요한 객체를 AppConfig를 사용하여 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾는다.
     * 스프링 빈은 applicationContext.getBean() 메서드를 사용해서 찾을 수 있다.
     *
     * Bean의 이름은 중복되면 안된다. 상황에 따라 다른데
     */

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    /**
     * new new 각각 2개의 MemoryMemberRepository()가 생성되어 싱글톤이 깨지는 것처럼 보인다.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
