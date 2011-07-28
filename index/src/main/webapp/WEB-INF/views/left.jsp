<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table width="200" height="200" bgcolor="EFEFEF">
<tr><td>
<%@page import="wli.java.db.*;" %>

<%
MyDatabaseUtils mdut = new MyDatabaseUtils();
try {
	//mdut.setStageByDb();
	//out.println(mdut.getStageList());
	
} catch (Exception e) {
	System.out.println(e.getMessage());
}
%>
</td></tr>
</table>
</body>
</html>