package ru.clevertec.cleverbank.mapper.impl;

import org.mapstruct.Mapper;
import ru.clevertec.cleverbank.dto.BankDto;
import ru.clevertec.cleverbank.model.Bank;

@Mapper
public interface BankMapper {
    BankDto toDto(Bank entity);
    Bank toEntity(BankDto dto);

}
