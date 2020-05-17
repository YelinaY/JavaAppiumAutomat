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
          SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='{SUBSTRING}']",
          ARROW_TO_RIGHT = "//android.widget.ImageView[@content-desc=\"Continue\"]",
          ARTICLE_FROM_SECOND_PAGE = "//*[contains(@text,'New ways to explore')]",
          ARTICLE_FROM_fIRST_PAGE = "//*[contains(@text,'Reading lists with sync')]",
          SWIP_PAGE_TO_LEFT = "//*[contains(@text,'New ways to explore')]",
          SWIPE_UNTILL_ELEMENT = "//*[@text='Nederlands']",
          CLEAN_TEXT_FROM_SEARCH = "org.wikipedia:id/search_src_text",
          ELEMENT_X = "org.wikipedia:id/search_close_btn";


  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  public void clickOnButtonsLanguages() {
    this.waitForElementPresentAndClick(By.xpath(ADD_OR_EDIT_LANGUAGES), "Can not find element", 5);
    this.waitForElementPresentAndClick(By.xpath(ADD_LANGUAGE), "Can not find element", 5);
  }

  public void iconSearch() {
    this.waitForElementPresentAndClick(By.xpath(ICON_SEARCH), "Icon can not find", 5);

  }

  public void typeSearchLine(String search_line) {
    this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can not find element", 5);
  }

  public void waitForSearchResult(String substring) {
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(By.xpath(search_result_xpath), "Can not find search result with substring", 5);
  }

  public void clickOnArrowMovePageToRight() {
    this.waitForElementPresentAndClick(By.xpath(ARROW_TO_RIGHT), "Arrow not find", 5);
  }
  public void waitFirstButtonAddOrEditLanguage(){
    this.waitForElementPresent(By.xpath(ADD_OR_EDIT_LANGUAGES), "Button not find", 5);
  }
  public void waitArticleFromSecondPage(){
    this.waitForElementPresent(By.xpath(ARTICLE_FROM_SECOND_PAGE), "Article not find", 30);
  }
  public void waitArticleFromFirstPage(){
    this.waitForElementPresent(By.xpath(ARTICLE_FROM_fIRST_PAGE), "Article not find", 30);
  }
  public void swipePageToleft(){
    this.swipElementToLeft(By.xpath(SWIP_PAGE_TO_LEFT), "Page does not swipe");
  }
   public  void swipePageUntilElementsNederlands(){
    this.swipeUpToFindElement(By.xpath(SWIPE_UNTILL_ELEMENT), "Element not find", 40);
}
  public void removeTextFromSearchLine(){
  this.waitForElementAndClear (By.id(CLEAN_TEXT_FROM_SEARCH), "Element not find", 10 );
      }
  public  void waitElementXInvisible(){
    this.waitForElementNotPresent(By.id(ELEMENT_X), "Element 'X' display", 15);
  }
    }
