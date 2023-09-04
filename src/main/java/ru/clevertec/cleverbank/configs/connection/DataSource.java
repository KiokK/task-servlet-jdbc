package ru.clevertec.cleverbank.configs.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.clevertec.cleverbank.configs.PropertyConstant.*;

public class DataSource {

    private static final String URL = DB_URL;
    private static final String USERNAME = DB_USERNAME;
    private static final String PASSWORD = DB_PASSWORD;
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName(DB_DRIVER_CLASSNAME);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setInitialSize(5);
        ds.setMaxOpenPreparedStatements(100);
    }

    private static class DataSourceHolder {
        private static final DataSource INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    public static Connection getConnection() {
        try {
            return getInstance().getBds().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BasicDataSource getBds() {
        return ds;
    }

    public void setBds(BasicDataSource bds) {
        this.ds = bds;
    }
}
