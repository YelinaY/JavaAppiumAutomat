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

    //Ввожу invalid переменную "fuyfyuffuy"
    String subtitle_of_language = "fuyfyuffuy";
    MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    String search_result_locator = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']";
     String empty_result_lable = "//*[@text= 'No languages found']";
    MainPageObject.waitForElementPresent(
        By.xpath(empty_result_lable),
        "Can not find empty result by the request",
        15);
    MainPageObject.assertElementPresent(
        By.xpath(search_result_locator),
        "Incorrectly We found some result by request");

  }
  @Test
  public void testAddAndRemoveNewLanguage () {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.clickOnButtonsLanguages();
    SearchPageObject.iconSearch();

    //Ввожу переменную "English"
    String subtitle_of_language = "English";
    MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']"), "Cannot find element search by the text 'Old English'", 15);
    //Проверка на наличие теска (assert - exp. result Text "Simple English")
    WebElement subtitle = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/language_subtitle"), "Can not find subtitle", 10);
    String subtitl = subtitle.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitl);

    //Добавляю 2 языка
    MainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']"), "Cannot find element search by the text 'Old English'", 15);
    MainPageObject.waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);
    MainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);
    MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);
    MainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Simple English']"), "Cannot find element search by the text 'Old English'", 15);
    //Ожидаю папку Wikipedia languages с сохраненными языками
    MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Wikipedia languages')]"), "Cannot find element", 5);

    //Проверяю что 2 языка добавлены в папку
    WebElement titleSimpleEnglish = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Simple English')]"), "Cannot find element", 5);
    String titleSimpleEnglissh = titleSimpleEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", titleSimpleEnglissh);

    WebElement titleOldEnglish = MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Ænglisc')]"), "Cannot find element", 5);
    String titleOldEnglissh = titleOldEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Ænglisc", titleOldEnglissh);

    //Удаляю языки из папки
    MainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"), "Cannot find element", 5);

    MainPageObject.waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/content']"), "Cannot find element search by the text 'Remove language'", 15);
    //Выбор № 2 из списка элементов
    List <WebElement> elements = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    elements.get(1).click();

    //Выбор № 3 из списка элементов
    List <WebElement> element = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    element.get(2).click();

    MainPageObject.waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc='Delete selected items']"), "Cannot find element", 5);

    MainPageObject.waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/alertTitle']"),
           "Cannot find element", 10);

    List <WebElement> buttonOK = (List<WebElement>) driver.findElementsById("android:id/button1");
    buttonOK.get(0).click();

    MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text,'Wikipedia languages')]"), "Cannot find element", 5);
//Проверить что удаленные ранее элементы не присутствуют на странице
    MainPageObject.waitForElementNotPresent(
            By.xpath("//*[contains(@text,'Ænglisc')]"), "Cannot find element", 5);

    MainPageObject.waitForElementNotPresent(
            By.xpath("//*[contains(@text,'Simple English')]"), "Cannot find element", 5);
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
