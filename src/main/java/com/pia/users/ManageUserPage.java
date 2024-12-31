package com.pia.users;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.checkerframework.checker.fenum.qual.AwtAlphaCompositingRule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.pia.commonpage.CommonPage;
import com.pia.utiltiy.DataUtil;
import com.pia.utiltiy.WebUtil;

public class ManageUserPage extends CommonPage {

	private	WebUtil util;
	private	DataUtil data=null;


	public ManageUserPage() {

		util=  WebUtil.getObject();

		PageFactory.initElements(util.getDriver(), this);
		data=  new DataUtil();

	}


	//=========== variable===============//
	@FindBy(xpath = "//button[@id='yes-btn']")
	private WebElement deleteUserPop_up;

	@FindBy(xpath = "//div[@role='alert']")
	private WebElement AccountDeleteMessage;

	@FindBy(xpath = "//input[@name='ModUserEmail']")	
	private WebElement emailTB;

	@FindBy(xpath = "//input[@name='ModUserPwd']")	
	private WebElement passowrdTB;

	@FindBy(xpath = "//input[@name='ModUserConfPwd']")	
	private WebElement comfirmPassowrdTB;


	@FindBy(xpath = "//div[@id='ModUserBrand-select-list']")
	private WebElement AssociatedBrand;

	@FindBy(xpath = "//div[@id='ModUserBrand-select-list']//li")
	private List<WebElement>AssociatedBrandOptions;


	@FindBy(xpath = "//div[@id='ModUserRole-sel-text']")
	private WebElement role;

	@FindBy(xpath = "//button[@id='imp-btn']")
	private WebElement saveBT;

	@FindBy(xpath = "//div[@id='userBrand-sel-text']")
	private WebElement filterBrand;

	@FindBy(xpath = "//ul[@id='userBrand-list-ul']//li")
	private List<WebElement>  filterBrandOption;

	@FindBy(xpath = "//ul[@id='userJobTitle-list-ul']//li")
	private List<WebElement>  filter_Job_Role_Option;

	@FindBy(xpath = "//div[@id='userJobTitle-sel-text']")
	private WebElement filterJob_RoleField;





	/*---------functions---------------*/



	/*   This method will edit user account  */
	public void userEditAccount(String tcID) throws InterruptedException {

		Map<String, String> mapData=  data.getTestCaseData(tcID);
		editUserAccountByname(mapData.get("UserName"));
		util.visibilityOfElement(emailTB, 60);
		util.click(emailTB);
		util.clearTextBoxValue(emailTB);
		util.sendValue(emailTB,mapData.get("Email") );
		util.sendValue(passowrdTB, mapData.get("Passowrd"));
		util.click(comfirmPassowrdTB);
		util.sendValue(comfirmPassowrdTB, mapData.get("ConfirmPassowrd"));

		util.click(AssociatedBrand);
		select_Brand(mapData.get("Brand"));
		util.click(role);
		select_Role(mapData.get("Role"));

		util.click(saveBT);
		util.visibilityOfElement(validetionAccountMessage, 60);
		String actualMessage=  getValidetionMessage();
		Assert.assertEquals(actualMessage, mapData.get("Message"));
		util.getExtentTest().log(Status.PASS, "User Account Edit Is Successfully ");

		Thread.sleep(5000);
	}



	/*This method will delete user account  by  user  name  and verify delete message  also         */
	public void userAccountDelete(String tcID) throws InterruptedException {
		Map<String, String> mapData=  data.getTestCaseData(tcID);
		clickDeleteUserAccountByname(mapData.get("UserName"));
		util.visibilityOfElement(deleteUserPop_up, 30);
		util.click(deleteUserPop_up);

		String actuleDeleteMessage=util.getUIText(AccountDeleteMessage);
		System.out.println("account delete |"+actuleDeleteMessage);
		String expectedDleteMessage=    mapData.get("DeleteMessage");
		Assert.assertEquals(actuleDeleteMessage, expectedDleteMessage,"user Account is  not deleted");
		Thread.sleep(5000);

	}

	public void userCancelEditAcoount(String tcID) throws InterruptedException {
		Map<String, String> mapData=  data.getTestCaseData(tcID);

		editUserAccountByname(mapData.get("UserName"));
		util.visibilityOfElement(emailTB, 100);
		util.click(emailTB);
		util.clearTextBoxValue(emailTB);
		util.sendValue(emailTB,mapData.get("Email") );
		util.sendValue(passowrdTB, mapData.get("Passowrd"));
		util.sendValue(comfirmPassowrdTB, mapData.get("conformPassowrd"));

		util.click(AssociatedBrand);
		select_Brand(mapData.get("Brand"));
		util.click(role);

		select_Role("CORP Approver");
		clickCancelButton();
		String actuleTitle=   util.currentPageTitle();

		Assert.assertEquals(actuleTitle, mapData.get("Title"));
		util.getExtentTest().log(Status.PASS, "User Account Edit Is Successfully ");
		Thread.sleep(3000);

	}

	public void clickOnFilterBrand() {
		util.click(filterBrand);	
	}
	public void filterBrandOption(String tcID) throws InterruptedException {
		Map<String, String> mapData=  data.getTestCaseData(tcID);

		List<String> listwe=  util.getTextList(filterBrandOption);

		Assert.assertEquals(listwe.get(1), mapData.get("Audi"));
		Assert.assertEquals(listwe.get(2), mapData.get("Volkswagen"));
		Assert.assertEquals(listwe.get(3), mapData.get("AUVW"));

		util.visibilityOfElement(crossBT, 50);
		util.click(crossBT);
		util.getExtentTest().log(Status.PASS, "Filter Brand Option Is Matched  Successfully ");
		
		Thread.sleep(5000);


	}
	public void getjob_Role_Option(String tcID) throws InterruptedException {
		
		Map<String, String> mapData=  data.getTestCaseData(tcID);
		
		util.visibilityOfElement(filterJob_RoleField, 60);
		util.click(filterJob_RoleField);
		List<String> list= util.getTextList(filter_Job_Role_Option);  

		

		//Super Admin, Admin, CORP Approver, CORP Option Approver, CORP Employee Approver, Corporate User, QA Approver, QA Option Approver, QA Employee Approver, QA User, Port Operations Manager, Port User, Dealer User
		
		//Assert.assertEquals(list.get(0), mapData.get("- Select -"));
		Assert.assertEquals(list.get(1), mapData.get("Super"));
		Assert.assertEquals(list.get(2), mapData.get("Admin"));
		Assert.assertEquals(list.get(3), mapData.get("CORPApprover"));
		Assert.assertEquals(list.get(4), mapData.get("CORP Option"));
		Assert.assertEquals(list.get(5), mapData.get("CORP Employee"));
		Assert.assertEquals(list.get(6), mapData.get("Corporate User"));
		Assert.assertEquals(list.get(7), mapData.get("QA Approver"));
		Assert.assertEquals(list.get(8), mapData.get("QA Option Approver"));
		Assert.assertEquals(list.get(9), mapData.get("QA Employee Approver"));
		Assert.assertEquals(list.get(10), mapData.get("QA User"));
		Assert.assertEquals(list.get(11), mapData.get("Port Operations Manager"));
		Assert.assertEquals(list.get(12), mapData.get("Port User"));
		Assert.assertEquals(list.get(13), mapData.get("Dealer User"));

		clickOnCrossButton();
		Thread.sleep(5000);


	}

}
