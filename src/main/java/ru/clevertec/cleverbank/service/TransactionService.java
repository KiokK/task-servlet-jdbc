package ru.clevertec.cleverbank.service;

import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService extends Service<Transaction, Integer>{

    List<Transaction> findAllBySenderAccountId(Integer senderAccountId, LocalDateTime start, LocalDateTime end);

    Receipt findExpensesAndRevenueById(Integer accountId, LocalDateTime start, LocalDateTime end);

}
