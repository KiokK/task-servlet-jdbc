package ru.clevertec.cleverbank.service.impl;

import ru.clevertec.cleverbank.aop.CustomLog;
import ru.clevertec.cleverbank.configs.connection.DataSource;
import ru.clevertec.cleverbank.dao.impl.AccountDaoImpl;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.service.AccountService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountService {

    private final AccountDaoImpl accountDao = new AccountDaoImpl();

    @CustomLog
    @Override
    public Account create(Account entity) {
        Objects.requireNonNull(entity);
        entity.setId(null);
        try (Connection connection = DataSource.getConnection()) {
            return accountDao.create(entity, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public Account findById(Integer id) {
        Objects.requireNonNull(id);
        try (Connection connection = DataSource.getConnection()) {
            return accountDao.findById(id, connection);
        } catch (SQLException ignored) {
            return null;
        }
    }

    @CustomLog
    @Override
    public List<Account> findAll() {
        try (Connection connection = DataSource.getConnection()) {
            return accountDao.findAll(connection);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @CustomLog
    @Override
    public boolean update(Account entity) {
        Objects.requireNonNull(entity);

        try (Connection connection = DataSource.getConnection()) {
            return accountDao.update(entity, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public boolean deleteById(Integer id) {
        Objects.requireNonNull(id);
        try (Connection connection = DataSource.getConnection()) {
            return accountDao.deleteById(id, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public Account findByUserId(Integer userId) {
        Objects.requireNonNull(userId);
        try (Connection connection = DataSource.getConnection()) {
            return accountDao.findByUserId(userId, connection);
        } catch (SQLException e) {
            return null;
        }
    }
}
