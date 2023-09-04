package ru.clevertec.cleverbank.factory.model;

import lombok.AllArgsConstructor;
import ru.clevertec.cleverbank.model.Account;

@AllArgsConstructor
public abstract class ReceiptData {
    public Account account;
}
