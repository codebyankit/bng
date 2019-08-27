package keywordsForiRequest

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import actionbot.ActionBot
import internal.GlobalVariable

public class Page_MyApprovalPO implements S2P_OR, S2P_OR_MyApprovals_ReviewPage, S2P_OR_Page_Edit_Request{

	public static Logger logger=Logger.getLogger(Page_MyApprovalPO.class)

	@Keyword
	public static boolean getRequestNumbersToApprove(WebDriver driver){
		boolean status = false
		try{
			WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			//List<WebElement> ApproveList= WebUI.findWebElements(findTestObject(GetApproveLinks),GlobalVariable.DEFAULT_LOW_WAIT)
			List<WebElement> ApproveList=driver.findElements(By.xpath("//dew-row//dew-col//dew-actions-menu//*[text()='Approve']"))
			println (ApproveList)
			println ("size is:" +ApproveList.size())
			for (int i=1;i<=20;i){
				if (!ApproveList.isEmpty()){
					WebElement first=ApproveList.get(0)
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", first);
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					first.click()
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					
 ***********************************Code of approving the request******************************************** 
					String capturedReqNum=WebUI.getText(findTestObject(Popup_extractRequestNumber))
					ActionBot.sendKeys(driver, ApproveRequestpopup_comment, 'Approving request')
					ActionBot.click(Popup_ApproveButton)
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					String capturedtext=WebUI.getText(findTestObject(RequestApprovedValidationMsg))
					if ((capturedtext!='null') && ('The request has been approved successfully.'.equals(capturedtext))){
						logger.info("*****************Request Approved successfully*****************")
						println("Approved Request number is:" +capturedReqNum)
						status=true
						ActionBot.click(ApprovedRequest_Done)
						WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
						
					}
					else{
						logger.info("************Unable to approve the request*************")
						status=false
					}
					break
				}

				else {
					++i;
					WebUI.scrollToElement(findTestObject(MyApprovalPageNavigationNumberChange), 10)
					ActionBot.click(MyApprovalPageNavigationNumberChange)
					ActionBot.sendKeys(driver, MyApprovalPageNavigationNumberChange, i.toString())
					customKeywords.Enterkey.enter()
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					//ApproveList= WebUI.findWebElements(findTestObject(GetApproveLinks),GlobalVariable.DEFAULT_LOW_WAIT)
					ApproveList=driver.findElements(By.xpath("//dew-row//dew-col//dew-actions-menu//*[text()='Approve']"))
					int len=ApproveList.size()
					println ("List length is:" +len)
				}
			}
		

		}

		catch(Exception e){
			logger.info("************************catched exception*******************************")
			status=false
			throw e
		}
		return status
	}


	@Keyword
	public static boolean getRequestNumbersToReject(WebDriver driver){
		boolean status = false
		try{
			WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			List<WebElement> rejectList=driver.findElements(By.xpath("//dew-row//dew-col//dew-actions-menu//*[text()='Reject']"))

			for (int i=1;i<=20;i){
				if (!rejectList.isEmpty()){
					WebElement first=rejectList.get(0)
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", first);
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					first.click()
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					
 ***********************************Code of rejecting the request******************************************** 
					String capturedReqNum=WebUI.getText(findTestObject(Popup_extractRequestNumber))
					ActionBot.sendKeys(driver, ApproveRequestpopup_comment, 'Rejecting request')
					ActionBot.click(Popup_RejectButton)
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					String capturedtext=WebUI.getText(findTestObject(RequestApprovedValidationMsg))

					if ((capturedtext!='null') && ('The request has been rejected successfully.'.equals(capturedtext))){
						logger.info("*****************Request rejected successfully*****************")
						println("Rejected Request number is:" +capturedReqNum)
						ActionBot.click(ApprovedRequest_Done)
						WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
						status=true
					}
					else{
						logger.info("************Unable to reject the request*************")
						status=false
					}
					break
				}

				else {
					++i;
					WebUI.scrollToElement(findTestObject(MyApprovalPageNavigationNumberChange), 10)
					ActionBot.click(MyApprovalPageNavigationNumberChange)
					ActionBot.sendKeys(driver, MyApprovalPageNavigationNumberChange, i.toString())
					customKeywords.Enterkey.enter()
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					rejectList=driver.findElements(By.xpath("//dew-row//dew-col//dew-actions-menu//*[text()='Reject']"))
					int len=rejectList.size()
					println ("List length is:" +len)
				}

			}
			
		}

		catch(Exception e){
			logger.info("************Unable to approve the request*************")
			status=false
			throw e
		}
		return status
	}

