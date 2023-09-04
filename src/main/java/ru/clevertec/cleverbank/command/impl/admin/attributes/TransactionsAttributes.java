package ru.clevertec.cleverbank.command.impl.admin.attributes;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.service.TransactionService;
import ru.clevertec.cleverbank.service.impl.TransactionServiceImpl;

import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.TABLE_TRANSACTIONS;
import static ru.clevertec.cleverbank.constants.JspConstants.ENTITY_LIST;
import static ru.clevertec.cleverbank.constants.JspConstants.TABLE_NAME;

public class TransactionsAttributes {

    private static final TransactionService transactionService = ComponentFactory.getComponent(TransactionServiceImpl.class);

    public static void setPageAttributes(HttpServletRequest request) {

        List<Transaction> transactions = transactionService.findAll();
        request.setAttribute(ENTITY_LIST, transactions);
        request.setAttribute(TABLE_NAME, TABLE_TRANSACTIONS);
    }
}
