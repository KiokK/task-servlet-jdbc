package ru.clevertec.cleverbank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.TransactionType;
import ru.clevertec.cleverbank.model.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class Transaction extends BaseEntity {

    private Currency currency;
    private BigDecimal amount;
    private Bank senderBank;
    private Bank recipientBank;
    private Account senderAccount;
    private Account recipientAccount;
    private TransactionType type;
    private LocalDateTime confirmed;
}
