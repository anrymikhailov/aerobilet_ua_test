package ua.aerobilet.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class FlightReservationDetailPage extends AbstractPage {

	public FlightReservationDetailPage(WebDriver driver) {
		super(driver);
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshotForCheck() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

	//take table with all passengers
	WebElement locatorPassengersTable = driver.findElement(By.xpath("//*[@id='FormTable']"));
	
	public FlightReservationDetailPage fillCitizenship(String passengerCitizenship) {
		// TODO Auto-generated method stub	
		return this;
	}
	 protected List<WebElement> $$(By by) {
	        return driver.findElements(by);
	    }

	 protected WebElement $(By by) {
	        return driver.findElement(by);
	    }
	    
	@Step("Fill name of passengers")
	public FlightReservationDetailPage fillName(String passengerName) {
		List<WebElement> fillNameList = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_name_')]"));
		for (int i = 0; i < fillNameList.size(); i++) {
			fillNameList.get(i).click();
			fillNameList.get(i).clear();
			fillNameList.get(i).sendKeys(passengerName);
            System.out.println("Name of passenger "+i+" = "+passengerName);
            }
		return this;
	}

	@Step("Fill surname of passengers")
	public FlightReservationDetailPage fillSurname(String passengerSurname) {
		List<WebElement> fillSurnameList = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_surname_')]"));
		for (int i = 0; i < fillSurnameList.size(); i++) {
			fillSurnameList.get(i).click();
			fillSurnameList.get(i).clear();
			fillSurnameList.get(i).sendKeys(passengerSurname);
            System.out.println("Surname of passenger "+i+" = "+passengerSurname);
		}
		return this;
	}

	@Step("Fill birthday of passengers")
	public FlightReservationDetailPage fillDateOfBirth(String passengerBirth) {
		List<WebElement> fillBirthdateList = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_birthdate_')]"));
		for (int i = 0; i < fillBirthdateList.size(); i++) {
			fillBirthdateList.get(i).click();
			fillBirthdateList.get(i).clear();
			fillBirthdateList.get(i).sendKeys(passengerBirth);
            System.out.println("Birthday of passenger "+i+" = "+passengerBirth);
		}
		return this;
	}

	@Step("Fill noDocument of passengers")
	public FlightReservationDetailPage fillNoDocument(String passengerNoDocument) {
		List<WebElement> fillNoDocumentList = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_passportNo_')]"));
		for (int i = 0; i < fillNoDocumentList.size(); i++) {
			fillNoDocumentList.get(i).click();
			fillNoDocumentList.get(i).clear();
			fillNoDocumentList.get(i).sendKeys(passengerNoDocument);
            System.out.println("No document of passenger "+i+" = "+passengerNoDocument);
		}
		return this;
	}

	@Step("Fill expiry date of document of passengers")
	public FlightReservationDetailPage fillExpiryDateOfDocument(String passengerExpiryDateOfDocument) {
		List<WebElement> fillExpiryDateOfDocumentList = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_endDate_')]"));
		for (int i = 0; i < fillExpiryDateOfDocumentList.size(); i++) {
			fillExpiryDateOfDocumentList.get(i).click();
			fillExpiryDateOfDocumentList.get(i).clear();
			fillExpiryDateOfDocumentList.get(i).sendKeys(passengerExpiryDateOfDocument);
            System.out.println("Expiry date of document of passenger "+i+" = "+passengerExpiryDateOfDocument);
		}
		return this;
	}

	@Step("Fill noPhone of buyer")
	public FlightReservationDetailPage fillNoPhone(String buyerNoPhone) {
		WebElement fillPhoneNumber = driver.findElement(By.id("gsm"));
		fillPhoneNumber.click();
		fillPhoneNumber.clear();
		fillPhoneNumber.sendKeys(buyerNoPhone);
		return this;
	}

	@Step("Fill email of buyer")
	public FlightReservationDetailPage fillEmail(String buyerEmail) {
		WebElement fillEmail = $(By.id("email"));
		fillEmail.click();
		fillEmail.clear();
		fillEmail.sendKeys(buyerEmail);
		return this;
	}

	@Step("Choose payment type Make reservation")
	public FlightReservationDetailPage choosePaymentTypeMakeReservation() {
		$(By.id("booking-tab-li")).click();
		return this;
	}

	@Step("Agree with Agreement Reservation")
	public FlightReservationDetailPage agreeWithAgreementReservation() {
		$(By.xpath("//input[contains(@class,'agreeCheckBox')]")).click();
		return this;
	}
	
	@Step("Agree with Agreement Buy")
	public FlightReservationDetailPage agreeWithAgreementBuy() {
		$(By.xpath("//input[contains(@class,'agreeCheckBox')]")).click();
		return this;
	}

	@Step("Click Make reservation button")
	public void clickMakeReservation() {
		$(By.id("makeReservation")).click();
	}
	
	@Step("Check success reservation or not")
	public boolean checkSuccessReservation(){
		
		boolean buttonBuy = $(By.xpath("//*[@id='content']/div/div/div[1]/input")).isDisplayed();	
		//Boolean buttonBuy = driver.findElements(By.className("button")).size() > 0;		
		//https://www.aerobilet.ru/flights/reservation-confirmed
		////*[@id="content"]/div/div/p -> <p class="alert alert-success">
		// <input type="button" name="" class="button" onclick="showReservation('J8JL26',17507947)" value="Купить билет">
		// #content > div > div > div.payDetailButtons > input
		//or
		////*[@id="content"]/div/div/div[1]/input
		return buttonBuy;
	}
	@Step("Click on Agreement link and check Agreement content ")
	public boolean clickOnAgreementLinkAndCheckContent(String textAgreementRuFirstElement) {
		(new WebDriverWait(driver, 20))
		  .until(ExpectedConditions.elementToBeClickable($(By.xpath("//*[@id='payDetailCC']/label/a[1]"))));
		$(By.xpath("//*[@id='payDetailCC']/label/a[1]")).click();		
		makeScreenshotForCheck();
		return $(By.tagName("body"))
				 .findElements(By.tagName("p"))
				 .stream()
				 .anyMatch(text -> text.getText().equals(textAgreementRuFirstElement));
		
	}
	
	@Step("Click on Agreement link and check Agreement content UA ")
	public boolean clickOnAgreementLinkAndCheckContentUA(String textAgreementRuFirstElement) {
		(new WebDriverWait(driver, 20))
		  .until(ExpectedConditions.elementToBeClickable($(By.xpath("//*[@id='payDetailCC']/label/a[1]"))));
		$(By.xpath("//*[@id='payDetailCC']/label/a[1]")).click();		
		makeScreenshotForCheck();
		WebElement locatorBody = $(By.tagName("body"));
		return locatorBody
				 .findElements(By.tagName("p"))
				 .stream()
				 .anyMatch(text -> text.getText().equals(textAgreementRuFirstElement));
	}


	@Step("Click on PrivatPolicy Link and Check Policy content")
	public boolean clickOnPrivatPolicyLinkAndCheckContent(String textPolicyRuFirstElement) {
		(new WebDriverWait(driver, 20))
		  .until(ExpectedConditions.elementToBeClickable($(By.xpath("//*[@id='payDetailCC']/label/a[2]"))));
		$(By.xpath("//*[@id='payDetailCC']/label/a[2]")).click();
		makeScreenshotForCheck();
		return $(By.tagName("body"))
				 .findElements(By.tagName("p"))
				 .stream()
				 .anyMatch(text -> text.getText().equals(textPolicyRuFirstElement));
	}
	
	@Step("Click on PrivatPolicy Link and Check Policy content UA")
	public boolean clickOnPrivatPolicyLinkAndCheckContentUA(String textPolicyRuFirstElement) {
		(new WebDriverWait(driver, 20))
		  .until(ExpectedConditions.elementToBeClickable($(By.xpath("//*[@id='payDetailCC']/label/a[2]"))));
		$(By.xpath("//*[@id='payDetailCC']/label/a[2]")).click();
		makeScreenshotForCheck();
		WebElement locatorBody = $(By.tagName("body"));
		return locatorBody
				 .findElements(By.tagName("p"))
				 .stream()
				 .anyMatch(text -> text.getText().equals(textPolicyRuFirstElement));
	}
	
	@Step("Get country phone code")
	public Object getCountryPhoneCode() {
		return $(By.cssSelector(
"#FormTable2 > div.col-md-5.col-sm-6.col-xs-12 > div > div.col-sm-3.col-xs-5.gsm-co > div > div > ul > li.country.active"))
				.getAttribute("data-dial-code");
	}

	@Step("Get country code")
	public Object getCountryIsoCode() {
		return $(By.cssSelector(
"#FormTable2 > div.col-md-5.col-sm-6.col-xs-12 > div > div.col-sm-3.col-xs-5.gsm-co > div > div > ul > li.country.active"))
				.getAttribute("data-country-code");
	}
 
	@Step("Get nationality")
	public Object getNationalityByDefault() {
		return $(By.xpath("//*[@id='adult_citizenshipCountry_0_title']/span[1]"))
				.getText();
	}

	@Step("Check if exist checkbox expiry date")
	public boolean ifExistExpiryDateCheckbox() {
	    Boolean isPresent = $$(By.id("passExpireCheck")).size() > 0;
	    		return isPresent;
	} 
	
	@Step("Check if exist checkbox I am Not TurkCitiz")
	public boolean ifExistIamNotTurkCitizCheckbox() {
	    Boolean isPresent = $$(By.id("adult_notTC_0")).size() > 0;
	    		return isPresent;
	}
	
	@Step("Fill Credit Card pan code")
	public FlightReservationDetailPage fillCreditCardPin(String pan) {
		WebElement locatorPin = driver.findElement(By.id("pan"));
		locatorPin.click();
		locatorPin.clear();
		locatorPin.sendKeys(pan);
		return this;
	} 
	 
	@Step("Fill Credit Card expire_month and expire_year")
	public FlightReservationDetailPage fillCreditCardDate(String month, String year) {
		WebElement locatorMonth = driver.findElement(By.id("cc_expire_month"));
		Select dropdownMonth = new Select(locatorMonth);
		dropdownMonth.selectByVisibleText(month);
		//dropdownMonth.selectByIndex(month);
		
		WebElement locatorYear = driver.findElement(By.id("cc_expire_year"));
		Select dropdownYear = new Select(locatorYear);
		dropdownYear.selectByVisibleText(year);
		return this;
	}
	
	@Step("Fill Credit Card cvv code")
	public FlightReservationDetailPage fillCreditCardCvv(String cvv) {
		WebElement locatorCvv = driver.findElement(By.id("cv2"));
		locatorCvv.click();
		locatorCvv.clear();
		locatorCvv.sendKeys(cvv);
		return this;
	}
	
	@Step("Fill Credit Card owner name")
	public FlightReservationDetailPage fillCreditCardOwnerName(String name) {
		WebElement locatorCardOwnerName = driver.findElement(By.id("cc_owner_name"));
		locatorCardOwnerName.click();
		locatorCardOwnerName.clear();
		locatorCardOwnerName.sendKeys(name);
		return this;
	}
	
	@Step("Choose 3ds payment checkbox")
	public FlightReservationDetailPage choose3DsPaymentCheckBox() {
		return this;
	}
	
	@Step("Click on button BUY")
	public FlightReservationDetailPage clickButtonBuy() {
		WebElement locatorButtonBuy = driver.findElement(By.id("makeTicketFinal"));
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
				
				
		locatorButtonBuy.click();
		//driver.wait();
		// Switch to new window opened if Chrome
		//System.out.println("size = " + driver.getWindowHandles().size());
		if (driver.getWindowHandles().size()>1)
		{ for(String winHandle : driver.getWindowHandles()){
						driver.switchTo().window(winHandle);
						System.out.println(driver.getCurrentUrl());
						driver.close();
						driver.switchTo().window(winHandleBefore);
			}
		} else {
			  WebDriverWait wait = new WebDriverWait(driver,15);
			  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("3dsecureFrame"));  
			  
		}
		//driver.getCurrentUrl();
		return this;
	}
	
	@Step("Check if exist 3DS Frame")
	public boolean ifExist3DsFrame() {
	    Boolean isPresent = $$(By.id("table3")).size() > 0;
	    		return isPresent;
	}

	@Step("Choose gender type")
	public FlightReservationDetailPage fillGender() {
		List<WebElement> selectGender = locatorPassengersTable.findElements(By.xpath("//*[contains(@id,'adult_gender_')]"));
		for (int i = 0; i < selectGender.size(); i++) {
			Select dropdown= new Select(selectGender.get(i));
			dropdown.selectByIndex(1);
            }
        return this;		
	}
}
