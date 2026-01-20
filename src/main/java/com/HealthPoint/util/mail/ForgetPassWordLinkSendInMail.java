package com.HealthPoint.util.mail;

public class ForgetPassWordLinkSendInMail {
	public boolean forgetPassWordLinkSendInMail(String name, String user_email, String user_name) {
        String to = user_email;  // Receiver's email
        String from = "mdjavedmansoori22@gmail.com";  // Sender's email
        String subject = "Email with Attachment";
        String messageText =
                "<html><body>"
              + "<p style='font-size:20px; font-family:Arial, sans-serif; color:#333;'>"
              + "Hello, <b>" + user_name + "</b>,<br><br>"
              + "Click the button below to reset your password:<br><br>"
              + "</p>"

              // BUTTON
              + "<a href='http://localhost:3000/forget-password?email=" + user_email + "' "
              + "style='display:inline-block; padding:12px 25px; "
              + "background-color:#007bff; color:#ffffff; text-decoration:none; "
              + "font-size:18px; font-weight:bold; border-radius:5px;'>"
              + "Reset Password"
              + "</a>"

              + "<br><br>"
              + "<span style='color:#ff0000; font-weight:bold;'>"
              + "Please do not share this link with anyone."
              + "</span>"
              + "</body></html>";


     //Send the email.
       boolean sendEmailWithAttachment = new SendMail().sendEmailWithAttachment(to, from, subject, messageText);
       
       return sendEmailWithAttachment;
    }

}
