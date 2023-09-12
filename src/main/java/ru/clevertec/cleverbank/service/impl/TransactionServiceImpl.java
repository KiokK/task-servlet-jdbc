package ru.clevertec.cleverbank.service.impl;

import ru.clevertec.cleverbank.aop.CustomLog;
import ru.clevertec.cleverbank.configs.connection.DataSource;
import ru.clevertec.cleverbank.dao.impl.TransactionDaoImpl;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;
import ru.clevertec.cleverbank.service.TransactionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionDaoImpl transactionDao = new TransactionDaoImpl();

    @CustomLog
    @Override
    public Transaction create(Transaction entity) {
        Objects.requireNonNull(entity);
        entity.setId(null);
        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.create(entity, connection);
        } catch (SQLException e) {
            return null;
        }
    }
    @CustomLog
    @Override
    public Transaction findById(Integer id) {
        Objects.requireNonNull(id);

        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.findById(id, connection);
        } catch (SQLException e) {
            return null;
        }
    }

    @CustomLog
    @Override
    public List<Transaction> findAll() {

        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.findAll(connection);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @CustomLog
    @Override
    public boolean update(Transaction entity) {
        Objects.requireNonNull(entity);
        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.update(entity, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public boolean deleteById(Integer id) {
        Objects.requireNonNull(id);
        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.deleteById(id, connection);
        } catch (SQLException e) {
            return false;
        }
    }

    @CustomLog
    @Override
    public List<Transaction> findAllBySenderAccountId(Integer senderAccountId, LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(senderAccountId);
        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.findAllBySenderAccountId(senderAccountId, start, end, connection);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @CustomLog
    @Override
    public Receipt findExpensesAndRevenueById(Integer accountId, LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(accountId);
        try (Connection connection = DataSource.getConnection()) {
            return transactionDao.findExpensesAndRevenueById(accountId, start, end, connection);
        } catch (SQLException e) {
            return null;
        }
    }
}
