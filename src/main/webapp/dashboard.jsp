<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="java.util.List" %>
<%@page import="java.lang.*"%>
<%@ page import="main.webapp.pojo.ClassList" %>
<%@ page import="main.webapp.pojo.Subject" %>
<%@ page import="main.webapp.pojo.Teacher" %>
<%@ page import="main.webapp.pojo.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: avikgoon
  Date: 14/04/22
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DashBoard</title>
    <style>
        *, body{
           padding: 0;
            margin: 0;
        }
        .user{
            display: flex;
            flex-direction: row;
            justify-content: flex-end;
            align-items: center;
            padding: 5px 5px ;
            border-bottom: 1px solid rosybrown;
        }
        .user > h4{
            margin-right: 5px;
        }
        .user > a {
            text-decoration: none;
            color: brown;
            border: 1px solid firebrick;
            padding: 2px 3px;
        }
        .user > a:hover{
            background-color: firebrick;
            color: white;
        }
        .classes{
            width: 70%;
            margin: 10px auto;
        }
        .classes > form > fieldset{
            padding: 10px;
        }
        .classes > form > fieldset > input[type=text]{
            margin-right: 10px;
        }
        .class_btn{
            margin: 0 10px;
            padding: 0 5px;
        }
        .live{

            margin: 0 auto;
            display: flex;
            flex-direction: row;
        }
        table{
            width: 100%;
        }
        td {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    String uname = (String) request.getSession().getAttribute("username");
    if(uname == null || uname.isEmpty()){
        response.sendRedirect("index.jsp");
    }
%>
    <div class="user" >
        <h4>Welcome, <%= uname%></h4>
        <a href="logout"><h6>LOGOUT</h6></a>
    </div>
    <div class="classes">
        <form action="master_class_list">
            <fieldset>
                <legend>Create Master List for Classes</legend>
                <label for="class_standard">Class Standard </label>
                <input type="text" id="class_standard" name="class_standard" placeholder="5" />
                <label for="class_name">Class Name </label>
                <input type="text" id="class_name" name="class_name" placeholder="Class name" />
                <label for="section_name">Section </label>
                <input type="text" id="section_name" name="section_name" placeholder="Section name" />
                <label for="date">Date </label>
                <input type="date" id="date" name="date" placeholder="DD/MM/YYYY" />
                <input class="class_btn" type="submit" value="Submit" >
            </fieldset>
        </form>
    </div>
    <div class="subjects classes">
        <form action="master_subject_list">
            <fieldset>
                <legend>Create Master List for Subjects</legend>
                <label for="subject_code">Subject Code </label>
                <input type="text" id="subject_code" name="subject_code" placeholder="Subject code" />
                <label for="subject_name">Subject Name </label>
                <input type="text" id="subject_name" name="subject_name" placeholder="Subject name" />
                <input class="class_btn" type="submit" value="Submit" >
            </fieldset>
        </form>
    </div>
    <div class="student classes">
        <%
            Session session1 = new src.main.webapp.utils.HibernateUtil().getSession();
            Transaction tx = session1.beginTransaction();
            Query query =  session1.createQuery("from ClassList");
            Query query1 = session1.createQuery("from Subject");
            Query query2 = session1.createQuery("from Teacher");
            Query query3 = session1.createQuery("from Student ");
            List list = query.list();
            List subjectList = query1.list();
            List teachersList = query2.list();
            List studentList = query3.list();
            tx.commit();
            session1.close();
        %>
        <form action="master_student_list">
            <fieldset>
                <legend>Create Master List for Students</legend>
                <label for="student_roll">Student RollNo. </label>
                <input type="text" id="student_roll" name="student_roll" placeholder="Student RollNo." />
                <label for="student_name">Student Name </label>
                <input type="text" id="student_name" name="student_name" placeholder="Student name" />
                <label for="student_age">Student Age </label>
                <input type="text" id="student_age" name="student_age" placeholder="Student age" />
                <label for="student_class">Student Class ID </label>
                <select name="student_class" id="student_class">
                <%
                    for (Object cList : list){
                        ClassList c = (ClassList) cList;
                        int id = c.getId();
                        out.println("<option value='"+id+"'>"+id+"</option>");
                    }
                %>
                </select>
                <input class="class_btn" type="submit" value="Submit" >
            </fieldset>
        </form>
    </div>
    <div class="teachers classes">
        <form action="master_teacher_list">
            <fieldset>
                <legend>Create Master List for Teachers</legend>
                <label for="teacher_name">Teacher Name </label>
                <input type="text" id="teacher_name" name="teacher_name" placeholder="Full Name" />
                <label for="subject_list">Subjects</label>
                <select name="subject_list" id="subject_list">
                <%
                    for (Object sub : subjectList){
                        Subject subject = (Subject) sub;
                        String name = subject.getSubject_name();
                        out.println("<option value='"+((Subject) sub).getId()+"'>"+name+"</option>");
                    }
                %>
                </select>
                <label style="margin: 0 5px 0px 10px" for="subject_list">Select Class</label>
                <select name="classes_list" id="classes_list">
                    <%
                        for (Object cList : list){
                            ClassList c = (ClassList) cList;
                            int id = c.getId();
                            out.println("<option value='"+id+"'>"+c.getClass_name()+"-"+c.getDate()+"</option>");
                        }
                    %>
                </select>
                <input class="class_btn" type="submit" value="Submit" >
            </fieldset>
        </form>
    </div>
    <div class="liveClass classes">
        <div class="live">
            <table border="1">
                <tr>
                    <th colspan="4" >
                        ASSIGNED CLASSES
                    </th>
                </tr>
                <tr>
                    <th>Teacher</th>
                    <th>Subject</th>
                    <th>Class</th>
                    <th>Date</th>
                </tr>
                <%
                    for (Object tList : teachersList){
                        Teacher t = (Teacher) tList;
                        String t_name = t.getName();
                        Subject subject = t.getSubject();
                        String subName = subject.getSubject_name();
                        ClassList cl = t.getClassList();
                        String clName = cl.getClass_name();
                        String clDate = cl.getDate();
                        out.println("<tr>");
                        out.println("<td>"+t_name+"</td>");
                        out.println("<td>"+subName+"</td>");
                        out.println("<td>"+clName+"</td>");
                        out.println("<td>"+clDate+"</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
        <div style="margin-top: 10px" class="live">
            <table border="1">
                <tr>
                    <th colspan="4" >
                        STUDENTS DATA
                    </th>
                </tr>
                <tr>
                    <th>Name</th>
                    <th>Class</th>
                    <th>Standard</th>
                    <th>RollNo.</th>
                </tr>
                <%
                    for (Object sList : studentList){
                        Student s = (Student) sList;
                        String sName = s.getStudent_name();
                        String rn = s.getStudent_roll();
                        ClassList cl = s.getClassList();
                        String clName = cl.getClass_name();
                        String clStd = cl.getStandard();
                        out.println("<tr>");
                        out.println("<td>"+sName+"</td>");
                        out.println("<td>"+clName+"</td>");
                        out.println("<td>"+clStd+"</td>");
                        out.println("<td>"+rn+"</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
        </div>
    </div>
</body>
</html>
