package ua.aerobilet.pageObjects;

import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends AbstractPage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	 // SEARCH BUTTON
   // By searchButtonLocator = By.xpath("//*[@id='searchForm']/div/div[4]/div[4]/button");
    @FindBy(xpath = "//*[@id='searchForm']/div/div[4]/div[4]/button")
    private WebElement searchButton;
    
	//LOGIN elements
    By singInButtonLocator = By.xpath("//*[@id='loginlink']");
    
    //AIRPORTS to from
    By fromAirportAutocomplete = By.xpath("//*[@id='ui-id-5']/li/a[1]");
    By toAirportLocator = By.xpath("//*[@id='toAirport']");
    By toAirportAutocomplete = By.xpath("//*[@id='ui-id-6']/li/a[1]");
    
    //DATE fields 
    By departureDateCalendar = By.xpath("//*[@id='departureDate']");
    
    //CALENDAR departure
    
    By nextMonthCalendar = By.xpath("//*[@id='ui-datepicker-div']/div[1]/a[2]");
    By selectDayDeparture = By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a");
    
    //CALENDAR return
    By selectDayReturn = By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[6]/a");
    //By selectDayReturne = By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a");
    
    By checkMonthLocator = By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/span[1]");  
    By checkYearLocator = By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/span[2]");
  

    @Step("Set date FROM")
	public void setDateFrom(String fromDay, String fromMonth, String fromYear) {
		driver.findElement(departureDateCalendar).click();
		 String checkYear = driver.findElement(checkYearLocator).getText();
		 String checkMonth = driver.findElement(checkMonthLocator).getText();
         
		//find userYear
		 if (checkYear.equals(fromYear)){
			 System.out.println("Year is already selected : " + checkYear);
		 } else {
			 //to do later
		 }
		 //find userMonth	 
		 while (checkMonth.equals(fromMonth)==false){
			 driver.findElement(nextMonthCalendar).click();
			 checkMonth = driver.findElement(checkMonthLocator).getText();
			 if (checkMonth.equals(fromMonth)){
				 System.out.println("Month is already selected: "+ checkMonth);
			 }
		 }
		    //datepicker take table with days
		  	WebElement listOfDaysLocator = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody"));
		  	//datepicker take only links a with days
		  	List<WebElement> allDaysInMonth = listOfDaysLocator.findElements(By.tagName("a"));
		  	
		  	//find userDay	
            for (int i = 0; i < allDaysInMonth.size(); i++) {
			String checkDay = allDaysInMonth.get(i).getText();
			if(checkDay.equals(fromDay)){
				System.out.printf("Day is already selected: " + checkDay);
				allDaysInMonth.get(i).click();
				break;
					}
            }   	 
	}

   
    
    @Step("Open home page")
	public void openHomePage(){
		driver.get("https://aerobilet.ua");
		//open("https://aerobilet.ua");	
	}
    
    @Step("Open home page url: ")
  	public void openHomePageUrl(String url){
  		driver.get(url);
  		//check and close privacy condition
  		Boolean isPresentPrivacyCondition = driver.findElements(By.id("privacy-remove")).size() > 0;
  			if(isPresentPrivacyCondition == true) 
  				driver.findElement(By.id("privacy-remove")).click();
  	}
    
    @Step("Click SignIN Button")
	public LoginPage clickSignInButton() {
		driver.findElement(singInButtonLocator).click();
		return new LoginPage(driver);
	}	
	
/*	public void searchSetDateFrom() {
		driver.findElement(departureDateCalendar).click();
		driver.findElement(nextMonthCalendar).click();
		driver.findElement(selectDayDeparture).click();
	}*/
	
	@Step("Set date Return")
	public void searchSetDateReturn() {
		
		//input field for Return Date by default is disabled, make it enabled
		String js = "arguments[0].removeAttribute('disabled');";
		By locatorInputReturnDate = By.xpath("//*[@id='returnDate']");
		WebElement returnDateCalendarMakeVisible = driver.findElement(locatorInputReturnDate); 
		((JavascriptExecutor) driver).executeScript(js, returnDateCalendarMakeVisible);
		//------------------
		By locatorDivReturnDate = By.xpath("//*[@id='dateToDiv']");
		driver.findElement(locatorDivReturnDate).click();
		driver.findElement(selectDayReturn).click();
	}

	@Step("Click Search Button for OW")
	public FlightSearchPageOW clickSearchButtonOW() {
		searchButton.click();
		return new FlightSearchPageOW(driver);
	}
	
	@Step("Click Search Button for RT")
	public FlightSearchPageRT clickSearchButtonRT() {
		searchButton.click();
		return new FlightSearchPageRT(driver);
	}
	
	//By fromAirportLocator = By.xpath("//*[@id='fromAirport']");
	@Step("Set Airport From")
	public HomePage searchSetDirectionFrom(String airportFrom) {
		WebElement fromAirport = driver.findElement(By.xpath("//*[@id='fromAirport']"));
		fromAirport.click();
		fromAirport.clear();
		fromAirport.sendKeys(airportFrom);
		driver.findElement(fromAirportAutocomplete).click();
		return this;
	}
   
	@Step("Set Airport Return")
	public HomePage searchSetDirectionReturn(String airportReturn) {
		WebElement returnAirport = driver.findElement(toAirportLocator);
		returnAirport.click();
		returnAirport.clear();
		returnAirport.sendKeys(airportReturn);
		driver.findElement(toAirportAutocomplete).click();
		return this;
	}


	 @Step("Open flightSearch @url")
	public FlightSearchPageOW openFlightSearchPageOW (String url) {
		driver.get(url);	
		return new FlightSearchPageOW(driver);
	}

}
