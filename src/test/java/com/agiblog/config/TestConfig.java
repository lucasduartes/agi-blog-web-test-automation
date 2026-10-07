package com.agiblog.config;

public final class TestConfig {

    private static final String DEFAULT_BASE_URL =
            "https://blogdoagi.com.br/";

    private TestConfig() {
    }

    public static String getBaseUrl() {
        return System.getProperty(
                "baseUrl",
                DEFAULT_BASE_URL
        );
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        "true"
                )
        );
    }
}