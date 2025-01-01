package com.pia.users;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.pia.commonpage.CommonPage;
import com.pia.utiltiy.DataUtil;
import com.pia.utiltiy.WebUtil;


public class AddNewUserPage extends CommonPage {
	private WebUtil util;     
	DataUtil datas=null;


	/* This is constructor  there it get webUtil object  and  also use pageFactry  and get dataUtil class object   */
	public AddNewUserPage() {
		util=WebUtil.getObject();

		PageFactory.initElements(util.getDriver(), this);

		datas=  new DataUtil();	


	}


	//https://nitssolutions1-my.sharepoint.com/personal/raju_yadav_nitssolutions_com/Documents/DataOfPIA.xlsx?web=1
	//================Variables==================//
	@FindBy(xpath ="//input[@name='ModUserName']")
	private WebElement userNameTB;

	@FindBy(xpath = "//input[@name='ModUserEmail']")	
	private WebElement emailTB;

	@FindBy(xpath = "//input[@name='ModUserPwd']")	
	private WebElement passowrdTB;

	@FindBy(xpath = "//input[@name='ModUserConfPwd']")	
	private WebElement comfirmPassowrdTB;


	@FindBy(xpath = "//div[@id='ModUserBrand-select-list']")
	private WebElement AssociatedBrandDropDun;

	@FindBy(xpath = "//div[@id='ModUserBrand-select-list']//li")
	private List<WebElement>AssociatedBrandOptions;


	@FindBy(xpath = "//div[@id='ModUserRole-sel-text']")
	private WebElement roleDropDun;

	@FindBy(xpath  = "//div[@role='alert']")
	private WebElement new_AccountCreationMessage; 


	@FindBy(xpath = "//button[@id='imp-btn']")
	private WebElement saveBT;


	@FindBy(xpath = "//div[@class='passwordPolict']")
	private WebElement passowrdPolicy;


	@FindBy(xpath = "//div[@class='policy fa fa-question-circle']")
	private WebElement question_Circle;

	@FindBy(xpath = "//form[@id='modify_user']//label")
	private List<WebElement> textBoxNames;



	@FindBy(xpath = "//div[@class='report_header']")
	private WebElement userAccountPageTitle;






	//=============Functions=============//
	
	
	/*

   This method will create new user account  by unique name and email 
   and also verify massage    */

	public void createUserNewAccount(String tcId ) throws InterruptedException  {
		Thread.sleep(2000);
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.click(userNameTB);
		util.sendValue(userNameTB, mapDatas.get("User Name"));
		util.click(emailTB);

		util.sendValue(emailTB, mapDatas.get("Email"));
		util.click(passowrdTB);

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));
		util.click(comfirmPassowrdTB);

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.elementToBeClickable(AssociatedBrandDropDun, 60);
		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));
		
		util.elementToBeClickable(roleDropDun, 60);
		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));
		util.elementToBeClickable(saveBT, 60);

		util.click(saveBT);

		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage,mapDatas.get("Message") );

		Thread.sleep(5000);	

	}
	   /**/
	public void blanckUserNameTextBox (String tcId ) throws InterruptedException  {
		Thread.sleep(2000);
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.click(emailTB);

		util.sendValue(emailTB, mapDatas.get("Email"));
		util.click(passowrdTB);

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));
		util.click(comfirmPassowrdTB);

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.elementToBeClickable(AssociatedBrandDropDun, 60);
		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));
		util.elementToBeClickable(roleDropDun, 60);
		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));
		util.elementToBeClickable(saveBT, 60);
		util.click(saveBT);

		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage,mapDatas.get("Message") );

		Thread.sleep(5000);


	}
	   /**/
	public void  blanckUserEmailTextBox(String tcId ) throws InterruptedException  {
		Thread.sleep(2000);
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.click(userNameTB);
		util.sendValue(userNameTB, mapDatas.get("UserName"));

		util.click(passowrdTB);

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));
		util.click(comfirmPassowrdTB);

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.elementToBeClickable(AssociatedBrandDropDun, 60);
		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));

		util.elementToBeClickable(roleDropDun, 60);
		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));

		util.elementToBeClickable(saveBT, 60);

		util.click(saveBT);

		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage,mapDatas.get("Message") );

		Thread.sleep(5000);

	}
	   /**/
	public void blanckUserPasswordTextBox(String tcId ) throws InterruptedException  {
		Thread.sleep(2000);
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.click(userNameTB);
		util.sendValue(userNameTB, mapDatas.get("UserName"));
		
		util.click(emailTB);

		util.sendValue(emailTB, mapDatas.get("Email"));

		util.click(comfirmPassowrdTB);

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));


		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));

		util.click(saveBT);

		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage,mapDatas.get("Message") );

		Thread.sleep(5000);

	}

	   /**/

	public void blanckComfirmPassowrdTextBox(String tcId ) throws InterruptedException  {

		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.click(userNameTB);
		util.sendValue(userNameTB, mapDatas.get("UserName"));
		util.click(emailTB);

		util.sendValue(emailTB, mapDatas.get("Email"));
		util.click(passowrdTB);

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));

		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));


		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));

		util.click(saveBT);

		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage,mapDatas.get("Message") );

		Thread.sleep(4000);

	}

	   /**/
	public void withoutSelectRole(String tcId) {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.sendValue(userNameTB, mapDatas.get("UserName"));

		util.sendValue(emailTB, mapDatas.get("Email"));

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.click(AssociatedBrandDropDun);
		select_Brand(mapDatas.get("Brand"));

		util.click(saveBT);
		
		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage, mapDatas.get("Message"));

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void withoutSelectBrand(String tcId) {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcId);

		util.sendValue(userNameTB, mapDatas.get("UserName"));

		util.sendValue(emailTB, mapDatas.get("Email"));

		util.sendValue(passowrdTB, mapDatas.get("Passowrd"));

		util.sendValue(comfirmPassowrdTB, mapDatas.get("ConfirmPassowrd"));

		util.click(AssociatedBrandDropDun);
		String brand="- Select Brand-";
		select_Brand(brand);
		
		util.click(roleDropDun);
		select_Role(mapDatas.get("Role"));

		util.click(saveBT);
