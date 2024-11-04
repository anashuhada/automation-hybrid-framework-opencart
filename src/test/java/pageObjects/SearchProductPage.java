package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.internal.EclipseInterface;

import java.time.Duration;
import java.util.List;

public class SearchProductPage extends BasePage {

    public SearchProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement fieldSearch;

    @FindBy(xpath = "//select[@name='category_id']")
    WebElement dropdownCategory;

    @FindBy(xpath = "//input[@id='button-search']")
    WebElement buttonSearch;

    @FindBy(xpath = "//div[@class='product-thumb']")
    List<WebElement> listProductThumb;

    @FindBy(xpath = "//div[@class='product-thumb']//div[@class='caption']//h4//a")
    List<WebElement> listProductCaption;

    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement textTitle;

    @FindBy(xpath = "//div[@id='content']//ul//li//h2")
    WebElement textPrice;

    @FindBy(xpath = "//input[@id='input-quantity']")
    WebElement textQuantity;

    @FindBy(xpath = "//a[normalize-space()='Description']")
    WebElement tabDescription;

    @FindBy(xpath = "//a[normalize-space()='Specification']")
    WebElement tabSpecification;

    @FindBy(xpath = "//a[normalize-space()='Reviews (0)']")
    WebElement tabReviews;

    @FindBy(xpath = "//ul[@class='thumbnails']//li//a")
    List<WebElement> thumbnailImages;

    @FindBy(xpath = "//button[@title='Next (Right arrow key)']")
    WebElement nextArrow;

    @FindBy(xpath = "//button[@title='Next (Right arrow key)']")
    WebElement previousArrow;

    @FindBy(xpath = "//button[normalize-space()='×']")
    WebElement closeButton;

    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    WebElement buttonWishList;

    @FindBy(xpath = "//textarea[@id='input-review']")
    WebElement textReview;

    @FindBy(xpath = "//input[@type='radio']")
    List<WebElement> buttonRating;

    @FindBy(xpath = "//button[@id='button-review']")
    WebElement buttonReview;

    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement buttonAddCart;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement messageSuccess;

    public void setFieldSearch(String search) throws InterruptedException {
        fieldSearch.sendKeys(search);
        Thread.sleep(2000);
        act.moveToElement(fieldSearch).sendKeys(Keys.ENTER).perform();
    }

    public void getDropDownCategory() {
        dropdownCategory.click();
        Select slt = new Select(dropdownCategory);
        List<WebElement> options = slt.getOptions();
        for(WebElement o : options) {
            // System.out.println(o.getText() + " - " + o.getAttribute("value"));
            if(o.getAttribute("value").equals("20")) {
                o.click();
                break;
            }
        }
    }

    public void clickButtonSearch() {
        buttonSearch.click();
    }

    public void productThumb() {
        // System.out.println("Number of list: " + listProductThumb.size());
        int countProductThumb = listProductThumb.size();
        if(countProductThumb > 0 ) {
            for(WebElement caption : listProductCaption) {
                // System.out.println(caption.getText());
                if(caption.getText().equals("MacBook")) {
                    caption.click();
                    break;
                }
            }
        }
        else {
            System.out.println("There's no product displayed.");
        }
    }

    public void clickThumbnailImages() throws InterruptedException {
        int countThumbnail = thumbnailImages.size();
        // System.out.println("Count thumbnail: " + countThumbnail);
        for(WebElement thumbnail : thumbnailImages) {
            thumbnail.click();
            Thread.sleep(2000);

            for (int i = 0; i < countThumbnail - 1; i++) {
                nextArrow.click();
                Thread.sleep(2000);
            }
            closeButton.click();
            return;
        }
    }

    public void tabs(String review) throws InterruptedException {

        js.executeScript("arguments[0].scrollIntoView(true)", tabDescription);
        act.moveToElement(tabDescription)
                .click()
                .perform();

        Thread.sleep(2000);

        act.moveToElement(tabSpecification)
                .click()
                .perform();

        Thread.sleep(2000);

        act.moveToElement(tabReviews)
                .click()
                .perform();

        textReview.sendKeys(review);

        for(WebElement rating : buttonRating) {
            if(rating.getAttribute("value").equals("4")) {
                rating.click();
                break;
            }

            wait.until(ExpectedConditions.elementToBeClickable(buttonReview)).click();

        }

    }

    public void clickAddToWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonWishList)).click();
    }

    public String messageAddToWishList() {
        try {
            return messageSuccess.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public String getTitle() {
        try {
            return textTitle.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public String getPrice() {
        try {
            return textPrice.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void setQuantity(String qty) {
        textQuantity.clear();
        textQuantity.sendKeys(qty);
    }

    public void clickButtonAddCart() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddCart)).click();
    }

    public String getMessageSuccess() {
        try {
            wait.until(ExpectedConditions.visibilityOf(messageSuccess));
            return messageSuccess.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }
}
