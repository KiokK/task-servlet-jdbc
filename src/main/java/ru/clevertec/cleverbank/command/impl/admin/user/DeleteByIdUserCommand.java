package ru.clevertec.cleverbank.command.impl.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.UserAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.service.UserService;
import ru.clevertec.cleverbank.service.impl.UserServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;

public class DeleteByIdUserCommand implements Command {

    private final UserService userService = ComponentFactory.getComponent(UserServiceImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer userId = Integer.valueOf(request.getParameter(ID));

        if (userService.deleteById(userId)) {
            UserAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }

        request.setAttribute(MESSAGE, "User с таким id не существует!");
        return PageName.ERROR_PAGE;
    }

}
