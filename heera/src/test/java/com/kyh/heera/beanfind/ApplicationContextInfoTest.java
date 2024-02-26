package com.kyh.heera.beanfind;

import com.kyh.heera.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 스프링 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionNames + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 스프링 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /** ROLE_APPLICATION
             * 스프링 내부에서 등록된 빈들 외에 외부라이브러리 및 내가 애플리케이션에서 등록한 빈들을 출력한다.
             */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                    Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", Object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("스프링 내부에서 사용하는 스프링 빈 출력하기")
    void findInfraStructureBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /** ROLE_INFRASTRUCTURE
             * 스프링 내부에서 사용하는 빈을 출력한다.
             */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                    Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", Object = " + bean);
            }
        }
    }




}
