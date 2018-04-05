package com.epam.tat19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://github.com/login";
	private final String USER_LOGIN = "UserTestTat19";
	private final String USER_PASSWORD = "testpasswordfortestuser!";
	private final String EMAIL_ADDRESS = "testusertat@gmail.com";

	@FindBy(xpath = "//*[@id=\"login\"]/p/a")
	private WebElement createNewAccountLink;

	@FindBy(id = "user_login")
	private WebElement inputUserLogin;

	@FindBy(id = "user_email")
	private WebElement inputUserEmail;

	@FindBy(id = "user_password")
	private WebElement inputUserPassword;

	@FindBy(xpath = "//*[@id=\"signup_button\"]")
	private WebElement createAnAccountButton;

	@FindBy(xpath = "//*[@id=\"js-pjax-container\"]/div/div[2]/div/form/button")
	private WebElement step2ContinueButton;

	@FindBy(name = "answers[98][choice]")
	private WebElement step3SomewhatExperiencedRadioButton;

	@FindBy(name = "answers[99][choices][]")
	private WebElement step3ResearchCheckbox;

	@FindBy(name = "answers[100][choice]")
	private WebElement step3StudentRadioButton;

	@FindBy(name = "commit")
	private WebElement step3SubmitButton;

	@FindBy(id = "login_field")
	private WebElement inputLogin;

	@FindBy(id = "password")
	private WebElement inputPassword;

	@FindBy(xpath = "//input[@value='Sign in']")
	private WebElement buttonSubmit;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}

	public void createNewAccount() {
		createNewAccountLink.click();
		inputUserLogin.sendKeys(USER_LOGIN);
		inputUserEmail.sendKeys(EMAIL_ADDRESS);
		inputUserPassword.sendKeys(USER_PASSWORD);
		createAnAccountButton.click();
		step2ContinueButton.click();
		step3SomewhatExperiencedRadioButton.click();
		step3ResearchCheckbox.click();
		step3StudentRadioButton.click();
		step3SubmitButton.click();
	}

	public void login(String username, String password) {
		inputLogin.sendKeys(username);
		inputPassword.sendKeys(password);
		buttonSubmit.click();
		logger.info("Login performed");
	}

}
