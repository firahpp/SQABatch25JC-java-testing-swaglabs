package com.juaracoding.swaglabs;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.pages.InventoryPage;
import com.juaracoding.swaglabs.pages.LoginPage;
import com.juaracoding.swaglabs.utils.MiscUtil;


public class InventoryTest extends BaseTest {
  private InventoryPage inventoryPage;
  private LoginPage loginPage;

  @Test(priority = 1, enabled = true)
  @Parameters({"username", "password"})
  public void addSingleProductToCartTest(String username, String password) throws InterruptedException {
    preTestLogin(username, password);

    inventoryPage = new InventoryPage(driver);

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-backpack']");

    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    Assert.assertFalse(inventoryPage.getHeaderComponent().isVisibleButtonAddToCart());
    Assert.assertTrue(inventoryPage.getHeaderComponent().isVisibleButtonRemoveToCart());

    Assert.assertEquals(inventoryPage.getHeaderComponent().getTotalCart(), 1);
  }

  @Test(priority = 2, enabled = true)
  @Parameters({"username", "password"})
  public void addMultipleProductToCartTest(String username, String password) throws InterruptedException {
    preTestLogin(username, password);
    List<Boolean> removeButtons = new ArrayList<>();

    inventoryPage = new InventoryPage(driver);

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-bike-light']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-backpack']");
    removeButtons.add(inventoryPage.getHeaderComponent().isVisibleButtonRemoveToCart());

    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-bike-light']");
    removeButtons.add(inventoryPage.getHeaderComponent().isVisibleButtonRemoveToCart());

    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-bolt-t-shirt']");
    removeButtons.add(inventoryPage.getHeaderComponent().isVisibleButtonRemoveToCart());

    int expected = 3;
    long actual = removeButtons.stream()
      .filter(n -> n)
      .count();

    Assert.assertEquals(actual, expected);
    Assert.assertEquals(inventoryPage.getHeaderComponent().getTotalCart(), expected);
  }
  @Test(priority = 3, enabled = true)
  @Parameters({"username", "password"})
  public void deleteProductFromInventariesPageTest(String username, String password) throws InterruptedException {
    preTestLogin(username, password);

    inventoryPage = new InventoryPage(driver);

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    Assert.assertEquals(inventoryPage.getHeaderComponent().getTotalCart(), 1);

    inventoryPage.getHeaderComponent().setButtonRemoveCart("//button[@data-test='remove-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().clickButtonRemoveCart();

    Assert.assertTrue(inventoryPage.getHeaderComponent().isVisibleButtonAddToCart());
    Assert.assertFalse(inventoryPage.getHeaderComponent().isVisibleCartIcon());
  }
  @Test(priority = 4, enabled = true)
  @Parameters({"username", "password"})
  public void orderLowToHighBasePriceTest(String username, String password) throws InterruptedException {  
    preTestLogin(username, password);

    inventoryPage = new InventoryPage(driver);
    inventoryPage.selectLowToHigh();

    Assert.assertTrue(MiscUtil.isSorted(inventoryPage.getPrices()));
  }

  @Test(priority = 5, enabled = true)
  public void forbidenAccessToInventoryPageWithoutLoginTest() {
    setManualOpenBrowser();
    openBrowser("https://www.saucedemo.com/inventory.html");
   

    loginPage = new LoginPage(driver);

    String expected = "https://www.saucedemo.com/";
    Assert.assertEquals(loginPage.getCurrentURL(), expected);


    expected = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    Assert.assertEquals(loginPage.getErrorMessage(), expected);
    setAutoOpenBrowser();
}

@Test(priority = 6, enabled = true)
  @Parameters({"username", "password"})
  public void resetApplicationSateAfterProductAddTest(String username, String password) throws InterruptedException {
    preTestLogin(username, password);

    List<Boolean> addToCartButtons = new ArrayList<>();
    inventoryPage = new InventoryPage(driver);

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-bike-light']");
    inventoryPage.getHeaderComponent().clickButtonAddToCart();

    Assert.assertEquals(inventoryPage.getHeaderComponent().getTotalCart(), 2);

    inventoryPage.getNavbarComponent().clickBurgerMenu();
    inventoryPage.getNavbarComponent().clickResetSideBar();

    Assert.assertFalse(inventoryPage.getHeaderComponent().isVisibleCartIcon());
    
    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    addToCartButtons.add(inventoryPage.getHeaderComponent().isVisibleButtonAddToCart());

    inventoryPage.getHeaderComponent().setButtonAddToCart("//button[@data-test='add-to-cart-sauce-labs-bike-light']");
    addToCartButtons.add(inventoryPage.getHeaderComponent().isVisibleButtonAddToCart());

    long actual = addToCartButtons.stream()
      .filter(n -> n)
      .count();

    Assert.assertEquals(actual, 2);
  }
} 
