import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);

    waitForElementPresent(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);

     //прокрутка страницы вниз  swipeUp(2000);
     //Прокрутка до искомого элемента

    swipeUpToFindElement(
            By.xpath("//*[@text='Nederlands']"),
            "Can not find element 'Nederlands' by the swiping",
           40

    );

    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);

    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), "English", "Cannot find element", 5);

    waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']"), "Cannot find element search by the text 'Old English'", 15);

    //Проверка на наличие теска (assert - exp. result Text "Simple English")
    WebElement subtitle = waitForElementPresent(By.id("org.wikipedia:id/language_subtitle"), "Can not find subtitle", 10);
    String subtitl = subtitle.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitl);

    //Стереть текст из поля ввода
    waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), // Стираю текст с поля
            "Can not find search field", 5);
    /* Ожидать элемент "X"
    waitForElementPresent(By.id("org.wikipedia:id/search_close_btn"), //button "X"
            "Can not find 'Search input'", 5);
           Oжидать элемент "X"  и кликнуть на него
    waitForElementPresentAndClick(By.id("org.wikipedia:id/search_close_btn"), "Can not find 'button 'X'", 5);
    */
    waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Element 'X' still present", 5);
  }

  private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  /*
    private WebElement waitForElementPresent(By by, String error_message) {
      return waitForElementPresent(by, error_message, 5);
    }
  */
  private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.click();
    return element;
  }

  private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.sendKeys(value);
    return element;
  }

  private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  private WebElement waitForElementAndClear(By by, String erro_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(by, erro_message, timeoutInSeconds);
    element.clear();
    return element;
  }

  //прокрутка страницы сверху вниз
  protected void swipeUp(int timeOfSwipe) {
    TouchAction action = new TouchAction(driver);
    Dimension size = driver.manage().window().getSize();
    int x = size.width / 2;
    int start_y = (int) (size.height * 0.8);
    int end_y = (int) (size.height * 0.2);
    action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();

  }
  //быстрая прокрутка вниз
  protected void swipeUpQuick(){
    swipeUp(200);
  }
  //быстрая прокрутка до искомого элемента (если элемент не найден - цикл прокрутки останавливается с ошибкой)
  protected  void swipeUpToFindElement (By by, String error_message, int max_swipes){
    driver.findElements(by);
    driver.findElements(by).size();
    int already_swiped = 0;
    while (driver.findElements(by).size()==0){
      if (already_swiped > max_swipes){
        waitForElementPresent(by, "Can not find element by swiping up. \n" + error_message, 0);
        return;
      }
      swipeUpQuick();
      ++already_swiped;

    }
  }
}
