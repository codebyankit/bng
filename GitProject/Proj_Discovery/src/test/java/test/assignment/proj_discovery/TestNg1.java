package test.assignment.proj_discovery;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import test.assignment.discovery.PageObject.HomePageImpl;
import test.assignment.discovery.PageObject.MyVideosImpl;
import test.assignment.discovery.testbase.TestBase;

public class TestNg1 extends TestBase {
	HomePageImpl hp;
	MyVideosImpl mv;

	@BeforeClass
	public void startBrowser() throws Exception {
		selectBrowser("chrome");
		HomeUrl();
		NavigateToGoDiscoveryUrl();
	}

	@Test(priority = 1)
	public void SelectVideos() throws Exception {
		try {
			hp = new HomePageImpl(driver);
			hp.ScrollToRecommendedVideos();
			System.out.println("Scrolled to recommended section");
			hp.MouseOverAndAdd();
		} catch (Exception e) {
			getScreenShot("Unable scroll to recommended section");
			e.printStackTrace();
			driver.quit();
		}
	}

/*	@Test(priority = 2)
	public void VerifySelectedVideos() throws Exception {
		try {
			// Have to use assert return should be boolean
			NavigateToMyVideos();
			System.out.println("Navigated to favotite section");
			mv = new MyVideosImpl(driver);
			mv.ScrollToFavoriteShows();
			System.out.println("Scrolled to favorite show section");
			mv.MouseOverAndVerifyVideos();
		} catch (Exception e) {
			getScreenShot("Unable to navigated to favotite section");
			e.printStackTrace();
			driver.quit();
		}
	}*/

	@AfterClass
	public void afterclass() throws Exception {
		System.out.println("Test Done");
		driver.quit();

	}
}
