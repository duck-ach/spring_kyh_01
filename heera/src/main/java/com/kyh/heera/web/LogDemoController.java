package com.kyh.heera.web;

import com.kyh.heera.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo") // log-demo 라는 요청을 받으면
    @ResponseBody // view 화면 없이 반환하고 싶어서
    public String logDemo(HttpServletRequest request) { // HttpServletRequest : 자바의 request 규약
        String requestURL = request.getRequestURI().toString(); // 어떤 URL 로 요청이 왔는지 확인 가능
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }


}
