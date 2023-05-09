package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy; // 이렇게만 하면 NullPointerExceptions

    /**
     * 수정자 타입
     */
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    /**
     * @Autowired
     *
     * 생성자가 딱 1개일때는 @Autowired를 생략해도 된다. (자동주입)
     *
     */

    @Autowired // 생성자 위에
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
