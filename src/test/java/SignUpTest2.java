import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignUpTest2 extends BaseTest {

    @Test
    public void spotifyFailedSignUpCheckTextErrors() {
        signUpPage.successLogOn("test@ya.ru","test@ya.ru", "test@ya.ruPASSWORD",
                "testUser", 11, "05", 2012, true);

        Assertions.assertEquals(signUpPage.getErrorByNumber(0), "Вы не достигли возраста, с которого можно пользоваться сервисом Spotify.", "Ошибки не совпадают!");
        Assertions.assertFalse(signUpPage.isErrorVisible("Укажите действительный день месяца."));
    }

    @Test
    public void CheckPasswordError() {
      // Пароль слишком короткий.
        signUpPage.closeCookieMessage();
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