//Role Must Have Some Valu
//Role Must Have Some Value		
		
		util.visibilityOfElement(validetionAccountMessage, 60);

		String actuleBlankuserNameMessage= util.getUIText(validetionAccountMessage);

		Assert.assertEquals(actuleBlankuserNameMessage, mapDatas.get("Message"));

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



	}

	/* This method will verify  password conditions massage  from add new user account page   */

	public void verify_PasswordConditionsMassage(String tcID) throws InterruptedException {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcID);

		util.mouseOver(question_Circle);


		String actualMassage=util.getUIText(passowrdPolicy);

		Assert.assertEquals(actualMassage, mapDatas.get("Passowrd policy Massage"));
		util.getExtentTest().log(Status.PASS, "Verify Passowrd conditions Massage Successfully ->"+actualMassage);
		Thread.sleep(3000);	

	}

	/* This method will verify all text box name of account  from add new user account page */

	public void verifyAccountTextBoxNames(String tcID) throws InterruptedException {


		Map<String, String> mapDatas= datas.getTestCaseData(tcID);

		List<String> actuleTextList=util.getTextList(textBoxNames);

		Assert.assertEquals(actuleTextList.get(0), mapDatas.get("Username"));
		Assert.assertEquals(actuleTextList.get(1), mapDatas.get("Email"));
		Assert.assertEquals(actuleTextList.get(2), mapDatas.get("Passowrd"));
		Assert.assertEquals(actuleTextList.get(3), mapDatas.get("ConfirmPassowrd"));
		Assert.assertEquals(actuleTextList.get(4), mapDatas.get("Brand"));
		Assert.assertEquals(actuleTextList.get(5), mapDatas.get("Role"));

		util.getExtentTest().log(Status.PASS, "Verify User Account Text Box Names Successfully  ->");
		Thread.sleep(3000);	

	}

	/*  This method  will get status and text of save button form add new user account page */
	public void verifyUserAccountSaveButton(String tcID) throws InterruptedException {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcID);

		String  textOfSaveButton= util.getUIText(saveBT);
		System.out.println("save button |"+textOfSaveButton.trim());
		if(saveBT.isDisplayed()==true) {
			Assert.assertEquals(saveBT.isDisplayed(), true);
			Thread.sleep(2000);
			Assert.assertEquals(mapDatas.get("Button"), textOfSaveButton.trim(),"Verify save button on  user account page ");

			util.getExtentTest().log(Status.PASS, textOfSaveButton.trim()+" Button is visible On UI  and enabe also  and save button text --> "+mapDatas.get("Button"));

		}

		Thread.sleep(3000);	


	}

	/* This method will  Verify user Brand Options of user add new  account page        */
	public void verifyBrandOptions(String tcID) throws InterruptedException  {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcID);

		util.mouseClick(AssociatedBrandDropDun);
		List<String> actuleTextOfBrands=util.getTextList(AssociatedBrandOptions);

		//Assert.assertEquals(actuleTextOfBrands.get(0), mapDatas.get("selectBrand") );
		Assert.assertEquals(actuleTextOfBrands.get(1), mapDatas.get("Audi"));
		Assert.assertEquals(actuleTextOfBrands.get(2), mapDatas.get("VW"));
		Assert.assertEquals(actuleTextOfBrands.get(3), mapDatas.get("AUVW"));
		util.getExtentTest().log(Status.PASS, "User Account Brand Options Match Successfully ");
		
		Thread.sleep(3000);	

	}

	/*This method will verify title of add new user page  by assert  */

	public void verifyTitleOfAdd_new_user_Page(String tcID) throws InterruptedException {
		
		Map<String, String > mapData=   datas.getTestCaseData(tcID);

		String actuleTitle=	util.getUIText(userAccountPageTitle);

		Assert.assertEquals(actuleTitle, mapData.get("Title")," add new user title is matched  ");
		util.getExtentTest().log(Status.PASS, "verify title of add new user account page expceted title--> "+ mapData.get("Title")+"  actuleTile--> "+actuleTitle+" successfully");
		Thread.sleep(3000);	

	}
	/*This method will click on cancel button of  user account also get title of current page  of  after click      */
	public void clickOnCancelBT(String tcID) throws InterruptedException {
		Map<String, String > mapData=   datas.getTestCaseData(tcID);
		clickCancelButton();
		String actuleTitle=	util.currentPageTitle();
		Assert.assertEquals(actuleTitle,mapData.get("Title") ,"get Current Page Title -->"+actuleTitle);
		util.getExtentTest().log(Status.INFO, "Verify Cancel user  Account  page    ");

	Thread.sleep(3000);	
	}

















}
