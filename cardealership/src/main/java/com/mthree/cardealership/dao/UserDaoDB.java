package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lewi
 */
@Repository
public class UserDaoDB implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserById(int userId) {
        try {
            final String GET_USER_BY_ID = "SELECT * FROM Users WHERE UserId = ?";
            return jdbc.queryForObject(GET_USER_BY_ID, new UserMapper(), userId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            final String GET_USER_BY_EMAIL = "SELECT * FROM Users WHERE Email = ?";
            return jdbc.queryForObject(GET_USER_BY_EMAIL, new UserMapper(), email);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String GET_ALL_USERS = "SELECT * FROM users";
        return jdbc.query(GET_ALL_USERS, new UserMapper());
    }

    @Override
    public List<User> searchUser(int userId) {
        return null;
    }

    @Override
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO Users(Email, Password, FirstName, LastName, PermissionId, LoggedIn)" +
                "VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_USER,
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPermissionId(),
                user.isLoggedIn());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(newId);
        return user;
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "Update Users SET Email = ?, Password = ?, FirstName = ?, LastName = ?, " +
                "PermissionId = ? WHERE UserId = ?";
        jdbc.update(UPDATE_USER,
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPermissionId(),
                user.isLoggedIn());
    }

    @Override
    public void deleteUserById(int userId) {
        final String DELETE_USER = "DELETE FROM Users WHERE users.UserId = ?";
        jdbc.update(DELETE_USER, userId);
    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("UserId"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setPermissionId(rs.getInt("permissionId"));
            user.setPassword(rs.getString("password"));
            user.setLoggedIn(rs.getBoolean("loggedIn"));

            return user;
        }
    }
}
