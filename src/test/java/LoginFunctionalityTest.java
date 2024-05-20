import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import util.TestUtil;

public class LoginFunctionalityTest {

  private WebDriver driver;
  private WebDriverWait wait;
  private Properties props;

  @Before
  public void setup() throws IOException {
    ChromeOptions options = new ChromeOptions();
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    driver.get("https://gyakorikerdesek.hu/");

    //Sets cookie, so the popup does not show for "accepting cookies"
    Cookie cookieOk = new Cookie.Builder("cookieok", "1700000000000").domain(".gyakorikerdesek.hu").path("/").build();
    driver.manage().addCookie(cookieOk);

    driver.manage().window().maximize();

    wait = new WebDriverWait(driver, 10);

    props = TestUtil.loadProps();
  }

  private final By logoutLocator = By.xpath("//div[@class='menu']/div[6]");
  private final By loginButtonLocator = By.xpath("//div[@class='flex menu1']/div[2]/a[2]");

  private WebElement waitVisibiiltyAndFindElement(By locator) {
    this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this.driver.findElement(locator);
  }

  @Test
  public void testLoginAndLogout() throws InterruptedException, IOException {
    HomePage homePage = TestUtil.login(driver);

    //waiting for screen change
    wait.until(ExpectedConditions.urlToBe("https://www.gyakorikerdesek.hu/belepes"));

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(props.getProperty("username"), props.getProperty("password"));

    //waiting for screen change
    wait.until(ExpectedConditions.urlToBe("https://www.gyakorikerdesek.hu/"));

    WebElement logoutButton = waitVisibiiltyAndFindElement(logoutLocator);

    Assert.assertTrue(logoutButton.isDisplayed());

    homePage.logout();

    WebElement loginButton = waitVisibiiltyAndFindElement(loginButtonLocator);
    Assert.assertTrue(loginButton.isDisplayed());
  }

  @After
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }
}
