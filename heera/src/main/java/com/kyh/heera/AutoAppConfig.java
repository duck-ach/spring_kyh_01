package com.kyh.heera;

import com.kyh.heera.member.MemberRepository;
import com.kyh.heera.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/**
 * [권장]
 * @ComponentScan 의 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다.
 * (예시) 프로젝트가 다음과 같이 구조되어 있다.
 *  - com.hello
 *  - com.hello.service
 *  - com.hello.repository
 *
 *  com.hello -> 프로젝트 시작 루트, 여기에 AppConfig 같은 메인 설정 정보를 두고
 *  @Configuration 을 붙이고 'basePackages' 지정은 생략한다.
 *
 */

@Configuration
@ComponentScan(
        // basePackages 패키지부터 하위 패키지로 찾아내려간다. (ComponentScan 대상)
        // basePackages = {"com.kyh.heera.member", "com.kyh.heera.order"} 이렇게 여러 시작 위치도 지정할 수 있다.
//        basePackages = "com.kyh.heera",

        // AutoAppConfig 의 패키지(package com.kyh.heera;) 부터 컴포넌트 스캔한다.
        // Default : com.kyh.heera 여기부터 다 뒤적거린다.(프로젝트 최상단) - 권장
//        basePackageClasses = AutoAppConfig.class,

        // AppConfig, TestConfig 등의 Configuration 을 제외하기 위해 필터 적용(보통은 잘 제외하지 않음, 기존예제코드 유지위함)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    Bean 자동등록 수동등록으로 인한 overriding=ture 충돌남
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
