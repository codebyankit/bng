package test.assignment.discovery.PageObject;

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

	public List<String> mouseoverAdd() throws Exception {
		for (int i = 0; i <= 1; i++) {
			try {
				WebElement ele = RecommendedDescription.get(i);
				moveToElement(driver, ele);
				Thread.sleep(5000);

				try {
					String icon = PlusIcon.get(i).getText();
					clickElementByXpath(icon, driver);

					try {
						String Recommended_CapturedDesc = RecommendedDescription.get(i).getText();
						System.out.println("Captured desc is:" + Recommended_CapturedDesc);
					} catch (Exception e) {
						e.printStackTrace();

					}

					try {
						String Recommended_CapturedTitle = RecommendedTitle.get(i).getText();
						System.out.println("Captured title is:" + Recommended_CapturedTitle);
						
						
					}

					catch (Exception e) {
						e.printStackTrace();

					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
