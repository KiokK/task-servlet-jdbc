package ru.clevertec.cleverbank.command;

import jakarta.servlet.http.HttpServletRequest;
import ru.clevertec.cleverbank.exception.CommandException;


public interface Command {

    String MESSAGE = "message";

    String execute(HttpServletRequest request) throws CommandException;

}
