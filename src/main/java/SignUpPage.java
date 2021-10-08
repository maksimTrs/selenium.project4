import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage  {
     private WebDriver driver;

    public  SignUpPage(WebDriver driver) {
       this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /*  public SignUpPage () {
            super();
        }
    */
    @FindBy(xpath = "//div[@id='__next']//div/h2")
    private WebElement h2HeaderData;

    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailField;

    @FindBy(css = "input#confirm[name='confirm']")
    private WebElement emailConfirmField;

    @FindBy(css = "div > input[type='password']")
    private WebElement passwordField;

    @FindBy(css = "input#displayname")
    private WebElement nameField;

    @FindBy(css = "input#day")
    private WebElement dayOfBirth;

    @FindBy(xpath = "//select[@id='month']")
    private WebElement monthDropDown;

    String monthDropDownOption = "//select[@id='month']/option[@value = '%s']"; // May = 05.
/*    @FindBy(xpath = "//select[@id='month']/option[@value = '05']") // May
    private WebElement monthDropDownOption;*/

    @FindBy(css = "input#year")
    private WebElement yearOfBirth;

    @FindBy(xpath = "//input[@id='gender_option_male']/following::label[1]")
    private WebElement maleGenderRadioButton;

    @FindBy(css = "input#gender_option_male")
    private WebElement femaleGenderRadioButton;

    @FindBy(xpath = "//input[@id='terms-conditions-checkbox']/following::label[1]")
    private WebElement termsConditionsCheckbox;

    @FindBy(xpath = "//div/label[@id='recaptcha-anchor-label']") //div[@id='rc-anchor-container']//div/span[@id='recaptcha-anchor']
    private WebElement recaptchaCheckBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@aria-label='Значок ошибки' and string-length(text())>0] | //div[@aria-label='Значок ошибки']/span[string-length(text())>0]")
    private List<WebElement> errorForEmptyFields;  // 10 fields

    String errorByText = "//div[@aria-label='Значок ошибки'][text()= '%s']";
    //Введите адрес электронной почты.   <<>>   Введите пароль.



    public String getH2HeaderValue() {
        return h2HeaderData.getText();
    }

    public SignUpPage addTextToEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignUpPage  addTextToEmailConfirmField(String email) {
        emailConfirmField.sendKeys(email);
        return this;
    }

    public SignUpPage  addTextToPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignUpPage  addTextToNameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public SignUpPage  setDayOfBirth(int birthDay) {
        dayOfBirth.sendKeys(String.valueOf(birthDay));
        return this;
    }

    public SignUpPage setMonth(String birthDay) {
        monthDropDown.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, birthDay)))).click();
       /* WebElement ee = driver.findElement((By.xpath(String.format(monthDropDownOption, birthDay))));
        ee.click();*/
        return this;
    }

    public SignUpPage  setYearOfBirth(int birthYear) {
        yearOfBirth.sendKeys(String.valueOf(birthYear));
        return this;
    }

    public SignUpPage setMaleGenderRadioButton() {
        maleGenderRadioButton.click();
        return this;
    }

    public SignUpPage setFemaleGenderRadioButton() {
        femaleGenderRadioButton.click();
        return this;
    }

    public SignUpPage setTermsConditionsCheckbox(boolean value) {
        if (!termsConditionsCheckbox.isSelected() == value)
            termsConditionsCheckbox.click();
        return this;
    }

    public void setRecaptchaCheckBox() {
        driver.switchTo().frame(0);
        recaptchaCheckBox.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recaptchaCheckBox.click();
        driver.switchTo().defaultContent();
    }

    public void clickSubmitButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
          submitButton.click();
   /*     String ss = driver.findElement(By.cssSelector("button[type='submit'] > div")).getText();
        System.out.println(ss);*/
    }

    public List<WebElement> getErrorFieldsList() {
        //return driver.findElements(By.xpath(errorForEmptyFields.toString()));
        return errorForEmptyFields;
    }

    public String getErrorByNumber(int number) {
        return getErrorFieldsList().get(number).getText();
    }

    public boolean isErrorVisible(String message) {
        return driver.findElements(By.xpath(String.format(errorByText, message))).size() > 0
                && driver.findElements(By.xpath(String.format(errorByText, message))).get(0).isDisplayed();
    }

    public void closeCookieMessage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='onetrust-group-container']")));
        driver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button")).click();
    }


    public void successLogOn(String email, String confirmEmail, String password, String userName, int dayBirth, String monthBirth,
                                  int yearBirth, boolean termsCondition) {
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
