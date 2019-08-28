package test.assignment.discovery.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.assignment.discovery.testbase.TestBase;

public class MyVideosImpl extends TestBase {

	public static final Logger log = Logger.getLogger(HomePageImpl.class.getName());
	WebDriver driver;

	public MyVideosImpl(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[@class='localStorageCarousel__heading']")
	private WebElement FavoriteShowLabel;

	@FindBy(xpath = "//div[@class='localStorageCarousel__wrapper']//div[@class='show-carousel showCarousel__main']//div[@class='showTileSquare__description']")
	private List<WebElement> FavoriteDescription;

	@FindBy(xpath = "//div[@class='localStorageCarousel__wrapper']//div[@class='show-carousel showCarousel__main']//h3[@class='showTileSquare__title']")
	private List<WebElement> FavoriteTitle;

	public void ScrollToFavoriteShows() {
		ScrollToELement(FavoriteShowLabel, driver);
	}

	public void MouseOverAndVerifyVideos(String ExpectedTitle, String ExpectedDesc) throws Exception {
		// for (int i = 1; i >= 0; i--) {
		for (int i = 0; i <= 1; i++) {
			try {
				WebElement ele = FavoriteDescription.get(i);
				moveToElement(driver, ele);
				Thread.sleep(5000);
				try {
					String Myvideos_CapturedTitle = FavoriteTitle.get(i).getText();
					System.out.println("Captured Favorite video title is:" + Myvideos_CapturedTitle);
					if ((Myvideos_CapturedTitle !="null")&& (Myvideos_CapturedTitle.equals(ExpectedTitle))){
						log.info("Title Matched");
					}
					else{
						
						log.info("Title do not match");
					}
					try {
						String Myvideos_CapturedDesc = FavoriteDescription.get(i).getText();
						System.out.println("Captured Captured Favorite video desc is:" + Myvideos_CapturedDesc);
						if ((Myvideos_CapturedDesc !="null")&& (ExpectedDesc.equals(Myvideos_CapturedDesc))){
							log.info("Title Matched");
						}
						else{
							
							log.info("Title do not match");
						}
					

					} catch (Exception e) {
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
