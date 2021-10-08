import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class SignUpTest2 extends BaseTest {
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("C:\\Program Files (x86)\\Jenkins\\workspace\\selenium.project4\\target\\selenide-screens");

    private  SignUpPage signUpPage;

    @Test
    public void spotifyFailedSignUpCheckTextErrors() {
        signUpPage = new SignUpPage();
        signUpPage.successLogOn("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "Декабрь", 2012, true);

        Assertions.assertEquals(signUpPage.getErrorByNumber(0), "Вы не достигли возраста, с которого можно пользоваться сервисом Spotify.", "Ошибки не совпадают!");
        Assertions.assertFalse(signUpPage.isErrorVisible("Укажите действительный день месяца."));
    }


    @Test
    public void CheckPasswordError() {
        signUpPage = new SignUpPage();
      // Пароль слишком короткий.
                signUpPage.openPage().closeCookieMessage();
                signUpPage.addTextToEmailField("test@ya.ru")
                .addTextToEmailConfirmField("test@ya.ru")
                .addTextToPasswordField("1")
                .addTextToNameField("testUser")
                .setDayOfBirth(15)
               // .setMonth("12")
                .setYearOfBirth(1989)
                .clickSubmitButton();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(signUpPage.getErrorByNumber(0)).as("Текст ошибки не сходится!").isEqualTo("Пароль слишком короткий.");
        Assertions.assertTrue(signUpPage.isErrorVisible("Выберите месяц."), "Month Field Error is not visible!");
        softAssertions.assertAll();
    }


}
