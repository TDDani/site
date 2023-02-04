package com.example.xowrld.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URL;

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
    public void beatbuyemail(String toEmail, String subject , String body, URL access) throws MessagingException {


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("dancmacabrewebsite@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject("Succesful Beat Purchase");
        String message = "Your purchase was succesfull, you can access your instrumental through the link below " + subject + "\r\n" + "\r\n" + "\r\n" + access;

        mimeMessageHelper.setText(message);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail with atatchment sent succesfully");


    }
}
