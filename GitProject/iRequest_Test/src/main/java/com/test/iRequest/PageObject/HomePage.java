package com.test.iRequest.PageObject;

import java.util.List;
import java.util.concurrent.TimeUnit;



import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.iRequest.testBase.TestBase;

/**
 * @author ankit.gaurav
 *
 */
public class HomePage extends TestBase {

	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	/*
	 * @FindBy(id = "emailAddressGhost") WebElement UserEmailAddressEditBox;
	 * 
	 * @FindBy(xpath = "//*[@name='pass_temp']") WebElement PasswordEditBox;
	 * 
	 * @FindBy(id = "signIn") WebElement LoginButton;
	 * 
	 * @FindBy(xpath = "//div[@class='blockBg']/div[@class='iRequestBigLogo']")
	 * WebElement iRequestCard;
	 */

	@FindBy(xpath = "//span[@class='dIblock vMiddle']")
	WebElement NewRequestBtn;

	@FindBy(id = "txtRequisitionName")
	WebElement ReqNameEdtBox;

	@FindBy(id = "txtRequisitionDescription")
	WebElement ReqDescEdtBox;

	@FindBy(xpath = "//span[contains(text(),'Submit')]")
	WebElement ReqSubmitBtn;

	@FindBy(xpath = "//div[@id='workFlowTrails']/div[@class='workflowContainer']/div[2]/div/input")
	WebElement SaveWFonReq;

	@FindBy(id = "selectedRequestType")
	WebElement RD_Ok;

	@FindBy(id = "txtFltrReqName")
	WebElement searchRequestEtdBox;

	@FindBy(xpath = "//div[@class='dataTables_filter']")
	WebElement clickouside;

	@FindBy(xpath = "//table[@id='reqList']")
	WebElement NoResultFound;

	@FindBy(xpath = "//span[@class='dIblock vMiddle']")
	WebElement CreateRDBtn;

	@FindBy(id = "requestdef_sub")
	WebElement SelectRequestDeftag;

	@FindBy(id = "txtRequestDefName")
	WebElement RequestDefinitionName;

	@FindBy(id = "txtRequestTypeName")
	WebElement RequesttypeName;

	@FindBy(xpath = "//ul[@id='ui-id-1']")
	List<WebElement> RdTypeList;

	@FindBy(xpath = "// div[@class='fL eFormToggleBtnSet fleft']/a")
	WebElement RadioFF;

	@FindBy(xpath = "//a[@id='eformConfigure']")
	WebElement FFConfigureButton;

//span[@class='zydf-icon zydf-add']
	@FindBy(id = "zydf-btnDevAddSection")
	WebElement InsideFFAddSectionButton;

	@FindBy(id = "zydf-dfSectionName")
	WebElement InsideFFCreateSectionNameEtdbox;

	@FindBy(id = "zydf-dfSectionDescription")
	WebElement InsideFFCreateSectionDescEtdbox;

	@FindBy(id = "zydf-dfSectionSave")
	WebElement InsideFFCreateSectionSave;

	@FindBy(xpath = "//h2[contains(text(),'Custom Fields')]")
	WebElement SelectCustomField;

	// h2[contains(text(),'Custom Master')]
	// h2[contains(text(),'Standard Master')]
	// h2[contains(text(),'iContract')]
	// h2[contains(text(),'iManage')]
	// h2[contains(text(),'iSource')]

	@FindBy(id = "// div[@class='fL workFlowToggleBtnSet']/a")
	WebElement RadioWF;

