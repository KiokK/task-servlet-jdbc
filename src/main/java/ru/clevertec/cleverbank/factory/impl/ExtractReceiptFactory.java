package ru.clevertec.cleverbank.factory.impl;

import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ExtractReceipt;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ExtractReceiptFactory implements ReceiptFactory<ExtractReceipt> {

    @Override
    public String createReceipt(ExtractReceipt receiptData) {
        List<Transaction> transactions = receiptData.transactions;
        Account account = receiptData.account;
        String top =  String.format(
                """
                                         Extract
                                       Clever-Bank
                Client:           |%s
                Account:          |%s
                Currency:         |%s
                Creation date:    |%s
                Date and time:    |%s
                Remains:          |%s %s
                
                Date            |          Description              | Amount
                -------------------------------------------------------------------
                """,
                account.getUser().getUsername(),
                account.getId(),
                account.getCurrency(),
                Timestamp.valueOf(account.getCreated()),
                new Date(),
                account.getAmount(),
                account.getCurrency()
        );
        StringBuffer extract = new StringBuffer(top);
        for (int i = 0; i < transactions.size(); i++)
            extract.append(String.format("%s  |          %s              | %s %s \n",
                    Timestamp.valueOf(transactions.get(i).getCreated()),
                    transactions.get(i).getType(),
                    transactions.get(i).getAmount(),
                    transactions.get(i).getCurrency()));
        return extract.toString();
    }

    @Override
    public String getDir() {
        return "/statement-money/";
    }

}
