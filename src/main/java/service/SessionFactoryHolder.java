package service;

import databaseService.FieldEntity;

import databaseService.GameNameEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHolder {

    private static SessionFactoryHolder instance;

    private final SessionFactory factory;

    private SessionFactoryHolder() {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(FieldEntity.class)
                    .addAnnotatedClass(GameNameEntity.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Exception ex) {

            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    private static SessionFactoryHolder getInstance() {
        if (instance == null) {
            instance = new SessionFactoryHolder();
        }
        return instance;
    }

    public static SessionFactory getFactory() {
        return getInstance().factory;
    }
}