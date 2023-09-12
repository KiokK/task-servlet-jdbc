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

import static ru.clevertec.cleverbank.constants.JspConstants.JSON_DATA;

public class CreateAccountCommand implements Command {

    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private AccountJsonMapper jsonMapper = ComponentFactory.getComponent(AccountJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Account account = jsonMapper.toEntity(request.getParameter(JSON_DATA));

        if (accountService.create(account) != null) {
            AccountAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Can't create account entity!");
        return PageName.ERROR_PAGE;
    }

}
