package ru.clevertec.cleverbank.factory.model;

import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;


public class TransferReceipt extends ReceiptData {

    public Transaction transaction;

    public TransferReceipt(Account senderAccount, Transaction transaction) {
        super(senderAccount);
        this.transaction = transaction;
    }

}
