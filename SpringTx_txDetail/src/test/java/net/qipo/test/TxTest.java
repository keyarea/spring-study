package net.qipo.test;

import net.qipo.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("tx.xml");

    @Test
    public void main() {
        BookService bean = context.getBean(BookService.class);
        bean.checkout("Tom", "ISBN-001");

    }
}
