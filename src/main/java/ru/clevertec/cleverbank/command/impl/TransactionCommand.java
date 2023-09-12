package ru.clevertec.cleverbank.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.mapper.impl.UserAccountMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.TransactionType;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountTransactionServiceImpl;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ru.clevertec.cleverbank.command.CommandName.ACCOUNT_REPLENISHMENT;
import static ru.clevertec.cleverbank.constants.EntityConstants.AMOUNT;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_DATA;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_ID;
import static ru.clevertec.cleverbank.constants.JspConstants.COMMAND;

public class TransactionCommand implements Command {

    private AccountTransactionServiceImpl accountTransactionService = ComponentFactory.getComponent(AccountTransactionServiceImpl.class);
    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private UserAccountMapper userAccountMapper = Mappers.getMapper(UserAccountMapper.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Transaction transaction = new Transaction();
        BigDecimal amount = new BigDecimal(String.valueOf(request.getParameter(AMOUNT)));
        transaction.setAmount(amount);
        Account account = accountService.findById(Integer.valueOf(request.getParameter(ACCOUNT_ID)));
        transaction.setSenderAccount(account);
        transaction.setSenderBank(account.getBank());
        transaction.setRecipientBank(account.getBank());
        transaction.setRecipientAccount(account);
        transaction.setCurrency(Currency.BYN);
        transaction.setConfirmed(LocalDateTime.now());

        if (ACCOUNT_REPLENISHMENT.toString().equals(request.getParameter(COMMAND))) {
            account.setAmount(account.getAmount().add(amount));
            transaction.setType(TransactionType.REPLENISHMENT);
            request.setAttribute(ACCOUNT_DATA, userAccountMapper.toDto(account));
            accountTransactionService.cashOperation(account, transaction);
        } else {
            account.setAmount(account.getAmount().subtract(amount));
            transaction.setType(TransactionType.DEBITING);
            request.setAttribute(ACCOUNT_DATA, userAccountMapper.toDto(account));
            accountTransactionService.cashOperation(account, transaction);
        }
        request.setAttribute(ACCOUNT_DATA,
                userAccountMapper.toDto(accountService.findById(account.getId())));
        return PageName.USER_PAGE;
    }
}
