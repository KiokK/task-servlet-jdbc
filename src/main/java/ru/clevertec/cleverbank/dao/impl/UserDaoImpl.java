package ru.clevertec.cleverbank.dao.impl;

import ru.clevertec.cleverbank.dao.Dao;
import ru.clevertec.cleverbank.exception.NullEntityIdException;
import ru.clevertec.cleverbank.mapper.sql.UserSqlMapper;
import ru.clevertec.cleverbank.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;

public class UserDaoImpl implements Dao<User, Integer> {
    private final UserSqlMapper sqlMapper = new UserSqlMapper();
    private final String FIND_ALL = "SELECT * FROM users;";
    private final String FIND_BY_ID = "SELECT * from users WHERE id = (?);";
    private final String DELETE_BY_ID = "delete from users WHERE id = (?);";
    private final String FIND_BY_USERNAME = "SELECT * from users WHERE username = (?);";
    private final String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?, ?, ?) RETURNING *;";

    private final String UPDATE_USER = "UPDATE users SET username=?, password=?, role=? WHERE id=?;";

    @Override
    public User create(User user, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            user.setId(rs.getInt(ID));
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public User findByUsername(String username, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Integer id, Connection connection) {
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            return sqlMapper.toEntityList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user, Connection connection) {
        if (user.getId() == null)
            throw new NullEntityIdException();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(user.getRole()));
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public boolean deleteById(Integer id, Connection connection) {
        if (id == null)
            throw new NullEntityIdException();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
