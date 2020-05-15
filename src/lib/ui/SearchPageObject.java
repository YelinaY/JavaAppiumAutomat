package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
 //Ввод констант
  private static final String
          ADD_OR_EDIT_LANGUAGES = "//*[contains(@text,'ADD OR EDIT LANGUAGES')]",
          ADD_LANGUAGE = "//*[contains(@text,'ADD LANGUAGE')]",
          ICON_SEARCH = "//android.widget.TextView[@content-desc=\"Search for a language\"]",
          SEARCH_INPUT = "//*[contains(@text,'Search for a language')]",
          SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='{SUBSTRING}']";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }
  private static String getResultSearchElement (String substring){
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace ( "{SUBSTRING}", substring);
  }

  public void initSearchInput() {
    this.waitForElementPresentAndClick(By.xpath(ADD_OR_EDIT_LANGUAGES), "Can not find element", 5);
    this.waitForElementPresentAndClick(By.xpath(ADD_LANGUAGE), "Can not find element", 5);
    this.waitForElementPresentAndClick(By.xpath(ICON_SEARCH), "Icon can not find", 5);
  }
  public void typeSearchLine(String search_line){
    this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can not find element", 5);
  }
  public void waitForSearchResult(String substring){
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(By.xpath(search_result_xpath), "Can not find search result with substring", 5);
  }
}
