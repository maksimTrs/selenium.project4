import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;




public abstract class AbstractPage {
    WebDriver driver;

    public AbstractPage() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

  /*  public void getHomePage() {
        driver.get("https://www.spotify.com/ru-ru/signup");
    }*/



}
