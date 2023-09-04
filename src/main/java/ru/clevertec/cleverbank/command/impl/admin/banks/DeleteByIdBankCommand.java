package ru.clevertec.cleverbank.command.impl.admin.banks;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.BanksAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.service.BankService;
import ru.clevertec.cleverbank.service.impl.BankServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;

public class DeleteByIdBankCommand implements Command {

    private final BankService bankService = ComponentFactory.getComponent(BankServiceImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer id = Integer.valueOf(request.getParameter(ID));
        if (bankService.deleteById(id)) {
            BanksAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Can't remove bank entity!");
        return PageName.ERROR_PAGE;
    }
}
