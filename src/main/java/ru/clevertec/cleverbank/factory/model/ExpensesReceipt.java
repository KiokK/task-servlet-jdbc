package ru.clevertec.cleverbank.factory.model;

import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;

public class ExpensesReceipt extends ReceiptData {
    public Receipt receipt;

    public ExpensesReceipt(Account account, Receipt receipt) {
        super(account);
        this.receipt = receipt;
    }
}
