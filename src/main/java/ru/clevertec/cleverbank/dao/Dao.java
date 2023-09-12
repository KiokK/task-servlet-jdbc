package ru.clevertec.cleverbank.dao;

import java.sql.Connection;
import java.util.List;

public interface Dao<T, K> {

    T create(T entity, Connection connection);

    T findById(K id, Connection connection);

    List<T> findAll(Connection connection);

    boolean update(T entity, Connection connection);

    boolean deleteById(K id, Connection connection);

}
