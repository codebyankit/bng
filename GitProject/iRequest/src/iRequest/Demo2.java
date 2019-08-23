package iRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;




public class Demo2 {
	
	public static void main(String[] args) throws InterruptedException{
		
		try{
		
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			///screenshot
			
			//WebDriver driver= new FirefoxDriver();
			//WebDriver driver= new ChromeDriver();
			WebDriver driver= new ChromeDriver();
			
			driver.get("https://smtlogin.zycus.net/sso/login");
			
			//driver.get("https://preprodtenant.zycus.net/tms");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			WebElement Username=driver.findElement(By.id("emailAddressGhost")); 
			Actions actions= new Actions(driver);
			actions.moveToElement(Username);
			actions.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			actions.sendKeys("ankit.gaurav@zycus.com");
			actions.build().perform();
			
			Actions action=new Actions(driver);
			WebElement passfield=driver.findElement(By.xpath("//*[@name='pass_temp']"));
			action.moveToElement(passfield);
			action.click();
			action.sendKeys("Zycus@123");
			action.build().perform();
			
			driver.findElement(By.id("signIn")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//div[@class='blockBg']/div[@class='iRequestBigLogo']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			
			
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//span[@class='dIblock vMiddle']")).click();
			
			
			
			
		List<WebElement> options= driver.findElements(By.xpath("//*[@class='frmLbl vspc dIblock bld']"));
		List<WebElement> values=driver.findElements(By.xpath("//*[@class='chkLbl']"));
		int lenofoptions=options.size();
		int lenofvalues=values.size();
		System.out.println(lenofoptions);
		System.out.println(lenofvalues);
		for(int i=0;i<lenofoptions;i++){
			System.out.println(options.get(i).getText());
			}
		for(int j=0;j<lenofvalues;j++){
		System.out.println(values.get(j).getText());
		if(values.get(j).getText().contains("addreqvalue")){
			values.get(j).click();
		}
		}
			driver.findElement(By.xpath("//*[@type='submit']")).click();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Unable to click Add Request Button");
			}
	}
	public static void reqFormInput(WebDriver driver){
		driver.findElement(By.id("txtRequisitionName")).sendKeys("Text");
		driver.findElement(By.id("txtRequisitionDescription")).sendKeys("Description");
		driver.findElement(By.id("submitToWorkflow")).click();
		WebElement userfield=driver.findElement(By.xpath("//*[@id='actionButton']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(userfield);
		actions.click();
	}

}