package com.kyh.heera.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
