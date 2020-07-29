package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import com.support.Browsers;

public class Driver {

	WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Driver() {
	}

	public WebDriver getWebdriver(Browsers browser) {
		switch (browser) {
		case Chrome:
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "./src/test/resources/drivers/chromedriver.exe");
			setDriver(new ChromeDriver());
			break;
		case FireFox:
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "./src/test/resources/drivers/geckodriver.exe");
			setDriver(new FirefoxDriver());
			break;
		default:
			break;

		}

		return getDriver();
	}

}
