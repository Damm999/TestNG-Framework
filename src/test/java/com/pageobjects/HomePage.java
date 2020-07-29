package com.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reporting.ExtentReporting;
import com.utils.SeleniumUtils;

public class HomePage {
	
	WebDriver driver;
	SeleniumUtils sel;
	ExtentReporting ex;
	
	public HomePage(WebDriver driver, SeleniumUtils seleniumHelper, ExtentReporting ex) {
		this.driver = driver;
		this.sel = seleniumHelper;
		this.ex = ex;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(css = "[class='btn club-home-button shop-menu-btn']")
	public WebElement menuButton;
	
	@FindBy(css = "[class='navigation--entry login-logout']")
	public WebElement loginButton;
	
	@FindBy(css = ".register--login-email > input")
	public WebElement emailAddressTextBox;
	
	
	public void navigateToMenusPage() throws IOException {
		sel.clickElement(menuButton);
	}
	
	public void clickOnLogin() throws IOException {
		sel.clickElement(loginButton);
		sel.waitForElementToBeVisible(emailAddressTextBox);
	}
}
