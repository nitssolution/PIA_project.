package com.pia.user_testscript;

import org.testng.annotations.Test;

import com.pia.commonpage.CommonPage;
import com.pia.users.AddNewUserPage;
import com.pia.users.ManageUserPage;
import com.pia.utiltiy.BaseClass;

public class ScriptManageUsers extends BaseClass {

	/*This method will verify user Edit  account  */

	@Test(priority = 1)
	public void verifyTC014_UserAccountEdit() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		ManageUserPage userManage=   new ManageUserPage();
		Thread.sleep(3000);
		userManage.userEditAccount("TC014");



	}

	/*This method will verify user Delete account   */
//@Test(priority = 2)
	public void verfiyTC015_UserDeleteAccount() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		ManageUserPage userManage=   new ManageUserPage();
		userManage.userAccountDelete("TC015");

	}

	/* This method will  verify  then user edit account cancel  come back ho*/
	@Test(priority = 3)
	public void verifyTC016_UserCancelEditAccount() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		ManageUserPage userManage=   new ManageUserPage();
		userManage.userCancelEditAcoount("TC016");


	}
	/*This method will verify  Filter Text  On users page  for Manage user module  */
	@Test(priority = 4)
	public void verifyTC017_FilterText() {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.getTextOfFilterButton("TC017");
	}

	/*This method will verify  Filter Button  On users page  for Manage user module  */
	@Test(priority = 5)

	public void verifyTC018_FliterButton_IsEnable_OnUI() {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.getStatusOfFilterButton("TC018");


	}
	/*This method  will verify Information Icon Status in manage user on users page  */
	@Test(priority = 6)
	public void verifyTC019_Information_Icon_IsDispaly_UI_Page() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.information_Icon_IsDisplay("TC019");
	}
	/*This method  will verify Information Icon Suggest  in manage user on users page  */
	@Test(priority = 7)
	public void  verifyTC020_Information_Icon_Suggestion() {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.getInfomation_Icon_Suggestion("TC020");


	}
	
	
	@Test(priority = 8)
	public void verifyTc021_ApplyButton() {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.clickOnFilterBT();
		AddNewUserPage newUserPage=	  new AddNewUserPage();
		newUserPage.applyButtonIsEnable();


	}
	@Test(priority = 9)
	public void verifyTC022_FilterTextBoxNames() {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.clickOnFilterBT();
		common.verfiyFilterTextBoxNames("TC022");

	}
	@Test(priority = 10)
	public void verifyTC023_FilterBrandOption() throws InterruptedException {
		CommonPage common= new CommonPage();
		common.clickOnMenuBT();
		common.nevigatManageUsersPage();
		common.clickOnFilterBT();
		ManageUserPage manage=  new ManageUserPage();
		manage.clickOnFilterBrand();
		manage.filterBrandOption("TC023");
		

	}
	@Test(priority = 11)
public void verifyTC024_Job_Role_Options() throws InterruptedException {
	CommonPage common= new CommonPage();
	common.clickOnMenuBT();
	common.nevigatManageUsersPage();
	common.clickOnFilterBT();
	ManageUserPage manageUser=  new ManageUserPage();
	manageUser.getjob_Role_Option("TC024");
}
	
	
}
