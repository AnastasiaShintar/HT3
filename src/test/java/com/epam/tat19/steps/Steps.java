package com.epam.tat19.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.epam.tat19.driver.DriverSingleton;
import com.epam.tat19.pages.AccountPage;
import com.epam.tat19.pages.LoginPage;
import com.epam.tat19.pages.MainPage;
import com.epam.tat19.pages.PublicProfilePage;

public class Steps {

	private WebDriver driver;

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void createNewAccount() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.createNewAccount();

	}

	public String getUserLogin() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		String userLogin = mainPage.getAccountName();
		return userLogin;
	}

	public void loginGithub(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public void enterProfileData() {
		PublicProfilePage publicProfilePage = new PublicProfilePage(driver);
		publicProfilePage.openPage();
		publicProfilePage.inputProfileData();
	}

	public String getSuccessEnterProfileData() {
		PublicProfilePage publicProfilePage = new PublicProfilePage(driver);
		String message = publicProfilePage.getSuccessMessage();
		return message;
	}

	public String searchGitHub(String inputData) {
		MainPage mainPage = new MainPage(driver);
		mainPage.enterDataIntoSearchLine(inputData);
		String title = driver.getTitle();
		return title;
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public String deleteAccount(String userName, String confirmationPhrase) {
		AccountPage accountPage = new AccountPage(driver);
		accountPage.openPage();
		accountPage.deleteAccount(userName, confirmationPhrase);
		String message = driver.findElement(By.tagName("body")).getText();
		return message;
	}
}
