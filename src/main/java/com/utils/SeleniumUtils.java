package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.reporting.ExtentReporting;

public class SeleniumUtils {

	WebDriver driver;
	ExtentReporting ex;
	WebDriverWait wait;
	JavascriptExecutor je;

	public SeleniumUtils(WebDriver driver, ExtentReporting extent) {
		this.driver = driver;
		ex = extent;
		wait = new WebDriverWait(driver, 10);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		je = (JavascriptExecutor) driver;
	}

	public void waitForElementToBeVisible(WebElement ele) {
			wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void clickElement(WebElement ele) throws IOException {
		try {
			waitForElementToBeVisible(ele);
			ele.click();
			ex.logTestSteps(Status.PASS, "User was able to click on element: " + ele.toString().substring(ele.toString().indexOf("->")+1));
		} catch (Exception e) {
			ex.logTestStepWithScreenshot(driver, Status.FAIL,
					"Unable to click element due to: " + e.toString() + "\n" + e.getMessage());
		}
	}
	
	public void clickElementJS(WebElement ele) throws IOException {
		try {
			waitForElementToBeVisible(ele);
			Thread.sleep(3000);
			je.executeScript("arguments[0].click();", ele);
			ex.logTestSteps(Status.PASS, "User was able to click on element: " + ele.toString().substring(ele.toString().indexOf("->")+1));
		} catch (Exception e) {
			ex.logTestStepWithScreenshot(driver, Status.FAIL,
					"Unable to click element due to: " + e.toString() + "\n" + e.getMessage());
		}
	}

	public boolean isElementPresent(WebElement ele) throws IOException {
		boolean check = false;
		try {

			if (ele.isDisplayed()) {
				check = true;
				ex.logTestSteps(Status.PASS, "Element is present: " + ele.toString().substring(ele.toString().indexOf("->")+1));
			}
		} catch (Exception e) {
			ex.logTestStepWithScreenshot(driver, Status.FAIL,
					"element is not present: " + e.toString() + "\n" + e.getMessage());
		}
		return check;
	}

	public void sendKeys(WebElement ele, String text) {

		ele.sendKeys(text);

	}

	public boolean isElementNotPresent(WebElement ele) throws IOException {
		boolean check = false;

		if (!ele.isDisplayed()) {
			check = true;
			ex.logTestSteps(Status.PASS, "Element is not present: " + ele.toString().substring(ele.toString().indexOf("->")+1));
		} else
			ex.logTestStepWithScreenshot(driver, Status.FAIL, "element is present: ");

		return check;

	}

	public void waitForElementToBeNotVisible(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public void scrollElementIntoView(WebElement element) {
		
		je.executeScript("arguments[0].scrollIntoView();",element);
	}
	
	public void scrollDown() throws IOException {
			je.executeScript("window.scrollBy(0,250)", "");
	}

	public List<WebElement> getListOfElements(By ele) throws IOException {
		List<WebElement> list = new ArrayList<WebElement>();
		try {
			list= driver.findElements(ele);
			
			if(list.size() == 0)
				ex.logTestSteps(Status.FAIL, "No elements were found.");
			else
				ex.logTestSteps(Status.PASS, list.size()+ " elements were found..");
		}catch (Exception e) {
			ex.logTestStepWithScreenshot(driver, Status.FAIL, "unable to find the list of elements due to: "+e.toString() +"/n"+ e.getMessage());
		}
		return list;
	}
}
