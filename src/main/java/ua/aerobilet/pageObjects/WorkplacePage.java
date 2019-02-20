package ua.aerobilet.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class WorkplacePage extends AbstractPage{
	
	
	public WorkplacePage(WebDriver driver) {
		super(driver);
	} 
		
	@Step("Check User Name")
	public String getUserName(){
		By userNameLocator = By.xpath("//*[@id='userDropdown']/span[1]");
		return driver.findElement(userNameLocator).getText();
	}
	

	 @Step("Back to HomePage click on logo Aerobilet")
	public HomePage goToHomePage() {
		driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div[1]")).click();
		return new HomePage(driver);
	}

	 @Step("Click on MyOrders in personal account")
	public OrdersPage clickOnMyOrders() {
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[1]/a")).click();
		return new OrdersPage(driver);
	}
}

