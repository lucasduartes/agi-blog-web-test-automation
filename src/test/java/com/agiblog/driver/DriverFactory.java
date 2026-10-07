package com.agiblog.driver;

import com.agiblog.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        ChromeOptions options =
                new ChromeOptions();

        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--window-size=1920,1080"
        );

        return new ChromeDriver(options);
    }
}