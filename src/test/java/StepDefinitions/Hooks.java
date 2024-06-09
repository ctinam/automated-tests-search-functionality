package StepDefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

public class Hooks {

  protected static WebDriver driver = new FirefoxDriver();
  private static String baseURL = "https://magento.softwaretestingboard.com";
  
  @Before
  public void openBrowser() throws Exception {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.navigate().to(baseURL);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
  }

  @After("@last")
  public void closeBrowser(Scenario scenario) throws InterruptedException {
    if (driver != null) {
      driver.close();
      driver.quit();
    }

  }



}
