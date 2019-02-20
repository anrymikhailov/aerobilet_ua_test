package ua.aerobilet.pageObjects;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


public class OrdersPage extends AbstractPage{

	public OrdersPage(WebDriver driver) {
		super(driver);
	}

	@Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshotForCheck() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
	
	@Step ("Chek if exist button cancel on Cancel Page Details")
	public boolean onDetailCancelPageExistButtonCancel() {
	
		//boolean isDisplayed = driver.findElement(By.id("lblTotal")).isDisplayed();
		boolean isDisplayed = driver.findElement(By.id("cancelButton")).isDisplayed();
		return isDisplayed;
	} 
	
	@Step ("New window with Order, we make a cancel Reservation in new window")
	public boolean clickOnButtonCancelReservation() {
		List<WebElement> listOfButton = driver.findElements(By.xpath("//*[contains(@class,'cancelBtn')]"));
		
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		
		listOfButton.get(0).click(); //always first
		
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
		}
		makeScreenshotForCheck();
		// Perform the actions on new window
		driver.findElement(By.id("cancelButton")).click();	
		// Close the new window, if that window no more required
		if(ifCancelSuccess()){
			driver.close();
			driver.switchTo().window(winHandleBefore);
			return true;
		} else return false;
	} 

	@Step("Click on Cancel button on Detail Cancel Page")
	public void clickOnCancelButtonOnDetailCancelPage() {
		// Perform the actions on new window
		//driver.findElement(By.id("cancelButton")).click();	
		
	}
	
	@Step("if exist reservation for cancel then we make a cancel")
	public boolean ifExistReservationForCancel() {
		List<WebElement> listOfButton = driver.findElements(By.xpath("//*[contains(@class,'cancelBtn')]"));
		for (int i = 0; i < listOfButton.size(); i++) {
            if(listOfButton.get(i).getAttribute("onclick").contains("cancel")){
            	return true;
            	} 
            }	
		return false;
	}
 
	@Step ("is Displayed Reservation Has Been Canceled")
	public boolean ifCancelSuccess() {
		
		//boolean isDisplayedReservationHasBeenCanceled = driver.findElement(By.id("lblTotal")).isDisplayed();
		boolean isDisplayedReservationHasBeenCanceled = driver.findElement(By.id("divCancelMessage")).isDisplayed();
		System.out.println("isDisplayd text : " + driver.findElement(By.id("divCancelMessage")).getText());
		System.out.println("isDisplayd : " + isDisplayedReservationHasBeenCanceled);
		return isDisplayedReservationHasBeenCanceled;
	}

	

}
