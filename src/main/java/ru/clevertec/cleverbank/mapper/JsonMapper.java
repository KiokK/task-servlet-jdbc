package ru.clevertec.cleverbank.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface JsonMapper<E> {

    ObjectMapper objectMapper = com.fasterxml.jackson.databind.json.JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    E toEntity(String json);

    String toJson(E entity);
}
