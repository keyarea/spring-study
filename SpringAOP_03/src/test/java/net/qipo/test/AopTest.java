package net.qipo.test;


import net.qipo.impl.MyMathCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        MyMathCalculator bean = context.getBean(MyMathCalculator.class);
        bean.add(1, 2);
    }
}
