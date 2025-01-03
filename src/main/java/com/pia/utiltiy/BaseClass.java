package com.pia.utiltiy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pia.commonpage.LoginPage;

public class BaseClass {

	private ExtentReports extent;
	private Properties prop;
	 protected    WebUtil  util=WebUtil.getObject();
	 private LoginPage login=null; 
	
   @BeforeSuite
	public void beforeSuite() {
	      
       String date = new SimpleDateFormat("-dd-MM-yyyy__hh_mm_ss_a-").format(new Date());
		extent=	new ExtentReports();
		ExtentSparkReporter spark=	   new ExtentSparkReporter(System.getProperty("user.dir")+"//target//Test-Output//ExtentdReport//PIA "+date+".html");
		extent.attachReporter(spark);	 
				
	}
	@BeforeTest
	public void BeforeTest() {
	System.out.println("Data canection");
	   prop=  util.getPropertiesObj();
	
	
	
	}
	@BeforeClass
	public void BeforeClass() throws Exception {
		util.browserLaunch(prop.getProperty("Browser"));
		
	  util.openUrl(prop.getProperty("Url"));
		
	}
	
	@BeforeMethod
	public void BedforeMethod(Method mt) throws InterruptedException {
		System.out.println(mt.getName());
	    ExtentTest extentTest=  extent.createTest(mt.getName());
		 util.setExtentTest(extentTest);
		 login=	new LoginPage(util);
		 login.verifyVaildUser();

	}
	@AfterMethod
	public void afterMothod(ITestResult result,Method mt) throws InterruptedException {
		if(result.getStatus() == ITestResult.FAILURE) {
            // Take snapshot (make sure util.takeSnapshot returns a valid path)
            String snapshotPath = util.takeSnapshot(mt.getName());
            // Add screenshot to Extent report (ensure extentTest is initialized properly)
            ExtentTest test = util.getExtentTest();
            if (test != null && snapshotPath != null) {
                test.addScreenCaptureFromPath(snapshotPath);
            }
        }
		
		login.logOut();
		extent.flush();
	}
	
	public void afterClass() {
		
	}
	@AfterTest
	public void afterTest() {
		extent.flush();

		
	}
	@AfterSuite
	public void aftersuite()  {
		
		System.out.println("final Report Of  All Test Cases");
		
		util.browserQuit();
		
	}
	
	

}
