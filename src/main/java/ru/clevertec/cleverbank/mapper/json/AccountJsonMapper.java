package ru.clevertec.cleverbank.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.dto.AccountDto;
import ru.clevertec.cleverbank.mapper.JsonMapper;
import ru.clevertec.cleverbank.mapper.impl.AccountMapper;
import ru.clevertec.cleverbank.model.Account;

public class AccountJsonMapper implements JsonMapper<Account> {
    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @Override
    public Account toEntity(String json) {
        AccountDto accountDto = null;
        try {
            accountDto = objectMapper.readValue(json, AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return accountMapper.toEntity(accountDto);
    }

    @Override
    public String toJson(Account account)  {
        AccountDto accountDto = accountMapper.toDto(account);
        try {
            return objectMapper.writeValueAsString(accountDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
