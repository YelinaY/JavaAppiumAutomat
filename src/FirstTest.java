import lib.CoreTestCaes;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.util.List;



public class FirstTest extends CoreTestCaes {
  private MainPageObject MainPageObject;
  protected void setUp() throws Exception{
    super.setUp();
    MainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSearchLanguage () {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();
    SearchPageObject.typeSearchLine("English");
    SearchPageObject.waitForSearchResult("Old English");
  }

    @Test
  public void testSwipeToLeftAndRite(){
      SearchPageObject SearchPageObject = new SearchPageObject(driver);
      SearchPageObject.waitFirstButtonAddOrEditLanguage();
      SearchPageObject.clickOnArrowMovePageToRight();
      SearchPageObject.waitArticleFromSecondPage();
      SearchPageObject.swipePageToleft();
      SearchPageObject.waitArticleFromFirstPage();
    }

    @Test
    public void testSwipeUpToFindElement(){
      SearchPageObject SearchPageObject = new SearchPageObject(driver);
      SearchPageObject.clickOnButtonsLanguages();
      SearchPageObject.swipePageUntilElementsNederlands();
    }

  @Test
  public void testCancelSearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();
    SearchPageObject.typeSearchLine("English");
    SearchPageObject.waitForSearchResult("Old English");
    //Проверка на наличие теска (assert - exp. result Text "Simple English")
    WebElement subtitle = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/language_subtitle"), "Can not find subtitle", 10);
    String subtitl = subtitle.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitl);
    SearchPageObject.removeTextFromSearchLine();
    SearchPageObject.waitElementXInvisible();
  }


  @Test
  public void testAmountOfNotEmptySearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();
    SearchPageObject.typeSearchLine("fuyfyuffuy");
    SearchPageObject.waitEmptySearchResult();
   SearchPageObject.assertTextOfEmptySearchResult();

  }
  @Test
  public void testAddAndRemoveNewLanguage () {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();
    SearchPageObject.typeSearchLine("English");
    SearchPageObject.waitForSearchResult("Old English");
    SearchPageObject.selectLanguageFromTheListOldEnglish();
    SearchPageObject.clickOnButtonAddLanguage();
    SearchPageObject.iconSearch();
    SearchPageObject.typeSearchLine("English");
    SearchPageObject.waitForSearchResult("Simple English");
    SearchPageObject.selectLanguageFromTheListSimpleEnglish();
    SearchPageObject.waitWikipediaLanguagesFaulder();
    SearchPageObject.subtitleLanguagesAssert();
    SearchPageObject.removeLanguagesFromFaulder();
    SearchPageObject.verifyThatFirstLanguageRemoved();
    SearchPageObject.verifyThatSecondLanguageRemoved();

    /*Ввожу переменную "English"
    String subtitle_of_language = "English";
    MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);
    //Проверка на наличие теска (assert - exp. result Text "Simple English")
    WebElement subtitle = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/language_subtitle"), "Can not find subtitle", 10);
    String subtitl = subtitle.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitl);
*/
    }


  @Test
  public void testChangeScreanOrientation(){
    //Open APK
    MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_onboarding_page_primary_text"),
            "text",
            "Not find Title of artcle",
            5);
    driver.rotate(ScreenOrientation.LANDSCAPE);

    String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_onboarding_page_primary_text"),
            "text",
            "Not find Title of artcle",
            5);
    Assert.assertEquals(
            "Articale title have been changed after screen rotation",
            title_before_rotation,
            title_after_rotation
    );
    //Сравнение элементов
    driver.rotate(ScreenOrientation.PORTRAIT);
    String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_onboarding_page_primary_text"),
            "text",
            "Not find Title of artcle",
            5);
    Assert.assertEquals(
            "Articale title have been changed after screen rotation",
            title_before_rotation,
            title_after_second_rotation
    );
  }
 /*
  @Test
  public void testChecTitleInBackground(){
    //Open APK
    waitForElementPresent(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);
    driver.runAppInBackground(10);

    waitForElementPresent(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"),
            "Cannot find article after returning from background", 5);

  }
*/
  @Test
  public void testAssertElementPresent() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();

    //Ввожу invalid переменную "English"
    String subtitle_of_language = "English";
    MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    String search_result_locator = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']";
    String empty_result_lable = "//*[@text= 'No languages found']";
    MainPageObject.waitForElementPresent(
            By.xpath(search_result_locator),
            "Can not find empty result by the request",
            15);
    MainPageObject.assertElementPresent(
            By.xpath(empty_result_lable),
            "Incorrectly We found some result by request");

    WebElement title = MainPageObject.waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Simple English']"),
            "Can not find title", 10);
    String subtitln = title.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitln);

  }
}
