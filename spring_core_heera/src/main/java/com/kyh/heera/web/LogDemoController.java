package com.kyh.heera.web;

import com.kyh.heera.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider; // myLogger 를 찾아주는 Provider 를 주입받음
    private final MyLogger myLogger;

    /**
     * private final ObjectProvider<MyLogger> myLoggerProvider; // myLogger 를 찾아주는 Provider 를 주입받음
     * [ ObjectProvider 를 사용한 이유 ]
     * 바로 MyLogger를 호출하면 MyLogger 객체의 스코프가 "request" 인데, request 요청이 들어오기 전 빈이 생성이 되면서 오류가 난다.
     * ObjectProvider 를 통해 MyLogger 객체가 생성될 때 까지 기다렸다가, Spring container에서 찾으면 MyLogger를 불러와서 의존성을 주입해준다.
     */
    @RequestMapping("log-demo") // log-demo 라는 요청을 받으면
    @ResponseBody // view 화면 없이 반환하고 싶어서
    public String logDemo(HttpServletRequest request) throws InterruptedException { // HttpServletRequest : 자바의 request 규약

        String requestURL = request.getRequestURI().toString(); // 어떤 URL 로 요청이 왔는지 확인 가능

//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        myLogger.getClass(); // class 확인용

        // uuid 가 같은지 확인하기 위해 1초 딜레이 후 request 1번 더 요청
        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }


}
