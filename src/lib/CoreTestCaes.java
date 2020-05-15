package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCaes extends TestCase {
  protected AppiumDriver driver;
private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

@Override

   protected void setUp() throws Exception {
     super.setUp();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "and80");
    capabilities.setCapability("platformVersion", "8.0");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("app", "C:\\JavaAppiumAutomat\\JavaAppiumAutomat\\apks\\org.wikipedia_50309_apps.evozi.com.apk");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    driver = new AndroidDriver(new URL(AppiumURL), capabilities);

  }
@Override
  protected void tearDown() throws Exception {
    driver.quit();
    super.tearDown();
  }

}
