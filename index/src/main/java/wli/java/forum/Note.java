package wli.java.forum;

import java.sql.Date;

public class Note {
	private int note_id;
	private String group_id;
	private String owner;
	private String title;
	private String subject;
	private String note;
	private Date date_created;
	private Date last_update;

	public void setNoteId(String note_id) {
		this.note_id = Integer.parseInt(note_id);
	}
	public void setNoteId(int note_id) {
		this.note_id = note_id;
	}
	public int getNoteId() {
		return note_id;
	}
	public void setGroupID(String gid) {
		this.group_id = gid;
	}
	public String getGroupId() {
		return group_id;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
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
}
