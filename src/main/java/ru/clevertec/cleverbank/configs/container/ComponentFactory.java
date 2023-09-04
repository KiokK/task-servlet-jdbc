package ru.clevertec.cleverbank.configs.container;

import ru.clevertec.cleverbank.factory.impl.ExpensesReceiptFactory;
import ru.clevertec.cleverbank.factory.impl.ExtractReceiptFactory;
import ru.clevertec.cleverbank.factory.impl.TransferReceiptFactory;
import ru.clevertec.cleverbank.mapper.json.AccountJsonMapper;
import ru.clevertec.cleverbank.mapper.json.BankJsonMapper;
import ru.clevertec.cleverbank.mapper.json.TransactionJsonMapper;
import ru.clevertec.cleverbank.mapper.json.UserJsonMapper;
import ru.clevertec.cleverbank.mapper.sql.AccountSqlMapper;
import ru.clevertec.cleverbank.mapper.sql.BankSqlMapper;
import ru.clevertec.cleverbank.mapper.sql.TransactionSqlMapper;
import ru.clevertec.cleverbank.mapper.sql.UserSqlMapper;
import ru.clevertec.cleverbank.service.impl.*;

import java.util.HashMap;

public class ComponentFactory {
    private static HashMap<Class<?>, Object> container = new HashMap<>();

    static {
        container.put(TransactionServiceImpl.class, new TransactionServiceImpl());
        container.put(AccountServiceImpl.class, new AccountServiceImpl());
        container.put(UserServiceImpl.class, new UserServiceImpl());
        container.put(BankServiceImpl.class, new BankServiceImpl());
        container.put(AccountTransactionServiceImpl.class, new AccountTransactionServiceImpl());

        container.put(AccountJsonMapper.class, new AccountJsonMapper());
        container.put(UserJsonMapper.class, new UserJsonMapper());
        container.put(BankJsonMapper.class, new BankJsonMapper());
        container.put(TransactionJsonMapper.class, new TransactionJsonMapper());

        container.put(AccountSqlMapper.class, new AccountSqlMapper());
        container.put(UserSqlMapper.class, new UserSqlMapper());
        container.put(BankSqlMapper.class, new BankSqlMapper());
        container.put(TransactionSqlMapper.class, new TransactionSqlMapper());

        container.put(TransferReceiptFactory.class, new TransferReceiptFactory());
        container.put(ExtractReceiptFactory.class, new ExtractReceiptFactory());
        container.put(ExpensesReceiptFactory.class, new ExpensesReceiptFactory());
    }

    public static <T> T getComponent(Class<T> clazz) {
        return (T) container.get(clazz);
    }
}
