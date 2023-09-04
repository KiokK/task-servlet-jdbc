package ru.clevertec.cleverbank.command.impl.admin.attributes;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.model.Bank;
import ru.clevertec.cleverbank.service.BankService;
import ru.clevertec.cleverbank.service.impl.BankServiceImpl;

import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.TABLE_BANKS;
import static ru.clevertec.cleverbank.constants.JspConstants.ENTITY_LIST;
import static ru.clevertec.cleverbank.constants.JspConstants.TABLE_NAME;

public class BanksAttributes {

    private static final BankService bankService = ComponentFactory.getComponent(BankServiceImpl.class);

    public static void setPageAttributes(HttpServletRequest request) {
        List<Bank> banks = bankService.findAll();
        request.setAttribute(ENTITY_LIST, banks);
        request.setAttribute(TABLE_NAME, TABLE_BANKS);
    }
}
