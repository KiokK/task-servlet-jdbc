package ru.clevertec.cleverbank.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface SqlMapper<E> {

    E toEntity(ResultSet rs);

    List<E> toEntityList(ResultSet rs);

}
