package com.kyh.heera.scan.filter;

import java.lang.annotation.*;

/**
 * @ComponentScan 에서 제외
 */
@Target(ElementType.TYPE) // TYPE : Class level 에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {

    

}
