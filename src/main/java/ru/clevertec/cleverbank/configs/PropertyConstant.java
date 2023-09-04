package ru.clevertec.cleverbank.configs;

import java.math.BigDecimal;

public class PropertyConstant {

    public static final String DB_URL = "jdbc:postgresql://localhost:3254/cleverbank";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "postgres";
    public static final String DB_DRIVER_CLASSNAME = "org.postgresql.Driver";
    public static final BigDecimal PERCENTS = new BigDecimal("1.01");
}
