package com.juaracoding.swaglabs.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class MiscUtil {
    public static boolean isSorted(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        return false; 
      }
    }

    return true;
  }

  public static boolean isSorted(ArrayList<BigDecimal> array) {

    for (int i = 0; i < array.size() - 1; i++) {
      if (array.get(i).compareTo(array.get(i + 1)) > 0) {
        return false; 
      }
    }

    return true;
  }

  public static BigDecimal convertToDecimalFrom(String value) {
   return new BigDecimal(value.replace("$", "").replace(",", ""));
  }

  public static ArrayList<BigDecimal> convertToDecimalsFrom(List<WebElement> elements) {
    ArrayList<BigDecimal> decimals = new ArrayList<>();

    for (WebElement value: elements) {
      decimals.add(convertToDecimalFrom(value.getText()));
    }

    return decimals;
  }
}
