package ru.clevertec.cleverbank.dao.impl;

import ru.clevertec.cleverbank.dao.Dao;
import ru.clevertec.cleverbank.mapper.sql.AccountSqlMapper;
import ru.clevertec.cleverbank.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.*;

public class AccountDaoImpl implements Dao<Account, Integer> {
    private final String FIND_ALL = "SELECT * FROM accounts as a, banks as b, users as u " +
            "WHERE a.user_id = u.id and a.bank_id = b.id;";

    private final String FIND_BY_ID = "SELECT * FROM accounts as a, banks as b, users as u " +
            "WHERE a.id = (?) and a.user_id = u.id and a.bank_id = b.id;";
    private final String FIND_BY_USER_ID = """
            SELECT * FROM accounts as a, banks as b, users as u 
            WHERE u.id = (?) and a.user_id = u.id and a.bank_id = b.id;
            """;
    private final String DELETE_BY_ID = "DELETE FROM accounts WHERE id = (?);";
    private final String INSERT = "INSERT INTO accounts " +
            "(created, interest_date, user_id, currency, amount, bank_id) VALUES " +
            "(?, ?, ?, ?, ?, ?) RETURNING *;";

    private final AccountSqlMapper sqlMapper = new AccountSqlMapper();

    @Override
    public Account create(Account account, Connection connection) {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedStatement.setInt(3, account.getUser().getId());
            preparedStatement.setString(4, account.getCurrency().toString());
            preparedStatement.setBigDecimal(5, account.getAmount());
            preparedStatement.setInt(6, account.getBank().getId());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            account.setId(rs.getInt(ID));
            return account;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Account findById(Integer id, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    public Account findByUserId(Integer userId, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Account> findAll(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            return sqlMapper.toEntityList(rs);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(Account entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setInt(1, entity.getId());

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            rs.updateTimestamp(INTEREST_DATE, Timestamp.valueOf(entity.getInterestDate()));
            rs.updateBigDecimal(AMOUNT, entity.getAmount());
            rs.updateInt(USER_ID, entity.getUser().getId());
            rs.updateRow();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id, Connection connection) {
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
