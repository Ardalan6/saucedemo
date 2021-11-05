package com.page.object.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.page.object.libraries.Base;

public class mainPage extends Base {

	final static Logger logger = Logger.getLogger(mainPage.class);

	public boolean showLogo() {

		boolean verifyloGO = driver.findElement(By.className("peek")).isDisplayed();

		if (verifyloGO) {

			logger.info("Logo is dispalyed.");
		}

		else {

			logger.info("Logo is not displayed!");
		}

		return verifyloGO;
	}

}
