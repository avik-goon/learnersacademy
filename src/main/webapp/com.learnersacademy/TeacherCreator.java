package main.webapp.com.learnersacademy;

import main.webapp.pojo.ClassList;
import main.webapp.pojo.Subject;
import main.webapp.pojo.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherCreator extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("teacher_name");
        int subjectID = Integer.parseInt(request.getParameter("subject_list"));
        int classID = Integer.parseInt(request.getParameter("classes_list"));
        Teacher teacher = new Teacher();
        teacher.setName(name);
        try {
            Session session = new src.main.webapp.utils.HibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            Subject subject = session.get(Subject.class, subjectID);
            ClassList classList = session.get(ClassList.class, classID);
            teacher.setSubject(subject);
            teacher.setClassList(classList);
            int id = (Integer) session.save(teacher);
            tx.commit();
            session.close();
            System.out.println(id);
            response.sendRedirect("dashboard.jsp");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
