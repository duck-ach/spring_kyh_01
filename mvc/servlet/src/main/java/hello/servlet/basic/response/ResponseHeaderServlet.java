package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // [response-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // custom 응답을 만들 수도 있음
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // [header 편의 메서드]
        content(response);
        cookie(response);
//        redirect(response);

        // [message-body]
        PrintWriter writer = response.getWriter();
        writer.println("하이하이");
        writer.println("안녕하세요");

    }


    // content 관련 response 메소드

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); // (생략 시 자동으로 content 길이를 계산하여 생성)
    }
    // cookie 관련 response 메소드

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600";
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초 동안 유효
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) {
        // Status Code 302
        // Location: /basic/hello-form.html

        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.setHeader("Location", "/basic/hello-form.html");
//        response.sendRedirect("/basic/hello-form.html");

    }
}
