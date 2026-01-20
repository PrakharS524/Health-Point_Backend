package com.HealthPoint.util.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	public boolean sendEmailWithAttachment(String to, String from, String subject, String messageText) {
        String host = "smtp.gmail.com";
        
        boolean result = false;

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

            // Attach multipart content to the message**
            message.setContent(multipart);

            //Send the email**
            Transport.send(message);
            
            result= true;
        } catch (MessagingException e) {
        	result= false;
            e.printStackTrace();
        }
        
       return result;
    }
}
