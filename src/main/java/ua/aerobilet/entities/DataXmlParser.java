package ua.aerobilet.entities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataXmlParser {
	
	  public static String[] parserPassengerFromXml() throws ParserConfigurationException, SAXException, IOException {
	    	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				//made random xml file http://www.generatedata.com/
				Document doc = dBuilder.parse("source/passengers.xml");
				NodeList nList = doc.getElementsByTagName("record");
				Random r = new Random();
				// take random passenger
				Node nNode = nList.item(r.nextInt(101));
				Element eElement = (Element) nNode;
				String[] passenger = new String[5];
				passenger[0]= eElement.getElementsByTagName("names").item(0).getTextContent();
				passenger[1] = eElement.getElementsByTagName("surname").item(0).getTextContent();
				passenger[2] = eElement.getElementsByTagName("birth").item(0).getTextContent();
				passenger[3] = eElement.getElementsByTagName("noDocument").item(0).getTextContent();
				passenger[4] = eElement.getElementsByTagName("expiryDate").item(0).getTextContent();
			
				return passenger;
	     }
	  
	  
	  
	  // test
	 
	 public void test() throws SAXException, IOException{
		  try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				//made random xml file http://www.generatedata.com/
				Document doc = dBuilder.parse("source/passengers.xml");
						
				NodeList nList = doc.getElementsByTagName("record");
				Random r = new Random();
				// take random passenger
				Node nNode = nList.item(r.nextInt(101));
				Element eElement = (Element) nNode;
                
				//for test
				
			/*	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				NodeList nList = doc.getElementsByTagName("record");
				System.out.println("----------------------------");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						//System.out.println("Staff id : " + eElement.getAttribute("id"));
						System.out.println("names : " + eElement.getElementsByTagName("names").item(0).getTextContent());
						System.out.println("surname : " + eElement.getElementsByTagName("surname").item(0).getTextContent());
						System.out.println("birth: " + eElement.getElementsByTagName("birth").item(0).getTextContent());
						System.out.println("noDocument : " + eElement.getElementsByTagName("noDocument").item(0).getTextContent());
						System.out.println("expiryDate : " + eElement.getElementsByTagName("expiryDate").item(0).getTextContent());
					}
				}*/
				
				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			  }
	 }


