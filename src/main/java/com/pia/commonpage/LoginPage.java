package com.pia.commonpage;

import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.pia.utiltiy.WebUtil;

public class LoginPage {

	private WebUtil util;
	private Properties prop;


	public LoginPage(WebUtil ut) {

		PageFactory.initElements(ut.getDriver(), this);

		this.util=ut;

	}

	@FindBy(xpath = "//input[@id='inputEmail']")
	private   WebElement userNameTB;
	@FindBy(xpath = "//input[@id='inputPassword']")
	private   WebElement passowrdTB;
	@FindBy(xpath = "//button[@id='login-btn']")
	private   WebElement loginBT;
	@FindBy(xpath = "//li[@class='current']")
	private   WebElement homePageTitle;
	@FindBy(xpath = "//li[@class='user_profile icon_wrapper']")
	private WebElement adminBT;
	@FindBy(xpath = "//li[@class='logout']//a")
	private WebElement logOutBT;
	@FindBy(xpath = "//div[text()='Invalid Username/Password.']")
	private WebElement erorrMessage;


	//===========Functions ==========//





	public void verifyVaildUser() throws InterruptedException {
		prop= util.getPropertiesObj();
		Thread.sleep(2000);
		util.sendValue(userNameTB, prop.getProperty("userName"));
		util.sendValue(passowrdTB, prop.getProperty("Password"));
		util.click(loginBT);
		Thread.sleep(2000);
	}
	
	

	public void logOut() throws InterruptedException {

		Thread.sleep(2000);
		util.elementToBeClickable(adminBT, 60);
		util.click(adminBT);
		Thread.sleep(3000);
		util.click(logOutBT);
		
		System.out.println("user logout this application Successfully ");

	}

}
