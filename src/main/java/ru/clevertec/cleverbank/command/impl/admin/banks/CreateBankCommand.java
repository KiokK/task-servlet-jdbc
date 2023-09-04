package ru.clevertec.cleverbank.command.impl.admin.banks;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.BanksAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.json.BankJsonMapper;
import ru.clevertec.cleverbank.model.Bank;
import ru.clevertec.cleverbank.service.BankService;
import ru.clevertec.cleverbank.service.impl.BankServiceImpl;

import static ru.clevertec.cleverbank.constants.JspConstants.JSON_DATA;

public class CreateBankCommand implements Command {

    private final BankService bankService = ComponentFactory.getComponent(BankServiceImpl.class);
    private final BankJsonMapper jsonMapper = ComponentFactory.getComponent(BankJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Bank bank = jsonMapper.toEntity(request.getParameter(JSON_DATA));
        if (bankService.create(bank) != null) {
            BanksAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Can't create bank entity!");
        return PageName.ERROR_PAGE;
    }

}
