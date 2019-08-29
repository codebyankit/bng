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
		int count=0;
		int j=0;
		if(count>0){
			j=j+1;
		}
		
		// for (int i = 1; i >= 0; i--) {
		for (int i = j; i <= j; i++) {
			try {
				WebElement ele = FavoriteDescription.get(i);
				System.out.println(ele);
				moveToElement(driver, ele);
				Thread.sleep(5000);
				try {
					String Myvideos_CapturedTitle = FavoriteTitle.get(i).getText();
					System.out.println("Added favorite video title is:" + Myvideos_CapturedTitle);
					if ((Myvideos_CapturedTitle !="null")&& (Myvideos_CapturedTitle.equals(ExpectedTitle))){
						System.out.println("Title Matched");
						log.info("*****************Title Matched******************************");
					}
					else{
						System.out.println("Title Matched");
						log.info("*******************Title do not match***********************");
					}
					try {
						String Myvideos_CapturedDesc = FavoriteDescription.get(i).getText();
						System.out.println("Captured Captured Favorite video desc is:" + Myvideos_CapturedDesc);
						if ((Myvideos_CapturedDesc !="null")&& (ExpectedDesc.equals(Myvideos_CapturedDesc))){
							System.out.println("Description Matched");
							log.info("***************************Description Matched****************************");
						}
						else{
							System.out.println("Description Matched");
							log.info("*********************Description do not match*****************************");
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
		count++;
	}

}
