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


}
