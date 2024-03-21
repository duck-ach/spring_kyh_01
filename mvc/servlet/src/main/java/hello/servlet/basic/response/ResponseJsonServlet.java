package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");
        /**
         * [참고]
         * application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
         * 그래서 스펙에서 charset=utf-8 과 같은 추가 파라미터를 지원하지 않는다.
         * application/json;charset=utf-8 이라고 전달하는 것은 의미없는 파라미터를 전달하는 것과 같다.
         * response.getWriter()를 사용하면 추가 파라미터를 자동으로 추가해버린다.
         * 이때는 response.getOutputStream() 으로 출력하면 그런 문제가 없다.
         */

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // {"username":"kim","age":20}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
