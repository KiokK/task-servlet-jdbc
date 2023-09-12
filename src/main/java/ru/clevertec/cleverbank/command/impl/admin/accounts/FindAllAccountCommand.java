package ru.clevertec.cleverbank.command.impl.admin.accounts;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.AccountAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

public class FindAllAccountCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AccountAttributes.setPageAttributes(request);
        return PageName.CRUD_PAGE;
    }

}
