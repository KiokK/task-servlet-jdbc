package ru.clevertec.cleverbank.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.dto.BankDto;
import ru.clevertec.cleverbank.mapper.JsonMapper;
import ru.clevertec.cleverbank.mapper.impl.BankMapper;
import ru.clevertec.cleverbank.model.Bank;

public class BankJsonMapper implements JsonMapper<Bank> {
    private final BankMapper bankMapper = Mappers.getMapper(BankMapper.class);
    @Override
    public Bank toEntity(String json) {
        BankDto bankDto = null;
        try {
            bankDto = objectMapper.readValue(json, BankDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bankMapper.toEntity(bankDto);
    }

    @Override
    public String toJson(Bank entity) {
        BankDto bankDto = bankMapper.toDto(entity);

        try {
            return objectMapper.writeValueAsString(bankDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
