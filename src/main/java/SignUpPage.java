import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.SelenideElement.*;

public class SignUpPage  {


    private By h2HeaderData = By.xpath("//div[@id='__next']//div/h2");

/*    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailField;*/
    private By emailField = By.xpath("//input[@id = 'email']");

    private By emailConfirmField = By.cssSelector("input#confirm[name='confirm']");

    private By passwordField = By.cssSelector("div > input[type='password']");

    private By nameField = By.cssSelector("input#displayname");

    private By dayOfBirth = By.cssSelector("input#day");

    private By monthDropDown = By.xpath("//select[@id='month']");

    String monthDropDownOption = "//select[@id='month']/option[@value = '%s']"; // May = 05.
/*    @FindBy(xpath = "//select[@id='month']/option[@value = '05']") // May
    private WebElement monthDropDownOption;*/

    private By yearOfBirth = By.cssSelector("input#year");

    private By maleGenderRadioButton = By.xpath("//input[@id='gender_option_male']/following::label[1]");

    private By femaleGenderRadioButton = By.cssSelector("input#gender_option_male");

    private By termsConditionsCheckbox = By.xpath("//input[@id='terms-conditions-checkbox']/following::label[1]");

   // @FindBy(xpath = "//div/label[@id='recaptcha-anchor-label']") //div[@id='rc-anchor-container']//div/span[@id='recaptcha-anchor']
    private By recaptchaCheckBox = By.xpath("//div/label[@id='recaptcha-anchor-label']");


    private By submitButton = By.xpath("//button[@type='submit']");

   /* @FindBy(xpath = "//div[@aria-label='Значок ошибки' and string-length(text())>0] | //div[@aria-label='Значок ошибки']/span[string-length(text())>0]")
    private List<WebElement> errorForEmptyFields;  // 10 fields*/
    private By errorForEmptyFields = By.xpath("//div[@aria-label='Значок ошибки' and string-length(text())>0] | //div[@aria-label='Значок ошибки']/span[string-length(text())>0]");

    String errorByText = "//div[@aria-label='Значок ошибки'][text()= '%s']";
    //Введите адрес электронной почты.   <<>>   Введите пароль.


    public SignUpPage openPage() {
        Selenide.open("https://www.spotify.com/ru-ru/signup");
        return this;
    }

    public String getH2HeaderValue() {
        return $(h2HeaderData).getText();
    }

    public SignUpPage addTextToEmailField(String email) {
        $(emailField).setValue(email);
      //  $(errorByText).val(email);
        return this;
    }

    public SignUpPage  addTextToEmailConfirmField(String email) {
        $(emailConfirmField).val(email);
        return this;
    }

    public SignUpPage  addTextToPasswordField(String password) {
        $(passwordField).val(password);
        return this;
    }

    public SignUpPage  addTextToNameField(String name) {
        $(nameField).val(name);
        return this;
    }

    public SignUpPage  setDayOfBirth(int birthDay) {
        $(dayOfBirth).val(String.valueOf(birthDay));
        return this;
    }

    public SignUpPage setMonth(String birthDay) {
        $(monthDropDown).selectOption(birthDay);
      /*  monthDropDown.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, birthDay)))).click();*/
       /* WebElement ee = driver.findElement((By.xpath(String.format(monthDropDownOption, birthDay))));
        ee.click();*/
        return this;
    }

    public SignUpPage  setYearOfBirth(int birthYear) {
        $(yearOfBirth).val(String.valueOf(birthYear));
        return this;
    }

    public SignUpPage setMaleGenderRadioButton() {
        $(maleGenderRadioButton).click();
        return this;
    }

    public SignUpPage setFemaleGenderRadioButton() {
        $(femaleGenderRadioButton).click();
        return this;
    }

    public SignUpPage setTermsConditionsCheckbox(boolean value) {
        if (!$(termsConditionsCheckbox).isSelected() == value)
            $(termsConditionsCheckbox).click();
        return this;
    }


    public void clickSubmitButton() {
        //JavascriptExecutor js = (JavascriptExecutor) driver;
      //  js.executeScript("window.scrollBy(0,250)");
        executeJavaScript("window.scrollBy(0,250)");
          $(submitButton).click();
   /*     String ss = driver.findElement(By.cssSelector("button[type='submit'] > div")).getText();
        System.out.println(ss);*/
    }

    public ElementsCollection getErrorFieldsList() {
        //return driver.findElements(By.xpath(errorForEmptyFields.toString()));
        return $$(errorForEmptyFields);
    }

    public String getErrorByNumber(int number) {
        return getErrorFieldsList().get(number).getText();
    }

    public boolean isErrorVisible(String message) {
        return $(By.xpath(String.format(errorByText, message))).isDisplayed();
    }

    public void closeCookieMessage() {
       /* new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='onetrust-group-container']")));*/
        $(By.xpath("//div[@id='onetrust-group-container']")).shouldBe(exist); // .waitUntil(exist, 10000);
        $(By.xpath("//div[@id='onetrust-close-btn-container']/button")).click();
    }


    public void successLogOn(String email, String confirmEmail, String password, String userName, int dayBirth, String monthBirth,
                                  int yearBirth, boolean termsCondition) {
        openPage();
        closeCookieMessage();
        addTextToEmailField(email);
        addTextToEmailConfirmField(confirmEmail);
        addTextToPasswordField(password);
        addTextToNameField(userName);
        setDayOfBirth(dayBirth);
        setMonth(monthBirth);
        setYearOfBirth(yearBirth);
        setMaleGenderRadioButton();
        setTermsConditionsCheckbox(termsCondition);
       // setRecaptchaCheckBox();
        clickSubmitButton();
        //return new LogOnPage();
    }
}
