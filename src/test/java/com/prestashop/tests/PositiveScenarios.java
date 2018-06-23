package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {
	{
		WebDriverManager.chromedriver().setup();
	}

	WebDriver driver = new ChromeDriver();

	@BeforeClass
	public void setUpClass() {
	}

	@BeforeMethod
	public void setUpMethod() {

		driver.get("http://automationpractice.com");
	}

	@Test
	public void loginTest() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("testTester@gmail.com");
		driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Enter registration information
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("testBest");
		Select select = new Select(driver.findElement(By.id("days")));
		select.selectByIndex(1);
		select = new Select(driver.findElement(By.id("months")));
		select.selectByIndex(1);
		select = new Select(driver.findElement(By.id("years")));
		select.selectByIndex(1);

		driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Best Test Inc.");
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("1234 N Main St.");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Anytown");
		select = new Select(driver.findElement(By.id("id_state")));
		select.selectByIndex(1);
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("11223");
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("(123)456 7889");
		driver.findElement(By.xpath("//input[@id='alias']")).sendKeys("BestTest");

		driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a[@title='Log me out']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("testTester@gmail.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("testBest");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

		
		String actual = driver.findElement(By.xpath("//div[@class='header_user_info']/a/span")).getText();
		Assert.assertTrue(actual.contains("Test Tester"));
	}

	@AfterClass
	public void teardownClass() {
		 driver.close();
	}

}
