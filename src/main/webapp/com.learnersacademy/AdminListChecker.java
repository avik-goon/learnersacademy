package main.webapp.com.learnersacademy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import src.main.webapp.utils.HibernateUtil;

import java.util.List;


public class AdminListChecker {
    public static boolean isAdminAvailable(){
        int size = 0;
        try {
            Session session = new HibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Admin where username = 'admin'");
            List list = query.list();
            session.close();
            size = list.size();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return size == 1 ? true : false;
    }
}
