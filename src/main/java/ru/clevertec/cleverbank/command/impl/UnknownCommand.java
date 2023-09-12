package ru.clevertec.cleverbank.command.impl;

import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;

public class UnknownCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute(MESSAGE, "Unknown command!");
        return PageName.ERROR_PAGE;
    }

}
