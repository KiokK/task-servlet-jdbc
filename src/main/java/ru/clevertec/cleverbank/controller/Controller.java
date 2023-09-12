package ru.clevertec.cleverbank.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.command.CommandHelper;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.exception.CommandException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final String COMMAND_NAME = "command";

    private final CommandHelper commandHelper = new CommandHelper();

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        try{
            String commandName = request.getParameter(COMMAND_NAME);
            Command command = commandHelper.getCommand(commandName);
            page = command.execute(request);
        }catch(CommandException e){
            page = PageName.ERROR_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }

    }

}
