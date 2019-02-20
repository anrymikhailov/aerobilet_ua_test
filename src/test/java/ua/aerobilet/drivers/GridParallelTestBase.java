package ua.aerobilet.drivers;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.*;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
 
public class GridParallelTestBase {
	
	
    //Declare DesiredCapabilities configuration variables

	protected String browserName;
	protected String browserVersion;
	protected String platform;
    protected WebDriver driver;
    protected PropertyManager property;
 
    //Hold all Configuration values in a LinkedList
    
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{Platform.LINUX.toString(),"chrome", "71"});
		env.add(new String[]{Platform.LINUX.toString(),"firefox","65"});
		env.add(new String[]{Platform.LINUX.toString(),"opera","58.0"});
		//env.add(new String[]{Platform.WINDOWS.toString(),"ie","9"});
        //add more browsers here
        return env;
    }
 
    //Constructor
    public GridParallelTestBase(String platform, String browserName, String browserVersion) {
    	this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }
 
    @Before
    public void setUp() throws Exception {
    	//add our property for test's
    	property = new PropertyManager();
		property.generateProperty();
        //Set DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Firefox Profile Settings
        if (browserName.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            //Accept Untrusted Certificates
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            //Use No Proxy Settings
            profile.setPreference("network.proxy.type", 0);
            //Set Firefox profile to capabilities        
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        }
       
        // Due to bug in operablink the binary path must be set
        if (browserName.equals("opera")) {
      	  OperaOptions options = new OperaOptions();
      	   //options.setBinary(new File("/usr/bin/opera"));
      	 ((OperaOptions) options).setBinary("/usr/bin/opera");
      	   capabilities.setCapability(OperaOptions.CAPABILITY, options);
      }
        
      //set proxy
      	/*	String PROXY = "10.38.1.212:3128";
      		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
      		proxy.setHttpProxy(PROXY)
      		     .setFtpProxy(PROXY)
      		     .setSslProxy(PROXY);  		
      	capabilities.setCapability(CapabilityType.PROXY, proxy);*/
        
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("build", "JUnit-Parallel");
        
     
        driver = new RemoteWebDriver(new URL("http://10.38.1.210:4444/wd/hub"), capabilities);
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
 
    //Save screenshot to Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshotForCheck() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); 	
    }
    
    //Save screenshot to local machine
    public void takeScreenShot () {
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = getClass().getSimpleName();
        System.out.println("ScreenShotName: " + screenshotName);
        try {
            FileUtils.copyFile(srcFile, new File("screenshotName.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
