package ru.clevertec.cleverbank.dto;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.cleverbank.model.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {
    public Integer id;
    public Currency currency;
    public BigDecimal amount;
    public LocalDateTime created;
    public LocalDateTime interest_date;

    public Integer user_id;
    public Integer bank_id;
}
