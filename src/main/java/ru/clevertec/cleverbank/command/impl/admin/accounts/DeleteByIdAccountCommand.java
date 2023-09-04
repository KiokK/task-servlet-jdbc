package ru.clevertec.cleverbank.command.impl.admin.accounts;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.AccountAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;

public class DeleteByIdAccountCommand implements Command {

    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer accountId = Integer.valueOf(request.getParameter(ID));

        if (accountService.deleteById(accountId)) {
            AccountAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Account with id="+accountId+" has not been removed!");
        return PageName.ERROR_PAGE;
    }

}
