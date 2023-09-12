package ru.clevertec.cleverbank.command.impl.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.UserAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

public class FindAllUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        UserAttributes.setPageAttributes(request);
        return PageName.CRUD_PAGE;
    }

}

