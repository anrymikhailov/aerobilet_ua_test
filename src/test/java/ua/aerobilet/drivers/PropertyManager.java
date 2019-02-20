package ua.aerobilet.drivers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	
	//url
	public String urlRu="";
	public String urlUa="";
	public String urlCheckIamNotTurkCitizCheckbox ="";
	public String urlOnurCheckIamNotTurkCitizCheckbox ="";
	public String urlCheckAgreementUA ="";
	public String urlCheckAgreementRU ="";
	public String urlCheckDomesticExpiryDate ="";
	public String urlRuOWOnePax ="";
	public String urlS7="";
	public String urlUral="";
	public String urlUtair="";
	public String urlForCheckUa="";
	public String urlJ2="";
	//gds
	public String gdsTurnOn ="";
	public String gdsGalileoUA ="";
	public String gdsPegasus ="";
	public String gdsAmadeusAlfa ="";
	public String gdsGalileoTR ="";
	public String gdsSabreTR ="";
	public String gdsSabreAlfa ="";
	public String gdsAmadeusAlfaID ="";
	public String gdsOnur ="";
	public String gdsTHY ="";
	public String gdsTHYB2C ="";
	
	//airline
	public String airlinePegasus ="";
	public String airlineAeroflot ="";
	public String airlineOnur ="";
	public String airlineS7 ="";
	public String airlineTHY ="";
	public String airlineUral="";
	public String airlineUtair="";
	public String airlineJ2="";
	
	//agreement
	public String textAgreementRuFirstElement="";
	public String textAgreementUaFirstElement="";
	//policy
	public String textPolicyRuFirstElement = "";
	public String textPolicyUaFirstElement = "";
	public void generateProperty(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("src/test/java/test.properties");
	        prop.load(input);
	        
	        //url
	        urlRu = prop.get("urlRu").toString();
	        urlUa = prop.get("urlUa").toString();
	        urlCheckIamNotTurkCitizCheckbox = prop.get("urlCheckIamNotTurkCitizCheckbox").toString();
	        urlOnurCheckIamNotTurkCitizCheckbox = prop.get("urlOnurCheckIamNotTurkCitizCheckbox").toString();
	        urlCheckAgreementUA = prop.get("urlCheckAgreementUA").toString();
	        urlCheckAgreementRU = prop.get("urlCheckAgreementRU").toString();
	        urlCheckDomesticExpiryDate = prop.get("urlCheckDomesticExpiryDate").toString();
	        urlRuOWOnePax = prop.get("urlRuOWOnePax").toString();
	        urlS7 = prop.get("urlS7").toString();
	        urlUral = prop.get("urlUral").toString();
	        urlUtair = prop.get("urlUral").toString();
	        urlForCheckUa = prop.get("urlForCheckUa").toString();
	        urlJ2 = prop.get("urlJ2").toString();

	        //gds
	        gdsTurnOn = prop.get("gdsTurnOn").toString();
	        gdsGalileoUA = prop.get("gdsGalileoUA").toString();
	        gdsAmadeusAlfa = prop.get("gdsAmadeusAlfa").toString();
	        gdsSabreTR = prop.get("gdsSabreTR").toString();
	        gdsSabreAlfa = prop.get("gdsSabreAlfa").toString();
	        gdsGalileoTR = prop.get("gdsGalileoTR").toString();
	        gdsAmadeusAlfaID = prop.get("gdsAmadeusAlfaID").toString();
	        gdsPegasus = prop.get("gdsPegasus").toString();
	        gdsOnur = prop.get("gdsOnur").toString();
	        gdsTHY = prop.get("gdsTHY").toString();
	        gdsTHYB2C = prop.get("gdsTHYB2C").toString();
	        
	        //airline
	        airlinePegasus = prop.get("airlinePegasus").toString();
	        airlineAeroflot = prop.get("airlineAeroflot").toString();
	        airlineOnur = prop.get("airlineOnur").toString();
	        airlineS7 = prop.get("airlineS7").toString();
	        airlineTHY = prop.get("airlineTHY").toString();
	        airlineUral = prop.get("airlineUral").toString();
	        airlineUtair = prop.get("airlineUtair").toString();
	        airlineJ2 = prop.get("airlineJ2").toString();
	        
	        //agreement
	        textAgreementRuFirstElement = prop.get("textAgreementRuFirstElement").toString();
	        textAgreementUaFirstElement = prop.get("textAgreementUaFirstElement").toString();
	        //policy
	        textPolicyRuFirstElement = prop.get("textPolicyRuFirstElement").toString();
	        textPolicyUaFirstElement = prop.get("textPolicyUaFirstElement").toString();
	        
	        input.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
