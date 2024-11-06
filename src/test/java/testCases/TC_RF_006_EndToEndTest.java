package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import testBase.BaseClass;

public class TC_RF_006_EndToEndTest extends BaseClass {

    @Test(groups = "EndToEnd")
    public void verifyEndToEndTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        try {
            // account registration
            logger.info("*** Starting TC_RF_001_AccountRegistrationTest ***");
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            logger.info("*** Clicking on MyAccount link ***");

            hp.clickLinkRegister();
            logger.info("*** Clicking on Register link ***");

            AccountRegistrationPage ar = new AccountRegistrationPage(driver);
            String firstName = randomString().toLowerCase();
            String lastName = randomString().toLowerCase();

            logger.info("*** Providing registration details ***");
            ar.setTextFirstName(firstName);
            ar.setTextLastName(lastName);
            ar.setTextEmail(firstName + "." + lastName + "@example.com");

            ar.setTextTelephone(randomNumeric());

            String password = randomAlphaNumeric();
            ar.setTextPassword(password);
            ar.setTextConfirmPassword(password);

            ar.setCheckSubscribe();
            ar.setCheckPrivacyPolicy();
            ar.clickButtonContinue();

            String msg1 = ar.confirmationMessage1();
            System.out.println(msg1);
            softAssert.assertEquals(msg1, "Your Account Has Been Created!", "Registration failed!");
            logger.info("*** Verify confirmation message: Your Account Has Been Created! ***");

            String msg2 = ar.confirmationMessage2();
            System.out.println(msg2);
            if (msg2.equals("Congratulations! Your new account has been successfully created!")) {
                softAssert.assertTrue(true);
            } else {
                logger.error("Test failed...");
                logger.debug("Debug logs...");
                softAssert.assertTrue(false);
            }
            logger.info("*** Verify confirmation message: Congratulations! Your new account has been successfully created! ***");

            MyAccountPage ma = new MyAccountPage(driver);
            ma.clickLogout();
            logger.info("*** Logout ***");
            Thread.sleep(3000);

            logger.info("*** Finished TC_RF_001_AccountRegistrationTest ***");

            // login
            logger.info("*** Starting TC_RF_002_LoginTest ***");

            hp.clickLinkMyAccount();
            logger.info("*** Clicking on MyAccount link ***");

            hp.clickLinkLogin();
            logger.info("*** Clicking on Login link ***");

            logger.info("*** Providing login details ***");
            LoginPage lp = new LoginPage(driver);
            lp.setTextEmail(prop.getProperty("email"));
            logger.info("*** Entering registered email ***");

            lp.setTextPassword(prop.getProperty("password"));
            logger.info("*** Entering registered password ***");

            lp.clickButtonLogin();
            logger.info("*** Clicking login button ***");

            try {
                boolean accountStatus = ma.isMyAccountPageExists();
                softAssert.assertEquals(accountStatus, true, "Login failed.");
            } catch (Exception e) {
                throw e;
            }

            logger.info("*** Verifying the existence of the account ***");

            logger.info("*** Finished TC_RF_002_LoginTest ***");

            // search product
            logger.info("*** Starting TC_RF_004_SearchProductTest ***");
            SearchProductPage sp = new SearchProductPage(driver);

            // search product name: iMac
            sp.setFieldSearch(prop.getProperty("searchProductName"));
            logger.info("*** Passing search product name from config.properties ***");

            sp.clickButtonSearch();
            logger.info("*** Clicking on search button ***");

            sp.clickViewOption();
            logger.info("*** Clicking on List view ***");

            sp.getiMac();
            logger.info("*** Clicking on iMac ***");
            System.out.println("iMac");

            Thread.sleep(2000);

            // search product name: MacBook Pro
            sp.setFieldSearch(prop.getProperty("searchProductName"));
            logger.info("*** Passing search product name from config.properties ***");

            sp.clickButtonSearch();
            logger.info("*** Clicking on search button ***");

            sp.getMacBookPro();
            logger.info("*** Clicking on MacBook Pro ***");
            System.out.println("MacBook Pro");

            Thread.sleep(2000);

            // search product name: MacBook
            sp.setFieldSearch(prop.getProperty("searchProductName"));
            logger.info("*** Passing search product name from config.properties ***");

            sp.getDropDownCategory();
            logger.info("*** Choosing option from dropdown list ***");

            sp.clickButtonSearch();
            logger.info("*** Clicking on search button ***");

            sp.productThumb();
            logger.info("*** Choosing one of the appeared products ***");

            sp.clickThumbnailImages();
            logger.info("*** Previewing thumbnail images of the product ***");

            sp.clickAddToWishList();
            logger.info("*** Clicking on Add to Wish List button ***");

            String msgWishList = sp.messageAddToWishList();
            System.out.println(msgWishList);
            if (msgWishList.contains("wish list")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying alert message after adding product to Add to Wish List ***");

            sp.tabs("Test the review section.");
            logger.info("*** Verifying the menu tabs ***");

            String textTitle = sp.getTitle();
            softAssert.assertEquals(textTitle, "MacBook", "Wrong product searched.");
            logger.info("*** Verifying the title of the product ***");

            String textPrice = sp.getPrice();
            if (textPrice.equals("$602.00")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying the price of the product ***");

            sp.setQuantity("10");
            logger.info("*** Updating the quantity of the product ***");

            sp.clickButtonAddCart();
            logger.info("*** Clicking on the Add to Cart button ***");

            String msgShoppingCart = sp.messageAddToCart();
            System.out.println(msgShoppingCart);
            if (msgShoppingCart.contains("shopping cart")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying alert message after adding product to shopping cart ***");

            sp.clickCompareProduct();
            logger.info("*** Clicking on Compare this Product button ***");

            String msgProductComparison = sp.messageCompareProduct();
            System.out.println(msgProductComparison);
            if (msgProductComparison.contains("product comparison")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying Compare this Product alert message ***");

            sp.clickLinkProductComparison();
            logger.info("*** Clicking on product comparison link ***");

            logger.info("*** Finished TC_RF_004_SearchProductTest ***");

            // product comparison
            ProductComparisonPage pc = new ProductComparisonPage(driver);

            String prodCompare = pc.getTitleProductComparison();
            System.out.println(prodCompare);
            if(prodCompare.contains("Product Comparison")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying Product Comparison heading ***");

            pc.removeiMac();
            logger.info("*** Removing iMac from product comparison page ***");

            // shopping cart
            logger.info("*** Starting TC_RF_005_ShoppingCartTest ***");
            ShoppingCartPage sc = new ShoppingCartPage(driver);
            sc.clickShoppingCart();
            logger.info("*** Clicking on Shopping Cart link ***");

            sc.clickLinkImage();
            logger.info("*** Clicking on product image link ***");

            sc.clickLinkText();
            logger.info("*** Clicking on product text link ***");

            sc.updateQuantity("MacBook", "2");
            logger.info("*** Updating the quantity of the product ***");

            String msgUpdate = sc.successMessage();
            if (msgUpdate.contains("Success")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(false);
            }
            logger.info("*** Verifying alert message after updating the quantity of the product ***");

            sc.clickAccordion();
            logger.info("*** Verifying to click accordion ***");

            sc.removeProduct("iMac");
            logger.info("*** Removing iMac from shopping cart page ***");

            sc.checkStockStatus("MacBook", "***");
            logger.info("*** Checking for text danger displayed ***");

            logger.info("*** Finished TC_RF_005_ShoppingCartTest ***");

        } catch (Exception e) {
            softAssert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }

        softAssert.assertAll();

        logger.info("*** Finished TC_RF_006_EndToEndTest ***");

    }
}
