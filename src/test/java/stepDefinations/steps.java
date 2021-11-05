package stepDefinations;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.page.object.libraries.ArdalanMainLibrary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class steps {

	final static Logger logger = Logger.getLogger(steps.class);
	private String URL = "https://www.saucedemo.com/";
	public static ArdalanMainLibrary ardLib;
	private static WebDriver driver;

	@Given("I am on the Sauce Demo Login Page")
	public void i_am_on_the_sauce_demo_login_page() {

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

	@When("I fill the account information for account StandardUser into the Username field and the Password field")
	public void i_fill_the_account_information_for_account_standard_user_into_the_username_field_and_the_password_field() {
		ardLib.enterText(By.id("user-name"), "standard_user");
		logger.info("You entered UserName : standard_user ");
		ardLib.enterText(By.id("password"), "secret_sauce");
		logger.info("You entered Password : secret_sauce");

	}

	@When("I click the Login Button")
	public void i_click_the_login_button() {
		driver.findElement(By.id("login-button")).click();
		logger.info("Clicking on 'Log in' button.");
	}

	@Then("I am redirected to the Sauce Demo Main Page")
	public void i_am_redirected_to_the_sauce_demo_main_page() {
		logger.info("Navagating to 'Main' Page ---> ");

	}

	@Then("I verify the App Logo exists")
	public void i_verify_the_app_logo_exists() {

		boolean verifyloGO = driver.findElement(By.className("peek")).isDisplayed();

		if (verifyloGO) {

			logger.info("Logo is dispalyed.");
		}

		else {

			logger.info("Logo is not displayed!");
		}
		driver.close();
		driver.quit();
	}

	@When("I fill the account information for account LockedOutUser into the Username field and the Password field")
	public void i_fill_the_account_information_for_account_locked_out_user_into_the_username_field_and_the_password_field() {

		ardLib.enterText(By.id("user-name"), "locked_out_user");

		logger.info("You entered UserName : locked_out_user ");

		ardLib.enterText(By.id("password"), "secret_sauce");
		logger.info("You entered Password : secret_sauce");
	}

	@Then("I verify the Error Message contains the text {string}")
	public void i_verify_the_error_message_contains_the_text(String string) {

		String actualMassage = driver
				.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error"))
				.getText();

		String expectedMsg = "Epic sadface: Sorry, this user has been locked out.";
		Assert.assertEquals(expectedMsg, actualMassage);

		logger.info("Error Massage is : ' " + actualMassage + " ' ");

		driver.close();
		driver.quit();

	}

}
