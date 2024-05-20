
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.After;
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

public class HomePageFunctionalityTest {

  private WebDriver driver;
  private WebDriverWait wait;
  private Properties props;

  private HomePage homePage;

  @Before
  public void setup() throws IOException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-popup-blocking");
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    driver.get("https://gyakorikerdesek.hu/");
    //Sets cookie, so the popup does not show for "accepting cookies"
    Cookie cookieOk = new Cookie.Builder("cookieok", "1700000000000").domain(".gyakorikerdesek.hu").path("/").build();
    driver.manage().addCookie(cookieOk);
    driver.manage().window().maximize();

    wait = new WebDriverWait(driver, 10);
    //props = TestUtil.loadProps();

    //homePage = TestUtil.login(driver);
    homePage = new HomePage(driver);
  }
  private WebElement waitVisibiiltyAndFindElement(By locator) {
    this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this.driver.findElement(locator);
  }

  @Test
  public void testSidebarPageLoadsAndBackArrow() throws InterruptedException {
    homePage.handleCookies();

    //Easily extendable array for static page load checking.
    List<List<String>> sideBarList = List.of(
      List.of("//div[@id='alkategoria']/div[1]/a", "Állatok - Gyakori kérdések"),
      List.of("//div[@id='alkategoria']/div[2]/a", "Családi kapcsolatok - Gyakori kérdések"),
      List.of("//div[@id='alkategoria']/div[3]/a", "Egészség - Gyakori kérdések"),
      List.of("//div[@id='alkategoria']/div[4]/a", "Elektronikus eszközök - Gyakori kérdések"),
      List.of("//div[@id='alkategoria']/div[5]/a", "Emberek - Gyakori kérdések"),
      List.of("//div[@id='alkategoria']/div[6]/a", "Ételek, italok - Gyakori kérdések")
    );

    for(List<String> option : sideBarList) {
      WebElement optionElement = waitVisibiiltyAndFindElement(By.xpath(option.get(0)));
      optionElement.click();
      wait.until(ExpectedConditions.titleIs(option.get(1)));
      driver.navigate().back();
      wait.until(ExpectedConditions.titleIs("Gyakori kérdések és válaszok"));
    }
  }

  /*@After
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }*/
}
