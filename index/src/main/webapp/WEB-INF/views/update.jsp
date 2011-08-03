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
		import="java.util.*,wli.java.db.*,wli.java.stigs.Stig,wli.java.stigs.StigNote,java.util.*,java.sql.Date,wli.java.web.util.*;"%>

	<%
		try {
			System.out.println("AAAAAAAAA");
			Process proc = Runtime.getRuntime().exec("regedit /S C:\\test.reg");
			System.out.println("BBBBBBBB");
			proc.waitFor();
			System.out.println("CCCCCCC");
			proc.destroy();
			System.out.println("DDDDDDD");
		} catch (Throwable th) {
			System.out.println(th);
		}
	%>
	<%
		Enumeration<String> enums = request.getParameterNames();
		String temp = null;
		String action_id = request.getParameter("action_id");

		if (action_id != null && action_id.equals("search_by_finding_id")) {
			MyDatabaseUtils mdut = new MyDatabaseUtils();
			out.println("search this server FINDING_ID=" + request.getParameter("finding_id"));
			out.println(mdut.getStigList(request.getParameter("finding_id")));
			out.println("search this server FINDING_ID=" + request.getParameter("finding_id"));
		}

		if (action_id != null && action_id.equals("search_all_by_finding_id")) {
			out.println("search all server FINDING_ID=" + request.getParameter("finding_id"));
			return;
		}

		if (action_id != null && action_id.equals("update_stig")) {

			while (enums.hasMoreElements()) {
				temp = enums.nextElement();
				//out.println("<BR>"+temp + "=" + request.getParameter(temp));
			}

			Stig stig = new Stig();
			stig.setAssetId("" + request.getParameter("asset_id"));
			stig.setFindingId("" + request.getParameter("finding_id"));
			stig.setTargetDescrip("" + request.getParameter("target_description"));
			stig.setStatus("" + request.getParameter("status"));
			stig.setTool("" + request.getParameter("tool"));
			stig.setToolVersion("" + request.getParameter("tool_version"));
			stig.setGdValName("" + request.getParameter("gd_val_name"));
			stig.setFindingStatus("" + request.getParameter("finding_status"));
			stig.setGdSeverity("" + request.getParameter("gd_severity"));
			stig.setTargetKey("" + request.getParameter("target_key"));
			stig.setLastUpdate("" + request.getParameter("last_update"));
			stig.setOwner("" + request.getParameter("owner"));
			stig.setNote("" + request.getParameter("note"));

			String note_counter = request.getParameter("note_counter");
			stig.setNoteCounter(note_counter);

			MyDatabaseUtils mdut = new MyDatabaseUtils();
			String note = stig.getNote();

			StringBuffer sbf = new StringBuffer();
			sbf.append("");
			String author = request.getParameter("author");

			if (note != null && note.length() > 0) {
				sbf = new StringBuffer();
				sbf.append(note.replaceAll("'", ""));
				StigNote stig_note = new StigNote();
				stig_note.setStigID(stig.getAssetId() + ":" + stig.getTargetKey() + ":" + stig.getFindingId());
				stig_note.setNote(note);
				stig_note.setOwner(request.getParameter("author"));
				stig.setStigNote(stig_note);

				mdut.insertStigNote(stig);

				note_counter = "" + (Integer.parseInt(note_counter) + 1);
				stig.setNoteCounter(note_counter);
				System.out.println(sbf);
			}

			mdut.updateStig(stig);
			out.println("<h3>Your record has been updated. Please refresh your page to review the update.</h3>");
		}

		if (action_id != null && action_id.equals("edit_all")) {
			Stig stig = new Stig();
			stig.setAssetId("" + request.getParameter("asset_id"));
			stig.setFindingId("" + request.getParameter("finding_id"));
			stig.setTargetDescrip("" + request.getParameter("target_description"));
			stig.setStatus("" + request.getParameter("status"));
			stig.setTool("" + request.getParameter("tool"));
			stig.setToolVersion("" + request.getParameter("tool_version"));
			stig.setGdValName("" + request.getParameter("gd_val_name"));
			stig.setFindingStatus("" + request.getParameter("finding_status"));
			stig.setGdSeverity("" + request.getParameter("gd_severity"));
			stig.setTargetKey("" + request.getParameter("target_key"));
			stig.setLastUpdate("" + request.getParameter("last_update"));
			stig.setOwner("" + request.getParameter("owner"));
			stig.setNote("" + request.getParameter("note"));
			stig.setNoteCounter(request.getParameter("note"));
			out.println("<FORM name='update_stig' action='/index/update'>");
			out.println("<table width='90%' border='1'>");

			out.println("<tr>");
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
			//out.println("<td nowrap>NOTES</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>" + request.getParameter("asset_id") + "</td>");
			out.println(HtmlForm.getHiddenInput("asset_id", request.getParameter("asset_id")));
			out.println("<td>" + request.getParameter("target_description") + "</td>");
			out.println("<td>" + request.getParameter("target_key") + "</td>");
			out.println(HtmlForm.getHiddenInput("target_key", request.getParameter("target_key")));
			out.println("<td>" + request.getParameter("finding_status") + "</td>");

			//out.println("<td>" + request.getParameter("gd_severity") + "</td>");

			out.println("<td>" + HtmlForm.getSelect("gd_severity", "1;2;3;4", ";", false, request.getParameter("gd_severity"), "") + "</td>");

			out.println("<td>" + request.getParameter("finding_id") + "</td>");
			out.println(HtmlForm.getHiddenInput("finding_id", request.getParameter("finding_id")));
			out.println("<td>" + request.getParameter("tool") + "</td>");
			out.println("<td>" + request.getParameter("tool_version") + "</td>");
			out.println("<td>" + request.getParameter("owner") + "</td>");
			String status = request.getParameter("status");

			if (status == null || status.equals("null")) {
				status = "N/A";
			}
			out.println("<td>" + HtmlForm.getSelect("status", "OPEN;CLOSED;EXCEPTION;Not a Finding;N/A", ";", false, status, "") + "</td>");
			out.println(HtmlForm.getHiddenInput("last_update", request.getParameter("last_update")));
			out.println("<td>" + request.getParameter("last_update") + "</td>");
			out.println("<td>" + request.getParameter("gd_val_name") + "</td>");
			//out.println("<td>" + request.getParameter("note") + "</td>");
			out.println("</tr>");
			out.println("</table>");

			out.println("<table width='70%' border='0'>");
			out.println("<tr><td>");
			out.println("Notes:");
			out.println("</td></tr>");
			String note = request.getParameter("note");
			out.println("<tr><td>");
			out.println(HtmlForm.getTextArea("note", 100, 10));
			out.println("</td></tr>");
			out.println("</table>");
			out.println("<table border='0'>");
			out.println("<tr><td>");
			out.println("Author:");
			out.println("</td>");
			out.println("<td>");
			out.println(HtmlForm.getTextBox("author", request.getRemoteUser(), 20, 40));
			out.println("</td><td>");
			//out.println("</table>");
			out.println(HtmlForm.getHiddenInput("action_id", "update_stig"));
			out.println(HtmlForm.getHiddenInput("note_counter", request.getParameter("note_counter")));
			//out.println("<table width='70%' border='0'>");
			out.println("</td><td>");
			out.println(HtmlForm.getSubmitButton("Submit", "Submit"));
			out.println("</td></tr>");
			out.println("</table>");
			out.println("</FROM><BR><BR>");

			out.println("<table border='1' width='90%'>");
			MyDatabaseUtils mdut = new MyDatabaseUtils();
			mdut.setStigNote(stig);
			ArrayList<StigNote> alist = (ArrayList<StigNote>) mdut.getNoteList();
			Iterator<StigNote> it = alist.iterator();
			StigNote stig_note = null;
			out.println("<tr>");
			out.println("<td>Date</td>");
			out.println("<td>Note</td>");
			out.println("<td>Author</td>");
			out.println("</tr>");
			String temp_note = null;
			while (it.hasNext()) {
				stig_note = it.next();
				temp_note = stig_note.getNote();
				out.println("<tr>");
				out.println("<td nowrap valign=top>" + stig_note.getDateCreated() + "</td>");

				if (temp_note != null && temp_note.indexOf("\n") != -1) {
					temp_note = temp_note.replaceAll("\n", "<BR>");
				}
				out.println("<td>" + temp_note + "</td>");
				out.println("<td valign=top>" + stig_note.getOwner() + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>RemoteUser:" + request.getRemoteUser());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>RemoteUser:" + request.getRemoteAddr());
		}
	%>

</body>
</html>