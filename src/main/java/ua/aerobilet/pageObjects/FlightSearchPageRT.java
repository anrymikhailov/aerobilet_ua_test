package ua.aerobilet.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class FlightSearchPageRT extends AbstractPage{

	public FlightSearchPageRT(WebDriver driver) {
		super(driver);
	}

	
	//for RT
	  //From
	@FindBy(xpath="//*[@id='link-table-multi-prov-dep']/tbody/tr[1]")
	private WebElement firstResultAirlineFromRT;
	@FindBy(xpath="//*[@id='link-table-multi-prov-dep']/tbody/tr[1]/td[1]/ul/li/label/input")
	private WebElement firstResultProviderKeyFromRT;
	  //Return
	@FindBy(xpath="//*[@id='link-table-multi-prov-ret']/tbody/tr[1]")
	private WebElement firstResultAirlineReturnRT;
	@FindBy(xpath="//*[@id='link-table-multi-prov-ret']/tbody/tr[1]/td[1]/ul/li[1]/label/input")
	private WebElement firstResultProviderKeyReturnRT;
	
	
	@Step("Assert Price Exist RT")
	public void assertPriceExistRT() {
		// show first price on searchPage for TO
	//	String airlinenameto = firstResultAirlineFromRT.getAttribute("airlinename");
		//String priceto = firstResultAirlineFromRT.getAttribute("price");
	//	String providerkeyto = firstResultProviderKeyFromRT.getAttribute("providerkey");
		/*System.out.printf("Price FROM is exist for RT %nproviderkey is: "+providerkeyto
				+"%nairline is: "+airlinenameto+"%nprice is: "+priceto);*/
		// show first price on searchPage for RETURN
	//	String airlinenamereturn = firstResultAirlineReturnRT.getAttribute("airlinename");
		//String pricereturn = firstResultAirlineReturnRT.getAttribute("price");
	//	String providerkeyreturn = firstResultProviderKeyReturnRT.getAttribute("providerkey");
		/*System.out.printf("Price RETURN is exist for RT %nproviderkey is: "+providerkeyreturn
				+"%nairline is: "+airlinenamereturn+"%nprice is: "+pricereturn);*/
	}
	
}