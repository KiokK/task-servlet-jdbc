package ru.clevertec.cleverbank.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.dto.TransactionDto;
import ru.clevertec.cleverbank.mapper.JsonMapper;
import ru.clevertec.cleverbank.mapper.impl.TransactionMapper;
import ru.clevertec.cleverbank.model.Transaction;

public class TransactionJsonMapper implements JsonMapper<Transaction> {

    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Override
    public Transaction toEntity(String json) {
        TransactionDto transactionDto = null;
        try {
            transactionDto = objectMapper.readValue(json, TransactionDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return transactionMapper.toEntity(transactionDto);
    }

    @Override
    public String toJson(Transaction entity) {
        TransactionDto transactionDto = transactionMapper.toDto(entity);
        try {
            return objectMapper.writeValueAsString(transactionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
