package net.qipo.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationContextTest01 {
    private ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext02.xml");

    @Test
    public void test01() throws SQLException {
        // 从容器中拿到链接池
//        DataSource dataSource = (DataSource) context.getBean("dataSource");

        // 按照类型获取组件可以获取到这个类型下的所有实现子类等等
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test02() {
        Book book = context.getBean(Book.class);
        System.out.println(book);
    }

    @Test
    public void test03() {
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test04() {
        Person person = context.getBean("person01", Person.class);
        System.out.println(person);
    }
}
