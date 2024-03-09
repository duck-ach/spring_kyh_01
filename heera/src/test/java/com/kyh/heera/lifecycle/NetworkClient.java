package com.kyh.heera.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean, DisposableBean
 * - 결론적으로 이 두 인터페이스는 오래되기도 했고 잘 사용하지 않는다. (더 좋은 방법들이 많음)
 *
 * [InitializingBean]
 *  - afterPropertiesSet() 메서드로 초기화를 지원해준다.
 *
 * [DisposableBean]
 *  - distroy() 메서드로 소멸을 지원해준다.
 *
 * [초기화, 소멸 인터페이스 단점]
 *  - 이 인터페이스는 스프링 전용 인터페이스 이기때문에 스프링에게 코드가 의존하게 된다.
 *  - 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 *  - 내가 코드를 고칠 수없는 외부 라이브러리에 적용할 수 없다.
 */
public class NetworkClient implements InitializingBean, DisposableBean {

    /**
     * Spring Bean은 객체를 생성하고, 의존관계가 다 주입이 되어야 데이터를 사용할 수 있는 준비가 완료된다.
     * 따라서 초기화 작업은 의존관계가 모두 주입되고 난 다음에 호출해야 한다.
     *
     * [ Spring Bean의 Life Cycle ]
     *  1. Spring Container 생성
     *  2. Spring Bean 생성
     *  3. Dependency Injection
     *  4. 초기화 Callback
     *  5. Use
     *  6. 소멸 전 Callback
     *  7. Spring 종료
     */

    /**
     * 참고 : 객체의 생성과 초기화를 분리하자.
     * - 생성자 : 필수정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
     * - 초기화 : 생성자를 통해 생성된 값들을 활용해서 외부 Connection을 연결하는 등 무거운 동작을 수행한다.
     *
     * 따라서, 생성자 안에서 무거운 초기화 작업을 함께 하기보다는
     * 객체를 생성하는 부분과 초기화 하는 부분을 분리하여 명확하게 나누는 것이 유지보수 관점에서 좋다.
     * 초기화 작업이 내부 값들만 약간 변경하는 정도거나 단순한 경우에는 한번에 다 처리하는 것이 더 나을 수 있다.
     */

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url); // 이땐 url = null 이다.
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception { // 의존관계 주입이 끝나면 호출해주겠다.
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception { // disconnect 호출해준다.
        disconnect();
    }
}
