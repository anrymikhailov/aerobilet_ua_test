package ua.aerobilet.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class LoginPage extends AbstractPage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//LOGIN elements
    By singInButtonLocator = By.xpath("//*[@id='loginlink']");
    By emailInputLocator = By.xpath("//*[@id='formLogin']/ul/li[1]/input");
    By passwordInputLocator = By.xpath("//*[@id='formLogin']/ul/li[2]/input");
   // By loginButtonLocator = By.xpath("//*[@id='formLogin']/ul/li[4]/div[1]/button");
    
    @FindBy(xpath ="//*[@id='formLogin']/ul/li[4]/div[1]/button")
    WebElement loginButton;
    
    By userNameLocator = By.xpath("//*[@id='userDropdown']/span[1]");
 
    @Step("Click SignIN button")
    public void clickSignInButton() {
		driver.findElement(singInButtonLocator).click();
	}
	

	public void typeEmail(String loginuser) {
		WebElement login = driver.findElement(emailInputLocator);
		login.click();
		login.clear();
		login.sendKeys(loginuser);
	}
    
	public void typePassword(String passworduser) {
		WebElement password = driver.findElement(passwordInputLocator);
		password.click();
		password.clear();
		password.sendKeys(passworduser);	
	}

	 @Step("Click LOGIN button")
	public WorkplacePage clickLoginButton() {
		 loginButton.click();
		return new WorkplacePage(driver);
	}
	 
	 @Step("Click LOGIN button, expect Error")
		public void clickLoginButtonExpectError() {
			 loginButton.click();	
		}
	 
	@Step("Check Login Error")
		public String getLoginError(){
			By loginError = By.xpath("//*[@id='loginError']/p");
			return driver.findElement(loginError).getText();
		}
	
   @Step("Type Login and Password")
	public void loginAs(String login, String password){
		typeEmail(login);
		typePassword(password);
	}
	 
	
	public LoginPage submitLoginExpectingFailure() {
        // This is the only place that submits the login form and expects the destination to be the login page due to login failure.
		loginButton.submit();
        return new LoginPage(driver);	
    }
	
	@Step("Check user name")
	public void checkUserName(String loginName){
		Assert.assertEquals("Wrong user name", driver.findElement(userNameLocator).getText(), loginName);
		System.out.println("User name is OK");
	}
}
