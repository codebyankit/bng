package iRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo1 {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		///screenshot
		
		//WebDriver driver= new FirefoxDriver();
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://smtlogin.zycus.net/sso/login");
		
		//driver.get("https://preprodtenant.zycus.net/tms");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		WebElement Username=driver.findElement(By.id("emailAddressGhost")); 
		Actions actions= new Actions(driver);
		actions.moveToElement(Username);
		actions.click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		actions.sendKeys("ankit.gaurav@zycus.com");
		actions.build().perform();
		
		Actions action=new Actions(driver);
		WebElement passfield=driver.findElement(By.xpath("//*[@name='pass_temp']"));
		action.moveToElement(passfield);
		action.click();
		action.sendKeys("Zycus@123");
		action.build().perform();
		
		driver.findElement(By.id("signIn")).click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		
		
		driver.findElement(By.xpath("//div[@class='blockBg']/div[@class='iRequestBigLogo']")).click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		
		//span[@class='dIblock vMiddle']
		//div[@id='reqdialogdata']//ol[1]//li[1]//label[1]//input[1]
		//div[@id='reqdialogdata']//ol[8]//li[2]//label[1]
		//selectedRequestType
		//div[@id='reqdialogdata']//ol[8]//li[2]//label[1]
		//div[@id='reqdialogdata']//ol[8]//li[3]//label[1]
		//input[@id='txtRequisitionName']
		//textarea[@id='txtRequisitionDescription']
		//a[@id='submitToWorkflow']   >> if workflow is there 
		//span[contains(text(),'Submit')]
		//<span class="inptbtn">Submit </span> 
		
		
	//	driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[@class='dIblock vMiddle']")).click();
		
		 List<WebElement> ReqType = driver.findElements(By.xpath("//*[@class='frmLbl vspc dIblock bld']"));
		 List<WebElement> RDName = driver.findElements(By.xpath("//*[@class='chkLbl']"));

		
		int ReqTypeCount=ReqType.size();
		 System.out.println("ReqTypeCountCount is:" +ReqTypeCount);
		for(int i=0;i<ReqTypeCount;i++) {
			System.out.println(ReqType.get(i).getText());
		}
		
		int RDCount=RDName.size();
		System.out.println("Request Definition count is:" +RDCount);
		for (int j=0;j<RDCount;j++) {
			System.out.println(ReqType.get(j).getText());
		/*if(RDName.get(j).getText().contains(arg0)) {
			RDName.get(j).click();
			
		}*/	
		}
		
		
		//driver.findElement(By.xpath("//div[@id='reqdialogdata']//ol[8]//li[3]//label[1]")).click();
		//driver.findElement(By.id("selectedRequestType")).click();
		
		
}

}