	@Keyword
	public static boolean ToCheckApproveLinkIsClickable(WebDriver driver){
		boolean status = false
		try{
			boolean flag=WebUI.verifyElementClickable(findTestObject('Object Repository/S2P/ListingPages/NavigateToMyApprovalsFromHomePage'))
			if (flag==true){
				logger.info("********************My approval widget is clickable****************")
			}
			status=true
		}
		catch(Exception e){
			logger.info("*******************************My approval widget is not clickable************************")
			status=false
			throw e
		}
		return status
	}
	
	 @Keyword
	 public static boolean ToCheck3DotIsClickable(WebDriver driver){
	 boolean status = false
	 try{
	 boolean flag=WebUI.verifyElementClickable(findTestObject(getAll3Dots))
	 if (flag==true){
	 logger.info("********************My approval: 3 Dot is clickable****************")
	 }
	 status=true
	 }
	 catch(Exception e){
	 logger.info("*******************************My approval: 3 Dot is not clickable************************")
	 status=false
	 throw e
	 }
	 return status
	 }
	 



	@Keyword
	public static boolean Click3DotsMyApproval(WebDriver driver){
		boolean status = false
		try{
			WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			List<WebElement> dotList=driver.findElements(By.xpath("//dew-row//dew-col[1]//dew-actions-menu[1]//div[1]//div[2]//div[1]//a[1]//dew-btn[1]//button[1]"))

			println (dotList)
			println ("size is:" +dotList.size())
			for (int i=1;i<=20;i){
				if (!dotList.isEmpty()){
					WebElement first=dotList.get(0)
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", first);
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					first.click()
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

					break
				}
				else {
					++i;
					WebUI.scrollToElement(findTestObject(MyApprovalPageNavigationNumberChange), 10)
					ActionBot.click(MyApprovalPageNavigationNumberChange)
					ActionBot.sendKeys(driver, MyApprovalPageNavigationNumberChange, i.toString())
					customKeywords.Enterkey.enter()
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					//ApproveList= WebUI.findWebElements(findTestObject(GetApproveLinks),GlobalVariable.DEFAULT_LOW_WAIT)
					dotList=driver.findElements(By.xpath("//dew-row//dew-col[1]//dew-actions-menu[1]//div[1]//div[2]//div[1]//a[1]//dew-btn[1]//button[1]"))
					int len=dotList.size()
					println ("List length is:" +len)
				}

			}

			status=true

		}

		catch(Exception e){
			logger.info("************************catched exception*******************************")
			status=false
			throw e
		}
		return status
	}



	 *************************************************** Delegate******************************************************* 
	@Keyword
	public static boolean Click_Action_Delegate(WebDriver driver){
		boolean status = false
		try{
			ActionBot.click(Action_Delegate_Click)
			status=true
		}

		catch(Exception e){
			logger.info("************************Unable to click Delegate option*******************************")
			//e.printStackTrace()
			status=false
			throw e

		}
		return status
	}


	@Keyword
	public static boolean Delegate_RequestPopup(WebDriver driver){
		boolean status = false
		try{
			ActionBot.sendKeys(driver, DelegateApprovalTo_SearchBox, GlobalVariable.Approver1_SingleUser)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			//WebUI.waitForElementVisible(Select_SearchedDelegateTo, GlobalVariable.DEFAULT_MEDIUM_WAIT)
			ActionBot.click(Select_SearchedDelegateTo)
			ActionBot.sendKeys(driver, Popup_Delegate_ReasonForDelegate_SearchBox, "Delegating the request through automation")

			ActionBot.click(Popup_Delegate_Attachment_Link)
			 String userDir =System.getProperty('user.dir')
			 String filePath = (userDir + '\\') + 'Data Files\\App Test Data\\FilesToUpload\\eCatalogue_User_Guides.pdf'
			 customKeywords.Uploadfile.uploadFile(findTestObject(Popup_Delegate_Attachment_Link), filePath)
			 WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			 customKeywords.Enterkey.enter()
			 WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

			ActionBot.click(Popup_Delegate_DelegateButton)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

			String successMsg=WebUI.getText(findTestObject(Delegate_PopUp_Success_Validation))
			if ((successMsg!='null') && ('The request has been delegated successfully.').equals(successMsg)) {

				logger.info('**********Request delegated successfully**********')
				ActionBot.click(Delegate_popUp_Success_Done)
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
				status=true
			}

		}

		catch(Exception e){
			logger.info("************************Unable to delegate a request*******************************")
			status=false
			throw e
		}
		return status
	}

