package main.webapp.com.learnersacademy;

import main.webapp.pojo.ClassList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MasterClassList extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String section_name, class_name, date, stnd;
        section_name = request.getParameter("section_name");
        class_name = request.getParameter("class_name");
        date = request.getParameter("date");
        stnd = request.getParameter("class_standard");
        ClassList classList = new ClassList();
        classList.setClass_name(class_name);
        classList.setDate(date);
        classList.setSection(section_name);
        classList.setStandard(stnd);
        try {
            Session session = new src.main.webapp.utils.HibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            int id = (Integer) session.save(classList);
            tx.commit();
            session.close();
            System.out.println("ID = "+id);
            response.sendRedirect("dashboard.jsp");
        }catch (Exception exception){
            System.err.println(exception.getMessage());
        }

    }

}
