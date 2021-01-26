package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot)DriverSingleton
                .getInstance())
                .getScreenshotAs(OutputType.FILE);

        Logger log = Logger.getLogger(TestListener.class);

        try {
            FileUtils.copyFile(screenCapture,
                    new File(String.format(".//target/screenshots/%s%s",getCurrentTime(), ".png")));
        } catch (IOException ex){
            log.error("Failed to save screenshot: " + ex.getMessage());
        }
    }

    private String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return Instant.now().atZone(ZoneId.systemDefault()).format(formatter);
    }
}