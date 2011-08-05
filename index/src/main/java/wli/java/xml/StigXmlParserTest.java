package wli.java.xml;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import wli.java.stigs.Stig;

public class StigXmlParserTest {

	@Test
	public void testReadConfig() {
		try {
			StigXmlParser read = new StigXmlParser();
			List<Stig> readConfig = read
					.readConfig("C:\\STIGS\\HOLLY\\VMS6X.xml");
			System.out.println("StigXmlParser: ..................Read Total:"
					+ readConfig.size());
		} catch (Exception e) {
			System.out.println("StigXmlParser: ......error..."+e);
		}
	}
}
