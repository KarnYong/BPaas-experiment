package epc;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import breadthfirst.Graph;


public class EPCHandler extends DefaultHandler {
	private EPCGraph epc = new EPCGraph();
	private boolean bfunction = false;
	private boolean bevent = false;
	private boolean band = false;
	private boolean bor = false;
	private boolean bxor = false;
	private boolean barc = false;
	private boolean bflow = false;
	private String sid = "";
	private String sname = "";
	private String ssource = "";
	private String starget = "";
 
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("FUNCTION")) {
			bfunction = true;
			sid = attributes.getValue("id");
			sname = "";
		}
		if (qName.equalsIgnoreCase("EVENT")) {
			bevent = true;
			sid = attributes.getValue("id");
			sname = "";
		}
		if (qName.equalsIgnoreCase("AND")) {
			band = true;
			sid = attributes.getValue("id");
		}
		if (qName.equalsIgnoreCase("OR")) {
			bor = true;
			sid = attributes.getValue("id");
		}
		if (qName.equalsIgnoreCase("XOR")) {
			bxor = true;
			sid = attributes.getValue("id");
		}
		if (qName.equalsIgnoreCase("ARC")) {
			barc = true;
			sid = attributes.getValue("id");
		}
		if (qName.equalsIgnoreCase("FLOW")) {
			bflow = true;
			ssource = attributes.getValue("source");
			starget = attributes.getValue("target");
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("FUNCTION")) {
			System.out.println("function: " + sid + " " + sname.trim());
			epc.addFunction(sid, sname.trim());
			sname = "";
			bfunction = false;
		}
		if (qName.equalsIgnoreCase("EVENT")) {
			System.out.println("event: " + sid + " " + sname.trim());
			epc.addEvent(sid, sname.trim());
			sname = "";
			bevent = false;
		}
		if (qName.equalsIgnoreCase("AND") || qName.equalsIgnoreCase("OR") || qName.equalsIgnoreCase("XOR")) {
			System.out.println(qName + ": " + sid);
			epc.addConnector(sid, qName.toLowerCase());
			band = false;
		}
		if (qName.equalsIgnoreCase("ARC")) {
			System.out.println("arc: " + sid + " " + ssource + "->" + starget);
			epc.addArc(sid, ssource, starget);
			barc = false;
		}
		if (qName.equalsIgnoreCase("FLOW")) {
			bflow = false;
		}
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bfunction || bevent || band || bor || bxor) {	
			String text = new String(ch, start, length).replaceAll("[\\t\\n\\r]"," ");
			sname += text;
		}		 
	}

	public EPCGraph getEpc() {
		epc.InitialStartEndEvents();
		return epc;
	}
	
	public Graph getGraph() {
		
		return null;
		
	}
}
