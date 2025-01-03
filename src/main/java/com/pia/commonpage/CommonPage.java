package com.pia.commonpage;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.pia.utiltiy.DataUtil;
import com.pia.utiltiy.WebUtil;

public class CommonPage {


	private WebUtil util=null;
	private	DataUtil datas;

	public  CommonPage() {
		util=WebUtil.getObject();
		PageFactory.initElements(util.getDriver(), this);

		datas= new DataUtil();

	}

	//==========Variables===============//

	@FindBy(xpath = "//i[@data-title='Menu']")
	private WebElement menuBT;

	@FindBy(xpath = "//span[text()='Add New User']")
	private WebElement add_New_UserLink;

	@FindBy(xpath = "//span[text()='Manage Users']")
	private WebElement manageUsersLink;

	@FindBy(xpath = "//div[@role='alert']")
	protected WebElement validetionAccountMessage;

	@FindBy(xpath = "//button[@id='cancel-btn']")
	private WebElement cancelBT;

	@FindBy(xpath = "//form[@id='modify_user']//label")
	private List<WebElement> textBoxNames;

	@FindBy(xpath = "//b[@class='menu_filter']")
	protected WebElement filterText;

	@FindBy(xpath = "//i[@class='fa fa-filter']")
	protected WebElement filterBT;

	@FindBy(xpath = "//i[@id='get_info']")
	protected WebElement information_Icon;

	@FindBy(xpath = "//div[@class='tooltip_container']")
	protected WebElement information_Suggestion;

	@FindBy(xpath = "//button[@id='imp-btn']")
	protected WebElement applyBT;

	@FindBy(xpath = "//i[@title='Close']")
	protected WebElement crossBT;


	@FindBy(xpath = "//form[@id='user_filter']//label")
	protected List<WebElement> filterTBName;

	@FindBy(xpath = "//button[@id='cancel-btn']")
	protected WebElement resetBT;
	
	@FindBy(xpath = "//input[@name='userName']")
	protected WebElement userNameFilterTB;



	//==========functions============//





	public void clickOnMenuBT() {
		util.visibilityOfElement(menuBT, 100);
		util.click(menuBT);
	}

	public void nevigat_Add_New_UserPage () {
		util.visibilityOfElement(add_New_UserLink, 60);

		util.click(add_New_UserLink);


	}
	public void nevigatManageUsersPage() {
		util.visibilityOfElement(manageUsersLink, 100);

		util.click(manageUsersLink);

	}

	public String  getValidetionMessage() {
		String message=util.getUIText(validetionAccountMessage);

		return message;
	}

	public void clickCancelButton() {
		util.visibilityOfElement(cancelBT, 50);
		util.click(cancelBT);
	}
	public void clickOnFilterBT() {
		util.click(filterBT);
	}


	public void withoutSelectBrandAndRole(String vaule) {
		WebElement we= util.getDriver().findElement(By.xpath("(//span[text()='"+vaule+"'])[2]"));
		util.  mouseOver(we);
		util. mouseClick(we);

	}

	public void select_Brand(String value) {
		try {
			WebElement we=	util.getDriver().findElement(By.xpath("//ul[@id='ModUserBrand-list-ul']//span[text()='"+value+"']"));
			util. mouseClick(we);
			util.getExtentTest().log(Status.INFO, "Select "+value+" Brand Successfully");
		}catch(Exception e) {
			e.getMessage();

		}

	}
	public void select_Role(String value)  {
		WebElement we=	util.getDriver().findElement(By.xpath("//ul[@id='ModUserRole-list-ul']//li//span[contains(text(),'"+value+"')]"));
		util.  elementToBeClickable(we, 60);
		util. mouseOver(we);
		util.  mouseClick(we);

	}

	public void clickDeleteUserAccountByname(String userName) {
		WebElement we=null;

		try {
			we=	util.getDriver().findElement(By.xpath("//table[@class='report_table']//td[contains(text(),'"+userName+"')]/..//span[@data-title='Delete']"));
			util. elementToBeClickable(we, 30);
			util.mouseClick(we);
			util.getExtentTest().log(Status.INFO, userName+"Click On Delete Button for this user Successfully ");

		}catch(NoSuchElementException e) {
			we=	util.getDriver().findElement(By.xpath("//table[@class='report_table']//td[contains(text(),'"+userName+"')]/..//span[@data-title='Delete']"));
			util.jsClick(we);
			util.getExtentTest().log(Status.INFO, userName+"Click On Delete Button for this user Successfully ");


		}catch(Exception e) {
			e.getMessage();
		}


	}
	public void editUserAccountByname(String userName) {
		WebElement we=null;

		try {
			Thread.sleep(3000);
			we=   util.getDriver().findElement(By.xpath("//table[@class='report_table']//td[contains(text(),'"+userName+"')]/..//span[@data-title='Edit']"));
			util. mouseOver(we);
			util. mouseClick(we);

			util.getExtentTest().log(Status.INFO,"Clicked  Edit Button  for "+ userName+" User  Account  Successfully ");



		}catch(NoSuchElementException e) {
			we=   util.getDriver().findElement(By.xpath("//table[@class='report_table']//td[contains(text(),'"+userName+"')]/..//span[@data-title='Edit']"));
			util.jsClick(we);
			util.getExtentTest().log(Status.INFO,"Clicked  Edit Button  for "+ userName+" User  Account  Successfully ");


		}catch(Exception e) {
			util.getExtentTest().log(Status.FAIL,"it is not Clicked  On Edit Button for "+userName+" account user   ");
			e.getMessage();
		}
	}
	public void getTextOfFilterButton(String tcID) {
		Map<String, String> mapDatas=  datas.getTestCaseData(tcID);

		String actuleTextOfFilter=  util.getUIText(filterText);
		Assert.assertEquals(actuleTextOfFilter,mapDatas.get("FilterButton"));
		util.getExtentTest().log(Status.PASS,"Matched  Fliter Text expected -> Filter  Actule "+actuleTextOfFilter);
	}

