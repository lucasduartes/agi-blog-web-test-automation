package com.agiblog.extensions;

import com.agiblog.tests.BaseTest;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class FailureEvidenceExtension
        implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable
    ) throws Throwable {

        Object testInstance =
                context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest) {

            BaseTest baseTest =
                    (BaseTest) testInstance;

            WebDriver driver =
                    baseTest.getDriver();

            if (driver != null) {

                attachCurrentUrl(driver);
                attachScreenshot(driver);
            }
        }

        throw throwable;
    }

    private void attachCurrentUrl(
            WebDriver driver
    ) {

        Allure.addAttachment(
                "URL no momento da falha",
                driver.getCurrentUrl()
        );
    }

    private void attachScreenshot(
            WebDriver driver
    ) {

        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        byte[] screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(
                                OutputType.BYTES
                        );

        Allure.addAttachment(
                "Screenshot da falha",
                "image/png",
                new ByteArrayInputStream(
                        screenshot
                ),
                ".png"
        );
    }
}