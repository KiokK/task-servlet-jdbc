package ru.clevertec.cleverbank.command.impl.admin.transactions;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.TransactionsAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.json.TransactionJsonMapper;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.service.TransactionService;
import ru.clevertec.cleverbank.service.impl.TransactionServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.JspConstants.CURRENT_ENTITY;

public class FindByIdTransactionCommand implements Command {
    private final TransactionService transactionService = ComponentFactory.getComponent(TransactionServiceImpl.class);
    private final TransactionJsonMapper jsonMapper = ComponentFactory.getComponent(TransactionJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer id = Integer.valueOf(request.getParameter(ID));
        Transaction transaction = transactionService.findById(id);
        if (transaction != null) {
            request.setAttribute(CURRENT_ENTITY, jsonMapper.toJson(transaction));
            TransactionsAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Transaction with id="+id+" has not been founded!");
        return PageName.ERROR_PAGE;
    }
}
