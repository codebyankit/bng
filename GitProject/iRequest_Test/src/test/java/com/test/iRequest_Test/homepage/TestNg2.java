package test.assignment.proj_discovery;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.assignment.discovery.PageObject.Home1;
//import junit.framework.Assert;
import test.assignment.discovery.PageObject.HomePageImpl;
import test.assignment.discovery.PageObject.MyVideosImpl;
import test.assignment.discovery.testbase.TestBase;

public class TestNg2 extends TestBase {
Home1 hp;
	
	//MyVideosImpl mv;

	@BeforeClass
	public void startBrowser() throws Exception {
		selectBrowser("chrome");
		HomeUrl();
		NavigateToGoDiscoveryUrl();
	}

	@Test(priority = 1)
	public void SelectVideos() throws Exception {
		try {
			hp = new Home1(driver);
			hp.ScrollToRecommendedVideos();
			System.out.println("Scrolled to recommended section");
			hp.mouseoverAdd();
		} catch (Exception e) {
			getScreenShot("Unable scroll to recommended section");
			e.printStackTrace();
			driver.quit();
		}
	}
	@AfterClass
	public void afterclass() throws Exception {
		System.out.println("Test Done");
		driver.quit();

	}
}
