package net.codejava;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection extends SencoreNetworkMain {

    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USERNAME = "sys as sysdba";
    private static final String PASSWORD = "241178";
    public static final String DIALECT= "org.hibernate.dialect.Oracle8iDialect";

    public static Session connectionSettings(Configuration config) {
        config.setProperty("hibernate.connection.driver_class", DRIVER);
        config.setProperty("hibernate.connection.url", URL);
        config.setProperty("hibernate.connection.username", USERNAME);
        config.setProperty("hibernate.connection.password", PASSWORD);
        config.setProperty("hibernate.dialect", DIALECT);
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(TEMP_MEASURE.class);
        SessionFactory sessionFactory = config.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
