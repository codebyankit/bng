package com.test.iRequest_Test.homepage;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.iRequest.PageObject.HomePage;
import com.test.iRequest.PageObject.LoginPage;
import com.test.iRequest.testBase.TestBase;

public class NewTest extends TestBase {

	HomePage homepage;
	LoginPage loginpage;
	public static final Logger log = Logger.getLogger(NewTest.class.getName());

	@Test(priority = 1)
	public void Login() throws Exception {

		try {
			log.info("=======Starting verifyLogin test========");
			//homepage = new HomePage(driver);
			loginpage.LoginToApplication("ankit.gaurav@zycus.com", "Zycus@123");
			log.info("=======Finished verifyLogin test========");

			// homepage.iRequestCard();
			log.info("******************iRequest selected*************");
			getScreenShot("verifyLogin");

		} catch (Exception e) {
			getScreenShot("verifyLoginExceptionIn");
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void CreateNewRequest() throws Exception {
		try {
			//homepage = new HomePage(driver);
			homepage.NewRequest("RD_QA_None1", "ReqName1Automation103", "ReqDescAutomation103");

		} catch (Exception e) {
			getScreenShot("Exception in Verify create new request");
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setUp() throws IOException {

		selectBrowser("chrome");
		SelectUrl("QCVMWARE");
		homepage = new HomePage(driver);
		loginpage=new LoginPage(driver);

	}

	@AfterClass
	public void afterClass() {

		log.info("**************All Test Finished******************");
		// driver.close();

	}

}
