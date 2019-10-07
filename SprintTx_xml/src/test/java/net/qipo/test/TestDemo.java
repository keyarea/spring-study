package net.qipo.test;

import net.qipo.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    ApplicationContext context = new ClassPathXmlApplicationContext("tx.xml");

    @Test
    public void test01() {
        BookService bean = context.getBean(BookService.class);
        bean.checkout("Tom", "ISBN-001");
    }

}
