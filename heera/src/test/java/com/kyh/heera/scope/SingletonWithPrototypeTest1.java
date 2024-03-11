package com.kyh.heera.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
     }


    @Test
    void singletonClientUsePrototype() {
         AnnotationConfigApplicationContext ac =
                 new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

         ClientBean clientBean1 = ac.getBean(ClientBean.class);
         int count1 = clientBean1.logic();
         assertThat(count1).isEqualTo(1);

         ClientBean clientBean2 = ac.getBean(ClientBean.class);
         int count2 = clientBean2.logic();
         assertThat(count2).isEqualTo(1);

         System.out.println("count1 = " + count1);
         System.out.println("count2 = " + count2);

    }

    @Scope("singleton")
    @Component
    static class ClientBean {

        // ObjectProvider
        @Autowired
        private PrototypeBean prototypeBean; // 생성 시점에 주입
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

//        // JSR330 Provider
//        // implementation 'javax.inject:javax.inject:1' gradle 추가 필수
//        @Autowired
//        private Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    /**
     *  여러 Bean 에서 같은 prototypeBean 을 주입 받으면,
     *  주입 받는 시점에 각각 새로운 prototypeBean 이 생성된다.
     *  예를 들어 clientA, clientB 각각 의존관계 주입을 받으면 각가 다른 프로토타입 빈을 주입 받는다.
     *  - clientA -> prototypeBean@x01
     *  - clientB -> prototypeBean@x02
     *  물론, 사용할 때마다 새로 생성되는 것은 아니다.
     */

    @Scope("prototype")
    @Component
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init = " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
