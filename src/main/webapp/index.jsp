
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="src.main.webapp.pojo.Admin" %>
<%@ page import="src.main.webapp.utils.HibernateUtil" %>
<%@ page import="main.webapp.com.learnersacademy.AdminListChecker" %>

<%
src.main.webapp.pojo.Admin admin = new Admin();
	boolean isAdmin = AdminListChecker.isAdminAvailable();
	if(!isAdmin){
		admin.setUsername("admin");
		admin.setPassword("123456");
		try {
			Session session1 = new HibernateUtil().getSession();
			Transaction tx = session1.beginTransaction();
			session1.save(admin);
			tx.commit();
			session1.close();
		}catch (Exception ex){
			out.println(ex.getMessage());
		}
	}
%>
<html>
	<head>
	  <title>Learner's Academy</title>
	  <meta charset="UTF-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <link  rel="stylesheet" href="css/main.css" >
	</head>
	
	<body>
		<div class="heading">
			<h1>
			    Learner&#39;s Academy 
			</h1>
		</div>
		<div class="form" >
			<form action="login">
				<input type="text" value="admin"  name="uname" placeholder="username" />
				<input type="password" value="123456" name="password" placeholder="password" />
				<input type="submit" value="submit" />
			</form>
		</div>
	</body>
</html>
