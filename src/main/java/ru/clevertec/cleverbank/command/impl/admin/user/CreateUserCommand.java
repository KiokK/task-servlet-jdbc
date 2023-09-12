package ru.clevertec.cleverbank.command.impl.admin.user;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.UserAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.json.UserJsonMapper;
import ru.clevertec.cleverbank.model.User;
import ru.clevertec.cleverbank.service.UserService;
import ru.clevertec.cleverbank.service.impl.UserServiceImpl;

import static ru.clevertec.cleverbank.constants.JspConstants.JSON_DATA;

public class CreateUserCommand implements Command {

    private final UserService userService = ComponentFactory.getComponent(UserServiceImpl.class);
    private final UserJsonMapper jsonMapper = ComponentFactory.getComponent(UserJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = jsonMapper.toEntity(request.getParameter(JSON_DATA));
        if (userService.findByUsername(user.getUsername()) != null) {
            request.setAttribute(MESSAGE, "User with the same username already exists!");
            return PageName.ERROR_PAGE;
        }

        if (userService.create(user) != null) {
            UserAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }

        return PageName.ERROR_PAGE;
    }

}
