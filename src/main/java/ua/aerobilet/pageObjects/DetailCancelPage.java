package ua.aerobilet.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailCancelPage extends AbstractPage{

	 // not use now
	public DetailCancelPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void writeNoteWhyCancel(String myNote) {
		driver.findElement(By.id("cancelNote")).click();
		driver.findElement(By.id("cancelNote")).sendKeys(myNote);
		// TODO Auto-generated method stub
		
	}

	public void clickOnCancelButton() {
		// TODO Auto-generated method stub
		
	}
	

}
