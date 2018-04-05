package com.epam.tat19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://github.com/";

	@FindBy(xpath = "//*[@id=\"user-links\"]/li[3]/details/summary")
	private WebElement viewProfile;

	@FindBy(xpath = "//*[@id=\"user-links\"]/li[3]/details/ul/li[8]/a")
	private WebElement profileSettingsLink;

	@FindBy(xpath = "//*[@id=\"user-links\"]/li[3]/details/ul/li[1]/strong")
	private WebElement accountName;

	@FindBy(xpath = "//*[@id=\"user-links\"]/li[3]/details/ul/li[3]/a")
	private WebElement profileLink;

	@FindBy(name = "q")
	private WebElement searchLine;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public String getAccountName() {
		viewProfile.click();
		String userLogin = accountName.getText();
		return userLogin;
	}

	public void enterDataIntoSearchLine(String inputData) { // ввод данных в строку поиска
		searchLine.sendKeys(inputData);
		searchLine.sendKeys(Keys.ENTER);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		if ((!driver.getTitle().equals("GitHub")) || (!driver.getCurrentUrl().equals(BASE_URL))) {
			throw new IllegalStateException("Wrong site page!");
		}
		logger.info("Main page opened");
	}

}
