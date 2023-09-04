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

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.JspConstants.CURRENT_ENTITY;

public class FindByIdUserCommand implements Command {

    private final UserService userService = ComponentFactory.getComponent(UserServiceImpl.class);
    private final UserJsonMapper jsonMapper = ComponentFactory.getComponent(UserJsonMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer userId = Integer.valueOf(request.getParameter(ID));
        User user = userService.findById(userId);
        if (user != null) {

            request.setAttribute(CURRENT_ENTITY, jsonMapper.toJson(user));

            UserAttributes.setPageAttributes(request);
            return PageName.CRUD_PAGE;
        }
        request.setAttribute(MESSAGE, "Пользователь с таким id не найден!");

        return PageName.ERROR_PAGE;
    }

}
