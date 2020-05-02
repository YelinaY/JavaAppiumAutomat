import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    
   //System.out.println("First test run");
    }
}
