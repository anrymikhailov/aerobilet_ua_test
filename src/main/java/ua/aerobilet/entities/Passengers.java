package ua.aerobilet.entities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Passengers extends Passenger{
	

	public static Passenger oneAdultPax = new Passenger() {
		{  
            name = "ANDRII";
			surname = "MIKHAILOV";
			birth = "11.08.1981";
			citizenship = "UKRAINE";
			noDocument = "24738382";
			expiryDateOfDocument = "10.01.2023";	
		}
     };
     
     
     //set random passenger from xml files
      public void randomPassenger() throws ParserConfigurationException, SAXException, IOException { 
    	  
    	  	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//made random xml file http://www.generatedata.com/
			Document doc = dBuilder.parse("source/passengers.xml");
			NodeList nList = doc.getElementsByTagName("record");
			Random r = new Random();
			// take random passenger
			Node nNode = nList.item(r.nextInt(101));
			Element eElement = (Element) nNode;
			
			this.name = eElement.getElementsByTagName("names").item(0).getTextContent();
			this.surname = eElement.getElementsByTagName("surname").item(0).getTextContent();
			this.birth = eElement.getElementsByTagName("birth").item(0).getTextContent();
			this.noDocument = eElement.getElementsByTagName("noDocument").item(0).getTextContent();
			this.expiryDateOfDocument = eElement.getElementsByTagName("expiryDate").item(0).getTextContent();
			
			// must to do 
			this.citizenship = "";
        } 
      
      // maybe i can use method for couple passenger in MAP(name, "JONE") etc ...
      
      public Map<String, String> parserPassengerFromXml() throws ParserConfigurationException, SAXException, IOException {
	    	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				//made random xml file http://www.generatedata.com/
				Document doc = dBuilder.parse("source/passengers.xml");
				NodeList nList = doc.getElementsByTagName("record");
				Random r = new Random();
				// take random passenger
				Node nNode = nList.item(r.nextInt(101));
				Element eElement = (Element) nNode;
				Map<String, String> passenger = new HashMap<String, String>();
				passenger.put("names" , eElement.getElementsByTagName("names").item(0).getTextContent());
				passenger.put("names" , eElement.getElementsByTagName("surname").item(0).getTextContent());
				passenger.put("names" , eElement.getElementsByTagName("birth").item(0).getTextContent());
				passenger.put("names" , eElement.getElementsByTagName("noDocument").item(0).getTextContent());
				passenger.put("names" , eElement.getElementsByTagName("expiryDate").item(0).getTextContent());
				return passenger;
	     }
     
}
