package com.juaracoding.swaglabs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    /**
     * Mengambil screenshot dari halaman web saat ini.
     *
     * @param driver         WebDriver instance yang sedang berjalan.
     * @param screenshotName Nama dasar untuk file screenshot. Timestamp akan ditambahkan.
     * @return Path absolut dari file screenshot yang disimpan.
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Membuat format nama file dengan timestamp untuk memastikan keunikan
        String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        // Mengambil screenshot sebagai file
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        // Menentukan path tujuan
        // Screenshot akan disimpan di dalam folder "screenshots" di root project
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        
        try {
            // Menyalin file screenshot ke tujuan
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            System.out.println("Gagal mengambil screenshot: " + e.getMessage());
        }
        
        // Mengembalikan path file untuk logging atau reporting
        return destination;
    }
}
