package com.myretail.util;

import org.springframework.stereotype.Component;

/**
 * @Aravind
 */
@Component
public class MyRetailUtil {

  public boolean isInteger(String inputId){
    try {
      Integer.parseInt(inputId);
    } catch(NumberFormatException | NullPointerException e) {
      return false;
    }
    return true;
  }
}
