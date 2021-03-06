package ua.aerobilet.drivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Attachment;
import ua.aerobilet.drivers.PropertyManager;

public class ChromeHeadlessDriverHub {
	protected RemoteWebDriver driver;
	protected PropertyManager property;
	
	@Before
	public void setUp() {	
		
		//add our property for test's
		property = new PropertyManager();
		property.generateProperty();
		
		// create remote driver
		DesiredCapabilities browser = new DesiredCapabilities();
		browser.setBrowserName("chrome");
		browser.setVersion("71.0");
		browser.setCapability("enableVNC", true);
		browser.setCapability("screenResolution", "1920x1080x24");
		
		//set proxy
		String PROXY = "10.38.1.212:3128";
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY)
		     .setFtpProxy(PROXY)
		     .setSslProxy(PROXY);
		
		browser.setCapability(CapabilityType.PROXY, proxy);
		
		browser.setCapability(ChromeOptions.CAPABILITY, "--headless");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		browser.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
		
		try {
			driver = new RemoteWebDriver(new URL("http://10.38.1.210:4444/wd/hub"), browser);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().setSize(new Dimension(1920, 1080));
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
