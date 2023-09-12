package ru.clevertec.cleverbank.factory.model;

import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;

import java.util.List;

public class ExtractReceipt extends ReceiptData {
    public List<Transaction> transactions;

    public ExtractReceipt(Account account, List<Transaction> transactions) {
        super(account);
        this.transactions = transactions;
    }
}
