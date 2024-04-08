package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.contoller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.contoller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.contoller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v2/* 하위로 들어오는 호출은 모두 이 컨트롤러로 들어옴
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");
        String requestURI = request.getRequestURI();

        // Controller에서 찾기
        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 잘 조회가 됐다 == controller 에 request, response 담아서 호출
        controller.process(request, response);

        // MyView 호출하여 화면에 request 와 response 를 담아 rendering
        MyView view = controller.process(request, response);
        view.render(request, response);

    }
}
