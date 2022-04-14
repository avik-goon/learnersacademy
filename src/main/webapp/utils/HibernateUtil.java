package src.main.webapp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration().configure().addAnnotatedClass(src.main.webapp.pojo.Admin.class);
                StandardServiceRegistry ssRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(ssRegistry);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }
    public Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }
}
