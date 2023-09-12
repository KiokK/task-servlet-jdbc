package ru.clevertec.cleverbank.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.factory.impl.ExpensesReceiptFactory;
import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ExpensesReceipt;
import ru.clevertec.cleverbank.factory.model.ReceiptData;
import ru.clevertec.cleverbank.mapper.impl.UserAccountMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.sqlSelection.Receipt;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;
import ru.clevertec.cleverbank.service.impl.TransactionServiceImpl;
import ru.clevertec.cleverbank.writer.impl.WriterPdf;
import ru.clevertec.cleverbank.writer.impl.WriterTxt;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_DATA;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_ID;
import static ru.clevertec.cleverbank.constants.JspConstants.END_DATE;
import static ru.clevertec.cleverbank.constants.JspConstants.START_DATE;
import static ru.clevertec.cleverbank.constants.Format.DATE_FORMATTER;

public class ExpensesCommand implements Command {

    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private UserAccountMapper userAccountMapper = Mappers.getMapper(UserAccountMapper.class);
    private ReceiptFactory receiptFactory = ComponentFactory.getComponent(ExpensesReceiptFactory.class);
    private TransactionServiceImpl transactionService = ComponentFactory.getComponent(TransactionServiceImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Account currentUserAccount = accountService.findById(Integer.valueOf(request.getParameter(ACCOUNT_ID)));
        if (currentUserAccount == null) {
            request.setAttribute(MESSAGE, "Account for user not exists!");
            return PageName.ERROR_PAGE;
        }
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = currentUserAccount.getCreated();
        try {
            endDate = LocalDateTime.parse(request.getParameter(END_DATE), DATE_FORMATTER);
            startDate = LocalDateTime.parse(request.getParameter(START_DATE), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
        }
        Receipt receipt = transactionService.findExpensesAndRevenueById(currentUserAccount.getId(), startDate, endDate);
        if (receipt == null) {
            request.setAttribute(MESSAGE, "There is no data!");
            return PageName.ERROR_PAGE;
        }

        ReceiptData receiptData = new ExpensesReceipt(currentUserAccount, receipt);
        new WriterTxt().writeData(receiptData, receiptFactory);
        new WriterPdf().writeData(receiptData, receiptFactory);

        request.setAttribute(ACCOUNT_DATA, userAccountMapper.toDto(currentUserAccount));
        return PageName.USER_PAGE;
    }

}
