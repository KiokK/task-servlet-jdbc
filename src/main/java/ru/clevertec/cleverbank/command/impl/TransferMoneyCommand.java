package ru.clevertec.cleverbank.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverbank.command.Command;
import ru.clevertec.cleverbank.constants.PageName;
import ru.clevertec.cleverbank.configs.container.ComponentFactory;
import ru.clevertec.cleverbank.exception.CommandException;
import ru.clevertec.cleverbank.factory.ReceiptFactory;
import ru.clevertec.cleverbank.factory.impl.TransferReceiptFactory;
import ru.clevertec.cleverbank.factory.model.ReceiptData;
import ru.clevertec.cleverbank.factory.model.TransferReceipt;
import ru.clevertec.cleverbank.mapper.impl.UserAccountMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.TransactionType;
import ru.clevertec.cleverbank.service.AccountService;
import ru.clevertec.cleverbank.service.impl.AccountTransactionServiceImpl;
import ru.clevertec.cleverbank.service.impl.AccountServiceImpl;
import ru.clevertec.cleverbank.writer.impl.WriterPdf;
import ru.clevertec.cleverbank.writer.impl.WriterTxt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ru.clevertec.cleverbank.constants.EntityConstants.AMOUNT;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_DATA;
import static ru.clevertec.cleverbank.constants.JspConstants.ACCOUNT_ID;
import static ru.clevertec.cleverbank.constants.JspConstants.RECIPIENT_ACCOUNT_ID;

public class TransferMoneyCommand implements Command {

    private AccountTransactionServiceImpl accountTransactionService = ComponentFactory.getComponent(AccountTransactionServiceImpl.class);
    private AccountService accountService = ComponentFactory.getComponent(AccountServiceImpl.class);
    private UserAccountMapper userAccountMapper = Mappers.getMapper(UserAccountMapper.class);
    private ReceiptFactory receiptFactory = ComponentFactory.getComponent(TransferReceiptFactory.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Transaction transaction = new Transaction();

        BigDecimal amount = new BigDecimal(String.valueOf(request.getParameter(AMOUNT)));
        transaction.setAmount(amount);

        Account senderAccount = accountService.findById(Integer.valueOf(request.getParameter(ACCOUNT_ID)));
        Account recepientAccount = accountService.findById(Integer.valueOf(request.getParameter(RECIPIENT_ACCOUNT_ID)));
        BigDecimal remains = senderAccount.getAmount().subtract(amount);
        if (remains.compareTo(new BigDecimal("0.00")) < 0) {
            request.setAttribute(ACCOUNT_DATA, userAccountMapper.toDto(senderAccount));
            return PageName.USER_PAGE;
        }
        senderAccount.setAmount(senderAccount.getAmount().subtract(amount));
        recepientAccount.setAmount(recepientAccount.getAmount().add(amount));
        transaction.setCreated(LocalDateTime.now());
        transaction.setSenderAccount(senderAccount);
        transaction.setSenderBank(senderAccount.getBank());
        transaction.setRecipientBank(recepientAccount.getBank());
        transaction.setRecipientAccount(recepientAccount);
        transaction.setCurrency(Currency.BYN);
        transaction.setType(TransactionType.TRANSFER);

        transaction = accountTransactionService.transferCashOperation(senderAccount, recepientAccount, transaction);

        ReceiptData receiptData = new TransferReceipt(senderAccount, transaction);
        new WriterTxt().writeData(receiptData, receiptFactory);
        new WriterPdf().writeData(receiptData, receiptFactory);

        request.setAttribute(ACCOUNT_DATA,
                userAccountMapper.toDto(accountService.findById(senderAccount.getId())));
        return PageName.USER_PAGE;
    }
}
