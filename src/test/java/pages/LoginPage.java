package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

  private final By loginEmailLocator = By.name("belepes_email");
  private final By loginPasswordLocator = By.name("belepes_jelszo");
  private final By loginButtonLocator = By.id("blp_sbmt");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void login(String username, String password) {
    WebElement loginEmailElement = waitVisibilityAndFindElement(loginEmailLocator);
    WebElement loginPasswordElement = waitVisibilityAndFindElement(loginPasswordLocator);
    WebElement loginButtonElement = waitVisibilityAndFindElement(loginButtonLocator);

    loginEmailElement.sendKeys(username);
    loginPasswordElement.sendKeys(password);
    loginButtonElement.click();
  }
}
