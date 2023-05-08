package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (// 스프링 빈을 자동으로 끌어올려주는 애너테이션

        // basePackages를 설정해주면 해당 경로부터 하위경로의 Bean 들을 찾는다.
        // 라이브러리같은 것들을 사용할 때 많이 사용한다.
        // basePackages = "hello.core.member",

        // 해당 클래스가 포함된 패키지의 경로(package hello.core)부터 찾는다.
        // basePackageClasses = AutoAppConfig.class,

        // 만약 위의 base들을 설정하지 않으면 @ComponentScan 이 붙은 설정 정보의 클래스의 패키지가 시작 위치가 된다.

        // AppConfig가 자동으로 등록되지 않도록 (충돌방지)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/**
 * 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에, AppConfig, TestConfig 등 앞서 만들어 두었던
 * 설정 정보도 함께 등록되고, 실행되어 버린다.
 * 그래서 excludeFilters를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트에 스캔 대상에서 제외하지는 않지만,
 * 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.
 */
public class AutoAppConfig {

}