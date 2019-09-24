package net.qipo.springIOC_01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOC3Test {
    @Test
    public void test01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc3.xml");
    }
}
