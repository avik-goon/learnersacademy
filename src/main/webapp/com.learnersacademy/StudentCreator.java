package main.webapp.com.learnersacademy;

import main.webapp.pojo.ClassList;
import main.webapp.pojo.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentCreator extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String student_name = request.getParameter("student_name");
        String student_roll = request.getParameter("student_roll");
        String student_age = request.getParameter("student_age");
        int student_class_id = Integer.parseInt(request.getParameter("student_class"));
        Student student = new Student();
        student.setStudent_age(student_age);
        student.setStudent_name(student_name);
        student.setStudent_roll(student_roll);


        try{
            Session session = new src.main.webapp.utils.HibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            ClassList classList = session.get(ClassList.class, student_class_id);
            student.setClassList(classList);
            int id = (Integer) session.save(student);
            tx.commit();
            session.close();
            System.out.println(student);
            response.sendRedirect("dashboard.jsp");
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}
