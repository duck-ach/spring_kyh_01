package com.kyh.heera.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!
//    }
//
//    public int getPrice() {
//        return price;
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price; // 들어오는 값에 return
    }

}
