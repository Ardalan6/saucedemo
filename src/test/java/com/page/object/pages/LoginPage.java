package com.page.object.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;


import com.page.object.libraries.Base;

public class LoginPage extends Base {

	
	final static Logger logger = Logger.getLogger(LoginPage.class);
	
	
	public LoginPage sendUsername(String UserName) {
logger.info("You entered UserName : "+UserName);
		ardLib.enterText(By.id("user-name"), UserName);

		return this;
	}

	public LoginPage sendPassword(String Password) {
		logger.info("You entered Password : "+Password);
		ardLib.enterText(By.id("password"), Password);

		return this;
	}

	public LoginPage clickLogInBtn() {
		logger.info("Clicking on 'Log in' button.");
		driver.findElement(By.id("login-button")).click();

		String actualMassage = driver
				.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error"))
				.getText();

		String expectedMsg = "Epic sadface: Sorry, this user has been  banned.";
		Assert.assertEquals(expectedMsg, actualMassage);

		logger.info("Error Massage is : ' " + actualMassage + " ' ");
		return this;
	}

	public mainPage clickLOGINbtnToMainPage() {
		logger.info("Clciking on 'Log in' button.");
		driver.findElement(By.id("login-button")).click();
		logger.info("Navagating to 'Main' Page ---> ");
		return new mainPage();
	}

}
