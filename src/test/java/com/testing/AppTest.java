package com.testing;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.driver.Driver;
import com.pageobjects.HomePage;
import com.pageobjects.MenuPage;
import com.reporting.ExtentReporting;
import com.support.Browsers;
import com.utils.JsonUtils;
import com.utils.LoggersUtils;
import com.utils.SeleniumUtils;

/**
 * Unit test for simple App.
 */
public class AppTest {

	WebDriver driver;
	ExtentReporting ex;
	JsonUtils jsonData;
	SeleniumUtils sel;
	Driver d;
	
	HomePage hp;
	MenuPage mp;

	@BeforeClass
	public void beforeClass() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-ss");
		    System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	
	@BeforeTest
	public void beforeTest() throws ClassNotFoundException {
		jsonData = new JsonUtils("AppTest");
		d = new Driver();
		driver = d.getDriver();
		LoggersUtils.getInstance(AppTest.class);
		ex = new ExtentReporting("AppTest");
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = d.getWebdriver(jsonData.getConfigData("browser"));
		LoggersUtils.getLog().info("Test input");
		sel = new SeleniumUtils(driver, ex);
		// Page Objects
		hp = new HomePage(driver, sel, ex);
		mp = new MenuPage(driver, sel, ex);

		driver.manage().window().maximize();
		driver.get(jsonData.getConfigData("url"));
	}

	@Test
	public void TC_001() throws Exception {
		JSONObject data = jsonData.getTestDataJsonObject();
		ex.createTestCase("ClubKitchen TC001");
		
		ex.logTestSteps(Status.INFO, "Sucessfully opened the website");
		hp.navigateToMenusPage();

		String streetAddress = (String) data.get("streetAddress");
		mp.verifyMenuPage();
		mp.enterStreetAddress(streetAddress);
		String menuName = (String) data.get("menu");
		mp.selectMenu(menuName);
		String items = (String) data.get("items");
		ArrayList<String> extras =  (ArrayList<String>) data.get("ExtrasList");
		mp.selectMenuItems(items, extras);
		Assert.assertTrue(true);
	}

	@Test
	public void TC_002() throws IOException {
		ex.createTestCase("ClubKitchen TC002");
		ex.logTestSteps(Status.PASS, "Sucessfully opened 2nd website");
		hp.clickOnLogin();
		Assert.assertTrue(true);
		ex.logTestStepWithScreenshot(driver, Status.WARNING, "Test Excersie");
	}

	@AfterMethod
	public void endDriver(ITestResult res, Method m) throws IOException {
		
		if(ITestResult.FAILURE == res.getStatus())
			ex.logTestStepWithScreenshot(driver, Status.FAIL, m.getName()+" case failed due to:\n"+ res.getThrowable());
		else if(ITestResult.SKIP == res.getStatus())
			ex.logTestStepWithScreenshot(driver, Status.SKIP, m.getName()+" case Skipped due to: \n"+ res.getThrowable());
		ex.closeTest();
		driver.close();
	}

	@AfterTest
	public void afterTest() throws IOException {
		
		if(driver!= null)
			driver.quit();
	}
}
