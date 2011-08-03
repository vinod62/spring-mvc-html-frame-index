package wli.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wli.java.stigs.StigNote;
import wli.java.stigs.Stig;

public class MyDatabaseUtils {

	private PreparedStatement preparedStatement = null;
	private Connection connect = null;
	private Statement statement = null;
	private Stig stig = null;
	private StigNote note = null;
	private ResultSet resultSet = null;
	private List<Stig> stigs = null;
	private List<StigNote> notes = null;

	public void saveStig(Stig stig) throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("insert into  STAGES.TARGET values (?, ?, ?, ?, ? , ?, ?,?,?,?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, stig.getTargetKey());
			preparedStatement.setString(2, stig.getTargetDescrip());
			preparedStatement.setString(3, stig.getFindingId());
			preparedStatement.setString(4, stig.getFindingStatus());
			preparedStatement.setString(5, stig.getTool());
			preparedStatement.setString(6, stig.getToolVersion());
			preparedStatement.setBoolean(7, stig.getAuthenticatedFinding());
			preparedStatement.setString(8, stig.getGdValName());
			preparedStatement.setString(9, stig.getGdSeverity());
			preparedStatement.setString(10, "SHERREL");
			preparedStatement.setString(11, null);
			preparedStatement.setString(12, null);
			preparedStatement.setString(13, stig.getAssetId());
			preparedStatement.setDate(14, new java.sql.Date(System.currentTimeMillis())); 
			preparedStatement.setDate(15, new java.sql.Date(System.currentTimeMillis())); 
			preparedStatement.setInt(16,0);								
			preparedStatement.executeUpdate();

			statement.close();
			connect.close();

		} catch (Exception e) {
			throw e;
		}
	}

	public void insertStigNote(Stig stig) throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");

			statement = connect.createStatement();
			
			String query = "insert into  STAGES.STIG_NOTE (STIG_ID, NOTE, LAST_UPDATE, OWNER) values ('" + stig.getAssetId() + ":" + stig.getTargetKey() + ":" + stig.getFindingId()
					+ "','" + stig.getNote() + "','" + new java.sql.Date(System.currentTimeMillis()) + "','"+ stig.getStigNote().getOwner()+"');";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();

			statement.close();
			connect.close();

		} catch (Exception e) {
			throw e;
		}
	}
	
	public void setStigNote(Stig stig) throws Exception {
		notes = new ArrayList<StigNote>();
		
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from STAGES.STIG_NOTE where STIG_ID='" + stig.getAssetId() + ":"+stig.getTargetKey()+":"+stig.getFindingId()+"' order by DATE_CREATED");

			while (resultSet.next()) {

				note = new StigNote();
				note.setNoteId(resultSet.getInt("ID"));
				note.setStigID(resultSet.getString("STIG_ID"));
				note.setNote(resultSet.getString("NOTE"));
				note.setDateCreated(resultSet.getDate("DATE_CREATED"));
				note.setOwner(resultSet.getString("OWNER"));
				notes.add(note);
				System.out.println(">>>>>>>>>>>>>>>>>>>>Stig Note=="+note.getStigId());
			}
			connect.close();

		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	public void updateStig(Stig stig) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");

			statement = connect.createStatement();

			
			String query = "update STAGES.TARGET set GD_SEVERITY='" + stig.getGdSeverity() + "', STATUS='" + stig.getStatus() + "', LAST_UPDATE='" + stig.getLastUpdate()
					+ "', NOTE_COUNTER="+stig.getNoteCounter()+" where ASSET_ID='" + stig.getAssetId() + "' and TARGET_KEY=" + stig.getTargetKey() + " and FINDING_ID='"
					+ stig.getFindingId() + "'";

			preparedStatement = connect.prepareStatement(query);
			System.out.println("Excuting Query>>>>>>>>>>>>>>>>>>" + preparedStatement.executeUpdate());
			System.out.println(query);
			statement.close();
			connect.close();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setStigByDb(String asset_id) throws Exception {
		stigs = new ArrayList<Stig>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from STAGES.TARGET where ASSET_ID='" + asset_id + "' order by ASSET_ID, TARGET_KEY, FINDING_STATUS DESC, GD_SEVERITY");

			while (resultSet.next()) {

				stig = new Stig();
				stig.setTargetKey(resultSet.getInt("TARGET_KEY"));
				stig.setTargetDescrip(resultSet.getString("TARGET_DESCRIP"));
				stig.setFindingId(resultSet.getString("FINDING_ID"));
				stig.setFindingStatus(resultSet.getString("FINDING_STATUS"));
				stig.setTool(resultSet.getString("TOOL"));
				stig.setToolVersion(resultSet.getString("TOOL_VERSION"));
				stig.setAuthenticatedFinding(resultSet.getString("AUTHENTICATED_FINDING"));
				stig.setGdValName(resultSet.getString("GD_VAL_NAME"));
				stig.setGdSeverity(resultSet.getString("GD_SEVERITY"));
				stig.setOwner(resultSet.getString("OWNER"));
				stig.setAssetId(resultSet.getString("ASSET_ID"));
				stig.setNote(resultSet.getString("NOTE"));
				stig.setNoteCounter(resultSet.getInt("NOTE_COUNTER"));
				stig.setStatus(resultSet.getString("STATUS"));
				stig.setDateCreated(resultSet.getDate("DATE_CREATED"));
				stig.setLastUpdate(resultSet.getDate("LAST_UPDATE"));
				stig.setNoteCounter(resultSet.getInt("NOTE_COUNTER"));
				stigs.add(stig);
				System.out.println(">>>>>>>>" + stig.getFindingId());

			}
			connect.close();
			System.out.println("Disconnected from database");

		} catch (Exception e) {
			throw e;
		}
	}
