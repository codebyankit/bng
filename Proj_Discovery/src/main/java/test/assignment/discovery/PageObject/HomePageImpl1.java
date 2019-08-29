package test.assignment.discovery.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.assignment.discovery.testbase.TestBase;

public class HomePageImpl1 extends TestBase {

	public static final Logger log = Logger.getLogger(HomePageImpl.class.getName());
	WebDriver driver;
	MyVideosImpl mvpl;

	public HomePageImpl1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='o-Header__m-SiteLogo']//a")
	private WebElement logo;

	@FindBy(xpath = "//div[contains(text(),'Recommended')]")
	private WebElement RecommendedShow;

	@FindBy(xpath = "//div[@id='react-root']/div[@class='app-main app__main umbrella']/div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//i[contains(@class,'flipIconCore__icon icon-plus')]")
	private List<WebElement> PlusIcon;

	@FindBy(xpath = "//div[@id='react-root']//div[@class='app-main app__main umbrella']//div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//div[@class='showTileSquare__description']")
	private List<WebElement> RecommendedDescription;

	@FindBy(xpath = "//div[@id='react-root']//div[@class='app-main app__main umbrella']//div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//h3[@class='showTileSquare__title']")
	private List<WebElement> RecommendedTitle;

	@FindBy(xpath = "//div[@class='localStorageCarousel__wrapper']//div[@class='show-carousel showCarousel__main']//div[@class='showTileSquare__description']")
	private List<WebElement> FavoriteDescription;

	@FindBy(xpath = "//div[@class='localStorageCarousel__wrapper']//div[@class='show-carousel showCarousel__main']//h3[@class='showTileSquare__title']")
	private List<WebElement> Favorite;

	/* *************************************************** */

	@FindBy(xpath = "//h2[@class='localStorageCarousel__heading']")
	private WebElement FavoriteShowLabel;

	@FindBy(xpath = "//div[@class='localStorageCarousel__wrapper']//div[@class='show-carousel showCarousel__main']//h3[@class='showTileSquare__title']")
	private List<WebElement> FavoriteTitle;

	public void ScrollToFavoriteShows() {
		ScrollToELement(FavoriteShowLabel, driver);
	}

	public void VerifyHomeScreen() {

		try {
			String DiscoveryLogo = logo.getText();
			if (("https://www.discovery.com").equals(DiscoveryLogo)) {
				log.info("Discovery icon displayed");

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public boolean ScrollToRecommendedVideos() throws Exception {
		boolean status=false;
		try{
		ScrollToELement(RecommendedShow, driver);
		status=true;
		}
		catch(Exception e){
			status=false;
			throw e;
		}
		return status;
	}

	public boolean MouseOverAndAdd() throws Exception {
		boolean status=false;
		for (int i = 0; i <= 2; i=i+2) {
			try {
				if (i == 2) {
					ScrollToRecommendedVideos();
					// Thread.sleep(4000);
				}
				WebElement ele = RecommendedDescription.get(i);
				moveToElement(driver, ele);
				Thread.sleep(5000);
				
				WebElement icon = PlusIcon.get(i);
				javascriptexeClick(icon, driver);				
				String Recommended_CapturedDesc = RecommendedDescription.get(i).getText();
				System.out.println("Captured desc of" + i + "th element is:" + Recommended_CapturedDesc);

				String Recommended_CapturedTitle = RecommendedTitle.get(i).getText();
				System.out.println("Captured title of" + i + "th element is:" + Recommended_CapturedTitle);

				NavigateToMyVideos(driver);
				ScrollToFavoriteShows();
				mvpl = new MyVideosImpl(driver);
				mvpl.MouseOverAndVerifyVideos(Recommended_CapturedTitle, Recommended_CapturedDesc);
				NavigateToGoDiscoveryUrl();
				
				status=true;

			} catch (Exception e) {
				e.printStackTrace();
				status=false;
			}
		}
		return status;
	}

}
