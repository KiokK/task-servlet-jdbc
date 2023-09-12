package ru.clevertec.cleverbank.service;

import java.util.List;

public interface Service<T, K> {

        T create(T entity);

        T findById(K id);

        List<T> findAll();

        boolean update(T entity);

        boolean deleteById(K id);
}
