package ua.aerobilet.pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.stream.Collector;
import com.oracle.webservices.internal.api.message.PropertySet.Property;*/

import io.qameta.allure.Step;
import junit.framework.Assert;
import static java.util.stream.Collectors.toList;

public class FlightSearchPageOW extends AbstractPage{

	public FlightSearchPageOW(WebDriver driver) {
		super(driver);
	}
	// for OW
	@FindBy(xpath="//*[@id='link-table']/tbody/tr[1]/td[1]/ul/li[1]/span")
	private WebElement firstResultAirlineOW;
	@FindBy(id="link-table")
	//WebElement locatorTableWithPriceOW = driver.findElement(By.id("link-table"));
    private WebElement locatorTableWithPriceOW;
	
	
	@Step("Click on First Price OW @gds")
	public FlightReservationDetailPage clickOnFirstPriceOW(String gds) {

		 //classic code
	/*	 List<WebElement> locatorAllPriceButtonOnPageOW = locatorTableWithPriceOW.findElements(By.className("button"));
		for (int i = 0; i < locatorAllPriceButtonOnPageOW.size(); i++) {
			String checkGds = locatorAllPriceButtonOnPageOW.get(i).getAttribute("providerkey");
			if(checkGds.equals(userGds)){
				//System.out.printf("We fount GDS price: " + checkGds);
				locatorAllPriceButtonOnPageOW.get(i).click();
				break;
					}
            }*/	
		//use stream
		locatorTableWithPriceOW
		 .findElements(By.className("button"))
		 .stream()
		 .filter(button -> button.isDisplayed())
		 .filter(provider -> provider.getAttribute("providerkey").equals(gds))
		 .findFirst()
		 .get()
		 .click();
		
		 return new FlightReservationDetailPage(driver);	
	}
	
	@Step("Click on First Price OW")
	public FlightReservationDetailPage clickOnAnyFirstPriceOW() {
		locatorTableWithPriceOW
		 .findElements(By.className("button"))
		 .stream()
		 .filter(button -> button.isDisplayed())
		 .findFirst()
		 .get()
		 .click();
		 return new FlightReservationDetailPage(driver);	
	}
	 

	@Step("Click on Price Button @airline @gds")
	public FlightReservationDetailPage clickOnPriceAirlineGdsOw(String airline, String gds) {			
		locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				.filter(tr -> tr.getAttribute("airlinename").equals(airline))
				.map(button->button.findElement(By.className("button")))
				.filter(providerkey->providerkey.getAttribute("providerkey").equals(gds))
				.findFirst()
				.get()
				.click();
			return new FlightReservationDetailPage(driver);
	}
	
	@Step("Click on Price Button @airline")
	public FlightReservationDetailPage clickOnPriceAirlineGdsOw(String airline) {			
		locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				.filter(tr -> tr.getAttribute("airlinename").equals(airline))
				.map(button->button.findElement(By.className("button")))				
				.findFirst()
				.get()
				.click();
			return new FlightReservationDetailPage(driver);
	}

	 //not use now
	@Step("Click on airline and gds")
	public FlightReservationDetailPage clickOnAirlineGdsPriceOLD(String airline, String gds) {
		
		 List<WebElement> locatorAllPriceButtonOnPageOW = locatorTableWithPriceOW
				 //.findElements(By.className("priceDetailButton"));
				 .findElements(By.className("button"));
		 // use "transferCount0" for direct flights
		 //use "transferCount1" for flights with one stop
		List<WebElement> locatorAllTrWithNameOfAirlineAndPrice = locatorTableWithPriceOW
				 .findElements(By.className("transferCount0"));
		
		 JavascriptExecutor js = ((JavascriptExecutor) driver);
			
			for (int i = 0; i < locatorAllTrWithNameOfAirlineAndPrice.size(); i++) {
				String checkGds = locatorAllPriceButtonOnPageOW.get(i).getAttribute("providerkey");
				String checkAirline = locatorAllTrWithNameOfAirlineAndPrice.get(i).getAttribute("airlinename"); 

				//delete all spaces
				if(airline.equals("Aeroflot") || airline.equals("Pegasus"))
					checkAirline = checkAirline.replaceAll(" ","");

                System.out.println("i = " + i + " checkAirline = " + checkAirline + " checkGds = " + checkGds
                		+ " value = " + locatorAllPriceButtonOnPageOW.get(i).getAttribute("value"));
				if(checkGds.equals(gds)&&checkAirline.equals(airline)){
					//scroll to price button
					 js.executeScript("arguments[0].scrollIntoView(true);", 
							 locatorAllPriceButtonOnPageOW.get(i));
					 
					 //make some wait
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    //click on price
					System.out.println(" click on i = " + i);
				
					System.out.println(" click on i = " + locatorAllPriceButtonOnPageOW.get(i).getAttribute("value"));
					locatorAllPriceButtonOnPageOW.get(i).click();
					break;
						}
	            }
		
		return new FlightReservationDetailPage(driver);
	}

