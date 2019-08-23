package iRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo3 {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		/// screenshot

		// WebDriver driver= new FirefoxDriver();
		// WebDriver driver= new ChromeDriver();
		WebDriver driver = new ChromeDriver();

		driver.get("https://smtlogin.zycus.net/sso/login");

		// driver.get("https://preprodtenant.zycus.net/tms");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement Username = driver.findElement(By.id("emailAddressGhost"));
		Actions actions = new Actions(driver);
		actions.moveToElement(Username);
		actions.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		actions.sendKeys("ankit.gaurav@zycus.com");
		actions.build().perform();

		Actions action = new Actions(driver);
		WebElement passfield = driver.findElement(By.xpath("//*[@name='pass_temp']"));
		action.moveToElement(passfield);
		action.click();
		action.sendKeys("Zycus@123");
		action.build().perform();

		driver.findElement(By.id("signIn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//div[@class='blockBg']/div[@class='iRequestBigLogo']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		//creating new request
//		//for(int i=1;i<=50;i++) {
//			//driver.findElement(By.id("txtRequisitionName")).sendKeys("RequestAuto2"+i);
//		driver.findElement(By.xpath("//span[@class='dIblock vMiddle']")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//div[@id='reqdialogdata']//ol[1]//li[1]//label[1]")).click();
//		driver.findElement(By.id("selectedRequestType")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.id("txtRequisitionName")).sendKeys("RequestAutomation");
//		driver.findElement(By.id("txtRequisitionDescription")).sendKeys("RequestAutoDesc");
//		//driver.findElement(By.id("txtRequisitionName")).sendKeys("RequestAutomation"+i);
//		//driver.findElement(By.id("txtRequisitionDescription")).sendKeys("RequestAutoDesc"+i);
//		driver.findElement(By.xpath("//span[contains(text(),'Submit')]")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//		driver.findElement(By.xpath("//div[@id='workFlowTrails']/div[@class='workflowContainer']/div[2]/div/input"))
//				.click();
//		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		//Thread.sleep(3000);
//		//}
//		
//
//		//Verify if request gets added:
//		  driver.findElement(By.id("txtFltrReqName")).sendKeys("Test");
//		  driver.findElement(By.xpath("//div[@class='dataTables_filter']")).click();
//		  String NoResult =
//		  driver.findElement(By.xpath("//table[@id='reqList']")).getText(); if
//		  ((NoResult.equals("No results found"))) {
//		  
//		  System.out.println("No Request added"); } else {
//		  System.out.println("Request added Succesfully!"); 
//		  Thread.sleep(3000);}
		  	 
	
		//Create new Rd:
		driver.findElement(By.id("requestdef_sub")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//edit of RD:
		driver.findElement(By.xpath("//*[contains(text(),'AutoRD25')]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
/*		driver.findElement(By.xpath("//span[@class='dIblock vMiddle']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		driver.findElement(By.id("txtRequestDefName")).sendKeys("AutoRD26");
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id='ui-id-1']"));
		System.out.println("Size is:" +list.size());
		driver.findElement(By.id("txtRequestTypeName")).sendKeys("AutoType1");
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-1']")));
		
		for (int i=0;i<list.size();i++) {	
			System.out.println(list.get(i).getText());
			if(list.get(i).getText().equals("AutoType1")) {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				//Thread.sleep(3000);
				list.get(i).click();
				System.out.println("*****************************Clicked************************");
			}
		}
		
		
		
		WebElement RadioButton1 = driver.findElement(By.xpath("//div[@class='fL eFormToggleBtnSet fleft']/a"));
		RadioButton1.click();
		Thread.sleep(2000);*/
		
		
		
		driver.findElement(By.xpath("//a[@id='eformConfigure']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Thread.sleep(7000);
		
		//Uding java script executor:
		WebElement AddSectionBtn = driver.findElement(By.xpath("//span[@class='zydf-icon zydf-add']"));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", AddSectionBtn);
		
	
		 /*WebDriverWait wait1 = new WebDriverWait(driver,30);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Add Section')]")));
		    driver.findElement(By.xpath("//*[contains(text(),'Add Section')]")).click();  */
		
		
		
		driver.findElement(By.id("zydf-dfSectionName")).sendKeys("Section1Name");
		
		driver.findElement(By.id("zydf-dfSectionDescription")).sendKeys("Desc section1");

		driver.findElement(By.id("zydf-dfSectionSave")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//h2[contains(text(),'Custom Fields')]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		System.out.println("Custom field clicked");
		
		//public void DragDrop(WebElement SourceWebElement, WebElement DestinationWebElement) {
			Actions act = new Actions(driver);
			act.clickAndHold(driver.findElement(By.xpath("//ul[@id='zydf-devLeftDragList']//li[1]//a[1]"))).perform();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			act.moveToElement(driver.findElement(By.xpath("//ul[@class='zydf-section-body zydf-dfFields zydf-clearfix connectedSortable connectedSortable_1 ui-sortable']"))).perform();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
			act.release(driver.findElement(By.xpath("//ul[@class='zydf-section-body zydf-dfFields zydf-clearfix connectedSortable connectedSortable_1 ui-sortable']"))).perform();
			
			
			
		//}
		}
	}
	