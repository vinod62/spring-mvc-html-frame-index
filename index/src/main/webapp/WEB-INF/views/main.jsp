<%@page import="wli.java.db.MyDatabaseUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>

<body>


	<%@page import="java.util.*,wli.java.db.*,wli.java.stigs.Stig;"%>

	<%
		out.println("<table><tr><td><a href=/index?sid=TOLLY>TOLLY</a></td></tr></table>");
		
			String sid = request.getParameter("sid");
			if (sid != null && sid.equals("TOLLY")) {
		MyDatabaseUtils mdut = new MyDatabaseUtils();
		try {
			Stig stage = null;
			mdut.setStageByDb();
			//ArrayList<Stage> alist = new ArrayList();
			ArrayList<Stig> alist = (ArrayList<Stig>) mdut.getStageList();
			Iterator<Stig> it = alist.iterator();
			out.println("<table width='90%' border='1'>");
			while (it.hasNext()) {
				stage = it.next();
				out.println("<tr>");
				out.println("<td>" + stage.getAssetId() + "</td>");
				out.println("<td>" + stage.getTargetDescrip() + "</td>");
				out.println("<td>" + stage.getTargetKey() + "</td>");
				out.println("<td>" + stage.getFindingStatus() + "</td>");
				out.println("<td>" + stage.getGdSeverity() + "</td>");

				out.println("<td><a href=/index/update?aid=" + stage.getAssetId() + "&fid=" + stage.getFindingId() + "&tkey=" + stage.getTargetKey() + ">"
						+ stage.getFindingId() + "</a></td>");

				out.println("<td>" + stage.getTool() + "</td>");
				out.println("<td>" + stage.getToolVersion() + "</td>");
				out.println("<td>" + stage.getOwner() + "</td>");
				out.println("<td>" + stage.getStatus() + "</td>");
				out.println("<td>" + stage.getLastUpdate() + "</td>");
				out.println("<td>" + stage.getGdValName() + "</td>");
				out.println("<td>" + stage.getNote() + "</td>");

				out.println("</tr>");
			}
			out.println("</table>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			} else {
		out.println("<h3>Please select your server to review</h3>");
			}
	%>
</body>
</html>