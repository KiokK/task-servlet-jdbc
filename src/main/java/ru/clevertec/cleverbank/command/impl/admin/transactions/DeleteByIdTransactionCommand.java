package ru.clevertec.cleverbank.command.impl.admin.transactions;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.TransactionsAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.service.TransactionService;
import ru.clevertec.cleverbank.service.impl.TransactionServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;

public class DeleteByIdTransactionCommand implements Command {
    private final TransactionService transactionService = ComponentFactory.getComponent(TransactionServiceImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer id = Integer.valueOf(request.getParameter(ID));
        if (transactionService.deleteById(id)) {
            TransactionsAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Can't remove transaction entity!");
        return PageName.ERROR_PAGE;
    }
}
