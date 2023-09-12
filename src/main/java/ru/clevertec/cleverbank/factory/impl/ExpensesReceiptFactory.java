package ru.clevertec.cleverbank.factory.impl;

import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ExpensesReceipt;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;

import java.util.Date;

public class ExpensesReceiptFactory implements ReceiptFactory<ExpensesReceipt> {

    @Override
    public String createReceipt(ExpensesReceipt receiptData) {
        Receipt receipt = receiptData.receipt;
        Account account = receiptData.account;
        return String.format(
                """
                     Money Statement
                       Clever-Bank
                Client:           |%s
                Account:          |%s
                Currency:         |%s
                Creation date:    |%s
                Date and time:    |%s
                Remains:          |%s %s
                
                  Expense      |     Revenue
                -------------------------------
                  -%s                %s
                """,
                account.getUser().getUsername(),
                account.getId(),
                account.getCurrency(),
                account.getCreated(),
                new Date(),
                account.getAmount(),
                account.getCurrency(),
                receipt.getExpense(),
                receipt.getRevenue()
        );
    }

    @Override
    public String getDir() {
        return "/statement-money/";
    }
}
