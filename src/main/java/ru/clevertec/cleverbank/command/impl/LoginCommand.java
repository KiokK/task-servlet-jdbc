package ru.clevertec.cleverbank.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.impl.admin.attributes.UserAttributes;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.model.enums.Role;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.impl.UserAccountMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.User;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.UserService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;
import ru.clevertec.cleverbank.service.impl.UserServiceImpl;

import static ru.clevertec.cleverbank.constants.EntityConstants.PASSWORD;
import static ru.clevertec.cleverbank.constants.EntityConstants.USERNAME;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_DATA;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_ID;

public class LoginCommand implements Command {

    private UserService userService = ComponentFactory.getComponent(UserServiceImpl.class);
    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private UserAccountMapper userAccountMapper = Mappers.getMapper(UserAccountMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = userService.findByUsername(request.getParameter(USERNAME));
        if (user != null && user.getPassword().equals(request.getParameter(PASSWORD))) {
            if (Role.ADMIN.equals(user.getRole())) {
                UserAttributes.setPageAttributes(request);
                return PageName.CRUD_PAGE;
            } else {
                Account currentUserAccount = accountService.findById(Integer.valueOf(request.getParameter(ACCOUNT_ID)));
                if (currentUserAccount == null || currentUserAccount.getUser().getId() != user.getId()) {
                    request.setAttribute(MESSAGE, "Account for user not exists!");
                    return PageName.ERROR_PAGE;
                } else
                    request.setAttribute(ACCOUNT_DATA, userAccountMapper.toDto(currentUserAccount));
                return PageName.USER_PAGE;
            }
        }
        request.setAttribute(MESSAGE, "Incorrect username or password!");
        return PageName.ERROR_PAGE;
    }

}
