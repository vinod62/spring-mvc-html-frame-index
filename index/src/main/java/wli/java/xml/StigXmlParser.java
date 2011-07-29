package wli.java.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import wli.java.stigs.*;

public class StigXmlParser {

	static final String asset_id = "ASSET_ID";
	static final String target = "TARGET";
	static final String target_key = "TARGET_KEY";
	static final String target_descrip = "TARGET_DESCRIP";
	static final String finding = "FINDING";
	static final String finding_id = "FINDING_ID";
	static final String finding_status = "FINDING_STATUS";
	static final String finding_details = "FINDING_DETAILS";
	static final String tool = "TOOL";
	static final String tool_version = "TOOL_VERSION";
	static final String authenticated_finding = "AUTHENTICATED_FINDING";
	static final String gd_val_name = "GD_VUL_NAME";
	static final String gd_severity = "GD_SEVERITY";

	@SuppressWarnings({"unchecked", "null"})
	public List<Stig> readConfig(String configFile) {
		List<Stig> stigs = new ArrayList<Stig>();

		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			Stig stig = null;

			String target_key_value = null;
			String target_descrip_value = null;
			String asset_id_value = null;
			Vector<String> v = new Vector<String>();

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (asset_id)) {
							Iterator<Attribute> attributes = startElement.getAttributes();
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								if (attribute.getName().toString().equals("TYPE")) {
									// asset_id_value =
									// event.asCharacters().getData();
								}
							}
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (asset_id)) {

							event = eventReader.nextEvent();
							asset_id_value = event.asCharacters().getData();
							v.add(asset_id_value.toUpperCase());
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (target_key)) {
							event = eventReader.nextEvent();
							target_key_value = event.asCharacters().getData();

						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (target_descrip)) {
							event = eventReader.nextEvent();
							target_descrip_value = event.asCharacters().getData();

						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (finding)) {
							stig = new Stig();
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (finding_id)) {
							stig = new Stig();

							stig.setAssetId(v.elementAt(0));
							stig.setTargetKey(target_key_value);
							stig.setTargetDescrip(target_descrip_value);
							/*
							 * System.out.println("ASSET_ID_VALUE=" +
							 * v.elementAt(0)); System.out.println("TARGET_KEY="
							 * + target_key_value);
							 * System.out.println("TARGET_DESCRIP_VALUE=" +
							 * target_descrip_value);
							 */
							Iterator<Attribute> attributes = startElement.getAttributes();
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								if (attribute.getName().toString().equals("TYPE")) {
									// System.out.println(">>>>>>>>>>>>>>>>" +
									// attribute.getValue());
								}
							}
						}
					}

					try {
						if (event.isStartElement()) {
							if (startElement.getName().getLocalPart() == (finding_id)) {
								event = eventReader.nextEvent();
								String temp = event.asCharacters().getData();
								// System.out.println("FINDING_ID=" + temp);

								stig.setFindingId(temp);
							}
						}
					} catch (Exception e) {
						stig = new Stig();
						continue;
						// System.out.println("<<<<<<<<<<<<<<<<<" + e);
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (finding_status)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setFindingStatus(temp);
							// System.out.println("FINDING_STATUS=" + temp);
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (tool)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setTool(temp);
							// System.out.println("TOOL=" + temp);
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (tool_version)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setToolVersion(temp);
							// System.out.println("TOOL_VERSION=" + temp);
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (authenticated_finding)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setAuthenticatedFinding(temp);

							// System.out.println("AUTHENTICATED_FINDING=" +
							// temp);
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (gd_val_name)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setGdValName(temp);
							// System.out.println("GD_VAL_NAME=" + temp);
						}
					}

					if (event.isStartElement()) {
						if (startElement.getName().getLocalPart() == (gd_severity)) {
							event = eventReader.nextEvent();
							String temp = event.asCharacters().getData();
							stig.setGdSeverity(temp);
							// System.out.println("GD_SEVERITY=" + temp);
						}
					}

					if (stig != null && !stigs.contains(stig) && stig.getFindingId() != null) {
						// System.out.println(">>>>>>>>>>>>>>>>>>>>" +
						// Stig.getFindingId());
						stigs.add(stig);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return stigs;
	}
}
