package net.qipo.test;

import net.qipo.service.BookService;
import net.qipo.service.MulService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

public class TxTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("tx.xml");

    @Test
    public void test01() throws FileNotFoundException {
        BookService bean = context.getBean(BookService.class);
        bean.checkout("Tom", "ISBN-001");

    }

    @Test
    public void test02() {
        BookService bean = context.getBean(BookService.class);
        int price = bean.getPrice("ISBN-001");
        System.out.println(price);
    }

    @Test
    public void test03() throws FileNotFoundException {
        MulService bean = context.getBean(MulService.class);
        bean.mulTx();
    }

}
