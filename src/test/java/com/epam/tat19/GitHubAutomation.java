package com.epam.tat19;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.tat19.steps.Steps;

public class GitHubAutomation {

	private Steps steps;
	private final String USER_LOGIN = "UserTestTat19";
	private final String USER_PASSWORD = "testpasswordfortestuser!";
	private final String SUCCESS_MESSAGE = "Profile updated successfully";
	private final String INPUT_DATA = "selenium";
	private final String CONFIRMATION_PHRASE = "delete my account";
	private final String DELETE_ACCOUNT_MESSAGE = "Account successfully deleted.";

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(description = "Create new account", priority = 1) // создание нового аккаунта
	public void createNewAccount() {
		steps.createNewAccount();
		Assert.assertEquals(steps.getUserLogin(), USER_LOGIN);
	}

	@Test(description = "Change profile data", priority = 2) // изменение данных профиля
	public void enterProfileData() {
		steps.loginGithub(USER_LOGIN, USER_PASSWORD);
		steps.enterProfileData();
		Assert.assertTrue(steps.getSuccessEnterProfileData().contains(SUCCESS_MESSAGE));
	}

	@Test(description = "Check search line", priority = 3) // проверка работы поисковой строки
	public void checkSearchLine() {
		steps.loginGithub(USER_LOGIN, USER_PASSWORD);
		Assert.assertEquals(steps.searchGitHub(INPUT_DATA), "Search · " + INPUT_DATA);
	}

	@Test(description = "delete account", priority = 4) // удаление аккаунта
	public void deleteAccount() {
		steps.loginGithub(USER_LOGIN, USER_PASSWORD);
		Assert.assertTrue(steps.deleteAccount(USER_LOGIN, CONFIRMATION_PHRASE).contains(DELETE_ACCOUNT_MESSAGE));
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();
	}
}
