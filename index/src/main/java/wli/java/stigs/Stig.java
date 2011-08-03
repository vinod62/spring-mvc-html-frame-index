package wli.java.stigs;

import java.sql.Date;

public class Stig {
	private String asset_id;
	private int target_key;
	private String target_descrip;
	private String finding_id;
	private String finding_status;
	private String tool;
	private String tool_version;
	private boolean authenticated_finding;
	private String gd_val_name;
	private String gd_severity;
	private String owner;
	private String status;
	private String note;
	private int note_counter;
	private Date date_created;
	private Date last_update;
	private StigNote stig_note;

	public void setAssetId(String assetid) {
		this.asset_id = assetid;
	}
	public String getAssetId() {
		return asset_id;
	}

	public void setTargetKey(int tk) {
		this.target_key = tk;
	}
	public void setTargetKey(String tk) {
		this.target_key = Integer.parseInt(tk);
	}
	public int getTargetKey() {
		return target_key;
	}
	public void setTargetDescrip(String tdes) {
		this.target_descrip = tdes;
	}
	public String getTargetDescrip() {
		return target_descrip;
	}
	public void setFindingId(String fid) {
		this.finding_id = fid;
	}
	public String getFindingId() {
		return finding_id;
	}
	public void setFindingStatus(String fst) {
		this.finding_status = fst;
	}
	public String getFindingStatus() {
		return finding_status;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getTool() {
		return tool;
	}
	public void setToolVersion(String toolvs) {
		this.tool_version = toolvs;
	}
	public String getToolVersion() {
		return tool_version;
	}
	public void setAuthenticatedFinding(boolean isaf) {
		this.authenticated_finding = isaf;
	}
	public void setAuthenticatedFinding(String isaf) {
		if (isaf != null && isaf.equals("TRUE"))
			this.authenticated_finding = true;
	}
	public boolean getAuthenticatedFinding() {
		return authenticated_finding;
	}
	public void setGdValName(String gd_val_name) {
		this.gd_val_name = gd_val_name;
	}
	public String getGdValName() {
		return gd_val_name;
	}
	public void setGdSeverity(String gd_severity) {
		this.gd_severity = gd_severity;
	}
	public String getGdSeverity() {
		return gd_severity;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNote() {
		return note;
	}

	public void setDateCreated(Date date_ct) {
		this.date_created = date_ct;
	}
	public void setDateCreated(String date_ct) {
		this.date_created = Date.valueOf(date_ct);
	}
	public Date getDateCreated() {
		return date_created;
	}

	public void setLastUpdate(Date last_up) {
		this.last_update = last_up;
	}
	public void setLastUpdate(String last_up) {
		this.last_update = Date.valueOf(last_up);
	}
	public Date getLastUpdate() {
		return last_update;
	}

	public void setStigNote(StigNote stig_note) {
		this.stig_note = stig_note;
	}
	public StigNote getStigNote() {
		return stig_note;
	}
	public void setNoteCounter(int note_counter) {
		this.note_counter = note_counter;
	}
	public void setNoteCounter(String note_counter) {
		try {
			this.note_counter = Integer.parseInt(note_counter);
		} catch (Exception e) {

		}
	}
	public int getNoteCounter() {
		return note_counter;
	}
	public String toXmlString() {
		return ("<TARGET><TARGET_KEY>" + this.getTargetKey() + "</TARGET_KEY>" + "<TARGET_DESCRIP>" + this.getTargetDescrip() + "</TARGET_DESCRIP>" + "<FINDING>"
				+ "<FINDING_ID TYPE=\"VK\">" + this.getFindingId() + "</FINDING_ID>" + "<FINDING_STATUS>" + this.getFindingStatus() + "</FINDING_STATUS>" + "<TOOL>"
				+ this.getTool() + "</TOOL>" + "<TOOL_VERSION>" + this.getToolVersion() + "</TOOL_VERSION>" + "<AUTHENTICATED_FINDING>" + this.getAuthenticatedFinding()
				+ "</AUTHENTICATED_FINDING>" + "<GD_VUL_NAME>" + this.getGdValName() + "</GD_VUL_NAME>" + "<GD_SEVERITY>" + this.getGdSeverity() + "</GD_SEVERITY>" + "</FINDING>");
	}
}
