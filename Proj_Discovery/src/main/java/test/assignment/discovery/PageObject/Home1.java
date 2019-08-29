package test.assignment.discovery.PageObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.assignment.discovery.testbase.TestBase;

public class Home1 extends TestBase {

	public static final Logger log = Logger.getLogger(Home1.class.getName());
	WebDriver driver;
	MyVideosImpl mvpl;

	public Home1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='o-Header__m-SiteLogo']//a")
	private WebElement logo;

	@FindBy(xpath = "//div[contains(text(),'Recommended')]")
	private WebElement RecommendedShow;

	/*
	 * @FindBy(xpath =
	 * "//div[@id='react-root']/div[@class='app-main app__main umbrella']/div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//i[contains(@class,'flipIconCore__icon icon-plus')]"
	 * ) private List<WebElement> PlusIcon;
	 */

	@FindBy(xpath = "//div[@id='react-root']//div[@class='app-main app__main umbrella']//div[@class='page-wrapper app__pageWrapper']//section[8]//div[1]//i[contains(@class,'flipIconCore__icon icon-plus')]")
	private List<WebElement> PlusIcon;

	@FindBy(xpath = "//div[@id='react-root']//div[@class='app-main app__main umbrella']//div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//div[@class='showTileSquare__description']")
	private List<WebElement> RecommendedDescription;

	@FindBy(xpath = "//div[@id='react-root']//div[@class='app-main app__main umbrella']//div[@class='page-wrapper app__pageWrapper']/section[8]/div[1]//h3[@class='showTileSquare__title']")
	private List<WebElement> RecommendedTitle;

	public void ScrollToRecommendedVideos() {
		ScrollToELement(RecommendedShow, driver);
	}
	
	@FindBy(xpath = "//h2[@class='localStorageCarousel__heading']")
	private WebElement FavoriteShowLabel;
	
	public void ScrollToFavoriteShows() {
		ScrollToELement(FavoriteShowLabel, driver);
	}
	
	public void mouseoverAdd() throws Exception {
		for (int i = 0; i <= 2; i=i+2) {
			try {
				WebElement ele = RecommendedDescription.get(i);
				moveToElement(driver, ele);
				Thread.sleep(5000);

				/*String icon = PlusIcon.get(i).getText();
				clickElementByXpath(icon, driver);*/
				/*WebElement icon = PlusIcon.get(i);
				moveToElementAndClick(driver, icon);*/
				WebElement icon = PlusIcon.get(i);
				System.out.println(icon.getText());
				// WaitForElementClickable(driver, icon, 20);
				javascriptexeClick(icon, driver);

				String Recommended_CapturedDesc = RecommendedDescription.get(i).getText();
				// System.out.println("Captured desc is:" +Recommended_CapturedDesc);

				List<String> desc = new ArrayList<String>();
				desc.add(Recommended_CapturedDesc);
				for (int j = 0; j <= 1; j++) {
					String Desc = desc.get(j);
					System.out.println("Desc value is:" + Desc);
				//}

				String Recommended_CapturedTitle = RecommendedTitle.get(i).getText();
				// System.out.println("Captured title is:" +
				// Recommended_CapturedTitle);
				List<String> title = new ArrayList<String>();
				title.add(Recommended_CapturedTitle);
				//for (int k = 0; k <= 1; k++) {
					String Title = title.get(j);
					System.out.println("Title value is:" + Title);
				//}
				NavigateToMyVideos(driver);
				ScrollToFavoriteShows();
				mvpl = new MyVideosImpl(driver);
				mvpl.MouseOverAndVerifyVideos(Title, Desc);
				}
			}

			catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

}
