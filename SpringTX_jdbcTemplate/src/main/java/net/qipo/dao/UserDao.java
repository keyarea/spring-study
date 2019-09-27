package net.qipo.dao;

import net.qipo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(User user){
        String sql = "INSERT INTO user (  name, age ) VALUES (?, ?);";
        jdbcTemplate.update(sql, user.getName(), user.getAge());
    }
}
