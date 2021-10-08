import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

@ExtendWith({ScreenShooterExtension.class})
public class SignUpTest extends BaseTest{
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("C:\\Program Files (x86)\\Jenkins\\workspace\\selenium.project4\\target\\selenide-screens");

    private  SignUpPage signUpPage;

    @Test
    public void spotifyFailedSignUpCheck() {
        signUpPage = new SignUpPage();
        signUpPage.successLogOn("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "Май", 2012, true);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(signUpPage.getH2HeaderValue()).as("Осталась страница регистрации!").isEqualTo("Зарегистрируйтесь и слушайте бесплатно");
        //Assertions.assertNotEquals("Зарегистрируйтесь и слушайте бесплатно", signUpPage.getH2HeaderValue(), "Осталась страница регистрации!");

        softAssert.assertThat(signUpPage.getErrorFieldsList().size()).as("Кол-во ошибок не совпадает!").isEqualTo(2);
        softAssert.assertAll();
       // Assertions.assertEquals(1, signUpPage.getErrorFieldsList().size(), "Кол-во ошибок не совпадает!");
        System.out.println("Test <SignUpTest> <spotifyFailedSignUpCheck()>  Passed!");

    }

}
//mvn -Dtest=SignUpTest,SignUpTest2 test