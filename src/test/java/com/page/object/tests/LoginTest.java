package com.page.object.tests;

import org.testng.annotations.Test;

import com.page.object.libraries.Base;
import com.page.object.pages.LoginPage;

public class LoginTest extends Base {
	LoginPage myLogInPage = new LoginPage();

	@Test(enabled = true, priority = 1)
	public void firstScenario() {

		myLogInPage.sendUsername("standard_user")
		.sendPassword("secret_sauce")
		.clickLOGINbtnToMainPage()
		.showLogo();

	}

	@Test(enabled = true, priority = 2)
	public void secondScenario() {

		myLogInPage.sendUsername("locked_out_user")
		.sendPassword("secret_sauce")
		.clickLogInBtn();

	}

}
