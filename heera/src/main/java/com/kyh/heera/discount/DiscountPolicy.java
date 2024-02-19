package com.kyh.heera.discount;

import com.kyh.heera.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */

    int discount(Member member, int price);
}
