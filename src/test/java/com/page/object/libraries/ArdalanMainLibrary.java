package com.page.object.libraries;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class ArdalanMainLibrary {

	final static Logger logger = Logger.getLogger(ArdalanMainLibrary.class);

	public static ArdalanMainLibrary ardLib;
	private WebDriver driver;
	private int defaultWaitTimeInSecs = 30;

	public ArdalanMainLibrary(WebDriver _driver) {
		driver = _driver;
	}

	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			logger.info("starting 'Chrome' browser...");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			setWebsiteWaits();

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private void setWebsiteWaits() {
		try {
			driver.manage().timeouts().implicitlyWait(defaultWaitTimeInSecs, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(defaultWaitTimeInSecs, TimeUnit.SECONDS);
			logger.info("Maximizing the browser.");
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void enterText(By by, String inputText) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(inputText);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public String getCurrentTime() {
		String finalTime = null;
		try {
			Date date = new Date();
			logger.debug("time1: " + date.toString());
			String tempTime = new Timestamp(date.getTime()).toString();
			logger.debug("time2: " + tempTime);
			finalTime = tempTime.replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
			logger.debug("time3: " + finalTime);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return finalTime;
	}

	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public String captureScreenshot() {
		String screenshotFilePath =null;

		try {
			
			screenshotFilePath = "target/screenshot/" + "_" + getCurrentTime() + ".png";

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(screenshotFilePath));

		} catch (Exception e) {
			logger.error("In case of diffrence between actual and expected resualt this massage will pop up. ", e);
			assertTrue(false);
		}
		logger.info("Screenshot File: " + screenshotFilePath);
		return screenshotFilePath;
	}


	
	
	
	
}
