package ru.clevertec.cleverbank.dao.impl;

import ru.clevertec.cleverbank.dao.Dao;
import ru.clevertec.cleverbank.exception.NullEntityIdException;
import ru.clevertec.cleverbank.mapper.sql.BankSqlMapper;
import ru.clevertec.cleverbank.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.TITLE;

public class BankDaoImpl implements Dao<Bank, Integer> {

    private final String FIND_ALL = "SELECT * FROM banks;";
    private final String FIND_BY_ID = "SELECT * FROM banks WHERE id = (?);";
    private final String DELETE_BY_ID = "DELETE FROM banks WHERE id = (?);";
    private final String INSERT = "INSERT INTO banks(title) VALUES(?) RETURNING *;";

    private final BankSqlMapper sqlMapper = new BankSqlMapper();

    @Override
    public Bank create(Bank entity, Connection connection) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, entity.getTitle());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            entity.setId(rs.getInt(ID));
            return entity;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Bank findById(Integer id, Connection connection) {
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
    public List<Bank> findAll(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            return sqlMapper.toEntityList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Bank entity, Connection connection) {
        try (PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setInt(1, entity.getId());
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            rs.updateString(TITLE, entity.getTitle());
            rs.updateRow();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
