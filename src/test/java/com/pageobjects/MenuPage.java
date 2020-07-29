package com.pageobjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.reporting.ExtentReporting;
import com.utils.SeleniumUtils;

public class MenuPage {

	WebDriver driver;
	SeleniumUtils sel;
	ExtentReporting ex;

	public MenuPage(WebDriver driver, SeleniumUtils seleniumHelper, ExtentReporting ex) {
		this.driver = driver;
		this.sel = seleniumHelper;
		this.ex = ex;
		PageFactory.initElements(this.driver, this);
	}
	
	// By elements
	By menuItems= By.cssSelector("li[class='top-up-item']");

	@FindBy(css = "[class='honest-mt-10 no-address-container'] a")
	public WebElement continueWithoutAnAddressLink;

	@FindBy(css = "[class='navigation--entry login-logout']")
	public WebElement loginButton;

	@FindBy(css = "#address-input")
	public WebElement addressTextBox;

	@FindBy(css = "[class='btn--honest blattgold--form-banner-submit']")
	public WebElement submitAddressButton;

	@FindBy(css = "#topup-modal--close")
	public WebElement popUpCloseButton;

	@FindBy(id = "jsLoadMethod")
	public WebElement jsLoader;
	
	public WebElement selectMenuItem(String name) {
		return driver.findElement(By.xpath("//input[name='" + name + "']"));
	}

	public WebElement menuNameLabel(String menuName) {
		// \"Mamacita's Burrito Menu\"
		return driver.findElement(By.xpath("//button[@class=\"buybox--button--image-overlay\"]//preceding::form//following-sibling::a[@title = \""+menuName+"\"]"));
	}

	public void verifyMenuPage() throws IOException {
		if (sel.isElementPresent(continueWithoutAnAddressLink)) {
			ex.logTestSteps(Status.PASS, "Menu page is opened.");
		} else
			ex.logTestStepWithScreenshot(driver, Status.FAIL, "Menu page is not opened.");
	}

	public void enterStreetAddress(String address) throws IOException, InterruptedException {

		sel.sendKeys(addressTextBox, address);
		sel.clickElement(submitAddressButton);
		sel.waitForElementToBeNotVisible(jsLoader);
		Thread.sleep(8000);
	}

	public void continueWithoutAddress() throws IOException {
		sel.clickElement(continueWithoutAnAddressLink);

		sel.isElementNotPresent(continueWithoutAnAddressLink);
	}

	public void selectMenu(String menuName) throws IOException {
		for(int i=0; i<5; i++)
			sel.scrollDown();
		sel.clickElementJS(menuNameLabel(menuName));
	}

	public void selectMenuItems(ArrayList<String> items, ArrayList<String> extras) throws IOException {
		List<WebElement> list = sel.getListOfElements(menuItems);

		for (String name : items) {
			sel.scrollElementIntoView(selectMenuItem(name));
			sel.clickElement(selectMenuItem(name));
		}

		for (WebElement item : list) {
			sel.scrollElementIntoView(item);
			if (extras.contains(item.getText()))
				sel.clickElement(item);
		}

		sel.clickElement(popUpCloseButton);
	}

}
