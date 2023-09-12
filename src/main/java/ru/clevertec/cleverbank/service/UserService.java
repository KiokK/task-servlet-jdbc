package ru.clevertec.cleverbank.service;

import ru.clevertec.cleverbank.model.User;

public interface UserService extends Service<User, Integer> {

    User findByUsername(String username);

}
