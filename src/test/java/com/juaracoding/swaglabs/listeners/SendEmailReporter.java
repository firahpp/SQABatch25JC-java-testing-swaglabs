package com.juaracoding.swaglabs.listeners;

import java.io.IOException;
import java.util.List;

import org.testng.IReporter;

import com.juaracoding.swaglabs.utils.MailUtil;

public class SendEmailReporter implements IReporter {

  @Override
  public void generateReport(List xmlSuites, List suites, String outputDirectory) {
    System.out.println("=== ALL REPORTS GENERATED ===");
    try {
      // MailUtil.sendEmail();
      MailUtil.sendEmailHTMLRendered();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
