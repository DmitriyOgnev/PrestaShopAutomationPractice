package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {
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
	public void wrongCredentialsTest() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("wrongEmail@yahoo.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

		String actual = driver.findElement(By.xpath("//div/ol/li")).getText();
		Assert.assertTrue(actual.contains("Authentication failed"));
	}

	@Test
	public void invalidEmailTest() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("invalidEmailATyahoo.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

		String actual = driver.findElement(By.xpath("//div/ol/li")).getText();
		Assert.assertTrue(actual.contains("Invalid email address"));
	}

	@Test
	public void blankEmailTest() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

		String actual = driver.findElement(By.xpath("//div/ol/li")).getText();
		Assert.assertTrue(actual.contains("An email address required"));
	}
	
	@Test
	public void blankPasswordTest() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("validEmail@yahoo.com");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

		String actual = driver.findElement(By.xpath("//div/ol/li")).getText();
		Assert.assertTrue(actual.contains("Password is required"));
	}
	
	
	@AfterClass
	public void teardownClass() {
		driver.close();
	}

}
