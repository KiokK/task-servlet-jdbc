package ru.clevertec.cleverbank.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.cleverbank.dto.AccountDto;
import ru.clevertec.cleverbank.model.Account;

import java.time.LocalDateTime;

import static ru.clevertec.cleverbank.constants.Format.PATTERN_DATE_TIME_POINT;

@Mapper(imports = LocalDateTime.class)
public interface AccountMapper {

    @Mapping(source = "created", target = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(source = "interestDate", target = "interest_date", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(source = "user.id", target = "user_id")
    @Mapping(source = "bank.id", target = "bank_id")
    AccountDto toDto(Account entity);

    @Mapping(target = "created", source = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(target = "interestDate", source = "interest_date", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(target = "user.id", source = "user_id")
    @Mapping(target = "bank.id", source = "bank_id")
    Account toEntity(AccountDto dto);
}
