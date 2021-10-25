package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //CREATE BUG IN JIRA VIA API
        //API REQUEST TO QASE TO SET RED STATUS
        //TAKE SCREEN
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        AllureUtils.takeScreenshot(driver);
        System.out.println(String.format("======================================== FAILED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //START TEST RUN VIA API
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
