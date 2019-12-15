package com.app.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import com.app.utilities.TestBase;

public class GitLinks extends TestBase{
	
	@Test
	public void test() {
		driver.get("https://www.github.com/");
		System.out.println(BrowserUtils.getElementsText(By.tagName("a")));
//		driver.get("https://www.uascs.org/");
//		System.out.println(BrowserUtils.getElementsText(By.tagName("a")));
	}
	

}
