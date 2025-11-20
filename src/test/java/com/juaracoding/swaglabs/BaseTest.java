package com.juaracoding.swaglabs;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.juaracoding.swaglabs.listeners.ScreenshotListener;
import com.juaracoding.swaglabs.listeners.SendEmailReporter;
import com.juaracoding.swaglabs.pages.LoginPage;

@Listeners({ScreenshotListener.class, SendEmailReporter.class})
public abstract class BaseTest {

    protected WebDriver driver; //hanya bisa diakses oleh diri sendiri atau turunannya
    private boolean manualOpeningBrowser = false;

    public void setManualOpenBrowser() {
        manualOpeningBrowser = true;
      }
    
      public void setAutoOpenBrowser() {
       manualOpeningBrowser = false;
      }
    @BeforeMethod
    public void setUp(ITestContext context){
        // 1. Buat objek ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // 2. Siapkan Map untuk menyimpan preferensi
        Map<String, Object> prefs = new HashMap<String, Object>();

        // -- MENONAKTIFKAN POPUP TERSEBUT --
        
        // Matikan fitur "Offer to save passwords"
        prefs.put("credentials_enable_service", false);
        
        // Matikan password manager secara keseluruhan
        prefs.put("profile.password_manager_enabled", false);
        
        // INI YANG PENTING UNTUK GAMBAR KAMU: 
        // Matikan deteksi kebocoran password (Data Breach warning)
        prefs.put("profile.password_manager_leak_detection", false);
        
        // Opsional: Matikan safebrowsing jika masih muncul (kadang berpengaruh)
        prefs.put("safebrowsing.enabled", true); 

        // 3. Masukkan preferensi ke dalam options
        options.setExperimentalOption("prefs", prefs);

        // 4. (Opsional) Tambahkan argument untuk menghindari deteksi bot lainnya
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications"); // Mematikan notifikasi "Show Notifications"

        // 5. Jalankan Driver dengan options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        context.setAttribute("driver", driver);
        if (!manualOpeningBrowser) {
            openBrowser("https://www.saucedemo.com/");
        }
        
    
    }

    @AfterMethod
    public void teardown(ITestContext context) {
        if (driver != null) {
            driver.quit();
        }
    
    }

  public void openBrowser(String url) {
    driver.get(url);
  }

    public void navigateTo(String url){
        driver.get(url);
    }

    public void quitBrowser(){
        driver.quit();
    }

    public void preTestLogin(String username, String password) throws InterruptedException {
     LoginPage loginPage = new LoginPage(driver);
    loginPage.login(username, password, 500);
    }
    
}