	 ***********************************************Review******************************************************************* 
	 *****Edit Request******  
	@Keyword
	public static boolean MyApproval_Review_EditRequest(WebDriver driver){
		boolean status=false
		try{
			ActionBot.click(Action_Review_Click)
			WebUI.delay(GlobalVariable.DEFAULT_HIGH_WAIT)

			WebElement refreshButton=driver.findElement(By.xpath("//dew-section-body[@class='px-5 dew-section-body']//dew-workflow"))
			//WebElement refreshButton=driver.findElement(By.xpath("//span[contains(text(),'Refresh')]"))
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", refreshButton);
			String url=driver.getCurrentUrl()

			if(url.contains("edit-request")){
				//if (((url!='null') && ('edit-request')).contains(url)){
				logger.info(" *****Successfully navigated to Review page****** ")
				status=true
			}
		}
		catch(Exception e){
			logger.info("************************Unable to click on Review*******************************")
			status=false
			throw e
		}
		return status
	}

	 *****View Request****** 

	@Keyword
	public static boolean MyApproval_ReviewRequest(WebDriver driver){
		boolean status=false
		try{
			ActionBot.click(Action_Review_Click)
			WebUI.delay(GlobalVariable.DEFAULT_HIGH_WAIT)

			WebElement refreshButton=driver.findElement(By.xpath("//dew-section-body[@class='px-5 dew-section-body']//dew-workflow"))
			//WebElement refreshButton=driver.findElement(By.xpath("//span[contains(text(),'Refresh')]"))
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", refreshButton);
			String url=driver.getCurrentUrl()

			if(url.contains("view-request") || url.contains("edit-request")){
				logger.info(" *****Successfully navigated to Review page****** ")
				status=true
			}
		}
		catch(Exception e){
			logger.info("************************Unable to click on Review*******************************")
			status=false
			throw e
		}
		return status
	}




	@Keyword
	public static boolean MyApproval_Review_Approve(WebDriver driver){
		boolean status=false
		try{
			ActionBot.click(Review_ApproveLink)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			String capturedReqNum=WebUI.getText(findTestObject(Popup_extractRequestNumber))
			ActionBot.sendKeys(driver, ApproveRequestpopup_comment, 'Approving request')
			ActionBot.click(Popup_ApproveButton)
			WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			String capturedtext=WebUI.getText(findTestObject(RequestApprovedValidationMsg))
			if ((capturedtext!='null') && ('The request has been approved successfully.'.equals(capturedtext))){
				logger.info("*****************Request Approved successfully*****************")
				println("Approved Request number is:" +capturedReqNum)
				ActionBot.click(ApprovedRequest_Done)
				WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
				status=true
			}
		}
		catch(Exception e){
			logger.info("************************Unable to approve a request from review page*******************************")
			status=false
			throw e
		}
		return status
	}



	@Keyword
	public static boolean MyApproval_Review_Reject(WebDriver driver){
		boolean status=false
		try{

			ActionBot.click(Review_RejectLink)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

			String capturedReqNum=WebUI.getText(findTestObject(Popup_extractRequestNumber))
			ActionBot.sendKeys(driver, ApproveRequestpopup_comment, 'Rejecting request')
			ActionBot.click(Popup_RejectButton)
			WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			String capturedtext=WebUI.getText(findTestObject(RequestApprovedValidationMsg))
			if ((capturedtext!='null') && ('The request has been approved successfully.'.equals(capturedtext))){
				logger.info("*****************Request Approved successfully*****************")
				println("Approved Request number is:" +capturedReqNum)
				ActionBot.click(ApprovedRequest_Done)
				WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			}
			status=true
		}
		catch(Exception e){
			logger.info("************************Unable to reject a request from review page*******************************")
			status=false
			throw e
		}
		return status
	}



	@Keyword
	public static boolean MyApproval_Review_Delegate(WebDriver driver){
		boolean status=false
		try{

			ActionBot.click(Review_DelegateLink)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

			String successMsg=WebUI.getText(findTestObject(Delegate_PopUp_Success_Validation))
			if ((successMsg!='null') && ('The request has been delegated successfully.').equals(successMsg)) {
				logger.info('**********Request delegated successfully**********')
				ActionBot.click(Delegate_popUp_Success_Done)
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			}
			status=true
		}
		catch(Exception e){
			logger.info("************************Unable to deleate a request from review page*******************************")
			status=false
			throw e

		}
		return status
	}

	 *****************************************Review Clickable********************************************************************* 

