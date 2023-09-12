package ru.clevertec.cleverbank.service.impl;

import ru.clevertec.cleverbank.aop.CustomLog;
import ru.clevertec.cleverbank.configs.connection.DataSource;
import ru.clevertec.cleverbank.dao.impl.UserDaoImpl;
import ru.clevertec.cleverbank.model.User;
import ru.clevertec.cleverbank.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao = new UserDaoImpl();

    @CustomLog
    @Override
    public User create(User entity) {
        Objects.requireNonNull(entity);
        entity.setId(null);

        try (Connection connection = DataSource.getConnection()) {
            return userDao.create(entity, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public User findById(Integer id) {
        Objects.requireNonNull(id);
        try (Connection connection = DataSource.getConnection()) {
            return userDao.findById(id, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public List<User> findAll() {
        try (Connection connection = DataSource.getConnection()) {
            return userDao.findAll(connection);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @CustomLog
    @Override
    public boolean update(User entity) {
        Objects.requireNonNull(entity);
        try (Connection connection = DataSource.getConnection()) {
            return userDao.update(entity, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public boolean deleteById(Integer id) {
        Objects.requireNonNull(id);
        try (Connection connection = DataSource.getConnection()) {
            return userDao.deleteById(id, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public User findByUsername(String username) {
        Objects.requireNonNull(username);
        try (Connection connection = DataSource.getConnection()) {
            return userDao.findByUsername(username, connection);
        } catch (SQLException e) {
            return null;
        }
    }
}
