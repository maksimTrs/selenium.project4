import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class SignUpTest extends BaseTest{


    @Test
    public void spotifyFailedSignUpCheck() {
        signUpPage.successLogOn("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "05", 2012, true);

      //  SoftAssertions softAssert = new SoftAssertions();
      //  softAssert.assertThat(signUpPage.getH2HeaderValue()).as("Осталась страница регистрации!").isNotEqualTo("Зарегистрируйтесь и слушайте бесплатно");
        Assertions.assertNotEquals("Зарегистрируйтесь и слушайте бесплатно", signUpPage.getH2HeaderValue(), "Осталась страница регистрации!");
      //  softAssert.assertAll();
    }

}
//mvn -Dtest=SignUpTest,SignUpTest2 test