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

  public void setUp () throws Exception{
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","and80");
    capabilities.setCapability("platformVersion","8.0");
    capabilities.setCapability("automationName","Appium");
    capabilities.setCapability("appPackage","org.wikipedia");
    capabilities.setCapability("app","C:\\JavaAppiumAutomat\\JavaAppiumAutomat\\apks\\org.wikipedia_50309_apps.evozi.com.apk");
    capabilities.setCapability("appActivity",".main.MainActivity");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

  }
  @After
  public void tearDown(){
    driver.quit();
  }
  @Test
  public  void firstTest(){
    WebElement element = driver.findElementByXPath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]");
    element.click();
    WebElement elementk = waitForElementPresentByXpath("//*[contains(@text,'ADD LANGUAGE')]", "Cannot find element", 5);
        elementk.click();

    //android.widget.TextView[@content-desc="Search for a language"]

    //WebElement element_to_enter_search_line = driver.findElementByXPath("//*[contains(@text,'Search for a language')]");
    //element_to_enter_search_line.sendKeys("English");

   //System.out.println("First test run");
    }

  private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    By by = By.xpath(xpath);
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

}
