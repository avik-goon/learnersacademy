package main.webapp.com.learnersacademy;

import main.webapp.pojo.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MasterSubjectCreator extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //subject_name, subject_code
        String sub_name = request.getParameter("subject_name");
        String sub_code = request.getParameter("subject_code");
        Subject subject = new Subject();
        subject.setSubject_code(sub_code);
        subject.setSubject_name(sub_name);
        try {
            Session session = new src.main.webapp.utils.HibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            int id = (Integer) session.save(subject);
            tx.commit();
            session.close();
            System.out.println("ID = "+id);
            response.sendRedirect("dashboard.jsp");
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}
