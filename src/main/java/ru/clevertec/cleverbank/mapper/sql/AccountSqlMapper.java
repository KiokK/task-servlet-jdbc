package ru.clevertec.cleverbank.mapper.sql;

import lombok.SneakyThrows;
import ru.clevertec.cleverbank.model.enums.Currency;
import ru.clevertec.cleverbank.model.enums.Role;
import ru.clevertec.cleverbank.mapper.SqlMapper;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Bank;
import ru.clevertec.cleverbank.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.AMOUNT;
import static ru.clevertec.cleverbank.constants.EntityConstants.BANK_ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.CREATED;
import static ru.clevertec.cleverbank.constants.EntityConstants.CURRENCY;
import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.INTEREST_DATE;
import static ru.clevertec.cleverbank.constants.EntityConstants.PASSWORD;
import static ru.clevertec.cleverbank.constants.EntityConstants.ROLE;
import static ru.clevertec.cleverbank.constants.EntityConstants.TITLE;
import static ru.clevertec.cleverbank.constants.EntityConstants.USERNAME;
import static ru.clevertec.cleverbank.constants.EntityConstants.USER_ID;

public class AccountSqlMapper implements SqlMapper<Account> {

    @SneakyThrows
    @Override
    public Account toEntity(ResultSet rs)  {
        if (rs.isAfterLast())
            return null;

        Account account = new Account();
        account.setId(rs.getInt(ID));
        account.setAmount(rs.getBigDecimal(AMOUNT));
        account.setCurrency(Currency.valueOf(rs.getString(CURRENCY)));
        if (rs.getTimestamp(CREATED) != null)
            account.setCreated(rs.getTimestamp(CREATED).toLocalDateTime());
        account.setInterestDate(rs.getTimestamp(INTEREST_DATE).toLocalDateTime());

        Bank bankId = new Bank();
        bankId.setId(rs.getInt(BANK_ID));
        bankId.setTitle(rs.getString(TITLE));
        account.setBank(bankId);

        User userId = new User();
        userId.setId(rs.getInt(USER_ID));
        userId.setUsername(rs.getString(USERNAME));
        userId.setRole(Role.valueOf(rs.getString(ROLE)));
        userId.setPassword(rs.getString(PASSWORD));
        account.setUser(userId);

        return account;
    }

    @SneakyThrows
    @Override
    public List<Account> toEntityList(ResultSet rs) {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = toEntity(rs);
            if (account == null)
                return accounts;
            accounts.add(account);
        }
        return accounts;
    }

}
