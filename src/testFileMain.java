import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import epc.EPCFunction;
import epc.EPCGraph;
import epc.EPCHandler;

public class testFileMain {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		final String dir = "C:\\dataset\\SAP\\epml\\";
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		ArrayList<EPCGraph> epcs = new ArrayList<EPCGraph>();
		for (File file: listOfFiles) {
			if (!file.isFile()) 
				break;
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EPCHandler epcHandler = new EPCHandler();
			saxParser.parse(dir + file.getName(), epcHandler);
			epcs.add(epcHandler.getEpc());
		}
		
		ArrayList<String> uniqueFunctions = new ArrayList<String>();
		ArrayList<String> allFunctions = new ArrayList<String>();
		for (EPCGraph epc: epcs) {
			for (EPCFunction function: epc.getFunctions()) {
				allFunctions.add(function.getName());
				if (!uniqueFunctions.contains(function.getName()))
					uniqueFunctions.add(function.getName());
			}
		}
		System.out.println("===");

	}

}
