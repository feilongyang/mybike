package com.yang.springtest.dao;

import com.yang.springtest.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean add(User u) {

        String sql = "INSERT INTO test_user(username,password) VALUES (?,?)";
        int result = jdbcTemplate.update(sql, u.getUsername(), u.getPassword());
        return result == 1;
    }

    public User getUser(String username) {

        String sql = "SELECT * FROM test_user WHERE username=?";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, username);

        return user;
    }
}
