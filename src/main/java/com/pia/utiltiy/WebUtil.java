package com.pia.utiltiy;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebUtil {


	private WebUtil() {

	}


	private static  WebUtil webUtil;

	private   WebDriver   driver;
	private ExtentTest extentTest;
	private JavascriptExecutor jsExecutor;


	public ExtentTest getExtentTest() {
		return extentTest;
	}



	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}



	public WebDriver getDriver() {
		return driver;
	}



	public static WebUtil getObject() {
		if(webUtil==null) {
			webUtil =	new WebUtil(); 
		}
		return webUtil;
	}







	public void browserLaunch(String browserName) {

		try {

			//System.setProperty("webdriver.chrome.driver","Driver\\chromedriver.exe");  

			if(browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();

				driver =  new ChromeDriver();
				windowMaxmize();
				implicitlyWait(60);


			}else if(browserName.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();;
				driver =  new EdgeDriver();
				windowMaxmize();
				implicitlyWait(60);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getPropertiesObj() {
		Properties	prop=   new Properties();
		FileInputStream file=null;
		try {
			file=	new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Configaration.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {


			e.printStackTrace();
		}

		return	prop;
	}



	public void openUrl(String url) throws Exception {
		try {
			driver.get(url);

			//extentTest.log(null, null)

			jsExecutor = (JavascriptExecutor)driver;

		}catch(Exception e) {
			extentTest.log(Status.FAIL, "Url Is Not Opened ");

			throw e;


		}

	}
	public String takeSnapshot(String testCaseName) {

		TakesScreenshot  tss=(TakesScreenshot) driver;
		File snapShortSourceFile= tss.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat df= new SimpleDateFormat("MM-dd-yyyy hh_mm_ss a");
		String dateStamp=  df.format(new Date());
		File snapshotDestintionFileObj=  new File(System.getProperty("user.dir")+"//target//Test-Output//Screenshot//"+testCaseName+dateStamp+".png");
		extentTest.log(Status.FAIL, "Take Snapshot  "+testCaseName+ " For  This Test Case ");
		try {
			Files.copy(snapShortSourceFile, snapshotDestintionFileObj);
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return snapshotDestintionFileObj.getAbsolutePath();     


	}
	public void windowMaxmize() {
		try {
			driver.manage().window().maximize();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void implicitlyWait(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));


		}catch(Exception e) {


			e.printStackTrace();
		}

	}
	public WebElement findElement(String path) throws Exception {
		WebElement element=null;
		try {
			element=driver.findElement(By.xpath(path));
			extentTest.log(Status.INFO,"Element Is  Find Successfully");
		}catch(Exception e) {
			extentTest.log(Status.FAIL,"Element Is  Not Finding ");

			throw e;

		}

		return element;

	}




	public void  click(WebElement we)  {
		String name=null;
		try {
			name= we.getAccessibleName();
			we.click();
			extentTest.log(Status.INFO, "Clicked ON "+name+" Element Successfully  ");
		}catch(NoSuchElementException noEx) {
			visibilityOfElement(we, 30);
			jsExecutor.executeScript("arguments[0].click();", we);
			extentTest.log(Status.INFO, "Click On "+name+" Element Successfully  ");

		}
		catch(Exception e) {
			extentTest.log(Status.FAIL, "Click  Is  Not Working  on "+name+" ");

			e.printStackTrace();

		}

	}
	public void jsClick(WebElement we) {

		jsExecutor.executeScript("arguments[0].click();",we);
		extentTest.log(Status.INFO, "Java Exector  click on Element Successfully");


	}

	public void browserQuit() {

		try {
			driver.quit();
			extentTest.log(Status.PASS, "Browser Is Quited Successfully");
		}catch(Exception e) {
			extentTest.log(Status.FAIL, "Browser Is Not Quited Successfully");

			e.getMessage();

		}

	}

	public  List<String> getTextList(List<WebElement> list) {
		List<String> textList= new ArrayList<>();


		for(int i=0;i<list.size();i++) {
			WebElement we=list.get(i);
			String actuleText= we.getText();
			textList.add(actuleText);	

		}

		return textList;
	}


	public void clearTextBoxValue(WebElement textBox) {

		String textBoxName=null;
		try {
			textBoxName=	textBox.getAccessibleName();
			textBox.clear();
			getExtentTest().log(Status.INFO, textBoxName+" This text box is cleared  Successfully  ");


		}catch (NoSuchElementException e) {
			visibilityOfElement(textBox, 30);
			textBoxName=	textBox.getAccessibleName();
			textBox.clear();
			getExtentTest().log(Status.INFO, textBoxName+" This text box is cleared  Successfully  ");

		}catch(Exception e){
			getExtentTest().log(Status.FAIL, textBoxName+" This text box is not cleared  ");

			e.getMessage();
		}



	}




	public void sendValue(WebElement we,String value) {
		String name=null;
		try {
			name= we.getAccessibleName();
			we.sendKeys(value);
			extentTest.log(Status.INFO, value+" Value Enter in Text Box Successfully");

		}catch(NoSuchElementException noSuchElement) {
			jsExecutor.executeScript("arguments[0].value,"+value+";", we);
			extentTest.log(Status.INFO, value+" Value Enter in Text Box Successfully");


		}catch(Exception e) {
			extentTest.log(Status.FAIL, value+" Value not Enter in Text Box ");

		}

	}


	public void switchToFrameByNameOrId(String NameOrId) throws Exception {
		try {
			driver.switchTo().frame(NameOrId);
			extentTest.log(Status.INFO,"the window is switched into the frame by Name or Id  suceessfully");
		} catch (NoSuchFrameException e) {
			driver.switchTo().frame(NameOrId);
			extentTest.log(Status.INFO,"the window is switched into the frame by Name or Id  suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().frame(NameOrId);
			extentTest.log(Status.INFO,"the window is switched into the frame by Name or Id  suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			extentTest.log(Status.FAIL,"the window is not switched into the frame by Name or Id  suceessfully");
			throw e;
		}
	}

	//////////// switchToParentFrame \\\\\\\\\\

	public void switchToParentFrame(String elementName) throws Exception {
		try {
			driver.switchTo().parentFrame();
			extentTest.log(Status.INFO,"the window is switched into the parentFrame suceessfully");
		} catch (StaleElementReferenceException e) {
			driver.switchTo().parentFrame();
			extentTest.log(Status.INFO,"the window is switched into the parentFrame suceessfully");
		} catch (Exception e) {
			e.printStackTrace();
			extentTest.log(Status.FAIL,"the window is not switched into the parentFrame ");
			throw e;
		}
	}

	public void visibilityOfElement(WebElement we,int time) {

		WebDriverWait wait=null;
		try {

			wait=    new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.visibilityOf(we));
			extentTest.log(Status.INFO, "Element  is  visibility  Successfully"); 

		}catch(Exception e) {
			extentTest.log(Status.FAIL, "Element  is Not   visibility  "); 
			e.printStackTrace();

		}

	}
	public void elementToBeClickable(WebElement we,int time) {

		WebDriverWait wait=null;
		String name=null;
		try {
         name=    we.getAccessibleName();
			wait=  new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.elementToBeClickable(we));
			extentTest.log(Status.INFO,name+ " This Element  is Clickable   Successfully"); 

		}catch(Exception e) {
			extentTest.log(Status.FAIL, "Element  is Not   Clickable  "); 
			e.printStackTrace();

		}

	}



	public void popUpAccept() throws Exception {
		try {
			driver.switchTo().alert().accept();
			extentTest.log(Status.INFO,"Pop up is accepted successfully");
		} catch (NoAlertPresentException e) {
			WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(30));
			extentTest.log(Status.PASS," Wait for the alert to be present (timeout for 30 seconds)");
			webWait.until(ExpectedConditions.alertIsPresent());
			extentTest.log(Status.PASS,"Switch to the alert and perform operations if needed");
			driver.switchTo().alert().accept();
			extentTest.log(Status.INFO,"Pop up is accepted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			extentTest.log(Status.FAIL,"Pop up is not accepted successfully");
			throw e;
		}

	}

	//======================= Window Handles========================//

	public void getHandleWindowByTitle(String expactadTitle) {
		Set<String> windows=null;
		try {			
			windows= 	driver.getWindowHandles();
			for(String window:windows) {
				driver.switchTo().window(window);

				String  title= driver.getTitle();

				if(title.equalsIgnoreCase(expactadTitle)) {
					extentTest.log(Status.INFO, "Switch To New Window By Title  Successfully");

					break;
				}
			}


		}
		catch(Exception e) {
			extentTest.log(Status.FAIL, "Not Switch To New Window By Title  ");
			e.printStackTrace();
		}
	}
	public void getHandleWindowByUrl(String expactadUrl) {
		Set<String> windows=null;
		try {			
			windows= driver.getWindowHandles();
			for(String window:windows) {
				driver.switchTo().window(window);

				String  url= driver.getCurrentUrl();

				if(url.equalsIgnoreCase(expactadUrl)) {
					extentTest.log(Status.INFO, "Switch To New Window By Url  Successfully");

					break;
				}
			}


		}
		catch(Exception e) {
			extentTest.log(Status.FAIL, "Not Switch To New Window By Url ");
			e.printStackTrace();
		}
	}

	public String getCurrentWindow() {
		String window=null;
		try {
			window=driver.getWindowHandle();
			extentTest.log(Status.INFO, " Get Current Window  Hash Code  Successfully");

		}catch(Exception e) {
			e.getMessage();
			extentTest.log(Status.INFO, "Not  Get Current Window  Hash Code  ");


		}
		return window;
	}

	public String  currentPageTitle() {
		String currentTitle=null;
		try {

			currentTitle=	driver.getTitle();
			extentTest.log(Status.INFO, " Get  Current Page title--> "+currentTitle);
		}catch(Exception e) {
			extentTest.log(Status.FAIL, "  Current Page title is not Get  "+currentTitle);

			e.getMessage();

		}

		return currentTitle;
	}


	//====----------- mouse actions----======--------------//
	public void mouseOver(WebElement webObj)  {

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			extentTest.log(Status.PASS,"Element is displayed and enabled");
			Actions act = new Actions(driver);
			try {
				act.moveToElement(webObj).build().perform();
				extentTest.log(Status.PASS,"mouseOver action is done successfully by Actions method");
			} catch (StaleElementReferenceException e) {

				act.click(webObj).build().perform();
				extentTest.log(Status.PASS,"mouseOver action is done successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", webObj);
				extentTest.log(Status.PASS,"mouseOver action is done successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				extentTest.log(Status.FAIL,"mouseOver action is not  done successfully by Actions method");
			}
		} else {
			extentTest.log(Status.FAIL,"mouseOver action is not done successfully by Actions method");

		}
	}


	public String getUIText(WebElement we) {
		String  text=null;
		try {
			text=	we.getText(); 
			extentTest.log(Status.INFO, "Get Inner Text Value is  Successfully -->"+text);	
		}catch (NoSuchElementException e) {
			text=	we.getText(); 
			extentTest.log(Status.INFO, " Get Inner Text Value is  Get Successfully --> "+text);	

		}

		catch(Exception e) {
			extentTest.log(Status.FAIL, "InnerText Value is  Not Get  ");	

			e.printStackTrace();


		}
		return text;
	}


	public void mouseClick(WebElement webObj) {

		if (webObj.isDisplayed() && webObj.isEnabled()) {

			extentTest.log(Status.PASS,"Element is displayed and enabled");
			Actions act = new Actions(driver);
			try {
				act.click(webObj).build().perform();
				extentTest.log(Status.PASS,"Click action is done successfully by Actions method");
			} catch (StaleElementReferenceException e) {

				act.click(webObj).build().perform();
				extentTest.log(Status.PASS,"Click action is done successfully by Actions method");
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", webObj);
				extentTest.log(Status.PASS,"Click action is done successfully by Actions method");
			} catch (Exception e) {
				e.printStackTrace();
				extentTest.log(Status.FAIL,"Click Action is not done successfully by Actions method");

			}
		} else {
			extentTest.log(Status.FAIL,"Element is not displayed and enabled");

		}
	}



	public void browserClose() {
		try {
			driver.close();
			extentTest.log(Status.INFO, "Creant Browser is closed Successfully");

		}catch(Exception e) {
			extentTest.log(Status.FAIL, "Creant Browser is Not  closed Successfully");
			e.printStackTrace();

		}
	}

	public boolean  getElementIsEnable(WebElement we) {
		boolean status=false;
		try {
			String name=  we.getAccessibleName();
			status=	we.isEnabled();

			extentTest.log(Status.INFO,name+ "This Element  is Enabled on UI page  actule status "+status);


		}catch(Exception e) {
			extentTest.log(Status.FAIL," This Element  is not  Enabled on UI page  actule status ");
			e.getMessage();

		}

		return status;
	}

	public boolean getElementIsDisplay(WebElement we) {

		boolean status=false;
		String name =null;
		try {
			name=  we.getAccessibleName();
			status=we.isDisplayed();
			extentTest.log(Status.PASS,name+"The element is displayed. status -> "+status);


		}catch(Exception e) {
			extentTest.log(Status.FAIL,name+"The element is not displayed. status -> "+status);
			e.getMessage();

		}
		return status;

	}


}
