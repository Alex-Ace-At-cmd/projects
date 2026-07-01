package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import java.lang.module.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();

            cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
            cfg.setProperty("hibernate.connection.username", "root");
            cfg.setProperty("hibernate.connection.password", "24681357");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

            cfg.setProperty("hibernate.show_sql", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");

            cfg.addAnnotatedClass(User.class);


            return cfg.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to build new session factory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