/*
	public void setNotesByDb(String group_id) throws Exception {
		notes = new ArrayList<Note>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from STAGES.NOTE where GROUP_ID='" + group_id + "' order by DATE_CREATE");

			while (resultSet.next()) {

				note = new Note();
				note.setNoteId(resultSet.getInt("ID"));
				note.setGroupID(resultSet.getString("GROUP_ID"));
				note.setNote(resultSet.getString("NOTE"));
				note.setOwner(resultSet.getString("OWNER"));
				note.setTitle(resultSet.getString("TITLE"));
				note.setSubject(resultSet.getString("SUBJECT"));
				note.setDateCreated(resultSet.getString("GROUP_ID"));
				note.setGroupID(resultSet.getString("GROUP_ID"));
				note.setDateCreated(resultSet.getDate("DATE_CREATED"));
				note.setLastUpdate(resultSet.getDate("LAST_UPDATE"));
				notes.add(note);
				System.out.println(">>>>>>>>" + note.getGroupId());
			}
			connect.close();
			System.out.println("Disconnected from database");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
*/
	public List<Stig> getStigList() {
		return stigs;
	}

	public List<Stig> getStigList(String finding_id) {
		stigs = new ArrayList<Stig>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/stages?" + "user=root&password=li0578");
			System.out.println("Connected to the database");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from STAGES.TARGET where FINDING_ID='" + finding_id + "' order by ASSET_ID");

			while (resultSet.next()) {
				stig = new Stig();
				stig.setTargetKey(resultSet.getInt("TARGET_KEY"));
				stig.setTargetDescrip(resultSet.getString("TARGET_DESCRIP"));
				stig.setFindingId(resultSet.getString("FINDING_ID"));
				stig.setFindingStatus(resultSet.getString("FINDING_STATUS"));
				stig.setTool(resultSet.getString("TOOL"));
				stig.setToolVersion(resultSet.getString("TOOL_VERSION"));
				stig.setAuthenticatedFinding(resultSet.getString("AUTHENTICATED_FINDING"));
				stig.setGdValName(resultSet.getString("GD_VAL_NAME"));
				stig.setGdSeverity(resultSet.getString("GD_SEVERITY"));
				stig.setOwner(resultSet.getString("OWNER"));
				stig.setAssetId(resultSet.getString("ASSET_ID"));
				stig.setNote(resultSet.getString("NOTE"));
				stig.setStatus(resultSet.getString("STATUS"));
				stig.setDateCreated(resultSet.getDate("DATE_CREATED"));
				stig.setLastUpdate(resultSet.getDate("LAST_UPDATE"));
				stig.setNoteCounter(resultSet.getInt("NOTE_COUNTER"));
				stigs.add(stig);
				System.out.println(">>>>>>>>" + stig.getAssetId() + "=" + stig.getFindingId());
			}
			connect.close();
			System.out.println("Disconnected from database");

		} catch (Exception e) {
			System.out.println(e);
		}
		return stigs;
	}
	public List<StigNote> getNoteList() {
		return notes;
	}
}
