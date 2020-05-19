package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LanguagePageObject extends MainPageObject {
  private static final String
  lANGUAGE_TITLE = "//*[contains(@text,'Ænglisc')]",
  SWIPE_SECOND_PAGE_TO_LEFT = "//*[contains(@text,'New ways to explore')]";
  public LanguagePageObject (AppiumDriver driver){
    super(driver);
  }
  public WebElement waitForTitleElement (){
    return this.waitForElementPresent(
            By.xpath(lANGUAGE_TITLE), "Cannot find Lnguage title element", 5);
  }
  public String getLanguageTitle (){
    WebElement title_element = waitForTitleElement();
    return title_element.getAttribute("text");
  }
  public void swipePageToLeft (){
    this.swipeUpToFindElement(
            By.xpath(SWIPE_SECOND_PAGE_TO_LEFT), "Page does not swipe", 20);
  }
}
