package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement textFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement textLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement textEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement textTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement textPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement textConfirmPassword;

    @FindBy(xpath = "//label[@class='radio-inline']")
    List<WebElement> listSubscribe;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkPrivacyPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement buttonContinue;

    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement messageConfirmation1;

    @FindBy(xpath = "//div[@id='content']//p[1]")
    WebElement messageConfirmation2;

    // action methods
    public void setTextFirstName(String firstName) {
        textFirstName.sendKeys(firstName);
    }

    public void setTextLastName(String lastName) {
        textLastName.sendKeys(lastName);
    }

    public void setTextEmail(String email) {
        textEmail.sendKeys(email);
    }

    public void setTextTelephone(String telephone) {
        textTelephone.sendKeys(telephone);
    }

    public void setTextPassword(String password) {
        textPassword.sendKeys(password);
    }

    public void setTextConfirmPassword(String password) {
        textConfirmPassword.sendKeys(password);
    }

    public void setCheckSubscribe() {
       for(WebElement subs : listSubscribe) {
           try {
               if(subs.getText().equals("Yes")) {
                   js.executeScript("arguments[0].click()", subs);
                   break;
               }
           } catch(Exception e) {
               System.out.println(e.getMessage());
           }
       }
    }

    public void setCheckPrivacyPolicy() {
        js.executeScript("arguments[0].click()", checkPrivacyPolicy);
    }

    public void clickButtonContinue() {
        js.executeScript("arguments[0].click()", buttonContinue);
    }

    public String confirmationMessage1() {
        try {
            return messageConfirmation1.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public String confirmationMessage2() {
        try {
            return messageConfirmation2.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }
}
