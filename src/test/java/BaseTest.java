import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Configuration.*;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {



    @BeforeAll
    static void setUp() {
        boolean switcher = true;
        Configuration.browser = "chrome";
        if (switcher) {
           // Configuration.browser = "chrome";
            Configuration.headless = true;
            Configuration.browserSize = "1920x1080";
        }
        else if (!switcher) {
            Configuration.headless = false;
            Configuration.browserSize = "1920x1080";
        }
        baseUrl = "https://www.spotify.com/ru-ru/signup";
      //  WebDriverRunner.getWebDriver().findElement();
       // WebDriverRunner.setWebDriver(new FirefoxDriver());
      //  Configuration.reportsFolder = "C:\\Program Files (x86)\\Jenkins\\workspace\\selenium.project4\\selenide-screens1";
    }

    @AfterEach
    void tearDown() {
       // WebDriverRunner.closeWebDriver();
        WebDriverRunner.clearBrowserCache();
    }
    }

  /*  @BeforeEach
    void setupTest() {
        boolean switcher = true;
        if (switcher) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.setHeadless(true);
       // options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        signUpPage =  new SignUpPage(driver);
        } else if (!switcher) {
            // driver.manage().window().setSize(new Dimension(1920,1080));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        signUpPage =  new SignUpPage(driver);
        driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        }
        driver.get("https://www.spotify.com/ru-ru/signup");
    }*/

  /*  @AfterEach
      void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/


