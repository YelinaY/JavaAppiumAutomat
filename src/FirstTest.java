import io.appium.java_client.TouchAction;
import lib.CoreTestCaes;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;




public class FirstTest extends CoreTestCaes {

  @Test
  public void testSwipeToLeftAndRite(){
    waitForElementPresent(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 20);

    waitForElementPresentAndClick(
            By.xpath("//android.widget.ImageView[@content-desc=\"Continue\"]"),
                   "Cannot find element",
                    40);
    waitForElementPresent(
           By.xpath("//*[contains(@text,'New ways to explore')]"), "Cannot find element", 30);
   swipElementToLeft(
           By.xpath("//*[contains(@text,'New ways to explore')]"), "Cannot find element");

    waitForElementPresent(
            By.xpath("//*[contains(@text,'Reading lists with sync')]"), "Cannot find element", 15);
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

    //Ввожу переменную "English"
    String subtitle_of_language = "English";
    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

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


  @Test
  public void testAmountOfNotEmptySearch() {
    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);

    //Ввожу invalid переменную "fuyfyuffuy"
    String subtitle_of_language = "fuyfyuffuy";
    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    String search_result_locator = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']";
     String empty_result_lable = "//*[@text= 'No languages found']";
waitForElementPresent(
        By.xpath(empty_result_lable),
        "Can not find empty result by the request",
        15);
assertElementPresent(
        By.xpath(search_result_locator),
        "Incorrectly We found some result by request");

  }
  @Test
  public void testAddAndRemoveNewLanguage () {
    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);

    //Ввожу переменную "English"
    String subtitle_of_language = "English";
    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']"), "Cannot find element search by the text 'Old English'", 15);
    //Проверка на наличие теска (assert - exp. result Text "Simple English")
    WebElement subtitle = waitForElementPresent(By.id("org.wikipedia:id/language_subtitle"), "Can not find subtitle", 10);
    String subtitl = subtitle.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitl);

    //Добавляю 2 языка
    waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']"), "Cannot find element search by the text 'Old English'", 15);
    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);
    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);
    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);
    waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Simple English']"), "Cannot find element search by the text 'Old English'", 15);
    //Ожидаю папку Wikipedia languages с сохраненными языками
    waitForElementPresent(By.xpath("//*[contains(@text,'Wikipedia languages')]"), "Cannot find element", 5);

    //Проверяю что 2 языка добавлены в папку
    WebElement titleSimpleEnglish = waitForElementPresent(By.xpath("//*[contains(@text,'Simple English')]"), "Cannot find element", 5);
    String titleSimpleEnglissh = titleSimpleEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", titleSimpleEnglissh);

    WebElement titleOldEnglish = waitForElementPresent(By.xpath("//*[contains(@text,'Ænglisc')]"), "Cannot find element", 5);
    String titleOldEnglissh = titleOldEnglish.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Ænglisc", titleOldEnglissh);

    //Удаляю языки из папки
    waitForElementPresentAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/content']"), "Cannot find element search by the text 'Remove language'", 15);
    //Выбор № 2 из списка элементов
    List <WebElement> elements = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    elements.get(1).click();

    //Выбор № 3 из списка элементов
    List <WebElement> element = (List <WebElement>) driver.findElements(By.id("org.wikipedia:id/wiki_language_title"));
    element.get(2).click();

    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc='Delete selected items']"), "Cannot find element", 5);

    waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/alertTitle']"),
           "Cannot find element", 10);

    List <WebElement> buttonOK = (List<WebElement>) driver.findElementsById("android:id/button1");
    buttonOK.get(0).click();

    waitForElementPresent(By.xpath("//*[contains(@text,'Wikipedia languages')]"), "Cannot find element", 5);
//Проверить что удаленные ранее элементы не присутствуют на странице
   waitForElementNotPresent(
            By.xpath("//*[contains(@text,'Ænglisc')]"), "Cannot find element", 5);

    waitForElementNotPresent(
            By.xpath("//*[contains(@text,'Simple English')]"), "Cannot find element", 5);
      }

   private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  @Test
  public void testChangeScreanOrientation(){
    //Open APK
    waitForElementPresent(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    String title_before_rotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_onboarding_page_primary_text"),
            "text",
            "Not find Title of artcle",
            5);
    driver.rotate(ScreenOrientation.LANDSCAPE);

    String title_after_rotation = waitForElementAndGetAttribute(
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
    String title_after_second_rotation = waitForElementAndGetAttribute(
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
    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD OR EDIT LANGUAGES')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//*[contains(@text,'ADD LANGUAGE')]"), "Cannot find element", 5);

    waitForElementPresentAndClick(By.xpath("//android.widget.TextView[@content-desc=\"Search for a language\"]"), "Cannot find element", 5);

    //Ввожу invalid переменную "English"
    String subtitle_of_language = "English";
    waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search for a language')]"), subtitle_of_language, "Cannot find element", 5);

    String search_result_locator = "//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Old English']";
    String empty_result_lable = "//*[@text= 'No languages found']";
    waitForElementPresent(
            By.xpath(search_result_locator),
            "Can not find empty result by the request",
            15);
    assertElementPresent(
            By.xpath(empty_result_lable),
            "Incorrectly We found some result by request");

    WebElement title = waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/languages_list_recycler']//*[@text='Simple English']"),
            "Can not find title", 10);
    String subtitln = title.getAttribute("text");
    Assert.assertEquals("We see unexpected subtitle", "Simple English", subtitln);

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
  protected void swipElementToLeft (By by, String error_message)
  {
    WebElement element = waitForElementPresent(by, error_message, 10);

    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y)/2;

    TouchAction action = new TouchAction(driver);
    action
            .press(right_x, middle_y)
            .waitAction(300)
            .moveTo(left_x, middle_y)
            .release()
            .perform();
  }
  private int getAmountOfElements (By by){
    List elements = driver.findElements(by);
    return elements.size();
  }
  private  void assertElementPresent(By by, String error_message){
    int amount_of_elements = getAmountOfElements(by);
    if (amount_of_elements>0){
      String default_message = "An element '" + by.toString() + "' Supposed to be not present";
      throw  new AssertionError(default_message + " " + error_message);
    }
  }
  //Получение атрибутов элемента (название сттьи
  private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds){
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    return  element.getAttribute(attribute);
  }

}
