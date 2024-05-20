package pages;

import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {

  private final By saveChangesButtonLocator = By.id("adatval_sbmt");
  private final By nameVisibleYesLocator =
    By.xpath("//form[@id='adatval']/div/div[2]/div[2]/label/input[@name='publikus_nev'][@value='1']");
  private final By nameVisibleNoLocator =
    By.xpath("//form[@id='adatval']/div/div[2]/div[2]/label/input[@name='publikus_nev'][@value='0']");
  private final By interestingQuestionsNewsletterYesLocator =
    By.xpath("//input[@name='hirlevelet_ker'][@value='1']");
  private final By interestingQuestionsNewsletterNoLocator =
    By.xpath("//input[@name='hirlevelet_ker'][@value='0']");
  private final By settingsSavedLocator = By.cssSelector("div.ok");
  private final By newsLetterSettingsButtonLocator =
    By.xpath("//div[@class='flex kismenu']/div[3]");

  public SettingsPage(WebDriver driver) {
    super(driver);
  }

  public void pressNewsletterSettings(){
    WebElement newsletterSettingsButton = driver.findElement(newsLetterSettingsButtonLocator);
    newsletterSettingsButton.click();
  }

  public void saveChanges() {
    WebElement saveChangesButton = waitVisibilityAndFindElement(saveChangesButtonLocator);
    saveChangesButton.click();
  }

  public boolean readNameVisible() {
    return readRadio(nameVisibleYesLocator);
  }

  public boolean readInterestingQuestionsNewsletter() {
    return readRadio(interestingQuestionsNewsletterYesLocator);
  }

  public void toggleNameVisible() {
    radioToggle(nameVisibleYesLocator, nameVisibleNoLocator);
  }

  public void toggleInterestingQuestionsNewsletter() {
    radioToggle(interestingQuestionsNewsletterYesLocator, interestingQuestionsNewsletterNoLocator);
  }

  public WebElement getSettingsSaved() {
    return waitVisibilityAndFindElement(settingsSavedLocator);
  }

  private void radioToggle(By yes, By no) {
    WebElement yesElement = waitVisibilityAndFindElement(yes);
    WebElement noElement = waitVisibilityAndFindElement(no);
    if(readRadio(yes)) {
      noElement.click();
      return;
    }
    yesElement.click();
  }

  private boolean readRadio(By by) {
    WebElement radioButton = waitVisibilityAndFindElement(by);

    return Objects.equals(radioButton.getAttribute("checked"), "true");
  }

}
