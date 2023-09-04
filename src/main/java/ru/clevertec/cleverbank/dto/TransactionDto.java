package ru.clevertec.cleverbank.dto;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto {
    public Integer id;
    public Currency currency;
    public BigDecimal amount;
    public LocalDateTime created;
    public Integer sender_bank_id;
    public Integer recipient_bank_id;
    public Integer sender_account_id;
    public Integer recipient_account_id;
    public TransactionType type;
    public LocalDateTime confirmed;

}
