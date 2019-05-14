package com.testing;

import org.testng.annotations.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
//Open google.com
//Check that the google logo is visible
//Check there are two options present: "Google Search" and "I'm Feeling Lucky"
//Enter text "PayPal" and click "I'm Feeling Lucky"
//Check the url is now "https://www.paypal.com/"
//Click "Sitemap"
//Check the url is now "https://www.paypal.com/us/webapps/mpp/full-sitemap"
//Store all of the links on this page into a list and then print them all to system.out

public class Scenario {
	WebDriver driver;
	String currentURL;

	@Test
	public void test() throws Exception {
		// Check that the google logo is visible

		if (driver.findElement(By.id("hplogo")).isDisplayed()) {
			System.out.println("Google logo is displayed...");

		} else {
			System.out.println("Google logo is NOT displayed...");
		}

		// Check there are two options present: "Google Search" and "I'm Feeling
		// Lucky"
		WebElement iamFeelingLuccky = driver.findElement(By.name("btnI"));
		if (driver.findElement(By.name("btnK")).isDisplayed() && iamFeelingLuccky.isDisplayed()) {
			System.out.println("GoogleSearch & I am Feeling Lucck buttons are  displayed...");
			// ptinr the button text
			System.out.println(driver.findElement(By.name("btnK")).getAttribute("aria-label"));
			System.out.println(iamFeelingLuccky.getAttribute("aria-label"));

		} else {
			System.out.println("GoogleSearch & I am Feeling Lucck buttons are NOT displayed...");
		}
		// Enter text "PayPal" and click "I'm Feeling Lucky"
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys("PayPal");
		Thread.sleep(3000);
		iamFeelingLuccky.click();
		Thread.sleep(3000);

		// Check the url is now "https://www.paypal.com/"
		currentURL = driver.getCurrentUrl();// https://www.paypal.com/us/home
		if (currentURL.contains("paypal.com")) {
			System.out.println("current URL is paypal.com");
		} else {
			System.out.println("paypal.com is nopt displayed, please check");
		}
		Thread.sleep(3000);
		// Click "Sitemap"
		driver.findElement(By.xpath("//*[text()='Sitemap']")).click();
		Thread.sleep(3000);

		// Check the url is now
		// "https://www.paypal.com/us/webapps/mpp/full-sitemap"
		currentURL = driver.getCurrentUrl();
		if (currentURL.contains("sitemap")) {
			System.out.println("current URL is sitemap URL");
		} else {
			System.out.println("paypal.com is nopt displayed, please check");
		}
		Thread.sleep(3000);

		// Store all of the links on this page into a list and then print them
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		System.out.println("All links found on web page are: " + allLinks.size() + " links");

		for (WebElement link : allLinks) {

			// print the links
			System.out.println(link.getAttribute("href"));

			// print the links text
			System.out.println(link.getText());
		}

	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		String baseURL = "https://www.google.com/";
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
