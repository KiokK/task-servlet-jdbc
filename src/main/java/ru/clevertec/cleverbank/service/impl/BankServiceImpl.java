package ru.clevertec.cleverbank.service.impl;

import ru.clevertec.cleverbank.aop.CustomLog;
import ru.clevertec.cleverbank.configs.connection.DataSource;
import ru.clevertec.cleverbank.dao.impl.BankDaoImpl;
import ru.clevertec.cleverbank.model.Bank;
import ru.clevertec.cleverbank.service.BankService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankServiceImpl implements BankService {

    private final BankDaoImpl bankDao = new BankDaoImpl();

    @CustomLog
    @Override
    public Bank create(Bank entity) {
        Objects.requireNonNull(entity);
        entity.setId(null);
        try (Connection connection = DataSource.getConnection()) {
            return bankDao.create(entity, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public Bank findById(Integer id) {
        Objects.requireNonNull(id);

        try (Connection connection = DataSource.getConnection()) {
            return bankDao.findById(id, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public List<Bank> findAll() {

        try (Connection connection = DataSource.getConnection()) {
            return bankDao.findAll(connection);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @CustomLog
    @Override
    public boolean update(Bank entity) {
        Objects.requireNonNull(entity);

        try (Connection connection = DataSource.getConnection()) {
            return bankDao.update(entity, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public boolean deleteById(Integer id) {
        Objects.requireNonNull(id);

        try (Connection connection = DataSource.getConnection()) {
            return bankDao.deleteById(id, connection);
        } catch (SQLException e) {
            return false;
        }
    }
}