	@Keyword
	public static boolean MyApproval_Review_ApproveLink_Clickable(WebDriver driver){
		boolean status=false
		try{
			WebUI.waitForElementClickable(findTestObject(Review_ApproveLink), GlobalVariable.DEFAULT_MEDIUM_WAIT)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			status=true

		}
		catch(Exception e){
			logger.info("************************Unable to click on Review approve link*******************************")
			status=false
			throw e
		}
		return status
	}

	@Keyword
	public static boolean MyApproval_Review_RejectLink_Clickable(WebDriver driver){
		boolean status=false
		try{
			WebUI.waitForElementClickable(findTestObject(Review_RejectLink), GlobalVariable.DEFAULT_MEDIUM_WAIT)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			status=true

		}
		catch(Exception e){
			logger.info("************************Unable to click on Review reject link*******************************")
			status=false
			throw e
		}
		return status
	}

	@Keyword
	public static boolean MyApproval_Review_DelegateLink_Clickable(WebDriver driver){
		boolean status=false
		try{
			WebUI.waitForElementClickable(findTestObject(Review_DelegateLink), GlobalVariable.DEFAULT_MEDIUM_WAIT)
			WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			logger.info("************************Able to click Review delegate link*******************************")
			status=true
		}
		catch(Exception e){
			logger.info("************************Unable to click on Review delegate link*******************************")
			status=false
			throw e

		}
		return status
	}
	
	 *************************************************Attachment while approval****************************************************** 
	
	@Keyword
	
	//WebUI.uploadFile(findTestObject('input_browse'), 'D:\\test-photo.png')
	
	public static boolean MyApproval_AddAttachment(WebDriver driver){
		boolean status=false;
		try{
			ActionBot.click(ApproveRequestpopup_Attachment)
			WebUI.uploadFile(findTestObject(ApproveRequestpopup_Attachment), 'D:\\RentReceipt.doc')
		}
		
		catch(Exception e){
			logger.info("************************Unable to attach an attachment*******************************")
			status=false
			throw e
		}
		
		return status
	}
	
	  ***********************************************************ReviewPage: Links**************************************************************************** 
	@Keyword
	public static boolean Review_MyApprovalLink(WebDriver driver){
		boolean status=false;
		try{
			ActionBot.click(Review_ApproveLink)
			//WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			logger.info("Clicked on approve link")
			
			status=true
		}
		
		catch(Exception e){
			logger.info("************************Unable to click on approve link*******************************")
			status=false;
			throw e
		}
		
		return status
	}
	
	
	@Keyword
	public static boolean Review_RejectLink(WebDriver driver){
		boolean status=false;
		try{
			ActionBot.click(Review_RejectLink)
			//WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			logger.info("Clicked on reject link")
			
			status=true
		}
		
		catch(Exception e){
			logger.info("************************Unable to click on reject link*******************************")
			status=false
			throw e
		}
		
		return status
	}
	
	
	@Keyword
	public static boolean Review_DelegateLink(WebDriver driver){
		boolean status=false;
		try{
			ActionBot.click(Review_DelegateLink)
			//WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
			logger.info("Clicked on delegate link")
			
			status=true
		}
		
		catch(Exception e){
			logger.info("************************Unable to click on delegate link*******************************")
			status=false
			throw e
		}
		
		return status
	}
	
	*******************************************************************************************************************************************************************************************************************
	
	import java.util.List;

	public class Discovery {
	      
	      public static void main(String[] args) {
	            
	            DiscoveryP1 p1 = new DiscoveryP1();
	            List<Deck> list = p1.addFavoites();
	            DiscoveryP2 p2 = new DiscoveryP2();
	            p2.verifyFavoites(list);
	            
	      }

	}


	import java.util.ArrayList;
	import java.util.List;

	public class DiscoveryP1 {
	      
	      public List<Deck> addFavoites(){
	            Deck d1 = new Deck("Man vs Wild","Desc");
	            Deck d2 = new Deck("Engg Marvels","Desc2");
	            List<Deck> list = new ArrayList<>();
	            list.add(d1);
	            list.add(d2);
	            return list;
	      }

	}


	import java.util.ArrayList;
	import java.util.List;

	public class DiscoveryP2 {

	      public boolean verifyFavoites(List<Deck> list){
	            List<Deck> pageDetails = new ArrayList<>();
	            Deck d1 = new Deck("Man vs Wild","Desc");
	            Deck d2 = new Deck("Engg Marvels","Desc2");
	            pageDetails.add(d1);
	            pageDetails.add(d2);
	            return pageDetails.containsAll(list);
	      }
	}





	
	

}
