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

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {

     private static WebDriver driver;
     SignUpPage signUpPage;

/*
    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }*/

    @BeforeEach
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
        } else {
            // driver.manage().window().setSize(new Dimension(1920,1080));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        signUpPage =  new SignUpPage(driver);
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        driver.get("https://www.spotify.com/ru-ru/signup");
    }

    @AfterEach
      void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

