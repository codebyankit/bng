package test.assignment.discovery.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.assignment.discovery.testbase.TestBase;

public class HomePageImpl extends TestBase {

	public static final Logger log = Logger.getLogger(HomePageImpl.class.getName());
	WebDriver driver;
	MyVideosImpl mvpl;
	public HomePageImpl(WebDriver driver) {
		this.driver = driver;
		// We use initElements method to initialize web elements.Init is static
		// method
		// of pagefactory class
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

	public void ScrollToRecommendedVideos() {
		ScrollToELement(RecommendedShow, driver);
	}

	public void MouseOver() throws Exception {
		try {
			WebElement ele = RecommendedDescription.get(0);
			moveToElementAndClick(driver, ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MouseOverAndAdd() throws Exception {
		for (int i = 0; i <= 1; i++) {
			try {
				/*if (i == 1) {
					ScrollToRecommendedVideos();
				}*/
				WebElement ele = RecommendedDescription.get(i);
				moveToElement(driver, ele);
				Thread.sleep(5000);
				try {
					String icon = PlusIcon.get(i).getText();
					clickElementByXpath(icon, driver);

					/*
					 * WebElement icon= PlusIcon.get(i);
					 * WaitForElementClickable(driver, icon, 20); icon.click();
					 */
					/*
					 * WebElement icon= PlusIcon.get(i);
					 * WaitForElementClickable(driver, icon, 20);
					 * javascriptexeClick(icon, driver);
					 */

					/*
					 * List<Taco> tacoList = new ArrayList<Taco>(); for (int i =
					 * 0; i < MAX_TACOS; i++) { tacoList.add(new Taco(i));
					 */

					try {
						String Recommended_CapturedDesc = RecommendedDescription.get(i).getText();
						System.out.println("Captured desc is:" + Recommended_CapturedDesc);

						try {
							String Recommended_CapturedTitle = RecommendedTitle.get(i).getText();
							System.out.println("Captured title is:" + Recommended_CapturedTitle);

							// try{
							/*
							 * NavigateToMyVideos(driver); mvpl= new
							 * MyVideosImpl(driver);
							 * mvpl.MouseOverAndVerifyVideos(
							 * Recommended_CapturedTitle,
							 * Recommended_CapturedDesc);
							 * NavigateToGoDiscoveryUrl();
							 */

							/*
							 * *************************************************
							 * ********
							 */

							try {
								NavigateToMyVideos(driver);
								ScrollToFavoriteShows();
								WebElement ele1 = FavoriteDescription.get(i);
								moveToElement(driver, ele1);
								Thread.sleep(5000);
								try {
									String Myvideos_CapturedTitle = FavoriteTitle.get(i).getText();
									System.out.println("Captured Favorite video title is:" + Myvideos_CapturedTitle);
									if ((Myvideos_CapturedTitle != "null")
											&& (Recommended_CapturedTitle.equals(Myvideos_CapturedTitle))) {
										log.info("Title Matched");
									} else {

										log.info("Title do not match");
									}
									try {
										String Myvideos_CapturedDesc = FavoriteDescription.get(i).getText();
										System.out.println(
												"Captured Captured Favorite video desc is:" + Myvideos_CapturedDesc);
										if ((Myvideos_CapturedDesc != "null")
												&& (Recommended_CapturedDesc.equals(Myvideos_CapturedDesc))) {
											log.info("Desc Matched");
											NavigateToGoDiscoveryUrl();
										} else {

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

							/*
							 * ************************************************
							 */
							// }

							/*
							 * catch(Exception e){ e.printStackTrace();
							 * 
							 * }
							 */

						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
