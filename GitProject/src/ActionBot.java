
public class ActionBot {
	
	
	//package actionbot
/*	import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

	import java.awt.Robot
	import java.awt.Toolkit
	import java.awt.datatransfer.StringSelection
	import java.awt.event.KeyEvent
	import java.util.concurrent.TimeUnit

	import org.apache.log4j.Logger
	import org.openqa.selenium.Alert
	import org.openqa.selenium.By
	import org.openqa.selenium.ElementNotSelectableException
	import org.openqa.selenium.ElementNotVisibleException
	import org.openqa.selenium.JavascriptExecutor
	import org.openqa.selenium.Keys
	import org.openqa.selenium.StaleElementReferenceException
	import org.openqa.selenium.WebDriver
	import org.openqa.selenium.WebElement
	import org.openqa.selenium.interactions.Actions
	import org.openqa.selenium.support.ui.ExpectedConditions
	import org.openqa.selenium.support.ui.Select
	import org.openqa.selenium.support.ui.WebDriverWait

	import com.kms.katalon.core.annotation.Keyword
	import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
	import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

	import constant_package.iConstants
	import internal.GlobalVariable
	import screenshot.Screenshot*/

	public class ActionBot {
		public static Logger logger = Logger.getLogger(ActionBot.class)


		public static void click(String elementPath) {
			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
						WebUI.click(findTestObject(elementPath))
					}
				}
			}
			catch(Exception e) {
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
							WebUI.click(findTestObject(elementPath))
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace()
				}
			}
		}

		public static void doubleClick(String elementPath) {
			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
						WebUI.doubleClick(findTestObject(elementPath))
					}
				}
			}
			catch(Exception e) {
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
							WebUI.doubleClick(findTestObject(elementPath))
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace()
				}
			}
		}

		public static void clickElementByXpath(String path,WebDriver driver) {
			WebElement element=null
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
				element=driver.findElement(By.xpath(path))
				if(element.isDisplayed()) {
					if(element.isEnabled()) {
						element.click()
					}
				}
			}
			catch(Exception e) {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
					if(element.isDisplayed()) {
						if(element.isEnabled()) {
							element.click()
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace()
				}
			}
		}



		/**
		 * @author 
		 * @description : Click on element by specifying Object repository path
		 * @param : driver, Object repo path
		 *
		 */

		@Keyword
		public static void click(WebDriver driver,String elementPath) throws Exception {
			logger.info("Performing Click on "+elementPath)
			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
					logger.info(elementPath+" is visible.")

					if(isEnabled(driver, elementPath)) {

						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {

							WebUI.click(findTestObject(elementPath))
							logger.info("Clicked on "+elementPath)
						}
						else {
							logger.error(elementPath +"Element not Clickable in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.info("Waiting for element to be enabled...!")
						defaultSleep(driver, 15)

						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {

							WebUI.click(findTestObject(elementPath))
							logger.info("Clicked on "+elementPath)
						}
						else {
							logger.error(elementPath +"Element not Clickable in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
				}
				else {
					logger.error(elementPath +"Element not Visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}

			catch(ElementNotSelectableException e2) {
				try {
					logger.info("Element was not clickable in 1st attempt. Performing click again on element.")

					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
						WebUI.click(findTestObject(elementPath))
						logger.info("Clicking on "+elementPath)
					}
					else {
						logger.error(elementPath+" Element not clickable in 2nd attempt")
						throw new ElementNotSelectableException("Unable to click on element in 2nd Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}
			catch(ElementNotVisibleException e) {
				try {
					logger.info("Element was not visible in 1st attempt. Searching element again.")

					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)) {
							logger.info("Clicking on "+elementPath)
							WebUI.click(findTestObject(elementPath))
						}
						else {
							logger.error(elementPath+" Element not clickable in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.error(elementPath+" Element not visible in 2nd attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd Attempt")
					}
				}

				catch(Exception e1) {
					logger.error("Exception Ouccured")
					throw new Exception(e1)
				}
			}

			catch(Exception e6) {
				try {
					logger.info("Exception "+e6.getMessage()+" occurred. Searching element again.")

					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)) {
							logger.info("Clicking on "+elementPath)
							WebUI.click(findTestObject(elementPath))
						}
						else {
							logger.error(elementPath+" Element not clickable in Last attempt")
							throw new ElementNotSelectableException("Unable to click on element in Last Attempt")
						}
					}
					else {
						logger.error(elementPath+" Element not visible on Last attempt")
						throw new ElementNotVisibleException("Unable to find element in Last Attempt")
					}
				}

				catch(Exception e1) {
					logger.error("Exception Ouccured")
					throw new Exception(e1)
				}
			}
		}

		/**
		 * @author 
		 * @description : Click on element by specifying xPath
		 * @param : driver,xPath
		 * @return text
		 */
		@Keyword
		public static void clickByXpath(WebDriver driver,String xPath) throws Exception {
			WebElement element = null
			WebDriverWait wait = new WebDriverWait(driver, 20)
			logger.info("Performing Click on "+xPath)
			try {
				element = findElementByXpath(driver, xPath)

				if(element!=null) {
					logger.info(xPath+" is visible.")

					if(waitUntilClickable(driver,element)) {
						element.click()
						logger.info("Clicked on "+xPath)
					}
					else {
						logger.error(xPath +"Element not Clickable in 1st attempt")
						throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
					}
				}
				else {
					logger.error(xPath +"Element not Visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}
			catch(ElementNotSelectableException e2) {
				try {
					logger.info("Element was not clickable in 1st attempt. Peforming click again on element.")

					element = findElement(driver, xPath)
					if(waitUntilClickable(driver,element)) {
						element.click()
						logger.info("Clicking on "+xPath)
					}
					else {
						logger.error(xPath+" Element not clickable in 2nd attempt")
						throw new ElementNotSelectableException("Unable to click on element in 2nd Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}
			catch(ElementNotVisibleException e) {
				try {

					logger.info("Element was not visible in 1st attempt. Searching element again.")

					element = findElement(driver, xPath)
					if(wait.until(ExpectedConditions.visibilityOf(element))) {
						if(waitUntilClickable(driver,element)) {
							element.click()
							logger.info("Clicking on "+xPath)
						}
						else {
							logger.error(xPath+" Element not clickable in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.error(xPath+" Element not visible in 2nd attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd Attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Ouccured")
					throw new Exception(e1)
				}
			}
			catch(Exception e4) {
				try {
					logger.info("Exception "+e4.getMessage()+" occurred. Searching element again.")

					element = findElement(driver, xPath)
					if(wait.until(ExpectedConditions.visibilityOf(element))) {
						if(waitUntilClickable(driver,element)) {
							element.click()
							logger.info("Clicking on "+xPath)
						}
						else {
							logger.error(xPath+" Element not clickable in Last attempt")
							throw new ElementNotSelectableException("Unable to click on element in Last Attempt")
						}
					}
					else {
						logger.error(xPath+" Element not visible in Last attempt")
						throw new ElementNotVisibleException("Unable to find element in Last Attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Ouccured")
					throw new Exception(e1)
				}
			}
		}

		@Keyword
		public static boolean waitUntilClickable(WebDriver driver,WebElement element) throws Exception {
			boolean status = false
			WebDriverWait wait = new WebDriverWait(driver, 20)
			try {
				if(wait.until(ExpectedConditions.elementToBeClickable(element))) {
					status = true
					logger.info("Element is clickable.")
				}
			}
			catch(Exception e) {
				try {
					if(wait.until(ExpectedConditions.elementToBeClickable(element))) {
						status = true
						logger.info("Element is clickable.")
					}
				}
				catch(Exception e1) {
					logger.error("Element is not clickable.")
					throw new Exception(e1)
				}
			}
			return status
		}

		/**
		 * @author snehal.yadav
		 * @description : Waiting for element to be visible by specifying Object repository path
		 * @param : driver, Object repo path
		 * @return text
		 */
		@Keyword
		public static WebElement waitUntilVisible(WebDriver driver,String elementPath) throws Exception {
			WebElement element = null

			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)) {
					element = WebUIAbstractKeyword.findWebElement(findTestObject(elementPath))
					if(element!=null) {
						logger.info("Element "+elementPath+" is Visible.")
					}
				}
				else {
					logger.error("Element "+element+" not Visible.")
					throw new ElementNotVisibleException("Unable to find element in 1st attempt.")
				}
			}
			catch(ElementNotVisibleException e1) {
				logger.info("Checking element visibility again..!")
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)) {
						element = WebUIAbstractKeyword.findWebElement(elementPath)
						if(element!=null) {
							logger.info("Element "+elementPath+" is Visible.")
						}
					}
					else {
						logger.error("Element "+element+" not Visible.")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt.")
					}
				}

				catch(Exception e2) {
					logger.error("Exception Occurred ")
					throw e2
				}
			}
			catch(Exception e) {
				logger.info("Checking element visibility again..!")
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)) {
						element = WebUIAbstractKeyword.findWebElement(elementPath)
						if(element!=null) {
							logger.info("Element "+elementPath+" is Visible.")
						}
					}
					else {
						logger.error("Element "+element+" not Visible.")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt.")
					}
				}

				catch(Exception e3) {
					logger.error("Exception Occurred ")
					throw e3
				}
			}
			return element
		}


		/**
		 * @author snehal.yadav
		 * @description : Waiting for element to be visible by specifying element xPath
		 * @param : driver, xPath
		 * @return WebElement
		 */
		@Keyword
		public static WebElement waitUntilVisibleByXpath(WebDriver driver,String xPath) throws Exception {
			WebElement element = null
			WebDriverWait wait = new WebDriverWait(driver, 20)
			try {
				element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xPath))))
				if(element!=null) {
					logger.info("Element "+xPath+" is Visible.")
				}
				else {
					logger.error("Element "+element+" not Visible.")
					throw new ElementNotVisibleException("Unable to find element in 1st attempt.")
				}
			}
			catch(ElementNotVisibleException e1) {
				logger.info("Checking element visibility again..!")
				try {
					element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xPath))))
					if(element!=null) {
						logger.info("Element "+xPath+" is Visible.")
					}
					else {
						logger.error("Element "+element+" not Visible.")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt.")
					}
				}
				catch(Exception e2) {
					logger.error("Exception Occurred ")
					throw e2
				}
			}
			catch(Exception e) {
				logger.info("Checking element visibility again..!")
				try {
					element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xPath))))
					if(element!=null) {
						logger.info("Element "+xPath+" is Visible.")
					}
					else {
						logger.error("Element "+element+" not Visible.")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt.")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occurred ")
					throw e3
				}
			}
			return element
		}

		@Keyword
		public static void doubleClick(WebDriver driver,String elementPath) {
			logger.info("Performing Double click on "+elementPath)

			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
					logger.info(elementPath+" is visible.")
					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {

						WebUI.doubleClick(findTestObject(elementPath))
						logger.info("Double Clicking on "+elementPath)
					}
					else {
						logger.error("Double Click failed on"+elementPath+" in 1st attempt")
						throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
					}
				}
				else {
					logger.error(elementPath +"Element not Visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}
			catch(ElementNotSelectableException e2) {
				try {
					logger.info("Element was not selectable in 1st attempt. Performing click again on element.")

					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						WebUI.doubleClick(findTestObject(elementPath))
						logger.info("Double Clicking on "+elementPath)
					}
					else {
						logger.error(elementPath+" Element not clickable in 2nd attempt")
						throw new ElementNotSelectableException("Unable to double click on element in 2nd Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}
			catch(ElementNotVisibleException e) {
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {

							WebUI.doubleClick(findTestObject(elementPath))
							logger.info("Double Clicking on "+elementPath)
						}
						else {
							logger.error("Double click failed on "+elementPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to double click on element in 1st Attempt")
						}
					}
					else {
						logger.error(elementPath+" Element not visible in 2nd attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd Attempt")
					}
				}

				catch(Exception e1) {
					logger.error("Exception Ouccured")
					throw new Exception(e1)
				}
			}
			catch(Exception e4) {

				try {
					logger.info("Exception "+e4.getMessage()+" occurred. Performing Double click again on element.")

					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
						logger.info(elementPath+" is visible.")
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {

							WebUI.doubleClick(findTestObject(elementPath))
							logger.info("Double Clicking on "+elementPath)
						}
						else {
							logger.error("Double Click failed on"+elementPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.error(elementPath +"Element not Visible in 2nd attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd Attempt")
					}
				}
				catch(Exception e5) {
					logger.error("Exception occurred.")
					throw e5
				}
			}
		}


		/**
		 * @author snehal.yadav
		 * @description : Perform sendkeys on element by specifying Object repository path
		 * @param : driver, Object repo path
		 *
		 */
		@Keyword
		public static void sendKeys(WebDriver driver,String elementPath, String text) throws Exception {
			logger.info("Performing Sendkeys on "+elementPath)

			try {

				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						WebUI.clearText(findTestObject(elementPath))
						WebUI.setText(findTestObject(elementPath),text)
						logger.info("Value entered in field "+elementPath+" is "+text)
					}
					else {
						logger.error("Unable to enter text in "+elementPath+" in 1st attempt")
						throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
					}
				}
				else {
					logger.error(elementPath+" Element not visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}
			catch(ElementNotSelectableException e2) {
				try {
					logger.info("Element was not clickable in 1st attempt. Performing sendkeys again.")

					if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						WebUI.clearText(findTestObject(elementPath))
						WebUI.setText(findTestObject(elementPath),text)
						logger.info("Value entered in field "+elementPath+" is "+text)
					}
					else {
						logger.error("Unable to enter text in "+elementPath+" in 2nd attempt")
						throw new ElementNotSelectableException("Unable to click on element in 2nd Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}
			catch(Exception e) {
				try {
					logger.info("Exception "+e.getMessage()+" occurred. Performing sendkeys again.")

					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						if(WebUI.waitForElementClickable(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
							WebUI.clearText(findTestObject(elementPath))
							WebUI.setText(findTestObject(elementPath),text)
							logger.info("Value entered in field "+elementPath+" is "+text)
						}
						else {
							logger.error("Unable to enter text in "+elementPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.error("Unable to find "+elementPath+" in 2nd Attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Occurred ")
					throw new Exception(e1)
				}
			}
		}

		/**
		 * @author snehal.yadav
		 * @description : Perform Sendkeys on element by specifying xPath
		 * @param : driver,xPath
		 *
		 */
		@Keyword
		public static void sendKeysByXPath(WebDriver driver,String xPath, String text) throws Exception {
			WebElement element = null
			WebDriverWait wait = new WebDriverWait(driver,20)

			logger.info("Performing Sendkeys on "+xPath)

			try {
				element = findElementByXpath(driver, xPath)
				if(element!=null) {
					logger.info("Element is visible.")
					if(waitUntilClickable(driver,element)) {
						element.clear()
						element.sendKeys(text)
						logger.info("Value entered in field "+xPath+" is "+text)
					}
					else {
						logger.error("Unable to enter text in "+xPath+" in 1st attempt")
						throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
					}
				}
				else {
					logger.error(xPath+" Element not visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}
			catch(ElementNotSelectableException e2) {
				logger.info("Element was not selectable. Selecting element again..!")
				try {
					element = findElementByXpath(driver, xPath)
					if(waitUntilClickable(driver,element)) {
						element.clear()
						element.sendKeys(text)
						logger.info("Value entered in field "+xPath+" is "+text)
					}
					else {
						logger.error("Unable to enter text in "+xPath+" in 2nd attempt")
						throw new ElementNotSelectableException("Unable to click on element in 2nd Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}
			catch(Exception e) {
				logger.info("Element was not visible. Searching element again..!")
				try {
					element = findElementByXpath(driver, xPath)
					if(element!=null) {
						logger.info("Element is visible.")
						if(waitUntilClickable(driver,element)) {
							element.clear()
							element.sendKeys(text)
							logger.info("Value entered in field "+xPath+" is "+text)
						}
						else {
							logger.error("Unable to enter text in "+xPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to click on element in 1st Attempt")
						}
					}
					else {
						logger.error("Unable to find "+xPath+" in 2nd Attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Occurred ")
					throw new Exception(e1)
				}
			}
		}

		@Keyword
		public static void clear(WebDriver driver,String elementPath) throws Exception {
			logger.info("Performing Clear on "+elementPath)

			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
					logger.info(elementPath+" is visible.")
					if(WebUI.verifyElementClickable(findTestObject(elementPath))==true) {
						WebUI.clearText(findTestObject(elementPath))
						logger.info("Cleared field "+elementPath)
					}
					else {
						logger.error("Unable to clear text in "+elementPath+" in 1st attempt")
						throw new ElementNotSelectableException("Unable to clear on element in 1st Attempt")
					}
				}
				else {
					logger.error(elementPath+" Element not visible in 1st attempt")
					throw new ElementNotVisibleException("Unable to find element in 1st Attempt")
				}
			}
			catch(ElementNotVisibleException e) {
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						if(WebUI.verifyElementClickable(findTestObject(elementPath))==true) {
							WebUI.clearText(findTestObject(elementPath))
							logger.info("Cleared field "+elementPath)
						}
						else {
							logger.error("Unable to clear text in "+elementPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to clear on element in 1st Attempt. Invalid element State.")
						}
					}
					else {
						logger.error("Unable to find "+elementPath+" in 2nd Attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Occurred ")
					throw new Exception(e1)
				}
			}
			catch(ElementNotSelectableException e2) {
				try {
					if(WebUI.verifyElementClickable(findTestObject(elementPath))==true) {
						WebUI.clearText(findTestObject(elementPath))
						logger.info("Cleared field "+elementPath)
					}
					else {
						logger.error("Unable to clear text in "+elementPath+" in 1st attempt")
						throw new ElementNotSelectableException("Unable to clear on element in 1st Attempt")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occurred.")
					throw new Exception(e3)
				}
			}
			catch(Exception e4) {
				logger.error("Clearing Field again..!")
				try {
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_LOW_WAIT)==true) {
						if(WebUI.verifyElementClickable(findTestObject(elementPath))==true) {
							WebUI.clearText(findTestObject(elementPath))
							logger.info("Cleared field "+elementPath)
						}
						else {
							logger.error("Unable to clear text in "+elementPath+" in 1st attempt")
							throw new ElementNotSelectableException("Unable to clear on element in 1st Attempt. Invalid element State.")
						}
					}
					else {
						logger.error("Unable to find "+elementPath+" in 2nd Attempt")
						throw new ElementNotVisibleException("Unable to find element in 2nd attempt")
					}
				}
				catch(Exception e5) {
					logger.error("Exception Occurred ")
					throw new Exception(e5)
				}
			}
		}

		/**
		 * @author snehal.yadav
		 * @description : Fetching element text by specifying Object Repository Path
		 * @param : driver, Object Repo path
		 * @return text
		 */
		@Keyword
		public static String getText(WebDriver driver,String elementPath) throws Exception {
			logger.info("Fetching text within "+elementPath)
			String value=''
			WebElement element = null

			try {
				element = waitUntilVisible(driver, elementPath)
				if(element!=null) {
					logger.info(elementPath+" is visible.")
					if(waitUntilClickable(driver,element)) {
						logger.info(elementPath+" is clickable.")
						value= element.getText()
						logger.info("Text is :"+value)
					}

					else {
						logger.error("Element is not clickable")
						throw new ElementNotSelectableException("Unable to click on element")
					}
				}
				else {
					logger.error(elementPath+" is not visible")
					throw new NoSuchElementException("Unable to find element in 1st attempt")
				}
			}

			catch(Exception e2) {

				try {
					element = waitUntilVisible(driver, elementPath)
					if(element!=null) {
						logger.info(elementPath+" is visible.")
						if(waitUntilClickable(driver,element)) {
							logger.info(elementPath+" is visible.")
							value= element.getText()
							logger.info("Text is :"+value)
						}

						else {
							logger.error("Element is not clickable")
							throw new ElementNotSelectableException("Unable to click on element")
						}
					}
					else {
						logger.error(elementPath+" is not visible")
						throw new NoSuchElementException("Unable to find element in 1st attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Occured")
					throw new Exception(e1)
				}
			}

			return value
		}

		/**
		 *  @author snehal.yadav
		 * @description : Fetching element text by specifying Object Repository Path
		 * @param : driver, Object Repo path
		 * @return text
		 */ 
		@Keyword
		public static String getTextWithinElement(WebDriver driver,String elementPath) throws Exception {
			logger.info("Fetching text within "+elementPath)
			String value=null
			WebDriverWait wait = new WebDriverWait(driver,40)
			WebElement element = null
			try {
				element = waitUntilVisible(driver, elementPath)
				if(element!=null) {
					logger.info(elementPath+" is visible.")
					if(waitUntilClickable(driver,element)!=null) {
						logger.info(elementPath+" is clickable.")
						value= element.getAttribute("value")
						logger.info("Text is :"+value)
					}
					else {
						logger.error("Element is not clickable")
						throw new ElementNotSelectableException("Unable to click on element")
					}
				}
				else {
					logger.error(elementPath+" is not visible")
					throw new NoSuchElementException("Unable to find element in 1st attempt")
				}
			}
			catch(Exception e) {
				try {
					element = waitUntilVisible(driver, elementPath)
					if(element!=null) {
						logger.info(elementPath+" is visible.")
						if(waitUntilClickable(driver,element)!=null) {
							logger.info(elementPath+" is clickable.")
							value= element.getAttribute("value")
							logger.info("Text is :"+value)
						}

						else {
							logger.error("Element is not clickable")
							throw new ElementNotSelectableException("Unable to click on element")
						}
					}
					else {
						logger.error(elementPath+" is not visible")
						throw new NoSuchElementException("Unable to find element in 1st attempt")
					}
				}
				catch(Exception e1) {
					throw e1
				}
			}

			return value
		}

		/**
		 * @author snehal.yadav
		 * @description : Fetching element text by specifying element xPath
		 * @param : driver, xPath
		 * @return text
		 */
		@Keyword
		public static String getTextByXpath(WebDriver driver,String xPath) throws Exception {
			logger.info("Fetching text within "+xPath)
			String value=null
			WebDriverWait wait = new WebDriverWait(driver,20)
			WebElement element = null
			try {
				element = findElementByXpath(driver, xPath)
				if(element!=null) {
					logger.info(xPath+" is visible.")
					if(waitUntilClickable(driver,element)) {
						logger.info(xPath+" is clickable.")
						value= element.getText()
						logger.info("Text is :"+value)
					}

					else {
						logger.error("Element is not clickable")
						throw new ElementNotSelectableException("Unable to click on element")
					}
				}
				else {
					logger.error(xPath+" is not visible")
					throw new NoSuchElementException("Unable to find element in 1st attempt")
				}
			}
			catch(NoSuchElementException e) {
				try {
					element = findElementByXpath(driver, xPath)
					if(element!=null) {
						logger.info(xPath+" is visible.")
						if(waitUntilClickable(driver,element)) {
							logger.info(xPath+" is clickable.")
							value= element.getText()
							logger.info("Text is :"+value)
						}
						else {
							logger.error("Element is not clickable")
							throw new ElementNotSelectableException("Unable to click on element")
						}
					}
					else {
						logger.error(xPath+" is not visible")
						throw new NoSuchElementException("Unable to find element in 1st attempt")
					}
				}
				catch(Exception e1) {
					logger.error("Exception Occured")
					throw new Exception(e1)
				}
			}

			catch(Exception e2) {
				try {
					element =findElementByXpath(driver,xPath)
					if(waitUntilClickable(driver,element)) {
						logger.info(xPath+" is visible.")
						value= element.getText()
						logger.info("Text is :"+value)
					}
					else {
						logger.error("Element is not clickable")
						throw new ElementNotSelectableException("Unable to click on element")
					}
				}
				catch(Exception e3) {
					logger.error("Exception Occured")
					throw new Exception(e3)
				}
			}

			return value
		}

		@Keyword
		public static boolean isElementPresent(WebDriver driver,String elementPath) throws Exception {
			boolean status = false
			try {
				if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
					status=true
					logger.info("Element "+elementPath+" is visible")
				}
				else {
					status = false
					logger.error("Element "+elementPath+" is not visible in 1st attempt")
					throw new ElementNotVisibleException("Element "+elementPath+" is not displayed within "+GlobalVariable.DEFAULT_MEDIUM_WAIT+" secs")
				}
			}
			catch(Exception e) {
				try {
					logger.info("Searching element again....")
					if(WebUI.waitForElementVisible(findTestObject(elementPath),GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
						status=true
						logger.info("Element "+elementPath+" is visible in 2nd attempt")
					}
					else {
						status = false
						logger.error("Element "+elementPath+" is not visible in 2nd attempt")
					}
				}
				catch(Exception e2) {
					logger.error("Exception Occurred.")
					throw e2
				}
			}

			return status
		}

		@Keyword
		public static boolean isAlertPresent(WebDriver driver) throws Exception {
			boolean status= false

			try {
				if(WebUI.verifyAlertPresent(GlobalVariable.DEFAULT_MEDIUM_WAIT)==true) {
					driver.switchTo().alert()
					status = true
				}
			}
			catch(Exception e) {
				status = false
				logger.error("Exception while verifying alert.")
			}

			return status
		}

		@Keyword
		public static void acceptAlert(WebDriver driver) throws Exception {

			try {
				if(isAlertPresent(driver)) {
					Alert alert = driver.switchTo().alert()
					alert.accept()
				}
			}
			catch(Exception e) {

				logger.error("Exception while accepting alert.")
			}
		}

		@Keyword
		public static String getTitle(WebDriver driver) throws Exception {
			String actualTitle= null
			try {

				ActionBot.defaultSleep()
				actualTitle= driver.getTitle()
				if(actualTitle==null) {
					logger.error("Title is blank.")
					throw new Exception("Navigation Failed. Title is blank.")
				}
			}
			catch(Exception e) {
				throw e
			}

			return actualTitle
		}

		@Keyword
		public static void defaultSleep() throws Exception {
			try {
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			}
			catch(Exception e) {
				logger.error("Error while performing Thread Sleep.")
			}
		}

		@Keyword
		public static void defaultSleep(WebDriver driver,int time) throws Exception {
			try {
				WebUI.delay(time)
			}
			catch(Exception e) {
				logger.error("Error while performing Thread Sleep.")
			}
		}

		@Keyword
		public static void selectDropdownValue(WebDriver driver,String elementPath,String selectBy, String value) throws Exception {

			Select select = null
			WebElement element = ActionBot.findElement(driver,elementPath)
			try {
				select = new Select(element)
				if(selectBy!=null) {
					if(selectBy.equalsIgnoreCase(iConstants.SELECT_BY_VALUE)) {
						select.selectByValue(value)
					}
					else if(selectBy.equalsIgnoreCase(iConstants.SELECT_BY_INDEX)) {
						select.selectByIndex(Integer.parseInt(value))
					}
					else if(selectBy.equalsIgnoreCase(iConstants.SELECT_BY_VISIBLE_TEXT)) {
						select.selectByVisibleText(value)
					}
					else if(selectBy.equalsIgnoreCase(iConstants.SELECT_BY_PARTIAL_VISIBLE_TEXT)) {
						for(WebElement options: select.getOptions()) {
							if(options.getText().trim().contains(value)) {
								options.click()
								break
							}
						}
					}
				}
				else {
					logger.error("Invalid Select By option selected")
				}

				String selectedOption = select.getFirstSelectedOption().getText()
				logger.info("Selected Option is : "+selectedOption)
			}
			catch(Exception e) {
				logger.error("Exception occurred while selecting dropdown value.")
				throw e
			}
		}

		/**
		 * @author 
		 * @description : Finding element by Object Repository Path
		 * @param : driver, Object Repo path
		 * @return webElement
		 */
		@Keyword
		public static WebElement findElement(WebDriver driver,String elementPath) throws Exception {
			try {
				return waitUntilVisible(driver,elementPath)
			}
			catch(StaleElementReferenceException e) {
				try {
					logger.error("Stale Element reference exception has occurred. Relocating element..!")
					return waitUntilVisible(driver,elementPath)
				}
				catch(Exception e1) {
					throw e1
				}
			}
			catch(Exception e2) {
				try {
					logger.error(" Relocating element..!")
					return waitUntilVisible(driver,elementPath)
				}
				catch(Exception e3) {
					throw e3
				}
			}
		}

		/**
		 * @author 
		 * @description : Finding element by specifying element xpath
		 * @param : driver, xpath
		 * @return webElement
		 */
		@Keyword
		public static WebElement findElementByXpath(WebDriver driver,String xPath) throws Exception {
			try {
				logger.info("Locating element by Xpath.")
				return waitUntilVisibleByXpath(driver,xPath)
			}
			catch(StaleElementReferenceException e) {
				try {
					logger.error("Stale Element reference exception has occurred. Relocating element..!")
					return waitUntilVisibleByXpath(driver,xPath)
				}
				catch(Exception e1) {
					throw e1
				}
			}
			catch(Exception e2) {
				try {
					logger.error("Relocating element..!")
					return waitUntilVisibleByXpath(driver,xPath)
				}
				catch(Exception e3) {
					throw e3
				}
			}
		}

		/**
		 * @author 
		 * @description : Finding elements by specifying element xpath within parent element
		 * @param : driver, xpath, parent element
		 * @return webElement
		 */
		@Keyword
		public static List<WebElement> findElements(WebDriver driver, WebElement element,String elementXpath) throws Exception {
			List<WebElement> elements = null
			try {
				if(element!= null) {
					elements = element.findElements(By.xpath(elementXpath))

					if(elements.isEmpty()==true) {

						logger.error("Elements searched by "+elementXpath+" not found..!!"+"\n Finding elements again..!")
						elements = element.findElements(By.xpath(elementXpath))

						if(elements.isEmpty()) {
							logger.error("Elements not found in  2nd attempt.")
						}
					}
					else {
						logger.info("Elements found."+elementXpath)
					}
				}
				else {
					logger.info("Elements found."+elementXpath)
				}
			}
			catch(Exception e) {
				try {
					if(element!= null) {
						elements = element.findElements(By.xpath(elementXpath))
						if(elements.isEmpty()==true) {

							logger.error("Elements searched by "+elementXpath+" not found..!!"+"\n Finding elements again..!")
							elements = element.findElements(By.xpath(elementXpath))

							if(elements.isEmpty()) {
								logger.error("Elements not found in  2nd attempt.")
							}
						}
						else {
							logger.info("Elements found."+elementXpath)
						}
					}
					else {
						logger.info("Elements found."+elementXpath)
					}
				}
				catch(Exception e1) {
					throw e1
				}
			}

			return elements
		}

		/**
		 * @author
		 * @description : Finding elements by specifying element xpath 
		 * @param : driver, xpath
		 * @return webElement
		 */
		@Keyword
		public static List<WebElement> findElements(WebDriver driver,String elementXpath) throws Exception {
			List<WebElement> elements = null
			try {

				elements = driver.findElements(By.xpath(elementXpath))
				if(elements.isEmpty()==true) {

					logger.error("Elements searched by "+elementXpath+" not found..!!"+"\n Finding elements again..!")
					elements = driver.findElements(By.xpath(elementXpath))

					if(elements.isEmpty()) {
						logger.error("Elements not found in  2nd attempt.")
					}
				}
				else {
					logger.info("Elements found."+elementXpath)
				}
			}
			catch(Exception e) {
				try {
					logger.info("Exception "+e.getMessage()+" occurred while finding elements in 1st attempt. Searching elements again.")
					elements = driver.findElements(By.xpath(elementXpath))
					if(elements.isEmpty()==true) {

						logger.error("Elements searched by "+elementXpath+" not found..!!"+"\n Finding elements again..!")
						elements = driver.findElements(By.xpath(elementXpath))

						if(elements.isEmpty()) {
							logger.error("Elements not found in  2nd attempt.")
						}
					}
					else {
						logger.info("Elements found."+elementXpath)
					}
				}
				catch(Exception e1) {
					throw e1
				}
			}

			return elements
		}

		@Keyword
		public static void selectElementCheckBox(WebDriver driver,String elementPath) throws Exception {

			try {
				if(isSelected(driver,elementPath)==false) {
					ActionBot.click(driver,elementPath)
					logger.info("Element is selected.")
				}
				else {
					logger.info("Element is already Selected.")
				}
			}
			catch(Exception e) {
				logger.error("Error while selecting element.")
				throw e
			}
		}

		@Keyword
		public static void deselectElementCheckBox(WebDriver driver,String elementPath) throws Exception {

			try {
				if(isSelected(driver,elementPath)==true) {
					ActionBot.click(driver,elementPath)
					logger.info("Element is deselected.")
				}
				else {
					logger.info("Element is already deselected.")
				}
			}
			catch(Exception e) {
				logger.error("Error while selecting element.")
				throw e
			}
		}

		@Keyword
		public static boolean isSelected(WebDriver driver, String elementPath) throws Exception {
			boolean status = false
			WebElement element = null

			try {
				element = ActionBot.findElement(driver, elementPath)
				if(element.isSelected()) {
					status = true
					logger.info("Element is Selected.")
				}
				else {
					logger.info("Element is not Selected.")
				}
			}
			catch(Exception e) {
				try {
					logger.info("Locating element again...!")
					element = ActionBot.findElement(driver, elementPath)
					if(element.isSelected()) {
						status = true
						logger.info("Element is Selected.")
					}
					else {
						logger.info("Element is not Selected.")
					}
				}
				catch(Exception e1) {
					logger.error("Error while verifying whether element is Selected.")
					throw e1
				}
			}


			return status
		}

		@Keyword
		public static boolean isEnabled(WebDriver driver, String elementPath) throws Exception {
			boolean status = false
			WebElement element = null

			try {
				element = ActionBot.findElement(driver, elementPath)
				if(element.isEnabled()) {
					status = true
					logger.info("Element is Enabled.")
				}
				else {
					logger.info("Element is not Enabled.")
				}
			}
			catch(Exception e) {
				try {
					logger.info("Locating element again...!")
					element = ActionBot.findElement(driver, elementPath)
					if(element.isEnabled()) {
						status = true
						logger.info("Element is Enabled.")
					}
					else {
						logger.info("Element is not Enabled.")
					}
				}
				catch(Exception e1) {
					logger.error("Error while verifying whether element is Enabled.")
					throw e1
				}
			}

			return status
		}

		/**
		 * @author
		 * @description : Enter file path using robot class
		 * @param : driver, file webElement
		 */
		@Keyword
		public static void PasteFilePath(WebDriver driver,String filepath) throws Exception {
			try {
				StringSelection string = new StringSelection(filepath)
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(string,null)

				Robot robot = new Robot()
				ActionBot.defaultSleep()


				robot.keyPress(KeyEvent.VK_ENTER)

				robot.keyRelease(KeyEvent.VK_ENTER)


				robot.keyPress(KeyEvent.VK_CONTROL)
				robot.keyPress(KeyEvent.VK_V)


				robot.keyRelease(KeyEvent.VK_CONTROL)
				robot.keyRelease(KeyEvent.VK_V)
				ActionBot.defaultSleep()

				robot.keyPress(KeyEvent.VK_ENTER)
				robot.keyRelease(KeyEvent.VK_ENTER)

				logger.info("File Path is entered.")
			}
			catch(Exception e) {
				logger.error("Unable to enter file path.")
				throw e
			}
		}

		/**
		 * @author
		 * @description : Enter filepath using katalon keywords
		 * @param : driver, file path, object repo path of fileupload field
		 */
		@Keyword
		public static boolean enterFilePath(WebDriver driver,String filepath,String elementPath) throws Exception {
			boolean status = false
			try {
				if(waitUntilVisible(driver, elementPath)) {
					WebUI.uploadFile(findTestObject(elementPath),filepath)

					defaultSleep()
					String enteredFilePath = WebUI.getAttribute(findTestObject('Upload File'), 'value')

					if(enteredFilePath.equalsIgnoreCase(filepath)) {
						status = true
						logger.info("File Path is entered.")
					}
				}
			}
			catch(Exception e) {
				logger.error("Unable to enter file path.")
				throw e
			}

			return status
		}


		/**
		 * @author
		 * @description : Fill auto complete field by selecting specific option from populated list after filling value
		 * @param : driver, textbox WebElement to enter value,xpath of suggestion menu , value to enter
		 * @return Selected value
		 */
		@Keyword
		public static String fillAutoCompleteByValue(WebDriver driver,String testcaseName,WebElement element,String suggestionMenuXpath, String value) throws Exception {
			String selectedValue = null
			WebElement suggestionMenu = null
			List<WebElement> suggestedOptions = null

			try {
				element.clear()
				element.sendKeys(value)
				Screenshot.captureScreenshot( driver, testcaseName,"After_Sending_value_to_autocompleteField")
				defaultSleep()

				suggestionMenu = waitUntilVisibleByXpath(driver, suggestionMenuXpath)


				//find options present in suggested list
				suggestedOptions = findElements(driver,suggestionMenu,suggestionMenuXpath)
				logger.info("Auto Populated Options :"+suggestedOptions.size())

				for(WebElement ele : suggestedOptions)
				{
					if(ele.getText().toLowerCase().contains(value.toLowerCase()))
					{
						ele.click()
						Screenshot.captureScreenshot( driver, testcaseName, "After_Selecting_Value_From_dropdown")
					}
				}
				defaultSleep()
				selectedValue = element.getAttribute("value")

				if(selectedValue == null || selectedValue =="")
				{
					throw new Exception("Could not select value in Auto Complete field in 1st attempt.")
				}
			}
			catch(Exception e)
			{
				try
				{
					logger.info("Unable to Select value in Auto Complete field in 1st attempt. Trying again..!")
					element.clear()
					element.sendKeys(value)
					defaultSleep()
					suggestionMenu = waitUntilVisibleByXpath(driver,suggestionMenuXpath)
					suggestedOptions = findElements(driver,suggestionMenu,suggestionMenuXpath)

					for(WebElement ele : suggestedOptions)
					{
						if(ele.getText().toLowerCase().contains(value.toLowerCase()))
						{
							ele.click()
							Screenshot.captureScreenshot( driver, testcaseName, "After_Selecting_Value_From_dropdown")
						}
					}
					defaultSleep()
					selectedValue = element.getAttribute("value")
					if(selectedValue == null || selectedValue =="")
					{
						throw new Exception("Could not select value in Auto Complete field in last attempt.")
					}
				}
				catch(Exception e1)
				{
					throw e1
				}
			}

			return selectedValue
		}

		/**
		 * @author 
		 * @description : Fill auto complete field by selecting 1st option from populated list after filling value
		 * @param : driver, textbox WebElement to enter value, xpath of specific suggestion menu option , value to enter, item type
		 * @return Selected value
		 */
		@Keyword
		public static void fillAutoCompleteItem(WebDriver driver,String testCaseName,WebElement element,String SuggestedValueXpath, String value) throws Exception
		{
			String selectedValue = null
			WebElement suggestion = null

			try
			{
				element.clear()
				element.sendKeys(value)

				defaultSleep(driver,20)
				Screenshot.captureScreenshot(driver,testCaseName ,"After_Sending_value_to_autocompleteField")


				suggestion = waitUntilVisibleByXpath(driver, SuggestedValueXpath)


				if(suggestion!=null)
				{
					suggestion.click()
				}

				defaultSleep()

				Screenshot.captureScreenshot(driver, testCaseName,"After_Selecting_value_in_autocompleteField")


			}
			catch(Exception e)
			{
				logger.error("Exception occurred while selecting value in autocomplete field...!")
				throw e
			}

		}



		@Keyword
		public static void jsScrollDown(WebDriver driver, String value) throws Exception
		{
			try
			{
				JavascriptExecutor js = (JavascriptExecutor) driver
				js.executeScript("window.scrollBy(0,"+value+")")
			}
			catch(Exception e)
			{
				throw e
			}
		}

		@Keyword
		public static void scrollDownToPosition(WebDriver driver,int x,int y) throws Exception
		{
			try
			{
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
				WebUI.scrollToPosition(x, y)
				WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)

			}
			catch(Exception e)
			{
				throw e
			}
		}

		@Keyword
		public static void scrollDownToElement(WebDriver driver,String elementPath) throws Exception
		{
			try
			{
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
				if(waitUntilVisible(driver, elementPath)!=null)
				{
					WebUI.scrollToElement(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)
					WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
				}

			}
			catch(Exception e)
			{
				try
				{
					logger.info("Trying to scroll again..!")
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
					if(waitUntilVisible(driver, elementPath)!=null)
					{
						WebUI.scrollToElement(findTestObject(elementPath),GlobalVariable.DEFAULT_HIGH_WAIT)
						WebUI.delay(GlobalVariable.DEFAULT_MEDIUM_WAIT)
					}

				}
				catch(Exception e1)
				{
					logger.error("Exception occurred while scrolling to element.")
					throw e1
				}
			}
		}

		@Keyword
		public static void moveToElement(WebDriver driver, WebElement element) throws Exception
		{
			Actions actions = new Actions(driver)
			try
			{
				if(element!=null)
				{
					actions.moveToElement(element).build().perform()
				}
				else
				{
					logger.error("Element is empty...!")
				}
			}
			catch(Exception e)
			{
				logger.error("Exception while moving focus on element..!")
				throw e
			}
		}

		@Keyword
		public static void moveToElementAndClick(WebDriver driver, WebElement element) throws Exception
		{
			Actions actions = new Actions(driver)
			try
			{
				if(element!=null)
				{
					actions.moveToElement(element).click().build().perform()
				}
				else
				{
					logger.error("Element is empty...!")
				}
			}
			catch(Exception e)
			{
				logger.error("Exception has occurred...Searching element again..!")
				try
				{
					if(element!=null)
					{
						actions.moveToElement(element).click().build().perform()
					}
					else
					{
						logger.error("Element is empty...!")
					}
				}
				catch(Exception e1)
				{
					logger.error("Exception while performing click on element..!")
					throw e1
				}
			}

		}

		public static String fillAutoCompleteAndCheckIfValueMatches(WebDriver driver, WebElement element, String suggestionMenuXpath, String value) throws Exception {
			String selectedValue = null
			WebElement suggestionMenu = null
			List<WebElement> suggestedOptions = null

			try {
				element.clear()
				element.sendKeys(value)
				defaultSleep()
				waitUntilVisibleByXpath(driver, suggestionMenuXpath)

				//find options present in suggested list
				suggestedOptions = ActionBot.findElements(driver, suggestionMenuXpath)
				logger.info("Auto Populated Options :" + suggestedOptions.size())

				for(WebElement ele : suggestedOptions)
				{
					if(ele.getText().toLowerCase().contains(value.toLowerCase()))
					{
						defaultSleep()
						ele.click()
						break
					}
				}
				defaultSleep()
				selectedValue = element.getAttribute("value")

				if(selectedValue == null || selectedValue =="")
				{
					throw new Exception("Could not select value in Auto Complete field in 1st attempt.")
				}
				else if(selectedValue.contains(value)) {
					logger.info("Selected value does not match. Value --> " + selectedValue)
				}
				logger.info("Value selected --> " + selectedValue)
			}
			catch(Exception e)
			{
				try
				{
					logger.info("Auto Complete failed in 1st attempt. Trying again..!")
					element.clear()
					element.sendKeys(value)
					//Screenshot.captureScreenshot( driver, testcaseName,"After_Sending_value_to_autocompleteField")
					defaultSleep()
					waitUntilVisibleByXpath(driver, suggestionMenuXpath)

					//find options present in suggested list
					suggestedOptions = ActionBot.findElements(driver, suggestionMenuXpath)
					logger.info("Auto Populated Options :" + suggestedOptions.size())

					for(WebElement ele : suggestedOptions)
					{
						if(ele.getText().toLowerCase().contains(value.toLowerCase()))
						{
							defaultSleep()
							ele.click()
							//Screenshot.captureScreenshot( driver, testcaseName, "After_Selecting_Value_From_dropdown")
							break
						}
					}
					defaultSleep()
					selectedValue = element.getAttribute("value")

					if(selectedValue == null || selectedValue =="")
					{
						throw new Exception("Could not select value in Auto Complete field in 1st attempt.")
					}
					else if(selectedValue.contains(value)) {
						logger.info("Selected value does not match. Value --> " + selectedValue)
					}
					logger.info("Value selected --> " + selectedValue)
				}
				catch(Exception e1)
				{
					throw e1
				}
			}

			return selectedValue
		}

		public static String fillAutoCompleteBuyerField(WebDriver driver, WebElement element, String suggestionMenuXpath, String value) throws Exception {
			String selectedValue = null
			WebElement suggestionMenu = null
			List<WebElement> suggestedOptions = null

			try {
				element.clear()
				element.sendKeys(value)
				defaultSleep()
				waitUntilVisibleByXpath(driver, suggestionMenuXpath)

				//find options present in suggested list
				suggestedOptions = ActionBot.findElements(driver, suggestionMenuXpath)
				logger.info("Auto Populated Options :" + suggestedOptions.size())

				for(WebElement ele : suggestedOptions)
				{
					if(ele.getText().toLowerCase().contains(value.toLowerCase()))
					{
						defaultSleep()
						ele.click()
						break
					}
				}
				defaultSleep()
				logger.info("Value selected --> " + selectedValue)
			}
			catch(Exception e)
			{
				try
				{
					logger.info("Auto Complete failed in 1st attempt. Trying again..!")
					element.clear()
					element.sendKeys(value)
					defaultSleep()
					waitUntilVisibleByXpath(driver, suggestionMenuXpath)

					//find options present in suggested list
					suggestedOptions = ActionBot.findElements(driver, suggestionMenuXpath)
					logger.info("Auto Populated Options :" + suggestedOptions.size())

					for(WebElement ele : suggestedOptions)
					{
						if(ele.getText().toLowerCase().contains(value.toLowerCase()))
						{
							defaultSleep()
							ele.click()
							break
						}
					}
					defaultSleep()
					logger.info("Value selected --> " + selectedValue)
				}
				catch(Exception e1)
				{
					throw e1
				}
			}

			return selectedValue
		}


		@Keyword
		public static void moveToElementAndDoubleClick(WebDriver driver, WebElement element) throws Exception
		{
			Actions actions = new Actions(driver)
			try
			{
				if(element!=null)
				{
					actions.moveToElement(element).doubleClick().build().perform()
				}
				else
				{
					logger.error("Element is empty...!")
				}
			}
			catch(Exception e)
			{
				logger.error("Exception has occurred...Searching element again..!")
				try
				{
					if(element!=null)
					{
						actions.moveToElement(element).doubleClick().build().perform()
					}
					else
					{
						logger.error("Element is empty...!")
					}
				}
				catch(Exception e1)
				{
					logger.error("Exception while performing click on element..!")
					throw e1
				}
			}
		}

		public static void clearTextThroughKeyboard(String elementPath) {
			try {
				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

				ActionBot.click(elementPath)

				WebUI.clearText(findTestObject(elementPath))

				logger.info("Clearing the text from " + elementPath)

				WebUI.sendKeys(findTestObject(elementPath), Keys.NUMPAD0 + Keys.NUMPAD1)

				WebUI.sendKeys(findTestObject(elementPath), Keys.BACK_SPACE + Keys.BACK_SPACE)

				WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)
			}
			catch(Exception e) {
				try {
					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

					ActionBot.click(elementPath)

					WebUI.clearText(findTestObject(elementPath))

					logger.info("Clearing the text from " + elementPath)

					WebUI.sendKeys(findTestObject(elementPath), Keys.NUMPAD0 + Keys.NUMPAD1)

					WebUI.sendKeys(findTestObject(elementPath), Keys.BACK_SPACE + Keys.BACK_SPACE)

					WebUI.delay(GlobalVariable.DEFAULT_LOW_WAIT)

				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		public static int getPosition(WebDriver driver, String elementPath) {
			int position = 0
			try {
				if(ActionBot.isElementPresent(driver, elementPath)){
					position = WebUI.getElementLeftPosition(findTestObject(elementPath))
					logger.info("Position of the Element is :" + position)
					if(position!=0){
						return position
					}
					else{

						logger.info("Position of the Element is :" + position)
						return position
					}
				}
			}
			catch(Exception e) {
				try {
					if(ActionBot.isElementPresent(driver, elementPath)){
						position = WebUI.getElementLeftPosition(findTestObject(elementPath))
						logger.info("Position of the Element is :" + position)
						if(position!=0){
							return position
						}
						else{

							logger.info("Position of the Element is :" + position)
							return position
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}


	}



}
