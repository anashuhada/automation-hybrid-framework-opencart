package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement textEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement textPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement buttonLogin;

    public void setTextEmail(String email) {
        textEmail.sendKeys(email);
    }

    public void setTextPassword(String password) {
        textPassword.sendKeys(password);
    }

    public void clickButtonLogin() {
        js.executeScript("arguments[0].click()", buttonLogin);
    }
}
