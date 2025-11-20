//file di package pages

package com.juaracoding.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    public static final int WAITING_TIME_IN_SECONDS = 10;
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME_IN_SECONDS));
    }

    public WebElement waitingElementReady(By elementBy) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated((elementBy)));
        return element;
    }
} 