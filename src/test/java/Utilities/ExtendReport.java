package Utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReport {
    static ExtentReports extent;
    static ExtentTest test;
    static ExtentSparkReporter htmlReporter;
    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static void setUpReport() {
        htmlReporter = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }
    
    public static void logInfo(WebDriver driver, String message) {
        String screenshotPath = BasePage.captureScreenshot(driver, "info_" + System.currentTimeMillis());
        test.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logPass(WebDriver driver, String message) {
    	String screenshotPath = BasePage.captureScreenshot(driver, "pass_" + System.currentTimeMillis());
        test.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logFail(String message) {
        test.log(Status.FAIL, message);
    }

    public static void tearDownReport() {
        extent.flush();
    }
}
