package ru.clevertec.cleverbank.service;

import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;

public interface AccountTransactionService {

    Transaction cashOperation(Account account, Transaction transaction);

    Transaction transferCashOperation(Account senderAccount, Account recipientAccount, Transaction transaction);

}
