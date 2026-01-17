package com.HealthPoint.util;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Send_mail {
    public boolean  mail_information(String resis_full_name, String resis_email, int otp) {
        String to = resis_email;  // Receiver's email
        String from = "mdjavedmansoori22@gmail.com";  // Sender's email
        String subject = "Email with Attachment";
        String messageText = "<html><body>"
                + "<p style='font-size:20px; font-family:Arial, sans-serif; color:#333;'>"
                + "Hello, <b>" + resis_full_name + "</b>,<br><br>"
                + "Your OTP for verification is: <b style='font-size:22px; color:red;'>" + otp + "</b><br><span style='color:#ff0000; font-weight:bold;'>Please do not share this OTP with anyone.</span><br><br>"
                + "</p></body></html>";
        String rootPath = System.getProperty("user.dir");
        String filePath =rootPath+File.separator+"src" + File.separator + "main" + File.separator +
                "webapp" + File.separator + "assets" + File.separator+ File.separator+"mail_send_info"+ File.separator+"send-mail-image.jpg";
        //Send the email.
        sendEmailWithAttachment(to, from, subject, messageText, filePath);
		return true;
    }
    public static void mail_information_forget_password(String user_name, String user_email, String user_userName, String user_password) {
        String to = user_email;  // Receiver's email
        String from = "mdjavedmansoori22@gmail.com";  // Sender's email
        String subject = "Email with Attachment";
        String messageText = "<html><body>"
                + "<p style='font-size:20px; font-family:Arial, sans-serif; color:#333;'>"
                + "Hello, <b>" + user_name + "</b>,<br>"
                + "Your user name : <b style='font-size:22px; color:red;'>" + user_userName + "</b><br>"
                + "Your password : <b style='font-size:22px; color:red;'>" + user_password + "</b><br><span style='color:#ff0000; font-weight:bold;'>Please do not share this information anyone.</span>"
                + "</p></body></html>";

        String filePath ="C:\\Users\\ideapadGaming\\Documents\\EclipsWork\\EDUCATION25\\src\\main\\webapp\\assets\\web-relative-images\\mailImage.png";//Send the email.
        sendEmailWithAttachment(to, from, subject, messageText, filePath);
    }

    public static void sendEmailWithAttachment(String to, String from, String subject, String messageText, String filePath) {
        String host = "smtp.gmail.com";

        // Set up SMTP properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a mail session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mdjavedmansoori22@gmail.com", "vykn vdsn dfnc okme"); //Replace with Gmail App Password
            }
        });

        try {
            //Create an email message.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            //Create a multipart message.
            Multipart multipart = new MimeMultipart();

            // 1. Add text part.
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);

            // 2. Add attachment part**
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("Screenshot.png"); //Set file name
            multipart.addBodyPart(attachmentPart);

            // Attach multipart content to the message**
            message.setContent(multipart);

            //Send the email**
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
