package ru.clevertec.cleverbank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserAccountDto {
    public Integer id;
    public String username;
    public String role;

    public LocalDateTime created;
    public String currency;
    public BigDecimal amount;
    public String title;
}
