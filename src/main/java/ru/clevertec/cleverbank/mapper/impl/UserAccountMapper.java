package ru.clevertec.cleverbank.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.cleverbank.dto.UserAccountDto;
import ru.clevertec.cleverbank.model.Account;

import static ru.clevertec.cleverbank.constants.Format.PATTERN_DATE_TIME_POINT;

@Mapper
public interface UserAccountMapper {

    @Mapping(source = "user.role", target = "role")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "bank.title", target = "title")
    @Mapping(source = "created", target = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    UserAccountDto toDto(Account entity);

    @Mapping(target = "user.role", source = "role")
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "bank.title", source = "title")
    @Mapping(target = "created", source = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    Account toEntity(UserAccountDto dto);
}
