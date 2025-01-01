package com.pia.user_testscript;

import org.testng.annotations.Test;

import com.pia.commonpage.CommonPage;
import com.pia.users.AddNewUserPage;
import com.pia.utiltiy.BaseClass;

public class ScriptUsersNewAccount extends BaseClass {



	/*  Project Name
	 *  
	 *   --  On port installation accessories  ---
	 *  
	 *  @  Author  Raju Yadav @  */

	@Test(priority = 1)
	public void verifyTC001_TitleOf_AddNewUserPage() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage userNewPage=	 new AddNewUserPage();
		userNewPage.verifyTitleOfAdd_new_user_Page("TC001");

	}


	/* This method will  verify password policy massage from add new user Account page   */
	@Test(priority = 2)
	public void verifyTC002_PasswordPolicyMassage() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage userNewPage= new AddNewUserPage();
		userNewPage.verify_PasswordConditionsMassage("TC002");


	}
	/*This method will verify user new  account   */
	@Test(priority = 3)
	public void VerifyTC003_UserNewAccount() throws InterruptedException {

		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.createUserNewAccount("TC00T");


	}

	/*This method  will verify user account  text Box names  from add user new account page  */
	@Test(priority=4)
	public void verifyTC004_AccountTextBoxNames() throws InterruptedException {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage newUserPage=	 new AddNewUserPage();
		newUserPage.verifyAccountTextBoxNames("TC004");
	}

	/* This method will verify save button visible and enable of add new user account page */

	@Test(priority = 5)
	public void verifyTC005_UserAccountSaveButton() throws InterruptedException {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage userNewPage=	 new AddNewUserPage();
		userNewPage.verifyUserAccountSaveButton("TC005");
	}

	/*This method  verify account brand  from add new user account page  */
	@Test(priority = 6)
	public void verifyTC006_BrandOptionsOfAddNewUserPage() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage userNewPage=	 new AddNewUserPage();
		userNewPage.verifyBrandOptions("TC006");

	}

	/*This method will verify title of add new user page  */
	@Test(priority = 7)
	public void verifyTC007_BlankUserNameTextBox() throws InterruptedException  {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.blanckUserNameTextBox("TC007");

	}

	/*This method  will verify blank text box email message for add  new user account page   */
	@Test(priority = 8)
	public void verifyTC008_BlankEmailTextBoxFieldMessage() throws InterruptedException  {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.blanckUserEmailTextBox("TC008");


	}

	/*This method will verify blank text box password message for add  new user account page */
	@Test(priority = 9)
	public void verifyTC009_BlankPasswordTextBoxFieldMessage() throws InterruptedException {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.blanckUserPasswordTextBox("TC009");
	}

	/*This method will verify blank text box Confirm password message for add  new user account page*/
	@Test(priority = 10)
	public void verifyTC010_BlankConfirmPasswordTextBoxFieldMessage() throws InterruptedException {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.blanckComfirmPassowrdTextBox("TC010");
	}

   /**/
    @Test(priority=11)
	public void verifyTC011_WithoutSelectBrandMessage() { 
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage(); 
		AddNewUserPage userPage=new AddNewUserPage();
		userPage.withoutSelectBrand("TC011"); 
		
		
		}

    /**/
	@Test(priority=12)
	public void verifyTC012_WithoutSelectRole() { 
		        CommonPage common= new
				CommonPage(); common.clickOnMenuBT(); common.nevigat_Add_New_UserPage();
				AddNewUserPage userPage=new AddNewUserPage();
				userPage.withoutSelectRole("TC012");

	}


	/*This method will  verify title then user will click cancel button  */
	@Test(priority = 13)
	public void verifyTC013_UserCancelUserAccount() throws InterruptedException {
		CommonPage common=	  new CommonPage();
		common.clickOnMenuBT();
		common.nevigat_Add_New_UserPage();
		AddNewUserPage  userPage=new AddNewUserPage();	
		userPage.clickOnCancelBT("TC013");	

	}












}
