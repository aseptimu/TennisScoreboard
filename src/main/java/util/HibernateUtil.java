package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil implements ServletContextListener {
    private static final SessionFactory sessionFactory;

    static {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
