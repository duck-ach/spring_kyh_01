package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        // BeanB beanB = ac.getBean("beanB", BeanB.class); // Filter로 걸러져서 bean을 찾을 수 없음
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            /***************************************************************************************
             * FilterType 종류
             * - ANNOTATION(default) : 기본값. 생략해도 잘 동작함
             * - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작한다.
             * - ASPECTJ : AspectJ 패턴 사용
             * - REGEX : 정규 표현식
             * - CUSTOM : 'TypeFilter' 이라는 인터페이스를 구현해서 처리
             ***************************************************************************************/
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

}
