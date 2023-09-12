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

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.JspConstants.CURRENT_ENTITY;

public class FindByIdBankCommand implements Command {
    private final BankService bankService = ComponentFactory.getComponent(BankServiceImpl.class);
    private final BankJsonMapper jsonMapper = ComponentFactory.getComponent(BankJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer id = Integer.valueOf(request.getParameter(ID));
        Bank bank = bankService.findById(id);
        if (bank != null) {
            request.setAttribute(CURRENT_ENTITY, jsonMapper.toJson(bank));
            BanksAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Bank with id="+id+" has not been founded!");
        return PageName.ERROR_PAGE;
    }

}
