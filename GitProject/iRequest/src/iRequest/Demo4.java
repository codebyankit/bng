package iRequest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Demo4 {

	public static void main(String[] args) {
	
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://smtlogin.zycus.net/sso/login");
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
		String ClassName = driver.findElement(By.xpath("//div[@id='rainbowHeader']")).getAttribute("class");
//		/System.out.println(ClassName);
		//driver.findElement(By.xpath("//div[@class='h-clearfix h-topBand-pad']"));
		
		if ("rb-header".equals(ClassName)) {
			System.out.println(ClassName);
			System.out.println("Rainbow Header");
		}
		else {
			
			System.out.println("Classic view Header");
		}
		
/*		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='rainbowHeader']")));*/

		//div[@class='h-clearfix h-topBand-pad']
		
	}

}
