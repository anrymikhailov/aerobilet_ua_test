package ua.aerobilet.test;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import ua.aerobilet.drivers.GridParallelTestBase;
import ua.aerobilet.drivers.Parallelized;
import ua.aerobilet.entities.Users;
import ua.aerobilet.pageObjects.HomePage;
import ua.aerobilet.pageObjects.LoginPage;
import ua.aerobilet.pageObjects.WorkplacePage;

import org.junit.Assert;

@RunWith(Parallelized.class)
public class LoginSuccessWithEmailPass extends GridParallelTestBase {
	
	 public LoginSuccessWithEmailPass(String platform, String browserName, String browserVersion) {
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
	public void loginSuccessWithEmailPass () throws InterruptedException {
		
		HomePage homePage = new HomePage(driver);
		homePage.openHomePage();
		LoginPage loginPage = homePage.clickSignInButton();
		loginPage.loginAs(Users.userUA.login, Users.userUA.password);
		//assertOnPage("workplace");
		WorkplacePage workplacePage = loginPage.clickLoginButton();
		makeScreenshotForCheck();
		Assert.assertEquals(Users.userUA.userName, workplacePage.getUserName());
		workplacePage.goToHomePage();
	}
	
	
	@After
	public void shutDown() throws Exception {
		   // to do
		  }
	
}
