package ru.clevertec.cleverbank.factory.impl;

import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.TransferReceipt;
import ru.clevertec.cleverbank.model.Transaction;

import java.sql.Timestamp;

public class TransferReceiptFactory implements ReceiptFactory<TransferReceipt> {

    @Override
    public String createReceipt(TransferReceipt receiptData) {
        Transaction transaction = receiptData.transaction;
        return String.format("""
                        --------------------------------------
                        |           Bank receipt             |
                          Receipt number:          %s
                          %s
                          SenderBank:              %s
                          RecipientBank:           %s
                          RecipientAccount:        %s
                          SenderAccount:           %s
                          Amount:                  %s %s
                        --------------------------------------
                        """,
                transaction.getId(),
                Timestamp.valueOf(transaction.getCreated()),
                transaction.getSenderBank().getTitle(),
                transaction.getRecipientBank().getTitle(),
                transaction.getRecipientAccount().getId(),
                transaction.getSenderAccount().getId(),
                transaction.getAmount(), transaction.getCurrency().toString()
        );
    }

    @Override
    public String getDir() {
        return "/check/";
    }

}
