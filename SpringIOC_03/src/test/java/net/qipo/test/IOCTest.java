package net.qipo.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        Object bean = context.getBean("bookDao");
        Object bean01 = context.getBean("bookDao");
        System.out.println(bean == bean01);
    }
}
