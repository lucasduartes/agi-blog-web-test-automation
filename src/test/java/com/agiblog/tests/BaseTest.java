package com.agiblog.tests;

import com.agiblog.config.TestConfig;
import com.agiblog.driver.DriverFactory;
import com.agiblog.extensions.FailureEvidenceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(FailureEvidenceExtension.class)
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {

        driver =
                DriverFactory.createDriver();

        driver.get(
                TestConfig.getBaseUrl()
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}