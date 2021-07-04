package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import com.support.Browsers;

import io.github.bonigarcia.wdm.WebDriverManager;

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
		try {
		switch (browser) {
		case Chrome:
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver());
			break;
		case FireFox:
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
		default:
			break;

		}
		}
		catch (Exception e) {
			System.out.println("...........Driver initilization failed......");
			System.out.print(e.toString());
		}
		return getDriver();
	}

}
