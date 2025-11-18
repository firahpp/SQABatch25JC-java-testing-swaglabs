package com.juaracoding.swaglabs;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.listeners.ScreenshotListener;
import com.juaracoding.swaglabs.pages.InventoryPage;
import com.juaracoding.swaglabs.utils.ScreenshotUtil;

@Listeners(ScreenshotListener.class)
public class InventoryTest extends BaseTest {
  
  @Test(priority = 1)
  @Parameters({"username", "password"})
  public void addSingleProductToCartTest(String username, String password) throws InterruptedException {
    
    preTestLogin(username, password);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    
    InventoryPage inventoryPage = new InventoryPage(driver);

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();
    

    Assert.assertFalse(inventoryPage.getHeaderComponent().isInvisibleButtonAddToCart()); //ngecek apakah tombol ADD TO CART hilang
    Assert.assertTrue(inventoryPage.getHeaderComponent().isInvisibleButtonRemoveToCart());
   
    Assert.assertEquals(inventoryPage.getHeaderComponent().getTotalCart(), "1 ");

    

    // quitBrowser();
  }
}
