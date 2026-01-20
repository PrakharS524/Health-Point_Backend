package com.HealthPoint.util.mail;

public class UserRegisterSendOtpInMail {
	public boolean  userRegisterSendOtpInMail(String name, String user_email, int otp) {
        String to = user_email;  // Receiver's email
        String from = "mdjavedmansoori22@gmail.com";  // Sender's email
        String subject = "Email with Attachment";
        String messageText = "<html><body>"
                + "<p style='font-size:20px; font-family:Arial, sans-serif; color:#333;'>"
                + "Hello, <b>" + name + "</b>,<br><br>"
                + "Your OTP for verification is: <b style='font-size:22px; color:red;'>" + otp + "</b><br><span style='color:#ff0000; font-weight:bold;'>Please do not share this OTP with anyone.</span><br><br>"
                + "</p></body></html>";
 
        // This class method to call send OTP in email. 
		boolean sendEmailWithAttachment = new SendMail().sendEmailWithAttachment(to, from, subject, messageText);
		
		return sendEmailWithAttachment;
    }
}
