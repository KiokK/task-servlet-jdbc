package ru.clevertec.cleverbank.command.impl.admin.banks;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.BanksAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

public class FindAllBanksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        BanksAttributes.setPageAttributes(request);
        return PageName.CRUD_PAGE;
    }
}
