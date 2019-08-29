package test.assignment.proj_discovery;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import test.assignment.discovery.PageObject.HomePageImpl;
import test.assignment.discovery.PageObject.HomePageImpl1;
import test.assignment.discovery.PageObject.MyVideosImpl;
import test.assignment.discovery.testbase.TestBase;

public class TestNg1 extends TestBase {
	HomePageImpl1 hp;
	MyVideosImpl mv;
	public static final Logger log = Logger.getLogger(TestNg1.class.getName());

	@BeforeClass
	public void startBrowser() throws Exception {
		log.info("Before class executed successfully");
		selectBrowser("chrome");
		HomeUrl();
		NavigateToGoDiscoveryUrl();
	}

	@Test(priority = 1)
	public void SelectVideos() throws Exception {
		try {
			hp = new HomePageImpl1(driver);
			boolean flag = hp.ScrollToRecommendedVideos();
			Assert.assertTrue(flag,
					"*********************Scrolled to recommended videos successfully********************");
			System.out.println("*****************Scrolled to recommended section**********************");
			boolean flag1 = hp.MouseOverAndAdd();
			Assert.assertTrue(flag1,
					"*********************Videos verified successfully********************");
			getScreenShot("****************verifing added videos******************");
		} catch (Exception e) {
			getScreenShot("Unable scroll to recommended section");
			e.printStackTrace();
			driver.quit();
		}
	}

	@AfterClass
	public void afterclass() throws Exception {

		System.out.println("*******************Test Done*****************************");
		driver.quit();

	}
}
