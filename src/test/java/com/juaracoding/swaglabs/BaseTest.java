package com.juaracoding.swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver; //hanya bisa diakses oleh diri sendiri atau turunannya

    //SATU METHOD UNTUK SATU AKSI

    public void openBrowser(){ //method compose
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
//     public BaseTest openBrowser() { //method chaining
//     driver = new FirefoxDriver();
//     driver.manage().window().maximize();
//     return this;
//   }

    public void navigateTo(String url){
        driver.get(url);
    }
    // public BaseTest navigateTo(String url) { //method chaining
    //     driver.get(url);
    //     return this;
    //   }

    public void quitBrowser(){
        driver.quit();
    }
    // public BaseTest quitBrowser() { //method chaining
    //     driver.quit();
    //     return this;
    //   }
    public void preTestLogin(String username, String password) throws InterruptedException {
    openBrowserAndNavigateTo("https://www.saucedemo.com/");
    Thread.sleep(500);
    driver.findElement(By.id("user-name")).sendKeys(username);
    Thread.sleep(500);
    driver.findElement(By.id("password")).sendKeys(password);
    Thread.sleep(500);
    driver.findElement(By.id("login-button")).click();
    }

    public void openBrowserAndNavigateTo(String url){
        openBrowser();
        navigateTo(url);
    }
    
    // public void openBrowserAndNavigateTo(String url) { //method chaining
    //     openBrowser().navigateTo(url);
    //   }

}
