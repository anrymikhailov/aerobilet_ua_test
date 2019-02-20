package ua.aerobilet.test;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import ua.aerobilet.entities.Passengers;
import ua.aerobilet.entities.Users;
import ua.aerobilet.pageObjects.HomePage;
import ua.aerobilet.pageObjects.LoginPage;
import ua.aerobilet.pageObjects.WorkplacePage;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import ua.aerobilet.drivers.GridParallelTestBase;
import ua.aerobilet.drivers.LocalDriver;
import ua.aerobilet.drivers.Parallelized;
import ua.aerobilet.entities.DataXmlParser;
import ua.aerobilet.entities.Passenger;

@RunWith(Parallelized.class)
public class LoginUnsuccessWithWrongEmailPass extends GridParallelTestBase {
	
	 public LoginUnsuccessWithWrongEmailPass(String platform, String browserName, String browserVersion) {
	        super(platform, browserName, browserVersion);
	    }
	 
	@Rule
	public final TestRule watchman = new TestWatcher() {
			@Override
			protected void failed(Throwable e, Description description){
				makeScreenshotForCheck();
			}
		};
	
	@Test
	public void loginUnsuccessWithWrongEmailPass () {
		
		HomePage homePage = new HomePage(driver);
		homePage.openHomePage();
		LoginPage loginPage = homePage.clickSignInButton();
		loginPage.loginAs(Users.unsuccessUser.login, Users.unsuccessUser.password);
		loginPage.clickLoginButtonExpectError();
		makeScreenshotForCheck();
				//Your login information is incorrect. Please check your e-mail and password and try again
		Assert.assertEquals("ffsdНадана інформація некоректна. Будь ласка, перевірте Вашу адресу електронної пошти та пароль, і спробуйте ще раз.", 
                    loginPage.getLoginError());
		
	}
}
