package ru.clevertec.cleverbank.dao.impl;

import ru.clevertec.cleverbank.dao.Dao;
import ru.clevertec.cleverbank.mapper.sql.TransactionSqlMapper;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;
import ru.clevertec.cleverbank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.*;

public class TransactionDaoImpl implements Dao<Transaction, Integer> {
    private final TransactionSqlMapper sqlMapper = new TransactionSqlMapper();

    private final String FIND_ALL = """
            SELECT t.*, 
                   b1.title as sender_bank_title, 
                   b2.title as recipient_bank_title 
                    FROM transactions as t JOIN accounts as a1
                ON t.recipient_account_id = a1.id JOIN accounts as a2
                ON t.sender_account_id = a2.id JOIN banks as b1
                ON t.sender_bank_id = b1.id JOIN banks as b2
                ON t.recipient_bank_id = b2.id;
            """;
    private final String INSERT = """
            INSERT INTO transactions(created, currency, amount, sender_bank_id, 
            recipient_bank_id, sender_account_id, recipient_account_id, type)
            VALUES (?,?,?,?,?,?,?,?)
            RETURNING id;
            """;
    private final String FIND_BY_ID = "SELECT * FROM transactions WHERE id = (?)";
    private final String FIND_ALL_BY_SENDER_ACCOUNT_ID_AND_DATE =
            "SELECT * FROM transactions WHERE sender_account_id = (?)  and (created between ? and ?)";

    private final String DELETE_BY_ID = "DELETE FROM transactions WHERE id = (?)";

    private final String FIND_EXPENSES_REVENUE = """
            SELECT SUM(COALESCE(CASE WHEN type = 'REPLENISHMENT' THEN amount END,0)) revenue,
                   SUM(COALESCE(CASE WHEN type = 'DEBITING' or type = 'TRANSFER' THEN amount END,0)) expense 
            FROM transactions
            WHERE (sender_account_id = ? or recipient_account_id = ?) and (created between ? and ?);
            """;

    @Override
    public Transaction create(Transaction entity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(2, entity.getCurrency().toString());
            preparedStatement.setBigDecimal(3, entity.getAmount());
            preparedStatement.setInt(4, entity.getSenderBank().getId());
            preparedStatement.setInt(5, entity.getRecipientBank().getId());
            preparedStatement.setInt(6, entity.getSenderAccount().getId());
            preparedStatement.setInt(7, entity.getRecipientAccount().getId());
            preparedStatement.setString(8, String.valueOf(entity.getType()));

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            entity.setId(rs.getInt(ID));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction findById(Integer id, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return sqlMapper.toEntity(rs);
        } catch (SQLException ignore) {
            return null;
        }
    }

    public Receipt findExpensesAndRevenueById(Integer accountId, LocalDateTime start, LocalDateTime end, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXPENSES_REVENUE);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Receipt(rs.getBigDecimal(REVENUE), rs.getBigDecimal(EXPENSE));
        } catch (SQLException ignore) {
            return null;
        }
    }


    @Override
    public List<Transaction> findAll(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            return sqlMapper.toEntityList(rs);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public List<Transaction> findAllBySenderAccountId(Integer senderAccountId, LocalDateTime start, LocalDateTime end, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_SENDER_ACCOUNT_ID_AND_DATE);
            preparedStatement.setInt(1, senderAccountId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();

            return sqlMapper.toEntityList(rs);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(Transaction entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setInt(1, entity.getId());
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            if (entity.getConfirmed() == null)
                rs.updateTimestamp(CONFIRMED, null);
            else
                rs.updateTimestamp(CONFIRMED, Timestamp.valueOf(entity.getConfirmed()));
            rs.updateRow();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
