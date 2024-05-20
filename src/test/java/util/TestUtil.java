package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class TestUtil {
  public static Properties loadProps() throws IOException {
    Properties props = new Properties();
    FileReader fr = new FileReader("configurations.properties");
    props.load(fr);
    fr.close();

    return props;
  }

  public static HomePage login(WebDriver driver) throws IOException {
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);

    Properties props = loadProps();

    homePage.handleCookies();
    homePage.pressLoginButton();
    loginPage.login(props.getProperty("username"), props.getProperty("password"));

    return homePage;
  }
}
