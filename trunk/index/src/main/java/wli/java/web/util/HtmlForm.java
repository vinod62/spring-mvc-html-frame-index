package wli.java.web.util;
import java.util.*;

public class HtmlForm {

	private static String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public static String cleanInputTextField(String in) {
		if (in == null) {
			return "";
		}
		if (in.indexOf("\"") != -1) {
			return (in.replaceAll("\"", "&quot;"));
		}
		return in;
	}

	public static StringBuffer getTextBox(String name, String init, String size, String mxsize) {
		StringBuffer sb = new StringBuffer();
		if (init == null) {
			init = "";
		}
		sb.append("\n<input type='text' name='" + name + "' value='" + init + "' size='" + size + "' maxLength='" + mxsize + "'>");
		return sb;
	}
	
	public static StringBuffer getHiddenInput(String name, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<input type='hidden' name='" + name + "' value='" + value + "'>");
		return sb;
	}
	
	public static StringBuffer getSubmitButton(String name, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<input type='submit' value='" + value + "'>");
		return sb;
	}
	
	public static StringBuffer getTextBox(String name, String init, int size, int mxsize) {
		StringBuffer sb = new StringBuffer();
		if (init == null) {
			init = "";
		}
		sb.append("\n<input type='text' name='" + name + "' value='" + init + "' size='" + size + "' maxLength='" + mxsize + "'>");
		return sb;
	}
	
	public static StringBuffer getTextArea(String name, int col_size, int row_size) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n<textarea name='" + name + "' cols='" + col_size + "' rows='" + row_size + "'></textarea>");
		return sb;
	}
	
	public static StringBuffer getSelect(String name, Hashtable<String, String> hs) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<select name=" + name + " size=1>");
		String temp = null;
		Enumeration<String> enums = hs.keys();
		while (enums.hasMoreElements()) {
			temp = (String) enums.nextElement();
			sb.append("\n<option value=" + hs.get(temp) + ">" + temp + "</option>");
		}
		sb.append("\n</select>");
		return sb;
	}

	public static StringBuffer getSelect(String name, ArrayList<String> a, String selected) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<select name=" + name + " size=1  style='font-size:8pt'>");
		sb.append("\n<option>--------</option>");
		String temp = null;
		Iterator<String> it = a.iterator();
		while (it.hasNext()) {
			temp = (String) it.next();
			if (temp != null && selected != null && temp.equals(selected)) {
				sb.append("\n<option selected>" + temp + "</option>");
			} else {
				sb.append("\n<option>" + temp + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb;
	}

	public static StringBuffer getSelect(String name, Vector<String> v, String selected) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<select name=" + name + " size=1  style='font-size:8pt'>");
		sb.append("\n<option>--------</option>");
		String temp = null;
		Enumeration<String> enums = v.elements();
		while (enums.hasMoreElements()) {
			temp = (String) enums.nextElement();
			if (temp != null && selected != null && temp.equals(selected)) {
				sb.append("\n<option selected>" + temp + "</option>");
			} else {
				sb.append("\n<option>" + temp + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb;
	}

	
	public static StringBuffer getSelect(String name, String options, String dim, boolean multiple, String default_value, String more) {
		StringBuffer sb = new StringBuffer();
		if (multiple) {
			sb.append("\n<select name='" + name + "' size='1' multiple " + more + ">");
		} else {
			sb.append("\n<select name='" + name + "' " + more + ">");
		}
		/*
		if (default_value == null) {
			sb.append("\n<option selected>--------</option>");
		} else {
			sb.append("\n<option>--------</option>");
		}
		*/
		String temp = null;
		StringTokenizer tokens = new StringTokenizer(options, dim);
		while (tokens.hasMoreTokens()) {
			temp = tokens.nextToken();
			if (temp != null && temp.length() > 0) {
				if (default_value != null && temp.equals(default_value)) {
					sb.append("\n<option selected>" + temp + "</option>");
				} else {
					sb.append("\n<option>" + temp + "</option>");
				}
			}
		}

		sb.append("\n</select>");
		return sb;
	}

	public static StringBuffer getSelect(String name, Vector<String> optionV, boolean multiple, String default_value) {
		StringBuffer sb = new StringBuffer();
		if (multiple) {
			sb.append("\n<select name='" + name + "' size='1' multiple style='font-size: 8pt'>");
		} else {
			sb.append("\n<select name='" + name + "' size='1' style='font-size: 8pt'>");
		}

		if (default_value == null) {
			sb.append("\n<option selected>--------</option>");
		} else {
			sb.append("\n<option>--------</option>");
		}

		String temp = null;
		Enumeration enums = optionV.elements();
		while (enums.hasMoreElements()) {
			temp = (String) enums.nextElement();
			if (temp != null && temp.length() > 0) {
				if (default_value != null && temp.equals(default_value)) {
					sb.append("\n<option selected>" + temp + "</option>");
				} else {
					sb.append("\n<option>" + temp + "</option>");
				}
			}
		}

		sb.append("\n</select>");
		return sb;
	}

	public static StringBuffer getOptions(int from, int to, String default_value) {
		if (default_value == null) {
			return getOptions(from, to, from);
		} else {
			return getOptions(from, to, Integer.parseInt(default_value));
		}
	}

	public static StringBuffer getOptions(int from, int to, int default_value) {
		StringBuffer sb = new StringBuffer();

		for (int i = from; i <= to; i++) {
			if (i == default_value) {
				sb.append("<option selected>" + i + "</option>");
			} else {
				sb.append("<option>" + i + "</option>");
			}
		}

		return sb;
	}

	public static StringBuffer getSelectYear(int m, int n) {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);

		StringBuffer sb = new StringBuffer();
		for (int i = m; i <= n; i++) {
			c.set((y - i), 0, 1, 0, 0);
			sb.append("\n<option value=" + c.get(Calendar.YEAR) + " O" + c.get(Calendar.YEAR) + ">" + c.get(Calendar.YEAR) + "</option>");
		}

		return sb;
	}

	public static StringBuffer getSelectBirthYear(int age) {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);

		StringBuffer sb = new StringBuffer();
		for (int i = age; i <= 120; i++) {
			c.set((y - i), 0, 1, 0, 0);
			sb.append("\n<option value=" + c.get(Calendar.YEAR) + " O" + c.get(Calendar.YEAR) + ">" + c.get(Calendar.YEAR) + "</option>");
		}

		return sb;
	}
}
