package ru.clevertec.cleverbank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.cleverbank.model.base.BaseEntity;
import ru.clevertec.cleverbank.model.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class Account extends BaseEntity {
    private User user;
    private Currency currency;
    private BigDecimal amount;
    private Bank bank;
    private LocalDateTime interestDate;
}