	public void getStatusOfFilterButton(String tcID) {
		//Map<String, String> mapDatas=   datas.getTestCaseData(tcID);
		boolean status=	util.getElementIsEnable(filterBT);

		if(status==true) {

			util.getExtentTest().log(Status.PASS,"Filter Button Is Enabled Expected Value -> true  | Actule Status -> "+status);	

		}else {
			util.getExtentTest().log(Status.FAIL,"Filter Button Is not  Enabled Expected Value -> true  | Actule Status -> "+status);	

		}
	}
	public void information_Icon_IsDisplay(String tcID) throws InterruptedException {
		Thread.sleep(5000);
		boolean status=util.getElementIsDisplay(information_Icon);

		if(status==true) {
			util.getExtentTest().log(Status.PASS," Information_Icon  Is Displayed on  UI Expected Status -> True | Actule Status ->  "+status);	
		}

	}
	public void getInfomation_Icon_Suggestion(String tcID) {
		Map<String, String> mapDatas=   datas.getTestCaseData(tcID);

		util.mouseOver(information_Icon);
		util.visibilityOfElement(information_Suggestion, 60);
		String textOfInformationIcon= util.getUIText(information_Suggestion);

		Assert.assertEquals(textOfInformationIcon, mapDatas.get("Suggestion"));
		util.getExtentTest().log(Status.PASS," Information_Icon  Suggestion on  UI Expected Suggestion -> Information | Actule Suggestion ->  "+textOfInformationIcon);	


	}
	public void applyButtonIsEnable() {

		boolean actuleStatus=util.getElementIsEnable(applyBT);
		if(actuleStatus==true) {
			util.getExtentTest().log(Status.PASS," Apply button is Enabled  on  UI Expected  -> true  | Actule Suggestion ->  "+actuleStatus);	

		}else {
			util.getExtentTest().log(Status.FAIL," Apply button is Not  Enabled  on  UI Expected  -> true  | Actule Suggestion ->  "+actuleStatus);	

		}
		clickOnCrossButton();

	}

	public void clickOnCrossButton() {
		util.mouseOver(crossBT);
		util.elementToBeClickable(crossBT, 60);
		util.mouseClick(crossBT);
		util.getExtentTest().log(Status.INFO,"Click On Cross Button of Filter  successfull ");	

	}
	public void verfiyFilterTextBoxNames(String tcID) {
		Map<String, String> mapDatas= datas.getTestCaseData(tcID);

		List<String>  listName=	util.getTextList(filterTBName);

		Assert.assertEquals(listName.get(0), mapDatas.get("Name"));
		Assert.assertEquals(listName.get(1), mapDatas.get("Brand"));
		Assert.assertEquals(listName.get(2), mapDatas.get("Role"));
		util.getExtentTest().log(Status.INFO," All Filter Text Box Name maich  Successfull ");	

		clickOnCrossButton();

	}
	public void resetButtonStatus(String tcID) {
	     boolean status=util.getElementIsEnable(resetBT);
	 	 if(status==true) {
	 		util.getExtentTest().log(Status.PASS,"Reset Button Expected Status  True and Actule -> "+status);	
 	 
	 	 }else {
		 	util.getExtentTest().log(Status.FAIL,"Reset Button Expected Status  True and Actule -> "+status);	
 
	 	 }
		
		clickOnCrossButton();

	}
	public void crossButtonStatus() {
	boolean actuleStatus=util.getElementIsDisplay(crossBT);
	      if(actuleStatus==true) {
			 	util.getExtentTest().log(Status.PASS,"Cross Button  Expected Status  True and Actule -> "+actuleStatus);	
  
	    	  
	      }else {
			 	util.getExtentTest().log(Status.FAIL,"Cross Button  Expected Status  True and Actule -> "+actuleStatus);	

	      }
	
	      clickOnCrossButton();
	
	}

}
