package com.juaracoding.swaglabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailUtil {

  public static void sendEmail() throws FileNotFoundException, IOException {
    Properties properties = new Properties();
    properties.load(new FileInputStream("src/test/resources/mailtrap.properties"));

    Properties props = new Properties();
    props.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
    props.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
    props.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));

    // ==== ENABLE TLS ====
    props.put("mail.smtp.starttls.enable", properties.getProperty("mail.smtp.starttls.enable"));
    props.put("mail.smtp.starttls.required", properties.getProperty("mail.smtp.starttls.required"));
    
    String username = properties.getProperty("mail.smtp.username");
    String password = properties.getProperty("mail.smtp.password");

    // Auth SMTP
    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };

    Session session = Session.getInstance(props, auth);

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.from")));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(properties.getProperty("mail.smtp.to")));
      message.setSubject("Hasil Test Automation + Screenshot");

      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setText("Berikut laporan hasil testing otomatis beserta screenshot.", "utf-8");

      MimeBodyPart reportAttachment = new MimeBodyPart();
      File report = new File("target/surefire-reports/emailable-report.html");
      reportAttachment.attachFile(report);

      Multipart multipart = new MimeMultipart();

      multipart.addBodyPart(textPart);
      multipart.addBodyPart(reportAttachment);

      File screenshotFolder = new File("target/screenshots");
      if (screenshotFolder.exists()) {
        for (File file : screenshotFolder.listFiles()) {
          if (file.isFile()) {
            MimeBodyPart screenshotPart = new MimeBodyPart();
            screenshotPart.attachFile(file);
            multipart.addBodyPart(screenshotPart);
          }
        }
      }

      message.setContent(multipart);

      Transport.send(message);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String loadReportAsHtml() {
    try {
      File file = new File("target/surefire-reports/emailable-report.html");
      FileInputStream fis = new FileInputStream(file);
      byte[] data = fis.readAllBytes();
      fis.close();
      return new String(data, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
      return "<p>Gagal memuat HTML report.</p>";
    }
  }

  public static String buildInlineScreenshotsHtml() {
    StringBuilder sb = new StringBuilder();
    File folder = new File("target/screenshots");

    if (!folder.exists()) {
      return "<p>Tidak ada screenshot ditemukan.</p>";
    }

    for (File file : folder.listFiles()) {
      if (file.isFile()) {
        try {
          FileInputStream fis = new FileInputStream(file);
          byte[] imageBytes = fis.readAllBytes();
          fis.close();

          String base64 = java.util.Base64.getEncoder().encodeToString(imageBytes);

          sb.append("<h3>")
              .append(file.getName())
              .append("</h3>")
              .append(
                  "<img style='max-width: 600px; border:1px solid #ccc; margin-bottom:20px' src='data:image/png;base64,")
              .append(base64)
              .append("'/>");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();
  }

  public static void sendEmailHTMLRendered() throws FileNotFoundException, IOException {

    Properties properties = new Properties();
    properties.load(new FileInputStream("src/test/resources/mailtrap.properties"));

    Properties props = new Properties();
    props.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
    props.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
    props.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));

    String username = properties.getProperty("mail.smtp.username");
    String password = properties.getProperty("mail.smtp.password");

    // Auth SMTP
    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };

    Session session = Session.getInstance(props, auth);

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.from")));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(properties.getProperty("mail.smtp.to")));
      message.setSubject("Rendered Test Report + Inline Screenshots");

      // ===== Generate HTML Content =====
      String htmlReport = loadReportAsHtml();
      String screenshotsHtml = buildInlineScreenshotsHtml();

      String fullHtml = "<h2 style='color:#333;'>📄 Test Report</h2>" +
          htmlReport +
          "<hr/>" +
          "<h2 style='color:#333;'>📸 Screenshots</h2>" +
          screenshotsHtml;

      MimeBodyPart htmlBody = new MimeBodyPart();
      htmlBody.setContent(fullHtml, "text/html; charset=UTF-8");

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(htmlBody);

      message.setContent(multipart);

      Transport.send(message);

      System.out.println("Email HTML Rendered terkirim!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
