import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.SettingsPage;
import util.TestUtil;

public class SettingsFunctionalityTest {

  private WebDriver driver;
  private WebDriverWait wait;

  private HomePage homePage;
  private SettingsPage settingsPage;

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

    homePage = TestUtil.login(driver);
    settingsPage = new SettingsPage(driver);
  }

  @Test
  public void testToggleNameVisible() throws InterruptedException {
    Thread.sleep(2000);
    driver.get("https://gyakorikerdesek.hu/");

    homePage.pressSettingsButton();
    Thread.sleep(2000);
    Assert.assertEquals("https://www.gyakorikerdesek.hu/adatmodositas", driver.getCurrentUrl());

    for (int i = 0; i < 2; i++) {
      boolean originalValue = settingsPage.readNameVisible();
      if (originalValue) {
        settingsPage.toggleNameVisible();
        Assert.assertFalse(settingsPage.readNameVisible());
      } else {
        settingsPage.toggleNameVisible();
        Assert.assertTrue(settingsPage.readNameVisible());
      }
      settingsPage.saveChanges();
      Assert.assertTrue(settingsPage.getSettingsSaved().isDisplayed());
      driver.navigate().refresh();
      Assert.assertNotEquals(originalValue, settingsPage.readNameVisible());
    }
  }

  @Test
  public void testInterestingQuestionsNewsletterToggle() throws InterruptedException {
    Thread.sleep(2000);
    driver.get("https://gyakorikerdesek.hu/");

    homePage.pressSettingsButton();
    Thread.sleep(2000);
    Assert.assertEquals("https://www.gyakorikerdesek.hu/adatmodositas", driver.getCurrentUrl());

    settingsPage.pressNewsletterSettings();
    Thread.sleep(2000);
    Assert.assertEquals("https://www.gyakorikerdesek.hu/hirlevel", driver.getCurrentUrl());

    for (int i = 0; i < 2; i++) {
      boolean originalValue = settingsPage.readInterestingQuestionsNewsletter();
      if (originalValue) {
        settingsPage.toggleInterestingQuestionsNewsletter();
        Assert.assertFalse(settingsPage.readInterestingQuestionsNewsletter());
      } else {
        settingsPage.toggleInterestingQuestionsNewsletter();
        Assert.assertTrue(settingsPage.readInterestingQuestionsNewsletter());
      }
      settingsPage.saveChanges();
      Assert.assertTrue(settingsPage.getSettingsSaved().isDisplayed());
      driver.navigate().refresh();
      Assert.assertNotEquals(originalValue, settingsPage.readInterestingQuestionsNewsletter());
    }
  }

  @After
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }
}
