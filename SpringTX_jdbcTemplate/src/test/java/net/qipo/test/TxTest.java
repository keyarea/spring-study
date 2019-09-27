package net.qipo.test;

import net.qipo.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TxTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

    @Test
    public void test01() throws SQLException {
        DataSource bean = context.getBean(DataSource.class);
        Connection connection = bean.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test02() {
        System.out.println(jdbcTemplate);
    }

    /**
     * 添加用户
     */
    @Test
    public void test03() {
        String sql = "INSERT INTO user (  name, age ) VALUES (?, ?);";
        int i = jdbcTemplate.update(sql,  "王五", 23);
        System.out.println(i);
    }

    /**
     * 批量插入
     */
    @Test
    public void test04() {
        String sql = "INSERT INTO user (  name, age ) VALUES (?, ?);";
        // List<Object>
        // List的长度就是sql语句要执行多少编
        // Object[] 每次执行要使用的参数
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"张三",12});
        batchArgs.add(new Object[]{"赵四",12});
        batchArgs.add(new Object[]{"王五",12});
        batchArgs.add(new Object[]{"赵六",12});
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    /**
     * 查询id为5的对象，并封装为一个java对象返回
     * 查询age>10的数据库记录，并封装为List返回
     *
     * JavaBean要和数据库中的字段名一致否则无法完成封装
     *
     * jdbcTemplate在方法级别进行了区分
     * 查询集合：jdbcTemplate.query()
     * 查询单个对象： jdbcTemplate.queryForObject()
     *
     *   如果查询没结果就报错
     */
    @Test
    public void test05() {
        String sql = "SELECT id id,name name,age age FROM user WHERE id = ?";
        // RowMapper:每一行和javabean的属性如何映射
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 5);
        }catch (Exception e) {

        }
        System.out.println(user);

    }

    /**
     * 查询age>10的数据库记录，并封装为List返回
     */
    @Test
    public void test06() {
        String sql = "SELECT id id, name name, age age FROM user WHERE age > ?";
        // 封装LIst，集合里面元素的类型
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), 10);

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询age最大的user
     */
    @Test
    public void test07() {
        String sql = "SELECT MAX(age) FROM user";
        // 无论返回单个对象还是单个数据，都是调用queryForObject
        Double aDouble = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(aDouble);
    }

}
