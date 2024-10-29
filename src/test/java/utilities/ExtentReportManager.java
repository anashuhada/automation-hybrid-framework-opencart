package utilities;

import org.testng.ITestListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        // Date dt = new Date();
        // String currentDateTimeStamp = df.format(dt);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // current time stamp

        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter("./reports/" + repName); // reports location

        sparkReporter.config().setDocumentTitle("OpenCart Automation Testing"); // title of the report
        sparkReporter.config().setReportName("OpenCart Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        // contains value
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // get the groups
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch(IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {

        extent.flush();

        // open the report on browser automatically
        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            if(extentReport.exists()) {
                System.out.println("Report file found: " + extentReport.getAbsolutePath());
                Desktop.getDesktop().browse(extentReport.toURI());
            }
            else {
                System.out.println("Report file not found: " + extentReport.getAbsolutePath());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

		/*// send the report automatically to team member
		try {
			URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + repName);

			// create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com"); // only for google
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("emailadd", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("senderemail"); // sender
			email.setSubject("Test results...");
			email.setMsg("Please find attach report...");
			email.addTo("receiveremail"); // receiver
			email.attach(url, "Extent Report", "Please check report...");
			email.send(); // send the email
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
    }

}
