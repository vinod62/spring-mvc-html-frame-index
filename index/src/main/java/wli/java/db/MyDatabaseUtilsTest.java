package wli.java.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import wli.java.xml.StigXmlParser;
import wli.java.stigs.*;

public class MyDatabaseUtilsTest {
	
	@Test
	public void saveStage() {
		MyDatabaseUtils mdbut = new MyDatabaseUtils();
		try {
			StigXmlParser read = new StigXmlParser();
			List<Stig> readConfig = read.readConfig("C:\\STIGS\\LEAR\\VMS6X.xml");
			
			int count_nr = 0; int count_cat1 =0; int count_cat2=0; int count_cat3 =0;
			for (Stig stig : readConfig) {
				//System.out.println(stage);
				
				mdbut.saveStig(stig, "tolly", "SHERREL");
				
				if(stig.getFindingStatus().equals("NR")) {
					count_nr++;
					if(stig.getGdSeverity().equals("1")) count_cat1++;
					if(stig.getGdSeverity().equals("2")) count_cat2++;
					if(stig.getGdSeverity().equals("3")) count_cat3++;
				}
				
				System.out.print(stig.getAssetId()+"	|	"+stig.getFindingId()+"	|	"+stig.getFindingStatus()+"	|	"+stig.getTargetDescrip()+"	|	"+stig.getGdSeverity()+"\n");
			}
			
			System.out.println("total nr="+count_nr);
			System.out.println("total nr cat1="+count_cat1);
			System.out.println("total nr cat2="+count_cat2);
			System.out.println("total nr cat3="+count_cat3);
			
			
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
	
	@Test
	public void testDatabase() {

		String dataBaseName = "jdbc:mysql://127.0.0.1/forum";
		String dbDriverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "li0578";

		try {
			Class.forName(dbDriverName).newInstance();
			Connection con = DriverManager.getConnection(dataBaseName,
					userName, password);
			Statement stmt = con.createStatement();
	
			ResultSet res = stmt.executeQuery("SELECT * FROM forum.test;");
			ResultSetMetaData rsmd = res.getMetaData();
		
			int number_of_colums = rsmd.getColumnCount();

			for (int i = 1; i <= number_of_colums; ++i) {
				System.out.println(rsmd.getColumnName(i));
				System.out.println(rsmd.getColumnTypeName(i));
				System.out.println("" + rsmd.getColumnDisplaySize(i));
			}
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
