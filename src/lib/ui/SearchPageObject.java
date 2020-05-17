package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
  //Ввод констант
  private static final String ADD_OR_EDIT_LANGUAGES = "//*[contains(@text,'ADD OR EDIT LANGUAGES')]",
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
          ELEMENT_X = "org.wikipedia:id/search_close_btn",
          EMPTY_SEARCH_RESULT = "//*[@text= 'No languages found']",
          ENGLISH_OLD_LANGUAGE_SUBTITLE = "//*[contains(@text,'Ænglisc')]",
          SIMPLE_ENGLISH_LANGUAGE_SUBTITLE = "//*[contains(@text,'Simple English')]",
          WIKIPEDIA_FAULDER = "//*[contains(@text,'Wikipedia languages')]";


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

  public void waitFirstButtonAddOrEditLanguage() {
    this.waitForElementPresent(By.xpath(ADD_OR_EDIT_LANGUAGES), "Button not find", 5);
  }

  public void waitArticleFromSecondPage() {
    this.waitForElementPresent(By.xpath(ARTICLE_FROM_SECOND_PAGE), "Article not find", 30);
  }

  public void waitArticleFromFirstPage() {
    this.waitForElementPresent(By.xpath(ARTICLE_FROM_fIRST_PAGE), "Article not find", 30);
  }

  public void swipePageToleft() {
    this.swipElementToLeft(By.xpath(SWIP_PAGE_TO_LEFT), "Page does not swipe");
  }

  public void swipePageUntilElementsNederlands() {
    this.swipeUpToFindElement(By.xpath(SWIPE_UNTILL_ELEMENT), "Element not find", 40);
  }

  public void removeTextFromSearchLine() {
    this.waitForElementAndClear(By.id(CLEAN_TEXT_FROM_SEARCH), "Element not find", 10);
  }

  public void waitElementXInvisible() {
    this.waitForElementNotPresent(By.id(ELEMENT_X), "Element 'X' display", 15);
  }

  public void waitEmptySearchResult() {
    this.waitForElementPresent(By.xpath(EMPTY_SEARCH_RESULT), "Result display incorrectly", 10);
  }

  public void assertTextOfEmptySearchResult() {
    String search_result_locator = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']";
    String empty_result_lable = "//*[@text= 'No languages found']";
    this.waitForElementPresent(By.xpath(empty_result_lable), "Can not find empty result by the request", 15);
    this.assertElementPresent(By.xpath(search_result_locator), "Incorrectly We found some result by request");

  }

  public void selectLanguageFromTheListOldEnglish() {
    this.waitForElementPresentAndClick(By.xpath(ENGLISH_OLD_LANGUAGE_SUBTITLE), "Language subtitle 'Old English not find'", 10);
  }

  public void clickOnButtonAddLanguage() {
    this.waitForElementPresentAndClick(By.xpath(ADD_LANGUAGE), "Can not find element", 5);
  }

  public void selectLanguageFromTheListSimpleEnglish() {
    this.waitForElementPresentAndClick(By.xpath(SIMPLE_ENGLISH_LANGUAGE_SUBTITLE), "Language subtitle 'Old English not find'", 10);
  }

  public void waitWikipediaLanguagesFaulder() {
    this.waitForElementPresent(By.xpath(WIKIPEDIA_FAULDER), "Faulder with Wikipedia laguages not find", 10);

  }

  public void subtitleLanguagesAssert() {
    WebElement titleSimpleEnglish = this.waitForElementPresent(By.xpath("//*[contains(@text,'Simple English')]"), "Cannot find element", 5);
    String titleSimpleEnglissh = titleSimpleEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", titleSimpleEnglissh);

    WebElement titleOldEnglish = this.waitForElementPresent(By.xpath("//*[contains(@text,'Ænglisc')]"), "Cannot find element", 5);
    String titleOldEnglissh = titleOldEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Ænglisc", titleOldEnglissh);
  }


  public void removeLanguagesFromFaulder (){
    //Удаляю языки из папки
    this.waitForElementPresentAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"), "Cannot find element", 5);

    this.waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/content']"), "Cannot find element search by the text 'Remove language'", 15);
    //Выбор № 2 из списка элементов
    List<WebElement> elements = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    elements.get(1).click();

    //Выбор № 3 из списка элементов
    List <WebElement> element = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    element.get(2).click();

    this.waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc='Delete selected items']"), "Cannot find element", 5);

    this.waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/alertTitle']"),
            "Cannot find element", 10);

    List <WebElement> buttonOK = (List<WebElement>) driver.findElementsById("android:id/button1");
    buttonOK.get(0).click();

    this.waitForElementPresent(By.xpath("//*[contains(@text,'Wikipedia languages')]"), "Cannot find element", 5);

  }
  public void verifyThatFirstLanguageRemoved (){
    this.waitForElementNotPresent(By.xpath(ENGLISH_OLD_LANGUAGE_SUBTITLE), "Subtitle should not display", 5);
  }
  public void verifyThatSecondLanguageRemoved (){
    this.waitForElementNotPresent(By.xpath(SIMPLE_ENGLISH_LANGUAGE_SUBTITLE), "Subtitle should not display", 5);
  }
}