	@FindBy(id = "// div[@class='fL linkedDocToggleBtnSet fleft']/a")
	WebElement RadioLD;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		// We use initElements method to initialize web elements.Init is static method
		// of pagefactory class
		PageFactory.initElements(driver, this);
	}

		
	/*
	 * public void LoginToApplication(String Username, String Password) {
	 * actionClickAndSendKeys(driver, UserEmailAddressEditBox, Username); log.
	 * info("****************Username entered successfully**********************");
	 * actionClickAndSendKeys(driver, PasswordEditBox, Password); log.
	 * info("****************Password entered successfully**********************");
	 * LoginButton.click(); implicitwait(10, TimeUnit.SECONDS);
	 * log.info("Logging in to application");
	 * 
	 * // selecting iRequest card
	 * log.info("*************Selecting iRequest Card*******************");
	 * iRequestCard.click(); implicitwait(5, TimeUnit.SECONDS); }
	 */

	public void NewRequest(String ChooseRD, String ReqName, String ReqDesc) {
		log.info("**************Clicking On New Request**************");
		NewRequestBtn.click();
		implicitwait(5, TimeUnit.SECONDS);

		log.info("**************Selecting RD*****************");
		List<WebElement> ReqType = driver.findElements(By.xpath("//*[@class='frmLbl vspc dIblock bld']"));
		List<WebElement> RDName = driver.findElements(By.xpath("//*[@class='chkLbl']"));

		log.info("**************Getting Request Type Count *****************");
		int ReqTypeCount = ReqType.size();
		System.out.println("ReqTypeCount is:" + ReqTypeCount);
		for (int i = 0; i < ReqTypeCount; i++) {
			System.out.println(ReqType.get(i).getText());
		}

		log.info("**************Getting Request Definition Count *****************");
		int RDCount = RDName.size();
		System.out.println("Request Definition count is:" + RDCount);
		for (int j = 0; j < RDCount; j++) {
			System.out.println(RDName.get(j).getText());
			if (RDName.get(j).getText().contains(ChooseRD)) {
				RDName.get(j).click();

				log.info(
						"**************Clicking on OK button while selecting Request Definition to create new request *****************");
				RD_Ok.click();
				implicitwait(5, TimeUnit.SECONDS);

				ReqNameEdtBox.sendKeys(ReqName);
				ReqDescEdtBox.sendKeys(ReqDesc);
				ReqSubmitBtn.click();
				implicitwait(5, TimeUnit.SECONDS);

				SaveWFonReq.click();
				implicitwait(10, TimeUnit.SECONDS);

				// verify if new request gets created
				log.info("*************************verifying the created request******************************");
				searchRequestEtdBox.sendKeys(ReqName);
				clickouside.click();

				// List<WebElement> ReqNameInPage = driver.findElements(By.xpath("//*[@class='
				// requestName']"));
				// *[@class=' requestName'] : to get request name from current page

				String NoResult = NoResultFound.getText();
				if ((NoResult.equals("No results found"))) {

					System.out.println("No Request added!");
				} else {
					System.out.println("Request added Succesfully!");
					
				}
				// assertEquals(actual, ReqName, message);
			}

		}

	}

	public void CreateNewRD(String RDName, String PartialRDType) {

		log.info("**************Selecting Request Definition option*****************");
		SelectRequestDeftag.click();
		implicitwait(5, TimeUnit.SECONDS);
		log.info("**************Clicking on create RD Button*****************");
		CreateRDBtn.click();
		implicitwait(5, TimeUnit.SECONDS);
		log.info("**************Giving Request Definition Name*****************");
		RequestDefinitionName.sendKeys(RDName);
		// Working with auto complete
		log.info("**************Getting sze of Request Type Name*****************");
		System.out.println("Auto Suggest List ::" + RdTypeList.size());
		RequesttypeName.sendKeys(PartialRDType);
		VisibilityOfElementLocatedBy(30, RdTypeList);

		for (int i = 0; i < RdTypeList.size(); i++) {
			System.out.println(RdTypeList.get(i).getText());

			if (RdTypeList.get(i).getText().equals(PartialRDType)) {
				RdTypeList.get(i).click();
				log.info("*******Selected & Clicked on Request type********");
				break;
			}
		}
	}

	public void ConfigureFF(String SectionName, String DescName) {
		log.info("**************Enabling FF radio button*****************");
		RadioFF.click();

		log.info("**************Clicking on FF Configure Button**********");
		FFConfigureButton.click();
		implicitwait(10, TimeUnit.SECONDS);

		log.info("**************Clicking on Add Section button inside FF**********");
		InsideFFAddSectionButton.click();
		implicitwait(5, TimeUnit.SECONDS);

		log.info("**************Giving name on create section**********");
		InsideFFCreateSectionNameEtdbox.sendKeys(SectionName);

		log.info("**************Giving description on create section**********");
		InsideFFCreateSectionDescEtdbox.sendKeys(DescName);

		log.info("**************Clicking on create section save button**********");
		InsideFFCreateSectionSave.click();
		implicitwait(5, TimeUnit.SECONDS);

		log.info("**************Clicking on create section save button**********");
		SelectCustomField.click();
		implicitwait(5, TimeUnit.SECONDS);


	}
}
