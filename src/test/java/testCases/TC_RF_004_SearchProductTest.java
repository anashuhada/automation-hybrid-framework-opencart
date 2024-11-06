package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchProductPage;
import testBase.BaseClass;

public class TC_RF_004_SearchProductTest extends BaseClass {

    @Test
    public void verifySearchProduct() {
        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            logger.info("*** Clicking on MyRegister link ***");

            hp.clickLinkLogin();
            logger.info("*** Clicking on Login link ***");

            LoginPage lp = new LoginPage(driver);

            logger.info("*** Entering login details ***");

            lp.setTextEmail(prop.getProperty("email"));
            logger.info("*** Entering registered email ***");

            lp.setTextPassword(prop.getProperty("password"));
            logger.info("*** Entering registered password ***");

            lp.clickButtonLogin();
            logger.info("*** Clicking on Login button ***");

            SearchProductPage sp = new SearchProductPage(driver);
            sp.setFieldSearch(prop.getProperty("searchProductName"));
            logger.info("*** Searching for MacBook ***");

            sp.getDropDownCategory();
            sp.clickButtonSearch();
            sp.productThumb();

            sp.clickThumbnailImages();
            logger.info("*** Clicking thumbnail images ***");

            sp.clickAddToWishList();
            logger.info("*** Adding the product to Wish List ***");

            String msgWishList = sp.messageAddToWishList();
            if(msgWishList.contains("wish list")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
            logger.info("*** Verifying the success message ***");

            sp.tabs("Test the review section.");
            logger.info("*** Clicking the menu tabs ***");

            String textTitle = sp.getTitle();
            Assert.assertEquals(textTitle, "MacBook", "Wrong product searched.");
            logger.info("*** Verifying the title ***");

            String textPrice = sp.getPrice();
            if (textPrice.equals("$602.00")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
            logger.info("*** Verifying the price ***");

            sp.setQuantity("10");
            logger.info("*** Updating the total quantity of the order ***");

            sp.clickButtonAddCart();
            logger.info("*** Clicking the Add to Cart button ***");

            String success = sp.messageAddToCart();
            if (success.contains("Success: You have added MacBook to your shopping cart!")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
            logger.info("*** Verifying the success message ***");

        } catch (Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }

        logger.info("*** Finished TC_RF_004_SearchProductTest ***");

    }
}