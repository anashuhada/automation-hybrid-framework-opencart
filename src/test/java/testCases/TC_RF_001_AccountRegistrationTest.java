package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_RF_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verifyAccountRegistration() {
        logger.info("*** Starting TC_RF_001_AccountRegistrationTest ***");
        try {
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
            logger.info("*** Verifying expected message 1 ***");
            // Assert.assertEquals(msg1, "Your Account Has Been Created!", "Registration failed!");
            if(msg1.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
            }

            String msg2 = ar.confirmationMessage2();
            logger.info("*** Verifying expected message 2 ***");
            Assert.assertEquals(msg2, "Congratulations! Your new account has been successfully created!", "Registration failed!");
//            if(msg2.equals("Congratulations! Your new account has been successfully created!")) {
//                Assert.assertTrue(true);
//            }
//            else {
//                logger.error("Test failed...");
//                logger.debug("Debug logs...");
//                Assert.assertTrue(false);
//            }

        } catch(Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }

        logger.info("*** Finished TC_RF_001_AccountRegistrationTest ***");

    }
}
