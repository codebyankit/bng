package com.test.iRequest_Test.homepage;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.test.iRequest.PageObject.LoginPage;
import com.test.iRequest.PageObject.HomePage;
import com.test.iRequest.testBase.TestBase;

public class RDTest1 extends TestBase {

	HomePage homepage;
	LoginPage loginPage;
	public static final Logger log = Logger.getLogger(NewTest.class.getName());

	@Test(priority = 1)
	public void Login() throws Exception {

		try {
			log.info("=======Starting verifyLogin test========");
			 homepage = new HomePage(driver);
			
			loginPage.LoginToApplication("ankit.gaurav@zycus.com", "Zycus@123");
			log.info("=======Finished verifyLogin test========");

			 //homepage.iRequestCard();
			log.info("******************iRequest selected*************");
			getScreenShot("verifyLogin");

		} catch (Exception e) {
			getScreenShot("verifyLoginExceptionIn");
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void CreateRequestDef() {
		 homepage = new HomePage(driver);
		homepage.CreateNewRD("RDAutomation2", "AutoType2");
	}

	@BeforeClass
	public void setUp() throws IOException {

		selectBrowser("chrome");
		SelectUrl("QA");
		homepage = new HomePage(driver);
		loginPage=new LoginPage(driver);

	}

	@AfterClass
	public void afterClass() {

		log.info("**************All Test Finished******************");
		// driver.close();

	}

}
