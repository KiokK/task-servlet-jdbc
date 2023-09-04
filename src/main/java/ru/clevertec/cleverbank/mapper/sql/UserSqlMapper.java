package ru.clevertec.cleverbank.mapper.sql;

import ru.clevertec.cleverbank.model.enums.Role;
import ru.clevertec.cleverbank.mapper.SqlMapper;
import ru.clevertec.cleverbank.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.PASSWORD;
import static ru.clevertec.cleverbank.constants.EntityConstants.ROLE;
import static ru.clevertec.cleverbank.constants.EntityConstants.USERNAME;

public class UserSqlMapper implements SqlMapper<User> {
    @Override
    public User toEntity(ResultSet rs) {
        try {
            if (rs.isAfterLast())
                return null;
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setUsername(rs.getString(USERNAME));
            user.setPassword(rs.getString(PASSWORD));
            user.setRole(Role.valueOf(rs.getString(ROLE)));
            return user;
        } catch (SQLException ignore) {
            return null;
        }
    }

    @Override
    public List<User> toEntityList(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = toEntity(rs);
                if (user == null)
                    return users;
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
