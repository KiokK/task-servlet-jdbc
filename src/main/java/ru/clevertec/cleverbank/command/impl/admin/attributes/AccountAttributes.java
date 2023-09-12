package ru.clevertec.cleverbank.command.impl.admin.attributes;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;

import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.TABLE_ACCOUNTS;
import static ru.clevertec.cleverbank.constants.JspConstants.ENTITY_LIST;
import static ru.clevertec.cleverbank.constants.JspConstants.TABLE_NAME;

public class AccountAttributes {
    private static final AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);

    public static void setPageAttributes(HttpServletRequest request) {

        List<Account> accounts = accountService.findAll();
        request.setAttribute(ENTITY_LIST, accounts);
        request.setAttribute(TABLE_NAME, TABLE_ACCOUNTS);
    }
}
