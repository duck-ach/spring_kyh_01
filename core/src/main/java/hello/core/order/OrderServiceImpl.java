package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // ComponentScan의 대상
public class OrderServiceImpl implements OrderService {

    /**
     * Field 주입
     * @Autowired private MemberRepository memberRepository;
     * @Autowired private DiscountPolicy discountPolicy;
     *
     * 코드는 간결하지만 외부에서 변경이 불가능해서 테스트하기 힘들어서 옛날엔 많이 사용했지만
     * 최근에는 권장하지 않음
     */

    /* final 키워드
    *  생성자 주입을 사용하면 final 키워드를 사용할 수 있는데,
    *  생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에서 막아준다.
    * */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 이렇게만 하면 NullPointerExceptions

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 수정자 주입
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//

    /**
     * @Autowired
     *
     * 생성자가 딱 1개일때는 @Autowired를 생략해도 된다. (자동주입)
     *
     */

    /**
     * 아래는 @RequiredArgsConstructor 롬복 라이브러리의 Annotation과 같은 역할을 한다.
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 일반 메서드 주입
     * 일반적으로 잘 사용하지 않는다.
     */
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    @Override // 주문 요청이 오면
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원을 생성하고
        Member member = memberRepository.findById(memberId);
        // 할인 정책 먹이고
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문을 반환해준다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
