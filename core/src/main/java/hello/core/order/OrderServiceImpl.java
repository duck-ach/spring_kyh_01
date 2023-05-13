package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /**
     * Field 주입
     * @Autowired private MemberRepository memberRepository;
     * @Autowired private DiscountPolicy discountPolicy;
     *
     * 코드는 간결하지만 외부에서 변경이 불가능해서 테스트하기 힘들어서 옛날엔 많이 사용했지만
     * 최근에는 권장하지 않음
     */

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy; // 이렇게만 하면 NullPointerExceptions

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /**
     * 수정자 타입
     */
    // @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게 하려면 required = false 로 지정하면 된다.
    @Autowired(required = false)
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
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
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
