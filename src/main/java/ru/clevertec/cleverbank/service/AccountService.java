package ru.clevertec.cleverbank.service;

import ru.clevertec.cleverbank.model.Account;

public interface AccountService extends Service<Account, Integer> {
    Account findByUserId(Integer userId);
}
