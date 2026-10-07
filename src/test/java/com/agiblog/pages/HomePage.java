package com.agiblog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By searchButton =
            By.cssSelector("a.full-screen.astra-search-icon");

    private final By searchOverlay =
            By.cssSelector(".ast-search-box.full-screen");

    private final By searchInput =
            By.cssSelector(
                    ".ast-search-box.full-screen input.search-field"
            );

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openSearch() {

        WebElement button =
                waitUntilClickable(searchButton);

        scrollIntoView(button);

        button.click();
    }

    public boolean isSearchOverlayVisible() {

        return driver.findElements(searchOverlay)
                .stream()
                .anyMatch(WebElement::isDisplayed);
    }

    public boolean isSearchInputVisible() {

        return driver.findElements(searchInput)
                .stream()
                .anyMatch(WebElement::isDisplayed);
    }
}