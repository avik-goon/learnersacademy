
package com.learnersacademy;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import src.main.webapp.pojo.Admin;
public class LoginServlet extends HttpServlet {
            public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
               src.main.webapp.pojo.Admin admin = null;
               String username = request.getParameter("uname");
               String password = request.getParameter("password");
                try {
                    PrintWriter rs = response.getWriter();
                    Session session = new src.main.webapp.utils.HibernateUtil().getSession();
                    Transaction tx = session.beginTransaction();
                    Query query = session.createQuery("from Admin where username = :uname and password = :pwd ")
                            .setParameter("uname", username)
                            .setParameter("pwd", password);
                    List listAdmin = query.list();
                    tx.commit();
                    session.close();
                    if(listAdmin.size() > 0){
                        for (Object a : listAdmin) {
                            Admin admin1 = (Admin) a;
                            System.out.println( admin1.getUsername() );
                        }
                        HttpSession session1 = request.getSession();
                        session1.setAttribute("username", username);
                        response.sendRedirect("dashboard.jsp");
                    }
                    else {
                        rs.println("<h1>Wrong Credentials</h1>");
                    }
                }catch (Exception e){
                    System.out.println(e.getLocalizedMessage());
                }

            }
}