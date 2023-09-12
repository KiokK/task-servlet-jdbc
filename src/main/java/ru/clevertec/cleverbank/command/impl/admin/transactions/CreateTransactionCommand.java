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

import static ru.clevertec.cleverbank.constants.JspConstants.JSON_DATA;

public class CreateTransactionCommand implements Command {

    private final TransactionService transactionService = ComponentFactory.getComponent(TransactionServiceImpl.class);
    private final TransactionJsonMapper jsonMapper = ComponentFactory.getComponent(TransactionJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Transaction transaction = jsonMapper.toEntity(request.getParameter(JSON_DATA));
        if (transactionService.create(transaction) != null) {
            TransactionsAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Can't create transaction entity!");
        return PageName.ERROR_PAGE;
    }

}
