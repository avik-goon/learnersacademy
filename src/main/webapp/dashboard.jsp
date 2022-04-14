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
</body>
</html>
