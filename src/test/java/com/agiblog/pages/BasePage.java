package com.agiblog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected WebElement waitUntilVisible(By locator) {

        return wait.until(
                driver ->
                        driver.findElements(locator)
                                .stream()
                                .filter(WebElement::isDisplayed)
                                .findFirst()
                                .orElse(null)
        );
    }

    protected WebElement waitUntilClickable(By locator) {

        return wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator)
        );
    }

    protected void scrollIntoView(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});",
                        element
                );
    }
}