package ru.clevertec.cleverbank.command.impl.admin.accounts;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.AccountAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.json.AccountJsonMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.JspConstants.CURRENT_ENTITY;

public class FindByIdAccountCommand implements Command {

    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private AccountJsonMapper jsonMapper = ComponentFactory.getComponent(AccountJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer accountId = Integer.valueOf(request.getParameter(ID));
        Account account = accountService.findById(accountId);

        if (account != null) {
            request.setAttribute(CURRENT_ENTITY, jsonMapper.toJson(account));
            AccountAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Account with id="+accountId+" has not been founded!");
        return PageName.ERROR_PAGE;
    }

}
