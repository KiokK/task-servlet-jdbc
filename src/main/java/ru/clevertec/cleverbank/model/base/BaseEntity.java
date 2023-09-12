package ru.clevertec.cleverbank.model.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity extends IdEntity {

    private LocalDateTime created;

}
