package com.kyh.heera.singleton;

public class SingletonService {

    /**
     * static
     * 어떤 객체에 소속되는 Instance 가 아닌,
     * 클래스에 고정되어 있는 변수나 메서드를 의미한다.
     *
     * (특징)
     * - 메모리에 고정적으로 할당(각 객체들에서 공통적으로 하나의 값이 유지되어야 할 경우 유용)
     * - 객체 생성 없이 사용 가능
     * - 프로그램이 시작되면 메모리의 static 영역에 적재되고, 프로그램이 종료될 때 해제된다.
     * - static 메서드 내에서는 인스턴스 변수를 사용할 수 없다.
     * - 이미 만들어진 객체를 공유해서 효율적으로 사용이 가능하다.
     *
     * (문제점)
     * - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
     * - 의존 관계 상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반 (AppConfig / MemberServiceImpl.getInstance())
     * - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
     * - 유연하게 테스트 하기 어렵다.
     * - 내부 속성을 변경하거나 초기화 하기 어렵다.
     * - private 생성자로 자식 클래스를 만들기 어렵다.
     * 결론 : 유연성이 떨어진다. (안티 패턴 이라고도 불림)
     */

    // 1. static 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("singleton 객체 로직 호출");
    }

}