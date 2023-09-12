package ru.clevertec.cleverbank.mapper.sql;

import ru.clevertec.cleverbank.mapper.SqlMapper;
import ru.clevertec.cleverbank.model.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.cleverbank.constants.EntityConstants.ID;
import static ru.clevertec.cleverbank.constants.EntityConstants.TITLE;

public class BankSqlMapper implements SqlMapper<Bank> {

    @Override
    public Bank toEntity(ResultSet rs) {
        try {
            if (rs.isAfterLast())
                return null;
            Bank bank = new Bank();
            bank.setId(rs.getInt(ID));
            bank.setTitle(rs.getString(TITLE));
            return bank;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bank> toEntityList(ResultSet rs) {
        List<Bank> banks = new ArrayList<>();
        try {
            while (rs.next()) {
                Bank bank = toEntity(rs);
                if (bank == null)
                    return banks;
                banks.add(bank);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return banks;
    }
}
