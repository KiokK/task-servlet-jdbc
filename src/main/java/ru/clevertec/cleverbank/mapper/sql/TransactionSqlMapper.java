package ru.clevertec.cleverbank.mapper.sql;

import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.TransactionType;
import ru.clevertec.cleverbank.mapper.SqlMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Bank;
import ru.clevertec.cleverbank.model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.AMOUNT;
import static ru.clevertec.cleverbank.constants.EntityConstants.CONFIRMED;
import static ru.clevertec.cleverbank.constants.EntityConstants.CREATED;
import static ru.clevertec.cleverbank.constants.EntityConstants.CURRENCY;
import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.RECIPIENT_ACCOUNT_ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.RECIPIENT_BANK_ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.RECIPIENT_BANK_TITLE;
import static ru.clevertec.cleverbank.constants.EntityConstants.SENDER_ACCOUNT_ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.SENDER_BANK_ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.SENDER_BANK_TITLE;
import static ru.clevertec.cleverbank.constants.EntityConstants.TYPE;

public class TransactionSqlMapper implements SqlMapper<Transaction> {

    @Override
    public Transaction toEntity(ResultSet rs) {
        try {
            if (rs.isAfterLast())
                return null;
            Transaction transaction = new Transaction();

            transaction.setId(rs.getInt(ID));
            transaction.setCreated(rs.getTimestamp(CREATED).toLocalDateTime());
            transaction.setCurrency(Currency.valueOf(rs.getString(CURRENCY)));
            transaction.setAmount(rs.getBigDecimal(AMOUNT));
            if (rs.getTimestamp(CONFIRMED) != null)
                transaction.setConfirmed(rs.getTimestamp(CONFIRMED).toLocalDateTime());
            transaction.setType(TransactionType.valueOf(rs.getString(TYPE)));

            Bank senderBank = new Bank();
            senderBank.setId(rs.getInt(SENDER_BANK_ID));

            Bank recipientBank = new Bank();
            recipientBank.setId(rs.getInt(RECIPIENT_BANK_ID));
            try {
                senderBank.setTitle(rs.getString(SENDER_BANK_TITLE));
                transaction.setSenderBank(senderBank);

                recipientBank.setTitle(rs.getString(RECIPIENT_BANK_TITLE));
                transaction.setRecipientBank(recipientBank);
            } catch (SQLException e) {
                transaction.setSenderBank(senderBank);
                transaction.setRecipientBank(recipientBank);
            }

            Account account = new Account();
            account.setId(rs.getInt(RECIPIENT_ACCOUNT_ID));
            transaction.setRecipientAccount(account);

            account.setId(rs.getInt(SENDER_ACCOUNT_ID));
            transaction.setSenderAccount(account);

            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> toEntityList(ResultSet rs) {
        try {
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                Transaction transaction = toEntity(rs);
                if (transaction == null)
                    return transactions;
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
