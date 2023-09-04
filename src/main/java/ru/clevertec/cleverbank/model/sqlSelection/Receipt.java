package ru.clevertec.cleverbank.model.sqlSelection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Receipt {
    private BigDecimal revenue;
    private BigDecimal expense;
}
