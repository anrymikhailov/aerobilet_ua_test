package ua.aerobilet.drivers;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Attachment;

public class LocalDriver {

protected RemoteWebDriver driver;
protected PropertyManager property;
	
	@Before
	public void setUp() {
	//add our property for test's
	property = new PropertyManager();
	property.generateProperty();
	System.setProperty("webdriver.gecko.driver", "D:/devel/eclipse/tools/geckodriver19_1/geckodriver.exe");	
	driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void tearDown() {
		//driver.close();
		if (driver != null){
			driver.quit();
			driver = null;
		}
	}
	@Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshotForCheck() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
