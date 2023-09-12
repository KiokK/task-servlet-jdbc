package ru.clevertec.cleverbank.service.impl;

import ru.clevertec.cleverbank.aop.CustomLog;
import ru.clevertec.cleverbank.configs.connection.DataSource;
import ru.clevertec.cleverbank.dao.impl.AccountDaoImpl;
import ru.clevertec.cleverbank.dao.impl.BankDaoImpl;
import ru.clevertec.cleverbank.dao.impl.TransactionDaoImpl;
import ru.clevertec.cleverbank.model.Account;
import ru.clevertec.cleverbank.model.Transaction;
import ru.clevertec.cleverbank.service.AccountTransactionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountTransactionServiceImpl implements AccountTransactionService {
    private HashMap<Integer, Lock> accountLocks = new HashMap();
    private final TransactionDaoImpl transactionDao = new TransactionDaoImpl();
    private final AccountDaoImpl accountDao = new AccountDaoImpl();
    private final BankDaoImpl bankDao = new BankDaoImpl();

    @Override
    @CustomLog
    public Transaction cashOperation(Account account, Transaction transaction) {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            accountDao.update(account, connection);
            transaction = transactionDao.create(transaction, connection);
            connection.commit();
            return transaction;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    @CustomLog
    public Transaction transferCashOperation(Account senderAccount, Account recipientAccount, Transaction transaction) {
        Lock senderLock = getLock(senderAccount.getId());
        Lock recepientLock = getLock(recipientAccount.getId());

        Connection connection = null;
        try {
            boolean isSenderLocked = senderLock.tryLock(10, TimeUnit.SECONDS);
            boolean isRecipientLocked = recepientLock.tryLock(10, TimeUnit.SECONDS);
            if (isRecipientLocked && isSenderLocked) {
                connection = DataSource.getConnection();
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                accountDao.update(senderAccount, connection);
                accountDao.update(recipientAccount, connection);
                transaction = transactionDao.create(transaction, connection);
                transaction.setRecipientBank(bankDao.findById(transaction.getRecipientBank().getId(), connection));
                transaction.setSenderBank(bankDao.findById(transaction.getSenderBank().getId(), connection));

                connection.commit();
                return transaction;
            }
            return null;

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        } finally {
            try {
                recepientLock.unlock();
                senderLock.unlock();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Lock getLock(Integer id) {
        ReentrantLock lock = new ReentrantLock();
        Lock actualLock = accountLocks.putIfAbsent(id, lock);
        if (actualLock == null) {
            actualLock = lock;
        }
        return actualLock;
    }

}