	@Step("Choose filter by airline ")
	public FlightSearchPageOW chooseFilterAirline(String airline) {
		
		//unselectAllFilterAirline
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", 
				 driver.findElement(By.id("unselectAllFilterAirline")));
		driver.findElement(By.id("unselectAllFilterAirline")).click();
		
		// id airlinelist  classname searchFilterAirline
		List<WebElement>  locatorAirlinelist = driver.findElements(By.className("searchFilterAirline"));
		for (int i = 0; i < locatorAirlinelist.size(); i++) {
			String checkAirline = locatorAirlinelist.get(i).getAttribute("value");
			if(airline.equals("Aeroflot")) checkAirline = checkAirline.replaceAll("\\s+","");
			
			if(checkAirline.equals(airline))  locatorAirlinelist.get(i).click();
		}
		return this;
	}
	

	@Step("If exist gds ")
	public boolean ifExistGds(String gds) {
		return locatorTableWithPriceOW.findElements(By.className("button"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				.anyMatch(providerkey->providerkey.getAttribute("providerkey").equals(gds));
	}
	
	@Step("find gds that turn on now")
	public String turnOnGds (String [] gdsArray) {
		String [] gds = locatorTableWithPriceOW.findElements(By.className("button"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				.map(provider -> provider.getAttribute("providerkey")).toArray(String [] :: new);
		return gds[0];
	}

	@Step("If exist airline ")
	public boolean ifExistAirline(String airline) {
		return locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				//.anyMatch(tr -> tr.getAttribute("airlinename").equals(airline));
				.anyMatch(tr -> tr.getAttribute("airlinename").equals(airline));
			}
	
	@Step("If exist gds and airline together")
 	public boolean ifExistAirlineGds(String airline, String gds) {
		
		return locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
				.stream()
				.filter(tr -> tr.isDisplayed())
				//.filter(tr -> tr.getAttribute("airlinename").equals(airline))
				.filter(tr -> tr.getAttribute("airlinename").contains(airline))
				.map(button->button.findElement(By.className("button")))
				.anyMatch(providerkey->providerkey.getAttribute("providerkey").equals(gds));
		}
	


	
	//-----------------------------for example temp-----------------------
	
	//not work for example
		public List<List<WebElement>> getListButtonOw(){
			return  locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
					.stream()
					.filter(tr -> tr.isDisplayed())
					.filter(tr -> tr.getAttribute("airlinename").equals("Ural Airlines"))
					.map(button-> button.findElements(By.className("button")))
					/*.filter(button ->((WebElement) button).getAttribute("providerkey").equals("Amadeus ALFA"))
					.filter(button ->((WebElement) button).getAttribute("airlinename").equals("S7 Airlines"))*/
					.collect(toList());
		}
		
		//not work for example
		public List<WebElement> getVisibleTrList(){
			return  locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
					.stream()
					.filter(tr -> tr.isDisplayed())
					.filter(tr -> tr.getAttribute("airlinename").equals("Ural Airlines"))
					.map(button->button.findElement(By.className("button")))
					.filter(providerkey->providerkey.getAttribute("providerkey").equals("Amadeus ALFA"))
					.collect(toList());
		}
		
		public boolean ifExistAirlineTest(String airline) {
			   //*[@id="link-table"]/tbody/tr[1]
	        // By.xpath("//*[contains(@id,'adult_endDate_')]")
			//*[@id="link-table"]
			
			locatorTableWithPriceOW.findElements(By.xpath("//*[@id='link-table']/tbody/tr"))
			.stream()
			.filter(tr -> tr.isDisplayed())
			.filter(tr -> tr.getAttribute("airlinename").contains("U"))
			.forEach(tr -> System.out.println((tr.getAttribute("airlinename"))));

			return false;
				}
		


	
}
