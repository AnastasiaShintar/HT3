package com.epam.tat19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PublicProfilePage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://github.com/settings/profile";
	private final String USER_NAME = "Super Developer";
	private final String PROFILE_BIO = "I'm a super-duper developer!";
	private final String PROFILE_COMPANY = "EPAM";
	private final String PROFILE_PICTURE_PATH = "C:\\profile_picture.jpg";// необходимо указать полный путь к файлу.Если
																			// путь и название файла иное - исправьте.
	private final String PROFILE_LOCATION = "Minsk, Belarus";

	@FindBy(name = "user[profile_name]")
	private WebElement userName;

	@FindBy(name = "user[profile_bio]")
	private WebElement profileBio;

	@FindBy(name = "user[profile_company]")
	private WebElement profileCompany;

	@FindBy(id = "upload-profile-picture")
	private WebElement uploadProfilePictureButton;

	@FindBy(name = "user[profile_location]")
	private WebElement profileLocation;

	@FindBy(xpath = "//*[@id=\"js-pjax-container\"]/div/div[2]/form/div/p/button")
	private WebElement updateProfileButton;

	@FindBy(name = "op")
	private WebElement setNewProfilePictureButton;

	@FindBy(tagName = "body")
	private WebElement pageBody;

	public PublicProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void inputProfileData() {
		uploadProfilePictureButton.sendKeys(PROFILE_PICTURE_PATH);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setNewProfilePictureButton.click();
		userName.clear();
		userName.sendKeys(USER_NAME);
		profileBio.clear();
		profileBio.sendKeys(PROFILE_BIO);
		profileCompany.clear();
		profileCompany.sendKeys(PROFILE_COMPANY);
		profileLocation.clear();
		profileLocation.sendKeys(PROFILE_LOCATION);
		
		updateProfileButton.click();
	}

	public String getSuccessMessage() {
		String successMessage = pageBody.getText();
		return successMessage;
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("Public profile page opened");
	}

}
