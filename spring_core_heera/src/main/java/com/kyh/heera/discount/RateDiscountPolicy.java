package com.kyh.heera.discount;

import com.kyh.heera.annotation.MainDiscountPolicy;
import com.kyh.heera.member.Grade;
import com.kyh.heera.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
@Primary // discountPolicy 에서 여러개의 Bean 이 조회되더라도 우선 순위 적용
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
