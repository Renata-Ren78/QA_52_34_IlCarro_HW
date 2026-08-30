package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxTimeCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxTimeCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
