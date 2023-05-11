package com.example.xowrld.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;

@Service
public class EmailSenderService {


    @Autowired
    private JavaMailSender javaMailSender;
    public void contactemail(String toEmail,  String subject, String body, String email) throws MessagingException {

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject("Site question");
        String message = "This message is from: " + subject + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" +
                body + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "Email: " + email;
        mimeMessageHelper.setText(message);




        javaMailSender.send(mimeMessage);

        System.out.println("Mail with atatchment sent succesfully");

    }
    public void beatbuyemail(String toEmail, String subject , String access) throws MessagingException {


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject("Succesful Beat Purchase");
        String message = "Your purchase was successfull, you can access your instrumental through the link below " + subject + "\r\n" + "\r\n" + "\r\n" + access;

        mimeMessageHelper.setText(message);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail with atatchment sent succesfully");


    }

    public void newsletteremail(String toname, String toemail, String title,String body) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toemail);
        mimeMessageHelper.setSubject(title);
        String message = "Hi, "+ toname + "\r\n" + "\r\n" +
                body + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "I hope you are doing fine, have a nice day";
        mimeMessageHelper.setText(message);




        javaMailSender.send(mimeMessage);

    }
    public void verificationsender(String toemail, String code) throws MessagingException{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toemail);
        mimeMessageHelper.setSubject("Verification Code");
        String message = "Hi, your verification code is: "+ code;
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessage);
    }

    public void forgotpasswordsender(String toemail, String code) throws MessagingException{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toemail);
        mimeMessageHelper.setSubject("Verification Code");
        String message = "Hi, your verification code for the password reset is: "+ code;
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessage);
    }

    public void verifypurchase(String toemail, String code) throws MessagingException{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toemail);
        mimeMessageHelper.setSubject("Verification Code");
        String message = "Hi, your verification code for the purchase is: "+ code;
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessage);
    }

    public void floatermachine(String toemail, String accessurl) throws MessagingException{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toemail);
        mimeMessageHelper.setSubject("Congratulations You Won");
        String message = "Congratulations, you won, here is the access link: "+ accessurl;
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessage);
    }
}


