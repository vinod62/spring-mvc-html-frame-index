<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<b>Update....</b>
	<%@page
		import="java.util.*,wli.java.db.*,wli.java.stigs.Stig,java.util.*,java.sql.Date;"%>

	<%
		Stig stig = new Stig();
		stig.setAssetId(request.getParameter("asset_id"));
		stig.setFindingId(request.getParameter("finding_id"));
		stig.setTargetDescrip(request.getParameter("target_description"));
		stig.setStatus(request.getParameter("status"));
		stig.setTool(request.getParameter("tool"));
		stig.setToolVersion(request.getParameter("tool_version"));
		stig.setGdValName(request.getParameter("gd_val_name"));
		stig.setFindingStatus(request.getParameter("finding_status"));
		stig.setGdSeverity(request.getParameter("gd_severity"));
		stig.setTargetKey(request.getParameter("target_key"));
		stig.setLastUpdate(request.getParameter("last_update"));
		stig.setOwner(request.getParameter("owner"));
		stig.setNote(request.getParameter("note"));

		System.out.println(stig);

		Enumeration<String> enums = request.getParameterNames();
		String temp = null;
		while (enums.hasMoreElements()) {
			temp = enums.nextElement();
			out.println("<BR>" + temp);
		}
	%>

</body>
</html>