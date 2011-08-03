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

	<%@page
		import="java.util.*,wli.java.db.*,wli.java.stigs.Stig,wli.java.web.util.*;"%>

	<%
		out.println("<table><tr>");
		out.println("<td>[<a href=/index?sid=TOLLY>TOLLY</a>]</td>");
		out.println("<td>[<a href=/index?sid=REVOLUTIONS>REVOLUTIONS</a>]</td>");
		out.println("<td>[<a href=/index?sid=MIDSUMMER>MIDSUMMER</a>]</td>");
		out.println("<td>[<a href=/index?sid=RODEO>RODEO</a>]</td>");
		out.println("<td>[<a href=/index?sid=HOLLY>HOLLY</a>]</td>");
		out.println("<td>[<a href=/index?sid=ANTHONY>ANTHONY</a>]</td>");
		out.println("<td>[<a href=/index?sid=HAMLET.CPMS.OSD.MIL>HAMLET</a>]</td>");
		out.println("</tr></table>");

		out.println("<HR>");

		String sid = request.getParameter("sid");
		String finding_id = request.getParameter("finding_id");
		String show_vid = request.getParameter("show_vid");

		MyDatabaseUtils mdut = new MyDatabaseUtils();

		try {
			Stig stig = null;

			//ArrayList<Stig> alist = new ArrayList();
			ArrayList<Stig> alist = null;
			if (finding_id == null) {
				if (sid != null && sid.length() > 0) {
					mdut.setStigByDb(sid);
					alist = (ArrayList<Stig>) mdut.getStigList();
				} else {
					out.println("<h3>Please select your server to review</h3>");
					return;
				}
			} else {
				alist = (ArrayList<Stig>) mdut.getStigList(finding_id);
			}

			Iterator<Stig> it = alist.iterator();
			out.println("<table width='90%' border='1'>");

			out.println("<tr>");
			out.println("<td nowrap>*</td>");
			out.println("<td nowrap>ASSET ID</td>");
			out.println("<td nowrap>TARGET DESCRIPTION</td>");
			out.println("<td nowrap>TARGET KEY</td>");
			out.println("<td nowrap>FINDING STATUS</td>");
			out.println("<td nowrap>SEVERITY</td>");
			out.println("<td nowrap>FINDING ID</td>");
			out.println("<td nowrap>TOOL</td>");
			out.println("<td nowrap>TOOL VERSION</td>");
			out.println("<td nowrap>OWNER</td>");
			out.println("<td nowrap>CURRENT STATUS</td>");
			out.println("<td nowrap>LAST UPDATE</td>");
			out.println("<td nowrap>GLOD DISK VAL NAME</td>");
			out.println("<td nowrap>NOTES</td>");
			out.println("</tr>");

			while (it.hasNext()) {
				stig = it.next();
				out.println("<tr>");
				out.println("<td><a href=\"/index/update?asset_id=" + stig.getAssetId() + "&finding_id=" + stig.getFindingId() + "&target_key=" + stig.getTargetKey()
						+ "&target_description=" + stig.getTargetDescrip() + "&finding_status=" + stig.getFindingStatus() + "&gd_severity=" + stig.getGdSeverity() + "&tool="
						+ stig.getTool() + "&tool_version=" + stig.getToolVersion() + "&gd_val_name=" + stig.getGdValName() + "&owner=" + stig.getOwner() + "&status="
						+ stig.getStatus() + "&last_update=" + stig.getLastUpdate() + "&note_counter=" + stig.getNoteCounter()
						+ "&action_id=edit_all\" method='post' target=_blank>" + "EDIT</a></td>");
				out.println("<td>" + stig.getAssetId() + "</td>");
				out.println("<td>" + stig.getTargetDescrip() + "</td>");
				out.println("<td>" + stig.getTargetKey() + "</td>");
				out.println("<td>" + stig.getFindingStatus() + "</td>");
				out.println("<td>" + stig.getGdSeverity() + "</td>");
				
				if (show_vid != null && show_vid.equals("0")) {
					out.println("<td>" + stig.getFindingId() + "</td>");
				} else {
					out.println("<td><a href=\"/index/main?asset_id=" + stig.getAssetId() + "&finding_id=" + stig.getFindingId() + "&target_key=" + stig.getTargetKey()
							+ "&target_description=" + stig.getTargetDescrip() + "&finding_status=" + stig.getFindingStatus() + "&gd_severity=" + stig.getGdSeverity()
							+ "&tool=" + stig.getTool() + "&tool_version=" + stig.getToolVersion() + "&gd_val_name=" + stig.getGdValName() + "&owner=" + stig.getOwner()
							+ "&status=" + stig.getStatus() + "&last_update=" + stig.getLastUpdate() + "&note_counter=" + stig.getNoteCounter()
							+ "&action_id=search_by_finding_id&show_vid=0\" target=_blank>" + stig.getFindingId() + "</a></td>");
				}
				out.println("<td>" + stig.getTool() + "</td>");
				out.println("<td>" + stig.getToolVersion() + "</td>");
				out.println("<td>" + stig.getOwner() + "</td>");

				if (stig.getStatus() == null || stig.getStatus().equals("null")) {
					out.println("<td>N/A</td>");
				} else {
					out.println("<td>" + stig.getStatus() + "</td>");
				}

				out.println("<td>" + stig.getLastUpdate() + "</td>");

				out.println("<td>" + stig.getGdValName() + "</td>");
				//out.println("<td align='center'>[" + stig.getNoteCounter() + "]</td>");
				out.println("<td align='center'><a href=\"/index/update?asset_id=" + stig.getAssetId() + "&finding_id=" + stig.getFindingId() + "&target_key="
						+ stig.getTargetKey() + "&target_description=" + stig.getTargetDescrip() + "&finding_status=" + stig.getFindingStatus() + "&gd_severity="
						+ stig.getGdSeverity() + "&tool=" + stig.getTool() + "&tool_version=" + stig.getToolVersion() + "&gd_val_name=" + stig.getGdValName() + "&owner="
						+ stig.getOwner() + "&status=" + stig.getStatus() + "&last_update=" + stig.getLastUpdate() + "&note_counter=" + stig.getNoteCounter()
						+ "&action_id=edit_all\" method='post' target=_blank>" + "[" + stig.getNoteCounter() + "]</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	%>
</body>
</html>