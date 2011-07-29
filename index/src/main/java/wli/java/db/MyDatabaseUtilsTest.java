package wli.java.db;

import static org.junit.Assert.*;

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
			List<Stig> readConfig = read.readConfig("C:\\STIGS\\RODEO\\VMS6X.xml");
			
			int count_nr = 0; int count_cat1 =0; int count_cat2=0; int count_cat3 =0;
			for (Stig stage : readConfig) {
				//System.out.println(stage);
				
				mdbut.saveStig(stage);
				
				if(stage.getFindingStatus().equals("NR")) {
					count_nr++;
					if(stage.getGdSeverity().equals("1")) count_cat1++;
					if(stage.getGdSeverity().equals("2")) count_cat2++;
					if(stage.getGdSeverity().equals("3")) count_cat3++;
				}
				
				System.out.print(stage.getAssetId()+"	|	"+stage.getFindingId()+"	|	"+stage.getFindingStatus()+"	|	"+stage.getTargetDescrip()+"	|	"+stage.getGdSeverity()+"\n");
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
	public void testSaveStage() {
		fail("Not yet implemented");
	}

}
