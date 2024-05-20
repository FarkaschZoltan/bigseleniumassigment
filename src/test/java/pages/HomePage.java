package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {

  private final By loginButtonLocator;
  private final By cookieAcceptLocator;
  private final By cookieAcceptAgainLocator;
  private final By settingsLocator;
  private final By logoutLocator;


  public HomePage(WebDriver driver) {
    super(driver);

    loginButtonLocator = By.xpath("//div[@class='flex menu1']/div[2]/a[2]");
    cookieAcceptLocator = By.xpath("//button[text()='Elfogadom']");
    cookieAcceptAgainLocator = By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button");
    settingsLocator = By.xpath("//div[@class='menu']/div[5]/a");
    logoutLocator = By.xpath("//div[@class='menu']/div[6]");
  }

  public void handleCookies() {
    WebElement cookieAccept = driver.findElement(cookieAcceptLocator);
    cookieAccept.click();

    WebElement cookieAcceptAgain = driver.findElement(cookieAcceptAgainLocator);
    cookieAcceptAgain.click();
  }

  public void pressLoginButton() {
    WebElement loginButton = waitVisibilityAndFindElement(loginButtonLocator);
    loginButton.click();
  }

  public void pressSettingsButton() {
    WebElement settingsButton = waitVisibilityAndFindElement(settingsLocator);
    settingsButton.click();
  }

  public void logout() {
    WebElement logoutButton = waitVisibilityAndFindElement(logoutLocator);
    logoutButton.click();
  }
}
