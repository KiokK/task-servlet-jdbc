package ru.clevertec.cleverbank.model.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IdEntity {
    private Integer id;

    @Override
    public String toString() {
        return "id=" + id;
    }
}
