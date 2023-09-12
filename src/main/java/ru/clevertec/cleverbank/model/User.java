package ru.clevertec.cleverbank.model;

import lombok.*;
import ru.clevertec.cleverbank.model.enums.Role;
import ru.clevertec.cleverbank.model.base.IdEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "accounts")
public class User extends IdEntity {

    private String username;
    private String password;
    private Role role;
    private List<Account> accounts;

    public User(Integer id, String username, String password) {
        this.username = username;
        this.password = password;
        this.setId(id);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
