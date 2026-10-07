package com.agiblog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    private final By articleTitles =
            By.cssSelector(
                    "article h2 a, " +
                    "article h3 a, " +
                    ".entry-title a"
            );

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String searchTerm) {

        String encodedSearchTerm =
                URLEncoder.encode(
                        searchTerm,
                        StandardCharsets.UTF_8
                );

        driver.get(
                "https://blog.agibank.com.br/?s="
                        + encodedSearchTerm
        );

        wait.until(
                webDriver ->
                        webDriver.getTitle() != null
                                && !webDriver.getTitle()
                                .trim()
                                .isEmpty()
        );
    }

    public List<String> getResultTitles() {

        return driver.findElements(articleTitles)
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .map(String::trim)
                .filter(title -> !title.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean hasResults() {
        return !getResultTitles().isEmpty();
    }

    public boolean containsSearchTerm(
            String searchTerm
    ) {

        return driver.getPageSource()
                .toLowerCase()
                .contains(
                        searchTerm.toLowerCase()
                );
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}