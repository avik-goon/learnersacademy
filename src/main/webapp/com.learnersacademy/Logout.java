package main.webapp.com.learnersacademy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if( (String) session.getAttribute("username") != null ){
            session.removeAttribute("username");
            System.out.println("Logged out");
        }
        response.sendRedirect("index.jsp");
    }
}
