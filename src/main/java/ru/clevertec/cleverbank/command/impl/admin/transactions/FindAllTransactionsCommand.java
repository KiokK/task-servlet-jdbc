package ru.clevertec.cleverbank.command.impl.admin.transactions;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.TransactionsAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

public class FindAllTransactionsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TransactionsAttributes.setPageAttributes(request);
        return PageName.CRUD_PAGE;
    }
}
