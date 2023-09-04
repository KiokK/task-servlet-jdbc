package ru.clevertec.cleverbank.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.cleverbank.dto.TransactionDto;
import ru.clevertec.cleverbank.model.Transaction;

import static ru.clevertec.cleverbank.constants.Format.PATTERN_DATE_TIME_POINT;

@Mapper
public interface TransactionMapper {

    @Mapping(source = "created", target = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(source = "senderAccount.id", target = "sender_account_id")
    @Mapping(source = "recipientAccount.id", target = "recipient_account_id")
    @Mapping(source = "senderBank.id", target = "sender_bank_id")
    @Mapping(source = "recipientBank.id", target = "recipient_bank_id")
    TransactionDto toDto(Transaction entity);

    @Mapping(source = "created", target = "created", dateFormat = PATTERN_DATE_TIME_POINT)
    @Mapping(target = "senderAccount.id", source = "sender_account_id")
    @Mapping(target = "recipientAccount.id", source = "recipient_account_id")
    @Mapping(target = "senderBank.id", source = "sender_bank_id")
    @Mapping(target = "recipientBank.id", source = "recipient_bank_id")
    Transaction toEntity(TransactionDto dto);

}