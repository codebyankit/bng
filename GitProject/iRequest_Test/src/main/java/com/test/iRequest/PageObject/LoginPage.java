package com.test.iRequest.PageObject;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.iRequest.testBase.TestBase;

public class LoginPage extends TestBase{
	/**
	 * @author ankit.gaurav
	 *
	 */
	
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	WebDriver driver;
	
	@FindBy(id = "emailAddressGhost")
	WebElement UserEmailAddressEditBox;
	
	@FindBy(xpath = "//*[@name='pass_temp']")
	WebElement PasswordEditBox;
	
	@FindBy(id = "signIn")
	WebElement LoginButton;
	

	
	//@FindBy(xpath = "//div[@id='rainbowHeader']/a[contains(@class,'rb-home')]")
	@FindBy(xpath = "//div[@class='blockBg']/div[@class='iRequestBigLogo']")
	WebElement iRequestCard;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// We use initElements method to initialize web elements.Init is static method
		// of pagefactory class
		PageFactory.initElements(driver, this);
	}
	
		
	public void LoginToApplication(String Username, String Password) throws InterruptedException {
		highlightMe(driver, UserEmailAddressEditBox);
		actionClickAndSendKeys(driver, UserEmailAddressEditBox, Username);
		log.info("****************Username entered successfully**********************");
		highlightMe(driver, PasswordEditBox);
		actionClickAndSendKeys(driver, PasswordEditBox, Password);
		log.info("****************Password entered successfully**********************");
		highlightMe(driver, LoginButton);
		LoginButton.click();
		implicitwait(10, TimeUnit.SECONDS);
		log.info("Logging in to application");

		// selecting iRequest card
		log.info("*************Selecting iRequest Card*******************");
		iRequestCard.click();
		implicitwait(10, TimeUnit.SECONDS);
	}

}
