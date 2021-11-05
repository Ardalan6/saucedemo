package com.page.object.libraries;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {

	final static Logger logger = Logger.getLogger(Base.class);

	public static ArdalanMainLibrary ardLib;
	public static WebDriver driver;
	String URL = "https://www.saucedemo.com/";

	@BeforeMethod
	public void setUp() {

		ardLib = new ArdalanMainLibrary(driver);
		driver = ardLib.startChromeBrowser();
		driver.get(URL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String webSiteTitle = driver.getTitle();
		logger.info("WebSite title : " + webSiteTitle);
		String expectedTitle = "Swag Labs";
		assertEquals(webSiteTitle, expectedTitle);
	}

	@AfterMethod
	public void tearDown() {

		ardLib.customWait(5);
		ardLib.captureScreenshot();
		if (driver != null) {
			driver.close();
		}

	}

	@AfterClass
	public void cleanUpAfterAllTestMethods() {
		logger.info("After Class ...");
		if (driver != null) {
			driver.quit();
		}
	}

}
