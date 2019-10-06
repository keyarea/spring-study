package net.qipo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 1、减余额
     *
     * 减去某个用户余额
     */
    public void updateBalance(String userName, int price) {
        String sql = "UPDATE account SET balance=balance-? WHERE username=?";
        jdbcTemplate.update(sql, price, userName);

    }

    /**
     * 2、按照图书的isbn获取某本图书的价格
     * @return
     */
    public int getPrice(String isbn) {
        String sql = "SELECT price FROM book WHERE isbn=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    /**
     * 3、减库存,减去某本书的库存;为了简单起见,每次减去一本
     */
    public void updateStock(String isbn) {
        String sql = "UPDATE book_stock SET stock=stock-1 WHERE isbn=?";
        jdbcTemplate.update(sql, isbn);

    }
}
