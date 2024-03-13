package com.kyh.heera.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * [ proxyMode = ScopedProxyMode.TARGET_CLASS ] - Provider 대신 사용
 * - CGLIB이라는 라이브러리로 내 클래스를 상속받은 가짜 껍데기(CGLIB$$erwe23어쩌고)MyLogger 를 의존관계 주입시킨다.
 * - 이 가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.
 *   - 클라이언트가 myLogger.logic() 을 호출하면 사실 가짜 프록시 객체의 메서드를 호출한 것이다.
 *   - 가짜 프록시 객체는 request 스코프의 진짜 myLogger.logic() 을 호출한다.
 *   - 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에
 *     이 객체를 사용하는 클라이언트 입장에서는 사실 원본인지 아닌지도 모르게 동일하게 사용할 수 있다. (다형성)
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this); // this : 주소가 나옴
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
