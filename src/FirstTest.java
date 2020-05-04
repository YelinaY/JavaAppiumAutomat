import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
  private AppiumDriver driver;

  @Before

  public void setUp() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "and80");
    capabilities.setCapability("platformVersion", "8.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("app", "C:\\JavaAppiumAutomat\\JavaAppiumAutomat\\apks\\org.wikipedia_50309_apps.evozi.com.apk");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testCancelSearch() {
    WebElement element1 = driver.findElementByXPath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]");
    element1.click();

    WebElement element2 = waitForElementPresentByXpath("//*[contains(@text,'ADD LANGUAGE')]", "Cannot find element", 5);

    WebElement element3 = waitForElementPresentByXpath("//*[contains(@text,'ADD LANGUAGE')]", "Cannot find element");
    element3.click();
/*
    WebElement element4 = waitForElementPresentByXpath("//android.widget.TextView[@content-desc=\"Search for a language\"]",
            "Cannot find element");
    element4.click();
*/
    waitForElementAndClick("//android.widget.TextView[@content-desc=\"Search for a language\"]", "Cannot find element", 5);
 /* WebElement element5 = waitForElementPresentByXpath("//*[contains(@text,'Search for a language')]", "Cannot find element");
    element5.sendKeys("English");
*/
    waitForElementAndSendKeys("//*[contains(@text,'Search for a language')]", "English", "Cannot find element", 5);

    waitForElementPresentByXpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']", "Cannot find element search by the text 'Old English'", 15);

    //System.out.println("First test run");

    waitForElementPresentById("org.wikipedia:id/search_close_btn", //button "X"
            "Can not find 'Search input'", 5);
    waitForElementPresentByIdAndClick("org.wikipedia:id/search_close_btn", "Can not find 'button 'X'", 5);
    waitForElementNotPresent("org.wikipedia:id/search_close_btn", "Element 'X' still present", 5);
  }

  private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.xpath(xpath);
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  private WebElement waitForElementPresentByXpath(String xpath, String error_message) {
    return waitForElementPresentByXpath(xpath, error_message, 5);
  }

  private WebElement waitForElementAndClick(String xpath, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
    element.click();
    return element;
  }

  private WebElement waitForElementAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
    element.sendKeys(value);
    return element;
  }

  private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.id(id);
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  private WebElement waitForElementPresentByIdAndClick(String id, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
    element.click();
    return element;
  }

  private boolean waitForElementNotPresent(String id, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.id(id);
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }
}
