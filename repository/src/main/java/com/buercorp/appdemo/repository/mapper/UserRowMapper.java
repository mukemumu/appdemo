package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setLoginId(rs.getString("login_id"));
        user.setPassword(rs.getString("password"));
        user.setBirthday(rs.getString("birthday"));
        user.setSex(rs.getByte("sex"));
        user.setCity(rs.getString("city"));
        user.setPhone(rs.getString("phone"));
        user.setMail(rs.getString("mail"));
        user.setAvatar(rs.getString("avatar"));
        user.setIdentity(rs.getByte("identity"));
        user.setFirstWorkingTime(rs.getDate("first_working_time"));
        user.setIsValid(rs.getByte("is_valid"));
        user.setCreateTime(rs.getDate("create_time"));
        user.setUpdateTime(rs.getDate("update_time"));
        return user;
    }
}