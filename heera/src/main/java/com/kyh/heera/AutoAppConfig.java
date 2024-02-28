package com.kyh.heera;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // AppConfig, TestConfig 등의 Configuration 을 제외하기 위해 필터 적용(보통은 잘 제외하지 않음, 기존예제코드 유지위함)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {



}
