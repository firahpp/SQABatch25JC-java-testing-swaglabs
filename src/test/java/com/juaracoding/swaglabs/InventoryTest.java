package com.juaracoding.swaglabs;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.utils.ScreenshotUtil;

public class InventoryTest extends BaseTest {
  
  /**
   * Test Case ID: 
   *  TC-INV-001
   * 
   * Judul: 
   *  Pengguna menambahkan satu produk ke keranjang dari halaman inventaris.
   * 
   * Skenario: 
   *  Setelah login, pengguna berada di halaman inventaris dan menambahkan satu item ke keranjang belanja.
   * @throws InterruptedException
   */

  @Test(priority = 1)
  @Parameters({"username", "password"})
  public void addSingleProductToCartTest(String username, String password) throws InterruptedException {
    // 1. Login sebagai standard_user.
    preTestLogin(username, password);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    
    // 2. Di halaman inventaris, cari produk "Sauce Labs Backpack".
    WebElement buttonAddToCart = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
    
    // 3. Klik tombol "Add to cart" pada produk tersebut.
    buttonAddToCart.click();

    // 4. Tombol pada produk "sauce-labs-backpack" berubah menjadi "Remove".

    WebElement buttonRemove = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-backpack']"));
    String actualButtonCartText = buttonRemove.getText();
    String expectedButtonCartText = "Remove";
    Assert.assertEquals(actualButtonCartText, expectedButtonCartText);
    // 5. Ikon keranjang belanja di pojok kanan atas menampilkan angka "1".
    WebElement cartIcon = driver.findElement(By.xpath("//span[@data-test='shopping-cart-badge']"));
    String actualTotalCartText = cartIcon.getText();
    String expectedTotalCartText = "1";
    Assert.assertEquals(actualTotalCartText, expectedTotalCartText);

    String path = ScreenshotUtil.takeScreenshot(driver, "addSingleProductToCartTest_01");
    Reporter.log("<img style='width: 30%' src='" + path + "' />");
    Reporter.log("<br /> <strong>" + path + "</strong>");

    quitBrowser();
  }
}
