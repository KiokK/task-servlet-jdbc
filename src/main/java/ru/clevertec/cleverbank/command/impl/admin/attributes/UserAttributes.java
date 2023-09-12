package ru.clevertec.cleverbank.command.impl.admin.attributes;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.model.User;
import ru.clevertec.cleverbank.service.UserService;
import ru.clevertec.cleverbank.service.impl.UserServiceImpl;

import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.TABLE_USERS;
import static ru.clevertec.cleverbank.constants.JspConstants.ENTITY_LIST;
import static ru.clevertec.cleverbank.constants.JspConstants.TABLE_NAME;

public class UserAttributes {

    private static final UserService userService = ComponentFactory.getComponent(UserServiceImpl.class);

    public static void setPageAttributes(HttpServletRequest request) {

        List<User> users = userService.findAll();
        request.setAttribute(ENTITY_LIST, users);
        request.setAttribute(TABLE_NAME, TABLE_USERS);
    }

}
